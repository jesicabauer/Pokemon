package game;

import java.awt.Color;

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
	
	protected ImageIcon getButtonSprite(){
		ImageIcon imageIcon = new ImageIcon("pikachu-front.png");
		return imageIcon; 
	}

	
	protected int getMoveType() {
		return ELECTRIC; 
	}
	
	protected int getType() {
		return ELECTRIC; 
	}
	
	protected String getTypeName() {
		return "ELECTRIC"; 
	}
	
	protected String getName() {
		return "PIKACHU"; 
	}
	
	protected String getMove() {
		return "THUNDERSHOCK";
	}
	
	protected String getNormMove() {
		return "QUICK ATTACK"; 
	}
	
	protected Color getColor() {
		Color color = Color.decode("#f8d030");
		return color; 
	}

}
