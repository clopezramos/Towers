package aima.core.environment.towers;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.search.framework.GoalTest;

/**
 * @author Cristian Lopez Ramos Rivera
 */

public class TowersGoalTest implements GoalTest {

	public boolean isGoalState(Object state) {
		TowersBoard board = (TowersBoard) state;
		return isGoal(board);
	}
	
	private boolean isGoal(TowersBoard board) {
		Set<Integer> list = new LinkedHashSet<Integer>();
		list.clear();
		list.add(1); list.add(2); list.add(3); list.add(4);
		if (board.getState()[27] == 1 || board.getState()[27] == 2 || board.getState()[27] == 3 || board.getState()[27] == 4)
			list.remove(board.getState()[27]);
		if (board.getState()[28] == 1 || board.getState()[28] == 2 || board.getState()[28] == 3 || board.getState()[28] == 4)
			list.remove(board.getState()[28]);
		if (board.getState()[35] == 1 || board.getState()[35] == 2 || board.getState()[35] == 3 || board.getState()[35] == 4)
			list.remove(board.getState()[35]);
		if (board.getState()[36] == 1 || board.getState()[36] == 2 || board.getState()[36] == 3 || board.getState()[36] == 4)
			list.remove(board.getState()[36]);
		
		return list.isEmpty();
	}
}