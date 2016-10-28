package game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Water extends PokemonSuper {
	
	protected JLabel getPlayerSprite() {
		ImageIcon imageIcon = new ImageIcon("squirtle-back.png");
	    JLabel label = new JLabel(imageIcon);
		return label; 
	}
	
	protected JLabel getPCSprite() {
		ImageIcon imageIcon = new ImageIcon("squirtle-front.png");
	    JLabel label = new JLabel(imageIcon);
		return label; 
	}
	
	protected ImageIcon getButtonSprite(){
		ImageIcon imageIcon = new ImageIcon("squirtle-front.png");
		return imageIcon; 
	}
	
	protected int getMoveType() {
		return WATER; 
	}
	
	protected int getType() {
		return WATER; 
	}
	
	protected String getName() {
		return "SQUIRTLE"; 
	}
	
	protected String getMove() {
		return "AQUA TAIL";
	}

}
