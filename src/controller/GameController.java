package controller;

import view.*;
import model.*;
import java.util.*;

public class GameController {
	static Tabuleiro tabuleiro = Tabuleiro.getTabuleiro();
	Panel painel;
	Frame f;
	static int dado;
	
	public GameController(Frame f,Panel panel) {
		this.f = f;
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
		
		f.getContentPane().repaint();
	}
	
	public static int getValorDado() {
		dado =  tabuleiro.rolarDados();
		tabuleiro.movimentaPinos(dado);
		return dado;
	}
}
