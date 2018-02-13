/*
* Copyright (c) 2017, Ritesh. All rights reserved.
*
*/
package com.mobiquityinc.util;

import java.util.regex.Pattern;

/**
 * DESCRIPTION - This utility class is part of Mobiquity's Package Challenge assignment.
 * 
 * It provides required constants to solve the Packer problem.
 * 
 * @author - Ritesh
 * @version 1.0
 * @since <11-February-2018>
 */
public class PackerConstants {
	
	// Restrict instantiation
	private PackerConstants() {
		super();
	}
	
	public static final String EMPTY = "";
	public static final String EMPTY_SPACE = " ";
	public static final String EMPTY_ITEMS_MARK = "-";
	public static final String COMMA = ",";
	
	public static final String KNAPSACK_RECURSIVE_STRATEGY = "knapsackRecursive";
	public static final String KNAPSACK_DYNAMIC_PROGRAMMING_STRATEGY = "knapsackDynamicProgramming";
	public static final String SUBSET_COMBINATION_STRATEGY = "subsetCombinations";
	
	public static final String PARENTHESES_REGEX = "\\(|\\)";
	public static final String CURRENCY_REGEX = "\\$|\\â‚¬|\\€";
	
	public static final String WINDOWS_1252_CHARSET = "windows-1252";
	
	public static final Pattern MULTILINE_PATTERN 	= Pattern.compile("\\r?\\n");
	public static final Pattern WEIGHT_PATTERN 		= Pattern.compile("(\\d+) : (.*)");
    public static final Pattern PACKETS_PATTERN 	= Pattern.compile(" ");

	public static final double MAX_PACKET_WEIGHT = 100.0;
	
	public static final int PARSED_PACKET_TOKENS_LENGTH 	= 3;
	
	public static final int MAX_PACKET_PRICE 	= 100;
	public static final int MAX_PACKETS_SIZE 	= 15;
	public static final int MAX_PACKAGE_WEIGHT 	= 100;
}
