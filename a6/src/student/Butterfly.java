/** TIME SPENT: # hours and # minutes. */

package student;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import org.w3c.dom.Node;

import danaus.*;

public class Butterfly extends AbstractButterfly {
	public TileState[][] ts;
	public Node[][] runMap;
	public HashMap<Long, TileState> kflowers;


	private class Node implements Comparable<Node>{
		private TileState tile;
		private int dist;
		private Node prev;
		private boolean flyable;

		private Node(TileState s){
			tile = s;
			prev = null;
			dist = Integer.MAX_VALUE;
		}

		private List<Node> getChildren(){
			LinkedList<Node> children = new LinkedList<Node>();
			for (Direction d : Direction.values()){
				int y = Common.mod(tile.location.row + d.dRow, getMapHeight());
				int x = Common.mod(tile.location.col + d.dCol, getMapWidth());
				if (runMap[y][x].flyable){
					children.add(runMap[y][x]);
				}
			}
			return children;
		}

		public int compareTo(Node o){
			return (this.dist - o.dist);
		}
	}

	private void makeRunMap(){
		runMap = new Node[getMapHeight()][getMapWidth()];
		for (int row = 0; row < getMapHeight(); row++){
			for(int col = 0; col < getMapWidth(); col++){
				runMap[row][col] = new Node(ts[row][col]);
			}
		}
	}

	public @Override //ask about this override
	TileState[][] learn() {
		ts = new TileState[getMapHeight()][getMapWidth()];
		kflowers = new HashMap<Long, TileState>(); 
		dfs();
		return ts;
	}

	public void dfs() {
		refreshState();
		ts[state.location.row][state.location.col] = state;
		for (Flower f : state.getFlowers()){
			kflowers.put(f.getFlowerId(), state);
		}

		for (Direction d : Direction.values()) {
			int tsCol = state.location.col;
			int tsRow = state.location.row;
			int y = Common.mod(tsRow + d.dRow, getMapHeight());
			int x = Common.mod(tsCol + d.dCol, getMapWidth());
			try {
				if (ts[y][x] == null) {
					fly(d, Speed.NORMAL);
					refreshState();
					ts[y][x] = state;

					dfs();

					fly(Direction.opposite(d), Speed.NORMAL);
					refreshState();
				}

			} catch (ObstacleCollisionException c) {
				ts[y][x] = TileState.nil;
			}
		}
	}

	public @Override void run(List<Long> flowerIds) {
		LinkedList<Long> uncollected = new LinkedList<Long>(flowerIds);
		int tsCol = state.location.col;
		int tsRow = state.location.row;
		makeRunMap();	
		for (Long id : flowerIds){
			refreshState();
			System.out.println("1");
			List<Node> path = djk(new Node (state),new Node (kflowers.get(id)));
			System.out.println("2");

			while (kflowers.keySet().contains(id)){
				System.out.println("3");
				makeRunMap();	
				System.out.println("4");

				if (uncollected.contains(id)){	
					System.out.println("5");

					flyonestep(path);
					System.out.println("6");

					for (Flower f : state.getFlowers()){

						System.out.println("7");

						if (f.getFlowerId() == id){
							collect(f);
							uncollected.remove(id);
						}
					} 
				}

				else {	
					while (uncollected.contains(id)){	

						for (Direction d : Direction.values()){
							Double oldSmell = smell().intensity;
							int y = Common.mod(tsRow + d.dRow, getMapHeight());
							int x = Common.mod(tsCol + d.dCol, getMapWidth());
							try {
								if (ts[y][x] != null){ 
									fly(d, Speed.NORMAL);
								}
								else if (smell().intensity > oldSmell) {
									if (state.getFlowers() != null){
										for (Flower f : state.getFlowers()){
											System.out.println(state.getFlowers());
											if (f.getFlowerId() == id){
												collect(f);
												uncollected.remove(id);
											} 
										}
									}
									else{	
										fly(Direction.opposite(d), Speed.NORMAL);
									}
								}
							}
							catch (ObstacleCollisionException c) {
								ts[y][x] = TileState.nil;	
							}
						}
					}
				}
			}
		}
	}

		public void flyonestep(List<Node> path){
			for (int x = 0; x <path.size()-1; x++){
				int r = path.get(x+1).tile.location.row - path.get(x).tile.location.row;
				int c =  path.get(x+1).tile.location.col - path.get(x).tile.location.col;		
				if (r == -1 && c == 0){
					fly(Direction.N, Speed.NORMAL);
				}
				else if (r == -1 && c == 1){
					fly(Direction.NE, Speed.NORMAL);
				}
				else if (r == 0 && c == 1){
					fly(Direction.E, Speed.NORMAL);
				}
				else if (r == 1 && c == 1){
					fly(Direction.SE, Speed.NORMAL);
				}
				else if (r == 1 && c == 0){
					fly(Direction.S, Speed.NORMAL);
				}
				else if (r == 1 && c == -1){
					fly(Direction.SW, Speed.NORMAL);
				}
				else if (r == 0 && c == 1){
					fly(Direction.W, Speed.NORMAL);
				}
				else if (r == -1 && c == -1){fly(Direction.NW, Speed.NORMAL);
				}

				refreshState();
			}
		}


		//**Return the most intense smelling flower in relation to where the 
		//butterfly is now**// 
		public Aroma smell(){ 
			refreshState(); 
			LinkedList<Aroma>smell= new LinkedList<Aroma>(); 
			double Largest=Integer.MIN_VALUE;
			for (Aroma s: state.getAromas()) {
				if (s.intensity > Largest){
					Largest = s.intensity;
					smell.add(s);
				}
			}
			return smell.getLast();
		}


		public List<Node> djk(Node s, Node e){
			s.dist = 0;
			PriorityQueue<Node> p = new PriorityQueue<Node>();
			p.add(s);

			while(!p.isEmpty() && !p.peek().equals(e)){
				Node n = p.poll();
				System.out.println(n + "node");

				for (Node c : n.getChildren())
					if(n.dist + 1 < c.dist){
						c.dist = n.dist+1;
						c.prev = n;
						if (!p.contains(c)){
							p.add(c);
						}
					}
			}	
			LinkedList<Node> path = new LinkedList<Node>();
			Node current = e;
			System.out.println(current + "e");

			while (current != null){
				path.push(current);
				current = current.prev;
				System.out.println(current + "current");
			}
			return path;

		}
	}