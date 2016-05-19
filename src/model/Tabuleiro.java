package model;

import java.util.*;

public class Tabuleiro {
	private static Tabuleiro tabuleiro = new Tabuleiro();
	
	private List<Casa> casas = new ArrayList<Casa>();
	private float x = 0, y = 240;
	
	private Tabuleiro() {
	
		for(int i = 0; i < 52; i++) {
			
			if(i > 0 && i < 3) {
				y += 40;
			}
			
			if(i > 2 && i < 8) {
				x += 40;
			}
			
			if(i > 7 && i < 14) {
				y += 40;
			}
			
			Casa casa = new Casa(x, y);
			casas.add(casa);
		}
	}
	
	// Get Casas
	public List<Casa> getCasas() {
		return casas;
	}
	
	/*
	 * Método que movimenta os pinos
	 * 
	 * Parametros ( Pino que vai se movimentar e o valor do dado )
	 * checar se no caminho até a casa destino existe uma barreira
	 * Se existir para na casa imediatamente antes da barreira
	 * checar se na casa destino tem um pino adversario
	 * se existir, esse pino retorna para o inicio e o pino em movimento chega na casa destino
	 * 
	 */
	public boolean movimentaPinos(Pino pinoEmMovimento, int valorDado) {
		
//		if(pinoEmMovimento.estaCasaInicial == false) {
//			int casaInicioPino = pinoEmMovimento.casaAtual, casaDestino = casas.size();
//			for(int i = casaInicioPino; i < casaDestino; i++) {
//				Casa casa = casas.get(i);
//				List<Pino> pinos = casa.getPinos();
//				if(pinos.size() == 2 || pinoEmMovimento.estaCasaInicial == true) {
//
//				}
//				if(pinos.size() == 2 || pinoEmMovimento.estaCasaInicial == true) {
//
//				}
//
//			}
//		} else {
//			Casa casa = casas.get(pinoEmMovimento.getNumeroCasaDeSaida());
//			List<Pino> pinos = casa.getPinos();
//			if(valorDado == 5) {
//				
//				
//			}
//		}
//		return false;
		
		int casaDestino = pinoEmMovimento.casaAtual + valorDado;
		boolean conseguiuMover = casas.get(casaDestino).adicionaPino(pinoEmMovimento);
		if(conseguiuMover) {
			pinoEmMovimento.casaAtual = casaDestino;
			return true;
		} else {
			return false;
		}
	
	}
	
	public static Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
}
