package simulations;

import io.gatling.javaapi.core.Simulation;

import static config.HttpProtocolConfig.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.constantConcurrentUsers;
import static io.gatling.javaapi.core.CoreDsl.rampConcurrentUsers;
import static simulations.UC1_Login.scnLogin;
import static simulations.UC2_FindTicket.scnFindTicket;
import static simulations.UC3_BuyTicket.scnBuyTicket;
import static simulations.UC4_ViewItinerary.scnViewItinerary;
import static simulations.UC5_DeleteTicket.scnDeleteTicket;
import static simulations.UC6_Registration.scnRegistration;

public class TestStressVolume extends Simulation {

    {
        setUp(scnLogin.injectClosed(
                        rampConcurrentUsers(1).to(12).during(180),
                        constantConcurrentUsers(12).during(3720)
                ),

                scnFindTicket.injectClosed(
                        rampConcurrentUsers(1).to(24).during(180),
                        constantConcurrentUsers(24).during(3720)
                ),

                scnBuyTicket.injectClosed(
                        rampConcurrentUsers(1).to(24).during(180),
                        constantConcurrentUsers(24).during(3720)
                ),

                scnViewItinerary.injectClosed(
                        rampConcurrentUsers(1).to(24).during(180),
                        constantConcurrentUsers(24).during(3720)
                ),

                scnDeleteTicket.injectClosed(
                        rampConcurrentUsers(1).to(12).during(180),
                        constantConcurrentUsers(12).during(3720)
                ),

                scnRegistration.injectClosed(
                        rampConcurrentUsers(1).to(24).during(180),
                        constantConcurrentUsers(24).during(3720)
                ))
                .protocols(httpProtocol).maxDuration(3900);
    }

    @Override
    public void after() {
        java.util.logging.Logger.getLogger("Scenarios").info("Test successfully completed.");
    }
}
