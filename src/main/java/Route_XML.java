import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class Route_XML extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        // Only .xml files here
        from("file:src/data?noop=true&antInclude=*.xml") //&antInclude=**/*.xml$
                .choice()

                //London Handling
                .when(xpath("/person/city = 'London'")).process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        // DRAWBACK: HEADER ETC GETS LOST
                        exchange.getOut().setBody("foo");
                        // do some business logic with the input body
                    }
                })
                .log("UK message")
                .to("file:target/messages/uk?fileName=uk.txt")

                // Karlsruhe handling
                .when(xpath("/person/city = 'Karlsruhe'"))
                .log("DE message")
                .to("file:target/messages/de")
                .otherwise()
                .log("Other message")
                .to("file:target/messages/others");
    }

}
