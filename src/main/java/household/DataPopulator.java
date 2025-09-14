package household;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataPopulator {

    // Create a Hashtable with String keys and ArrayList<Person> values
    private Map<String, List<Person>> database = new HashMap<>();

    String formatRecord(String str) {
        // Remove . and extra space between words in a string
        String strFormatted = str.replace(".", "").toLowerCase().replaceAll("\\s+", " ").trim();
        return strFormatted;
    }

    // Checks if a record for the same person exists to avoid adding duplicates.
    boolean checkIfPersonExists(List<Person> peopleArray, Person person) {
        for (Person one : peopleArray) {
            if (one.getFirstName().equalsIgnoreCase(person.getFirstName())
                    && one.getLastName().equalsIgnoreCase(person.getLastName()) && one.getAge() == person.getAge()) {
                return true;
            }
        }
        return false;
    }

    void addRecordToDatabase(Person record) {
        String recordFormatted = formatRecord(record.getAddress());

        // Get the list for this address, or create it if absent
        List<Person> peopleRecords = database.computeIfAbsent(recordFormatted, k -> new ArrayList<>());

        // Check if the person already exists
        if (!checkIfPersonExists(peopleRecords, record)) {
            // Add the person if not present
            peopleRecords.add(record);
        }
    }

    void populateHashtableFromFile(String fileName) {
        try {
            // Read lines from the input file line by line
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines) {
                String[] fields = line.split("\",");

                // Formatting a string - remove " and , and trim to remove extra space at the
                // end of a string
                String firstnameFormat = fields[0].replace("\"", "").trim();
                String lastnameFormat = fields[1].replace("\"", "").trim();
                String addressFormat = fields[2].replace("\"", "").replace(",", "").trim();
                String cityFormat = fields[3].replace("\"", "").trim();
                String stateFormat = fields[4].replace("\"", "").trim();
                String ageFormat = fields[5].replace("\"", "");

                // Concatenate strings to build a full address
                String addressConcatenation = addressFormat + ", " + cityFormat + ", " + stateFormat;

                // Build a Person object
                Integer ageToInteger = Integer.valueOf(ageFormat);
                Person record = new Person(firstnameFormat, lastnameFormat, addressConcatenation, ageToInteger);

                // Add the record to the hashtable
                addRecordToDatabase(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Print the hashtable
    void printHashtable() {
        for (Map.Entry<String, List<Person>> entry : database.entrySet()) {
            String key = entry.getKey();
            List<Person> values = entry.getValue();
            System.out.println("Key: " + key);
            System.out.println("Values: ");
            for (Person person : values) {
                System.out.println(person);
            }
        }
    }

    Map<String, List<Person>> getDatabase() {
        return database;
    }

}
