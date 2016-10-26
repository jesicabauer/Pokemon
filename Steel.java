package game;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Steel extends PokemonSuper {
	protected JLabel getPlayerSprite() {
		ImageIcon imageIcon = new ImageIcon("magnemite-back.png");
	    JLabel label = new JLabel(imageIcon);
		return label; 
	}
	
	protected JLabel getPCSprite() {
		ImageIcon imageIcon = new ImageIcon("magnemite-front.png");
	    JLabel label = new JLabel(imageIcon);
		return label; 
	}
	
	protected ImageIcon getButtonSprite(){
		ImageIcon imageIconOrg = new ImageIcon("magnemite-front.png");
		Image imageOrg = imageIconOrg.getImage(); 
		Image image = imageOrg.getScaledInstance(50, 50, 1);
		ImageIcon imageIcon = new ImageIcon(image);
		return imageIcon; 
	}

	protected int getMoveType() {
		return STEEL; 
	}
	
	protected int getType() {
		return STEEL; 
	}
	
	protected String getName() {
		return "MAGNEMITE"; 
	}
	
	protected String getMove() {
		return "MAGNET BOMB";
	}
}
