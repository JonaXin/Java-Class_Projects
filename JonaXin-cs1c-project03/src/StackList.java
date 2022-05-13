package stacks;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class StackList<E> implements Iterable<E> {

    private String name;
    private int listSize;
    private Node top;
    private Node currentTop;
    private int modCount = 0;

    public StackList(String stackName)
    {
        top = null;
        currentTop = null;
        this.listSize = 0;
        this.name = stackName;
    }

    public void push(E data)
    {
        Node nodeToPush = new Node(data, null);
        if (listSize == 0)
            top = nodeToPush;
        else {
            nodeToPush.next = top;
            top = nodeToPush;
        }
        modCount++;
        listSize++;
    }

    public E pop()
    {
        if (listSize == 0)
            throw new NoSuchElementException();

        currentTop = top;
        top = top.next;
        listSize--;
        modCount++;
        return currentTop.data;
    }

    public E peek()
    {
        if (listSize == 0)
            return null;
        return top.data;
    }

    public void clear()
    {
        top = null;
        listSize = 0;
        modCount++;
    }


    public String toString()
    {
        String returnStr = "";
        if (listSize == 0)
            return returnStr;
        Iterator iterator = this.iterator();
        returnStr = returnStr + iterator.next();
        while (iterator.hasNext() && listSize > 1) {
            returnStr = returnStr + ", " + iterator.next();
        }
        return returnStr;
    }

    public boolean isEmpty()
    {
        return (top == null);
    }

    public int size()
    {
        return listSize;
    }

    private class Node
    {
        Node next;
        E data;

        Node( E x, Node nxt )
        {
            next = nxt;
            data = x;
        }
    }

    private class StackIterator implements java.util.Iterator<E>
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
        public boolean hasNext() { return mCurrentIndex < listSize; }

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

        // constructors
        // Note: default access for package only
        StackIterator()
        {
            mCurrentNode = top;
            mCurrentIndex = 0;
        }
    }

    public java.util.Iterator<E> iterator() { return new StackIterator(); }
}
