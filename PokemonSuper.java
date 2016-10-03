package game;

import java.util.Random;

class PokemonSuper {
	
	public final static int NUM_NORM_MOVES = 4; 

	// get type, get name, get moves
	
	protected void normMoves() {
		
		Random rand = new Random(); 
		int n = rand.nextInt(4);
		System.out.println(n); 
		 
	}
	
}
