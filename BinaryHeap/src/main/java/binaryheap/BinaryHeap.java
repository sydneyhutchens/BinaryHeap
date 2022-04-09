package binaryheap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Queue;

/**
 * This class implements a binary heap data structure by extending the
 * ArrayList class.
 * @author Your name here
 * @param <E> the type of element stored on this binary heap
 */
public class BinaryHeap<E> extends ArrayList<E> implements Queue<E> {

    /**
     * Creates an empty binary heap with a given capacity and comparator.
     * @param capacity The initial size of the underlying ArrayList object.
     * @param comp A comparator object for comparing keys of binary heap elements.
     */
    public BinaryHeap(int capacity, Comparator<E> comp) {
        super(capacity+1);
        init();
        this.comp = comp;
    }

    /**
     * Getter for the comparator for this binary heap.
     * @return the comparator for this binary heap
     */
    public Comparator<E> getComp() {
        return comp;
    }

    /**
     * Initializes the underlying ArrayList object for use as a binary heap.
     * A null object is added to location 0, which is not used by the heap.
     */
    private void init() {
        add(0, null);
    }

    /**
     * Clears this binary heap by clearing and initializing the underlying
     * ArrayList object.
     */
    @Override
    public void clear() {
        super.clear();
        init();
    }

    /**
     * Returns the current size of this binary heap.  Since the first location 
     * (index 0) of the underlying ArrayList object is not used, the size of the 
     * binary heap is one less than the size of the ArrayList object.
     * @return The binary heap's current size. 
     */
    @Override
    public int size() {
        return super.size()-1;
    }

    /**
     * Returns true if this binary heap is empty, that is, its size is zero.
     * @return Whether this binary heap is empty.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds a new element to this binary heap.  At the end of the add,
     * the heap has one more element and the heap property is maintained.
     * @param element The element to add
     * @return true.  In accordance with the Collection interface, returns
     * true since duplicate elements are allowed.
     */
    @Override
    public boolean add(E element) {
        super.add(element);
        int elementIndex = super.indexOf(element);
        int parentNodeIndex = elementIndex / 2;
        E parentNode = super.get(parentNodeIndex);
        
        if(super.size() > 2) {
            while(comp.compare(element, parentNode) == -1) {
                super.set(parentNodeIndex, element);
                super.set(elementIndex, parentNode);
                
                if(super.indexOf(element) == 1) {
                    break;
                }
                elementIndex = super.indexOf(element);
                parentNodeIndex = elementIndex / 2;
                parentNode = super.get(parentNodeIndex);   
            }
        }
        return true;
    }

    /**
     * Removes an element from the root of this binary heap.  After removal,
     * the heap has one less element and the heap property is restored.
     * This method does not override any method in the ArrayList class 
     * (note absence of an index parameter).
     * @return The removed element
     */
    @Override
    public E remove() {
        E elementToRemove = super.get(1);
        super.set(1, super.get(super.size() - 1));
        super.remove(super.size() -1);
        
        if(super.size() == 1 || super.size() == 2) {
            return elementToRemove;
        }
        if(super.size() == 3) {
            if(comp.compare(super.get(1), super.get(2)) == 1) {
                E temp = super.get(1);
                super.set(1, super.get(2));
                super.set(2, temp);
            }
            return elementToRemove;
        }
        
         int currentIndex = 1;
            E currentNode = super.get(currentIndex);
            int leftChildIndex = currentIndex * 2;
            E leftChild = super.get(leftChildIndex);
            int rightChildIndex = currentIndex * 2 + 1;
            E rightChild = super.get(rightChildIndex);
            
            if(super.size() > 2) {
                 while((comp.compare(currentNode, leftChild) == -1 && comp.compare(currentNode, rightChild) == -1) || 
                        leftChild != null && rightChild != null) {
                    if(comp.compare(leftChild, rightChild) == -1) {
                        super.set(leftChildIndex, currentNode);
                        super.set(currentIndex, leftChild);
                        currentIndex = leftChildIndex;
                        leftChildIndex = currentIndex * 2;
                        rightChildIndex = currentIndex * 2 + 1;
                    }
                    
                    else if(comp.compare(rightChild, leftChild) == -1) {
                        super.set(rightChildIndex, currentNode);
                        super.set(currentIndex, rightChild);
                        currentIndex = rightChildIndex;
                        leftChildIndex = currentIndex * 2;
                        rightChildIndex = currentIndex * 2 + 1;
                    }
                    
                    if(leftChildIndex >= super.size()) {
                        leftChild = null;
                    }
                    else if(leftChildIndex < super.size() - 1) {
                        leftChild = super.get(leftChildIndex);
                    }
                    
                    if(rightChildIndex >= super.size() -1) {
                        rightChild = null;
                    }
                    
                    else if(rightChildIndex < super.size() -1) {
                        rightChild = super.get(rightChildIndex);
                    }
                    
                    if(leftChild == null || rightChild == null){
                        return elementToRemove;
                    }
                }
                
            }
        return null;    
    }
    /**
     * A Comparator object used to compare binary heap elements during its
     * add and remove operations.
     */
    private final Comparator<E> comp;

    /*
    The following are required to complete the implementation of the Queue<E> 
    interface. They are not used in the test.
    */
    
    @Override
    public boolean offer(E e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E poll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E element() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E peek() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}