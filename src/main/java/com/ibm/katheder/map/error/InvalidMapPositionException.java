/**
 * 
 */
package com.ibm.katheder.map.error;

/**
 * <p>Error which is thrown when trying to place the hiker or destination to an invalid field.
 * For example if the selected field is "unreachable" indicated by Integer.MAX_VALUE.</p>
 * 
 * <p>The current implementation is actually not needed. Just a simple showcase...</p>
 * 
 * @author Sterbling
 * @version 1.0
 *
 */
public class InvalidMapPositionException extends Exception {

	private static final long serialVersionUID = 8274801820840281792L;

	
	public InvalidMapPositionException(String errorMessage) {
		super(errorMessage);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	public String getTitle() {
		return "Warning: You are about to do sth nasty!";
	}
}

