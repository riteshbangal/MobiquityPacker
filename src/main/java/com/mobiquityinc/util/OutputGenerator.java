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

	public static String getOutput(List<List<Packet>> optimumResults) {
		return optimumResults.stream()
				.map(OutputGenerator::outputString)
				.collect(Collectors.joining(System.lineSeparator()));
	}

	private static String outputString(List<Packet> packets) {
		if (PackerUtils.isEmpty(packets)) {
			return PackerConstants.EMPTY_ITEMS_MARK;
		}
		return packets.stream()
				.map(item -> item.index).map(Objects::toString)
				.collect(Collectors.joining(PackerConstants.COMMA));
	}
}
