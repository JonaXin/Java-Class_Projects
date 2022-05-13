package subsetsum;

import java.util.ArrayList;
import java.util.Scanner;

import cs1c.TimeConverter;

/**
 * An object of type ShoppingBag class creates an object of type subset sum to find a best
 * possible grocery shopping list within the given budget.
 * @author Foothill College, Bita M, Jonathan
 */
public class ShoppingBag {
    private ArrayList<Double> priceOfGroceries;

    /**
     * Parameterized constructor for an object of class ShoppingBag.
     * Reads in a file and adds the prices into a list of the prices of groceries
     *
     * @param filePath The input file to parse.
     */
    public ShoppingBag(String filePath) {

        GroceriesFileReader reader = new GroceriesFileReader();

        priceOfGroceries = reader.readFile(filePath);

        // Check the size of the resulting ArrayList object.
        if (priceOfGroceries.size() < 1) {
            System.out.println("WARNING: The list of groceries is empty.");
            return;
        }

        System.out.printf("The list of groceries has %d items. \n", priceOfGroceries.size());

    }

    /**
     * Accessor method returns the list of items read from input file.
     *
     * @return the price of groceries.
     */
    public ArrayList<Double> getPriceOfGroceries() {
        return priceOfGroceries;
    }

    /**
     * Reads an input file that contains the prices of the different items.
     * Then stores and outputs a list of items we can buy
     * given the condition of how much money you have in your wallet.
     * We're at a cash only store. So, no checks or credit purchases!
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        
        final String FILEPATH = "groceries.txt";

        ShoppingBag bag = new ShoppingBag(FILEPATH);
        ArrayList<Double> shoppingList = bag.getPriceOfGroceries();

        // displays the prices of items in the input file
        System.out.println("Groceries wanted:");
        System.out.println(shoppingList);

        // prompt the user for their budget

        Scanner keyboard = new Scanner(System.in);
        do {
            // REMINDER: Test your implementation against various budgets, including boundary cases.
            System.out.println("\nEnter your budget:");
        } while (!keyboard.hasNextDouble());

        double budget = Double.parseDouble(keyboard.nextLine());

        // for measuring run time
        long startTime, estimatedTime;

        // capture the start time
        startTime = System.nanoTime();

        // stop the timer
        estimatedTime = System.nanoTime() - startTime;

        // report algorithm time
        System.out.println("\nAlgorithm Elapsed Time: "
                + TimeConverter.convertTimeToString(estimatedTime));

        ArrayList<Double> purchases = SubsetSum.findSubset(shoppingList, budget);
        System.out.println("Purchased grocery prices are:");
        System.out.println(purchases);

        System.err.flush();
        System.out.println("Done with ShoppingBag.");


    }
}
