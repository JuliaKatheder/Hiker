/**
 * 	Hiker Application. For educational purposes only.
 * 
 *  Copyright (C) 2016  Julia Katheder
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ibm.katheder.map.error;

/**
 * <p>Error which is thrown when trying to place the hiker or destination to an invalid field.
 * For example if the selected field is "unreachable" indicated by Integer.MAX_VALUE.</p>
 * 
 * <p>The current implementation is actually not needed. Just a simple showcase...</p>
 * 
 * @author Julia Katheder
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

