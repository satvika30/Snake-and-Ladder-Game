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
public class Players {
    Node head = null;
    Node tail = null;
    
    public boolean addPlayer(Player player) {
	if(player == null){
            return false;
	}
	
	Node n = new Node(player);
	if (head == null && tail == null) {
            head = n;
            tail = n;
	} else {
            while (tail.getNext() != null) {
		tail = tail.getNext();
            }
            tail.setNext(n);
            tail = tail.getNext();
	}
	return true;
    }
    
    public Player getNextPlayer() {
	Player current = null;
		
	tail = tail.getNext();
	if (tail == null) {
		tail = head;
	}
	current  = tail.getPlayer();
	return current;
    }
}

class Node {

    private Player player;
    private Node next;

    public Node(Player player) {
    	super();
	this.player = player;
	this.next = null;
    }
    
    public void setNext(Node next) {
	this.next = next;
    }

    public Node getNext() {
	return next;
    }

    public Player getPlayer() {
	return player;
    }

    public void setPlayer(Player player) {
	this.player = player;
    }

}
