package game;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	public static String getButtonSprite[] = new String[NUM_OF_TYPES];
	public static String getMove[] = new String[NUM_OF_TYPES];
	
    public JFrame gameJFrame;
    public Container gameContentPane;
    public JPanel gameJPanel; 
    public JPanel playerPanel;
    public JPanel CPUPanel; 
    
	public static Scanner sc;
	
	public int jframeWidth = 400; 
	public int jframeHeight = 500; 
	public int imageWidth = 80; 
	public int imageHeight = 80; 
	public int plPokePos = 200; 
	public final static Color GREEN = Color.decode("#A6D785");
	public final static Color WHITE = Color.decode("#FFFFFF");
    
    public Controller() {
    	
    	shufflePokemon(); 
		playerPokemon(); 
		CPUPokemon();
    	
    	gameJFrame = new JFrame("Pokemon Go!");
    	gameJFrame.setSize(jframeWidth,jframeHeight);
    	gameJFrame.setLocationRelativeTo(null);
    	
    	gameContentPane = gameJFrame.getContentPane(); 
    	gameContentPane.setLayout(null);
    	gameContentPane.setBackground(GREEN);
    	
    	gameJPanel = new JPanel(); 
    	gameJPanel.setBackground(WHITE);
    	gameJPanel.setBounds(0,plPokePos+imageWidth,385,180);
    	gameJPanel.setLayout(new GridLayout(2,0));
    	
    	gameJPanel.add(new JLabel("Fight"));
    	gameJPanel.add(new JLabel(""));
    	gameJPanel.add(new JLabel(""));
    	gameJPanel.add(new JLabel("Switch"));
    	gameJPanel.add(new JLabel(""));
    	gameJPanel.add(new JButton("NORMAL MOVE"));
    	gameJPanel.add(new JButton(getActiveTypeMove(pokemon[0])));
    	gameJPanel.add(new JButton(new ImageIcon(getButtonSprite(pokemon[0]))));
    	gameJPanel.add(new JButton(new ImageIcon(getButtonSprite(pokemon[1]))));
    	gameJPanel.add(new JButton(new ImageIcon(getButtonSprite(pokemon[2]))));

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
//		gameContentPane.add(player2);
//		gameContentPane.add(player3);
//		gameContentPane.add(pc1);
//		gameContentPane.add(pc2);
		gameContentPane.add(pc3);
		gameContentPane.add(gameJPanel);

		playerPanel = CreateIDBoxes(pokemon[0],50);
		CPUPanel = CreateIDBoxes(pokemon[3],50);		
		playerPanel.setBounds(imageWidth, plPokePos, jframeWidth-imageWidth, imageHeight);
		CPUPanel.setBounds(0,0, jframeWidth - imageWidth, imageHeight);
		
		gameContentPane.add(playerPanel);
		gameContentPane.add(CPUPanel);
		
		
		gameJPanel.setVisible(true);
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
    
    private String getButtonSprite(int i){
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

    
    private String getActiveTypeMove(int i) {
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
	

	private JPanel CreateIDBoxes(int Pokemon, int PokemonDamage){
		JPanel myPanel = new JPanel();
		JLabel PokemonName = new JLabel(whichPokemon(Pokemon));
		JLabel Health = new JLabel(PokemonDamage+"/50");
		
		myPanel.add(PokemonName);
		myPanel.add(Health);
		return myPanel;
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
	
	
	public String DamageMessage(){
		String Damage = "We need to do this";
		return Damage;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException  {
		
		Controller myController = new Controller(); 

	}
	
}
