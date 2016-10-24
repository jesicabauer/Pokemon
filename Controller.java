package game;

import java.awt.Color;
import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;

class Controller {
	
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
	
	public int jframeWidth = 400; 
	public int jframeHeight = 500; 
	public int imageWidth = 80; 
	public int imageHeight = 80; 
	public int plPokePos = 200; 
	
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
	public static JLabel getSprite[] = new JLabel[NUM_OF_TYPES];
	
    public JFrame gameJFrame;
    public Container gameContentPane;
    
	public static Scanner sc;
	Color background = Color.decode("#A6D785");
    
    public Controller() {
    	
    	gameJFrame = new JFrame("Pokemon Go!");
    	gameJFrame.setSize(jframeWidth,jframeHeight);
    	gameJFrame.setLocationRelativeTo(null);
    	gameContentPane = gameJFrame.getContentPane(); 
    	gameContentPane.setLayout(null);
    	gameContentPane.setBackground(background);
    	
    	
		shufflePokemon(); 
		playerPokemon(); 
		CPUPokemon();
		JLabel player1 = getPlayerSprite(pokemon[0]); 
		JLabel player2 = getPlayerSprite(pokemon[1]);
		JLabel player3 = getPlayerSprite(pokemon[2]);
		JLabel pc1 = getPCSprite(pokemon[3]); 
		JLabel pc2 = getPCSprite(pokemon[4]);
		JLabel pc3 = getPCSprite(pokemon[5]);
		player1.setBounds(0,plPokePos,imageWidth,imageHeight);
		player2.setBounds(imageWidth,plPokePos,imageWidth,imageHeight);
		player3.setBounds(imageWidth*2,plPokePos,imageWidth,imageHeight);
		pc1.setBounds(jframeWidth-imageWidth*3,0,imageWidth,imageHeight);
		pc2.setBounds(jframeWidth-imageWidth*2,0,imageWidth,imageHeight);
		pc3.setBounds(jframeWidth-imageWidth,0,imageWidth,imageHeight);
		gameContentPane.add(player1);
		gameContentPane.add(player2);
		gameContentPane.add(player3);
		gameContentPane.add(pc1);
		gameContentPane.add(pc2);
		gameContentPane.add(pc3);
		gameJFrame.setVisible(true); 
		
    }
    
    private JLabel getPlayerSprite(int i) {
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
    
    private JLabel getPCSprite(int i) {
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
    
    
	
	public static void typeMatrix() throws FileNotFoundException {
		sc = new Scanner(new File("type-matrix.txt"));
		for (int i = 0; i < NUM_OF_TYPES; i++) {
			for (int j = 0; j < NUM_OF_TYPES; j++) {
				attackEfficiency[i][j] = Double.parseDouble(sc.next()); 
//				System.out.print(attackEfficiency[i][j] + " ");
			}
//			System.out.println();
		} 
	}
	


	private void shufflePokemon() {
		List<Integer> temp = new ArrayList<>(); 
		for (int i = 0; i < NUM_OF_POKEMON; i++) {
			temp.add(i+1); 
//			System.out.print(temp.get(i));
		}
//		System.out.println();
		Collections.shuffle(temp);
		
		for (int i = 0; i < NUM_OF_POKEMON; i++) {
			pokemon[i] = temp.get(i);
//			System.out.print(pokemon[i]);
		}
//		System.out.println();
	}
	
	private void playerPokemon() {
		System.out.println("Your Pokemon are: ");
		for (int i = 0; i < ASSIGNED_POKEMON; i++ ) {
			System.out.println(whichPokemon(pokemon[i]));
		}
	}
	
	private void CPUPokemon() {
		System.out.println("The CPU's Pokemon are: "); 
		for (int i = ASSIGNED_POKEMON; i < ASSIGNED_POKEMON*2; i++ ) {
			System.out.println(whichPokemon(pokemon[i]));
		}
	}
	
	public String whichPokemon(int i){
		switch(i){
			case 0: return "Normal";
			case 1: return fire.getName();
			case 2: return water.getName();
			case 3: return electric.getName();
			case 4: return grass.getName();
			case 5: return fighting.getName();
			case 6: return psychic.getName();
			case 7: return steel.getName();
			case 8: return fairy.getName();
		}
		return "Oh no";
	}
	
	
	public static void main(String[] args) throws FileNotFoundException  {
		
		Controller myController = new Controller(); 

	}
	
}
