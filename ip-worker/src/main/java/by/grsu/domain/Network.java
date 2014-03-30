package by.grsu.domain;

import java.util.ArrayList;
import java.util.List;

public class Network {
	
	private Ip networkIp;
	private Ip broadcastIp;
	private NetworkMask mask;
	private List<Network> subNetworks;

	
	public Network(Ip ip, NetworkMask mask){
		this.networkIp = ip;
		this.mask = mask;
		subNetworks = new ArrayList<>();
		calcBroadcastIp();
	}
	
	/**
	 * add subnetwork
	 * @param numHosts - number of hosts in this subnetwork
	 */
	public void addSubnet(Integer numHosts){
		numHosts +=3;	//add network ip, gateway ip, broadcast ip
		Network temp;
		if(this.subNetworks.size()==0){
			temp = new Network(networkIp, new NetworkMask(numHosts));
		}else {
			temp = new Network(subNetworks.get(subNetworks.size()-1).broadcastIp, new NetworkMask(numHosts));
		}
		subNetworks.add(temp);
		
	}
	
	/**
	 * calculate broadcast ip for this network and network's mask
	 */
	private void calcBroadcastIp(){
		broadcastIp = new Ip(this.networkIp.getIp());
		Integer[] maskIp = mask.getIp().getIp();
		for(int i=0; i<maskIp.length; i++){
			if(maskIp[i]==255){
				broadcastIp.setOct(i, networkIp.getOct(i));
			}else{
				broadcastIp.setOct(i, 255-maskIp[i]);
			}
		}
	}
	
	@Override
	public String toString() {
		return this.networkIp + " " + this.broadcastIp;
	}
	
	public String displaySubNetworks(){
		StringBuilder result = new StringBuilder();
		for(Network temp:this.subNetworks){
			result.append(temp.toString()).append("\n");
		}
		return result.toString();
	}
	
}
