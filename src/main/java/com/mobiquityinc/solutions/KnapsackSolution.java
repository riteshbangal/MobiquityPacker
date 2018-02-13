/*
* Copyright (c) 2017, Ritesh. All rights reserved.
*
*/
package com.mobiquityinc.solutions;

import java.util.ArrayList;
import java.util.List;

import com.mobiquityinc.packer.Package;
import com.mobiquityinc.packer.Packet;
import com.mobiquityinc.util.PackerUtils;

/**
 * DESCRIPTION - This class is part of Mobiquity's Package Challenge assignment.
 * 
 * It provide knapsack solution strategy. A recursive implementation of 0-1 Knapsack problem.
 *
 * @author - Ritesh
 * @version 1.0
 * @since <11-February-2018>
 */
public class KnapsackSolution implements SolutionStrategy {

	private int capacity;
	private List<Packet> packets;
	private final double[][] knapsackTable;
	
	public KnapsackSolution(Package pPackage) {
		capacity = pPackage.getCapacity();
		packets = pPackage.getPackets();
		knapsackTable = createTable();
	}

	private double[][] createTable() {
        double[][] knapsackMatrix = new double[capacity + 1][packets.size()];
        for (int j = 0; j < capacity + 1; j++) {
            for (int i = 0; i < packets.size(); i++) {
                knapsackMatrix[j][i] = -1;
            }
        }
        return knapsackMatrix;
    }

	@Override
	public List<Packet> findOptimumPackets() {
		populateKnapsackTable(capacity, packets.size() - 1);
        return getFilteredPackets();
	}
	
	/**
	 * Implemented Knapsack Algorithm. Traverse back for selecting the cells. 
	 * 
	 * @return list of optimum packets
	 */
	private List<Packet> getFilteredPackets() {
        List<Packet> filteredPackets = new ArrayList<>();
        int i = packets.size() - 1;
        int j = capacity;
        while (i >= 0) {
        	Packet packet = packets.get(i);
            double without = i == 0 ? 0 : knapsackTable[j][i - 1];
            if (!PackerUtils.equals(knapsackTable[j][i], without)) {
            	filteredPackets.add(packet);
                j -= (int) packet.weight;
            }
            i--;
        }
        return filteredPackets;
    }

	/**
	 * Implemented Knapsack Algorithm. Construct knapsack matrix.
	 * It's a recursive implementation of 0-1 Knapsack problem.
	 * 
	 * @param capacity
	 * @param packet's size -1
	 * @return
	 */
	private double populateKnapsackTable(int j, int i) {
        if (i < 0 || j < 0) {
            return 0;
        }
        
        Packet packet = packets.get(i);
        double with, without, cell = knapsackTable[j][i];
        if (cell == -1) {
            if (packet.weight > j) {
                with = -1;
            } else {
                with = packet.price + populateKnapsackTable(j - (int) packet.weight, i - 1);
            }
            without = populateKnapsackTable(j, i - 1);
            cell = Math.max(with, without);
            knapsackTable[j][i] = cell;
        }
        return cell;
    }
}
