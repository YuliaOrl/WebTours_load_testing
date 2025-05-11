package simulations;

import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static chains.AuthChains.*;
import static chains.TicketChains.*;
import static config.HttpProtocolConfig.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;

public class UC2_FindTicket extends Simulation {

    private static final FeederBuilder<String> userDataFeeder = csv("data/users.csv").circular();

    public static ScenarioBuilder scnFindTicket = scenario("UC2_FindTicket")
            .forever().on(pace(76)
            .feed(userDataFeeder)
            .group("home_page").on(home_page)
            .pause(2, 7)
            .group("login").on(login)
            .pause(2, 7)
            .group("flights").on(flights)
            .pause(2, 7)
            .group("find_flight").on(find_flight)
            .pause(2, 7)
            .group("payment").on(payment)
            .pause(2, 7)
            .group("itinerary").on(itinerary)
            .pause(2, 7)
            .group("sign_off").on(sign_off));

    public static ScenarioBuilder scnFindTicketStress = scenario("UC2_FindTicket")
            .exec(pace(76)
                    .feed(userDataFeeder)
                    .group("home_page").on(home_page)
                    .pause(2, 7)
                    .group("login").on(login)
                    .pause(2, 7)
                    .group("flights").on(flights)
                    .pause(2, 7)
                    .group("find_flight").on(find_flight)
                    .pause(2, 7)
                    .group("payment").on(payment)
                    .pause(2, 7)
                    .group("itinerary").on(itinerary)
                    .pause(2, 7)
                    .group("sign_off").on(sign_off));



    {
        setUp(scnFindTicket.injectClosed(constantConcurrentUsers(1).during(76)).protocols(httpProtocol))
                .maxDuration(76);
    }
}
