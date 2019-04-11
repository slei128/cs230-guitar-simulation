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
        BoundedQueue bq = new BoundedQueue(2);
        System.out.println("-------Testing on bounded queue (bq) of capacity=2-----");
        System.out.println("test toString() on EMPTY bq:\n" + bq);
        System.out.println("test inherited isEmpty() on EMPTY bq, expect true: " + bq.isEmpty());
        System.out.println("test inherited size() on EMPTY bq, expect 0: " + bq.size());
        System.out.println("testing isFull on EMPTY bq, expect false: " + bq.isFull() + "\n");
        bq.enqueue("first elt");
        System.out.println("enqueued first element to non-full bq, expect enqueued first elt:\n" + bq + "\n");
        bq.enqueue("second elt");
        System.out.println("enqueued second element to non-full bq, expect enqueued second elt:\n" + bq + "\n");
        System.out.println("testing isFull on FULL bq, expect true: " + bq.isFull() + "\n");
        bq.enqueue("third elt");
        System.out.println("enqueued third element to AT-CAPACITY bq, expect does NOT enqueue:\n" + bq + "\n");
        
        BoundedQueue bq2 = new BoundedQueue(10);
        System.out.println("-------Testing on bounded queue (bq2) of capacity=10-----");
        bq2.enqueue("1st");
        System.out.println("test inherited first() on bq2, expect 1st: " + bq2.first());
        bq2.dequeue();
        System.out.println("test inherited dequeue() on bq2, expect bq2 to become empty: " + bq2);
    }
}
