package be.seriousbusiness.java.junit.rule.externalresource;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.seriousbusiness.java.socket.client.SocketClient;
import be.seriousbusiness.java.socket.handler.factory.client.StringClientSocketHandlerFactory;
import be.seriousbusiness.java.socket.handler.factory.server.StringServerSocketHandlerFactory;
import be.seriousbusiness.java.socket.server.SocketServer;

public class ExternalResourceTest {
	private static final Logger LOGGER=LoggerFactory.getLogger(ExternalResourceTest.class);
	private SocketServer socketServer;
	private SocketClient socketClient;
	
	@Rule
	public ExternalResource externalResource=new ExternalResource(){
		
		/**
		 * Set-up and start the Server.
		 */
		@Override
	    protected void before() throws Throwable {
			LOGGER.info("Create and start the server.");
			socketServer=new SocketServer(4763,new StringClientSocketHandlerFactory());
			socketServer.start();
	    };

	    /**
	     * Stop  the Server when finished.
	     */
	    @Override
	    protected void after() {
	    	LOGGER.info("Stop the server.");
	    	socketServer.interrupt();
	    };
	};
	
	@Before
	public void before(){
		LOGGER.info("Create the client");
		socketClient=new SocketClient(socketServer.getHost(),socketServer.getPort(),new StringServerSocketHandlerFactory());
	}
	
	@After
	public void after(){
		LOGGER.info("Stop the client");
		socketClient.interrupt();
	}
	
	/**
	 * Test the client by letting him talk to the server for a while.
	 * @throws InterruptedException
	 */
	@Test
	public void testSocketClientStart() throws InterruptedException{
		LOGGER.info("[Test] - SocketClient start");
		socketClient.start();
		Thread.sleep(60000); // Sleep for 60 seconds
	}
	
	/**
	 * Test if the socketServer's host and port are set.
	 * @throws InterruptedException
	 */
	@Ignore
	@Test
	public void testSocketServerHostPort() throws InterruptedException{
		LOGGER.info("[Test] - SocketServer host and port");
		Thread.sleep(10000); // Sleep for 10 seconds
		Assert.assertNotNull("The socketServer's host should be set",socketServer.getHost());
		Assert.assertNotNull("The socketServer's port should be set",socketServer.getPort());
	}
	
	/**
	 * Test if the socketClient's host and port are set.
	 * @throws InterruptedException
	 */
	@Ignore
	@Test
	public void testSocketClientHostPort() throws InterruptedException{
		LOGGER.info("[Test] - SocketClient host and port");
		socketClient.start();
		Thread.sleep(10000); // Sleep for 10 seconds
		Assert.assertNotNull("The socketClient's host should be set",socketClient.getHost());
		Assert.assertNotNull("The socketClient's port should be set",socketClient.getPort());
	}

}
