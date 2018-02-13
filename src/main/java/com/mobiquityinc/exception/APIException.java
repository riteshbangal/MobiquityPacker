/*
* Copyright (c) 2017, Ritesh. All rights reserved.
*
*/
package com.mobiquityinc.exception;

import com.mobiquityinc.util.PackerConstants;

/**
 * DESCRIPTION - This class is part of Mobiquity's Package Challenge assignment.
 * 
 * This class is wrapper over Runtime Exception. 
 * Packer will throw a runtime exception, if incorrect parameters are being passed. 
 * 
 * @author - Ritesh
 * @version 1.0
 * @since <11-February-2018>
 */
public class APIException extends RuntimeException {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private final String mErrorCode;

    /**
     * Instantiates a new Generic exception.
     * 
     * @param errorMessage the error message
     */
    public APIException(final String errorMessage) {
        super(errorMessage);
        this.mErrorCode = PackerConstants.EMPTY;
    }

    /**
     * Instantiates a new Generic exception.
     * 
     * @param errorCode the error code
     * @param errorMessage the error message
     */
    public APIException(final String errorCode, final String errorMessage) {
        super(errorMessage);
        this.mErrorCode = errorCode;
    }

    /**
     * Instantiates a new Generic exception.
     * 
     * @param errorMessage the error message
     * @param cause the cause
     */
    public APIException(final String errorMessage, final Throwable cause) {
        super(errorMessage, cause);
        this.mErrorCode = PackerConstants.EMPTY;
    }

    /**
     * Instantiates a new Generic exception.
     * 
     * @param errorCode the error code
     * @param errorMessage the error message
     * @param cause the cause
     */
    public APIException(final String errorCode, final String errorMessage, final Throwable cause) {
        super(errorMessage, cause);
        this.mErrorCode = errorCode;
    }

    /**
     * Constructs a new exception with the specified cause
     *
     * @param cause the cause
     */

    public APIException(Throwable cause) {
        super(cause);
        this.mErrorCode = PackerConstants.EMPTY;
    }

    /**
     * Gets the error code.
     * 
     * @return the error code
     */
    public String getErrorCode() {
        return mErrorCode;
    }
}
