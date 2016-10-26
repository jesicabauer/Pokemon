package game;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Fairy extends PokemonSuper {
	
	protected JLabel getPlayerSprite() {
		ImageIcon imageIcon = new ImageIcon("jigglypuff-back.png");
	    JLabel label = new JLabel(imageIcon);
		return label; 
	}
	
	protected JLabel getPCSprite() {
		ImageIcon imageIcon = new ImageIcon("jigglypuff-front.png");
	    JLabel label = new JLabel(imageIcon);
		return label; 
	}

	protected ImageIcon getButtonSprite(){
		ImageIcon imageIconOrg = new ImageIcon("jigglypuff-front.png");
		Image imageOrg = imageIconOrg.getImage(); 
		Image image = imageOrg.getScaledInstance(50, 50, 1);
		ImageIcon imageIcon = new ImageIcon(image);
		return imageIcon; 
	}

	protected int getMoveType() {
		return FAIRY; 
	}
	
	protected int getType() {
		return FAIRY; 
	}
	
	protected String getName() {
		return "JIGGLYPUFF"; 
	}
	
	protected String getMove() {
		return "SING";
	}
	
}
