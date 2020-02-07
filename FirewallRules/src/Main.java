
public class Main {
public static void main(String args[]) {
	Firewall fw=new Firewall("C:\\Users\\harika paluri\\git\\Illumio_Coding-Challenge\\FirewallRules\\src\\resources\\input.csv");
	
	 boolean test1=fw.accept_packet("inbound","udp", 53, "192.168.2.1");
	 
		boolean test2 = fw.accept_packet("outbound","tcp", 10234, "192.168.10.11");
		
		  boolean test3 = fw.accept_packet("inbound","tcp", 80,"192.168.1.2"); 
		  boolean test4 = fw.accept_packet("inbound","udp",43,"12.53.6.25"); 
		  boolean test5 = fw.accept_packet("inbound","udp", 24, "52.12.48.92");
		 
		System.out.println(test1);
		System.out.println(test2);
		
		 System.out.println(test3); System.out.println(test4);
		  System.out.println(test5);
		 

}
}
