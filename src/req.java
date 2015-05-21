import java.util.*;


public class req {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String msg = "rajesh,Sajjan";
		List<String> recvdMsgTokens = new ArrayList<String>();
		StringTokenizer msgTokens = new StringTokenizer(msg,",");
		while(msgTokens.hasMoreTokens()){
			recvdMsgTokens.add(msgTokens.nextToken());
		}
		
		System.out.println(recvdMsgTokens.get(0));
		msg="raj";
		System.out.println(recvdMsgTokens.get(1));
	}

}
