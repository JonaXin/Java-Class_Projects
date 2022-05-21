package cs_1c;

/**
 * Defines our own interface to traverse the whole tree.
 * @param <E> the interface is generic type.
 */
public interface Traverser<E> {
    public void visit(E x);
}
