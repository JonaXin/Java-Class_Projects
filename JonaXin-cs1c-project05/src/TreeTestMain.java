/**
 * The test main() for the binary tree data structure.
 */
public class TreeTestMain {
    /**
     * Tests the tree by adding and removing nodes from the tree and
     * prints out the whole tree.
     * @param args not used.
     */
    public static void main(String[] args)
    {
        LazySearchTree<Integer> testTree = new LazySearchTree<Integer>();
        testTree.insert(10);
        testTree.insert(13);
        testTree.insert(7);
        testTree.insert(21);
        testTree.insert(3);
        testTree.insert(8);
        System.out.println("The smallest value in the tree: " + testTree.findMin());
        System.out.println("The biggest value in the tree: " + testTree.findMax());
        System.out.println("Size: " + testTree.size() + "\nHard size: " + testTree.sizeHard() + "\n");
        testTree.remove(3);
        testTree.remove(10);
        testTree.remove(21);
        System.out.println("The smallest value in the tree: " + testTree.findMin());
        System.out.println("The biggest value in the tree: " + testTree.findMax());
        System.out.println("Size: " + testTree.mSize + "\nHard size: " + testTree.mSizeHard + "\n");
        testTree.insert(21);
        System.out.println("The biggest value in the tree: " + testTree.findMax());
        System.out.println("Size: " + testTree.mSize + "\nHard size: " + testTree.mSizeHard);
        System.out.println("\nDone with testing.");
    }
}
