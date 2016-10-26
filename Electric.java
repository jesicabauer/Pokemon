package game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Electric extends PokemonSuper {
	
	protected JLabel getPlayerSprite() {
		ImageIcon imageIcon = new ImageIcon("pikachu-back.png");
	    JLabel label = new JLabel(imageIcon);
		return label; 
	}
	
	protected JLabel getPCSprite() {
		ImageIcon imageIcon = new ImageIcon("pikachu-front.png");
	    JLabel label = new JLabel(imageIcon);
		return label; 
	}
	
	protected String getButtonSprite(){
		return "pikachu-front.png";
	}

	
	protected int getMoveType() {
		return ELECTRIC; 
	}
	
	protected int getType() {
		return ELECTRIC; 
	}
	
	protected String getName() {
		return "PIKACHU"; 
	}
	
	protected String getMove() {
		return "THUNDERSHOCK";
	}

}
