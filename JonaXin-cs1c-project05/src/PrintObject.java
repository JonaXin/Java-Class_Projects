import Traverser;

/**
 * The class that uses traverser to print out all data in the tree.
 * @param <E> the class is generic type.
 */
class PrintObject<E> implements Traverser<E>
{
    /**
     * The method that visits every nodes in the tree and prints out
     * their data.
     * @param x the data that needs to be printed out.
     */
    public void visit(E x)
    {
        System.out.print( x + " ");
    }
};
