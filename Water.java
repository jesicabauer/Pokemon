package game;

import java.awt.Image;

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
		ImageIcon imageIconOrg = new ImageIcon("squirtle-front.png");
		Image imageOrg = imageIconOrg.getImage(); 
		Image image = imageOrg.getScaledInstance(50, 50, 1);
		ImageIcon imageIcon = new ImageIcon(image);
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
