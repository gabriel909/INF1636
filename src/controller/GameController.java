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
//		List<Casa[]> casasColoridas = tabuleiro.getCasasColoridas();
		painel.pinosCoord = tabuleiro.getPinoCoords();
		painel.casasCoord.clear();
		for(int i = 0; i < casas.size(); i++) {
			Casa casa = casas.get(i);
			painel.casasCoord.add(casa.getCoord());
		} 
		
//		if()
		
//		for(int j = 0; j < casasColoridas.size(); j++) {
//			for(int k = 0; k < casasColoridas.get(j).length; k++) {
//				
//			}
//		}
	}
	
	public void geraOrdemEquipes() {
		tabuleiro.geraDadoInicialDasEquipes();
//		tabuleiro.ordenaEquipes(3);
	}
	public String getCorEquipedaVez() {
		return tabuleiro.getEquipedaVez();
	}
	
	public int getValorDado() {
		dado =  tabuleiro.rolarDados();
		if(dado == 6) {
			if(tabuleiro.contaSeis()) {
				return 7;
			}
		}
		return dado;
	}
	
	public boolean acessaTabuleiro() {
		return tabuleiro.movimentacaoDoTerceiro6();
	}
	
	public boolean acessaTabuleiro(double x, double y) {
		Pino pino = tabuleiro.achaPino(x, y);
		boolean valorReturn;
		if(pino != null) {
//			if(pino.getCasaInical()) {
//				pino.
//			}
			valorReturn = tabuleiro.movimentaPinos(pino, dado);
			updateView();
			
			return valorReturn;
		}
		return false;
	}
	
	
	
}
