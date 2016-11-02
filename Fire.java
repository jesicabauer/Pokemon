package game;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Fire extends PokemonSuper {
	
	protected JLabel getPlayerSprite() {
		ImageIcon imageIcon = new ImageIcon("charmander-back.png");
	    JLabel label = new JLabel(imageIcon);
		return label; 
	}
	
	protected JLabel getPCSprite() {
		ImageIcon imageIcon = new ImageIcon("charmander-front.png");
	    JLabel label = new JLabel(imageIcon);
		return label; 
	}
	
	protected ImageIcon getButtonSprite(){
		ImageIcon imageIcon = new ImageIcon("charmander-front.png");
		return imageIcon; 
	}

	protected int getMoveType() {
		return FIRE; 
	}
	
	protected int getType() {
		return FIRE; 
	}
	
	protected String getTypeName() {
		return "FIRE"; 
	}
	
	protected String getName() {
		return "CHARMANDER"; 
	}
	
	protected String getMove() {
		return "FIRE FANG";
	}
	
	protected String getNormMove() {
		return "SCRATCH"; 
	}
	
	protected Color getColor() {
		Color color = Color.decode("#f08030");
		return color; 
	}

}
