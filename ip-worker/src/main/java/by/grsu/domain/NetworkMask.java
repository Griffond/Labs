package by.grsu.domain;

/**
 * Network mask's entity
 * @author Raman_Volkau
 */

public class NetworkMask {
	
	private Ip ip;

	public NetworkMask(Ip ip){
		this.ip = ip;
	}
	
	/**
	 * Build network mask by the number of necessary hosts
	 * @param numHosts
	 */
	public NetworkMask(Integer numHosts){
		Long temp= 2L;
		while(temp<numHosts){
			temp*=2;
		}
		temp = 4294967295L - temp + 1;
		String binIp = Long.toBinaryString(temp);
		Integer[] ip = new Integer[4];
		for(int i=0; i<ip.length; i++){
			ip[i] = Integer.parseInt(binIp.substring(i*8, (i+1)*8),2);
		}
		
		this.ip = new Ip(ip);
	}
	/**
	 * @return number of '1' in binary presentation
	 */
	public Integer getNum1(){
		Integer result = 0;
		String tempstr = this.ip.toBinaryString();
		for(int i=0; i< tempstr.length(); i++){
			if(tempstr.charAt(i)=='1') result++;
		}
		return result;
	}
	
	public Ip getIp() {
		return ip;
	}

	public void setIp(Ip ip) {
		this.ip = ip;
	}

	/**
	 * @return calculated available number of hosts for this network mask
	 */
	public Integer calcNumHosts(){
		Integer result = 1;
		for(Integer oct:this.ip.getIp()){
			if(255-oct>0){
				result*= 255-oct + 1;
			}
		}
		return result;
	}

}
