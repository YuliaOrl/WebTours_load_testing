package chains;

import io.gatling.javaapi.core.ChainBuilder;

import static config.HeadersConfig.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class AuthChains {

    public static ChainBuilder home_page = exec(
            http("/WebTours/")
                    .get("/WebTours/")
                    .headers(headers_0)
                    .check(substring("Web Tours")),
            http("/WebTours/header.html")
                    .get("/WebTours/header.html")
                    .headers(headers_1)
                    .check(status().in(200, 304)),
            http("/favicon.ico")
                    .get("/favicon.ico")
                    .headers(headers_2)
                    .check(status().in(200, 404)),
            http("/cgi-bin/welcome.pl?signOff=true")
                    .get("/cgi-bin/welcome.pl?signOff=true")
                    .headers(headers_1)
                    .check(substring("A Session ID has been created")),
            http("/WebTours/images/hp_logo.png")
                    .get("/WebTours/images/hp_logo.png")
                    .headers(headers_3),
            http("/WebTours/images/webtours.png")
                    .get("/WebTours/images/webtours.png")
                    .headers(headers_3),
            http("/WebTours/home.html")
                    .get("/WebTours/home.html")
                    .headers(headers_1)
                    .check(substring("Welcome to the Web Tours site")),
            http("/cgi-bin/nav.pl?in=home")
                    .get("/cgi-bin/nav.pl?in=home")
                    .headers(headers_1)
                    .check(substring("Web Tours Navigation Bar"))
                    .check(regex("name=\"userSession\" value=\"(.+?)\"").saveAs("userSession")),
            http("/WebTours/images/mer_login.gif")
                    .get("/WebTours/images/mer_login.gif")
                    .headers(headers_4));

    public static ChainBuilder login = exec(
            http("/cgi-bin/login.pl")
                    .post("/cgi-bin/login.pl")
                    .headers(headers_5)
                    .formParam("userSession", "#{userSession}")
                    .formParam("username", "#{username}")
                    .formParam("password", "#{password}")
                    .formParam("login.x", "0")
                    .formParam("login.y", "0")
                    .formParam("JSFormSubmit", "off")
                    .check(substring("User password was correct"))
                    .resources(
                            http("/cgi-bin/nav.pl?page=menu&in=home")
                                    .get("/cgi-bin/nav.pl?page=menu&in=home")
                                    .headers(headers_1)
                                    .check(substring("Web Tours Navigation Bar")),
                            http("/cgi-bin/login.pl?intro=true")
                                    .get("/cgi-bin/login.pl?intro=true")
                                    .headers(headers_1)
                                    .check(substring("Welcome to Web Tours"))
                                    .check(substring("Welcome, <b>#{username}</b>, to the Web Tours")),
                            http("/WebTours/images/flights.gif")
                                    .get("/WebTours/images/flights.gif")
                                    .headers(headers_4),
                            http("/WebTours/images/signoff.gif")
                                    .get("/WebTours/images/signoff.gif")
                                    .headers(headers_4),
                            http("/WebTours/images/itinerary.gif")
                                    .get("/WebTours/images/itinerary.gif")
                                    .headers(headers_4),
                            http("/WebTours/images/in_home.gif")
                                    .get("/WebTours/images/in_home.gif")
                                    .headers(headers_4)));

    public static ChainBuilder sign_off = exec(
            http("/cgi-bin/welcome.pl?signOff=1")
                    .get("/cgi-bin/welcome.pl?signOff=1")
                    .headers(headers_1)
                    .check(substring("A Session ID has been created"))
                    .resources(
                            http("/cgi-bin/nav.pl?in=home")
                                    .get("/cgi-bin/nav.pl?in=home")
                                    .headers(headers_1)
                                    .check(substring("Web Tours Navigation Bar"))));
}
