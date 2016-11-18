package game;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
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
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.util.Timer;
import java.util.TimerTask;

class Controller extends TimerTask implements ActionListener{
	
	
	
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
	InstantiateArrays myArrays = new InstantiateArrays();
	Fire fire = new Fire(); 
	Water water = new Water();
	Electric electric = new Electric(); 
	Grass grass = new Grass(); 
	Fighting fighting = new Fighting(); 
	Psychic psychic = new Psychic(); 
	Steel steel = new Steel(); 
	Fairy fairy = new Fairy();
	CPUaction cpu = new CPUaction(); 
	
	public static double attackEfficiency[][] = new double[NUM_OF_TYPES][NUM_OF_TYPES]; 
	
	public static int pokemon[] = new int[NUM_OF_POKEMON];
	public static int playerPokemon[] = new int[ASSIGNED_POKEMON];
	public static int cpuPokemon[] = new int[ASSIGNED_POKEMON];
	
	public static double pokemonHealth[] = new double[NUM_OF_POKEMON];
	public static double playerHealth[] = new double[ASSIGNED_POKEMON];
	public static double cpuHealth[] = new double[ASSIGNED_POKEMON];
	
	public static double CPU_MAX_HEALTH = 50;
	public static double PLAYER_MAX_HEALTH = 100;
	
	public static boolean isDead[] = new boolean[NUM_OF_TYPES];
	public static boolean isPlayerDead[] = new boolean[ASSIGNED_POKEMON];
	public static boolean isCPUDead[] = new boolean[ASSIGNED_POKEMON];
	
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
	protected int CPUActive = 0;
	protected boolean isNormalMove; 
	protected boolean isPlayerTurn = true; 
	protected boolean didIWin = false; 
	protected boolean didILose = false;
	protected boolean isGameReady = false; 
	protected boolean waiting = true; 
	
	protected Timer myTimer = new Timer();

	private static final int DISPLAY_TIME = 2000;
	private static final int DECREMENT = 100; 
	private static int playercountdown = 0; 
	private static int cpucountdown = 0; 
	
    public Controller()  {
    	
    	myTimer.schedule(this, 0, DECREMENT);
    	
    	shufflePokemon(); 
		assignPokemon(); 
    	
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
    	
    	normButton = new JButton("<html><center>" + myArrays.getNormMove(playerPokemon[PlayerActive]) + "<br><font size=1>(NORMAL)</font></center></html>");
    	typeButton = new JButton("<html><center>" + myArrays.getMove(playerPokemon[PlayerActive]) + "<br><font size=1>(" + myArrays.getTypeName(playerPokemon[PlayerActive]) + ")</font></center></html>");
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

    	switch1Button = new JButton(myArrays.getButtonSprite(pokemon[0]));
    	switch2Button = new JButton(myArrays.getButtonSprite(pokemon[1]));
    	switch3Button = new JButton(myArrays.getButtonSprite(pokemon[2]));
    	switch1Button.addActionListener(this);
    	switch2Button.addActionListener(this);
    	switch3Button.addActionListener(this);
    	switch1Button.setBackground(myArrays.getColor(pokemon[0]));
    	switch2Button.setBackground(myArrays.getColor(pokemon[1]));
    	switch3Button.setBackground(myArrays.getColor(pokemon[2]));

    	switchJPanel.add(switch1Button);
    	switchJPanel.add(switch2Button);
    	switchJPanel.add(switch3Button); 
    	
		userMessageLabel = new JLabel();
		userMessagePanel = new JPanel(); 
		
		player1 = myArrays.getPlayerSprite(playerPokemon[PlayerActive]); 
		pc1 = myArrays.getPCSprite(cpuPokemon[CPUActive]); 
		player1.setBounds(0,plPokePos,imageWidth,imageHeight);
		pc1.setBounds(jframeWidth-imageWidth,0,imageWidth,imageHeight);
		
		gameContentPane.add(player1);
		gameContentPane.add(pc1);
		gameContentPane.add(fightJPanel);
		gameContentPane.add(switchJPanel);

		playerPanel = CreatePlayerIDBoxes(playerPokemon[PlayerActive],playerHealth[PlayerActive]);
		CPUPanel = CreateCPUIDBoxes(cpuPokemon[CPUActive],cpuHealth[CPUActive]);		
		playerPanel.setBounds(125, 210, jframeWidth-imageWidth*2, 50);
		playerPanel.setBorder(BorderFactory.createMatteBorder(matteTop,matteBottom,matteTop,matteBottom,myArrays.getColor(playerPokemon[PlayerActive])));
		playerPanel.setBackground(WHITE);
		CPUPanel.setBounds(20,20, jframeWidth - imageWidth*2, 50);
		CPUPanel.setBorder(BorderFactory.createMatteBorder(
				matteTop, matteBottom, matteTop, matteBottom, myArrays.getColor(cpuPokemon[CPUActive])));
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
		
		isGameReady = true; 
		
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
			if (i<3){
				pokemonHealth[i] = PLAYER_MAX_HEALTH;
				pokemonHealth[i+3] = CPU_MAX_HEALTH;
			}
			isDead[i] = false; 
//			System.out.print(pokemon[i]);
		}
//		System.out.println();
	}
	
	private void assignPokemon() {
		System.out.println("Your Pokemon are: ");
		for (int i = 0; i < ASSIGNED_POKEMON; i++) {
			playerPokemon[i] = pokemon[i]; 
			playerHealth[i] = pokemonHealth[i]; 
			isPlayerDead[i] = isDead[i]; 
			System.out.println(whichPokemon(pokemon[i]));
		}
		System.out.println("The CPU's Pokemon are: "); 
		for (int i = 0; i < ASSIGNED_POKEMON; i++) {
			cpuPokemon[i] = pokemon[i+ASSIGNED_POKEMON];
			cpuHealth[i] = pokemonHealth[i+ASSIGNED_POKEMON];
			isCPUDead[i] = isDead[i+ASSIGNED_POKEMON];
			System.out.println(whichPokemon(pokemon[i+ASSIGNED_POKEMON]));
		}
	}
	

	private JPanel CreatePlayerIDBoxes(int Pokemon, double pokemonHealth){ // creates the health boxes
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(2,1));
		JLabel PokemonName = new JLabel("<html><font size=5>" + whichPokemon(Pokemon) + "</font></html>", SwingConstants.CENTER);
		JLabel Health = new JLabel("<html>(" + myArrays.getTypeName(Pokemon) + ") <font size=4>" + pokemonHealth+"/" + PLAYER_MAX_HEALTH + "</font></html>",  SwingConstants.CENTER);
		
		myPanel.add(PokemonName);
		myPanel.add(Health);
		return myPanel;
	}
	
	private JPanel CreateCPUIDBoxes(int Pokemon, double pokemonHealth){ // creates the health boxes
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(2,1));
		JLabel PokemonName = new JLabel("<html><font size=5>" + whichPokemon(Pokemon) + "</font></html>", SwingConstants.CENTER);
		JLabel Health = new JLabel("<html>(" + myArrays.getTypeName(Pokemon) + ") <font size=4>" + pokemonHealth+"/" + CPU_MAX_HEALTH + "</font></html>",  SwingConstants.CENTER);
		
		myPanel.add(PokemonName);
		myPanel.add(Health);
		return myPanel;
	}
	
	protected void isPokemonFainted(int i){
		if (isPlayerTurn) {
			if (cpuHealth[i] <= 0) {
				cpuHealth[i] = 0;
				isCPUDead[i] = true; 
//				System.out.println(whichPokemon(cpuPokemon[i]) + " fainted!");
				userMessageLabel.setVisible(false);
				userMessageLabel.setText(whichPokemon(cpuPokemon[i]) + " fainted!");
				userMessageLabel.setVisible(true);

				userMessagePanel.setVisible(true);
				userMessagePanel.setVisible(true);
				int pokemonAct = ++CPUActive;
				CPUActive = pokemonAct%3; 
				
			} 
		} else {
			if (playerHealth[i] <= 0) {
				playerHealth[i] = 0;
				isPlayerDead[i] = true;
//				System.out.println(whichPokemon(playerPokemon[i]) + " fainted!");
				userMessageLabel.setVisible(false);
				userMessageLabel.setText(whichPokemon(playerPokemon[i]) + " fainted!");
				userMessageLabel.setVisible(true);

				userMessagePanel.setVisible(true);
				userMessagePanel.setVisible(true);
				int pokemonAct = ++PlayerActive;
				PlayerActive = pokemonAct%3; 
//				myTimer.schedule(this, TIME);
			}
		}
			
	}
	
	protected void isAllDead() {
		if (isPlayerTurn) {
			didIWin = true;
			for (int i = 0; i < ASSIGNED_POKEMON; i++) {
				if (!isCPUDead[i]) {
					didIWin = false;
				}
			}
			
			if (didIWin) {
//				System.out.println("Player defeated! You won!");
				userMessageLabel.setVisible(false);
				userMessageLabel.setText("Player defeated! You won!");
				userMessageLabel.setVisible(true);

				userMessagePanel.setVisible(true);
				isPlayerTurn = false; 
			}
		} else {
			didILose = true;
			for (int i = 0; i < ASSIGNED_POKEMON; i++) {
				if(!isPlayerDead[i]) {
					didILose = false; 
				}
			}
			if (didILose) {
//				System.out.println("You have been defeated! You lost!");
				userMessageLabel.setVisible(false);
				userMessageLabel.setText("You have been defeated! You lost!");
				userMessageLabel.setVisible(true);

				userMessagePanel.setVisible(true);
			}
		}
	}


	protected void DamageMessage(){ // message when a pokemon is damaged
		userMessageLabel.setVisible(false);
		if (isPlayerTurn) {
			if (!isNormalMove) {		
				userMessageLabel.setText("<html>" + whichPokemon(playerPokemon[PlayerActive])+" used " + myArrays.getMove(playerPokemon[PlayerActive]) + ". <br>" + effectiveness() + pokemonHit() +"</html>");
			
			} else {
				userMessageLabel.setText("<html>" + whichPokemon(playerPokemon[PlayerActive])+" used " + myArrays.getNormMove(playerPokemon[PlayerActive]) + ". <br>" + effectiveness() + pokemonHit()+ "</html>");
			}
			userMessageLabel.setVisible(true);

			userMessagePanel.setVisible(true);
		} else {
			if (!isNormalMove) {		
				userMessageLabel.setText("<html>" + whichPokemon(cpuPokemon[CPUActive])+" used " + myArrays.getMove(cpuPokemon[CPUActive]) + ". <br>" + effectiveness() + pokemonHit() +"</html>");
//				System.out.println("CPU used a type move!");
			} else {
				userMessageLabel.setText("<html>" + whichPokemon(cpuPokemon[CPUActive])+" used " + myArrays.getNormMove(cpuPokemon[CPUActive]) + ". <br>" + effectiveness() + pokemonHit()+ "</html>");
//				System.out.println("CPU used a normal move!");
			}
			userMessageLabel.setVisible(true);

			userMessagePanel.setVisible(true);
		} 
	}
	
	protected String effectiveness() { // returns how effective an attack is
		double efficiency; 
		if (!isNormalMove) {
			efficiency = attackEfficiency[playerPokemon[PlayerActive]][cpuPokemon[CPUActive]];
		} else {
			efficiency = attackEfficiency[NORMAL][cpuPokemon[CPUActive]];
		}
		String message = ""; 
		if (efficiency == 2.0) {
			message = "It's SUPER EFFECTIVE!";
		} else if (efficiency == 0.5) {
			message = "It's NOT VERY EFFECTIVE...";
		} 
//		System.out.println(attackEfficiency[playerPokemon[PlayerActive]][cpuPokemon[CPUActive]]);
//		System.out.println(message);
		return message; 
	}
	
	protected void SwitchMessage(){ // message that appears when the player chooses to change their pokemon
		userMessageLabel.setVisible(false);
		userMessageLabel.setText("You switched to "+whichPokemon(playerPokemon[PlayerActive])+"!");
		userMessageLabel.setVisible(true);	

		userMessagePanel.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event){ // button pressed
		playercountdown = DISPLAY_TIME;
		if(isPlayerTurn && !didIWin && !didILose) {
			if(event.getSource() == normButton){ // Normal move
				isNormalMove = true; 
				DamageMessage();
				UpdateHealth();
				if (isPlayerTurn) {
					isPokemonFainted(CPUActive);
				} else {
					isPokemonFainted(PlayerActive);
				}
				isAllDead();
				waiting = false; 
//				if(!didIWin) {
//					cpuTurn(); 
//				}
			}else if(event.getSource() == typeButton){ // Type move
				isNormalMove = false; 
				DamageMessage();
				UpdateHealth();
				if (isPlayerTurn) {
					isPokemonFainted(CPUActive);
				} else {
					isPokemonFainted(PlayerActive);
				}

				isAllDead();
				waiting = false; 
//				if(!didIWin) {
//					cpuTurn(); 
//				}
			}else if(event.getSource() == switch1Button){ // Switch to pokemon[0]
				PlayerActive = 0;
				switchPokemon();
				waiting = false; 
//				if(!didIWin) {
//					cpuTurn(); 
//				}
			}else if(event.getSource() == switch2Button){ // Switch to pokemon[1]
				PlayerActive = 1;
				switchPokemon();
				waiting = false; 
//				if(!didIWin) {
//					cpuTurn(); 
//				} 
			}else if(event.getSource() == switch3Button){ // switch to pokemon[2]
				PlayerActive = 2;
				switchPokemon();
				waiting = false; 
//				if(!didIWin) {
//					cpuTurn(); 
//				}
			}
		}
	}
	
	protected void switchPokemon() { // Code to switch the graphics when the pokemon changes 
		if (isPlayerTurn) {
			SwitchMessage(); 
			player1.setVisible(false); 
			player1 = myArrays.getPlayerSprite(playerPokemon[PlayerActive]);
			player1.setBounds(0,plPokePos,imageWidth,imageHeight);
			gameContentPane.add(player1);
			typeButton.setVisible(false);
			typeButton.setText("<html><center>" + myArrays.getMove(playerPokemon[PlayerActive]) + "<br><font size=1>(" + myArrays.getTypeName(playerPokemon[PlayerActive]) + ")</font></center></html>");
			typeButton.setVisible(true);
			normButton.setVisible(false);
			normButton.setText("<html><center>" + myArrays.getNormMove(playerPokemon[PlayerActive]) + "<br><font size=1>(NORMAL)</font></center></html>");
			normButton.setVisible(true);
			playerPanel.setVisible(false);
			playerPanel = CreatePlayerIDBoxes(playerPokemon[PlayerActive],playerHealth[PlayerActive]);
			playerPanel.setBounds(125, 210, jframeWidth-imageWidth*2, 50);
			playerPanel.setBackground(WHITE);
			playerPanel.setBorder(BorderFactory.createMatteBorder(matteTop,matteBottom,matteTop,matteBottom,myArrays.getColor(playerPokemon[PlayerActive])));
			gameContentPane.add(playerPanel);
			playerPanel.setVisible(true);
		} else {
			pc1.setVisible(false);
			pc1 = myArrays.getPCSprite(cpuPokemon[CPUActive]); 
			pc1.setBounds(jframeWidth-imageWidth,0,imageWidth,imageHeight);
			gameContentPane.add(pc1);
			pc1.setVisible(true);
		}
	}
	
	protected void UpdateHealth(){
		playerPanel.setVisible(false);
		CPUPanel.setVisible(false);
		if (playerHealth[PlayerActive] <= 0) {
			playerHealth[PlayerActive] = 0;
		} 
		if (cpuHealth[CPUActive] <= 0) {
			cpuHealth[CPUActive] = 0;
		}
		playerPanel = CreatePlayerIDBoxes(playerPokemon[PlayerActive],playerHealth[PlayerActive]);
		CPUPanel = CreateCPUIDBoxes(cpuPokemon[CPUActive],cpuHealth[CPUActive]);		
		playerPanel.setBounds(125, 210, jframeWidth-imageWidth*2, 50);
		playerPanel.setBorder(BorderFactory.createMatteBorder(matteTop,matteBottom,matteTop,matteBottom,myArrays.getColor(playerPokemon[PlayerActive])));
		playerPanel.setBackground(WHITE);
		CPUPanel.setBounds(20,20, jframeWidth - imageWidth*2, 50);
		CPUPanel.setBorder(BorderFactory.createMatteBorder(
				matteTop, matteBottom, matteTop, matteBottom, myArrays.getColor(cpuPokemon[CPUActive])));
		CPUPanel.setBackground(WHITE);

		
		gameContentPane.add(playerPanel);
		gameContentPane.add(CPUPanel);
		CPUPanel.setVisible(true);
		playerPanel.setVisible(true);
		UpdateImage();
	}
	
	protected void UpdateImage() {
		if (isPlayerTurn) {
			pc1.setVisible(false);
			pc1 = myArrays.getPCSprite(cpuPokemon[CPUActive]); 
			pc1.setBounds(jframeWidth-imageWidth,0,imageWidth,imageHeight);
			gameContentPane.add(pc1);
			pc1.setVisible(true);
		} else {
			player1.setVisible(false);
			player1 = myArrays.getPlayerSprite(playerPokemon[PlayerActive]); 
			player1.setBounds(0,plPokePos,imageWidth,imageHeight);
			gameContentPane.add(player1);
			player1.setVisible(true);
		}
	}
	
	
	protected String pokemonHit(){ //Runs when the player chooses to attack the cpu
		if (isPlayerTurn) {
			double damageDone = game.getDamage();
			double efficiency; 
			if (!isNormalMove) {
				efficiency = attackEfficiency[playerPokemon[PlayerActive]][cpuPokemon[CPUActive]];
			} else {
				efficiency = attackEfficiency[NORMAL][cpuPokemon[CPUActive]];
			}
			cpuHealth[CPUActive] -= damageDone*efficiency;
			return " (-"+damageDone*efficiency+")";
		} else {
			double damageDone = game.getDamage();
			double efficiency; 
			if (!isNormalMove) {
				efficiency = attackEfficiency[cpuPokemon[CPUActive]][playerPokemon[PlayerActive]];
			} else {
				efficiency = attackEfficiency[cpuPokemon[CPUActive]][NORMAL];
			}
			playerHealth[PlayerActive] -= damageDone*efficiency ;
			return " (-"+damageDone*efficiency+")";
		}
	}
	
	protected void cpuTurn() {

//		System.out.println("I MADE IT TO THE CPU\'S TURN");
		cpucountdown = DISPLAY_TIME; 
		isNormalMove = cpu.cpuMove();
		DamageMessage();
		UpdateHealth();
		if (isPlayerTurn) {
			isPokemonFainted(CPUActive);
		} else {
			isPokemonFainted(PlayerActive);
		}
		isAllDead(); 
	}

	
	public void run(){
		if(isGameReady) {
			if (isPlayerTurn) {
				if (!waiting) {
					if (playercountdown > 0) {
					playercountdown -= DECREMENT; 
		//			System.out.print(playercountdown);
					userMessagePanel.setBounds(50,90,285,100);
					userMessagePanel.setBorder(BorderFactory.createMatteBorder(matteTop, matteBottom, matteTop, matteBottom, BLACK));
					userMessagePanel.setBackground(WHITE);
					} else {
						userMessagePanel.setVisible(false);
						isPlayerTurn = false; 
						cpuTurn(); 
						gameJFrame.repaint();
					} 
				}
			} else {
				if (cpucountdown > 0) {
					cpucountdown -= DECREMENT; 
	//				System.out.print(playercountdown);
					userMessagePanel.setBounds(50,90,285,100);
					userMessagePanel.setBorder(BorderFactory.createMatteBorder(matteTop, matteBottom, matteTop, matteBottom, BLACK));
					userMessagePanel.setBackground(WHITE);
				} else {
					userMessagePanel.setVisible(false);
					isPlayerTurn = true; 
					waiting = true;
					gameJFrame.repaint();
				}
			}
		}
		
	}
	private static void StartScreen(){
		JFrame StartFrame = new JFrame();
		JTextArea myName = new JTextArea("Enter Your Name");
		JTextArea RivalName = new JTextArea("Enter Your Rival's Name");

		JRadioButton EasyButton = new JRadioButton();
		EasyButton.setText("Easy");
		JRadioButton HardButton = new JRadioButton();
		HardButton.setText("Hard");
		JPanel radioPanel = new JPanel(); 
		radioPanel.add(EasyButton);
		radioPanel.add(HardButton);
		
		StartFrame.setLayout(new FlowLayout());
		StartFrame.add(myName);
		StartFrame.add(radioPanel);
		StartFrame.add(RivalName);
		StartFrame.setVisible(true);
		StartFrame.setSize(400,400);
		
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException  {
		
		StartScreen();
		typeMatrix(); 
		@SuppressWarnings("unused")
		Controller myController = new Controller(); 

	}
	
}
