/* Author:Harshavardhan Narayanaswamy
................................
Implementation of Double LinkedList in java
................................
Jan 05 2026 */
class Node<T>
{
    T data;
    Node<T> nextRef;

    public Node(T data)
    {
        this.data = data;
    }
}
class CircularLinkedListImpl<T> {
    private Node<T> last;
    int size;

    public CircularLinkedListImpl()
    {
        last = null;
    }

    public void insert(T data) {
        Node<T> newNode = new Node<>(data);
        if (last == null) {
            last = newNode;
            last.nextRef = last; // Points to itself to start the circle
        } else {
            // newNode becomes the new "first" node
            newNode.nextRef = last.nextRef; // newNode points to current head
            last.nextRef = newNode;         // last points to newNode (the new head)
        }
        size++;
    }

    public void delete() {
        if (last == null) throw new IllegalArgumentException("List is empty");

        if (last.nextRef == last) {
            // Only one node in the list
            last = null;
        } else {
            // Bypass the first node (head)
            last.nextRef = last.nextRef.nextRef;
        }
        size--;
    }

    public boolean isCircular() {

        if (last == null) return false;
        Node<T> head = last;
        Node<T> current = last.nextRef;
        while (current != null && current != head) {
            current = current.nextRef;
        }
        return current == head;
    }


    @Override
    public String toString() {
        if (last == null) {
            return "CircularLinkedList: []";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("CircularLinkedList: [");

        Node<T> head = last.nextRef;   // first node
        Node<T> current = head;

        do {
            sb.append(current.data);
            current = current.nextRef;
            if (current != head) {
                sb.append(" -> ");
            }
        } while (current != head);

        sb.append("]");
        return sb.toString();
    }

}
public class CircularLinkedList {
    public static void main(String[] args) {
        CircularLinkedListImpl<Integer> list = new CircularLinkedListImpl<>();
        list.insert(1);
        list.insert(2);
        System.out.println("-----------------------------");
        System.out.println(list);
        list.insert(3);
        System.out.println(list);
        System.out.println("-----------------------------");
        list.insert(4);
        System.out.println(list);
        System.out.println("-----------------------------");
        list.delete();
        System.out.println(list);
        System.out.println("-----------------------------");
        list.delete();
        System.out.println(list);
        System.out.println(list.isCircular());
    }
}
