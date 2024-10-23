//Smith   Benjamin
//COSC-311 fall2024
//Project 1


package main;

public class IndexLinked 
{
//ordered array

	   public String ID; // Keep the ID field
	    private Node iterator; // Iterator for traversing the linked list
	    private Node front, back; // Doubly-ended pointers for the linked list
	    private int elements; // Count of elements in the list

	    // Constructor
	    public IndexLinked() {
	        this.front = null;
	        this.back = null;
	        this.iterator = null;
	        this.elements = 0;
	    }
	// Sets the the iterator to 0:
	    public void iteratorInitFront() {
	        iterator = front;
	    }

	    public void iteratorInitBack() {
	        iterator = back;
	    }

	// Method to get the record using linear search
	    public int find(String key) {
	        Node current = front; // Start from the front of the list
	        while (current != null) {
	            if (current.getData().getKey().equalsIgnoreCase(key)) {
	                // If the key matches, return the 'where' field of the IndexRecord
	                return current.getData().getWhere();
	            }
	            current = current.getNext(); // Move to the next node
	        }
	        return -1; // Key not found
	    }

	public boolean hasNext() {
	    return iterator != null && iterator.getNext() != null;
	}

	public boolean hasPrevious() {
	    return iterator != null && iterator.getPrevious() != null;
	}

	// Return the 'where' field from IndexRecord; then increment the iterator:
	
	//make sure that the indexRecord reflects the new positions of all elements after the removal.
	public void updateIndicesAfterRemoval(int removedIndex) {
	    Node current = front;
	    while (current != null) {
	        int where = current.getData().getWhere();
	        if (where > removedIndex) {
	            // If the 'where' value is greater than the removed index, decrement it
	            current.getData().setWhere(where - 1);
	        }
	        current = current.getNext(); // Move to the next node
	    }
	}
	public int getNext() {
	    if (!hasNext()) {
	        return -1; // Indicate no more elements
	    }
	    iterator = iterator.getNext();
	    return iterator.getData().getWhere();
	}

	public int getPrevious() {
	    if (!hasPrevious()) {
	        return -1; // Indicate no more elements
	    }
	    iterator = iterator.getPrevious();
	    return iterator.getData().getWhere();
	}

	public void insert(IndexRecord record) {
	    Node newNode = new Node(record);
	    if (front == null) { // Empty list
	        front = back = newNode;
	    } else {
	        Node current = front;
	        while (current != null && current.getData().getKey().compareTo(record.getKey()) < 0) {
	            current = current.getNext();
	        }
	        if (current == null) { // Insert at the end
	            back.setNext(newNode);
	            newNode.setPrevious(back);
	            back = newNode;
	        } else if (current == front) { // Insert at the front
	            newNode.setNext(front);
	            front.setPrevious(newNode);
	            front = newNode;
	        } else { // Insert in the middle
	            Node previous = current.getPrevious();
	            previous.setNext(newNode);
	            newNode.setPrevious(previous);
	            newNode.setNext(current);
	            current.setPrevious(newNode);
	        }
	    }
	    elements++;
	}

	public boolean remove(String key) {
	    Node current = front;
	    while (current != null && !current.getData().getKey().equalsIgnoreCase(key)) {
	        current = current.getNext();
	    }
	    if (current == null) { // Key not found
	        return false;
	    }
	    if (current == front) { // Removing front
	        front = front.getNext();
	        if (front != null) {
	            front.setPrevious(null);
	        } else {
	            back = null; // List is now empty
	        }
	    } else if (current == back) { // Removing back
	        back = back.getPrevious();
	        back.setNext(null);
	    } else { // Removing from the middle
	        Node previous = current.getPrevious();
	        Node next = current.getNext();
	        previous.setNext(next);
	        next.setPrevious(previous);
	    }
	    elements--;
	    return true;
	}


}