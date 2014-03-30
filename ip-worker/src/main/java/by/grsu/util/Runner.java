package by.grsu.util;

import by.grsu.domain.Ip;
import by.grsu.domain.Network;
import by.grsu.domain.NetworkMask;

public class Runner {

	public static void main(String[] args) {
		
		Ip networkIp = new Ip( new Integer[]{192, 168, 1, 0});
		Ip maskIp = new Ip( new Integer[]{255, 255, 255, 0});
		
		NetworkMask mask = new NetworkMask(maskIp);
		Network network = new Network(networkIp, mask);
		
		network.addSubnet(12);
		network.addSubnet(12);
		network.addSubnet(12);
		
		System.out.println(network.displaySubNetworks());
	}

}
