package helpers;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicketData {

    private static final Faker faker = new Faker();

    public static String[] randomCity(List<String> citiesDeparture, List<String> citiesArrival) {
        Random random = new Random();
        List<String> citiesArrivalCopy = new ArrayList<>(citiesArrival);

        int randomIndexDepart = random.nextInt(citiesDeparture.size());
        String cityDeparture = citiesDeparture.get(randomIndexDepart);

        citiesArrivalCopy.remove(cityDeparture); // Исключаем город отправления из списка городов прибытыя

        int randomIndexArrive = random.nextInt(citiesArrivalCopy.size());
        String cityArrival = citiesArrivalCopy.get(randomIndexArrive);
        return new String[]{cityDeparture, cityArrival};
    }

    public static String[] generateDate() {
        LocalDate departDate = LocalDate.now().plusWeeks(new Random().nextInt(49) + 1);
        LocalDate returnDate = departDate.plusWeeks(new Random().nextInt(49) + 1);
        return new String[]{departDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                returnDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))};
    }

    public static String generatePassenger() {
        return faker.name().fullName();
    }
}
