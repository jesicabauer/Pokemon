package game;

import java.awt.Image;

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
	
	protected ImageIcon getButtonSprite(){
		ImageIcon imageIconOrg = new ImageIcon("mankey-front.png");
		Image imageOrg = imageIconOrg.getImage(); 
		Image image = imageOrg.getScaledInstance(50, 50, 1);
		ImageIcon imageIcon = new ImageIcon(image);
		return imageIcon; 
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
