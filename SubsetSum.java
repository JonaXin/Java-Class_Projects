package subsetsum;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 */
public class SubsetSum {

    /**
     *
     * @param shoppingList
     * @param budget
     * @return
     */
    public static ArrayList<Double> findSubset(ArrayList<Double> shoppingList, double budget) {
        ArrayList<ArrayList<Double>> col = new ArrayList<>();
        ArrayList<Double> subset = new ArrayList<>();
        col.add(subset);
        for (int i = 0; i < shoppingList.size(); i++) {
            ArrayList<ArrayList<Double>> newCol = new ArrayList<>();
            Iterator<ArrayList<Double>> iterator = col.iterator();
            while (iterator.hasNext()) {
                newCol.add((ArrayList<Double>) iterator.next().clone());
            }

            for (int j = 0; j < col.size(); j++)
                col.get(j).add(shoppingList.get(i));
            col.addAll(newCol);
        }
        return returnSubset(col, budget);
    }

    private static ArrayList<Double> returnSubset(ArrayList<ArrayList<Double>> col, double budget) {
        double totalCost = 0;
        double moneyLeft;
        double bestLeft = budget;
        ArrayList<Double> returnSubset = new ArrayList<>();
        for (int k = 0; k < col.size(); k++) {
            for (int n = 0; n < col.get(k).size(); n++) {
                totalCost = totalCost + col.get(k).get(n);
            }
            if (totalCost == budget) {
                returnSubset = col.get(k);
                break;
            }

            if (totalCost < budget) {
                moneyLeft = budget - totalCost;
                if (moneyLeft < bestLeft) {
                    bestLeft = moneyLeft;
                    returnSubset = col.get(k);
                }
            }
            totalCost = 0;
        }
        return returnSubset;
    }
}