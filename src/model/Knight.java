package model;

import util.Location;

/**
 * Knight object
 * @author Joseph & Adam
 *
 */

public class Knight extends Piece{

	public Knight(String owner, Location currentPos) {
		super(currentPos);
		
		if(owner.equals("Black"))
			this.asciiModel = "bN";
		else
			this.asciiModel = "wN";
		
		this.owner = owner;
		this.moveset = new int[][]{{2,1}, {-2,1}, {-2,-1}, {2,-1}, {1,2}, {-1,2}, {-1,-2}, {1,-2}};
		this.numMoves = 1;
	}
	
	public Knight(Piece piece) {
		super(piece);
	}
}
