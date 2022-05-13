package queues;

import java.util.NoSuchElementException;

/**
 * The test main() for class Queue. Simply test some functions from
 * Queue class by showing the output.
 */
public class TestMainForQueue {

    /**
     * Creates a Queue type object and test some functions in the Queue class.
     * @param args Not used.
     */
    public static void main(String[] args)
    {
        Queue<String> testQueue = new Queue<>("testQueue");
        testQueue.enqueue("google1");
        testQueue.enqueue("google2");
        testQueue.enqueue("google3");
        System.out.println(testQueue);
        System.out.println(testQueue.dequeue());
        System.out.println(testQueue.dequeue());
        System.out.println(testQueue.peek());
        testQueue.enqueue("google4");
        System.out.println(testQueue.dequeue());
        System.out.println(testQueue);
        testQueue.dequeue();
        if (testQueue.isEmpty())
        {
            System.out.println("The queue is empty");
        }
        else{
            System.out.println("The queue isn't empty");
        }
        try
        {
            testQueue.dequeue();
        }
        catch(NoSuchElementException e)
        {
            System.out.println("Warning: the queue is empty, cannot remove an item.");
        }
        System.out.println("Test ends");
    }
}
