package trivia.utils;

public class CircularLinkedList<T> {
    private CircularLinkedListNode<T> head;

    public T getHead() {
        if(head == null) return null;
        return head.getData();
    }

    public CircularLinkedListNode<T> getHeadNode() {
        if(head == null) return null;
        return head;
    }

    public T next() {
        if (head == null) return null;
        head = head.getNext();
        return head.getPrev().getData();
    }

    public void addFirst(T element) {
        head = addNode(element);
    }

    public void addLast(T element) {
        addNode(element);
    }

    public void remove(T element) {
        if (head == null) return;

        CircularLinkedListNode<T> current = head;

        do {
            if (current.getData().equals(element)) {
                if (current == head) {
                    if (head.getNext() == head) { // Single node case
                        head = null;
                    } else {
                        head = head.getNext();
                    }
                }
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
                return;
            }
            current = current.getNext();
        } while (current != head);
    }

    private CircularLinkedListNode<T> addNode(T element) {
        CircularLinkedListNode<T> node = new CircularLinkedListNode<>(element);

        if (head == null) {
            head = node;
            head.setNext(head);
            head.setPrev(head);
            return node;
        }

        CircularLinkedListNode<T> tail = head.getPrev();
        tail.setNext(node);
        node.setPrev(tail);
        node.setNext(head);
        head.setPrev(node);

        return node;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        if (head == null) return 0;

        CircularLinkedListNode<T> current = head;
        int count = 0;
        do {
            count++;
            current = current.getNext();
        } while (current != head);

        return count;
    }
}
