/*
* Copyright (c) 2017, Ritesh. All rights reserved.
*
*/
package com.mobiquityinc.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Package;
import com.mobiquityinc.packer.Packet;
import com.mobiquityinc.util.PackerConstants;
import com.mobiquityinc.util.PackerUtils;

/**
 * DESCRIPTION - This utility class is part of Mobiquity's Package Challenge assignment.
 * 
 * It is responsible to validate and parse input file path. 
 * Get file content after reading and convert it into list of Packets.
 * 
 * This program should accept as its first argument a path to a filename. The input file contains several lines. 
 * Each line is one test case. Each line contains the weight that the package can take (before the colon) 
 * and the list of things you need to choose. 
 * Each thing is enclosed in parentheses where the 1st number is a thing's index number,
 * the 2nd is its weight and the 3rd is its cost.
 * 
 * @author - Ritesh
 * @version 1.0
 * @since <11-February-2018>
 */
public class InputParser {
	
	// Create an singleton object with lazy loading approach 
	private static InputParser inputParser;

	private InputParser() {
		// Make the constructor private so that this class cannot be instantiated.
	}

	// Get the available singleton object
	public static InputParser getInstance() {
		if (null == inputParser) {
		     // Instance creation with lazy loading approach
			inputParser = new InputParser();
		}
		return inputParser;
	}
	
	public List<Package> parse(String filePath) {
		String fileContent = read(filePath);
		return parsedPackages(fileContent);
	}
	
	private String read(String filePath) {
        validateFilePath(filePath);
        return getFileContent(filePath);
    }

	private void validateFilePath(String filePath) {
		if (PackerUtils.isBlank(filePath)) {
            throw new APIException("Input file-path is empty.");
        }
		
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new APIException("Input file-path doesn't exists.");
        }
	}

	private String getFileContent(String filePath) {
        try {
        	// As euro symbol is not supported by UTF_8 or ISO_8859_1.
        	//return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.ISO_8859_1);
			return new String(Files.readAllBytes(Paths.get(filePath)), PackerConstants.WINDOWS_1252_CHARSET);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
	
	private List<Package> parsedPackages(String fileContent) {
		if (PackerUtils.isBlank(fileContent)) {
            throw new APIException("It's an empty file.");
        }
		
		return PackerConstants.MULTILINE_PATTERN.splitAsStream(fileContent)
		.map(this::getPackage)
        .collect(Collectors.toList());
	}
	
	private Package getPackage(String parsedLine) {
        Matcher matcher = PackerConstants.WEIGHT_PATTERN.matcher(parsedLine);
        if (!matcher.find()) {
            throw new APIException("Parsed line is not is correct format. It should contains package weight.");
        }
        
        int weight = Integer.parseInt(matcher.group(1));
        validatePackageWeight(weight);
        List<Packet> packets = getPackets(weight, matcher.group(2));
        return new Package(weight, packets);
    }

	/**
	 * Constraints: Max weight of a package is <= 100
	 * 
	 * @param package's weight
	 */
	private void validatePackageWeight(int weight) {
        if (weight > PackerConstants.MAX_PACKAGE_WEIGHT) {
            throw new APIException("Package weight should be less or equals to " + PackerConstants.MAX_PACKAGE_WEIGHT + ".");
        }
    }
	
	private List<Packet> getPackets(int maxWeight, String parsedPacketString) {
		if (parsedPacketString.isEmpty()) {
            return Collections.emptyList();
        }
		
		List<Packet> packets = PackerConstants.PACKETS_PATTERN.splitAsStream(parsedPacketString)
                .map(this::getPacket)
                .sorted((i1, i2) -> Double.compare(i1.getWeight(), i2.getWeight()))
                .collect(Collectors.toList());
		validatePacketsSize(packets);
		return packets;	
	}
	
	private Packet getPacket(String parsedPacketString) {
		String[] parsedPacket = parsedPacket(parsedPacketString).split(PackerConstants.COMMA);
		if (PackerUtils.isEmpty(parsedPacket) && parsedPacket.length != PackerConstants.PARSED_PACKET_TOKENS_LENGTH) {
			throw new APIException("Packet string should be in format: (XX,XX.XX,$XX).");
		}
		
		int index = Integer.parseInt(parsedPacket[0]);
		double weight = Double.parseDouble(parsedPacket[1]);
		validatePacketWeight(weight);
		int price = Integer.parseInt(parsedPacket[2]);
		validatePacketPrice(price);
		return new Packet(index, weight, price);
	}

	private String parsedPacket(String pPacketString) {
		String parsedPacketCurrency = pPacketString
				.replaceAll(PackerConstants.PARENTHESES_REGEX, PackerConstants.EMPTY)
				.replaceAll(PackerConstants.CURRENCY_REGEX, PackerConstants.EMPTY)
				.replaceAll(PackerConstants.EMPTY_SPACE, PackerConstants.EMPTY);
		return parsedPacketCurrency;
	}
	
	/**
	 * Constraints: Max weight of a packet is <= 100.00
	 * 
	 * @param packet's weight
	 */
	private void validatePacketWeight(double weight) {
        if (weight > PackerConstants.MAX_PACKET_WEIGHT) {
            throw new APIException("Packet weight should be less or equals to " + PackerConstants.MAX_PACKET_WEIGHT + ".");
        }
    }
	
	/**
	 * Constraints: Max cost of a packet is <= 100 
	 * 
	 * @param packet's price
	 */
	private void validatePacketPrice(int price) {
        if (price > PackerConstants.MAX_PACKET_PRICE) {
            throw new APIException("Packet price should be less or equals to " + PackerConstants.MAX_PACKET_PRICE + ".");
        }
    }

	/**
	 * Constraints: There might be up to 15 items you need to choose from.
	 * 
	 * @param number of packets in a package
	 */
    private void validatePacketsSize(List<Packet> packets) {
        if (packets.size() > PackerConstants.MAX_PACKETS_SIZE) {
            throw new APIException("Number of packets inside a package should not be more than " + PackerConstants.MAX_PACKETS_SIZE + ".");
        }
    }
}
