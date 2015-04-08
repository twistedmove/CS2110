import java.util.*;

/**
 * An instance is a node in a binary tree; it may be viewed also as the root of
 * a tree
 */
public class TreeNode {
	private int val; // The value in the node
	private TreeNode left; // The left subtree (null if empty)
	private TreeNode right; // The right subtree (null if empty)

	/** Return the size of the tree with this as the root. */
	public int size() {
		if (this == null) {
			return 0;
		}
		return 1 + size(this.left) + size(this.right);
	}

	/** Return the size of the tree whose root is r. */
	public static int size(TreeNode r) {
		if (r == null) {
			return 0;
		}
		return 1 + size(r.left) + size(r.right);
	}

	/** Return the number of leaves of a binary tree. */
	public static int staticNumberOfLeaves(TreeNode t) {
		if (t != null && t.left == null && t.right == null) {
			return 1;
		}
		return staticNumberOfLeaves(t.right) + staticNumberOfLeaves(t.left);
	}

	/** Return the number of leaves of a binary tree. */
	public int nonstaticNumberOfLeaves(TreeNode t) {
		if (t != null && t.left == null && t.right == null) {
			return 1;
		}
		return staticNumberOfLeaves(t.right) + staticNumberOfLeaves(t.left);
	}

	
	/** Returns the value of a to the nth power = a**n. Precondition: n >= 0 */
	static int power(int a, int n) {
		if (n == 0)
			return 1;
		if (n % 2 == 0)
			return power(a * a, n / 2);
		return a * power(a, n - 1);
	}

	 /** Returns true if all the letters of the first word x are contained in the second s, but in the same order.*/
	public boolean isXish(String x, String s) {
		if (x.length() == 0) {
			return true;
		}
		if (s.indexOf(s.charAt(0)) != -1) {
			return isXish(s.substring(1), s.substring(s.indexOf(s.charAt(0))));
		} else {
			return false;
		}
	}

	/** Returns true if all the letters of elf are contained in the second b.*/
	public boolean isElfish(String x, String b) {
		return elfishHelper(x, b);
	}

	/** Returns true if all the letters of elf are contained in the second b.*/	
	public boolean elfishHelper(String x, String b) {
		if (x.length() == 0) {
			return true;
		}
		int y = b.indexOf(x.charAt(0));
		if (y != -1) {
			return elfishHelper(x.substring(1),
					b.substring(0, y) + b.substring(y + 1));
		} else {
			return false;
		}
		}

	
	/**Return a set of all permutations of s. Precondition: s contains no duplicates --all chars are different*/
	public static Set<String> permutations(String s) {
		 int N = s.length();
	       char[] a = new char[N];
	       for (int i = 0; i < N; i++){
	           a[i] = s.charAt(i);
	       permutations(s);
	       }
		return null;
	}

	
	// Write your coin-game stuff here (it is optional)

	
	/** Constructor: a one-node tree with value v */
	public TreeNode(int v) {
		val = v;
	}

	/**
	 * Constructor: a treee with value v, left subtree left, and right subtee
	 * right
	 */
	public TreeNode(int v, TreeNode left, TreeNode right) {
		val = v;
		this.left = left;
		this.right = right;
	}

	/**
	 * a representation of this tree. The root appears on one line. then its
	 * left subtree indented 2 spaces in the same representation (null if none)
	 * then its right subtree similarly. If the tree is a leaf, its subtrees do
	 * not appear.
	 */
	public @Override
	String toString() {
		return toString("");
	}

	/**
	 * Like toString() except that everything is indented by sp (assumed to be a
	 * string with blanks in it)
	 */
	public String toString(String sp) {
		if (left == null && right == null) {
			return sp + val;
		}
		return sp
				+ val
				+ "\n"
				+ (left == null ? sp + "  " + null : left.toString(sp + "  "))
				+ "\n"
				+ (right == null ? sp + "  " + null : right.toString(sp + "  "));
	}

	/** = a tree (1 2 (3 (4 5 6) null)) */
	public static TreeNode tree1() {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		return new TreeNode(1, t2, new TreeNode(3, new TreeNode(4, t5, t6),
				null));
	}

}
