package game;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class InstantiateArrays {
	
	public final static int NUM_OF_TYPES = 9; 
	public final static int NUM_OF_POKEMON = 8; 
	public final static int ASSIGNED_POKEMON = 3; 
	public final static int NORMAL = 0; 
	public final static int FIRE = 1; 
	public final static int WATER = 2; 
	public final static int ELECTRIC = 3; 
	public final static int GRASS = 4; 
	public final static int FIGHTING = 5; 
	public final static int PSYCHIC = 6; 
	public final static int STEEL = 7; 
	public final static int FAIRY = 8;
	public final static int MAX_HEALTH = 50; 
	
	PokemonSuper game = new PokemonSuper();
	Fire fire = new Fire(); 
	Water water = new Water();
	Electric electric = new Electric(); 
	Grass grass = new Grass(); 
	Fighting fighting = new Fighting(); 
	Psychic psychic = new Psychic(); 
	Steel steel = new Steel(); 
	Fairy fairy = new Fairy();
	
	public static double attackEfficiency[][] = new double[NUM_OF_TYPES][NUM_OF_TYPES]; 
	public static int pokemon[] = new int[NUM_OF_POKEMON];
	public static double pokemonHealth[] = new double[NUM_OF_POKEMON];
	public static JLabel getSprite[] = new JLabel[NUM_OF_TYPES];
	public static ImageIcon getButtonSprite[] = new ImageIcon[NUM_OF_TYPES];
	public static String getMove[] = new String[NUM_OF_TYPES];
	public static String getNormMove[] = new String[NUM_OF_TYPES];
	public static String getTypeName[] = new String[NUM_OF_TYPES];
	public static Color getColor[] = new Color[NUM_OF_TYPES];
	public static int getDamage[] = new int[NUM_OF_TYPES];
    
	public final static Color GREEN = Color.decode("#A6D785");
	public final static Color WHITE = Color.decode("#FFFFFF");
	public final static Color BLACK = Color.decode("#555555");
	protected final static int matteTop = 2; 
	protected final static int matteBottom = 15; 
	
	protected JLabel getPlayerSprite(int i) {
    	getSprite[NORMAL] = null; 
    	getSprite[FIRE] = fire.getPlayerSprite(); 
    	getSprite[WATER] = water.getPlayerSprite(); 
    	getSprite[ELECTRIC] = electric.getPlayerSprite(); 
    	getSprite[GRASS] = grass.getPlayerSprite(); 
    	getSprite[FIGHTING] = fighting.getPlayerSprite(); 
    	getSprite[PSYCHIC] = psychic.getPlayerSprite(); 
    	getSprite[STEEL] = steel.getPlayerSprite(); 
    	getSprite[FAIRY] = fairy.getPlayerSprite(); 
    	
    	return getSprite[i]; 
    }
    
	protected JLabel getPCSprite(int i) {
    	getSprite[NORMAL] = null; 
    	getSprite[FIRE] = fire.getPCSprite(); 
    	getSprite[WATER] = water.getPCSprite(); 
    	getSprite[ELECTRIC] = electric.getPCSprite(); 
    	getSprite[GRASS] = grass.getPCSprite(); 
    	getSprite[FIGHTING] = fighting.getPCSprite(); 
    	getSprite[PSYCHIC] = psychic.getPCSprite(); 
    	getSprite[STEEL] = steel.getPCSprite(); 
    	getSprite[FAIRY] = fairy.getPCSprite(); 
    	
    	return getSprite[i]; 
    }
    
	protected ImageIcon getButtonSprite(int i){
    	getButtonSprite[FIRE] = fire.getButtonSprite(); 
    	getButtonSprite[WATER] = water.getButtonSprite(); 
    	getButtonSprite[ELECTRIC] = electric.getButtonSprite(); 
    	getButtonSprite[GRASS] = grass.getButtonSprite(); 
    	getButtonSprite[FIGHTING] = fighting.getButtonSprite(); 
    	getButtonSprite[PSYCHIC] = psychic.getButtonSprite(); 
    	getButtonSprite[STEEL] = steel.getButtonSprite(); 
    	getButtonSprite[FAIRY] = fairy.getButtonSprite();
    	
    	return getButtonSprite[i];
    }

	protected String getNormMove(int i) {
    	getNormMove[NORMAL] = null; 
    	getNormMove[FIRE] = fire.getNormMove(); 
    	getNormMove[WATER] = water.getNormMove(); 
    	getNormMove[ELECTRIC] = electric.getNormMove(); 
    	getNormMove[GRASS] = grass.getNormMove(); 
    	getNormMove[FIGHTING] = fighting.getNormMove(); 
    	getNormMove[PSYCHIC] = psychic.getNormMove(); 
    	getNormMove[STEEL] = steel.getNormMove(); 
    	getNormMove[FAIRY] = fairy.getNormMove(); 
    	
    	return getNormMove[i]; 
    }
    
	protected String getMove(int i) {
    	getMove[NORMAL] = null; 
    	getMove[FIRE] = fire.getMove(); 
    	getMove[WATER] = water.getMove(); 
    	getMove[ELECTRIC] = electric.getMove(); 
    	getMove[GRASS] = grass.getMove(); 
    	getMove[FIGHTING] = fighting.getMove(); 
    	getMove[PSYCHIC] = psychic.getMove(); 
    	getMove[STEEL] = steel.getMove(); 
    	getMove[FAIRY] = fairy.getMove(); 
    	
    	return getMove[i]; 
    }
    
	protected String getTypeName(int i) {
    	getTypeName[NORMAL] = null; 
    	getTypeName[FIRE] = fire.getTypeName(); 
    	getTypeName[WATER] = water.getTypeName(); 
    	getTypeName[ELECTRIC] = electric.getTypeName(); 
    	getTypeName[GRASS] = grass.getTypeName(); 
    	getTypeName[FIGHTING] = fighting.getTypeName(); 
    	getTypeName[PSYCHIC] = psychic.getTypeName(); 
    	getTypeName[STEEL] = steel.getTypeName(); 
    	getTypeName[FAIRY] = fairy.getTypeName(); 
    	
    	return getTypeName[i]; 
    }
    
	protected Color getColor(int i) {
    	getColor[NORMAL] = null; 
    	getColor[FIRE] = fire.getColor(); 
    	getColor[WATER] = water.getColor(); 
    	getColor[ELECTRIC] = electric.getColor(); 
    	getColor[GRASS] = grass.getColor(); 
    	getColor[FIGHTING] = fighting.getColor(); 
    	getColor[PSYCHIC] = psychic.getColor(); 
    	getColor[STEEL] = steel.getColor(); 
    	getColor[FAIRY] = fairy.getColor(); 
    	
    	return getColor[i]; 
    }

	

}
