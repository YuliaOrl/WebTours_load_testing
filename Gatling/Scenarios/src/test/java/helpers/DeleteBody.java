package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeleteBody {

    public static String[] createDeleteBody(List<String> flightIDsAll) {
        List<String> flightIDs = new ArrayList<>(flightIDsAll);

        Random random = new Random();
        int numberOfFlightsToRemove = Math.min(random.nextInt(3) + 1, flightIDs.size());

        List<String> selectedFlightIDs = flightIDs.subList(0, numberOfFlightsToRemove); // Удаляемые
        List<String> remainingFlightIDs = flightIDs.subList(numberOfFlightsToRemove, flightIDs.size()); // Оставшиеся

        String boundary = "------geckoformboundarydc736b67ecb76e74644bf35d79d6a996";

        StringBuilder requestBody = new StringBuilder();

        for (int i = 0; i < selectedFlightIDs.size(); i++) {
            String flightID = selectedFlightIDs.get(i);
            requestBody.append(boundary).append("\r\n");
            requestBody.append("Content-Disposition: form-data; name=\"").append(i + 1).append("\"\r\n\r\n");
            requestBody.append("on\r\n");
            requestBody.append(boundary).append("\r\n");
            requestBody.append("Content-Disposition: form-data; name=\"flightID\"\r\n\r\n");
            requestBody.append(flightID).append("\r\n");
        }

        for (String flightID : remainingFlightIDs) {
            requestBody.append(boundary).append("\r\n");
            requestBody.append("Content-Disposition: form-data; name=\"flightID\"\r\n\r\n");
            requestBody.append(flightID).append("\r\n");
        }

        requestBody.append(boundary).append("\r\n");
        requestBody.append("Content-Disposition: form-data; name=\"removeFlights.x\"\r\n\r\n");
        requestBody.append("68\r\n");
        requestBody.append(boundary).append("\r\n");
        requestBody.append("Content-Disposition: form-data; name=\"removeFlights.y\"\r\n\r\n");
        requestBody.append("11\r\n");

        for (int i = 1; i <= flightIDs.size(); i++) {
            requestBody.append(boundary).append("\r\n");
            requestBody.append("Content-Disposition: form-data; name=\".cgifields\"\r\n\r\n");
            requestBody.append(i).append("\r\n");
        }
        requestBody.append(boundary).append("--\r\n");

        return new String[]{requestBody.toString(), selectedFlightIDs.get(0)};
    }
}
