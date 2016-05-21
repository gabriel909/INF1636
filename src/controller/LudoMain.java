package controller;

import view.Frame;

public class LudoMain {
	public static void main(String[] args) {
		Frame f = new Frame();
		GameController controller = new GameController(f, f.getPainel());
		controller.updateView();
		
		f.setTitle("Ludo");
		f.setVisible(true);
	}
}
