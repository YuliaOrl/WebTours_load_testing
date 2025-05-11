package simulations;

import io.gatling.javaapi.core.Simulation;

import static config.HttpProtocolConfig.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static simulations.UC1_Login.scnLogin;
import static simulations.UC2_FindTicket.scnFindTicket;
import static simulations.UC3_BuyTicket.scnBuyTicket;
import static simulations.UC4_ViewItinerary.scnViewItinerary;
import static simulations.UC5_DeleteTicket.scnDeleteTicket;
import static simulations.UC6_Registration.scnRegistration;

public class TestMax extends Simulation {

    {
        setUp(scnLogin.injectClosed(
                        rampConcurrentUsers(1).to(11).during(180),
                        constantConcurrentUsers(11).during(1320),
                        rampConcurrentUsers(11).to(12).during(10),
                        constantConcurrentUsers(12).during(1320),
                        rampConcurrentUsers(12).to(13).during(10),
                        constantConcurrentUsers(13).during(1320),
                        rampConcurrentUsers(13).to(14).during(10),
                        constantConcurrentUsers(14).during(1320),
                        rampConcurrentUsers(14).to(15).during(10),
                        constantConcurrentUsers(15).during(1320)
                ),

                scnFindTicket.injectClosed(
                        rampConcurrentUsers(1).to(22).during(180),
                        constantConcurrentUsers(22).during(1320),
                        rampConcurrentUsers(22).to(24).during(10),
                        constantConcurrentUsers(24).during(1320),
                        rampConcurrentUsers(24).to(26).during(10),
                        constantConcurrentUsers(26).during(1320),
                        rampConcurrentUsers(26).to(28).during(10),
                        constantConcurrentUsers(28).during(1320),
                        rampConcurrentUsers(28).to(30).during(10),
                        constantConcurrentUsers(30).during(1320)
                ),

                scnBuyTicket.injectClosed(
                        rampConcurrentUsers(1).to(22).during(180),
                        constantConcurrentUsers(22).during(1320),
                        rampConcurrentUsers(22).to(24).during(10),
                        constantConcurrentUsers(24).during(1320),
                        rampConcurrentUsers(24).to(26).during(10),
                        constantConcurrentUsers(26).during(1320),
                        rampConcurrentUsers(26).to(28).during(10),
                        constantConcurrentUsers(28).during(1320),
                        rampConcurrentUsers(28).to(30).during(10),
                        constantConcurrentUsers(30).during(1320)
                ),

                scnViewItinerary.injectClosed(
                        rampConcurrentUsers(1).to(22).during(180),
                        constantConcurrentUsers(22).during(1320),
                        rampConcurrentUsers(22).to(24).during(10),
                        constantConcurrentUsers(24).during(1320),
                        rampConcurrentUsers(24).to(26).during(10),
                        constantConcurrentUsers(26).during(1320),
                        rampConcurrentUsers(26).to(28).during(10),
                        constantConcurrentUsers(28).during(1320),
                        rampConcurrentUsers(28).to(30).during(10),
                        constantConcurrentUsers(30).during(1320)
                ),

                scnDeleteTicket.injectClosed(
                        rampConcurrentUsers(1).to(11).during(180),
                        constantConcurrentUsers(11).during(1320),
                        rampConcurrentUsers(11).to(12).during(10),
                        constantConcurrentUsers(12).during(1320),
                        rampConcurrentUsers(12).to(13).during(10),
                        constantConcurrentUsers(13).during(1320),
                        rampConcurrentUsers(13).to(14).during(10),
                        constantConcurrentUsers(14).during(1320),
                        rampConcurrentUsers(14).to(15).during(10),
                        constantConcurrentUsers(15).during(1320)
                ),

                scnRegistration.injectClosed(
                        rampConcurrentUsers(1).to(22).during(180),
                        constantConcurrentUsers(22).during(1320),
                        rampConcurrentUsers(22).to(24).during(10),
                        constantConcurrentUsers(24).during(1320),
                        rampConcurrentUsers(24).to(26).during(10),
                        constantConcurrentUsers(26).during(1320),
                        rampConcurrentUsers(26).to(28).during(10),
                        constantConcurrentUsers(28).during(1320),
                        rampConcurrentUsers(28).to(30).during(10),
                        constantConcurrentUsers(30).during(1320)
                ))
                .protocols(httpProtocol).maxDuration(6820);
    }

    @Override
    public void after() {
        java.util.logging.Logger.getLogger("Scenarios").info("Test successfully completed.");
    }
}
