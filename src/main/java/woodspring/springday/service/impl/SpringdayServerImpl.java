package woodspring.springday.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import woodspring.springday.service.SpringdayServerService;
import woodspring.springday.socket.GeneralServer;

public class SpringdayServerImpl  implements SpringdayServerService{
	
	@Autowired
	GeneralServer dayServer;

	@Override
	public String startServer(int port) {
		dayServer.serverStartPort( port);
		return null;
	}

}
