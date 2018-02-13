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
	
	/*
	 * Get strategy for this solution; 
	 * It will be used in the solution factory to get Solution approach (e.g. Knapsack or Subset combinations).
	 */
	private static String strategy;
	
    public static void main(String[] args) {
        if (PackerUtils.isEmpty(args)) {
            System.err.println("Wrong parameters. It accept as its first argument a path to a filename.");
            System.exit(1);
        }
        System.out.println(pack(args[0]));
    }
    
    /**
     * This static method accepts the absolute path to a test file as a String. 
     * It does return the solution as a String.
     * 
     * It will throw APIException if incorrect parameters are being passed.
     * 
     * @param filePath
     * @return output/result
     */
    public static String pack(String filePath) {
        return new Packer().processPacking(filePath);
    }

	private String processPacking(String filePath) {
		List<List<Packet>> optimumResults = new ArrayList<>();
		getInputParser().parse(filePath).stream()
			.forEach(lPackage -> optimumResults.add(getSolutionStrategy(lPackage).findOptimumPackets()));
		
		return OutputGenerator.getOutput(optimumResults);
	}

	/**
	 * Get the solution strategy for this problem.
	 * 
	 * Also if in future we want to switch to any other solution approach; 
	 * It will help us to maintain our application.
	 * 
	 * @param pPackage
	 * @return SolutionStrategy - Knapsack/Others
	 */
	private SolutionStrategy getSolutionStrategy(Package pPackage) {
		// Get singleton instance of SolutionFactory. Then get the SolutionStrategy.
		return SolutionFactory.getInstance().getSolutionStrategy(getStrategy(), pPackage);
	}

	private InputParser getInputParser() {
		// Get singleton instance of InputParser.
		return InputParser.getInstance();
	}

	/**
	 * Get strategy for this solution; 
	 * It will be used in the solution factory to get Solution approach (e.g. Knapsack or Subset combinations).
	 * 
	 * @return solution strategy
	 */
	private String getStrategy() {
		if (PackerUtils.isBlank(strategy)) {
			// Set default 'knapsack' as default solution strategy
			strategy = PackerConstants.KNAPSACK_RECURSIVE_STRATEGY;
		}
		return strategy;
	}

	public static void setStrategy(String pStrategy) {
		strategy = pStrategy;
	}
}
