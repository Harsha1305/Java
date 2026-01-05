/* Author:Harshavardhan Narayanaswamy
................................
Implementation of Double LinkedList in java
................................
Jan 05 2026 */
import java.util.Arrays;

class QueueImpl<T> {
    private T[] queueArray;
    private int front;
    private int rear;
    private int size;

    @SuppressWarnings("unchecked")
    public QueueImpl() {
        queueArray = (T[]) new Object[10];
        front = 0;
        rear = 0;
        size = 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= queueArray.length) return;

        int newCapacity = Math.max(queueArray.length * 2, minCapacity);
        T[] newArr = (T[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newArr[i] = queueArray[(front + i) % queueArray.length];
        }

        queueArray = newArr;
        front = 0;
        rear = size;
    }

    public void enqueue(T item) {
        ensureCapacity(size + 1);
        queueArray[rear] = item;
        rear = (rear + 1) % queueArray.length;
        size++;
    }

    public T dequeue() {
        if (size == 0) {
            throw new IllegalArgumentException("Queue is empty");
        }

        T item = queueArray[front];
        queueArray[front] = null;
        front = (front + 1) % queueArray.length;
        size--;
        return item;
    }

    public T peek() {
        if (size == 0) {
            throw new IllegalArgumentException("Queue is empty");
        }
        return queueArray[front];
    }

    public int size() {
        return size;
    }

    public void displayRearAndFront()
    {
        System.out.println("The value of Rear: " + rear + "\nThe value of front: " + front);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: [");
        for (int i = 0; i < size; i++) {
            sb.append(queueArray[(front + i) % queueArray.length]);
            if (i != size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
public class Queue {
    public static void main(String[] args)
    {
        QueueImpl<Integer> queue = new QueueImpl<>();
        queue.enqueue(1);
        queue.displayRearAndFront();
        queue.enqueue(2);
        queue.displayRearAndFront();
        queue.enqueue(3);
        System.out.println(queue);
        System.out.println("--------------------------");
        System.out.println("Stack top element: " + queue.peek());
        System.out.println("--------------------------");
        System.out.println("Popped element: " + queue.dequeue());
        queue.displayRearAndFront();
        System.out.println("--------------------------");
        System.out.println("Popped element: " + queue.dequeue());
        queue.displayRearAndFront();
        System.out.println("--------------------------");
        System.out.println("Stack top element: " + queue.peek());
    }
}
