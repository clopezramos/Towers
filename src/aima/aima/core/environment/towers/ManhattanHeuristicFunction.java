package aima.core.environment.towers;

import aima.core.search.framework.HeuristicFunction;
import aima.core.util.datastructure.XYLocation;

/**
 * @author Cristian Lopez Ramos Rivera
 */

public class ManhattanHeuristicFunction implements HeuristicFunction {

	public double h(Object state) {
		TowersBoard board = (TowersBoard) state;
		int retVal = 0;
		for (int i = 1; i < 5; i++) {
			retVal += evaluateManhattanDistanceOf(board.getLocationOf(i));
		}
		return retVal;

	}

	private int evaluateManhattanDistanceOf(XYLocation loc) {
		int xpos = loc.getXCoOrdinate();
		int ypos = loc.getYCoOrdinate();

		return getValue(xpos, ypos);
	}
	
	private int getValue(int xpos, int ypos) {
		int value = 0;
		
		value = Math.min(Math.abs(xpos - 3) + Math.abs(ypos - 3), Math.abs(xpos - 3) + Math.abs(ypos - 4));
		value = Math.min(value, Math.abs(xpos - 4) + Math.abs(ypos - 3));
		value = Math.min(value, Math.abs(xpos - 4) + Math.abs(ypos - 4));
		
		return value;
	}
}