/*
* Copyright (c) 2017, Ritesh. All rights reserved.
*
*/
package com.mobiquityinc.util;

import java.util.Collection;

/**
 * DESCRIPTION - This utility class is part of Mobiquity's Package Challenge assignment.
 * 
 * It provides required utilities to solve the Packer problem.
 * 
 * @author - Ritesh
 * @version 1.0
 * @since <11-February-2018>
 */
public class PackerUtils {
	
	// Restrict instantiation
	private PackerUtils() {
		super();
	}
	
	public static boolean isEmpty(Object pObject) {
		return (pObject == null);
	}

	public static boolean isNotEmpty(Object pObject) {
		return (pObject != null);
	}

	public static boolean isEmpty(String[] pStr) {
		return (pStr == null || pStr.length == 0);
	}

	public static boolean isEmpty(String pStr) {
		return (pStr == null || pStr.length() == 0);
	}

	public static boolean isNotEmpty(String pStr) {
		return (pStr != null && pStr.length() > 0);
	}
	
	public static boolean isBlank(String pStr) {
		return ((pStr == null) || (pStr.length() == 0) || (pStr.trim().length() == 0));
	}

	public static boolean isNotBlank(String pStr) {
		return (pStr != null && pStr.length() > 0 && pStr.trim().length() > 0);
	}
	
	public static <E> boolean isEmpty(Collection<E> pCollectionObj) {
		return (pCollectionObj == null || pCollectionObj.isEmpty());
	}

	public static <E> boolean isNotEmpty(Collection<E> pCollectionObj) {
		return (pCollectionObj != null && pCollectionObj.isEmpty());
	}
	
	public static boolean equals(double d1, double d2) {
        return Double.compare(d1, d2) == 0;
    }
}
