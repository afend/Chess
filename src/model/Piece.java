package model;

import java.util.ArrayList;

import util.Location;

/**
 * Abstract piece object
 * @author Joseph & Adam
 *
 */

public abstract class Piece implements Cloneable {
	protected Boolean alive = true;
	protected String asciiModel;
	protected String owner;
	protected ArrayList<Location> validMoves;
	protected int[][] moveset;
	protected int[][] sMoveset;
	protected int numMoves;
	protected Location currentPos;
	protected boolean atStart = true;
	protected Location ghost;
	protected int turnsAlive;
	protected int upgradeLoc;
	protected static int pieceCount;
	protected int pieceID;
	protected boolean isCopy = false;
	
	public Piece(Location currentPos) {
		this.alive = true;
		this.validMoves = new ArrayList<Location>();
		this.currentPos = currentPos;
		this.pieceID = pieceCount;
		pieceCount++;
	}
	
	public Piece(Piece piece) {
		this.isCopy = true;
		this.pieceID = piece.pieceID;
		
		if(piece.alive)
			this.alive = true;
		else
			this.alive = false;
		
		this.asciiModel = piece.asciiModel;
		this.owner = piece.owner;
		this.numMoves = piece.numMoves;
		this.currentPos = new Location(piece.currentPos.getI(), piece.currentPos.getJ());
		this.atStart = piece.atStart;
		
		if(piece.getGhost() != null)
			this.ghost = new Location(piece.getGhost().getI(), piece.getGhost().getJ());
		
		this.turnsAlive = piece.turnsAlive;
		this.upgradeLoc = piece.upgradeLoc;
		this.validMoves = new ArrayList<Location>();
		
		for(int i = 0; i < piece.validMoves.size(); i++) {
			this.validMoves.add(new Location(piece.validMoves.get(i).getI(), piece.validMoves.get(i).getJ()));
		}
		
		if(!(piece instanceof Enpassant)) {
			this.moveset = new int[piece.moveset.length][2];
		
			for(int i = 0; i < piece.moveset.length; i++) {
				for(int j = 0; j < 2; j++) {
					this.moveset[i][j] = piece.moveset[i][j];
				}
			}
		
			if(piece.asciiModel.charAt(1) == 'p' || piece.asciiModel.charAt(1) == 'K') {
				this.sMoveset = new int[piece.sMoveset.length][2];
			
				for(int i = 0; i < piece.sMoveset.length; i++) {
					for(int j = 0; j < 2; j++) {
						this.sMoveset[i][j] = piece.sMoveset[i][j];
					}
				}
			}
		}
	}
	
	public void kill() {
		this.alive = false;
	}
	
	public String toString() {
		return this.asciiModel;
	}
	
	public int[][] getMoveSet() {
		return this.moveset;
	}
	
	public int getMoves() {
		return this.numMoves;
	}
	
	public void addValidMove(Location location) {
		this.validMoves.add(location);
	}
	
	public String getOwner() {
		return this.owner;
	}
	
	public ArrayList<Location> getValidMoves() {
		return this.validMoves;
	}
	
	public void resetValidMoves() {
		this.validMoves.clear();
	}
	
	public Location getPos() {
		return this.currentPos;
	}
	
	public void updatePos(Location pos) {
		
		this.currentPos = pos;
	}
	
	public boolean atStart() {
		return this.atStart;
	}
	
	public void hasMoved() {
		this.atStart = false;
	}
	
	public int[][] getSMoveSet() {
		return this.sMoveset;
	}
	
	public Location getGhost() {
		return this.ghost;
	}
	
	public int getTurns() {
		return this.turnsAlive;
	}
	
	public void incrementTurn() {
		this.turnsAlive++;
	}
	
	public void delMove(Location moveToDel) {
		this.validMoves.remove(moveToDel);
	}
	public int getID() {
		return this.pieceID;
	}
	
	public int getUpgradeLoc() {
		return this.upgradeLoc;
	}
	
	public boolean isCopy() {
		return this.isCopy;
	}
	
	public Piece getClone(){
		try {
			return (Piece) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Piece cloned = (Piece)super.clone();
		return cloned;
	}	
}