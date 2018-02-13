/*
* Copyright (c) 2017, Ritesh. All rights reserved.
*
*/
package com.mobiquityinc.packer;

import com.mobiquityinc.util.PackerUtils;

/**
 * DESCRIPTION - This class is part of Mobiquity's Package Challenge assignment.
 * 
 * Each Packet inside the package has such parameters as index number, weight and cost. 
 * Packet is an immutable class. So that it can provide caching purpose and inherently thread-safe. 
 * 
 * @author - Ritesh
 * @version 1.0
 * @since <11-February-2018>
 */
public final class Packet {

	public final int index;
    public final double weight;
    public final int price;

    public Packet(int index, double weight, int price) {
        this.index = index;
        this.weight = weight;
        this.price = price;
    }
    
    @Override
	public String toString() {
		return "(" + index + ", " + weight + ", " + price + ") ";
	}
    
    @Override
    public boolean equals(Object pPacket) {
    	// Check if o is an instance of Complex or not "null instance of [type]" also returns false
    	if (!(pPacket instanceof Packet)) {
    		return false;
    	}

    	// type-cast pPacket to Packet so that we can compare data members 
    	Packet packet = (Packet) pPacket;

    	// Compare data members 
    	return (this.index == packet.getIndex())
    			&& PackerUtils.equals(this.weight, packet.getWeight())
    			&& (this.price == packet.getPrice());
    }
    
    public int getIndex() {
		return index;
	}

	public double getWeight() {
		return weight;
	}

	public int getPrice() {
		return price;
	}
}
