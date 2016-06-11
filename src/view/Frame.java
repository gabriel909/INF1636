package view;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.GameController;

public class Frame extends JFrame {
	
	Panel p = new Panel();
	JButton b1;
	JLabel label;
	GameController controller = new GameController(p);
	boolean flagDado = false;

	
	public Frame() {
		b1 = new JButton("Dado");
		label = new JLabel();
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!flagDado) {
					int dado = controller.getValorDado();
					label.setText(Integer.toString(dado));
					flagDado = true;
					//				getContentPane().validate();
					//				getContentPane().repaint();
				}
			}
		});
		
		p.setLayout(null);
		label.setBounds(685, 400, 50, 50);
		label.setFont(new Font("Arial", Font.PLAIN, 50));
		getContentPane().add(p);
		p.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(flagDado) {
					if(controller.acessaTabuleiro(e.getX(), e.getY())) {
						controller.updateView();
						getContentPane().validate();
						getContentPane().repaint();
						flagDado = false;
					}
					
				}
			}
		
		});
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
