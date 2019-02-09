package controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class HelloWorldController extends Controller {

    public Result helloWorld() {

        return ok("hello");
    }

    public Result helloName(String name) {

        return ok("hello" + name);
    }

    public Result helloNum(String name, Integer num) {

        final StringBuffer sb = new StringBuffer();

        for(int i = 0 ; i < num ; i++)
        {
            sb.append(name);
            sb.append("\n");
        }

        return ok(sb.toString());

    }

    public Result greetings() {

        final JsonNode json = request().body().asJson();

       // final String firstName = json.get("first_name").asText();

       //  final String lastName = json.get("last_name").asText();

        final Person person = Json.fromJson(json,Person.class);

        String message = person.getFirstName() +" "+ person.getLastName();

        return ok(message);

    }

    public Result me() {

        final Person me = new Person("Joanna","Ruth");
        final JsonNode json = Json.toJson(me);

        return ok(json);
    }

    static class Person {

        @JsonProperty("first_name")
        String firstName;

        @JsonProperty("last_name")
        String lastName;

        public Person() {


        }

        public Person(String firstName, String lastName) {

            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}
