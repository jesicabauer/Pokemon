package game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Psychic  extends PokemonSuper {
	
	protected JLabel getPlayerSprite() {
		ImageIcon imageIcon = new ImageIcon("abra-back.png");
	    JLabel label = new JLabel(imageIcon);
		return label; 
	}
	
	protected JLabel getPCSprite() {
		ImageIcon imageIcon = new ImageIcon("abra-front.png");
	    JLabel label = new JLabel(imageIcon);
		return label; 
	}

	protected int getMoveType() {
		return PSYCHIC; 
	}
	
	protected int getType() {
		return PSYCHIC; 
	}
	
	protected String getName() {
		return "ABRA"; 
	}
	
	protected String getMove() {
		return "DREAM EATER";
	}
}
