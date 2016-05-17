import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.*;

public class Frame extends JFrame {
//	JButton b1 = new JButton("Botao 1");
//	JButton b2 = new JButton("Botao 2");
	JPanel p = new JPanel();
	
	public Frame() {
//		p.add(b1);
//		p.add(b2);
		p.setBackground(Color.WHITE);
		getContentPane().add(p);
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		getContentPane().add(new Panel());
	}
}
