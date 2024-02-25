import java.util.Arrays;

public class IntegerArrayList implements IntegerList {
    private static final int DEFAULT_CAPACITY = 10;
    private Integer[] array;
    private int size;

    public IntegerArrayList() {
        this.array = new Integer[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public IntegerArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        this.array = new Integer[initialCapacity];
        this.size = 0;
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = array.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity + (oldCapacity >> 1); // Increase capacity by 50%
            if (newCapacity < minCapacity)
                newCapacity = minCapacity;
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    @Override
    public Integer add(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        ensureCapacity(size + 1);
        array[size] = item;
        size++;
        return item;
    }

    private void quickSort(Integer[] arr, int low, int high) {
        if (arr == null || arr.length == 0) {
            return;
        }
        if (low >= high) {
            return;
        }

        // Выбор опорного элемента
        int middle = low + (high - low) / 2;
        Integer pivot = arr[middle];

        // Разделение на подмассивы
        int i = low, j = high;
        while (i <= j) {
            while (arr[i].compareTo(pivot) < 0) {
                i++;
            }
            while (arr[j].compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                Integer temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // Рекурсивные вызовы для подмассивов
        if (low < j) {
            quickSort(arr, low, j);
        }
        if (high > i) {
            quickSort(arr, i, high);
        }
    }

    private boolean binarySearch(Integer[] arr, int left, int right, Integer key) {
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid].equals(key)) {
                return true;
            }

            if (arr[mid].compareTo(key) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        // Сначала отсортируем массив
        Integer[] sortedArray = Arrays.copyOf(array, size);
        quickSort(sortedArray, 0, size - 1);

        // Затем выполним бинарный поиск
        return binarySearch(sortedArray, 0, size - 1, item);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(array, size);
    }
}

interface IntegerList {
    Integer add(Integer item);
    boolean contains(Integer item);
    int size();
    boolean isEmpty();
    void clear();
    Integer[] toArray();
}