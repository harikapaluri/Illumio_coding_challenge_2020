import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

public class Firewall {
	public String filepath;
	static Set<Firewallproperties> hash_Set = new HashSet<Firewallproperties>();
	
	
	public Firewall(String filepath) {
		this.filepath = filepath;
		setFirewall(filepath);
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	//Method to read from a csv and set firewall properties
	public void setFirewall(String filepath) {
		
		BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int portFlag=0;
         
		
		 try {
                   
	            br = new BufferedReader(new FileReader(filepath));
	            while ((line = br.readLine()) != null) {
            
	                // use comma as separator
	                String[] fileproperties = line.split(cvsSplitBy);
	                String portNo=fileproperties[2];
	                 String longip=fileproperties[3];
	                 longip=longip.replaceAll("[.]","");
	                if(fileproperties[2].matches("[0-9]-")&&fileproperties[3].matches("[0-9]-")) {
	                	portFlag=2;
	                	     	splitIps(portFlag,fileproperties,hash_Set);         
	                	
	                }
	                else if(fileproperties[2].contains("-")) {
	                	splitport(hash_Set,fileproperties);
	                }
	                else if(fileproperties[3].contains("-")) {
	                	splitIps(portFlag,fileproperties,hash_Set);
	                }
	                else
	                	{
	                	hash_Set.add(new Firewallproperties(fileproperties[0],fileproperties[1],Integer.parseInt(portNo),Long.parseLong(longip)));
	                	}
	                
	            }
	            //Since we are assuming valid input in the firewall csv
	          
		 }
	            catch(FileNotFoundException e) {
	            	e.printStackTrace();
	            }
		 catch (IOException e) {
	            e.printStackTrace();
	        } finally {
 	            if (br != null) {
	                try {
	                    br.close();
	                   
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	           
	            }
		
	}
	//This a method to split network rules when a range of port numbers as well as Ip is given
   private void splitIps( int portFlag,String[] fileproperties,Set<Firewallproperties> hash_Set) throws UnknownHostException {
		// TODO Auto-generated method stub
		String[] ips=fileproperties[3].split("-");
		String ip1=ips[0].replaceAll("[.]","");
		String ip2=ips[1].replaceAll("[.]","");
		long ipStart = Long.valueOf(ip1);
		long ipEnd = Long.valueOf(ip2);
		long ipdiff =  ipEnd -ipStart;
		if(portFlag==2) {
			String[] ports=fileproperties[2].split("-");
			int portmin=Integer.parseInt(ports[0]);
			int portmax=Integer.parseInt(ports[1]);
					int portdiff=portmax=portmin;
			for (int i = 0; i <= portdiff ; i++) {
				for (int j = 0; j <= ipdiff; j++) {
					hash_Set.add(new Firewallproperties(fileproperties[0],fileproperties[1],portmin+i,ipStart+j));
					
				}
			}
			portFlag=0;//Resetting the Flag variable because the next entry might have input which are not ranges of ports and ip's.
		}else {
			for (int j = 0; j <= ipdiff; j++) {
				hash_Set.add(new Firewallproperties(fileproperties[0],fileproperties[1],Integer.parseInt(fileproperties[2]),ipStart+j));
				
			}
		}
			
	}

//If two ports are given as input then split according to port numbers.
	private void splitport(Set<Firewallproperties> hash_Set,String[] fileproperties) {
		String[] ports=fileproperties[2].split("-");
		int minport=Integer.parseInt(ports[0]);
		int maxport=Integer.parseInt(ports[1]);
		 String longip=fileproperties[3].replaceAll("[.]","");
		for(int i=minport;i<=maxport;i++) {
			hash_Set.add(new Firewallproperties(fileproperties[0],fileproperties[1],i,Long.parseLong(longip)));
		}
		
	}
	
	//The method to check if the given packet follows the different firewallRules established in the hash_Set
 public boolean accept_packet(String direction,String stream,int port,String ip) {
	 ip=ip.replaceAll("[.]","");
	 Firewallproperties firewallproperties=new Firewallproperties(direction,stream,port,Long.valueOf(ip));
	 
	 	 if(hash_Set.contains(firewallproperties)) {
	 		
		 return true;
	 }else {
		 
		 return false;
	 }
 }
}
