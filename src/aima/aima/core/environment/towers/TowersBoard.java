package aima.core.environment.towers;

import java.util.ArrayList;
import java.util.List;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.util.datastructure.XYLocation;

/**
 * @author Cristian Lopez Ramos Rivera
 */

public class TowersBoard {

	public static Action LEFT_1 = new DynamicAction("Tower_One_Left");
	public static Action LEFT_2 = new DynamicAction("Tower_Two_Left");
	public static Action LEFT_3 = new DynamicAction("Tower_Three_Left");
	public static Action LEFT_4 = new DynamicAction("Tower_Four_Left");

	public static Action RIGHT_1 = new DynamicAction("Tower_One_Right");
	public static Action RIGHT_2 = new DynamicAction("Tower_Two_Right");
	public static Action RIGHT_3 = new DynamicAction("Tower_Three_Right");
	public static Action RIGHT_4 = new DynamicAction("Tower_Four_Right");

	public static Action UP_1 = new DynamicAction("Tower_One_Up");
	public static Action UP_2 = new DynamicAction("Tower_Two_Up");
	public static Action UP_3 = new DynamicAction("Tower_Three_Up");
	public static Action UP_4 = new DynamicAction("Tower_Four_Up");

	public static Action DOWN_1 = new DynamicAction("Tower_One_Down");
	public static Action DOWN_2 = new DynamicAction("Tower_Two_Down");
	public static Action DOWN_3 = new DynamicAction("Tower_Three_Down");
	public static Action DOWN_4 = new DynamicAction("Tower_Four_Down");

	private int[] state;

	//
	// PUBLIC METHODS
	//

	public TowersBoard(int[] state) {
		this.state = new int[state.length];
		System.arraycopy(state, 0, this.state, 0, state.length);
	}

	public TowersBoard(TowersBoard copyBoard) {
		this(copyBoard.getState());
	}
	
	public int[] getTowersIndex() {
		int[] towers = new int[4];
		int counter = 0;
		for (int i = 0; i < 64; i++) {
			if (state[i] != 0) {
				towers[counter] = i;
				counter++;
			}
		}
		return towers;
	}

	public int[] getState() {
		return state;
	}
	
	public int getValueAt(int index) {
		return state[index];
	}

	public int getValueAt(XYLocation loc) {
		return getValueAt(loc.getXCoOrdinate(), loc.getYCoOrdinate());
	}

	public XYLocation getLocationOf(int val) {
		int absPos = getPositionOf(val);
		return new XYLocation(getXCoord(absPos), getYCoord(absPos));
	}
	
	public XYLocation getIndexLoc(int index) {
		return new XYLocation(getXCoord(index), getYCoord(index));
	}

	public void moveTowerRight(int tower) {
		int absPos = getPositionOf(tower);
		int x = getXCoord(absPos);
		int y = getYCoord(absPos);
		boolean found = false;
		int count = 1;
		int valueOnRight = -1;
		
		if (!(y == 7)) {
			while (!found && (y + count <= 7)) {
				valueOnRight = getValueAt(x, y + count);
				if (valueOnRight == 1 || valueOnRight == 2 || valueOnRight == 3 || valueOnRight == 4) {
					found = true;
				} else count++;
		    }
			if (found || (y + count - 1 == 7 && getValueAt(x, y + count - 1) == 0)) {
				moveValue(x, y, x, y + count - 1, tower);
			}
		}
	}

	public void moveTowerLeft(int tower) {
		int absPos = getPositionOf(tower);
		int x = getXCoord(absPos);
		int y = getYCoord(absPos);
		boolean found = false;
		int count = 1;
		int valueOnLeft = -1;
		
		if (!(y == 0)) {
			while (!found && (y - count >= 0)) {
				valueOnLeft = getValueAt(x, y - count);
				if (valueOnLeft == 1 || valueOnLeft == 2 || valueOnLeft == 3 || valueOnLeft == 4) {
					found = true;
				} else count++;
		    }
			if (found || (y - count + 1 == 0 && getValueAt(x, y - count + 1) == 0)) {
				moveValue(x, y, x, y - count + 1, tower);
			}
		}
	}
	
	public void moveTowerDown(int tower) {
		int absPos = getPositionOf(tower);
		int x = getXCoord(absPos);
		int y = getYCoord(absPos);
		boolean found = false;
		int count = 1;
		int valueDown = -1;
		
		if (!(x == 7)) {
			while (!found && (x + count <= 7)) {
				valueDown = getValueAt(x + count, y);
				if (valueDown == 1 || valueDown == 2 || valueDown == 3 || valueDown == 4) {
					found = true;
				} else count++;
		    }
			if (found || (x + count - 1 == 7 && getValueAt(x + count - 1, y) == 0)) {
				moveValue(x, y, x + count - 1, y, tower);
			}
		}
	}
	
	public void moveTowerUp(int tower) {
		int absPos = getPositionOf(tower);
		int x = getXCoord(absPos);
		int y = getYCoord(absPos);
		boolean found = false;
		int count = 1;
		int valueUp = -1;
		
		if (!(x == 0)) {
			while (!found && (x - count >= 0)) {
				valueUp = getValueAt(x - count, y);
				if (valueUp == 1 || valueUp == 2 || valueUp == 3 || valueUp == 4) {
					found = true;
				} else count++;
		    }
			if (found || (x - count + 1 == 0 && getValueAt(x - count + 1, y) == 0)) {
				moveValue(x, y, x - count + 1, y, tower);
			}
		}
	}

	public List<XYLocation> getPositions() {
		ArrayList<XYLocation> retVal = new ArrayList<XYLocation>();
		for (int i = 0; i < 64; i++) {
			int absPos = getPositionOf(i);
			XYLocation loc = new XYLocation(getXCoord(absPos),
					getYCoord(absPos));
			retVal.add(loc);

		}
		return retVal;
	}

	public void setBoard(List<XYLocation> locs) {
		int count = 0;
		for (int i = 0; i < locs.size(); i++) {
			XYLocation loc = locs.get(i);
			this.setValue(loc.getXCoOrdinate(), loc.getYCoOrdinate(), count);
			count = count + 1;
		}
	}

	public boolean canMoveTower(int tower, Action where) {
		int absPos = getPositionOf(tower);
		int x = getXCoord(absPos);
		int y = getYCoord(absPos);
		boolean found = false;
		int count = 1;
		int gap = 0;
		if (where.equals(LEFT_1) || where.equals(LEFT_2) || where.equals(LEFT_3) || where.equals(LEFT_4)) {
			int valueOnLeft = -1;
			if (!(y == 0)) {
				while (!found && (y - count >= 0)) {
					valueOnLeft = getValueAt(x, y - count);
					if (valueOnLeft == 1 || valueOnLeft == 2 || valueOnLeft == 3 || valueOnLeft == 4) {
						found = true;
					} else count++;
			    }
				if (found || (y - count + 1 == 0 && getValueAt(x, y - count + 1) == 0)) {
					gap = y - (y - count + 1);
				}
			}
		}
		else if (where.equals(RIGHT_1) || where.equals(RIGHT_2) || where.equals(RIGHT_3) || where.equals(RIGHT_4)) {
			int valueOnRight = -1;
			if (!(y == 7)) {
				while (!found && (y + count <= 7)) {
					valueOnRight = getValueAt(x, y + count);
					if (valueOnRight == 1 || valueOnRight == 2 || valueOnRight == 3 || valueOnRight == 4) {
						found = true;
					} else count++;
			    }
				if (found || (y + count - 1 == 7 && getValueAt(x, y + count - 1) == 0)) {
					gap = (y + count - 1) - y;
				}
			}
		}
		else if (where.equals(UP_1) || where.equals(UP_2) || where.equals(UP_3) || where.equals(UP_4)) {
			int valueUp = -1;		
			if (!(x == 0)) {
				while (!found && (x - count >= 0)) {
					valueUp = getValueAt(x - count, y);
					if (valueUp == 1 || valueUp == 2 || valueUp == 3 || valueUp == 4) {
						found = true;
					} else count++;
			    }
				if (found || (x - count + 1 == 0 && getValueAt(x - count + 1, y) == 0)) {
					gap = x - (x - count + 1);
				}
			}		
		}
		else if (where.equals(DOWN_1) || where.equals(DOWN_2) || where.equals(DOWN_3) || where.equals(DOWN_4)) {
			int valueDown = -1;			
			if (!(x == 7)) {
				while (!found && (x + count <= 7)) {
					valueDown = getValueAt(x + count, y);
					if (valueDown == 1 || valueDown == 2 || valueDown == 3 || valueDown == 4) {
						found = true;
					} else count++;
			    }
				if (found || (x + count - 1 == 7 && getValueAt(x + count - 1, y) == 0)) {
					gap = (x + count - 1) - x;
				}
			}	
		}
		return (gap >= 1);
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if ((o == null) || (this.getClass() != o.getClass())) {
			return false;
		}
		TowersBoard aBoard = (TowersBoard) o;

		for (int i = 0; i < 64; i++) {
			if (this.getPositionOf(i) != aBoard.getPositionOf(i)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = 1;
		for (int i = 1; i < 5; i++) {
			int position = this.getPositionOf(i);
			result = result * 7 + position;
		}
		return result;
	}

	@Override
	public String toString() {
		String retVal = "";
		for (int i = 0; i < 64; i++) {
			retVal += " ";
			retVal += state[i];
			if (i % 8 == 0)
				retVal += "\n";
		}

		return retVal;
	}

	//
	// PRIVATE METHODS
	//

	/**
	 * Note: The graphic representation maps x values on row numbers (x-axis in
	 * vertical direction).
	 */
	private int getXCoord(int absPos) {
		return absPos / 8;
	}

	/**
	 * Note: The graphic representation maps y values on column numbers (y-axis
	 * in horizontal direction).
	 */
	private int getYCoord(int absPos) {
		return absPos % 8;
	}

	private int getAbsPosition(int x, int y) {
		return x * 8 + y;
	}

	private int getValueAt(int x, int y) {
		// refactor this use either case or a div/mod soln
		return state[getAbsPosition(x, y)];
	}

	private int getPositionOf(int val) {
		int retVal = -1;
		for (int i = 0; i < 64; i++) {
			if (state[i] == val) {
				retVal = i;
			}
		}
		return retVal;
	}

	private void setValue(int x, int y, int val) {
		int absPos = getAbsPosition(x, y);
		state[absPos] = val;

	}

	private void moveValue(int initX, int initY, int destX, int destY, int value) {
		setValue(initX, initY, 0);
		setValue(destX, destY, value);
	}
}