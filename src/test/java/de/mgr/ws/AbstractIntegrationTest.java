package de.mgr.ws;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class AbstractIntegrationTest extends CamelSpringTestSupport {

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        // must create application context with refrehs = false when using active profiles
        return new ClassPathXmlApplicationContext(new String[]{"classpath:/camel-context-test.xml"}, false);
    }

    @Produce(uri = "direct:mockGet200")
    protected ProducerTemplate get200;

    @Produce(uri = "direct:mockGet201")
    protected ProducerTemplate get201;
    
    @Produce(uri = "direct:mockPost200")
    protected ProducerTemplate post200;    
    
    @Produce(uri = "direct:mockPost201")
    protected ProducerTemplate post201;    
    
    @EndpointInject(uri = "mock:mockOut")
    protected MockEndpoint mockOut;

    @Test
    public void get200() throws Exception {
    	CamelContext camelContext = this.context();
        MockEndpoint.resetMocks(camelContext);
        get200.sendBody("get200");
        Exchange exchange = mockOut.getExchanges().get(0);
        MockEndpoint.assertIsSatisfied(camelContext);
        String responseCode = exchange.getIn().getHeader("CamelHttpResponseCode").toString();
        assertEquals(responseCode,"200");
    }
    
    @Test
    public void post200() throws Exception {
    	CamelContext camelContext = this.context();
        MockEndpoint.resetMocks(camelContext);
        post200.sendBody("post200");
        Exchange exchange = mockOut.getExchanges().get(0);
        MockEndpoint.assertIsSatisfied(camelContext);
        String responseCode = exchange.getIn().getHeader("CamelHttpResponseCode").toString();
        assertEquals(responseCode,"200");
    }
}