package game;

import java.awt.Color;

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
		ImageIcon imageIcon = new ImageIcon("abra-front.png");
		return imageIcon; 
	}

	protected int getMoveType() {
		return PSYCHIC; 
	}
	
	protected int getType() {
		return PSYCHIC; 
	}
	
	protected String getTypeName() {
		return "PSYCHIC"; 
	}
	
	protected String getName() {
		return "ABRA"; 
	}
	
	protected String getMove() {
		return "CONFUSION";
	}
	
	protected String getNormMove() {
		return "HEADBUTT"; 
	}
	
	protected Color getColor() {
		Color color = Color.decode("#f85888");
		return color; 
	}
}
