import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("apple");
        list.add("banana");
        list.add("orange");

        System.out.println("List size: " + list.size());
        System.out.println("List contains 'banana': " + list.contains("banana"));
        System.out.println("Index of 'orange': " + list.indexOf("orange"));
        System.out.println("Last index of 'banana': " + list.lastIndexOf("banana"));
        System.out.println("Element at index 1: " + list.get(1));
        System.out.println("Is list empty: " + list.isEmpty());
        System.out.println("Array representation: " + Arrays.toString(list.toArray()));

        list.remove(1);
        System.out.println("After removing element at index 1: " + Arrays.toString(list.toArray()));

        list.clear();
        System.out.println("After clearing: " + Arrays.toString(list.toArray()));
    }
}
