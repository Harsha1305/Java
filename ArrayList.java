import java.util.Arrays;

public class ArrayListImpl<T> {
    private T[] asArray;
    int size;

    @SuppressWarnings("unchecked")
    public ArrayListImpl()
    {
        asArray = (T[]) new Object[10];
        size = 0;
    }

    public void add(T item)
    {
        ensureCapacity(size+1);
        asArray[size++] = item;
    }

    private void ensureCapacity(int currentCapacity) {
        int newCapacity;
        if(currentCapacity > asArray.length)
        {
            newCapacity = Math.max(asArray.length*2, currentCapacity);
            asArray = Arrays.copyOf(asArray, newCapacity);
        }
    }

    public T get(int index)
    {
        rangeCheck(index);
        return asArray[index];
    }

    private void rangeCheck(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    public void add(int index, T item)
    {
        rangeCheckForAdd(index);
        ensureCapacity(size+1);
        for(int i = size -1; i >= index; i--)
        {
            asArray[i + 1] = asArray[i];
        }
        asArray[index] = item;
        size++;
//        System.arraycopy(asArray, index, asArray, index+1, size-index);
    }

    private void rangeCheckForAdd(int index) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    public T[] getAll()
    {
        return asArray;
    }

    public void remove(int index)
    {
        rangeCheck(index);
        for(int i = index; i < size - 1; i++)
        {
            asArray[i] = asArray[i+1];
        }
        size--;
    }

    public void removeElement(T element) {
        for(int i = 0; i < size - 1; i++)
        {
            if(element != null && element.equals(asArray[i+1]))
            {
                asArray[i] = asArray[i+1];
            }
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(asArray, size));
    }
}

import java.util.List;

public class ArrayList {
    public static void main(String[] args) {

        ArrayListImpl<Integer> list = new ArrayListImpl<>();
        List<Integer> asList = new java.util.ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        asList.add(1);
        asList.add(2);
        asList.add(3);
        System.out.println(list);
        System.out.println("------------------------------------");
        System.out.println(list);
        System.out.println("------------------------------------");
        list.add(2,44);
//        System.out.println(asList);
//        asList.add(44,5);
        System.out.println(list);
        System.out.println("------------------------------------");
        list.add(4);
        list.add(5);
        System.out.println("The value in the index " + list.get(3));
        System.out.println("------------------------------------");
        list.remove(5);
        System.out.println(list);
        System.out.println("------------------------------------");
        list.removeElement(44);
        }
    }