import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class Route_REST extends RouteBuilder {

    // Source: https://tomd.xyz/camel-call-rest-http/
    // https://camel.apache.org/components/4.0.x/eips/toD-eip.html

    // API I wish to call: https://cat-fact.herokuapp.com/facts
    public void configure() {

        from("timer:mytimer?repeatCount=1")
                .toD("https://cat-fact.herokuapp.com/facts" +
                        "?httpMethod=GET")

                .log("Response is ${body}")
                .process(exchange -> {
                    String responseBody = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody(responseBody);
                })
                .to("file:target/messages/REST?filename=catFacts.txt");

    }
    }
