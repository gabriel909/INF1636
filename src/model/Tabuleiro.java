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
				if(pinos.get(j).estaCasaInicial) {
					pinoCoord.add(60);
				} else {
					pinoCoord.add(pinos.get(j).casaAtual);
				}
			}
		}
		
		return pinoCoord;
	}
	
	public int rolarDados() {
		int dado = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		return dado;
	}
	
	/* input: casa atual, casa destino e valor do dado
	 * output: true, caso haja uma barreira na casa destino ou
	 * no caminho entre a casa atual e a casa destino e false, caso contrário.
	 */
	private boolean checaBarreira(int casaDestino, int valorDado, int casaAtual) {
		if(casas.get(casaDestino).getBarreira()) {
			return true;
			
		} else {
			for(int i = casaAtual; i <= valorDado; i++) {
				if (casas.get(i).getBarreira()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/*
	 * input: cor do pino em movimento e casa destino
	 * output: false, caso não existam pinos inimigos na casaDestino e true, caso contrário
	 */
	private boolean checaInimigo(Cor cor, Casa casaDestino) {
		List<Pino> pinosCasaDestino = casaDestino.getPinos();
		if(pinosCasaDestino.isEmpty() || pinosCasaDestino.get(0).getCor() == cor) {
			return false;
		} else {
			return true;
		}
	}
	
	/*
	 * input: casaDestino na qual há inimigo (foi checado anteriormente)
	 * output: void
	 */
	private void comePinoInimigo(Casa casaDestino) {
		List<Pino> pinosCasaDestino = casaDestino.getPinos();
		Pino pinoComido = pinosCasaDestino.get(0);
		pinosCasaDestino.clear();
		pinoComido.estaCasaInicial = true;
		
	}
	
	/*
	 * Método que movimenta os pinos
	 * 
	 * Parametros ( Pino que vai se movimentar e o valor do dado )
	 * checar se no caminho até a casa destino existe uma barreira gabriel
	 * checar se na casa destino tem um pino adversario guilherme
	 * se tiver um pino adversario, esse pino é comido (caso tenha dois essa casa é uma barreira) guilherme
	 * se for um abrigo e tiver um pino adversário, fica parado guilherme
	 * se existir, esse pino retorna para o inicio e o pino em movimento chega na casa destino
	 * 
	 */
	public void movimentaPinos(Pino pinoEmMovimento, int valorDado) {	
		int posicaoCasaDestino = pinoEmMovimento.casaAtual + valorDado;
		
		if(posicaoCasaDestino >= 52) {
			posicaoCasaDestino -= 52;
		}
		
		Casa casaDestino = casas.get(posicaoCasaDestino);
		int casaAtual = pinoEmMovimento.casaAtual;
		Cor cor = pinoEmMovimento.getCor();
		if(!checaBarreira(posicaoCasaDestino, valorDado, casaAtual)) {
			if(checaInimigo(cor, casaDestino) && !casaDestino.getAbrigo()) {
				comePinoInimigo(casaDestino);
				casaDestino.adicionaPino(pinoEmMovimento);
				pinoEmMovimento.casaAtual = posicaoCasaDestino;
			} else {
				pinoEmMovimento.casaAtual = posicaoCasaDestino;
				casaDestino.adicionaPino(pinoEmMovimento);
			}
		}
		
	}
	
	public Pino achaPino(double x, double y) {
		Casa casa;
		Double[] coord;
		for(int i = 0; i < casas.size(); i++) {
			casa = casas.get(i);
			coord = casa.getCoord();
			if((x >= coord[0] && x<= coord[0] + 40) && (y >= coord[1] && y <= coord[1] + 40)) {
				if(casa.getPinos().isEmpty()) {
					return null;
				} else {
					return casa.getPinos().get(0);
				}
				
			}
		}
		
		return null;
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
			if(i == 3 || i == 16 || i == 29 || i == 42) {
				casa.setAbrigo();
			}
			casas.add(casa);
		}
	}
	
	private void criaCaminhoColorido() {
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
			}
			casasColoridas.add(arrayCasas);
		}
	}
	
	public void setupInicial() {
		for(int i = 0; i < equipes.size(); i++) {
			Pino pino = equipes.get(i).getPinos().get(0);
			casas.get(pino.casaAtual).adicionaPino(pino);
		}
	}
	
	//Método que retorna o Singleton
	public static Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
}
