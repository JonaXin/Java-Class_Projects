package lazyTrees;

/**
 * A simple test main() for the binary tree data structure, testing
 * the tree by soft deletion and then hard deletion.
 */
public class TreeTestMain {
    /**
     * Tests the tree by adding and removing nodes from the tree and
     * prints out the whole tree.
     * @param args not used.
     */
    public static void main(String[] args)
    {
        LazySearchTree<String> testTree = new LazySearchTree<>();
        testTree.insert("E");
        testTree.insert("B");
        testTree.insert("A");
        testTree.insert("C");
        PrintObject<String> printObject = new PrintObject<>();
        System.out.println("Before deletion...");
        System.out.println("Hard:");
        testTree.traverseHard(printObject);
        System.out.println("Soft:");
        testTree.traverseSoft(printObject);
        System.out.println("After soft deletion...");
        testTree.remove(testTree.mRoot, "A");
        //testTree.remove(testTree.mRoot, "bread");
        //testTree.remove(testTree.mRoot, "soup");
        System.out.println("Hard:");
        testTree.traverseHard(printObject);
        System.out.println("Soft:");
        testTree.traverseSoft(printObject);
        //testTree.remove(testTree.mRoot, "soup");
        System.out.println("After hard deletion...");
        testTree.removeHard(testTree.mRoot, "A");
        //testTree.removeHard(testTree.mRoot, "apple");
        //testTree.removeHard(testTree.mRoot, "soup");
        System.out.println("Hard:");
        testTree.traverseHard(printObject);
        System.out.println("Soft:");
        testTree.traverseSoft(printObject);
    }
}
