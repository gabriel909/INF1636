package controller;

import view.Panel;
import model.*;
import java.util.*;

public class GameController {
	Tabuleiro tabuleiro;
	Panel painel;
	static int dado;
	
	public GameController(Tabuleiro tab, Panel panel) {
		this.tabuleiro = tab;
		this.painel = panel;
	}
	
	// get posição peça
	public void updateView() {
		List<Casa> casas = tabuleiro.getCasas();
		
		for(int i=0; i < casas.size(); i++) {
			Casa casa = casas.get(i);
			painel.casasCoord.add(casa.getCoord());
			
		}
	}
	
	public static int getValorDado() {
		dado =  Dado.rolarDados();
		return dado;
	}
}
