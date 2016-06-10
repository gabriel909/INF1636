package controller;

import view.Frame;

public class LudoMain {
	public static void main(String[] args) {
		Frame f = new Frame();

		GameController controller = new GameController(f.getPainel());
		controller.tabuleiro.setupInicial();
		controller.updateView();
		
		f.repaint();
		
		f.setTitle("Ludo");
		f.setVisible(true);
	}
}
