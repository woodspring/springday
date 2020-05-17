package woodspring.springday.socket.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerServerHandler extends Thread {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter outWriter;
	private BufferedReader inReader;
	
	public GenerServerHandler(ServerSocket socket) {
		this.serverSocket = socket;
	}
	public GenerServerHandler(Socket socket) {
		logger.info(" clientSocket constructor ");
		this.clientSocket = socket;
		logger.info(" clientSocket constructor end");
	}
	
	private String onHandle(BufferedReader inReader_) {
		StringBuffer strBuf = new StringBuffer();
		String inLine = "";
		try {
			boolean endConnected = false;
			int index = 0;
			while( inReader_.ready() && ( (inLine = inReader_.readLine()) != null) && !endConnected)  {
				if (inLine.contains("#####")) {
					strBuf.append("Good Byte, will disconnected!!");
					endConnected = true;
				} else {
					strBuf.append( index+++" add message:["+ inLine+"]");
				}
				logger.info( "onHandle:["+ strBuf.toString()+"]");
				outWriter.println( strBuf.toString());
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return strBuf.toString();
		
	}
	
	private void close() {
		try {
			inReader.close();
			outWriter.close();
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void run() {
		try {
			logger.info( "GenerServerHandler start to run");
			//clientSocket = serverSocket.accept();
			logger.info("clientSocket get after serverSocket.accept");;
			outWriter = new PrintWriter(clientSocket.getOutputStream(), true);
			inReader = new BufferedReader( new InputStreamReader( clientSocket.getInputStream()));
			onHandle(inReader);
			
			close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logger.info("GenerServerHandler finally end  ");
		}		
	}

}
