package game;

import java.awt.Color;

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
		ImageIcon imageIcon = new ImageIcon("mankey-front.png");
		return imageIcon; 
	}
	
	protected int getMoveType() {
		return FIGHTING; 
	}
	
	protected int getType() {
		return FIGHTING; 
	}
	
	protected String getTypeName() {
		return "FIGHTING"; 
	}
	
	protected String getName() {
		return "MANKEY"; 
	}
	
	protected String getMove() {
		return "KARATE CHOP";
	}
	
	protected String getNormMove() {
		return "TAKE DOWN"; 
	}
	
	protected Color getColor() {
		Color color = Color.decode("#c03028");
		return color; 
	}

}
