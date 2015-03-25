package model;

import util.Location;

/**
 * Queen object
 * @author Joseph & Adam
 *
 */

public class Queen extends Piece {

	public Queen(String owner, Location currentPos) {
		super(currentPos);
		
		if(owner.equals("Black"))
			this.asciiModel = "bQ";
		else
			this.asciiModel = "wQ";
		
		this.owner = owner;
		this.moveset = new int[][] {{1,1}, {-1,1}, {-1,-1}, {1, -1}, {1,0}, {0,1}, {-1,0}, {0,-1}};
		this.numMoves = 7;
	}
	
	public Queen(Piece piece) {
		super(piece);
	}
}