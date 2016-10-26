package game;

import java.awt.Image;

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
	
	protected ImageIcon getButtonSprite(){
		ImageIcon imageIconOrg = new ImageIcon("abra-front.png");
		Image imageOrg = imageIconOrg.getImage(); 
		Image image = imageOrg.getScaledInstance(50, 50, 1);
		ImageIcon imageIcon = new ImageIcon(image);
		return imageIcon; 
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
