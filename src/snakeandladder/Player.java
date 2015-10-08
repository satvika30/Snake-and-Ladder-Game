/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snakeandladder;

/**
 *
 * @author VAIO
 */
public class Player {
    private static int idCounter =1 ;
    private Integer currentPosition;
    private String name;
    private Integer id;
    
    public Player(String name) {
	super();
	this.name = name;
	this.currentPosition = 0;
	this.id = Player.idCounter++;
    }
    
    public Integer getCurrentPosition() {
	return currentPosition;
    }

    public void setCurrentPosition(Integer currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getName() {
	return name;
    }
}
