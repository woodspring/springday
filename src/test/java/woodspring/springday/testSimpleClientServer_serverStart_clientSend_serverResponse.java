package woodspring.springday;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringRunner;

import woodspring.springday.socket.GeneralServer;
import woodspring.springday.socket.SimpleClient;
import woodspring.springday.socket.SimpleServer;
import woodspring.springday.socket.SocketServer;

@RunWith(SpringRunner.class)
public class testSimpleClientServer_serverStart_clientSend_serverResponse {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Autowired
	//@Qualifier("SimpleServer")
	private SimpleServer simpleSrv;
	
	private GeneralServer oneServer;
	
	private SimpleClient client;
	
	@Before
	public void setup() {
		//simpleSrv = new SimpleServer();
		//simpleSrv.serverStartPort( 5832);
		oneServer = new GeneralServer();
		oneServer.serverStartPort(35832);
		logger.info(" after setup server");
		client = new SimpleClient();
		client.startConnection("127.0.0.1",  35832);
		
		
	}
	
	@Test 
	public void testClientSend_andReceiveResponse() {
		//SimpleClient client = new SimpleClient();
		String msg = "testClientSend_andReceiveResponse";
		//client.startConnection("192.168.0.144", 5832);
		assertTrue( client.isConnected());
		
		String resp = client.sendMessage("TEST from JUNIT");
		logger.info("msg:["+ msg+" receive ----------------------------------------------->resp:["+resp+"]");
		
		assertNotNull(resp);
	//simpleSrv.stop();
	//client.stopConnection();
		//assertNot
		
	}

}
