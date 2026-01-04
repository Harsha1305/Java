/* Author:Harshavardhan Narayanaswamy
................................
Implementation of Double LinkedList in java
................................
Jan 03 2026 */

class Node<T> {
    public T data;
    public Node<T> nextRef;

    public Node(T data) {
        this.data = data;
    }

//    public void displayNode() {
//        System.out.println("data: " + data);
//    }
}

class SingleLinkedListImpl<T> {
    private Node<T> firstNode;
    int size;

    public SingleLinkedListImpl() {
        firstNode = null;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<T>(data);
        newNode.nextRef = firstNode;
//        System.out.println(newNode.data);
        firstNode = newNode;
        System.out.println(firstNode.data);
        size++;
    }

    public void deleteFirst() {
        if (firstNode == null) {
            throw new IllegalArgumentException("LinkedList doesn't contain any element");
        }

        Node<T> temp = firstNode;
        firstNode = firstNode.nextRef;
        temp.nextRef = null;
        size--;
    }

    public void addAt(int index, T data) {
        if (index == 0) {
            addFirst(data);
            return;
        }

        Node<T> prev = firstNode;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.nextRef;
        }

        Node<T> newNode = new Node<>(data);
        newNode.nextRef = prev.nextRef; // Link new node to the forward chain
        prev.nextRef = newNode;         // Link previous node to our new node

        size++;
    }

    public void deleteAt(int index) {
        // 1. Handle the start of the list
        if (index == 0) {
            deleteFirst();
            return;
        }
        Node<T> prev = firstNode;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.nextRef;
        }
        prev.nextRef = prev.nextRef.nextRef;

        size--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SingleLinkedList: [");
        Node<T> temp = firstNode;
        while (temp != null) {
            sb.append(temp.data);

            temp = temp.nextRef;

            if (temp != null) {
                sb.append(" --> ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

public class SingleLinkedList{
    public static void main(String[] args) {
        SingleLinkedListImpl<Integer> list = new SingleLinkedListImpl<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        System.out.println("------------------------------------");
        System.out.println(list);
        System.out.println("------------------------------------");
        list.addAt(2, 44);
        System.out.println(list);
        System.out.println("------------------------------------");
        list.deleteFirst();
        System.out.println(list);
        System.out.println("------------------------------------");
        list.deleteAt(2);
        System.out.println(list);
        System.out.println("------------------------------------");
    }
}