package chains;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.List;

import static config.HeadersConfig.*;
import static helpers.DeleteBody.createDeleteBody;
import static helpers.TicketData.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class TicketChains {

    public static ChainBuilder flights = exec(
            http("/cgi-bin/welcome.pl?page=search")
                    .get("/cgi-bin/welcome.pl?page=search")
                    .headers(headers_1)
                    .check(substring("User has returned to the search page"))
                    .resources(
                            http("/cgi-bin/nav.pl?page=menu&in=flights")
                                    .get("/cgi-bin/nav.pl?page=menu&in=flights")
                                    .headers(headers_1)
                                    .check(substring("Web Tours Navigation Bar")),
                            http("/WebTours/images/in_flights.gif")
                                    .get("/WebTours/images/in_flights.gif")
                                    .headers(headers_4),
                            http("/WebTours/images/home.gif")
                                    .get("/WebTours/images/home.gif")
                                    .headers(headers_4),
                            http("/cgi-bin/reservations.pl?page=welcome")
                                    .get("/cgi-bin/reservations.pl?page=welcome")
                                    .headers(headers_1)
                                    .check(substring("Flight Selections"))
                                    .check(regex("name=\"seatPref\" value=\"(.+?)\"").findRandom().saveAs("seatPreference"))
                                    .check(regex("name=\"seatType\" value=\"(.+?)\"").findRandom().saveAs("seatType"))
                                    .check(css("select[name='depart'] option", "value").findAll().saveAs("citiesDeparture"))
                                    .check(css("select[name='arrive'] option", "value").findAll().saveAs("citiesArrival")),
                            http("/WebTours/images/button_next.gif")
                                    .get("/WebTours/images/button_next.gif")
                                    .headers(headers_4)));

    public static ChainBuilder find_flight = exec(session -> {
        List<String> citiesDeparture = session.getList("citiesDeparture");
        List<String> citiesArrival = session.getList("citiesArrival");
        String[] randomCity = randomCity(citiesDeparture, citiesArrival);
        String[] randomDate = generateDate();
        return session
                .set("departure", randomCity[0])
                .set("arrival", randomCity[1])
                .set("departDate", randomDate[0])
                .set("returnDate", randomDate[1]);
    }).exec(
            http("/cgi-bin/reservations.pl")
                    .post("/cgi-bin/reservations.pl")
                    .headers(headers_6)
                    .body(ElFileBody("bodies/find_flight.html"))
                    .check(substring("Flight Selections"))
                    .check(regex("name=\"outboundFlight\" value=\"(.+?);").findRandom().saveAs("flightNumber"))
                    .check(regex("name=\"returnFlight\" value=\"(.+?);").findRandom().saveAs("flightNumberReturn"))
                    .check(substring("Flight departing from <B>#{departure}</B> to <B>#{arrival}</B> on <B>#{departDate}"),
                            substring("Flight departing from <B>#{arrival}</B> to <B>#{departure}</B> on <B>#{returnDate}")));

    public static ChainBuilder payment = exec(
            http("/cgi-bin/reservations.pl")
                    .post("/cgi-bin/reservations.pl")
                    .headers(headers_7)
                    .body(ElFileBody("bodies/payment.html"))
                    .check(substring("Flight Reservation"))
                    .check(substring("#{firstname}"), substring("#{lastname}"), substring("#{passenger}"),
                            substring("#{street}"), substring("#{city_state_zip}")));

    public static ChainBuilder true_invoice = exec(session -> session
            .set("passenger2", generatePassenger())
            .set("passenger3", generatePassenger())
    ).exec(
            http("/cgi-bin/reservations.pl")
                    .post("/cgi-bin/reservations.pl")
                    .headers(headers_8)
                    .body(ElFileBody("bodies/true_invoice.html"))
                    .check(substring("Reservation Made!"))
                    .check(substring("#{firstname}#{lastname}'s Flight Invoice"), substring("Credit Card # #{card}"),
                            substring("3 #{seatType} Class tickets from #{departure} to #{arrival}"),
                            substring("Flight #{flightNumber} leaves #{departure}  for #{arrival}"),
                            substring("Flight #{flightNumberReturn} leaves #{arrival}  for #{departure}"),
                            substring("#{departDate}"), substring("#{returnDate}")));

    public static ChainBuilder itinerary = exec(
            http("/cgi-bin/welcome.pl?page=itinerary")
                    .get("/cgi-bin/welcome.pl?page=itinerary")
                    .headers(headers_1)
                    .check(substring("User wants the intineraries"))
                    .resources(
                            http("/cgi-bin/nav.pl?page=menu&in=itinerary")
                                    .get("/cgi-bin/nav.pl?page=menu&in=itinerary")
                                    .headers(headers_1)
                                    .check(substring("Web Tours Navigation Bar")),
                            http("/WebTours/images/in_itinerary.gif")
                                    .get("/WebTours/images/in_itinerary.gif")
                                    .headers(headers_4),
                            http("/cgi-bin/itinerary.pl")
                                    .get("/cgi-bin/itinerary.pl")
                                    .headers(headers_1)
                                    .check(substring("Flights List"))
                                    .check(substring("#{firstname} #{lastname}\n" +
                                            " 's Flight Transaction Summary"))
                                    .check(css("input[name='flightID']", "value").findAll().saveAs("flightIDsAll")),
                            http("/WebTours/images/cancelallreservations.gif")
                                    .get("/WebTours/images/cancelallreservations.gif")
                                    .headers(headers_4),
                            http("/WebTours/images/cancelreservation.gif")
                                    .get("/WebTours/images/cancelreservation.gif")
                                    .headers(headers_4)));

    public static ChainBuilder delete_flights = exec(session -> {
        List<String> flightIDsAll = session.getList("flightIDsAll");
        String[] requestBody = createDeleteBody(flightIDsAll);
        System.out.println("Deleted flight number 1: " + requestBody[1]);
        System.out.println("Delete body: \n" + requestBody[0]);
        return session
                .set("requestBody", requestBody[0])
                .set("deleteFlightID", requestBody[1]);
    }).exec(
            http("/cgi-bin/itinerary.pl")
                    .post("/cgi-bin/itinerary.pl")
                    .headers(headers_9)
                    .body(ElFileBody("bodies/delete_flights.html"))
                    .check(substring("Flights List"))
                    .check(substring("#{deleteFlightID}").notExists()));
}
