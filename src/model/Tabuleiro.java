package model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Tabuleiro {
	private static Tabuleiro tabuleiro = new Tabuleiro();
	
	private List<Casa> casas = new ArrayList<Casa>();
	private List<Casa[]> casasColoridas = new ArrayList<Casa[]>();
	private List<Equipe> equipes = new ArrayList<Equipe>();
	private Double x, y;
		
	private Tabuleiro() {
		criaCaminhoBranco();
		criaEquipes();
		criaCaminhoColorido();
	}
	
	// Get Casas
	public List<Casa> getCasas() {
		return casas;
	}
	
	// Get Casas Coloridas
	public List<Casa[]> getCasasColoridas() {
		return casasColoridas;
	}
	
	// Get Pinos
	public List<Integer> getPinoCoords() {
		List<Pino> pinos;
		Equipe equipe;
		List<Integer> pinoCoord = new ArrayList<Integer>();
		for(int i = 0; i < equipes.size(); i++) {
			equipe = equipes.get(i);
			pinos = equipe.getPinos();
			for(int j = 0; j < pinos.size(); j++) {
				pinoCoord.add(pinos.get(j).casaAtual);
			}
		}
		
		return pinoCoord;
	}
	
	public int rolarDados() {
		int dado = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		return dado;
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
	
	private void criaEquipes() {
		Equipe equipeAzul = new Equipe(Cor.Azul);
		Equipe equipeAmarelo = new Equipe(Cor.Amarelo);
		Equipe equipeVerde = new Equipe(Cor.Verde);
		Equipe equipeVermelho = new Equipe(Cor.Vermelho);
		equipes.add(equipeAmarelo);
		equipes.add(equipeAzul);
		equipes.add(equipeVerde);
		equipes.add(equipeVermelho);
	}
	
	private void criaCaminhoBranco() {
		x = 0.0;
		y = 240.0;
		for(int i = 0; i < 52; i++) {
			
			if(i > 0 && i < 3) {
				y += 40.0;
			}
			
			if(i > 2 && i < 8) {
				x += 40.0;
			}
			
			if(i == 8) {
				x += 40.0;
				y += 40.0;
			}
			
			if( i > 8 && i < 14 ) {
				y += 40.0;
			}
			
			if (i > 13 && i < 16) {
				x += 40.0;
			}
			
			if  (i > 15 && i < 21) {
				y -= 40.0;
			}
			
			if (i == 21) {
				x += 40.0;
				y -= 40.0;
			}
			
			if (i > 21 && i < 27) {
				x += 40;
			}
			
			if (i > 26 && i < 29) {
				y -= 40.0;
			}
			
			if (i > 28 && i < 34) {
				x -= 40.0;
			}
			
			if (i == 34) {
				x -= 40.0;
				y -= 40.0;
			}
			
			if (i > 34 && i < 40) {
				y -= 40;
			}
			
			 if (i > 39 && i < 42) {
				 x -= 40.0; 
			 }
			
			 if (i > 41 && i < 47) {
				 y += 40.0;
			 }
			 
			 if (i == 47) {
				 x -= 40.0;
				 y += 40.0;
			 }
			 
			 if (i > 47 && i < 52) {
				 x -= 40.0;
			 }
			 
			Casa casa = new Casa(x, y,Cor.Branco);
			casas.add(casa);
		}
	}
	
	private void criaCaminhoColorido(){
		Double coordAdd = 0.0, coordSub = 560.0;
		Casa[] arrayCasas = new Casa[6];
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 6; j++) {
				if(i == 0) {
					coordAdd += 40.0;
					Casa casa = new Casa(coordAdd, 280.0, Cor.Verde);
					arrayCasas[j] = casa;
				}
				
				if(i == 1) {
					coordSub -= 40.0;
					Casa casa = new Casa(280.0, coordSub, Cor.Amarelo);
					arrayCasas[j] = casa;
				}
				
				if(i == 2) {
					coordSub -= 40.0;
					Casa casa = new Casa(coordSub, 280.0, Cor.Azul);
					arrayCasas[j] = casa;
				}
				
				if(i == 3) {
					coordAdd += 40.0;
					Casa casa = new Casa(280.0, coordAdd, Cor.Vermelho);
					arrayCasas[j] = casa;
				}
				
//				System.out.println(arrayCasas[j].getCoord()[0]+" "+arrayCasas[j].getCoord()[1]);
				
			}
//			System.out.println(arrayCasas);
			casasColoridas.add(arrayCasas);
		}
	}
	
	//Método que retorna o Singleton
	public static Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
}
