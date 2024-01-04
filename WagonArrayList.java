package Lab7;

import Lab6.Wagon;

import java.util.*;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class WagonArrayList implements List {

    private final static int INITIAL_CAPACITY = 5;
    private Wagon[] wagons;
    private int size;

    public WagonArrayList(){
        this.wagons = new Wagon[INITIAL_CAPACITY];
    }
    public WagonArrayList(Wagon wagon){
        this();
        add(wagon);
    }
    public WagonArrayList(Collection<? extends Wagon> collection){
        wagons = new Wagon[INITIAL_CAPACITY];
        addAll(collection);
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
    public boolean contains(Object o) {
        //WagonArrayList();
        for (Object w : WagonArrayList.this) {
            if (o.equals(w)) {
                return true;
            }
        }
        return false;
    }
    public List getAll(){return Arrays.stream(wagons).toList();}

    @Override
    public Iterator iterator() {
        return new WagonListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(wagons, size);
    }

    @Override
    public boolean add(Object w) {
        if(w instanceof Wagon && contains(w)==false){
            resizeIfNeeded();
            wagons[size] = (Wagon)w;
            size++;
            return true;
        }
        return false;
    }
    private void resizeIfNeeded() {
        if (size == wagons.length) {
            Wagon[] newWagonArray = new Wagon[(int) (size + size / 3)];
            System.arraycopy(wagons, 0, newWagonArray, 0, size);
            wagons = newWagonArray;
        }
    }
    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            remove(indexOf(o));
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        if(c.size()==0){return true;}
        if(containsAll(c)){
            return false;
        }
        int init_wagons_size = size;
        for (int i = 0; i < c.size(); i++) {
            resizeIfNeeded();
            if(c.toArray()[i] instanceof Wagon){
                wagons[init_wagons_size+i] = (Wagon)c.toArray()[i];
                size++;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        for (int i = 0; i < c.size(); i++) {
            resizeIfNeeded();
            if(c.toArray()[i] instanceof Wagon){
                wagons[index+i] = (Wagon)c.toArray()[i];
                size++;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        wagons = new Wagon[INITIAL_CAPACITY];
    }

    @Override
    public Object get(int index) {
        return wagons[index];
    }

    @Override
    public Object set(int index, Object element) {
        if(element instanceof Wagon){
            wagons[index] = (Wagon)element;
        }
        return null;
    }

    @Override
    public void add(int index, Object w) {
        Objects.checkIndex(index, size);
        if(w instanceof Wagon){
            resizeIfNeeded();
            wagons[index] = (Wagon)w;
            size++;
        }
    }

    @Override
    public Object remove(int index) {
        Objects.checkIndex(index, size);
        Wagon removedElement = (Wagon) wagons[index];
        System.arraycopy(wagons, index + 1, wagons, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(wagons[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (o.equals(wagons[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        return new WagonListIterator(0);
    }

    @Override
    public ListIterator listIterator(int index) {
        return new WagonListIterator(index);
    }

    private class WagonListIterator implements ListIterator<Wagon> {
        private int currentIndex;
        private int lastReturnedIndex = -1;
        public WagonListIterator() {
            this.currentIndex = 0;
        }
        public WagonListIterator(int index) {
            if (index < 0 || index > WagonArrayList.this.size()) {
                throw new IndexOutOfBoundsException("Invalid index");
            }
            this.currentIndex = index;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < WagonArrayList.this.size();
        }

        @Override
        public Wagon next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturnedIndex = currentIndex;
            return (Wagon)get(currentIndex++);
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        @Override
        public Wagon previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            lastReturnedIndex = --currentIndex;
            return (Wagon)get(currentIndex);
        }

        @Override
        public int nextIndex() {
            return currentIndex;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        @Override
        public void remove() {
            if (lastReturnedIndex == -1) {
                throw new IllegalStateException("No element to remove");
            }
            WagonArrayList.this.remove(lastReturnedIndex);
            currentIndex = lastReturnedIndex;
            lastReturnedIndex = -1;
        }

        @Override
        public void set(Wagon wagon) {
            if (lastReturnedIndex == -1) {
                throw new IllegalStateException("No element to set");
            }
            WagonArrayList.this.set(lastReturnedIndex, wagon);
        }

        @Override
        public void add(Wagon wagon) {
            WagonArrayList.this.add(currentIndex++, wagon);
            lastReturnedIndex = -1;
        }
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        List subList = new ArrayList<>();
        if(size()>toIndex){
            for(int i =fromIndex; i<=toIndex; i++){
                subList.add(wagons[i]);
            }
        }
        return subList;
    }

    @Override
    public boolean retainAll(Collection c) {
        List<Wagon> copyList = new ArrayList<>(List.of(wagons));

        // Iterate over the list and remove elements not present in the specified collection
        Iterator<Wagon> iterator = copyList.iterator();
        while (iterator.hasNext()) {
            Wagon wagon = iterator.next();
            if (!c.contains(wagon)) {
                iterator.remove();
            }
        }

        // Update the original list with the retained elements
        boolean modified = retainAll(copyList);

        return modified;
    }

    @Override
    public boolean removeAll(Collection c) {
        List<Object> elementsToRemove = new ArrayList<>(c);

        for (Object o : elementsToRemove) {
            if (contains(o)) {
                remove(o);
            }
            else{
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        //boolean flag = true;
        for (Object o : c) {
            if (contains(o)) {
                continue;
            }
            else{
                //flag = false;
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return Arrays.copyOf(a, a.length);
    }
}
