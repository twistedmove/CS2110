/* Time spent on a2:  10 hours and 00 minutes.
 * Name: Pujaa Rajan and Jess Yuan
 * Netid: pr338 and jyy23
 * What I thought about this assignment:
 * I found this assignment interesting
 *and learned a lot doing it.
 */

/** An instance is a circular doubly linked list. */
public class CircularLinkedList<E> {
	private Node head; // a node of linked list (null if none)
	private int size;  // Number of nodes in linked list.

	/** Constructor: an empty linked list. */
	public CircularLinkedList() {
	}

	
	/** Return the number of values in this list. */
	public int size() {
		return size;
	}

	
	/** Return the first node of the list (null if the list is empty). */
	public Node getFirst() {
		return head;
	}

	
	/** Return the last node of the list (null if the list is empty). */
	public Node getLast() {
		if (head == null)
			return null;
		return head.pred;
	}

	
	/** If this circular list is empty, return null.
	 *  Otherwise, move down the list in circular fashion, so that the
	 *  first node becomes the last node, the second becomes the first, ec. */
	public Node moveDown() {
		if (head != null) {
			head= head.succ;
		}
		return head;
	}

	
	/** Return the value of node e of this list.
	 * Precondition: e must be a node of this list; it may not be null. */
	public E valueOf(Node e) {
		assert e != null;
		return e.value;
	}

	
	/** Return a representation of this list: its values, with adjacent
	 * ones separated by ", ", "[" at the beginning, and "]" at the end. <br>
	 * 
	 * E.g. for the list containing 6 3 8 in that order, the result it "[6, 3, 8]". */
	public String toString() {
		/* Note: This method should NOT refer to field size. It refers to
		 * field head and all the succ fields of the nodes. Reason: It allows
		 * toString to be used in testing head and all the succ fields. */

		if (head==null){
			return "[]";

		}
		Node temp = getFirst(); 
		String string=""; 

		string = "" + temp.value;   
		Node second = head.succ;
		while (second!=head){
			string += ", " + second.value;
			second=second.successor();
		}
		return "[" + string + "]"; 
	}   

	
	/** Return a representation of this list: its values in reverse, with adjacent
	 * ones separated by ", ", "[" at the beginning, and "]" at the end. <br>
	 * 
	 * E.g. for the list containing 6 3 8 in that order, the result it "[8, 3, 6]".*/
	public String toStringReverse() {
		/* Note: This method should NOT refer to field size. It refers to
		 * field head and all the pred fields of the nodes. Reason: It allows
		 * toStringReverse to be used in testing head and all the pred fields. */

		if (head == null){
			return "[]";
		}
		Node last =  getLast();
		String string = "";

		string = "" + last.value;   
		Node second = last.pred;
		while (second!= last){
			string = string + ", " + second.value; 
			second = second.pred;
		}
		return "[" + string + "]"; 
	}

	
	/** Append value v to the list. */
	public void append(E v) {
		/* Note: this method views the list as a list with a first and
		 * a last value. It adds a new value at the end, not changing any
		 * others. */

		if (head == null) {
			head= new Node(null, v, null);
			head.pred = head;
			head.succ = head;
			size = 1; 

		} else { 
			Node ntail = new Node(getLast(),v, head);
			getLast().succ = ntail;
			head.pred = ntail;
			size += 1; 

		}
	}

	
	/** Prepend value v to the list. */
	public void prepend(E v) {
		/* Note: this method views the list as a list with a first and
		 * a last value. It adds a new value at the beginning, so head
		 * should end up pointing to the new node. */

		append(v);
		head = head.pred;
	}

	
	/** Insert value v in a new node before node e of this circular list.
	 * Precondition: e must be a node of this list, i.e. it may not be null. */
	public void insertBefore(E v, Node e) {
		/* Note: This method views the list as a circular list, so it doesn't
		 * really matter which node head points to when the method is done.
		 * However, we require that head does not change. */

		assert e!=null; 
		Node temp = e.pred;
		Node Insert = new Node (temp, v, e); 
		temp.succ = Insert; 
		e.pred = Insert;
		size += 1;
	}

	
	/** Insert value v in a new node after node e.
	 * Precondition: e must be a node of this list, i.e. it may not be null. */
	public void insertAfter(E v, Node e) {
		/* Note: This method views the list as a circular list, so it doesn't
		 * really matter which node head points to when the method is done.
		 * However, we require that head does not change. */

		assert e!=null; 
		Node temp = e.succ;
		Node Insert = new Node (e, v, temp); 
		temp.pred = Insert; 
		e.succ = Insert;
		size += 1;

	}

	
	/** Remove node e from this list.
	 *  Precondition: e must be a node of this list, i.e. it may not be null. */
	public void remove(Node e) {
		/* Note: if the head (first) node is being removed and size >= 2, head
		 * should end up pointing at head's successor. */
		
		assert e != null;
		if (size() >= 2 && head ==e) {
        	head = head.succ;
        }
       if (size == 1) {
        	head = null;
        }
        else 
        {e.pred.succ = e.succ;
        e.succ.pred = e.pred;}
        
       size = size - 1;
	}

	/*********************/
	
	/** An instance is a node of this list. */
	public class Node {
		/** Predecessor of this node on the list (null if the list is empty). */
		private Node pred;
		
		/** The value of this element. */
		private E value; 

		/** Successor of this node on the list. (null if the list is empty). */
		private Node succ; 

		/** Constructor: an instance with predecessor p (p can be null),
		 * successor s (s can be null), and value v. */
		private Node(Node p, E v, Node s) {
			pred= p;
			value= v;
			succ= s;
		}

		/** Return the value of this node. */
		public E getValue() {
			return value;
		}

		/** Return the predecessor of this node e in the list. */
		public Node predecessor() {
			return pred;
		}

		/** Return the successor of this node in this list. */
		public Node successor() {
			return succ;
		}
	}

}