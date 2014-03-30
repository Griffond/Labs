package by.grsu.domain;

public class Ip {
	
	private Integer[] ip;
	
	public Ip(Integer[] ip){
		this.ip = ip;
	}
	
	public Integer[] getIp() {
		return ip;
	}

	public void setIp(Integer[] ip) {
		this.ip = ip;
	}
	
	public Integer getOct(Integer i){
		return this.ip[i];
	}
	
	public void setOct(Integer i, Integer oct){
		this.ip[i] = oct;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for(Integer oct:this.ip){
			result.append(oct.toString()).append('.');
		}
		result.deleteCharAt(result.length()-1);
		return result.toString();
	}

}
