package chains;

import helpers.CreateUser;
import io.gatling.javaapi.core.ChainBuilder;

import static config.HeadersConfig.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class RegistrationChains {

    public static ChainBuilder sign_up = exec(
            http("/cgi-bin/login.pl?username=&password=&getInfo=true")
                    .get("/cgi-bin/login.pl?username=&password=&getInfo=true")
                    .headers(headers_1)
                    .check(substring("Customer Profile")));

    public static ChainBuilder registration = exec(session -> {
        CreateUser user = new CreateUser();
        return session
                .set("username", user.getUsername())
                .set("password", user.getPassword())
                .set("firstname", user.getFirstname())
                .set("lastname", user.getLastname())
                .set("street", user.getStreet())
                .set("city_state_zip", user.getCityStateZip());
    }).exec(
            http("/cgi-bin/login.pl")
                    .post("/cgi-bin/login.pl")
                    .headers(headers_10)
                    .body(ElFileBody("bodies/registration.html"))
                    .check(substring("Welcome to Web Tours"))
                    .check(substring("Thank you, <b>#{username}</b>, for registering")));

    public static ChainBuilder continue_registration = exec(
            http("/cgi-bin/welcome.pl?page=menus")
                    .get("/cgi-bin/welcome.pl?page=menus")
                    .headers(headers_1)
                    .check(substring("User has returned to the home page"))
                    .resources(
                            http("/cgi-bin/nav.pl?page=menu&in=home")
                                    .get("/cgi-bin/nav.pl?page=menu&in=home")
                                    .headers(headers_1)
                                    .check(substring("Web Tours Navigation Bar")),
                            http("/cgi-bin/login.pl?intro=true")
                                    .get("/cgi-bin/login.pl?intro=true")
                                    .headers(headers_1)
                                    .check(substring("Welcome to Web Tours"))
                                    .check(substring("Welcome, <b>#{username}</b>, to the Web Tours")))
    ).exec(session -> {
        String username = session.getString("username");
        String password = session.getString("password");
        String firstname = session.getString("firstname");
        String lastname = session.getString("lastname");
        String street = session.getString("street");
        String city_state_zip = session.getString("city_state_zip");
        System.out.println("username: " + (username != null ? username : "Not Found"));
        System.out.println("password: " + (password != null ? password : "Not Found"));
        System.out.println("firstname: " + (firstname != null ? firstname : "Not Found"));
        System.out.println("lastname: " + (lastname != null ? lastname : "Not Found"));
        System.out.println("street: " + (street != null ? street : "Not Found"));
        System.out.println("city_state_zip: " + (city_state_zip != null ? city_state_zip : "Not Found"));
        return session;
    });
}
