package controller;

import model.Pino;
import model.SalvaJogo;
import view.Frame;

import java.io.IOException;
import java.util.*;

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
