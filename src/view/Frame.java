package view;
import java.awt.Color;
import javax.swing.*;

public class Frame extends JFrame {
	
	Panel p = new Panel();
	
	public Frame() {

		p.setBackground(Color.WHITE);
		getContentPane().add(p);
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		getContentPane().add(new Panel());
	}
	
	// get painel
	public Panel getPainel() {
		return p;
	}

}
