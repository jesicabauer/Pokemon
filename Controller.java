package game;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.util.Timer;
import java.util.TimerTask;

class Controller extends TimerTask implements ActionListener, ItemListener, MouseListener{
	
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
	public final static int MAX_NAME_LENGTH = 9; 
	
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
	
	public static double EASY_HEALTH = 100;
	public static double HARD_HEALTH = 75; 
	public static double cpuMaxHealth;
	public static double playerMaxHealth;
	
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
	
	public int jframeWidth = 390; 
	public int jframeHeight = 490; 
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

	private static final int DISPLAY_TIME = 5000;
	private static final int DECREMENT = 100; 
	private static int playercountdown = 0; 
	private static int cpucountdown = 0; 
	
	private JFrame StartFrame = new JFrame();
	private Container StartContainer = new Container();
	private JTextArea myName = new JTextArea(); 
	private JTextArea RivalName = new JTextArea();
	private JRadioButton EasyButton = new JRadioButton();
	private JRadioButton HardButton = new JRadioButton();
	private JButton goButton = new JButton();
	private JRadioButton CurrentButton = EasyButton;
	private String PlayerName;
	private String CPUName;
	
	private JFrame EndFrame = new JFrame();
	private Container EndContainer = new Container();
	private JButton AgainButton = new JButton();
	private JButton ExitButton = new JButton();
	private JLabel EndLabel = new JLabel();
	
	private JFrame InstructionFrame = new JFrame();
	private Container InstructionContainer = new Container();
	private JButton NextButton = new JButton();
	
	private String iknowString = "I know how to play!";
	private String teachmeString = "Teach me how to Play!";
	
    public Controller()  {
    	
    	myTimer.schedule(this, 0, DECREMENT);
    	
    	Object stringArray[] = {iknowString, teachmeString};
    	
    	int returnvalue = JOptionPane.showOptionDialog(null, "Have you played Pokemon before?", "Select an Option",
    	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray,
    	        stringArray[0]);
    	if (returnvalue < 0) {
    		System.exit(0);
    	}
    	if(stringArray[returnvalue].equals(iknowString)){
	    	StartScreen();
	//    	if(isGameReady){
	    	shufflePokemon(); 
	    	gameJFrame = new JFrame("Pokemon Showdown!");
    	}else if(stringArray[returnvalue].equals(teachmeString)){
    		Instructions();	
    	} 
 
	}
    private void Instructions(){
    	InstructionFrame.setSize(806, 529);
    	JLabel background = new JLabel();
    	background.setIcon(new ImageIcon("instructions.png"));
    	background.setVisible(true);
    	background.setLayout(new BorderLayout());
    	InstructionFrame.setContentPane(background);
    	
    	NextButton.setText("Got It!");
    	NextButton.addActionListener(this);
    	NextButton.setBounds(581,437,202,51);
    	NextButton.setBackground(WHITE);
    	InstructionContainer.add(NextButton);
    	

    	InstructionFrame.add(InstructionContainer);
    	InstructionFrame.setLocationRelativeTo(null);
    	InstructionContainer.setVisible(true);
    	InstructionFrame.setVisible(true);
    	InstructionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	InstructionFrame.setResizable(false);
    }
   
    private void StartScreen(){
    	JLabel background = new JLabel();
    	background.setIcon( new ImageIcon("startscreen.png") );
    	background.setVisible(true);
    	background.setLayout( new BorderLayout() );
    	StartFrame.setContentPane( background );
    	
    	
		ButtonGroup Buttons = new ButtonGroup();
		
		EasyButton.setText("Easy");
		EasyButton.addItemListener(this);
		EasyButton.setSelected(true);
		EasyButton.setBounds(76,335,55,48);
		EasyButton.setBackground(WHITE);
		HardButton.setText("Hard");
		HardButton.addItemListener(this);
		HardButton.setBounds(267,334,55,50);
		HardButton.setBackground(WHITE);
		
		StartContainer.add(EasyButton);
		StartContainer.add(HardButton);
		
		Buttons.add(EasyButton);
		Buttons.add(HardButton);
		
		goButton.setText("Start Game!");
		goButton.addActionListener(this);
		goButton.setBounds(49,407,301,51);
		goButton.setBackground(WHITE);
		
		myName.setBounds(197,181,153,50);
		RivalName.setBounds(197,257,153,50);
		
		StartContainer.add(myName);
		StartContainer.add(RivalName);
		StartContainer.add(goButton);
	 
		StartFrame.add(StartContainer);
		StartFrame.setSize(jframeWidth,jframeHeight);
    	StartFrame.setLocationRelativeTo(null);
    	StartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	StartFrame.setVisible(true);
    	StartFrame.setResizable(false);
    	StartContainer.setVisible(true);
		isGameReady = false;
	}
    
    private void StartGame(){
    	
		
		StartFrame.setVisible(false);
		
		if (myName.getText().equals("")) {
			PlayerName = "ERICA";
		} else if(myName.getText().trim().length() > MAX_NAME_LENGTH) { 
			PlayerName = myName.getText().trim().substring(0,MAX_NAME_LENGTH) + "...";
		} else {
			PlayerName = myName.getText().toUpperCase().trim();
		}
		if (RivalName.getText().equals("")) {
			CPUName = "JESICA";
		} else if (RivalName.getText().trim().length() > MAX_NAME_LENGTH) {
			CPUName = RivalName.getText().trim().substring(0,MAX_NAME_LENGTH) + "...";
		} else {
			CPUName = RivalName.getText().toUpperCase().trim();
		}
		
		
		if(CurrentButton.equals(HardButton)){
			playerMaxHealth = HARD_HEALTH;
			cpuMaxHealth = HARD_HEALTH;
			GameDisplay();
		}else{
			playerMaxHealth = EASY_HEALTH;
			cpuMaxHealth = EASY_HEALTH;
			GameDisplay();
		}
    }
    
    public void GameDisplay(){
    	shufflePokemon();
    	assignPokemon();
    
    	gameJFrame.setSize(jframeWidth,jframeHeight);
    	gameJFrame.setLocationRelativeTo(null);
    	gameJFrame.addMouseListener(this); 
    	gameJFrame.setResizable(false);

    	
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
		
		userMessagePanel.setVisible(false);
		fightJPanel.setVisible(true);
		switchJPanel.setVisible(true);
		gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameJFrame.setVisible(true); 
		
		isPlayerTurn = true; 
		isGameReady = true;
//		isPlayerTurn = true; 

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
				pokemonHealth[i] = playerMaxHealth;
				pokemonHealth[i+3] = cpuMaxHealth;
			}
			isDead[i] = false; 
//			System.out.print(pokemon[i]);
		}
//		System.out.println();
	}
	
	private void assignPokemon() {
//		System.out.println("Your Pokemon are: ");
		for (int i = 0; i < ASSIGNED_POKEMON; i++) {
			playerPokemon[i] = pokemon[i]; 
			playerHealth[i] = pokemonHealth[i]; 
			isPlayerDead[i] = isDead[i]; 
//			System.out.println(whichPokemon(pokemon[i]));
		}
//		System.out.println("The CPU's Pokemon are: "); 
		for (int i = 0; i < ASSIGNED_POKEMON; i++) {
			cpuPokemon[i] = pokemon[i+ASSIGNED_POKEMON];
			cpuHealth[i] = pokemonHealth[i+ASSIGNED_POKEMON];
			isCPUDead[i] = isDead[i+ASSIGNED_POKEMON];
//			System.out.println(whichPokemon(pokemon[i+ASSIGNED_POKEMON]));
		}
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

	private JPanel CreatePlayerIDBoxes(int Pokemon, double pokemonHealth){ // creates the health boxes
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(2,1));
		JLabel PokemonName = new JLabel("<html><font size=5>" + whichPokemon(Pokemon) + "</font></html>", SwingConstants.CENTER);
		JLabel Health = new JLabel("<html>(" + myArrays.getTypeName(Pokemon) + ") <font size=4>" + pokemonHealth+"/" + playerMaxHealth + "</font></html>",  SwingConstants.CENTER);
		
		myPanel.add(PokemonName);
		myPanel.add(Health);
		return myPanel;
	}
	
	private JPanel CreateCPUIDBoxes(int Pokemon, double pokemonHealth){ // creates the health boxes
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(2,1));
		JLabel PokemonName = new JLabel("<html><font size=5>" + whichPokemon(Pokemon) + "</font></html>", SwingConstants.CENTER);
		JLabel Health = new JLabel("<html>(" + myArrays.getTypeName(Pokemon) + ") <font size=4>" + pokemonHealth+"/" + cpuMaxHealth + "</font></html>",  SwingConstants.CENTER);
		
		myPanel.add(PokemonName);
		myPanel.add(Health);
		return myPanel;
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

	protected void DamageMessage(){ // message when a pokemon is damaged
		userMessageLabel.setVisible(false);
		if (isPlayerTurn) {
			if (!isNormalMove) {		
				userMessageLabel.setText("<html> " + PlayerName + "'s " + whichPokemon(playerPokemon[PlayerActive])+" used " + myArrays.getMove(playerPokemon[PlayerActive]) + ". <br>" + effectiveness() + pokemonHit() +"</html>");
			
			} else {
				userMessageLabel.setText("<html> " + PlayerName + "'s " + whichPokemon(playerPokemon[PlayerActive])+" used " + myArrays.getNormMove(playerPokemon[PlayerActive]) + ". <br>" + effectiveness() + pokemonHit()+ "</html>");
			}
			userMessageLabel.setVisible(true);

			userMessagePanel.setVisible(true);
		} else {
			if (!isNormalMove) {		
				userMessageLabel.setText("<html>" + CPUName + "'s " + whichPokemon(cpuPokemon[CPUActive])+" used " + myArrays.getMove(cpuPokemon[CPUActive]) + ". <br>" + effectiveness() + pokemonHit() +"</html>");
//				System.out.println("CPU used a type move!");
			} else {
				userMessageLabel.setText("<html>" + CPUName + "'s " + whichPokemon(cpuPokemon[CPUActive])+" used " + myArrays.getNormMove(cpuPokemon[CPUActive]) + ". <br>" + effectiveness() + pokemonHit()+ "</html>");
//				System.out.println("CPU used a normal move!");
			}
			userMessageLabel.setVisible(true);

			userMessagePanel.setVisible(true);
		} 
	}
	
	protected String effectiveness() { // returns how effective an attack is
		if (isPlayerTurn) {
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
		} else {
			double efficiency; 
			if (!isNormalMove) {
				efficiency = attackEfficiency[cpuPokemon[CPUActive]][playerPokemon[PlayerActive]];
			} else {
				efficiency = attackEfficiency[NORMAL][playerPokemon[PlayerActive]];
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
		
	}
		
	protected void isPokemonFainted(int i){
		if (isPlayerTurn) {
			if (cpuHealth[i] <= 0) {
				cpuHealth[i] = 0;
				isCPUDead[i] = true; 
//				System.out.println(whichPokemon(cpuPokemon[i]) + " fainted!");

				userMessagePanel.setVisible(true);
				userMessagePanel.setVisible(true);
				int pokemonAct = ++CPUActive;
				CPUActive = pokemonAct%3; 
				if (cpuHealth[CPUActive] == 0) {
					pokemonAct = ++CPUActive;
					CPUActive = pokemonAct%3; 
				}
				userMessageLabel.setVisible(false);
				userMessageLabel.setText("<html>" + CPUName + "'s " + whichPokemon(cpuPokemon[i]) + " fainted! <br>"+CPUName+" switched to " + whichPokemon(cpuPokemon[CPUActive]) + ". </html>");
				userMessageLabel.setVisible(true);
				faintedSwitch(); 
				UpdateHealth();
				UpdateImage();
			} 
		} else {
			if (playerHealth[i] <= 0) {
				playerHealth[i] = 0;
				isPlayerDead[i] = true;
//				System.out.println(whichPokemon(playerPokemon[i]) + " fainted!");
				
				userMessagePanel.setVisible(true);
				userMessagePanel.setVisible(true);
				int pokemonAct = ++PlayerActive;
				PlayerActive = pokemonAct%3;
				if (playerHealth[PlayerActive] == 0) {
					pokemonAct = ++PlayerActive;
					PlayerActive = pokemonAct%3;
				}
				userMessageLabel.setVisible(false);
				userMessageLabel.setText("<html>" + PlayerName + "'s " + whichPokemon(playerPokemon[i]) + " fainted! <br> You switched to " + whichPokemon(playerPokemon[PlayerActive]) + ". </html>");
				userMessageLabel.setVisible(true);
				faintedSwitch(); 
				UpdateHealth();
				UpdateImage();
//				myTimer.schedule(this, TIME);
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
	
	protected void faintedSwitch() { // Code to switch the graphics when the pokemon changes 
		if (isPlayerTurn) {
//			userMessageLabel.setVisible(false);
//			userMessageLabel.setText("COMPUTER switched to "+whichPokemon(cpuPokemon[CPUActive])+"!");
//			userMessageLabel.setVisible(true);	
//			userMessagePanel.setVisible(true);
			
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
			playerPanel = CreatePlayerIDBoxes(playerPokemon[PlayerActive],50);
			playerPanel.setBounds(125, 210, jframeWidth-imageWidth*2, 50);
			playerPanel.setBackground(WHITE);
			playerPanel.setBorder(BorderFactory.createMatteBorder(matteTop,matteBottom,matteTop,matteBottom,myArrays.getColor(playerPokemon[PlayerActive])));
			gameContentPane.add(playerPanel);
			playerPanel.setVisible(true);
		} else {
//			userMessageLabel.setVisible(false);
//			userMessageLabel.setText("You switched to "+whichPokemon(playerPokemon[PlayerActive])+"!");
//			userMessageLabel.setVisible(true);	
//			userMessagePanel.setVisible(true);
			pc1.setVisible(false);
			pc1 = myArrays.getPCSprite(cpuPokemon[CPUActive]); 
			pc1.setBounds(jframeWidth-imageWidth,0,imageWidth,imageHeight);
			gameContentPane.add(pc1);
			pc1.setVisible(true);
			typeButton.setVisible(false);
			typeButton.setText("<html><center>" + myArrays.getMove(playerPokemon[PlayerActive]) + "<br><font size=1>(" + myArrays.getTypeName(playerPokemon[PlayerActive]) + ")</font></center></html>");
			typeButton.setVisible(true);
			normButton.setVisible(false);
			normButton.setText("<html><center>" + myArrays.getNormMove(playerPokemon[PlayerActive]) + "<br><font size=1>(NORMAL)</font></center></html>");
			normButton.setVisible(true);
		}
	}
	
	protected void SwitchMessage(){ // message that appears when the player chooses to change their pokemon
		if(isPlayerTurn) {
			userMessageLabel.setVisible(false);
			userMessageLabel.setText(PlayerName+" switched to "+whichPokemon(playerPokemon[PlayerActive])+"!");
			userMessageLabel.setVisible(true);	
	
			userMessagePanel.setVisible(true);
		} else {
			userMessageLabel.setVisible(false);
			userMessageLabel.setText(CPUName+" switched to "+whichPokemon(cpuPokemon[CPUActive])+"!");
			userMessageLabel.setVisible(true);	

			userMessagePanel.setVisible(true);
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
//				userMessageLabel.setVisible(false);
//				userMessageLabel.setText("Player defeated! You won!");
//				userMessageLabel.setVisible(true);
//
//				userMessagePanel.setVisible(true);
//				isPlayerTurn = true; 
				isGameReady = false; 
				EndScreen();
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
//				userMessageLabel.setVisible(false);
//				userMessageLabel.setText("You have been defeated! You lost!");
//				userMessageLabel.setVisible(true);
//
//				userMessagePanel.setVisible(true);
				isGameReady = false; 
				EndScreen();
			}
		}
	}

	protected void cpuTurn() {

//		System.out.println("I MADE IT TO THE CPU\'S TURN");
		cpucountdown = DISPLAY_TIME; 
		if (CurrentButton == HardButton) {
			isNormalMove = cpu.cpuMoveHard(attackEfficiency, playerPokemon, PlayerActive, cpuPokemon, CPUActive);
		} else {
			isNormalMove = cpu.cpuMove(); 
		}
		DamageMessage();
		UpdateHealth();
		if (isPlayerTurn) {
			isPokemonFainted(CPUActive);
		} else {
			isPokemonFainted(PlayerActive);
		}
		isAllDead(); 
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		isGameReady = false;
		playercountdown = 0;
		cpucountdown = 0; 
		isGameReady = true; 
	}
	
	public void EndScreen(){
		gameJFrame.setVisible(false);
		EndFrame.setSize(jframeWidth, jframeHeight);
		JLabel background = new JLabel();
    	background.setIcon( new ImageIcon("winscreen.png") );
    	background.setVisible(true);
    	background.setLayout( new BorderLayout() );
    	EndFrame.setContentPane( background );
    	EndFrame.setLocationRelativeTo(null);
    	EndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	EndFrame.setResizable(false);
			
		AgainButton.setText("Play Again");
		AgainButton.addActionListener(this);
		AgainButton.setBounds(50, 304, 300, 50);
		AgainButton.setBackground(WHITE);
		
		ExitButton.setText("Quit");
		ExitButton.addActionListener(this);
		ExitButton.setBounds(50, 394, 300, 50);
		ExitButton.setBackground(WHITE);
		
		
		if (didIWin){
			EndLabel.setText("<html><center> Congratulations, " + PlayerName + "! You defeated RIVAL " + CPUName + "! </font></center></html>");
		} 
		if (didILose) {
			EndLabel.setText("<html><center> Oh no, " + PlayerName + "! You have been defeated by RIVAL " + CPUName + "! </font></center></html>");
		}
		
		EndLabel.setBounds(100, 225, 200, 50);
		EndLabel.setBackground(WHITE);
		
		EndContainer.add(EndLabel, SwingConstants.CENTER);
		EndContainer.add(AgainButton);
		EndContainer.add(ExitButton);
		
		
		EndFrame.add(EndContainer);
		EndFrame.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent event){ // button pressed
		playercountdown = DISPLAY_TIME;
		
		if(event.getSource() ==NextButton){
			InstructionFrame.setVisible(false);
			StartScreen();
	    	gameJFrame = new JFrame("Pokemon Showdown!");

		}
		
		if(event.getSource() == goButton){
			StartGame();
//			System.out.println(PlayerName+" "+ CPUName);

		} else if(event.getSource() == AgainButton){
			 
			EndFrame.setVisible(false);
			didIWin = false;
			didILose = false; 
			@SuppressWarnings("unused")
			Controller myController = new Controller();
			
		}else if(event.getSource()== ExitButton){
			System.exit(0);
		}
		
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
				if (playerHealth[0] == 0) {
					userMessageLabel.setVisible(false);
					userMessageLabel.setText("That Pokemon has already fainted!");
					userMessageLabel.setVisible(true);	
					userMessagePanel.setVisible(true);
				}
				else if (PlayerActive == 0) {
					userMessageLabel.setVisible(false);
					userMessageLabel.setText("That Pokemon is already out!");
					userMessageLabel.setVisible(true);	
					userMessagePanel.setVisible(true);
				} else {
				PlayerActive = 0;
				switchPokemon();
				waiting = false; 
//				if(!didIWin) {
//					cpuTurn(); 
//				}
				}
				
			}else if(event.getSource() == switch2Button){ // Switch to pokemon[1]
				if (playerHealth[1] == 0) {
					userMessageLabel.setVisible(false);
					userMessageLabel.setText("That Pokemon has already fainted!");
					userMessageLabel.setVisible(true);	
					userMessagePanel.setVisible(true);
				}
				else if (PlayerActive == 1) {
					userMessageLabel.setVisible(false);
					userMessageLabel.setText("That Pokemon is already out!");
					userMessageLabel.setVisible(true);	
					userMessagePanel.setVisible(true);
				} else {
				PlayerActive = 1;
				switchPokemon();
				waiting = false; 
//				if(!didIWin) {
//					cpuTurn(); 
//				} 
				}
			}else if(event.getSource() == switch3Button){ // switch to pokemon[2]
				if (playerHealth[2] == 0) {
					userMessageLabel.setVisible(false);
					userMessageLabel.setText("That Pokemon has already fainted!");
					userMessageLabel.setVisible(true);	
					userMessagePanel.setVisible(true);
				}
				else if (PlayerActive == 2) {
					userMessageLabel.setVisible(false);
					userMessageLabel.setText("That Pokemon is already out!");
					userMessageLabel.setVisible(true);	
					userMessagePanel.setVisible(true);
				} else {
				PlayerActive = 2;
				switchPokemon();
				waiting = false; 
//				if(!didIWin) {
//					cpuTurn(); 
//				}
				}
			} 
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent event) {
		CurrentButton = (JRadioButton) event.getSource();
	}
	
	public void run(){
		if(isGameReady) {
			if (isPlayerTurn) {
				if (!waiting) {
					if (playercountdown > 0) {
					normButton.setEnabled(false);
					typeButton.setEnabled(false);
					switch1Button.setEnabled(false);
					switch2Button.setEnabled(false);
					switch3Button.setEnabled(false);
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
					normButton.setEnabled(true);
					typeButton.setEnabled(true);
					switch1Button.setEnabled(true);
					switch2Button.setEnabled(true);
					switch3Button.setEnabled(true);
					waiting = true;
					gameJFrame.repaint();
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws FileNotFoundException  {
		typeMatrix(); 
		@SuppressWarnings("unused")
		Controller myController = new Controller(); 
		// Credit to Kyle and Natalie for writing the sound class
		Sound mySound = new Sound(); 
		mySound.SetMusic("music.wav");
		mySound.playSound(true); 

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
}
