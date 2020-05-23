package woodspring.springday.socket.Handler;

import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;
import java.util.Map;
import java.util.concurrent.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ReadWriteHandler implements CompletionHandler<Integer, Map<String, Object>> {
	private final static Logger logger = LoggerFactory.getLogger("ReadWriteHandler");

	@Override
	public void completed(Integer result, Map<String, Object> attachment) {
		Map<String, Object> actionInfo = attachment;
		String action = (String)actionInfo.get("action");
		if ( "read".equalsIgnoreCase(action) ) {
			
		} else if ( "write".equalsIgnoreCase(action) ) {
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			actionInfo.put("action", "read");
			actionInfo.put("buffer",  buffer);
			
		} else {
			logger.info(" completed, action:"+ action+ " NOT FOUND");
		}
		
	}

	@Override
	public void failed(Throwable exc, Map<String, Object> attachment) {
		exc.printStackTrace();
		attachment.forEach( (key, value) -> logger.info("ReadWriteHandler failed, key:"+ key+" value:"+ ((String)value) ));
		
	}



}
