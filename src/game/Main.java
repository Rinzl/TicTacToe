package game;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		char player, opp;
		int choice;
		System.out.println("Ban chon X hay O ? (an 1 de chon X, 0 de chon O )");
		Scanner scc = new Scanner(System.in);
		choice = scc.nextInt();

		if(choice ==1 ) {
			player = 'X';
			opp = 'O';
		}
		else {player = 'O';opp='X';};
		
		Board board = new Board(player,opp);
		
		while(board.isMoveLeft()) {	
			board.getUserMove();
			board.drawBoard();
			if(board.isGameWin(player)) {
				System.out.println("You Won ");
				break;
			}
			if(!board.isMoveLeft()) {
				System.out.println("Game tie !");
				break;
			}
			System.out.println("Com turn : ");
			board.AITurn();
			
			board.drawBoard();
			if(board.isGameWin(opp)) {
				System.out.println("Com won ");
				break;
			}
			System.out.println("\n");
			
		}
		scc.close();
	}
}
