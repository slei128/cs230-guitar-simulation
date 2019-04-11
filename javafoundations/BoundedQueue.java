package javafoundations;
/**
 * BoundedQueue implements a bounded queue ADT. A bounded queue is a queue 
 * with a maximum capacity, meaning that no elements can be enqueued when 
 * the queue is full to capacity.
 *
 * @author Shirley Lei, Emily Yin
 * @version April 8th, 2019
 */
public class BoundedQueue<T> extends CircularArrayQueue<T>{
    
    private final int CAPACITY;
    
    /**
     * Constructor for BoundedQueue
     * @param capacity the capacity of the bounded queue
     */
    public BoundedQueue(int capacity){
        super(); //invokes CircularArrayQueue's constructor
        CAPACITY = capacity;
    }

    /**
     * A predicate that indicates if the bounded queue is at capacity or not
     * @return boolean true if bounded queue is at capacity, false otherwise
     */
    public boolean isFull(){
        return (size() == CAPACITY);
    }

    /**
     * Enqueues an element if the queue is not at capacity
     * @param element the element to be enqueued
     * @Override
     */
    public void enqueue (T element) {
        if (!isFull()){
            super.enqueue(element);
        }
    }

    /**
     * Trivial (preliminary) testing for BoundedQueue methods
     * @param args an array string (unused in this case)
     */
    public static void main (String[] args){
        BoundedQueue bq = new BoundedQueue(10);
        // testing is full on empty bounded queue, expect false
        bq.isFull();
        bq.enqueue("hi");
        // expect true
        bq.isFull();
        //test enqueue on bq at CAPACITY, expect no enqueuing
    }
}
