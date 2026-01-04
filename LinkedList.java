/* Author:Harshavardhan Narayanaswamy
................................
Implementation of Double LinkedList in java
................................
Jan 03 2026 */

class Node<T>{
    T data;
    Node<T> nextRef;
    Node<T> prevRef;

    public Node(T data)
    {
        this.data = data;
    }
}

class DoubleLinkedListImpl<T> {
    private Node<T> frontNode;
    private Node<T> lastNode;
    private int size;

    public DoubleLinkedListImpl()
    {
        lastNode = null;
    }

    public void insert(T data) {
        Node<T> newNode = new Node<>(data);
        if (frontNode == null) {
            frontNode = newNode;
            lastNode = newNode;
        } else {
            newNode.nextRef = frontNode;
            frontNode.prevRef = newNode;
            frontNode = newNode;
        }
        size++;
    }

    public void delete()
    {
        if(frontNode == null)
        {
            throw new IllegalArgumentException("LinkedList is empty");
        }

        Node<T> temp = frontNode;
        if(frontNode.nextRef == null)
        {
            frontNode = null;
            lastNode =null;
        }
        else {
            frontNode = frontNode.nextRef;
            frontNode.prevRef = null;
        }
        size--;
    }

    public void insertAt(int index, T data) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        if (index == 0) {
            insert(data);
            return;
        }

        Node<T> temp = frontNode;
        for (int i = 0; i < index; i++) {
            temp = temp.nextRef;
        }
    // If temp is null, we are inserting at the very end
        if (temp == null) {
            Node<T> newNode = new Node<>(data);
            lastNode.nextRef = newNode;
            newNode.prevRef = lastNode;
            lastNode = newNode;
        } else {
            Node<T> newNode = new Node<>(data);
            Node<T> previousNode = temp.prevRef;

            newNode.nextRef = temp;
            newNode.prevRef = previousNode;

            previousNode.nextRef = newNode; // Link previous to new
            temp.prevRef = newNode;         // Link current to new
        }
        size++;
    }

    public void deleteAt(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        if (index == 0) {
            delete();
            return;
        }

        Node<T> temp = frontNode;
        for (int i = 0; i < index; i++) {
            temp = temp.nextRef;
        }
//        Bridge the FORWARD path
        temp.prevRef.nextRef = temp.nextRef;

//        Bridge the BACKWARD path
        if (temp.nextRef != null) {
            temp.nextRef.prevRef = temp.prevRef;
        } else {
            lastNode = temp.prevRef;
        }

        size--;
    }
    public void frwdTraverse()
    {
        Node<T> temp = frontNode;
        while(temp != null)
        {
            System.out.print(temp.data + "->");
            temp = temp.nextRef;
        }

    }

    public void bckwrdTraverse()
    {
        Node<T> temp = lastNode;
        while(temp != null)
        {
            System.out.print(temp.data + "->");
            temp = temp.prevRef;
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        Node<T> temp = frontNode;
        sb.append("DoubleLinkedList: ["); // Start of the list
        while (temp != null) {
            sb.append(temp.data);

            temp = temp.nextRef;

            if (temp != null) {
                sb.append(" <--> ");
            }
        }
        sb.append("]");

        return sb.toString();
    }
}

public class LinkedList
{
    public static void main(String[] args) {
    DoubleLinkedListImpl<Integer> list = new DoubleLinkedListImpl<>();
    list.insert(1);
    list.insert(2);
    list.insert(3);
    list.insert(4);
    System.out.println(list);
    list.insertAt(4,5);
    System.out.println(list);
    list.insertAt(2,6);
    System.out.println(list);
    list.delete();
    System.out.println(list);
    list.deleteAt(2);
    System.out.println(list);

    }
}