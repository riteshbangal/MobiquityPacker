/*
* Copyright (c) 2017, Ritesh. All rights reserved.
*
*/
package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.List;

import com.mobiquityinc.parser.InputParser;
import com.mobiquityinc.solutions.SolutionFactory;
import com.mobiquityinc.solutions.SolutionStrategy;
import com.mobiquityinc.util.OutputGenerator;
import com.mobiquityinc.util.PackerConstants;
import com.mobiquityinc.util.PackerUtils;

/**
 * DESCRIPTION - This class is part of Mobiquity's Package Challenge assignment.
 * 
 * It accept as its first argument a path to a filename. The input file contains several lines.
 * It  contains a static method named pack, 
 * which accepts the absolute path to a test file as a String and return the solution as a String.
 * 
 * @author - Ritesh
 * @version 1.0
 * @since <11-February-2018>
 */
public class Packer {
	
	// Set default 'knapsack' as default solution strategy
	public static String solutionStrategy = PackerConstants.KNAPSACK_STRATEGY;
	
    public static void main(String[] args) {
        if (PackerUtils.isEmpty(args)) {
            System.err.println("Wrong parameters. It accept as its first argument a path to a filename.");
            System.exit(1);
        }
        System.out.println(pack(args[0]));
    }
    
    public static String pack(String filePath) {
        return processPacking(filePath);
    }

	private static String processPacking(String filePath) {
		List<List<Packet>> optimumResults = new ArrayList<>();
		List<Package> packages = getInputParser().parse(filePath);
		for (Package lPackage : packages) {
			optimumResults.add(getSolutionStrategy(lPackage).findOptimumPackets());
		}
		return OutputGenerator.getOutput(optimumResults);
	}

	/**
	 * Get the solution strategy for this problem.
	 * 
	 * Also if in future we want to switch to any other solution approach; 
	 * it will help us to maintain our application.
	 * 
	 * @param pPackage
	 * @return SolutionStrategy - Knapsack/Others
	 */
	private static SolutionStrategy getSolutionStrategy(Package pPackage) {
		return SolutionFactory.getInstance().getSolutionStrategy(solutionStrategy, pPackage);
	}

	private static InputParser getInputParser() {
		// Get singleton instance of InputParser.
		return InputParser.getInstance();
	}
}
