package controller;

import model.*;
import view.Frame;

public class LudoMain {
	public static void main(String[] args) {
		Frame f = new Frame();
		Tabuleiro tab = Tabuleiro.getTabuleiro();
		GameController controller = new GameController(tab, f.getPainel());

		controller.updateView();
		
		f.setTitle("Ludo");
		f.setVisible(true);
	}
}
