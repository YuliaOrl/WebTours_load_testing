package simulations;

import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static chains.AuthChains.*;
import static chains.TicketChains.*;
import static config.HttpProtocolConfig.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;

public class UC4_ViewItinerary extends Simulation {

    private static final FeederBuilder<String> userDataFeeder = csv("data/users.csv").circular();

    public static ScenarioBuilder scnViewItinerary = scenario("UC4_ViewItinerary")
            .forever().on(pace(128)
            .feed(userDataFeeder)
            .group("home_page").on(home_page)
            .pause(2, 32)
            .group("login").on(login)
            .pause(2, 32)
            .group("itinerary").on(itinerary));

    public static ScenarioBuilder scnViewItineraryStress = scenario("UC4_ViewItinerary")
            .exec(pace(128)
                    .feed(userDataFeeder)
                    .group("home_page").on(home_page)
                    .pause(2, 32)
                    .group("login").on(login)
                    .pause(2, 32)
                    .group("itinerary").on(itinerary));

    {
        setUp(scnViewItinerary.injectClosed(constantConcurrentUsers(1).during(128)).protocols(httpProtocol))
                .maxDuration(128);
    }
}
