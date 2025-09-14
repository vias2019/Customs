package household;

public class Person {
    private String firstName;
    private String lastName;
    private String address;
    private int age;

    public Person(String firstName, String lastName, String address, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return "Person{"
                + "firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", address='" + address + '\''
                + ", age=" + age
                + '}';
    }
}
