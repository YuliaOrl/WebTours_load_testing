package simulations;

import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static chains.AuthChains.*;
import static chains.TicketChains.flights;
import static chains.TicketChains.itinerary;
import static config.HttpProtocolConfig.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;

public class UC1_Login extends Simulation {

    private static final FeederBuilder<String> userDataFeeder = csv("data/users.csv").circular();

    public static ScenarioBuilder scnLogin = scenario("UC1_Login")
            .forever().on(pace(90)
            .feed(userDataFeeder)
            .group("home_page").on(home_page)
            .pause(2, 12)
            .group("login").on(login)
            .pause(2, 12)
            .group("flights").on(flights)
            .pause(2, 12)
            .group("itinerary").on(itinerary)
            .pause(2, 12)
            .group("sign_off").on(sign_off));

    public static ScenarioBuilder scnLoginStress = scenario("UC1_Login")
            .exec(pace(90)
                    .feed(userDataFeeder)
                    .group("home_page").on(home_page)
                    .pause(2, 12)
                    .group("login").on(login)
                    .pause(2, 12)
                    .group("flights").on(flights)
                    .pause(2, 12)
                    .group("itinerary").on(itinerary)
                    .pause(2, 12)
                    .group("sign_off").on(sign_off));

    {
        setUp(scnLogin.injectClosed(constantConcurrentUsers(1).during(90)).protocols(httpProtocol))
                .maxDuration(90);
    }
}
