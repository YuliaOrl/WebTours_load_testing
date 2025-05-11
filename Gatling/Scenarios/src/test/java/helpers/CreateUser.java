package helpers;

import com.github.javafaker.Faker;

import java.util.Random;

public class CreateUser {

    private final Faker faker = new Faker();
    private static final Random random = new Random();

    private static final String LETTERS_LOWER = "abcdefghijklmnopqrstuvwxyz";

    private final String username, firstname, lastname, password, street, cityStateZip;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getStreet() {
        return street;
    }

    public String getCityStateZip() {
        return cityStateZip;
    }

    private String createCityStateZip() {
        String city = faker.address().city(),
                state = faker.address().state(),
                zip = faker.address().zipCode();
        return city + "/" + state + "/" + zip;
    }

    private String generateSuffix() {
        // Генерируем 2 случайные буквы
        StringBuilder suffix = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            int index = random.nextInt(LETTERS_LOWER.length());
            suffix.append(LETTERS_LOWER.charAt(index));
        }
        return suffix.toString();
    }

    public CreateUser() {
        this.firstname = faker.name().firstName();
        this.lastname = faker.name().lastName();
        this.username = this.firstname.toLowerCase() + generateSuffix();
        this.password = faker.internet().password(6, 8, true, false, false);
        this.street = faker.address().streetName();
        this.cityStateZip = createCityStateZip();
    }
}