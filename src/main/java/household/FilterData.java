package household;

import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

//A separate class for Hashtable filtering is created to make the class extendable to allow add more filters later in the code.
public class FilterData {

    private static final int MIN_ADULT_AGE = 18;

    static void filter(Map<String, List<Person>> hashtable) {

        // Filters hashtable values for each key
        for (Map.Entry<String, List<Person>> entry : hashtable.entrySet()) {
            String household = entry.getKey();
            List<Person> occupants = entry.getValue();

            // Sort the values list in the hashtable - first by lastname then by firstname
            Collections.sort(occupants, Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName));

            // Get the number of all occupants in the household
            int totalOccupants = occupants.size();

            // Get the number of Adults in the household
            int adultCount = countAdults(occupants);

            // Printing the output/results
            System.out.println("Household: " + household + " || number of adults: " + adultCount + " of " + "total occupants "
                    + totalOccupants);
            for (Person person : occupants) {
                if (person.getAge() > MIN_ADULT_AGE) {
                    System.out.println(formatPerson(person));
                }
            }
            System.out.println();
        }
    }

    // Counts the number of persons older than the specified age.
    private static int countAdults(List<Person> persons) {
        int count = 0;
        for (Person person : persons) {
            if (person.getAge() > MIN_ADULT_AGE) {
                count++;
            }
        }
        return count;
    }

    // Formats person details into a string.
    private static String formatPerson(Person person) {
        return person.getFirstName() + ", " + person.getLastName() + ", " + person.getAddress() + ", "
                + person.getAge();
    }
}
