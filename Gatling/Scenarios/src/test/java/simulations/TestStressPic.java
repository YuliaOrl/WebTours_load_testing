package simulations;

import io.gatling.javaapi.core.Simulation;

import static config.HttpProtocolConfig.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.constantConcurrentUsers;
import static io.gatling.javaapi.core.CoreDsl.rampConcurrentUsers;
import static simulations.UC1_Login.scnLoginStress;
import static simulations.UC2_FindTicket.scnFindTicketStress;
import static simulations.UC3_BuyTicket.scnBuyTicketStress;
import static simulations.UC4_ViewItinerary.scnViewItineraryStress;
import static simulations.UC5_DeleteTicket.scnDeleteTicketStress;
import static simulations.UC6_Registration.scnRegistrationStress;

public class TestStressPic extends Simulation {

    {
        setUp(scnLoginStress.injectClosed(
                        rampConcurrentUsers(1).to(15).during(120),
                        constantConcurrentUsers(15).during(600),
                        rampConcurrentUsers(15).to(12).during(10),
                        constantConcurrentUsers(12).during(120),
                        rampConcurrentUsers(12).to(8).during(10),
                        constantConcurrentUsers(8).during(120),
                        rampConcurrentUsers(8).to(4).during(10),
                        constantConcurrentUsers(4).during(120),
                        rampConcurrentUsers(4).to(0).during(10)
                ),

                scnFindTicketStress.injectClosed(
                        rampConcurrentUsers(1).to(30).during(120),
                        constantConcurrentUsers(30).during(600),
                        rampConcurrentUsers(30).to(22).during(10),
                        constantConcurrentUsers(22).during(120),
                        rampConcurrentUsers(22).to(15).during(10),
                        constantConcurrentUsers(15).during(120),
                        rampConcurrentUsers(15).to(8).during(10),
                        constantConcurrentUsers(8).during(120),
                        rampConcurrentUsers(8).to(0).during(10)
                ),

                scnBuyTicketStress.injectClosed(
                        rampConcurrentUsers(1).to(30).during(120),
                        constantConcurrentUsers(30).during(600),
                        rampConcurrentUsers(30).to(22).during(10),
                        constantConcurrentUsers(22).during(120),
                        rampConcurrentUsers(22).to(15).during(10),
                        constantConcurrentUsers(15).during(120),
                        rampConcurrentUsers(15).to(8).during(10),
                        constantConcurrentUsers(8).during(120),
                        rampConcurrentUsers(8).to(0).during(10)
                ),

                scnViewItineraryStress.injectClosed(
                        rampConcurrentUsers(1).to(30).during(120),
                        constantConcurrentUsers(30).during(600),
                        rampConcurrentUsers(30).to(22).during(10),
                        constantConcurrentUsers(22).during(120),
                        rampConcurrentUsers(22).to(15).during(10),
                        constantConcurrentUsers(15).during(120),
                        rampConcurrentUsers(15).to(8).during(10),
                        constantConcurrentUsers(8).during(120),
                        rampConcurrentUsers(8).to(0).during(10)
                ),

                scnDeleteTicketStress.injectClosed(
                        rampConcurrentUsers(1).to(15).during(120),
                        constantConcurrentUsers(15).during(600),
                        rampConcurrentUsers(15).to(12).during(10),
                        constantConcurrentUsers(12).during(120),
                        rampConcurrentUsers(12).to(8).during(10),
                        constantConcurrentUsers(8).during(120),
                        rampConcurrentUsers(8).to(4).during(10),
                        constantConcurrentUsers(4).during(120),
                        rampConcurrentUsers(4).to(0).during(10)
                ),

                scnRegistrationStress.injectClosed(
                        rampConcurrentUsers(1).to(30).during(120),
                        constantConcurrentUsers(30).during(600),
                        rampConcurrentUsers(30).to(22).during(10),
                        constantConcurrentUsers(22).during(120),
                        rampConcurrentUsers(22).to(15).during(10),
                        constantConcurrentUsers(15).during(120),
                        rampConcurrentUsers(15).to(8).during(10),
                        constantConcurrentUsers(8).during(120),
                        rampConcurrentUsers(8).to(0).during(10)
                ))
                .protocols(httpProtocol).maxDuration(1120);
    }

    @Override
    public void after() {
        java.util.logging.Logger.getLogger("Scenarios").info("Test successfully completed.");
    }
}
