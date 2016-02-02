package de.mgr.ws;


import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:/camel-context-test.xml" })
public class MockEndpointTest {
	
    @Autowired
    protected CamelContext camelContext;

    @Autowired
    private DataSource dataSource;

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
        MockEndpoint.resetMocks(camelContext);
        get200.sendBody("get200");
        Exchange exchange = mockOut.getExchanges().get(0);
        MockEndpoint.assertIsSatisfied(camelContext);
        String responseCode = exchange.getIn().getHeader("CamelHttpResponseCode").toString();
        assertEquals(responseCode,"200");
    }
    
    @Test
    public void post200() throws Exception {
        MockEndpoint.resetMocks(camelContext);
        post200.sendBody("post200");
        Exchange exchange = mockOut.getExchanges().get(0);
        MockEndpoint.assertIsSatisfied(camelContext);
        String responseCode = exchange.getIn().getHeader("CamelHttpResponseCode").toString();
        assertEquals(responseCode,"200");
    }

    @Test
    public void get201() throws Exception {
        MockEndpoint.resetMocks(camelContext);
        get201.sendBody("get201");
        Exchange exchange = mockOut.getExchanges().get(0);
        MockEndpoint.assertIsSatisfied(camelContext);
        String responseCode = exchange.getIn().getHeader("CamelHttpResponseCode").toString();
        assertEquals(responseCode,"201");
    }
    
    @Test
    public void post201() throws Exception {
        MockEndpoint.resetMocks(camelContext);
        post201.sendBody("post201");
        Exchange exchange = mockOut.getExchanges().get(0);
        MockEndpoint.assertIsSatisfied(camelContext);
        String responseCode = exchange.getIn().getHeader("CamelHttpResponseCode").toString();
        assertEquals(responseCode,"201");
    }
}
