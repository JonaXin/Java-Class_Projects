package queues;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The class that provides some basic functions for a queue data structure.
 * Contains inner class Node and QueueIterator.
 * @param <E>
 */
public class Queue<E> implements Iterable<E> {

    private String name;
    private Node head;
    private Node tail;
    private Node currentHead;
    private int queueSize;
    private int modCount;

    /**
     * The constructor for class Queue, initialize the class attributes.
     * @param queueName The name of the queue
     */
    public Queue(String queueName)
    {
        this.name = queueName;
        head = null;
        tail = null;
        currentHead = null;
        this.queueSize = 0;
        this.modCount = 0;
    }

    /**
     * The method that helps add a generic item to the queue, creates an object
     * of type Node and assign the data passed in to it, then first check if
     * the queue is empty, if so points the head and tail to it, otherwise add
     * it to the tail and reset the tail. Increment queue's size and modification
     * count.
     * @param data The data that the node carries.
     */

    public void enqueue(E data)
    {
        Node nodeToPush = new Node(data, null);
        if (queueSize == 0) {
            head = nodeToPush;
            tail = nodeToPush;
        }
        else{
            tail.next = nodeToPush;
            tail = nodeToPush;
        }
        queueSize++;
        modCount++;
    }

    /**
     * The method that helps remove a generic item in the queue, first check
     * if the queue is empty, if so throw a NoSuchElementException, otherwise
     * check if there's only one item in the queue, if so use currentHead node
     * to record the head node's data, set head and tail to null and return
     * the data. If not simply record that data and reset the head reference.
     * Increment modification count and decrement queue's size.
     * @return data that the removed node contained.
     */
    public E dequeue()
    {
        if (queueSize == 0)
            throw new NoSuchElementException();
        if (head == tail)
        {
            currentHead = head;
            head = null;
            tail = null;
            queueSize--;
            modCount++;
            return currentHead.data;
        }
        else {
            currentHead = head;
            head = head.next;
            queueSize--;
            modCount++;
            return currentHead.data;
        }
    }

    /**
     * The method that helps return the data that the head node carries.
     * Return null if the queue is empty.
     * @return the data that the head node carries, or null.
     */
    public E peek()
    {
        if (queueSize == 0)
            return null;
        return head.data;
    }

    /**
     * The method that helps check if the queue is empty, simply check if
     * the queue's size is 0.
     * @return true or false depends on whether or not the queue is empty.
     */
    public boolean isEmpty() { return (queueSize == 0); }

    /**
     * The method that returns the number of items in the queue.
     * @return the number of items in the queue.
     */
    public int size() { return queueSize; }

    /**
     * The method that helps print out the whole queue. Use iterator to go
     * through each items in the queue and return their data.
     * @return the whole queue in the correct format.
     */
    public String toString()
    {
        //return "Name of the queue: " + name + ", number of elements: " + size();
        String returnStr = name + ": \n[";
        if (queueSize == 0)
            return returnStr + " ]";
        Iterator iterator = this.iterator();
        returnStr = returnStr + iterator.next().toString();
        while (iterator.hasNext() && queueSize > 1) {
            returnStr = returnStr + "; \n" + iterator.next().toString();
        }
        return returnStr + "]";
    }

    /**
     * Accessor for the name of the queue.
     * @return the name of the queue.
     */
    public String getName()
    {
        return name;
    }

    /**
     * An inner class of Queue, for each item in the queue there is a next
     * and data reference.
     */
    private class Node
    {
        Node next;
        E data;

        /**
         * A constructor for Node class, initializes the class attributes.
         * @param x The data that a node contains.
         * @param nxt A reference of the node, which enables us to build a
         *            queue data structure.
         */
        Node( E x, Node nxt )
        {
            next = nxt;
            data = x;
        }
    }

    /**
     * An inner class of Queue which implements iterator, contains only
     * two methods next() and hasNext(), and a constructor.
     */
    private class QueueIterator implements java.util.Iterator<E>
    {
        protected static final int NOT_VALID = -1;
        protected static final int NEXT = 10;

        protected Node mCurrentNode;
        protected int mCurrentIndex;

        // for ConcurrentModificationExceptions
        protected int mIterModCount = modCount;

        // for IllegalStateExceptions
        protected Node mLastNodeReturned = null; // valid: any Node object
        protected int mLastIteration = NOT_VALID; // valid: NEXT or PREVIUOS

        // required interface implementations

        /**
         * The method that helps check if there are more items left in
         * the queue.
         * @return return true or false depends on whether or not there
         *         are more items left in the queue.
         */
        public boolean hasNext()
        { return mCurrentIndex < queueSize; }

        /**
         * The method that helps return the data that a node carries in a
         * queue. Check if there are more items left in the queue, if so
         * return its data.
         * @return the data of each node in the queue.
         */
        public E next()
        {
            if (mIterModCount != modCount)
                throw new ConcurrentModificationException();
            if( !hasNext() )
                throw new java.util.NoSuchElementException();
            mLastNodeReturned = mCurrentNode;

            mCurrentNode = mCurrentNode.next;
            mCurrentIndex++;
            mLastIteration = NEXT;
            return mLastNodeReturned.data;
        }

        /**
         * A default constructor for QueueIterator class.
         */
        QueueIterator()
        {
            mCurrentNode = head;
            mCurrentIndex = 0;
        }
    }

    /**
     * The iterator method, simply return an object of type QueueIterator.
     * @return a new QueueIterator.
     */
    public java.util.Iterator<E> iterator()
    { return new QueueIterator(); }
}
