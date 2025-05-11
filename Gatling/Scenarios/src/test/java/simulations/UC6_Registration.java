package simulations;

import io.gatling.javaapi.core.*;

import static chains.RegistrationChains.*;
import static chains.AuthChains.*;
import static config.HttpProtocolConfig.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;

public class UC6_Registration extends Simulation {

    public static ScenarioBuilder scnRegistration = scenario("UC6_Registration")
            .forever().on(pace(74)
                    .group("home_page").on(home_page)
                    .pause(2, 13)
                    .group("sign_up").on(sign_up)
                    .pause(2, 13)
                    .group("registration").on(registration)
                    .pause(2, 13)
                    .group("continue_registration").on(continue_registration));

    public static ScenarioBuilder scnRegistrationStress = scenario("UC6_Registration")
            .exec(pace(74)
                    .group("home_page").on(home_page)
                    .pause(2, 13)
                    .group("sign_up").on(sign_up)
                    .pause(2, 13)
                    .group("registration").on(registration)
                    .pause(2, 13)
                    .group("continue_registration").on(continue_registration));

    {
        setUp(scnRegistration.injectClosed(constantConcurrentUsers(5).during(74)).protocols(httpProtocol))
                .maxDuration(74);
    }
}
