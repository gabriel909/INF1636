package view;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.GameController;

public class Frame extends JFrame {
	
	Panel p = new Panel();
	
	public Frame() {
		JButton b1 = new JButton("Dado");
		JLabel label = new JLabel();
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dado = GameController.getValorDado();
	
				label.setText(Integer.toString(dado));
				System.out.println(dado);
				
//				JOptionPane.showMessageDialog(p,dado);
//				System.out.println(dado);
			}
		});
		
//		p.setBackground(Color.BLACK);
		p.setLayout(null);
		label.setBounds(685, 400, 50, 50);
		label.setFont(new Font("Arial", Font.PLAIN, 50));
		getContentPane().add(p);
		p.add(label);
		p.add(b1);
		
		setSize(800, 620);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		b1.setBounds(650, 300, 100, 50);
		
	}
	
	// get painel
	public Panel getPainel() {
		return p;
	}
}
