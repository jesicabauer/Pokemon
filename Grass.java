package game;

public class Grass extends PokemonSuper {

	protected int getMoveType() {
		return GRASS; 
	}
	
	protected int getType() {
		return GRASS; 
	}
	
	protected String getName() {
		return "BULBASAUR"; 
	}
	
	protected String getMove() {
		return "VINE WHIP";
	}
}
