import java.util.*;

/** An instance represents a Solver that intelligently determines 
 *  Moves using algorithm Minimax. */
public class AI implements Solver {

    private Board.Player player; // the current player

    /** The depth of the search in the game space when evaluating moves. */
    private int depth;

    /** Constructor: an instance with player p who searches to depth d
     * when searching the game space for moves. */
    public AI(Board.Player p, int d) {
        player= p;
        depth= d;
    }

    /** See Solver.getMoves for the specification. */
    public @Override Move[] getMoves(Board b) {
    	LinkedList<Move> preferredMoves = new LinkedList<Move>();
		State s = new State(player, b, null);
		int max = Integer.MIN_VALUE;
		createGameTree(s, depth);
		minimax(s);

		for (State t : s.getChildren()) {
			if (t.getValue() > max) {
				max = t.getValue();
			}
		}
		for (State u : s.getChildren()) {
			if (u.getValue() == max) { 
				preferredMoves.add(u.getLastMove());
			}
		}
		return preferredMoves.toArray(new Move[preferredMoves.size()]);
    }

    /** Generate the game tree with root s of depth d.
     * The game tree's nodes are State objects that represent the state of a game
     * and whose children are all possible States that can result from the next move.
     * NOTE: this method runs in exponential time with respect to d.
     * With d around 5 or 6, it is extremely slow and will start to take a very
     * long time to run.
     * Note: If s has a winner (4 in a row), it should be a leaf. */
    public static void createGameTree(State s, int d) {
        // Note: This method must be recursive, recursing on d,
        // which should get smaller with each recursive call
    	if (d==0){ // base case
    		return ;
    	}
    	
    	if (d > 0) { //if depth is bigger than 0 and 
			s.initializeChildren();
			for (State c : s.getChildren()) {
				createGameTree(c, d - 1);
			}
		}
	}
    
    
    /** Call minimax in ai with state s. */
    public static void minimax(AI ai, State s) {
        ai.minimax(s);
    }

    /** State s is a node of a game tree (i.e. the current State of the game).
     * Use the Minimax algorithm to assign a numerical value to each State of the
     * tree rooted at s, indicating how desirable that State is to this player. */
    public void minimax(State s) {
    	if (s.getChildren().length == 0) {
			evaluateBoard(s.getBoard());
			s.setValue(evaluateBoard(s.getBoard()));
			return;
		}

		for (State child : s.getChildren()) {
			minimax(child);
		}

		int bestValue = Integer.MIN_VALUE;
		for (State kid : s.getChildren()) {
			if (kid.getValue() >= bestValue) {
				bestValue = kid.getValue();
			}
		}
		int worstValue = Integer.MAX_VALUE;
		for (State kid : s.getChildren()) {
			if (kid.getValue() <= worstValue) {
				worstValue = kid.getValue();
			}

		}
		if (player == s.getPlayer()) {
			s.setValue(bestValue);
		} else {
			s.setValue(worstValue);
		}
    }

    /** Evaluate the desirability of Board b for this player
     * Precondition: b is a leaf node of the game tree (because that is most
     * effective when looking several moves into the future). 
     * The desireability is calculated as follows.
     * 1. If the board does not have a winner: */
    public int evaluateBoard(Board b) {
        Board.Player winner= b.hasConnectFour();
        if (winner == null) {
            // Store in sum the value of board b. 
            int sum= 0;
            List<Board.Player[]> locs= b.winLocations();
            for (Board.Player[] loc : locs) {
                for (Board.Player p : loc) {
                    sum= sum + (p == player ? 1 : p != null ? -1 : 0);
                }
            }
            return sum;
        }
        // There is a winner
        int numEmpty= 0;
        for (int r= 0; r < Board.NUM_ROWS; r= r+1) {
            for (int c= 0; c < Board.NUM_COLS; c= c+1) {
                if (b.getTile(r, c) == null) numEmpty += 1;
            }
        }
        return (winner == player ? 1 : -1) * 10000 * numEmpty;

    }
    

}
