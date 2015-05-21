/* Author : Rajesh Sajjan
 * Version : 1.0
 * Description : Program broadcasts messages to all servers.
 */

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class broadcast {
	
	public synchronized  void broadsend(String string) throws IOException, InterruptedException {
	
		// Send request message all the servers along with client which requesting to broadcast.
		if(string.contains("REQUEST"))
		{
		  Iterator it = Caller.ips.entrySet().iterator();
		  Ricart.setTimer(System.nanoTime());
		  System.out.println(" TIMER ************* "+Ricart.getTimer());
		  
		  //Gets socket connection from hashmap and sends message.
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        Socket sock = (Socket) pair.getValue();
		        Ricart.req_count.getAndIncrement(); // to keep count of all requests sent. so that we can track of how many reply to receive
		        Caller.count++;
		        Caller.RQ_count++;
		        DataOutputStream dout=new DataOutputStream(sock.getOutputStream());
				String str2="REQUEST "+Ricart.getTimer()+" "+Caller.serial+"";
				//System.out.println("******** "+str2+"*********");
				dout.writeUTF(str2); 
		    }
		  
		}
	}

}