import static org.junit.Assert.*;
import org.junit.Test;


public class CircularLinkedListTester {

	@Test	
	public void testConstructor() {	
		CircularLinkedList<Integer> b= new CircularLinkedList<Integer>();	
		assertEquals("[]", b.toString());	
		assertEquals("[]", b.toStringReverse());	
		assertEquals(0, b.size());	
	}

	@Test
	public void testAppend(){
		CircularLinkedList<Integer> b = new CircularLinkedList<Integer>();
		b.append(3); 
		assertEquals("[3]", b.toString());
		assertEquals("[3]", b.toStringReverse()); 
		assertEquals(1, b.size());
		b.append(4); 
		assertEquals("[3, 4]", b.toString());
		assertEquals("[4, 3]", b.toStringReverse()); 
		assertEquals(2, b.size()); 
		b.append(5); 
		assertEquals("[3, 4, 5]", b.toString());
		assertEquals("[5, 4, 3]", b.toStringReverse()); 
		assertEquals(3, b.size()); 
	}

	@Test
	public void testPrepend(){
		CircularLinkedList<Integer> b = new CircularLinkedList<Integer>();
		b.prepend(3); 
		assertEquals("[3]", b.toString());
		assertEquals("[3]", b.toStringReverse()); 
		assertEquals(1, b.size());
		b.prepend(4); 
		assertEquals("[4, 3]", b.toString());
		assertEquals("[3, 4]", b.toStringReverse()); 
		assertEquals(2, b.size()); 
		b.prepend(5); 
		assertEquals("[5, 4, 3]", b.toString());
		assertEquals("[3, 4, 5]", b.toStringReverse()); 
		assertEquals(3, b.size()); 
	}

	@Test
	public void testInsertBefore(){
		CircularLinkedList<Integer> b = new CircularLinkedList<Integer>();
		CircularLinkedList<Integer> c = new CircularLinkedList<Integer>();

		c.append(1);
		c.append(2);
		c.insertBefore(3, c.getFirst());
		assertEquals("[1, 2, 3]", c.toString());
		assertEquals("[3, 2, 1]", c.toStringReverse());
		assertEquals(3, c.size());  

		b.append(5);
		b.insertBefore(0, b.getFirst()); 
		assertEquals("[5, 0]", b.toString());
		assertEquals("[0, 5]", b.toStringReverse());
		assertEquals(2, b.size());  

		b.insertBefore(2, b.getFirst().successor()); 
		assertEquals("[5, 2, 0]", b.toString()); 
		assertEquals("[0, 2, 5]", b.toStringReverse()); 
		assertEquals(3, b.size()); 

		b.insertBefore(1, b.getLast().predecessor());
		assertEquals("[5, 1, 2, 0]", b.toString()); 
		assertEquals("[0, 2, 1, 5]", b.toStringReverse()); 
		assertEquals(4, b.size());

		b.insertBefore(3, b.getLast()); 
		assertEquals("[5, 1, 2, 3, 0]", b.toString()); 
		assertEquals("[0, 3, 2, 1, 5]", b.toStringReverse()); 
		assertEquals(5, b.size());
	}

	@Test
	public void testInsertAfter(){
		CircularLinkedList<Integer> b = new CircularLinkedList<Integer>();
		CircularLinkedList<Integer> c = new CircularLinkedList<Integer>();

		c.append(1);
		c.append(2);
		c.insertAfter(3, c.getFirst());
		assertEquals("[1, 3, 2]", c.toString());
		assertEquals("[2, 3, 1]", c.toStringReverse());
		assertEquals(3, c.size());  

		b.append(5);
		b.insertAfter(0, b.getFirst()); 
		assertEquals("[5, 0]", b.toString());
		assertEquals("[0, 5]", b.toStringReverse());
		assertEquals(2, b.size());  

		b.insertAfter(2, b.getFirst().successor()); 
		assertEquals("[5, 0, 2]", b.toString()); 
		assertEquals("[2, 0, 5]", b.toStringReverse()); 
		assertEquals(3, b.size()); 

		b.insertAfter(1, b.getLast().predecessor()); 
		assertEquals("[5, 0, 1, 2]", b.toString()); 
		assertEquals("[2, 1, 0, 5]", b.toStringReverse()); 
		assertEquals(4, b.size());

		b.insertAfter(3, b.getLast()); 
		assertEquals("[5, 0, 1, 2, 3]", b.toString()); 
		assertEquals("[3, 2, 1, 0, 5]", b.toStringReverse()); 
		assertEquals(5, b.size());
	}

	@Test
	public void testRemove(){
		CircularLinkedList<Integer> b = new CircularLinkedList<Integer>();

		b.append(1);
		b.remove(b.getFirst());
		assertEquals("[]", b.toString());
		assertEquals("[]", b.toStringReverse());
		assertEquals(0, b.size());

		b.append(1);
		b.append(2);
		b.append(3);
		b.append(4); 
		b.append(5);
		b.append(6);

		b.remove(b.getFirst()); 
		assertEquals("[2, 3, 4, 5, 6]", b.toString()); 
		assertEquals("[6, 5, 4, 3, 2]", b.toStringReverse()); 
		assertEquals(5, b.size()); 

		b.remove(b.getFirst().successor()); 
		assertEquals("[2, 4, 5, 6]", b.toString()); 
		assertEquals("[6, 5, 4, 2]", b.toStringReverse()); 
		assertEquals(4, b.size()); 

		b.remove(b.getLast().predecessor());
		assertEquals("[2, 4, 6]", b.toString()); 
		assertEquals("[6, 4, 2]", b.toStringReverse()); 
		assertEquals(3, b.size()); 

		b.remove(b.getLast()); 
		assertEquals("[2, 4]", b.toString()); 
		assertEquals("[4, 2]", b.toStringReverse()); 
		assertEquals(2, b.size()); 

		CircularLinkedList<Integer>.Node last = b.getLast(); 
		b.remove(last);
		assertEquals("[2]", b.toString());
		assertEquals("[2]", b.toStringReverse());
		assertEquals(1, b.size());
	}

	@Test
	public void testStrings() {
		CircularLinkedList<String> x = new CircularLinkedList<String> ();
		
		x.append("World");
		assertEquals("[World]", x.toString());
		assertEquals("[World]", x.toStringReverse());
		assertEquals(1,x.size());
		
		x.prepend("Hello");
		assertEquals("[Hello, World]", x.toString());
		assertEquals("[World, Hello]", x.toStringReverse());
		assertEquals(2, x.size());
		
		x.insertBefore("to", x.getLast());
		assertEquals("[Hello, to, World]", x.toString());
		assertEquals("[World, to, Hello]", x.toStringReverse());
		assertEquals(3, x.size());
		
		x.insertAfter("the", x.getFirst().successor());
		assertEquals("[Hello, to, the, World]", x.toString());
		assertEquals("[World, the, to, Hello]", x.toStringReverse());
		assertEquals(4, x.size());
		
		x.remove(x.getFirst());
		assertEquals("[to, the, World]", x.toString());
		assertEquals("[World, the, to]", x.toStringReverse());
		assertEquals(3, x.size());
	}


}
