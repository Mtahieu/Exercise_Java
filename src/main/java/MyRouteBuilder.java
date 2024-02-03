import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        // here is a sample which processes the input files
        // (leaving them in place - see the 'noop' flag)
        // then performs content based routing on the message using XPath
        from("file:src/data?noop=true")
            .choice()
                .when(xpath("/person/city = 'London'")).process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                // DRAWBACK: HEADER ETC GETS LOST
                exchange.getOut().setBody("foo");
                // do some business logic with the input body
            }
        })
                    .log("UK message")
                    .to("file:target/messages/uk?fileName=uk.txt")
                .when(xpath("/person/city = 'Karlsruhe'"))
                .log("DE message")
                .to("file:target/messages/de")
                .otherwise()
                    .log("Other message")
                    .to("file:target/messages/others");
    }

}
