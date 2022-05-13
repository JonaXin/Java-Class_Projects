package subsetsum;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class GroceriesFileReader {
    public ArrayList<Double> readFile(String filePath) {
        ArrayList<Double> reader = new ArrayList<>();
        File file = new File(filePath);

        try {

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                double d = Double.valueOf(line.split(",")[1]);
                reader.add(d);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return reader;
    }
}