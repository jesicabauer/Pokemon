package game;

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
import javax.swing.JPanel;

class Controller {
	
	public final static int NUM_OF_TYPES = 9; 
	public final static int NUM_OF_POKEMON = 8; 
	public final static int ASSIGNED_POKEMON = 3; 
	
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
	
    public JFrame gameJFrame;
    public Container gameContentPane;
    public JPanel playerPanel;
    public JPanel CPUPanel; 
    
	public static Scanner sc;
    
    public Controller() {
    	
    	gameJFrame = new JFrame("Pokemon Go!");
    	gameJFrame.setSize(400,500);
    	gameJFrame.setLocationRelativeTo(null);
    	
    	gameJFrame.setVisible(true); 
		shufflePokemon(); 
		playerPokemon(); 
		CPUPokemon();
		CreateIDBoxes(playerPanel,1,50);
		
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
	
	private void CreateIDBoxes(JPanel myPanel, int Pokemon, int PokemonDamage){
		JLabel PokemonName = new JLabel(whichPokemon(Pokemon));
		JLabel Health = new JLabel(PokemonDamage+"/50");
		
		myPanel.add(PokemonName);
		myPanel.add(Health);
		
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
