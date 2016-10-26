package game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Fighting  extends PokemonSuper {
	
	protected JLabel getPlayerSprite() {
		ImageIcon imageIcon = new ImageIcon("mankey-back.png");
	    JLabel label = new JLabel(imageIcon);
		return label; 
	}
	
	protected JLabel getPCSprite() {
		ImageIcon imageIcon = new ImageIcon("mankey-front.png");
	    JLabel label = new JLabel(imageIcon);
		return label; 
	}
	
	protected String getButtonSprite(){
		return "mankey-front.png";
	}
	
	protected int getMoveType() {
		return FIGHTING; 
	}
	
	protected int getType() {
		return FIGHTING; 
	}
	
	protected String getName() {
		return "MANKEY"; 
	}
	
	protected String getMove() {
		return "SEISMIC TOSS";
	}

}
