/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snakeandladder;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author VAIO
 */
public class Ladder {
    private Map<Integer, Integer> ladders = new HashMap<Integer, Integer>();

    public boolean addNewLadder(int bottom, int top) {
	if (isLadderExist(bottom)) {
            return false;
	}
	if (!isValid(bottom, top)) {
            System.out.println("Not A valid head and tail position");
            return false;
	}
	ladders.put(bottom, top);

	return true;
    }
    
    public boolean isLadderExist(int bottom){
	return ladders.containsKey(bottom);
    }
    
    public boolean isValid(int bottom, int top) {
	if (bottom <= 0 || top <= 0) {
            return false;
	}
	if(bottom == top){
            return false;
	}
	if (bottom > top) {
            return false;
	}
	return true;
    }
    
    public boolean isLadderBottom(Integer position){
	return ladders.containsKey(position);
    }

    public Integer getLadderTop(Integer position){
	return ladders.get(position);
    }
}
