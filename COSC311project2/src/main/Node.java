package main;
public class Node {
    private IndexRecord data;
    private Node next;
    private Node previous;

    public Node(IndexRecord data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public IndexRecord getData() {
        return data;
    }

    public void setData(IndexRecord data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}