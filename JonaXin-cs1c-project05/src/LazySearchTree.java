package lazyTrees;

import java.util.*;
import cs_1c.Traverser;

/**
 * The basis of a binary tree data structure, contains several methods that
 * are needed to perform a binary tree structure.
 * @param <E> The tree is generic type.
 */
public class LazySearchTree<E extends Comparable< ? super E > >
        implements Cloneable
{
    protected int mSize;
    protected int mSizeHard;
    protected LazySTNode mRoot;

    /**
     * The constructor for the LazySearchTree, simply calls clear() method
     * to create a new tree.
     */
    public LazySearchTree()
    {
        clear();
    }

    /**
     * The method that helps check if the tree is empty, return true if the
     * hard size of tree is 0, and false otherwise.
     * @return true or false depending on whether or not there is nothing
     *         in the tree.
     */
    public boolean empty()
    {
        return (mSize == 0);
    }

    /**
     * The accessor for the soft size of the tree, which only counts the number
     * of nodes that are not marked as deleted in the tree.
     * @return the soft size of the tree.
     */
    public int size()
    {
        return mSize;
    }

    /**
     * The accessor for the hard size of the tree, which counts both deleted
     * and non-deleted nodes in the tree.
     * @return the hard size of the tree.
     */
    public int sizeHard()
    {
        return mSizeHard;
    }

    /**
     * The method that helps clear out the tree by simply resetting the root
     * and set both soft and hard size to 0.
     */
    public void clear()
    {
        mSize = 0;
        mSizeHard = 0;
        mRoot = null;
    }

    /**
     * The public method that helps return the height of the tree, calls its
     * private helper method.
     * @return the height of the tree.
     */
    public int showHeight()
    {
        return findHeight(mRoot, -1);
    }

    /**
     * The public method that returns the data of the node that contains the
     * smallest value in the tree by calling its private helper method,
     * throws NoSuchElementException if the tree is empty.
     * @return the smallest value in the tree
     */
    public E findMin()
    {
        if (mRoot == null)
            throw new NoSuchElementException();
        return findMin(mRoot).data;
    }

    /**
     * The public method that returns the data of the node that contains the
     * biggest value in the tree by calling its private helper method, throws
     * NoSuchElementException if the tree is empty.
     * @return the biggest value in the tree
     */
    public E findMax()
    {
        if (mRoot == null)
            throw new NoSuchElementException();
        return findMax(mRoot).data;
    }

    /**
     * The public method that finds some specific data in the tree and return
     * it by calling its private helper method, throw NoSuchElementException
     * if the tree is empty.
     * @param x the data that is trying to find in the tree.
     * @return the data that is found in the tree.
     */
    public E find( E x )
    {
        LazySTNode resultNode;
        resultNode = find(mRoot, x);
        if (resultNode == null)
            throw new NoSuchElementException();
        return resultNode.data;
    }

    /**
     * The method that returns true if the data is contained somewhere in
     * the tree by calling the private find() method, otherwise return false.
     * @param x the data that is checked.
     * @return true or false depending on whether or not the data is in the
     *         tree.
     */
    public boolean contains(E x)
    { return find(mRoot, x) != null; }

    /**
     * The method that helps adding a data into the tree by calling its
     * private helper method. Returns true if the insertion is successful
     * and false otherwise.
     * @param x the data that needs to be added.
     * @return true or false depending on whether or not the insertion is
     *         successful.
     */
    public boolean insert( E x )
    {
        int oldSize = mSize;
        mRoot = insert(mRoot, x);
        return (mSize != oldSize);
    }

    /**
     * The method that helps removing a data from the tree by calling its
     * private helper method. Returns true if the deletion is successful
     * and false otherwise.
     * @param x the data that needs to be removed from the tree.
     * @return true or false depending on whether or not the deletion is
     *         successful.
     */
    public boolean remove( E x )
    {
        int oldSize = mSize;
        remove(mRoot, x);
        return (mSize != oldSize);
    }

    /**
     * The public method that traverses both deleted and non-deleted nodes
     * in the tree by calling its private helper method.
     * @param func the function that performs during traversal.
     * @param <F> the method is generic type.
     */
    public < F extends Traverser<? super E > >
    void traverseHard(F func)
    {
        traverseHard(func, mRoot);
    }

    /**
     * The public method that traverses non-deleted nodes only in the tree
     * by calling its private helper method.
     * @param func the function that performs during traversal.
     * @param <F> the method is generic type.
     */
    public < F extends Traverser<? super E > >
    void traverseSoft(F func)
    {
        traverseSoft(func, mRoot);
    }

    /**
     * The method that helps making a deep copy of a tree by calling its
     * private method and set both soft and hard sizes.
     * @return the new copied tree.
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException
    {
        LazySearchTree<E> newObject = (LazySearchTree<E>)super.clone();
        newObject.clear();  // can't point to other's data

        newObject.mRoot = cloneSubtree(mRoot);
        newObject.mSize = mSize;
        newObject.mSizeHard = mSizeHard;
        return newObject;
    }

    // private helper methods ----------------------------------------

    /**
     * The private recursive method for finding the node that contains
     * the smallest value in the tree.
     * @param root the tree root that starts to find the node.
     * @return the node after found.
     */
    protected LazySTNode findMin( LazySTNode root )
    {
        if (root == null)
            return null;

        LazySTNode tempLftMin = findMin(root.lftChild);

        if (tempLftMin != null)
            return tempLftMin;

        if(!root.deleted)
            return root;

        LazySTNode tempRtMin = findMin(root.rtChild);

        if (tempRtMin != null)
            return tempRtMin;

        return null;
    }

    /**
     * The private recursive method for finding the node that contains
     * the biggest value in the tree.
     * @param root the tree root that starts to find the node.
     * @return the node after found.
     */
    protected LazySTNode findMax( LazySTNode root )
    {
        if (root == null)
            return null;

        LazySTNode tempRtMax = findMax(root.rtChild);

        if (tempRtMax != null)
            return tempRtMax;

        if (!root.deleted)
            return root;

        LazySTNode tempLftMax = findMax(root.lftChild);

        if (tempLftMax != null)
            return tempLftMax;

        return null;
    }

    /**
     * The private recursive method that helps adding a node to the
     * tree, compares data with the root to determine where the node
     * should be inserted. Creates a new node if the node doesn't exist
     * in the tree, or set the deleted to false if the node is marked as
     * deleted before. Increment both soft and hard sizes.
     * @param root tree root that is needed to compare with the new data.
     * @param x the data that the newly added node contains.
     * @return the newly added node.
     */
    protected LazySTNode insert( LazySTNode root, E x )
    {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null)
        {
            mSize++;
            mSizeHard++;
            return new LazySTNode(x, null, null, false);
        }

        compareResult = x.compareTo(root.data);
        if ( compareResult < 0 )
            root.lftChild = insert(root.lftChild, x);
        else if ( compareResult > 0 )
            root.rtChild = insert(root.rtChild, x);
        else if (root.deleted) {
            root.deleted = false;
            mSize++;
        }
        return root;
    }

    /**
     * The private recursive method that performs lazy deletion, if a
     * node is need to be deleted simply set the boolean attribute
     * deleted to true if it is found in the tree, and decrement the
     * soft size only.
     * @param root tree root that is needed to compare with the data.
     * @param x the data that should be deleted from the tree.
     */
    protected void remove( LazySTNode root, E x  )
    {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null)
            return;

        compareResult = x.compareTo(root.data);
        if ( compareResult < 0 )
            remove(root.lftChild, x);
        else if ( compareResult > 0 )
            remove(root.rtChild, x);

            // found the node
        else{
            root.deleted = true;
            mSize--;
        }
    }

    /**
     * The private recursive method that traverses both deleted and
     * non-deleted nodes.
     * @param func the function that performs during traversal.
     * @param treeNode the tree node that starts to traverse.
     * @param <F> the method is generic type.
     */
    protected <F extends Traverser<? super E>>
    void traverseHard(F func, LazySTNode treeNode)
    {
        if (treeNode == null)
            return;

        traverseHard(func, treeNode.lftChild);
        func.visit(treeNode.data);
        traverseHard(func, treeNode.rtChild);
    }

    /**
     * The private recursive method that traverses non-deleted
     * nodes only.
     * @param func the function that performs during traversal.
     * @param treeNode the tree node that starts to traverse.
     * @param <F> the method is generic type.
     */
    protected <F extends Traverser<? super E>>
    void traverseSoft(F func, LazySTNode treeNode)
    {
        if (treeNode == null)
            return;
        if (treeNode.deleted)
        {
            traverseSoft(func, treeNode.lftChild);
            traverseSoft(func, treeNode.rtChild);
            return;
        }

        traverseSoft(func, treeNode.lftChild);
        func.visit(treeNode.data);
        traverseSoft(func, treeNode.rtChild);
    }

    /**
     * The private recursive method that helps finding a node in
     * the tree and return it, returns null if the node doesn't
     * exist or is marked as deleted in the tree.
     * @param root the tree root that is needed to compare with
     *             the data.
     * @param x the data that we want to find in the tree.
     * @return the node if found, otherwise null.
     */
    protected LazySTNode find( LazySTNode root, E x )
    {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null)
            return null;

        compareResult = x.compareTo(root.data);
        if (compareResult < 0)
            return find(root.lftChild, x);
        if (compareResult > 0)
            return find(root.rtChild, x);

        if (root.deleted)
        {
            return null;
        }
        return root;   // found
    }

    /**
     * The private recursive method that helps making a deep copy
     * of a tree by coping every nodes in the tree.
     * @param root the tree root that starts coping.
     * @return the newly copied node.
     */
    protected LazySTNode cloneSubtree(LazySTNode root)
    {
        LazySTNode newNode;
        if (root == null)
            return null;

        // does not set myRoot which must be done by caller
        newNode = new LazySTNode
                (
                        root.data,
                        cloneSubtree(root.lftChild),
                        cloneSubtree(root.rtChild),
                        root.deleted
                );
        return newNode;
    }

    /**
     *  The private recursive method that helps return the height
     *  of a tree.
     * @param treeNode the tree node that starts counting the
     *                 height.
     * @param height the height passed in by its public method.
     * @return the height of the tree.
     */
    protected int findHeight( LazySTNode treeNode, int height )
    {
        int leftHeight, rightHeight;
        if (treeNode == null)
            return height;
        height++;
        leftHeight = findHeight(treeNode.lftChild, height);
        rightHeight = findHeight(treeNode.rtChild, height);
        return (leftHeight > rightHeight)? leftHeight : rightHeight;
    }

    /**
     * The private inner class for the node objects in a tree, contains
     * constructors and class attributes.
     */
    private class LazySTNode
    {
        // use public access so the tree or other classes can access members
        public LazySTNode lftChild, rtChild;
        public E data;
        public LazySTNode myRoot;  // needed to test for certain error
        public boolean deleted;

        /**
         * A constructor for LazySTNode type object, assign its corresponding
         * attributes.
         * @param d the data that the node carries.
         * @param lft the node's left child.
         * @param rt the node's right child.
         * @param deleted the mark for the node if it is soft deleted or not.
         */
        public LazySTNode( E d, LazySTNode lft, LazySTNode rt, boolean deleted )
        {
            lftChild = lft;
            rtChild = rt;
            data = d;
            deleted = false;
        }

        /**
         * The default constructor for LazySTNode type object, assigns the data,
         * left and right child to null and defaults non-deleted.
         */
        public LazySTNode()
        {
            this(null, null, null, false);
        }
    }
}