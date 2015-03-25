package model;

import java.util.Scanner;
import util.Location;

/**
 * Board object
 * @author Joseph & Adam
 *
 */

public class Board implements Cloneable {
	private Piece[][] cells;
	private static Scanner sc = new Scanner(System.in);
	
	public Board(Piece[][] cells) {
		this.cells = cells;	
	}
	
	public Board(Board board) {
		this.cells = new Piece[8][8];
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(board.cells[i][j] instanceof Pawn) {
					this.cells[i][j] = new Pawn(board.cells[i][j]);
				} else if(board.cells[i][j] instanceof Rook) {
					this.cells[i][j] = new Rook(board.cells[i][j]);
				} else if(board.cells[i][j] instanceof Knight) {
					this.cells[i][j]= new Knight(board.cells[i][j]);
				} else if(board.cells[i][j] instanceof Bishop) {
					this.cells[i][j] = new Bishop(board.cells[i][j]);
				} else if(board.cells[i][j] instanceof Queen) {
					this.cells[i][j] = new Queen(board.cells[i][j]);
				} else if(board.cells[i][j] instanceof King) {
					this.cells[i][j] = new King(board.cells[i][j]);
				} else if(board.cells[i][j] instanceof Enpassant) {
					this.cells[i][j] = new Enpassant(board.cells[i][j]);
				} else {
					this.cells[i][j] = null;
				}
			}
		}
	}
	
	public void printBoard() {
		System.out.println();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(this.isEmpty(i, j)) {
					if(i % 2 == 0) {
						if(j % 2 != 0) {
							System.out.print("##");
						} else {
							System.out.print("  ");
						}
					}
					else {
						if(j % 2 == 0) {
							System.out.print("##");
						} else {
							System.out.print("  ");
						}
					}
					
					System.out.print(" ");
				} else {
					System.out.print(this.cells[i][j]);
					System.out.print(" ");
				}
					
			}
			
			System.out.println(8 - i);
		}
		
		for(int i = 0; i < 8; i++) {
			System.out.print(" " + Character.toString((char) ('a' + i))+ " ");
		}
		
		System.out.println("\n");
	}
	
	public boolean isEmpty(int i, int j) {
		
		if(this.cells[i][j] == null || this.cells[i][j] instanceof Enpassant)
			return true;
		else
			return false;
	}
	
	public void closeInput() {
		sc.close();
	}
	
	public Scanner input() {
		return sc;
	}
	
	public Piece getPiece(Location location) {
		return this.cells[location.getI()][location.getJ()];
	}

	public boolean loadPlayer(Player player) {
		String owner = player.toString();
		
		if(owner.equals("Black")) {
			this.cells[0][0] = new Rook(owner, new Location(0,0));
			this.cells[0][1] = new Knight(owner, new Location(0,1));
			this.cells[0][2] = new Bishop(owner, new Location(0,2));
			this.cells[0][3] = new Queen(owner, new Location(0,3));
			this.cells[0][4] = new King(owner, new Location(0,4));
			this.cells[0][5] = new Bishop(owner, new Location(0,5));
			this.cells[0][6] = new Knight(owner, new Location(0,6));
			this.cells[0][7] = new Rook(owner, new Location(0,7));
			
			for(int i = 0; i < 8; i++) {
				this.cells[1][i] = new Pawn(owner, new Location(1,i));
			}

			return true;

		} else if(owner.equals("White")) {
			
			for(int i = 0; i < 8; i++) {
				this.cells[6][i] = new Pawn(owner, new Location(6,i));
			}
				
			this.cells[7][0] = new Rook(owner, new Location(7,0));
			this.cells[7][1] = new Knight(owner, new Location(7,1));
			this.cells[7][2] = new Bishop(owner, new Location(7,2));
			this.cells[7][3] = new Queen(owner, new Location(7,3));
			this.cells[7][4] = new King(owner, new Location(7,4));
			this.cells[7][5] = new Bishop(owner, new Location(7,5));
			this.cells[7][6] = new Knight(owner, new Location(7,6));
			this.cells[7][7] = new Rook(owner, new Location(7,7));
				
			return true;
				
		} else {
			return false;
		}
	}
	
	public void updateBoard(Piece piece, Location location) {
		
		if(piece instanceof King) {
			
			if(Math.abs(piece.getPos().getJ() - location.getJ()) == 2) {
				//castling
				
				//right castle
				if(location.getJ() > piece.getPos().getJ()) {
					this.cells[location.getI()][location.getJ()] = piece;
					this.cells[piece.getPos().getI()][piece.getPos().getJ()+1] = this.cells[location.getI()][location.getJ()+1];
					this.cells[piece.getPos().getI()][piece.getPos().getJ()+1].updatePos(new Location(piece.getPos().getI(), piece.getPos().getJ() +1));
					this.cells[location.getI()][location.getJ()+1] = null;
					this.cells[piece.getPos().getI()][piece.getPos().getJ()+1].hasMoved();
				
				//left side
				} else {
					this.cells[location.getI()][location.getJ()] = piece;
					this.cells[piece.getPos().getI()][piece.getPos().getJ()-1] = this.cells[location.getI()][location.getJ()-2];
					this.cells[piece.getPos().getI()][piece.getPos().getJ()-1].updatePos(new Location(piece.getPos().getI(), piece.getPos().getJ() -1));
					this.cells[location.getI()][location.getJ()-2] = null;
					this.cells[piece.getPos().getI()][piece.getPos().getJ()-1].hasMoved();
				}
				
				this.cells[piece.getPos().getI()][piece.getPos().getJ()] = null;
				piece.hasMoved();
				piece.updatePos(location);
				return;
			}	
		}
		
		if(piece instanceof Pawn) {
			if(Math.abs(piece.getPos().getI() - location.getI()) == 2) {
				if(piece.getPos().getI() > location.getI()) {
					this.cells[piece.getPos().getI() - 1][piece.getPos().getJ()] = new Enpassant(piece.getOwner(), new Location(piece.getPos().getI() - 1, piece.getPos().getJ()), location);
				} else {
					this.cells[piece.getPos().getI() + 1][piece.getPos().getJ()] = new Enpassant(piece.getOwner(), new Location(piece.getPos().getI() + 1, piece.getPos().getJ()), location);
				}
			}
		}
		
		this.cells[location.getI()][location.getJ()] = piece;
		this.cells[piece.getPos().getI()][piece.getPos().getJ()] = null;
		piece.updatePos(location);
		
		if(piece.atStart()) {
			piece.hasMoved();
		}
		
		if(piece instanceof Pawn) {
			Pawn temp = (Pawn) piece;
			if(piece.getPos().getI() == temp.getUpgradeLoc()){
				updatePiece(piece);
			}
		}
	}
	
	public void nukeCell(Location location) {
		this.cells[location.getI()][location.getJ()] = null;
	}
	
	public boolean isEnpassant(int i, int j) {
		if(this.cells[i][j] instanceof Enpassant) {
			return true;
		} else {
			return false;
		}
	}
	
	public Piece[][] getBoard() {
		return this.cells;
	}
	
	public void updatePiece(Piece piece) {
		
		if(piece.isCopy) {
			return;
		}
		
		String input;
		char choice = ' ';
		
		System.out.print("Upgrade pawn: q = queen, b = bishop, n = knight, r = rook");
		
		while(choice != 'q' && choice != 'b' && choice != 'n' && choice != 'r') {
			input = sc.nextLine();
			input.toLowerCase();
		
			if(input.length() > 0) {
				choice = input.charAt(0);
			
			} else {
				System.out.println("Invalid input, please enter a valid choice: q = queen, b = bishop, n = knight, r = rook");
				continue;
			}
		}
		switch(choice) {
			case 'q':
				this.cells[piece.getPos().getI()][piece.getPos().getJ()] = new Queen(piece.getOwner(), piece.getPos());
				break;
			case 'b':
				this.cells[piece.getPos().getI()][piece.getPos().getJ()] = new Bishop(piece.getOwner(), piece.getPos());
				break;
			case 'n':
				this.cells[piece.getPos().getI()][piece.getPos().getJ()] = new Knight(piece.getOwner(), piece.getPos());
				break;
			case 'r':
				this.cells[piece.getPos().getI()][piece.getPos().getJ()] = new Rook(piece.getOwner(), piece.getPos());
				break;
			default:
				System.out.println("Illegal input, please enter a valid option");
			}
	}
	
	public Board getClone() {
		try {
			return (Board) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
	Board cloned = new Board(this.cells);
		return cloned;
	}
}