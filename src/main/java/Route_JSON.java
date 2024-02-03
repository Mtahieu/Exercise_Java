import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class Route_JSON extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        from("file:src/data?noop=true&antInclude=*.json")//.setProperty("city").jsonpath("$.city")
                .choice()
                .when(jsonpath("$[?(@.city == 'London')]"))
                //.jsonpath("$.city==London",true)
                .log("UK JSON").to("file:target/messages/JSONS/uk")
                .when(jsonpath("$[?(@.city == 'Karlsruhe')]"))
                .log("UK JSON").to("file:target/messages/JSONS/de")
                .otherwise()
                .log("other json").to("file:target/messages/JSONS/other");
    }

}
