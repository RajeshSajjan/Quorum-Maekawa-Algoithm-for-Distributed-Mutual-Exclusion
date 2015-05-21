/* Author : Rajesh Sajjan
 * Version : 1.0
 * Description : Listen program gets spawned on the servers and clients and stay in listen state to receive messages.
 */

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

// Class that gets spawned in server and stays in listen state receiving messages.
public class Listen implements Runnable {

	Socket SOCK;
	volatile  String str2;
	static volatile String str3;
	volatile String str4;
	private volatile boolean flager=true;
	static AtomicInteger count = new AtomicInteger();
	
	public Listen(Socket sock) {
		this.SOCK = sock;
	}
	public void run() {
		try {
			str2 = "";
			str3 = "";
			while (flager) {
				flager=false;
				DataInputStream din = new DataInputStream(SOCK.getInputStream());
				str2 = din.readUTF().toString();
				System.out.println("Received msg: " + str2);
								
				if(str2.contains("start"))
					str3=str2;
							str4=str2;

				if(str4.contains("REQUEST"))
				{
					// Check if server is in locked state or unlocked state. if locked add to queue else send grant and go to locked state.
					String[] parts = str4.split(" ");
					if(Caller.locker)
					{
						Caller.waitlist.add(parts[2]);
						System.out.println("Caller.waitlist --- "+Caller.waitlist);
					}
					else
					{
						Caller.locker = true;
						String node = parts[2];
						Ricart.Send_grant(node);
					}
	
				}
				
				else if(str4.contains("GRANT"))
				{
					String[] parts = str4.split(" ");
					String node = parts[1];
					Caller.RE_count++;
					
					// Use a flag here to make sure multiple times CS is not called because of grant from multiple quorums.
					if(Caller.entry)
					{
						Caller.grant.add(node);
					for(int i =0 ; i < 15; i++)
					{
					if(Caller.grant.containsAll(queue.group.get(i)))
					{
						Caller.entry = false;
						System.out.println("Received grant from quorum "+queue.group.get(i));
						//Enter Critical Section
						System.out.println("Number of messages exchanged "+(7+Caller.grant.size()));
						if(Ricart.inCS)
						Ricart.CriticalSection(Caller.serial);
						break;
					}
					}
						
						/*if(Caller.grant.contains("s1") && Caller.grant.contains("s2") && Caller.grant.contains("s4"))
						{
							Caller.entry = false;
							//System.out.println("Received grant from quorum "+queue.group.get(i));
							//Enter Critical Section
							System.out.println("Number of messages exchanged "+(7+Caller.grant.size()));
							if(Ricart.inCS)
							Ricart.CriticalSection(Caller.serial);
							break;
						}
						else if(Caller.grant.contains("s1") && Caller.grant.contains("s2") && Caller.grant.contains("s5"))
						{
							Caller.entry = false;
							//System.out.println("Received grant from quorum "+queue.group.get(i));
							//Enter Critical Section
							System.out.println("Number of messages exchanged "+(7+Caller.grant.size()));
							if(Ricart.inCS)
							Ricart.CriticalSection(Caller.serial);
							break;
						}
						else if(Caller.grant.contains("s1") && Caller.grant.contains("s4") && Caller.grant.contains("s5"))
						{
							Caller.entry = false;
							//System.out.println("Received grant from quorum "+queue.group.get(i));
							//Enter Critical Section
							System.out.println("Number of messages exchanged "+(7+Caller.grant.size()));
							if(Ricart.inCS)
							Ricart.CriticalSection(Caller.serial);
							break;
						}
						else if(Caller.grant.contains("s1") && Caller.grant.contains("s3") && Caller.grant.contains("s6"))
						{
							Caller.entry = false;
							//System.out.println("Received grant from quorum "+queue.group.get(i));
							//Enter Critical Section
							System.out.println("Number of messages exchanged "+(7+Caller.grant.size()));
							if(Ricart.inCS)
							Ricart.CriticalSection(Caller.serial);
							break;
						}
						else if(Caller.grant.contains("s1") && Caller.grant.contains("s3") && Caller.grant.contains("s7"))
						{
							Caller.entry = false;
							//System.out.println("Received grant from quorum "+queue.group.get(i));
							//Enter Critical Section
							System.out.println("Number of messages exchanged "+(7+Caller.grant.size()));
							if(Ricart.inCS)
							Ricart.CriticalSection(Caller.serial);
							break;
						}
						else if(Caller.grant.contains("s1") && Caller.grant.contains("s6") && Caller.grant.contains("s7"))
						{
							Caller.entry = false;
							//System.out.println("Received grant from quorum "+queue.group.get(i));
							//Enter Critical Section
							System.out.println("Number of messages exchanged "+(7+Caller.grant.size()));
							if(Ricart.inCS)
							Ricart.CriticalSection(Caller.serial);
							break;
						}
						else if(Caller.grant.contains("s2") && Caller.grant.contains("s4") && Caller.grant.contains("s3") && Caller.grant.contains("s6"))
						{
							Caller.entry = false;
							//System.out.println("Received grant from quorum "+queue.group.get(i));
							//Enter Critical Section
							System.out.println("Number of messages exchanged "+(7+Caller.grant.size()));
							if(Ricart.inCS)
							Ricart.CriticalSection(Caller.serial);
							break;
						}
						else if(Caller.grant.contains("s2") && Caller.grant.contains("s4") && Caller.grant.contains("s3") && Caller.grant.contains("s7"))
						{
							Caller.entry = false;
							//System.out.println("Received grant from quorum "+queue.group.get(i));
							//Enter Critical Section
							System.out.println("Number of messages exchanged "+(7+Caller.grant.size()));
							if(Ricart.inCS)
							Ricart.CriticalSection(Caller.serial);
							break;
						}
						else if(Caller.grant.contains("s2") && Caller.grant.contains("s4") && Caller.grant.contains("s6") && Caller.grant.contains("s7"))
						{
							Caller.entry = false;
							//System.out.println("Received grant from quorum "+queue.group.get(i));
							//Enter Critical Section
							System.out.println("Number of messages exchanged "+(7+Caller.grant.size()));
							if(Ricart.inCS)
							Ricart.CriticalSection(Caller.serial);
							break;
						}
						else if(Caller.grant.contains("s2") && Caller.grant.contains("s5") && Caller.grant.contains("s3") && Caller.grant.contains("s6"))
						{
							Caller.entry = false;
							//System.out.println("Received grant from quorum "+queue.group.get(i));
							//Enter Critical Section
							System.out.println("Number of messages exchanged "+(7+Caller.grant.size()));
							if(Ricart.inCS)
							Ricart.CriticalSection(Caller.serial);
							break;
						}
						else if(Caller.grant.contains("s2") && Caller.grant.contains("s5") && Caller.grant.contains("s3") && Caller.grant.contains("s7"))
						{
							Caller.entry = false;
							//System.out.println("Received grant from quorum "+queue.group.get(i));
							//Enter Critical Section
							System.out.println("Number of messages exchanged "+(7+Caller.grant.size()));
							if(Ricart.inCS)
							Ricart.CriticalSection(Caller.serial);
							break;
						}
						else if(Caller.grant.contains("s2") && Caller.grant.contains("s5") && Caller.grant.contains("s6") && Caller.grant.contains("s7"))
						{
							Caller.entry = false;
							//System.out.println("Received grant from quorum "+queue.group.get(i));
							//Enter Critical Section
							System.out.println("Number of messages exchanged "+(7+Caller.grant.size()));
							if(Ricart.inCS)
							Ricart.CriticalSection(Caller.serial);
							break;
						}
						else if(Caller.grant.contains("s4") && Caller.grant.contains("s5") && Caller.grant.contains("s3") && Caller.grant.contains("s6"))
						{
							Caller.entry = false;
							//System.out.println("Received grant from quorum "+queue.group.get(i));
							//Enter Critical Section
							System.out.println("Number of messages exchanged "+(7+Caller.grant.size()));
							if(Ricart.inCS)
							Ricart.CriticalSection(Caller.serial);
							break;
						}
						else if(Caller.grant.contains("s4") && Caller.grant.contains("s5") && Caller.grant.contains("s3") && Caller.grant.contains("s7"))
						{
							Caller.entry = false;
							//System.out.println("Received grant from quorum "+queue.group.get(i));
							//Enter Critical Section
							System.out.println("Number of messages exchanged "+(7+Caller.grant.size()));
							if(Ricart.inCS)
							Ricart.CriticalSection(Caller.serial);
							break;
						}
						else if(Caller.grant.contains("s4") && Caller.grant.contains("s5") && Caller.grant.contains("s6") && Caller.grant.contains("s7"))
						{
							Caller.entry = false;
							//System.out.println("Received grant from quorum "+queue.group.get(i));
							//Enter Critical Section
							System.out.println("Number of messages exchanged "+(7+Caller.grant.size()));
							if(Ricart.inCS)
							Ricart.CriticalSection(Caller.serial);
							break;
						}*/
					}
					else
					{
						// Send release message.
						Socket sock = (Socket) Caller.ips.get(node);
						DataOutputStream dout=new DataOutputStream(sock.getOutputStream());
						dout.writeUTF("RELEASE "+Caller.serial+"");  
						//System.out.println("sent release from "+Caller.serial+" to "+node);
					}
					
					
				}
				
				else if(str4.contains("RELEASE"))
				{
					//check size of the waitlist if its zero then keep server in unlocked state else send grant to the client from front of the queue 
					//and remove front element in queue.
					
					String[] parts = str4.split(" ");
					String node = parts[1];
					
					if(Caller.waitlist.size() == 0)
					{
						Caller.locker = false;
					}
					else
					{
						String node1 = Caller.waitlist.remove();
						Ricart.Send_grant(node1);
						
					}

				}
				else if(str4.contains("RELEASE-WAIT"))
				{
					//Check if waitlist has the node if it is remove it.
					String[] parts = str4.split(" ");
					String node = parts[1];
					
					Iterator<String> itr2 = Caller.waitlist.iterator(); 
					while(itr2.hasNext())
						{  
						boolean temp = false;
							String listnode = itr2.next();
							if(listnode.equalsIgnoreCase(node))
							{
								System.out.println("Found "+Caller.serial+" in the waitlist and it will be removed");
								//temp = true;
								Caller.waitlist.remove(node);
								break;
							}
						}  
				}
				else if(str4.contains("Completion"))
				{
					count.getAndIncrement();
					
					if(count.get() == 5)
					{
						System.out.println("Sent terminate to all other clients and servers");
						 HashMap<String,Socket> ipstemp = new HashMap<String,Socket>(Caller.ips);
						    Iterator itr2=ipstemp.entrySet().iterator();  
						    while (itr2.hasNext()) 
						    {
						        Map.Entry pair = (Map.Entry)itr2.next();
						        String tim = (String) pair.getKey();
						        Socket sock = (Socket) Caller.ips.get(tim);
						        Caller.RQ_count++;
						        DataOutputStream dout=new DataOutputStream(sock.getOutputStream());
								dout.writeUTF("Terminate "+Caller.serial+"");  
								itr2.remove();
						    }
						    
						    System.out.println("Terminating node "+Caller.serial);
							System.exit(0);
						    
					}
				}
				else if(str4.contains("Terminate"))
				{
					System.out.println("Terminating node "+Caller.serial);
					System.exit(0);
				}
				
				
			flager=true;}
		} 
		catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Completed. Terminating\n");
		}
	}
}
