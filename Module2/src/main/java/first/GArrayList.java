package first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by nikit on 17.02.2018.
 */
public class GArrayList <T> {

    public int size;
    private int current;

    private Object[] array;

    public GArrayList() {
        array = new Object[10];
    }

    private void makeBigger() {
        Object[] newArray = new Object[array.length + (array.length / 2) + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    public void add(T obj) {
        if (current >= array.length) makeBigger();

        array[current] = obj;
        current++;
        size = current;
    }

    public void add(T obj, int index) {
        if (index < 0 || index > current) {
            System.err.println("Out of Bounds. Canceled.");
            return;
        }
        if (current+1 >array.length) makeBigger();

        for (int i = size; i>=index; i--) {
            array[i+1] = array[i];

        }
        array[index] = obj;
        size++;
        current++;
    }

    public T get(int index) {
        if (index < 0 || index > current) {
            System.err.println("Out of Bounds. Canceled.");
            return null;
        }

        return (T) array[index];
    }

    public T remove(int index) {
        if (index < 0 || index > current) {
            System.err.println("Out of Bounds. Canceled.");
            return null;
        }
        T obj = (T) array[index];

        for (int i = index; i<size; i++) {
            array[i] = array[i+1];
        }
        size--;
        return obj;
    }



}
