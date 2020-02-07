
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.io.FileNotFoundException;
import java.sql.Connection; import java.sql.DriverManager; import
 java.sql.SQLException;

import org.junit.Test;



 
 public class FirewallTest { 
 // case to show that junit is up and running
 
 @Test 
 public void testSetup() { 
	 String str= "I am done with Junit setup";
 assertEquals("I am done with Junit setup",str); } //This is sample test case
 // to show that database connection is up and running
 
//Test to check if invalid entry returns false without disruption.
@Test
public void testacceptPacket() {
	Firewall fw=new Firewall("txt.csv");
	boolean value=fw.accept_packet("direction", "stream",1, "0.0.0.341");
	assertEquals(value,false);
}
//Test to check if valid entry returns true without disruption.
@Test
public void testpositiveacceptPacket() {
	Firewall fw=new Firewall("C:\\\\Users\\\\harika paluri\\\\git\\\\Illumio_Coding-Challenge\\\\FirewallRules\\\\src\\\\resources\\\\input.csv");
	boolean value=fw.accept_packet("inbound","udp", 53, "192.168.2.1");
	assertEquals(value,true);
}
 
 }
 