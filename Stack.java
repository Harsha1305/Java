/* Author:Harshavardhan Narayanaswamy
................................
Implementation of Double LinkedList in java
................................
Jan 05 2026 */
import java.util.Arrays;

class StackImpl<T> {
    private T[] stackArray;
    private int top;

    @SuppressWarnings("unchecked")
    public StackImpl()
    {
        stackArray = (T[])new Object[10];
        top = -1;
    }

    private void ensureCapacity(int index)
    {
        if(index >= stackArray.length)
       {
            Math.max(index, stackArray.length*2);
            stackArray = (T[]) Arrays.copyOf(stackArray,stackArray.length*2);
        }
    }
    public void push(T item)
    {
        ensureCapacity(top+1);
        stackArray[++top] = item;
    }

    public T pop()
    {
        if(top < 0)
        {
            throw  new IllegalArgumentException("Stack is empty");
        }
        T item  = stackArray[top];
        stackArray[top--] = null ;
        return item;
    }

    public T peek()
    {
        if(top < 0)
        {
            throw  new ArrayIndexOutOfBoundsException("Stack is empty");
        }
        return stackArray[top];
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: [");
        for(int i = top; i >= 0; i--)
        {
            sb.append(stackArray[i]);
            if (i != 0) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
public class Stack {
   public static void main(String[] args) {
        StackImpl<Integer> stack = new StackImpl<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
        System.out.println("--------------------------");
        System.out.println("Stack top element: " + stack.peek());
        System.out.println("--------------------------");
        System.out.println("Popped element: " + stack.pop());
        System.out.println("--------------------------");
        System.out.println("Popped element: " + stack.pop());
        System.out.println("--------------------------");
        System.out.println("Stack top element: " + stack.peek());
    } 
}
