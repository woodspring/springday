package woodspring.springday.socket;

import java.io.IOException;
import java.net.ServerSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import woodspring.springday.socket.Handler.GenerServerHandler;

@Component("GeneralServer")
public class GeneralServer implements SocketServer{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private ServerSocket serverSocket;

	@Override
	public void serverStartPort(int port) {
		try {
			serverSocket = new ServerSocket( port);
			while (true) {
				logger.info(" serverStartPort:"+port);
				new GenerServerHandler( serverSocket.accept()).start();
				logger.info("after  serverStartPort:"+port);
				//sleep( 60000);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stop();
			logger.info("GeneralServer finally END");
			
		}	
	}

	@Override
	public void stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logger.info("GeneralServer stop  finally" );
		}
		
	}

}
