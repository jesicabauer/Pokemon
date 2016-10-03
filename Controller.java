package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Controller {
	
	public final static int NUM_OF_TYPES = 8; 
	
	public static int attackEfficiency[][] = new int[NUM_OF_TYPES][NUM_OF_TYPES]; 
	public static Scanner sc;
	
	public void typeMatrix() throws FileNotFoundException {
		sc = new Scanner(new File("type-matrix.txt"));
		for (int i = 0; i < NUM_OF_TYPES; i++) {
			for (int j = 0; j < NUM_OF_TYPES; j++) {
				attackEfficiency[i][j] = Integer.parseInt(sc.next()); 
			}
		} 
	}

	public static void main(String[] args)  {
		
		Controller myController = new Controller(); 

	}

}
