package woodspring.springday.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("NonBlockingAsynchServer")
public class NonBlockingAsynchServer  implements Callable<String>{
	private final static Logger logger = LoggerFactory.getLogger("NonBlockingAsynchServer");
	private static  Selector selector = null;
	private ServerSocketChannel serverSocketChannel;
	private InetSocketAddress inetAdrs;
	private String serverIP;
	private int port;
	//private int ops;
	
	public NonBlockingAsynchServer(String serverIp, int port) {
		this.serverIP = serverIp;
		this.port = port;
		try {
			selector = Selector.open();
			serverSocketChannel = ServerSocketChannel.open();
			ServerSocket serverSocket = serverSocketChannel.socket();
			this.inetAdrs = new InetSocketAddress(serverIp, port);
			serverSocket.bind(inetAdrs);
			serverSocketChannel.configureBlocking( false);
			int ops = serverSocketChannel.validOps();
			serverSocketChannel.register(selector, ops);		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logger.info( "IP:"+ serverIp+" port:"+ port +" constructed");
		}
	}

	@Override
	public String call() throws Exception {
		String retStr = new String("SERVER:"+serverIP+":"+port +" was terminated");
		boolean isBreak = false;
		try {
			while (!isBreak) {
				selector.select();
				Set<SelectionKey> selectKeys = selector.selectedKeys();
				Iterator<SelectionKey> itr = selectKeys.iterator();
				while (itr.hasNext()) {
					SelectionKey key = itr.next();
					logger.info(" selectionKey:"+ key.interestOps()+" readyOps:"+ key.readyOps() );
					if ( key.isAcceptable()) {
						handleAccept(serverSocketChannel, key); //serverSocketChannel
					} else if ( key.isReadable()) {
						// We can run non-blocking operation READ on our client
						handleRead(key);
					} else {
						logger.info("Still didn't handle well");
					}
					
					itr.remove();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			retStr += ex.getMessage();
		} finally {
			logger.info( "IP:"+ serverIP+" port:"+ port +" END");
		}
		
		return retStr;
	}
	
	
    private static void handleAccept(ServerSocketChannel mySocket,SelectionKey key) throws IOException {
        logger.info("Connection Accepted...");
        // Accept the connection and set non-blocking mode
        SocketChannel client = mySocket.accept();
        client.configureBlocking(false);
       // Register that client is reading this channel
        client.register(selector, SelectionKey.OP_READ);
    }

    private static void handleRead(SelectionKey key)     throws IOException {
        logger.info("Reading... key:"+ key.interestOps()+" readOp:"+ key.readyOps());
        // create a ServerSocketChannel to read the request
        SocketChannel client = (SocketChannel) key.channel();

        // Create buffer to read data
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        client.read(buffer);
        //    Parse data from buffer to String
        String data = new String(buffer.array()).trim();
        if (data.length() > 0) {
            logger.info("Received message: " + data);
            if (data.equalsIgnoreCase("exit")) {
                client.close();
                logger.info("Connection closed...");
            }
        }
    }


}
