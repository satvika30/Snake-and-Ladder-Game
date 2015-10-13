/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snakeandladder;

import java.util.LinkedList;

/**
 *
 * @author VAIO
 */
public class Players {
    
    private LinkedList<Player> players = new LinkedList<Player>();
    
    public boolean addPlayer(Player player) {
	if(player == null){
            return false;
	}
	
        players.add(player);
        
	return true;
    }
    
    public Player getNextPlayer() {
	Player current = players.remove();
        players.add(current);
		
	return current;
    }
}
