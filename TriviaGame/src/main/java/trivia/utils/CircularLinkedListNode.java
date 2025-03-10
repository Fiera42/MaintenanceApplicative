package trivia.utils;

public class CircularLinkedListNode<T> {
    private final T data;
    private CircularLinkedListNode<T> next;
    private CircularLinkedListNode<T> prev;

    public CircularLinkedListNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public CircularLinkedListNode<T> getNext() {
        return next;
    }

    public CircularLinkedListNode<T> getPrev() {
        return prev;
    }

    public void setNext(CircularLinkedListNode<T> next) {
        this.next = next;
    }

    public void setPrev(CircularLinkedListNode<T> prev) {
        this.prev = prev;
    }
}
