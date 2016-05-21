package controller;

import view.Panel;
import model.*;
import java.util.*;

public class GameController {
	static Tabuleiro tabuleiro = Tabuleiro.getTabuleiro();
	Panel painel;
	static int dado;
	
	public GameController(Panel panel) {
		this.painel = panel;
	}
	
	// get posição peça
	public void updateView() {
		List<Casa> casas = tabuleiro.getCasas();
		painel.pinosCoord = tabuleiro.getPinoCoords();
		
		for(int i=0; i < casas.size(); i++) {
			Casa casa = casas.get(i);
			painel.casasCoord.add(casa.getCoord());
			
		}
	}
	
	public static int getValorDado() {
		dado =  tabuleiro.rolarDados();
		return dado;
	}
}
