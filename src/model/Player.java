package model;

import java.util.ArrayList;

/**
 * Player object
 * @author Joseph & Adam
 *
 */

public class Player {
	private static int playerCount;
	private String player;
	private ArrayList<Piece> captured;
	
	
	public Player() {
		
		if (playerCount == 0)
			this.player = "White";
		else if (playerCount == 1)
			this.player = "Black";
		else
			throw new IllegalArgumentException("Chess only supports two players.");
		
		this.captured = new ArrayList<Piece>();
		playerCount++;
	}
	
	public String toString() {
		return this.player;
	}
	
	public void capturePiece(Piece piece) {
		captured.add(piece);
	}
}
