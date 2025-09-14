package household;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("One argument is required!");
            return; // Exit gracefully if no file is provided or more arguments are provided
        }
        DataPopulator populatedHashtable = null;
        String inputFilePath = args[0];
        try {
            // Redirect System.out to output.txt
            PrintStream fileOut = new PrintStream(new FileOutputStream("output.txt"));
            System.setOut(fileOut);

            populatedHashtable = new DataPopulator();
            populatedHashtable.populateHashtableFromFile(inputFilePath);
            FilterData.filter(populatedHashtable.getDatabase());
            System.out.println("The End!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
