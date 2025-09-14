import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileComparatorTest {

    /**
     * Compares two files line by line.
     *
     * @param filePath1 Path to the first file
     * @param filePath2 Path to the second file
     * @return true if files have the same content, false otherwise
     */
    public static boolean compareFiles(String filePath1, String filePath2) {
        try (BufferedReader reader1 = new BufferedReader(new FileReader(filePath1));
             BufferedReader reader2 = new BufferedReader(new FileReader(filePath2))) {

            String line1;
            String line2;

            while (true) {
                line1 = reader1.readLine();
                line2 = reader2.readLine();

                if (line1 == null && line2 == null) {
                    // Both files ended simultaneously
                    return true;
                }
                if (line1 == null || line2 == null) {
                    // One file ended before the other
                    return false;
                }
                if (!line1.equals(line2)) {
                    // Lines differ
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false; // If there's an error, consider files not same
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java FileComparator <file1> <file2>");
            return;
        }

        String file1 = args[0];
        String file2 = args[1];

        boolean areEqual = compareFiles(file1, file2);
        if (areEqual) {
            System.out.println("Files are identical.");
        } else {
            System.out.println("Files differ.");
        }
    }
} 
