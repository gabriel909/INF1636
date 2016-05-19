package controller;

import view.Panel;
import model.*;

public class GameController {
	Tabuleiro tabuleiro;
	Panel painel;
	static int dado;
	
	public GameController(Tabuleiro tab, Panel panel) {
		this.tabuleiro = tab;
		this.painel = panel;
	}
	
	// get posição peça
	
	public static int getValorDado() {
		dado =  Dado.rolarDados();
		
		return dado;
	}
}
