package controller;

import model.Tabuleiro;
import view.Frame;

public class LudoMain {
	public static void main(String[] args) {
		Frame f = new Frame();
		Tabuleiro tab = Tabuleiro.getTabuleiro();
		GameController controller = new GameController(tab, f.getPainel());
		
		f.setTitle("Ludo");
		f.setVisible(true);
	}
}
