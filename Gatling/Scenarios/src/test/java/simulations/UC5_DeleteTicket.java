package simulations;

import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static chains.AuthChains.*;
import static chains.TicketChains.*;
import static config.HttpProtocolConfig.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;

public class UC5_DeleteTicket extends Simulation {

    private static final FeederBuilder<String> userDataFeeder = csv("data/users.csv").circular();

    public static ScenarioBuilder scnDeleteTicket = scenario("UC5_DeleteTicket")
            .forever().on(pace(48)
            .feed(userDataFeeder)
            .group("home_page").on(home_page)
            .pause(2, 8)
            .group("login").on(login)
            .pause(2, 8)
            .group("itinerary").on(itinerary)
            .pause(2, 8)
            .group("delete_flights").on(delete_flights));

    public static ScenarioBuilder scnDeleteTicketStress = scenario("UC5_DeleteTicket")
            .exec(pace(48)
                    .feed(userDataFeeder)
                    .group("home_page").on(home_page)
                    .pause(2, 8)
                    .group("login").on(login)
                    .pause(2, 8)
                    .group("itinerary").on(itinerary)
                    .pause(2, 8)
                    .group("delete_flights").on(delete_flights));

    {
        setUp(scnDeleteTicket.injectClosed(constantConcurrentUsers(50).during(48)).protocols(httpProtocol))
                .maxDuration(48);
    }
}
