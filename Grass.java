package game;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Grass extends PokemonSuper {
	
	protected JLabel getPlayerSprite() {
		ImageIcon imageIcon = new ImageIcon("bulbasaur-back.png");
	    JLabel label = new JLabel(imageIcon);
		return label; 
	}
	
	protected JLabel getPCSprite() {
		ImageIcon imageIcon = new ImageIcon("bulbasaur-front.png");
	    JLabel label = new JLabel(imageIcon);
		return label; 
	}
	
	protected ImageIcon getButtonSprite(){
		ImageIcon imageIconOrg = new ImageIcon("bulbasaur-front.png");
		Image imageOrg = imageIconOrg.getImage(); 
		Image image = imageOrg.getScaledInstance(50, 50, 1);
		ImageIcon imageIcon = new ImageIcon(image);
		return imageIcon; 
	}

	protected int getMoveType() {
		return GRASS; 
	}
	
	protected int getType() {
		return GRASS; 
	}
	
	protected String getName() {
		return "BULBASAUR"; 
	}
	
	protected String getMove() {
		return "VINE WHIP";
	}
}
