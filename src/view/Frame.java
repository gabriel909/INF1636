package view;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.GameController;

public class Frame extends JFrame {
	
	Panel p = new Panel();
	JButton b1, b2;
	JLabel label, label2;
	GameController controller = new GameController(p);
	boolean flagDado = false;
	boolean inicioJogo = true;
	int dado;

	
	public Frame() {
		b1 = new JButton("Dado");
		b2 = new JButton("Gera ordem");
		label = new JLabel();
		label2 = new JLabel();

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!flagDado) {
					dado = controller.getValorDado();					
					if(dado == 7) {
						label.setText(Integer.toString(6));
						if(controller.acessaTabuleiro()) {
							// AVISO QUE ULTIMA PEÇA VAI VOLTAR PRA CASA INICIAL
							JOptionPane.showMessageDialog(null, "Você tirou 6 pela terceira vez, sua última peça movimentada será colocada na casa inicial.");
							controller.updateView();
							getContentPane().validate();
							getContentPane().repaint();
							String cor = controller.getCorEquipedaVez();
							label2.setText("Equipe da vez:"+cor);
						} else {
							//  AVISO QUE ULTIMA PEÇA N VAI VOLTAR PRA CASA INICIAL POIS JA ESTA NO CAMINHO COLORIDO
							JOptionPane.showMessageDialog(null, "Você tirou 6 pela terceira vez, mas sua última peça não será colocada na casa inicial pois ela já está no caminho colorido.");
							controller.updateView();
							getContentPane().validate();
							getContentPane().repaint();
							String cor = controller.getCorEquipedaVez();
							label2.setText("Equipe da vez:"+cor);
						}
					} else if(dado == 6){
						label.setText(Integer.toString(dado));
						flagDado = true;
						// AVISO QUE TIROU 6 E POR ISSO PODERÁ JOGAR MAIS UMA VEZ
						JOptionPane.showMessageDialog(null, "Você tirou 6. Por isso, poderá jogar mais uma vez e se todas as suas peças estiverem fora da casa inicial sua próxima movimentação será de 7 unidades.");
					} else {
						label.setText(Integer.toString(dado));
						flagDado = true;
					}

				}
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					controller.geraOrdemEquipes();
					String cor = controller.getCorEquipedaVez();
					label2.setText("Equipe da vez:"+cor);
					inicioJogo = false;
					b2.hide();
			}
		});
		
		p.setLayout(null);
		label.setBounds(685, 400, 50, 50);
		label.setFont(new Font("Arial", Font.PLAIN, 50));
		label2.setBounds(615,200, 175, 100);
		label2.setFont(new Font("Arial", Font.PLAIN, 15));
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
				if(flagDado && !inicioJogo) {
					if(controller.acessaTabuleiro(e.getX(), e.getY())) {
//						if()
//						controller.updateView();
						String cor = controller.getCorEquipedaVez();
						label2.setText("Equipe da vez:"+cor);
						getContentPane().validate();
						getContentPane().repaint();
						flagDado = false;
					}
				}
			}
		});
		p.add(label);
		p.add(label2);
		p.add(b1);
		p.add(b2);
		
		setSize(800, 620);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		b1.setBounds(650, 300, 100, 50);
		b2.setBounds(650, 150, 100, 50);
	}
	// get painel
	public Panel getPainel() {
		return p;
	}
}
