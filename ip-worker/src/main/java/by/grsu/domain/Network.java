package by.grsu.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Network entity
 * @author Raman_Volkau
 */

public class Network {
	
	private Ip networkIp;
	private Ip broadcastIp;
	private NetworkMask mask;
	private List<Network> subNetworks;

	/**
	 * Constructor for network
	 * @param ip - new network's address
	 * @param mask - new network's mask
	 */
	public Network(Ip ip, NetworkMask mask){
		this.networkIp = ip;
		this.mask = mask;
		subNetworks = new ArrayList<>();
		calcBroadcastIp();
	}
	
	/**
	 * Add subnetwork
	 * @param numHosts - number of hosts in this subnetwork
	 */
	public void addSubnet(Integer numHosts){
		numHosts +=3;
		Network temp;
		
		// if we add first subnetwork, set for this subnetwork parent network's address
		if(this.subNetworks.size()==0){ 
			temp = new Network(networkIp, new NetworkMask(numHosts));
		}
		//if we also added any subnetworks, set for this subnetwork previous subnetwork's address, but incremented
		else { 				
			Ip newNetworkIp = new Ip(subNetworks.get(subNetworks.size()-1).broadcastIp.getIp());  
			newNetworkIp.increment();
			temp = new Network(newNetworkIp, new NetworkMask(numHosts));
		}
		subNetworks.add(temp);
		
	}
	
	/**
	 * Calculate broadcast address for this network and network's mask
	 */
	private void calcBroadcastIp(){
		broadcastIp = new Ip(this.networkIp.getIp());
		Integer[] maskIp = mask.getIp().getIp();
		for(int i=0; i<maskIp.length; i++){
			if(maskIp[i]==255){
				broadcastIp.setOct(i, networkIp.getOct(i));
			}else{
				broadcastIp.setOct(i, networkIp.getOct(i) + 255-maskIp[i]);
			}
		}
	}
	
	/**
	 * @return default gateway's address
	 */
	private Ip getGateway(){
		Ip gateway = new Ip(this.networkIp.getIp());
		gateway.increment();
		return gateway;
	}
	
	@Override
	public String toString() {
		return this.networkIp + " /" + this.mask.getNum1() + " via " + getGateway().toString();
	}
	
	public String displaySubNetworks(){
		StringBuilder result = new StringBuilder();
		for(Network temp:this.subNetworks){
			result.append(temp.toString()).append("\n");
		}
		return result.toString();
	}
	
}
