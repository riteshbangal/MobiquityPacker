/*
 * Copyright (c) 2017, Ritesh. All rights reserved.
 *
 */
package com.mobiquityinc.packer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mobiquityinc.exception.APIException;

/**
 * DESCRIPTION - This class is responsible to test Packer's functionalities.
 * 
 * @author - Ritesh Bangal
 * @version 1.0
 * @since <12-February-2018>
 */
@RunWith(JUnit4.class)
public class PackerTestCase {

	private String filePath;

	@Before
	public void setUp() throws Exception {
		filePath = getFilePath("inputs.txt");
	}

	@Test
	public void testPackHappyFlow() {
		String results = Packer.pack(filePath);
		
		String newLine = System.getProperty("line.separator");
		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("4")
			.append(newLine).append("-")
			.append(newLine).append("2,7")
			.append(newLine).append("8,9");
		
		assertEquals(expectedOutput.toString(), results);
	}
	
	@Test
	public void testPackUnhappyFlow() {
		String results = Packer.pack(filePath);
		assertNotEquals("0", results);
	}
	
	@Test(expected = APIException.class)
	public void testMissingFile() {
		filePath = getFilePath("some_imaginary_file_path.txt");
		Packer.pack(filePath);
	}
	
	@Test(expected = APIException.class)
	public void testInvalidContents() {
		filePath = getFilePath("invalid.txt");
		Packer.pack(filePath);
	}
	
	/**
     * Get file from resources folder.
     *
     * @return AbsolutePath of data-feed
     */
    protected String getFilePath(String pFile) {
        // Get file from resources folder
        File file = new File("src/test/resources/"  + pFile);
        return file.getAbsolutePath();
    }
}
