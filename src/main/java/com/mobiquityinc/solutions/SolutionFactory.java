package com.mobiquityinc.solutions;

import com.mobiquityinc.packer.Package;
import com.mobiquityinc.util.PackerConstants;

/**
 * DESCRIPTION - This factory class is part of Mobiquity's Package Challenge assignment.
 * 
 * This factory class is responsible to provide a solution strategy.
 * Get the solution strategy for this problem.
 * 
 * Also if in future we want to switch to any other solution approach; 
 * it will help us to maintain our application.
 *
 * @author - Ritesh
 * @version 1.0
 * @since <11-February-2018>
 */
public class SolutionFactory {

	// Create an singleton object with lazy loading approach
	private static SolutionFactory solutionFactoryInstance;

	private SolutionFactory() {
		// Make the constructor private so that this class cannot be instantiated.
	}

	// Get the only singleton object available
	public static SolutionFactory getInstance() {
		if (null == solutionFactoryInstance) {
			// Instance creation with lazy loading approach
			solutionFactoryInstance = new SolutionFactory();
		}
		return solutionFactoryInstance;
	}

	/**
	 * Returns the solution strategy for this problem.
	 * If we want to switch to any other solution approach; it will help us to maintain our application.
	 * 
	 * @param strategy
	 * @param pPackage
	 * @return SolutionStrategy - Knapsack-Recursion/Knapsack-Dynamic Programming/Others
	 */
	public SolutionStrategy getSolutionStrategy(String strategy, Package pPackage) {
		if (PackerConstants.SUBSET_COMBINATION_STRATEGY.equalsIgnoreCase(strategy)) {
			/*
			 * Note: It's a provision for future. If you want to switch to any other solutions. 
			 * 
			 * Return a solution strategy; 
			 * which will first find out all possible subsets/combinations 
			 * and get the optimized ones as a solution of this problem.  
			 */
		} else if (PackerConstants.KNAPSACK_RECURSIVE_STRATEGY.equalsIgnoreCase(strategy)) {
			// A recursive implementation of 0-1 Knapsack problem.
			return new KnapsackRecursiveSolution(pPackage);
		} else if (PackerConstants.KNAPSACK_DYNAMIC_PROGRAMMING_STRATEGY.equalsIgnoreCase(strategy)) {
			// A dynamic programming implementation of 0-1 Knapsack problem.
		}
		// Default solution strategy
		return new KnapsackRecursiveSolution(pPackage);
	}
}
