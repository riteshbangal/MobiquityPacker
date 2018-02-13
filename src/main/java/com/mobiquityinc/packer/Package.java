/*
* Copyright (c) 2017, Ritesh. All rights reserved.
*
*/
package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION - This class is part of Mobiquity's Package Challenge assignment.
 * 
 * Each Package is constructed with different set of Packets. 
 * Each Packet inside the package has such parameters as index number, weight and cost. 
 * Each package has a weight limit, which is also known as its capacity.
 * 
 * @author - Ritesh
 * @version 1.0
 * @since <11-February-2018>
 */
public class Package {

    public int capacity;
    public List<Packet> packets;

    public Package(int capacity, List<Packet> packets) {
        this.capacity = capacity;
        this.packets = new ArrayList<>(packets);
    }
    
    @Override
	public String toString() {
		return "Package: {capacity=" + capacity + ", packets=" + packets + "}";
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Packet> getPackets() {
		return packets;
	}

	public void setPackets(List<Packet> packets) {
		this.packets = packets;
	}
}
