/*
* Copyright (c) 2017, Ritesh. All rights reserved.
*
*/
package com.mobiquityinc.solutions;

import java.util.List;

import com.mobiquityinc.packer.Packet;

/**
 * DESCRIPTION - This class is part of Mobiquity's Package Challenge assignment.
 * 
 * Interface to follow strategy pattern for solution strategy.
 *
 * @author - Ritesh
 * @version 1.0
 * @since <11-February-2018>
 */
public interface SolutionStrategy {

	public List<Packet> findOptimumPackets();
}
