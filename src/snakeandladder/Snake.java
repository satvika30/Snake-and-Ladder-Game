/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snakeandladder;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author VAIO
 */
public class Snake {
    private Map<Integer, Integer> snakes = new TreeMap<Integer, Integer>();
    
    public boolean addNewSnake(int head, int tail){
	if(isSnakeExist(head)){
	    return false;
	}
	if(!isValid(head, tail)){
	    System.out.println("Not A valid head and tail position");
	    return false;
	}
	snakes.put(head, tail);		
	return true;
    }
    
    public boolean isSnakeExist(int head){
	return snakes.containsKey(head);
    }
    
    public boolean isValid(int head, int tail){
	if(head <= 0 || tail<= 0){
	    return false;
	}
	if(head == tail){
	    return false;
	}
	if(tail >= head){
	    return false;
	}
	return true;
    }
    
    public boolean isSnakeHead(Integer position){
	return snakes.containsKey(position);
    }

    public Integer getSnakeTail(Integer position){
	return snakes.get(position);
    }
}
