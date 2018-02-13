/*
* Copyright (c) 2017, Ritesh. All rights reserved.
*
*/
package com.mobiquityinc.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.mobiquityinc.packer.Packet;

/**
 * DESCRIPTION - This utility class is part of Mobiquity's Package Challenge assignment.
 * 
 * It is responsible to generate the result/output in the given format.
 * For each set of things that you put into the package provide a list (packets’ index numbers are separated by comma).
 * 
 * @author - Ritesh
 * @version 1.0
 * @since <11-February-2018>
 */
public class OutputGenerator {

	// Restrict instantiation
	private OutputGenerator() {
		super();
	}

	/**
	 * Returns output/result for all input packages.
	 * 
	 * @param optimumResults
	 * @return output/result for all input packages.
	 */
	public static String getOutput(List<List<Packet>> optimumResults) {
		return optimumResults.stream()
				.map(OutputGenerator::outputString)
				.collect(Collectors.joining(System.lineSeparator()));
	}

	/**
	 * It provides a list of (packets’ index numbers are separated by comma).
	 * 
	 * @param packets
	 * @return actual output/result of each package.
	 */
	private static String outputString(List<Packet> packets) {
		if (PackerUtils.isEmpty(packets)) {
			return PackerConstants.EMPTY_ITEMS_MARK;
		}
		return packets.stream()
				.map(packet -> packet.getIndex()).sorted().map(Objects::toString)
				.collect(Collectors.joining(PackerConstants.COMMA));
	}
}
