package by.grsu.domain;

/**
 * Network's ip address entity
 * @author Raman_Volkau
 */

public class Ip {
	
	/**
	 * Integer presentation of address
	 */
	private Integer[] ip;
	
	public Ip(Integer[] ip){
		this.ip = ip;
	}
	
	public Integer[] getIp() {
		Integer[] temp = new Integer[4];
		for(int i = 0; i<4; i++){
			temp[i] = this.ip[i];
		}
		return temp;
	}
	
	/**
	 * Calculate next address and set instead current address
	 */
	public void increment(){
		for(int i=3; i>=0; i--){
			if(this.ip[i]<255) {
				this.ip[i]+=1;
				break;
			}
			else this.ip[i] = 0;
		}
	}
	
	/**
	 * Calculate previous address and set instead current address
	 */
	public void decrement(){
		for(int i=3; i>=0; i--){
			if(this.ip[i]>0) {
				this.ip[i]-=1;
				break;
			}
		}
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
	
	/**
	 * @return binary presentation of current address
	 */
	public String toBinaryString(){
		StringBuilder result = new StringBuilder();
		for(Integer oct:this.ip){
			result.append(Integer.toBinaryString(oct));
		}
		return result.toString();
	}
	
	/**
	 * @return presentation of current address in format {oct1}.{oct2}.{oct3}.{oct4}
	 */
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
