package woodspring.springday.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("SimpleClient")
public class SimpleClient {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Socket clientSocket;
	private PrintWriter outWriter;
	private BufferedReader inReader;
	private boolean isConnected = false;
	
	public void startConnection(String ip, int port) {
		try {
			clientSocket = new Socket(ip, port);
			outWriter = new PrintWriter( clientSocket.getOutputStream(), true);
			inReader = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));
			logger.info("server IP: "+ clientSocket.getRemoteSocketAddress()+" port:"+ clientSocket.getPort() +" -- 0--");
			isConnected = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	public String sendMessage(String msg) {
		outWriter.println(msg);
		String resp = "";;
		try {
			resp = inReader.readLine();
			logger.info("SimpleClient receiver response:[" + resp+"]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logger.info("== SimpleClient sendMessage end");
		}
		return resp;
	}

	public void stopConnection() {
		try {
			inReader.close();
			outWriter.close();
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logger.info("== SimpleClient stopConnection end");
		}

	}
	
}
