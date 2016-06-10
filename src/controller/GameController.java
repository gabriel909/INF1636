package controller;

import view.*;
import model.*;
import java.util.*;

public class GameController {
	static Tabuleiro tabuleiro = Tabuleiro.getTabuleiro();
	Panel painel;
	Frame f;
	static int dado = 0;
	
	public GameController(Panel panel) {
//		this.f = f;
		this.painel = panel;
	}
	
	// get posição peça
	public void updateView() {
		List<Casa> casas = tabuleiro.getCasas();
		painel.pinosCoord = tabuleiro.getPinoCoords();
		painel.casasCoord.clear();
		
		for(int i=0; i < casas.size(); i++) {
			Casa casa = casas.get(i);
			painel.casasCoord.add(casa.getCoord());
		}
	}
	
	public int getValorDado() {
		dado =  tabuleiro.rolarDados();
		return dado;
	}
	
	public void acessaTabuleiro(double x, double y) {
		Pino pino = tabuleiro.achaPino(x, y);
		if(pino != null) {
			tabuleiro.movimentaPinos(pino, dado);
			updateView();
		}
	}
	
}
