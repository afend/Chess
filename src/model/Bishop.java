package model;

import util.Location;

/**
 * Bishop Object
 * @author Joseph & Adam
 *
 */

public class Bishop extends Piece {

	public Bishop(String owner, Location currentPos) {
		super(currentPos);
		
		if(owner.equals("Black"))
			this.asciiModel = "bB";
		else
			this.asciiModel = "wB";
		
		this.owner = owner;
		this.moveset = new int[][]{{1,1}, {-1,1}, {-1,-1}, {1, -1}};
		this.numMoves = 7;
	}
	
	public Bishop(Piece piece) {
		super(piece);
	}
}