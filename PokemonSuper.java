package game;

import java.awt.Color;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

class PokemonSuper {
	
	public final static int NUM_NORM_MOVES = 4; 
	public final static int NORMAL = 0; 
	public final static int FIRE = 1; 
	public final static int WATER = 2; 
	public final static int ELECTRIC = 3; 
	public final static int GRASS = 4; 
	public final static int FIGHTING = 5; 
	public final static int PSYCHIC = 6; 
	public final static int STEEL = 7; 
	public final static int FAIRY = 8;
	public final static int MIN_HIT = 10; 

	// get type, get name, get moves
	
	protected String getNormMove() {
	
		return ""; 
	}
	
	protected JLabel getPlayerSprite() {

		return null; 
	}
	
	protected JLabel getPCSprite() {

		return null; 
	}
	
	protected ImageIcon getButtonSprite(){
		
		return null; 
	}
	
	protected String setMove() {
		
		return "SUPER CLASS"; 
	}
	
	protected int getMoveType() {
		return 0;  
	}
	
	protected int getType() {
		return 0; 
	}
	
	protected String getName() {
		return ""; 
	}
	
	protected String getMove() {
		return "";
	}
	
	protected Color getColor() {
		return Color.decode("#ffffff"); 
	}
	
	protected int damage() {
		return MIN_HIT; 
	}
	protected int getDamage(){
		Random myRand = new Random();
		int damage = myRand.nextInt(10)+MIN_HIT;
		return damage;
	}
}
