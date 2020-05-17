package woodspring.springday.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("SimpleServer")
public class SimpleServer implements SocketServer {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter outWriter;
	private BufferedReader inReader;
	
	public void serverStartPort( int port) {
		try {
			logger.info("serverStartPort, port:"+port);
			serverSocket = new ServerSocket(port);
			logger.info("serverStartPort, port:"+port +"              ---0-------");
			clientSocket = serverSocket.accept();
			logger.info("serverStartPort, port:"+port +"              ---1-------");
			outWriter = new PrintWriter( clientSocket.getOutputStream(), true);
			inReader = new BufferedReader( new InputStreamReader( clientSocket.getInputStream()));
			StringBuffer strbuff = new StringBuffer();
			String theString = "";
			logger.info("serverStartPort, port:"+port +"              ---2-------");
			while ( inReader.ready() && (theString = inReader.readLine()) != null) {
				logger.info("serverStartPort, port:"+port +"              ---3-------");
				strbuff.append("SimpleServer response:[" + theString+"]");
				strbuff.append("SimpleServer response:[" + theString+"]");
				logger.info( strbuff.toString());

			}
			//inReader = new BufferedReader( new InputStreamReader( clientSocket.getInputStream()));
			//String theString = inReader.readLine();
			logger.info( "end of while "+ strbuff.toString());
			outWriter.println( strbuff.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logger.info( "serverStartPort, port:"+ port+" end");
		}
	}
	
	public void stop() {
		try {
			inReader.close();
			outWriter.close();
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logger.info( " SimpleServer stop end");
		}

	}

}
