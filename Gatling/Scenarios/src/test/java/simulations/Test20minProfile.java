package simulations;

import io.gatling.javaapi.core.Simulation;

import static config.HttpProtocolConfig.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.constantConcurrentUsers;
import static simulations.UC1_Login.scnLogin;
import static simulations.UC2_FindTicket.scnFindTicket;
import static simulations.UC3_BuyTicket.scnBuyTicket;
import static simulations.UC4_ViewItinerary.scnViewItinerary;
import static simulations.UC5_DeleteTicket.scnDeleteTicket;
import static simulations.UC6_Registration.scnRegistration;

public class Test20minProfile extends Simulation {

    {
        setUp(scnLogin.injectClosed(constantConcurrentUsers(1).during(1320)),
                scnFindTicket.injectClosed(constantConcurrentUsers(2).during(1320)),
                scnBuyTicket.injectClosed(constantConcurrentUsers(2).during(1320)),
                scnViewItinerary.injectClosed(constantConcurrentUsers(2).during(1320)),
                scnDeleteTicket.injectClosed(constantConcurrentUsers(1).during(1320)),
                scnRegistration.injectClosed(constantConcurrentUsers(2).during(1320)))
                .protocols(httpProtocol).maxDuration(1320);
    }
}
