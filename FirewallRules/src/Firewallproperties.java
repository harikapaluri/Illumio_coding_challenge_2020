
public class Firewallproperties {
	public String direction;
	public String stream;
	public int port;
	public Long ip;
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Long getIp() {
		return ip;
	}
	public void setIp(Long ip) {
		this.ip = ip;
	}
	public Firewallproperties(String direction, String stream, int port,Long i) {
		this.direction = direction;
		this.stream = stream;
		this.port = port;
		this.ip = i;
	}
	public Firewallproperties(String direction, String stream, int port,String ip) {
		this.direction = direction;
		this.stream = stream;
		this.port = port;
		this.ip =Long.parseLong(ip.replaceAll("\\.", ""));
	}
	
	@Override
	public String toString() {
		return "Firewallproperties [direction=" + direction + ", stream=" + stream + ", port=" + port + ", ip=" + ip
				+ "]";
	}
	@Override
	//Necessary to override because we need to define that two objects are equal only if direction,stream,port and ip are.
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Firewallproperties)) return false;
        Firewallproperties fp = (Firewallproperties) o;
        return  this.direction.equalsIgnoreCase(fp.direction) && this.stream.equalsIgnoreCase(fp.stream) && this.port==fp.port && this.ip.longValue()==fp.ip.longValue();
       		 
    }
	public int hashCode() {
        long hash =  31 * (this.ip + this.port + this.direction.hashCode() + this.stream.hashCode()); //get unique key from all the components
        return Long.valueOf(hash).hashCode();
    }
	
}
