package cs1c;

/**
 * The test class of quick sort.
 */
public class RescursionTestMain {

    /**
     * First initialize an array that contains different sizes, then
     * use them to create an array and fill in random numbers. For
     * each array we make recursive call up to 300 times, and print
     * out their corresponding time.
     * @param args not used
     */
    public static void main(String[] args)
    {
        final int [] ARRAY_SIZE = {
                                    20000, 600000, 1200000, 1800000, 2400000,
                                    3000000, 3600000, 4200000, 4800000, 5400000,
                                    6000000, 6600000, 7200000, 7800000, 8400000,
                                    9000000, 9600000, 10200000, 10800000, 11400000,
                                    12000000
                                    };

        final int RECURSION_LIMIT = 300;
        int randomInt, currentSize;
        long startTime, endTime, estimatedTime;

        for (int i = 0; i < ARRAY_SIZE.length; i++)
        {
            currentSize = ARRAY_SIZE[i];
            Integer[] testArray = new Integer[currentSize];
            for(int j = 0; j < currentSize; j++) {
                randomInt = (int) (Math.random() * currentSize);
                testArray[j] = randomInt;
            }
            System.out.println("Array size is " + currentSize);

            for(int k = 2; k <= RECURSION_LIMIT; k += 2){
                if (FHsort.setRecursionLimit(RECURSION_LIMIT)) {

                    startTime = System.currentTimeMillis();
                    FHsort.quickSort(testArray);

                    endTime = System.currentTimeMillis();
                    estimatedTime = endTime - startTime;

                    System.out.println(k + ", " + estimatedTime);
                }
            }
        }
    }
}
