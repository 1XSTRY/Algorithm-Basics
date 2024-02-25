import java.util.Arrays;

public class StringArrayList implements StringList {
    private static final int DEFAULT_CAPACITY = 10;
    private String[] array;
    private int size;

    public StringArrayList() {
        this.array = new String[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public StringArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        this.array = new String[initialCapacity];
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
    public String add(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        ensureCapacity(size + 1);
        array[size] = item;
        size++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        String oldItem = array[index];
        array[index] = item;
        return oldItem;
    }

    @Override
    public String remove(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item can't be null");
        }
        for (int i = 0; i < size; i++) {
            if (item.equals(array[i])) {
                return remove(i);
            }
        }
        throw new IllegalArgumentException("Item not found: " + item);
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        String removedItem = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null; // Avoid memory leak
        size--;
        return removedItem;
    }

    @Override
    public boolean contains(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        for (int i = 0; i < size; i++) {
            if (item.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        for (int i = 0; i < size; i++) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return array[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new IllegalArgumentException("Other list cannot be null");
        }
        if (otherList.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!array[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
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
    public String[] toArray() {
        return Arrays.copyOf(array, size);
    }
}

interface StringList {
    String add(String item);

    String add(int index, String item);

    String set(int index, String item);

    String remove(String item);

    String remove(int index);

    boolean contains(String item);

    int indexOf(String item);

    int lastIndexOf(String item);

    String get(int index);

    boolean equals(StringList otherList);

    int size();

    boolean isEmpty();

    void clear();

    String[] toArray();
}