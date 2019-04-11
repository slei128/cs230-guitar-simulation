//********************************************************************
//  LinkedQueue.java       Java Foundations
//
//  Represents a linked implementation of a queue.
//********************************************************************

package javafoundations;

import javafoundations.exceptions.*;

public class LinkedQueue<T> implements Queue<T> {
 private int count;
 private LinearNode<T> front, rear;

 //-----------------------------------------------------------------
 //  Creates an empty queue.
 //-----------------------------------------------------------------
 public LinkedQueue() {
  count = 0;
  front = rear = null;
 }

 //-----------------------------------------------------------------
 //  Adds the specified element to the rear of this queue.
 //-----------------------------------------------------------------
 public void enqueue (T el) {
   //create a LinearNote out of the input element
  LinearNode<T> node = new LinearNode<T>(el);

  if (count == 0)
   front = node;
  else
   rear.setNext(node);

  rear = node;
  count++;
 }

 //-----------------------------------------------------------------
 //  The following methods are left as Programming Projects.
 //-----------------------------------------------------------------
 public T dequeue () throws EmptyCollectionException {
     if (count == 0)
        throw new EmptyCollectionException("Queue is empty");
     LinearNode<T> oldFront = front;
     front = oldFront.getNext();
     count--;
     return oldFront.getElement();
 }
 
 public T first () throws EmptyCollectionException {
     /* if (count==0)
        throw new EmptyCollectionException("Stack is empty");
     return front.getElement(); */
     
     try {
         return front.getElement();
     } catch (NullPointerException e) {
         throw new EmptyCollectionException("first() failed: Queue is empty");
     }
 }
 
 
 public boolean isEmpty() {
     return count == 0;
 }
 
 public int size() {
     return count;
 }
 
 public String toString() {
     String result = "<top of queue> \n ";
     LinearNode current = front;
     while(current != null){
         result += current.getElement() + "\n";
         current = current.getNext();
    }
    return result + "<bottom of queue>";
 }
 
 public static void main(String[] args) {
     LinkedQueue<String> tester = new LinkedQueue<String>();
     tester.enqueue("today");
     tester.enqueue("is");
     tester.enqueue("Wednesday");
     tester.dequeue();
     System.out.println(tester);
     
 }
 
}
