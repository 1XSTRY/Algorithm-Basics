import java.util.ArrayList;
import java.util.Arrays;

import static sun.jvm.hotspot.runtime.PerfMemory.capacity;

public class IntegerArrayList extends ArrayList<Integer> {

    private void grow() {
        int newCapacity = (int) (size() * 1.5);
        ensureCapacity(newCapacity);
    }

    @Override
    public boolean add(Integer item) {
        if (size() == capacity()) {
            grow();
        }
        return super.add(item);
    }

    // Рекурсивная сортировка слиянием
    private void mergeSort(Integer[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(Integer[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Integer[] L = new Integer[n1];
        Integer[] R = new Integer[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }


    public void sort() {
        Integer[] arr = (Integer[]) toArray();
        mergeSort(arr, 0, size() - 1);
        for (int i = 0; i < size(); i++) {
            set(i, arr[i]);
        }
    }
}