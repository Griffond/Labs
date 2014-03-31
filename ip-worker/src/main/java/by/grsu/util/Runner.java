package by.grsu.util;

import by.grsu.domain.Ip;
import by.grsu.domain.Network;
import by.grsu.domain.NetworkMask;

public class Runner {

	public static void main(String[] args) {
		
		Integer num1 = 61;
		Integer num2 = 120;
		Integer num3 = 12;
		
		Ip networkIp = new Ip(new Integer[]{192, 168, 1, 0});
		
		NetworkMask mask = new NetworkMask(new Ip(new Integer[]{255,255,255,0}));
		Network network = new Network(networkIp, mask);
		
		if(round(num1) + round(num2) + round(num3) > mask.calcNumHosts()){
			System.out.println("Error!");
		}
		else {
			network.addSubnet(num1);
			network.addSubnet(num2);
			network.addSubnet(num3);
			System.out.println(network.displaySubNetworks());
		}
	}
	
	public static Integer round(Integer in){
		in+=3;
		Integer out = 2;
		while(out < in){
			out*=2;
		}
		return out;
	}

}
