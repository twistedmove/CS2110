/** TIME SPENT: 2 hours and 0 minutes. */

package student;
import java.util.List;
import danaus.*;

public class Butterfly extends AbstractButterfly {

	private Direction d = Direction.E; //The direction East in which the butterfly flies.
	private boolean notDone = true; //When the butterfly has not finished its search and
									//the array of TileStates has not been properly constructed.
	/**
	 * Boustrophedonically search the map.
	 */
	public @Override TileState[][] learn() {
		TileState[][] t = new TileState [getMapHeight()][getMapWidth()];
		while (notDone){
			refreshState();
			t[state.location.row][state.location.col] = state;
			if ((state.location.col==0 && d == Direction.W) || 
					(state.location.col == getMapWidth() - 1 && d == Direction.E)){
				turn();
			}
			else{	
				try {
					fly(d, Speed.NORMAL);
				}
				catch (danaus.ObstacleCollisionException e) {		
					turn();			
				}
			}
		}	
			refreshState();
				t[state.location.row][state.location.col] = state;
				return t;
	}		

	/** If butterfly gets to bottom row, then change notDone to False 
	 * because the butterfly finished its search and the array of TileStates
	 * has been properly constructed. Change direction of butterfly to South
	 * and then change the direction in which the butterfly flies to the
	 * opposite direction.*/
	private void turn() {
		if (state.location.row == getMapHeight() - 1) {
			notDone = false;
		} else {
			fly(Direction.S, Speed.NORMAL);
			d = Direction.opposite(d);
			}
	}
	
	public @Override
	void run(List<Long> flowerIds) {
		// DO NOT IMPLEMENT FOR A3
	}

	public @Override
	List<Flower> flowerList() {
		// DO NOT IMPLEMENT FOR A3
		return null;
	}

	public @Override
	Location flowerLocation(Flower f) {
		// DO NOT IMPLEMENT FOR A3
		return null;
	}

	public @Override
	Location flowerLocation(long flowerId) {
		// DO NOT IMPLEMENT FOR A3
		return null;
	}
}