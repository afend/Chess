package model;

import util.Location;

/**
 * Enpassant object
 * @author Joseph & Adam
 *
 */

public class Enpassant extends Piece {
	
	public Enpassant(String owner, Location location, Location ghost) {
		super(location);
		
		this.owner = owner;
		this.ghost = ghost;
		this.turnsAlive = 0;
		this.asciiModel = "";
	}
	
	public Enpassant(Piece piece) {
		super(piece);
	}
}