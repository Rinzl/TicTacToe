package game;

import java.util.Scanner;

public class Board {
	private int[] node ={0,0,0,0,0,0,0,0,0,0};
	private char[][] board = {{'_','_','_'},{'_','_','_'},{'_','_','_'}};
	private char player;
	private char AICom;
	public Board(char player, char AICom) {
		// TODO Auto-generated constructor stub
		this.player = player;
		this.AICom = AICom;
	}
	public boolean isRightMove(int move) {
		if(this.node[move]==1) return false;
		return true;
	}
	public boolean isGameWin(char player) {
		if(
				(this.board[0][0]==this.board[0][1] && this.board[0][0]==this.board[0][2] && this.board[0][0] == player) ||
				(this.board[1][0]==this.board[1][1] && this.board[1][0]==this.board[1][2] && this.board[1][0] == player) ||
				(this.board[2][0]==this.board[2][1] && this.board[2][0]==this.board[2][2] && this.board[2][0] == player) || 
				(this.board[0][0]==this.board[1][1] && this.board[0][0]==this.board[2][2] && this.board[2][2] == player) ||
				(this.board[0][2]==this.board[1][1] && this.board[0][2]==this.board[2][0] && this.board[0][2] == player) ||
				(this.board[0][0]==this.board[1][0] && this.board[0][0]==this.board[2][0] && this.board[0][0] == player) ||
				(this.board[0][1]==this.board[1][1] && this.board[0][1]==this.board[2][1] && this.board[0][1] == player) ||
				(this.board[0][2]==this.board[1][2] && this.board[0][2]==this.board[2][2] && this.board[0][2] == player)
				) return true;
		return false;
	}
	private int evaluate(int depth) {
		if(this.isGameWin(this.player)) return depth-10;
		if(this.isGameWin(this.AICom)) return 10 - depth;
		return 0;
	}
	public boolean isMoveLeft() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(this.board[i][j]=='_') return true;
			}
		}
		return false;
	}
	public void drawBoard() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(this.board[i][j] + "  ");
			}
			System.out.println("\n");
		}
	}
	public void getUserMove() {
		System.out.println("Nhap vi tri ban muon di ");
		Scanner sc = new Scanner(System.in);
		int move = sc.nextInt();
		if(this.isRightMove(move)) {
			this.node[move] = 1;
			switch(move) {
			case 1 : {
				this.board[0][0] = this.player;
				break;
				}
			case 2 : {
				this.board[0][1] = this.player;
				break;
				}
			case 3 : {
				this.board[0][2] = this.player;
				break;
				}
			case 4 : {
				this.board[1][0] = this.player;
				break;
				}
			case 5 : {
				this.board[1][1] = this.player;
				break;
				}
			case 6 : {
				this.board[1][2] = this.player;
				break;
				}
			case 7 : {
				this.board[2][0] = this.player;
				break;
				}
			case 8 : {
				this.board[2][1] = this.player;
				break;
				}
			case 9 : {
				this.board[2][2] = this.player;
				break;
				}
			}
		}
		else {
			System.out.println("Vi tri nhap khong hop le ");
			getUserMove();
		}
		if(!this.isMoveLeft()) sc.close();
		
	}
	public int minimax(int depth, boolean isMax) {
		int score = this.evaluate(depth);
		if(this.isGameWin(player) || this.isGameWin(AICom)) return score;
		if(!this.isMoveLeft()) return 0;
		if(isMax) {
			int best = Integer.MIN_VALUE;
			for(int i =0;i<3;i++) {
				for(int j=0;j<3;j++) {
					if(this.board[i][j]=='_') {
						this.board[i][j]=AICom;
						//this.drawBoard();
						//int bestVal = minimax(depth+1,false);
						best = Integer.max(best, minimax(depth+1, false));
						this.board[i][j]='_';
					}
				}
			}
			return best;
		}
		else {
			int best = Integer.MAX_VALUE;
			for(int i =0;i<3;i++) {
				for(int j=0;j<3;j++) {
					if(this.board[i][j]=='_') {
						this.board[i][j]=player;
						//this.drawBoard();
						//int bestVal = minimax(depth+1,true);
						best = Integer.min(best, minimax(depth+1, true));
						this.board[i][j]='_';
					}
				}
			}
			return best;
		}
	}
	private Move findBestMove() {
		Move move = new Move();
		int best = Integer.MIN_VALUE;
		move.x=-1;
		move.y=-1;
		for(int i=0;i<3;i++) 
			for(int j=0;j<3;j++) {
				if(this.board[i][j]=='_') {
					this.board[i][j] = AICom;
					int moveVal = this.minimax(0, false);
					//System.out.println(moveVal+"?"+best);
					this.board[i][j]='_';
					if(best < moveVal) {
						move.x = i;
						move.y = j;
						best =moveVal;
					}
				}
			}
		return move;
	}
	public void AITurn() {
		//if(!this.isMoveLeft()) return; 
		Move move = this.findBestMove();
		//System.out.println(""+move.x +" "+move.y);
		if(move.x ==0 && move.y==0) this.node[1]=1;
		else if(move.x ==0 && move.y==1) this.node[2]=1;
		else if(move.x ==0 && move.y==2) this.node[3]=1;
		else if(move.x ==1 && move.y==0) this.node[4]=1;
		else if(move.x ==1 && move.y==1) this.node[5]=1;
		else if(move.x ==1 && move.y==2) this.node[6]=1;
		else if(move.x ==2 && move.y==0) this.node[7]=1;
		else if(move.x ==2 && move.y==1) this.node[8]=1;
		else if(move.x ==2 && move.y==2) this.node[9]=1;
		this.board[move.x][move.y]= AICom;
	}
	public class Move {
		public int x;
		public int y;
		
		public Move(){};
	}
}
