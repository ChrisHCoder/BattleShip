package BattleShipGame.BattleShip;

import java.util.Scanner;

// This program simulates playing the game BattleShip against an AI player.
/*
 * public void pause(int milli) {
        try {
            Thread.sleep(milli);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    } 
 */
//Add the above code at a later point.


public class App {
	private static String[][] userBoard = new String[10][10];
	private static String[][] aIBoard = new String[10][10];
	private static String[][] aIBoard2 = new String[10][10];
	private static int userShipCount;
	private static int aIShipCount;
	private static Scanner input = new Scanner(System.in);
	private static String username;
    public static void main( String[] args ) {
    	greeting();
    	setUp();
    	game();
    }
    public static void greeting() {
    	System.out.println("Greetings Admiral, and welcome to the game BattleShip. Let's get you ready for battle!");
    	System.out.println("Enter your username here:");
    	username = input.nextLine();
    	System.out.println();
    	System.out.println("Alright! Now before we get started, let's deploy your fleet onto the field.");
    	System.out.println("After that we'll cover the rules of engagement.");
    	System.out.println();
    }
    public static void setUp() {
    	createBoards();
    	placeShips();
    	howToPlay();
    }
    public static void createBoards() {
    	for(int i = 0; i < 10; i++) {
    		for(int j = 0; j < 10; j++) {
    			userBoard[i][j] = "O";
    		}
    	}
    	for(int i = 0; i < 10; i++) {
    		for(int j = 0; j < 10; j++) {
    			aIBoard[i][j] = "O";
    		}
    	}
    	for(int i = 0; i < 10; i++) {
    		for(int j = 0; j < 10; j++) {
    			aIBoard2[i][j] = "?";
    		}
    	}
    }
    public static void placeShips() {
    	for(int i = 0; i < 5; i++) {
    		while(true) {
    			int xCoord = (int) ((Math.random() * ((10 - 1) + 1)) + 1);
    			int yCoord = (int) ((Math.random() * ((10 - 1) + 1)) + 1);
    			if(aIBoard[xCoord - 1][yCoord - 1].equals("O")) {
    				aIBoard[xCoord - 1][yCoord - 1] = "X";
    				break;
    			}
    		}
    	}
    	for(int i = 0; i < 5; i++) {
    		int row;
    		int col;
    		String inputCol;
    		while(true) {
    			while(true) {
    				System.out.println("Select a column to place your ship in (A-J, case insensitive):");
    				inputCol = input.nextLine();
    				inputCol = inputCol.toUpperCase();
    				if(inputCol.equals("A")) {
    					col = 1;
    					break;
    				}else if(inputCol.equals("B")) {
    					col = 2;
    					break;
    				}else if(inputCol.equals("C")) {
    					col = 3;
    					break;
    				}else if(inputCol.equals("D")) {
    					col = 4;
    					break;
    				}else if(inputCol.equals("E")) {
    					col = 5;
    					break;
    				}else if(inputCol.equals("F")) {
    					col = 6;
    					break;
    				}else if(inputCol.equals("G")) {
    					col = 7;
    					break;
    				}else if(inputCol.equals("H")) {
    					col = 8;
    					break;
    				}else if(inputCol.equals("I")) {
    					col = 9;
    					break;
    				}else if(inputCol.equals("J")) {
    					col = 10;
    					break;
    				}else {
    					System.out.println("Invalid entry. Please try again.");
    				}
    			}
    			while(true) {
    				System.out.println("Select a row to place your ship in (1 - 10, positive integers only):");
    				row = input.nextInt();
    				input.nextLine();
    				if(row >= 1 && row <= 10) {
    					break;
    				}else {
    					System.out.println("Invalid entry. Please try again.");
    				}
    			}
    			if(userBoard[row - 1][col - 1].equals("O")) {
    				userBoard[row - 1][col - 1] = "X";
    				break;
    			}else {
    				System.out.println("That position is already occupied by one of your previously placed vessels. Please select another position.");
    			}
    		}
    	}
    	System.out.println();
    	printUserBoard();
    	System.out.println();
    }
    public static void howToPlay() {
    	System.out.println();
    	System.out.println("Legend:");
    	System.out.println("\t'X' = Vessel.");
    	System.out.println("\t'O' = Ocean.");
    	System.out.println("\t'-' = Miss.");
    	System.out.println("\t'!' = Hit.");
    	System.out.println();
    	System.out.println("Rules of how to play BattleShip:");
    	System.out.println();
    	System.out.println("The goal of the game is to sink the enemy player's fleet. Each fleet is comprised of 5 vessels.");
    	System.out.println("Players take turns guessing the coordinates of the enemy player's vessels. If on your turn, you");
    	System.out.println("guess a set of coordinates, and those coordinates contain an enemy vessel, which is represented");
    	System.out.println("by an 'X', then the enemy vessel is destroyed. It's location is replaced by a '0' which repres-");
    	System.out.println("ents a hit. If on your turn you guess a set of coordinates that contains nothing, which is rep-");
    	System.out.println("resented by an 'O', then it's a miss, and your turn ends. Then, the location is replaced with a");
    	System.out.println("'-'. If your guess was a hit, then you get to make another guess. Otherwise, the turn ends, and");
    	System.out.println("your opponent gets to make a guess. As long as your guesses result in a hit, you can make mult-");
    	System.out.println("iple guesses in a row. Play continues until all the vessels in your fleet or your opponents are");
    	System.out.println("destroyed. Good luck Admiral " + username + "!");
    	System.out.println("");
    }
    public static void game() {
    	userShipCount = 5;
    	aIShipCount = 5;
    	while(userShipCount > 0 && aIShipCount > 0) {
    		userTurn();
    		aITurn();
    		if(aIShipCount == 0) {
    			System.out.println("You sunk the enemy's battleship! Well done Admiral " + username + "!");
    			break;
    		}
    		if(userShipCount == 0) {
    			System.out.println("Our fleet's been destroyed! Better luck next time, Admiral " + username + "!");
    		}
    	}
    }
    public static void userTurn() {
    	System.out.println("Start of " + username + "'s turn:");
    	System.out.println();
    	printAIBoard2();
    	System.out.println();
    	int col;
    	int row;
    	String inputCol;
    	while(true) {
    		while(true) {
    			System.out.println("Enter a column:");
    			inputCol = input.nextLine();
				inputCol = inputCol.toUpperCase();
				if(inputCol.equals("A")) {
					col = 1;
					break;
				}else if(inputCol.equals("B")) {
					col = 2;
					break;
				}else if(inputCol.equals("C")) {
					col = 3;
					break;
				}else if(inputCol.equals("D")) {
					col = 4;
					break;
				}else if(inputCol.equals("E")) {
					col = 5;
					break;
				}else if(inputCol.equals("F")) {
					col = 6;
					break;
				}else if(inputCol.equals("G")) {
					col = 7;
					break;
				}else if(inputCol.equals("H")) {
					col = 8;
					break;
				}else if(inputCol.equals("I")) {
					col = 9;
					break;
				}else if(inputCol.equals("J")) {
					col = 10;
					break;
				}else {
					System.out.println("Invalid entry. Please try again.");
				}
    		}
    		while(true) {
				System.out.println("Select a row:");
				row = input.nextInt();
				input.nextLine();
				if(row >= 1 && row <= 10) {
					break;
				}else {
					System.out.println("Invalid entry. Please try again.");
				}
			}
    		if(aIBoard2[row - 1][col - 1].equals("-") || aIBoard2[row - 1][col - 1].equals("!")) {
    			System.out.println("You've already fired there Admiral! Let's try another set of coordinates.");
    		}else {
    			if(aIBoard[row - 1][col - 1].equals("X")) {
    				System.out.println("It's a hit!");
    				aIBoard2[row - 1][col - 1] = "!";
    				System.out.println();
    				printAIBoard2();
    				System.out.println();
    				aIShipCount--;
    			}else {
    				System.out.println("That's a negative, Admiral.");
    				aIBoard2[row - 1][col - 1] = "-";
    				System.out.println();
    				printAIBoard2();
    				System.out.println();
    				break;
    			}
    		}
    	}
    }
    public static void aITurn() {
    	int xCoord;
    	int yCoord;
    	System.out.println();
    	System.out.println("Start of Computer's turn:");
    	System.out.println();
    	printUserBoard();
    	while(true) {
    		xCoord = (int) ((Math.random() * ((10 - 1) + 1)) + 1);
    		yCoord = (int) ((Math.random() * ((10 - 1) + 1)) + 1);
    		if(userBoard[xCoord - 1][yCoord - 1].equals("O")) {
    			userBoard[xCoord - 1][yCoord - 1] = "-";
    			System.out.println();
    			System.out.println("Computer is thinking...");
    			System.out.println("Calculating all possible targets...");
    			System.out.println("Filtering out all invalid targets...");
    			System.out.println("Selecting a random, valid, target...");
    			if(yCoord == 1) {
    				System.out.println("Target selected: (" + "A" + ", " + xCoord + ")...");
    			}else if(yCoord == 2) {
    				System.out.println("Target selected: (" + "B" + ", " + xCoord + ")...");
    			}else if(yCoord == 3) {
    				System.out.println("Target selected: (" + "C" + ", " + xCoord + ")...");
    			}else if(yCoord == 4) {
    				System.out.println("Target selected: (" + "D" + ", " + xCoord + ")...");
    			}else if(yCoord == 5) {
    				System.out.println("Target selected: (" + "E" + ", " + xCoord + ")...");
    			}else if(yCoord == 6) {
    				System.out.println("Target selected: (" + "F" + ", " + xCoord + ")...");
    			}else if(yCoord == 7) {
    				System.out.println("Target selected: (" + "G" + ", " + xCoord + ")...");
    			}else if(yCoord == 8) {
    				System.out.println("Target selected: (" + "H" + ", " + xCoord + ")...");
    			}else if(yCoord == 9) {
    				System.out.println("Target selected: (" + "I" + ", " + xCoord + ")...");
    			}else if(yCoord == 10) {
    				System.out.println("Target selected: (" + "J" + ", " + xCoord + ")...");
    			}else {
    				dN();
    			}	
    			System.out.println("Firing at selected position...");
    			System.out.println("Result: miss...");
    			System.out.println("Updating player's field...");
    			System.out.println("Updating finished...");
    			System.out.println();
    			printUserBoard();
    			System.out.println();
    			System.out.println("End of Computer's turn.");
    			System.out.println();
    			break;
    		}
    		if(userBoard[xCoord - 1][yCoord  - 1].equals("X")) {
    			userBoard[xCoord - 1][yCoord - 1] = "!";
    			userShipCount--;
    			System.out.println();
    			System.out.println("Computer is thinking...");
    			System.out.println("Calculating all possible targets...");
    			System.out.println("Filtering out all invalid targets...");
    			System.out.println("Selecting a random, valid, target...");
    			if(yCoord == 1) {
    				System.out.println("Target selected: (" + "A" + ", " + xCoord + ")...");
    			}else if(yCoord == 2) {
    				System.out.println("Target selected: (" + "B" + ", " + xCoord + ")...");
    			}else if(yCoord == 3) {
    				System.out.println("Target selected: (" + "C" + ", " + xCoord + ")...");
    			}else if(yCoord == 4) {
    				System.out.println("Target selected: (" + "D" + ", " + xCoord + ")...");
    			}else if(yCoord == 5) {
    				System.out.println("Target selected: (" + "E" + ", " + xCoord + ")...");
    			}else if(yCoord == 6) {
    				System.out.println("Target selected: (" + "F" + ", " + xCoord + ")...");
    			}else if(yCoord == 7) {
    				System.out.println("Target selected: (" + "G" + ", " + xCoord + ")...");
    			}else if(yCoord == 8) {
    				System.out.println("Target selected: (" + "H" + ", " + xCoord + ")...");
    			}else if(yCoord == 9) {
    				System.out.println("Target selected: (" + "I" + ", " + xCoord + ")...");
    			}else if(yCoord == 10) {
    				System.out.println("Target selected: (" + "J" + ", " + xCoord + ")...");
    			}else {
    				dN();
    			}	
    			System.out.println("Firing at selected position...");
    			System.out.println("Result: We've been hit Admiral!");
    			System.out.println("Updating player's field...");
    			System.out.println("Updating finished...");
    			System.out.println();
    			printUserBoard();
    		}
    	}
    }
    public static void printUserBoard() {
    	System.out.println("    A B C D E F G H I J");
    	System.out.println("");
    	for(int i = 0; i < 10; i++) {
    		if(i != 9) {
    			System.out.print((i + 1) + "   ");
    		}else {
    			System.out.print((i + 1) + "  ");
    		}
    		for(int j = 0; j < 10; j++) {
    			System.out.print(userBoard[i][j].concat(" "));
    		}
    		System.out.println();
    	}
    }
    public static void printAIBoard() {
    	System.out.println("    A B C D E F G H I J");
    	System.out.println("");
    	for(int i = 0; i < 10; i++) {
    		if(i != 9) {
    			System.out.print((i + 1) + "   ");
    		}else {
    			System.out.print((i + 1) + "  ");
    		}
    		for(int j = 0; j < 10; j++) {
    			System.out.print(aIBoard[i][j].concat(" "));
    		}
    		System.out.println();
    	}
    }
    public static void printAIBoard2() {
    	System.out.println("    A B C D E F G H I J");
    	System.out.println("");
    	for(int i = 0; i < 10; i++) {
    		if(i != 9) {
    			System.out.print((i + 1) + "   ");
    		}else {
    			System.out.print((i + 1) + "  ");
    		}
    		for(int j = 0; j < 10; j++) {
    			System.out.print(aIBoard2[i][j].concat(" "));
    		}
    		System.out.println();
    	}
    }
    public static void dN() {
    }
}
