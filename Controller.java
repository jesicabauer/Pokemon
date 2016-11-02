package game;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class Controller implements ActionListener {
	
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
	
    public JFrame gameJFrame;
    public Container gameContentPane;
    public JPanel fightJPanel; 
    public JPanel switchJPanel;
    public JPanel playerPanel;
    public JPanel CPUPanel; 
    public JPanel userMessagePanel; 
    public JButton normButton; 
    public JButton typeButton; 
    public JButton switch1Button; 
    public JButton switch2Button; 
    public JButton switch3Button; 
    public JLabel player1;
    public JLabel pc1;
    public JLabel userMessageLabel; 
    
	public static Scanner sc;
	
	public int jframeWidth = 400; 
	public int jframeHeight = 500; 
	public int imageWidth = 80; 
	public int imageHeight = 80; 
	public int plPokePos = 200; 
	public final static Color GREEN = Color.decode("#A6D785");
	public final static Color WHITE = Color.decode("#FFFFFF");
	public final static Color BLACK = Color.decode("#555555");
	protected final static int matteTop = 2; 
	protected final static int matteBottom = 15; 
    
	protected int PlayerActive = 0;
	protected int CPUActive = 3;
	protected boolean isNormalMove; 
	
    public Controller()  {
    	
    	shufflePokemon(); 
		playerPokemon(); 
		CPUPokemon();
    	
    	gameJFrame = new JFrame("Pokemon Go!");
    	gameJFrame.setSize(jframeWidth,jframeHeight);
    	gameJFrame.setLocationRelativeTo(null);
    	
    	gameContentPane = gameJFrame.getContentPane(); 
    	gameContentPane.setLayout(null);
    	gameContentPane.setBackground(GREEN);
    	
    	fightJPanel = new JPanel(); 
    	fightJPanel.setBackground(WHITE);

    	fightJPanel.setBounds(0,plPokePos+imageWidth,385,90);
    	fightJPanel.setLayout(new GridLayout(1,2));
    	
    	normButton = new JButton("<html><center>" + getNormMove(pokemon[PlayerActive]) + "<br><font size=1>(NORMAL)</font></center></html>");
    	typeButton = new JButton("<html><center>" + getMove(pokemon[PlayerActive]) + "<br><font size=1>(" + getTypeName(pokemon[PlayerActive]) + ")</font></center></html>");
    	normButton.addActionListener(this);
    	typeButton.addActionListener(this);
    	normButton.setBackground(WHITE);
    	typeButton.setBackground(WHITE);
    	fightJPanel.add(normButton);
    	fightJPanel.add(typeButton); 
  	
    	switchJPanel = new JPanel(); 
    	switchJPanel.setBackground(WHITE);
    	switchJPanel.setBounds(0,plPokePos+imageWidth+90,385,90);
    	switchJPanel.setLayout(new GridLayout(1,3));

    	switch1Button = new JButton(getButtonSprite(pokemon[0]));
    	switch2Button = new JButton(getButtonSprite(pokemon[1]));
    	switch3Button = new JButton(getButtonSprite(pokemon[2]));
    	switch1Button.addActionListener(this);
    	switch2Button.addActionListener(this);
    	switch3Button.addActionListener(this);
    	switch1Button.setBackground(getColor(pokemon[0]));
    	switch2Button.setBackground(getColor(pokemon[1]));
    	switch3Button.setBackground(getColor(pokemon[2]));

    	switchJPanel.add(switch1Button);
    	switchJPanel.add(switch2Button);
    	switchJPanel.add(switch3Button); 
    	

		player1 = getPlayerSprite(pokemon[PlayerActive]); 
		pc1 = getPCSprite(pokemon[CPUActive]); 
		player1.setBounds(0,plPokePos,imageWidth,imageHeight);
		pc1.setBounds(jframeWidth-imageWidth,0,imageWidth,imageHeight);
		
		gameContentPane.add(player1);
		gameContentPane.add(pc1);
		gameContentPane.add(fightJPanel);
		gameContentPane.add(switchJPanel);

		playerPanel = CreateIDBoxes(pokemon[PlayerActive],pokemonHealth[PlayerActive]);
		CPUPanel = CreateIDBoxes(pokemon[CPUActive],pokemonHealth[PlayerActive]);		
		playerPanel.setBounds(125, 210, jframeWidth-imageWidth*2, 50);
		playerPanel.setBorder(BorderFactory.createMatteBorder(matteTop,matteBottom,matteTop,matteBottom,getColor(pokemon[PlayerActive])));
		playerPanel.setBackground(WHITE);
		CPUPanel.setBounds(20,20, jframeWidth - imageWidth*2, 50);
		CPUPanel.setBorder(BorderFactory.createMatteBorder(
				matteTop, matteBottom, matteTop, matteBottom, getColor(pokemon[CPUActive])));
		CPUPanel.setBackground(WHITE);
		
		userMessagePanel = new JPanel(); 
		userMessagePanel.setBounds(50,90,285,100);
		userMessagePanel.setBorder(BorderFactory.createMatteBorder(matteTop, matteBottom, matteTop, matteBottom, BLACK));
		userMessagePanel.setBackground(WHITE);
		
		userMessageLabel = new JLabel(); 
		userMessagePanel.add(userMessageLabel, SwingConstants.CENTER);
	
		gameContentPane.add(playerPanel);
		gameContentPane.add(CPUPanel);
		gameContentPane.add(userMessagePanel);
		
		userMessagePanel.setVisible(true);
		fightJPanel.setVisible(true);
		switchJPanel.setVisible(true);
		gameJFrame.setVisible(true); 
		
    }
    
	protected String whichPokemon(int i){
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
	
	protected String getType(int i){
		switch(i){
		case 0: return "Normal";
		case 1: return "fire";
		case 2: return "water";
		case 3: return "electric";
		case 4: return "grass";
		case 5: return "fighting";
		case 6: return "psychic";
		case 7: return "steel";
		case 8: return "fairy";
	}
	return "Oh no";
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
    
    private ImageIcon getButtonSprite(int i){
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

    private String getNormMove(int i) {
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
    
    private String getMove(int i) {
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
    
    private String getTypeName(int i) {
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
    
    private Color getColor(int i) {
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
    private int getDamage() { 
    	return game.getDamage(); 
    }

	
	public static void typeMatrix() throws FileNotFoundException {
		sc = new Scanner(new File("type-matrix.txt"));
		for (int i = 0; i < NUM_OF_TYPES; i++) {
			for (int j = 0; j < NUM_OF_TYPES; j++) {
				attackEfficiency[i][j] = Double.parseDouble(sc.next()); 
				System.out.print(attackEfficiency[i][j] + " ");
			}
			System.out.println();
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
			pokemonHealth[i] = MAX_HEALTH; 
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
	

	private JPanel CreateIDBoxes(int Pokemon, double pokemonHealth){
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(2,1));
		JLabel PokemonName = new JLabel("<html><font size=5>" + whichPokemon(Pokemon) + "</font></html>", SwingConstants.CENTER);
		JLabel Health = new JLabel("<html>(" + getTypeName(Pokemon) + ") <font size=4>" + pokemonHealth+"/" + MAX_HEALTH + "</font></html>",  SwingConstants.CENTER);
		
		myPanel.add(PokemonName);
		myPanel.add(Health);
		return myPanel;
	}


	protected void DamageMessage(){
		userMessageLabel.setVisible(false);
		if (!isNormalMove) {		
			userMessageLabel.setText("<html>" + whichPokemon(pokemon[PlayerActive])+" used " + getMove(pokemon[PlayerActive]) + ". <br>" + effectiveness() + CPUPokemonHit() +"</html>");
		
		} else {
			userMessageLabel.setText("<html>" + whichPokemon(pokemon[PlayerActive])+" used " + getNormMove(pokemon[PlayerActive]) + ". <br>" + effectiveness() + CPUPokemonHit()+ "</html>");
		}
		CPUPokemonHit();
		userMessageLabel.setVisible(true);
	}
	
	protected String effectiveness() {
		double efficiency; 
		if (!isNormalMove) {
			efficiency = attackEfficiency[pokemon[PlayerActive]][pokemon[CPUActive]];
		} else {
			efficiency = attackEfficiency[NORMAL][pokemon[CPUActive]];
		}
		String message = ""; 
		if (efficiency == 2.0) {
			message = "It's SUPER EFFECTIVE!";
		} else if (efficiency == 0.5) {
			message = "It's NOT VERY EFFECTIVE...";
		} 
//		System.out.println(attackEfficiency[pokemon[PlayerActive]][pokemon[CPUActive]]);
//		System.out.println(message);
		return message; 
	}
	
	protected void SwitchMessage(){
		userMessageLabel.setVisible(false);
		userMessageLabel.setText("You switched to "+whichPokemon(pokemon[PlayerActive])+"!");
		userMessageLabel.setVisible(true);	
	}
	
	public void actionPerformed(ActionEvent event){
		if(event.getSource() == normButton){ // Normal move
			isNormalMove = true; 
			DamageMessage();
			
		}else if(event.getSource() == typeButton){ // Type move
			isNormalMove = false; 
			DamageMessage();
		}else if(event.getSource() == switch1Button){ // Switch to pokemon[0]
			PlayerActive = 0;
			switchPokemon();
		}else if(event.getSource() == switch2Button){ // Switch to pokemon[1]
			PlayerActive = 1;
			switchPokemon();
		}else if(event.getSource() == switch3Button){ // switch to pokemon[2]
			PlayerActive = 2;
			switchPokemon();

		}
	}
	
	protected void switchPokemon() {
		SwitchMessage(); 
		player1.setVisible(false); 
		player1 = getPlayerSprite(pokemon[PlayerActive]);
		player1.setBounds(0,plPokePos,imageWidth,imageHeight);
		gameContentPane.add(player1);
		typeButton.setVisible(false);
		typeButton.setText("<html><center>" + getMove(pokemon[PlayerActive]) + "<br><font size=1>(" + getTypeName(pokemon[PlayerActive]) + ")</font></center></html>");
		typeButton.setVisible(true);
		normButton.setVisible(false);
		normButton.setText("<html><center>" + getNormMove(pokemon[PlayerActive]) + "<br><font size=1>(NORMAL)</font></center></html>");
		normButton.setVisible(true);
		playerPanel.setVisible(false);
		playerPanel = CreateIDBoxes(pokemon[PlayerActive],50);
		playerPanel.setBounds(125, 210, jframeWidth-imageWidth*2, 50);
		playerPanel.setBackground(WHITE);
		playerPanel.setBorder(BorderFactory.createMatteBorder(matteTop,matteBottom,matteTop,matteBottom,getColor(pokemon[PlayerActive])));
		gameContentPane.add(playerPanel);
		playerPanel.setVisible(true);
	}
	
	protected void UserPokemonHit(){
		double damageDone = getDamage();
		double efficiency; 
		if (!isNormalMove) {
			efficiency = attackEfficiency[pokemon[CPUActive]][pokemon[PlayerActive]];
		} else {
			efficiency = attackEfficiency[CPUActive][NORMAL];
		}
		pokemonHealth[PlayerActive] -= damageDone*efficiency ;
	}
	
	
	protected String CPUPokemonHit(){
		double damageDone = getDamage();
		double efficiency; 
		if (!isNormalMove) {
			efficiency = attackEfficiency[pokemon[PlayerActive]][pokemon[CPUActive]];
		} else {
			efficiency = attackEfficiency[NORMAL][pokemon[CPUActive]];
		}
		pokemonHealth[CPUActive] -= damageDone*efficiency;
		return " (-"+damageDone*efficiency+")";
	}
	
	
	public static void main(String[] args) throws FileNotFoundException  {
		
		typeMatrix(); 
		@SuppressWarnings("unused")
		Controller myController = new Controller(); 

	}
	
}
