package simulations;

import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static chains.AuthChains.*;
import static chains.TicketChains.*;
import static config.HttpProtocolConfig.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;

public class UC3_BuyTicket extends Simulation {

    private static final FeederBuilder<String> userDataFeeder = csv("data/users.csv").circular();

    public static ScenarioBuilder scnBuyTicket = scenario("UC3_BuyTicket")
            .forever().on(pace(40)
            .feed(userDataFeeder)
            .group("home_page").on(home_page)
            .pause(2, 4)
            .group("login").on(login)
            .pause(2, 4)
            .group("flights").on(flights)
            .pause(2, 4)
            .group("find_flight").on(find_flight)
            .pause(2, 4)
            .group("payment").on(payment)
            .pause(2, 4)
            .group("true_invoice").on(true_invoice)
            .pause(2, 4)
            .group("sign_off").on(sign_off));

    public static ScenarioBuilder scnBuyTicketStress = scenario("UC3_BuyTicket")
            .exec(pace(40)
                    .feed(userDataFeeder)
                    .group("home_page").on(home_page)
                    .pause(2, 4)
                    .group("login").on(login)
                    .pause(2, 4)
                    .group("flights").on(flights)
                    .pause(2, 4)
                    .group("find_flight").on(find_flight)
                    .pause(2, 4)
                    .group("payment").on(payment)
                    .pause(2, 4)
                    .group("true_invoice").on(true_invoice)
                    .pause(2, 4)
                    .group("sign_off").on(sign_off));

    {
        setUp(scnBuyTicket.injectClosed(constantConcurrentUsers(50).during(40)).protocols(httpProtocol))
                .maxDuration(40);
    }
}
