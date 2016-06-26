package model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Tabuleiro {
	private static Tabuleiro tabuleiro = new Tabuleiro();
	
	private List<Casa> casas = new ArrayList<Casa>();
	private List<Casa[]> casasColoridas = new ArrayList<Casa[]>();
	private ArrayList<Equipe> equipes = new ArrayList<Equipe>();
	private Double x, y;
	private Cor corEquipedaVez;
	private int tirou6 = 0;
		
	private Tabuleiro() {
		criaCaminhoBranco();
		criaEquipes();
		criaCaminhoColorido();
	}
	
	public List<Equipe> getEquipes() {
		return equipes;
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
	public List<Integer[]> getPinoCoords() {
		List<Pino> pinos;
		Equipe equipe;
		List<Integer[]> pinoCoord = new ArrayList<Integer[]>();
		
		for(int i = 0; i < equipes.size(); i++) {
			equipe = equipes.get(i);
			pinos = equipe.getPinos();
			
			for(int j = 0; j < pinos.size(); j++) {
				if(pinos.get(j).estaCasaInicial) {
					Integer[] pos = {0, 0};
					pos[1] = j;
					pinoCoord.add(pos);
				} else if(pinos.get(j).estaCaminhoColorido()) {
					Integer[] pos = {0, 0};
					pos[0] = 2;
					pos[1] = pinos.get(j).casaAtual;
					pinoCoord.add(pos);
				} else {
					Integer[] pos = {0, 0};
					pos[0] = 1;
					pos[1] = pinos.get(j).casaAtual;
					pinoCoord.add(pos);
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
			System.out.println("Barreira true");
			return true;
			
		} else {
			for(int i = casaAtual; i <= valorDado; i++) {
				if (casas.get(i).getBarreira()) {
					System.out.println("Barreira true");
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
		pinoComido.qtdCasasAndadas = 0;
		
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
	public boolean movimentaPinos(Pino pinoEmMovimento, int valorDado) {
		
		int posicaoCasaDestino = pinoEmMovimento.casaAtual + valorDado;
        
        if(pinoEmMovimento.getCor() == corEquipedaVez) {
            if(posicaoCasaDestino >= 52) {
                posicaoCasaDestino -= 52;
            }
		
            if((pinoEmMovimento.qtdCasasAndadas + valorDado) < 52) {
                Casa casaDestino = casas.get(posicaoCasaDestino);
                int posicaoCasaAtual = pinoEmMovimento.casaAtual;
                Casa casaAtual = casas.get(posicaoCasaAtual);
                Cor cor = pinoEmMovimento.getCor();
			
                if(!checaBarreira(posicaoCasaDestino, valorDado, posicaoCasaAtual)) {
                    if(checaInimigo(cor, casaDestino)) {
                        if(!casaDestino.getAbrigo()) {
                            comePinoInimigo(casaDestino);
                            casaDestino.adicionaPino(pinoEmMovimento);
                            casaAtual.removerPinos(pinoEmMovimento);
                            pinoEmMovimento.casaAtual = posicaoCasaDestino;
                            pinoEmMovimento.qtdCasasAndadas += valorDado;
                            
                        } else {
                        	return false;
                        }
					
                    } else if(pinoEmMovimento.estaCasaInicial) {
                    	if(valorDado == 5) {
                    		Casa casaInicial = casas.get(pinoEmMovimento.getNumeroCasaDeSaida());
                        	casaInicial.adicionaPino(pinoEmMovimento);
                        	pinoEmMovimento.estaCasaInicial = false;
                        	pinoEmMovimento.casaAtual = pinoEmMovimento.getNumeroCasaDeSaida();
                    	} else {
                    		return false;
                    	}
                    } else {
                        pinoEmMovimento.casaAtual = posicaoCasaDestino;
                        casaDestino.adicionaPino(pinoEmMovimento);
                        casaAtual.removerPinos(pinoEmMovimento);
                        pinoEmMovimento.qtdCasasAndadas += valorDado;
                        
                    }
				
                }
            } else {
            	System.out.println("entrou");
                movimentaPinoCaminhoColorido(pinoEmMovimento, valorDado);
            }

            if(tirou6 > 0 && valorDado == 6) {
            	Equipe equipe = achaEquipePelaCorPino(pinoEmMovimento.getCor());
            	equipe.ultimoPinoMovimentado = pinoEmMovimento;
                return true;
            } else {
            	Equipe equipe = achaEquipePelaCorPino(pinoEmMovimento.getCor());
            	equipe.ultimoPinoMovimentado = pinoEmMovimento;
            	tirou6 = 0;
                trocaTurno();
                return true;
            }
        } else {
            return false;
        }
	}
	/*
	 * input: -
	 * output: Se tirou6 ficar igual a 3, retorna true, do contrário falso.
	 */
	public boolean contaSeis() {
		tirou6++;
		if(tirou6 == 3) {
			tirou6 = 0;
			return true;
		}
		return false;
	}
	
	public boolean movimentacaoDoTerceiro6() {
		Pino pino = null;
		for(Equipe equipe : equipes) {
			if(equipe.getCor() == corEquipedaVez) {
				pino = equipe.ultimoPinoMovimentado;
				break;
			}
		}
		if(!pino.estaCaminhoColorido()) {
			pino.estaCasaInicial = true;
			pino.qtdCasasAndadas = 0;
			trocaTurno();
			return true;
		} else {
			trocaTurno();
			return false;
		}
			
	}
	
	private Equipe achaEquipePelaCorPino(Cor corDoPino) {
		for(Equipe equipe : equipes) {
			if(equipe.getCor() == corEquipedaVez) {
				return equipe;
				
			}
		}
		return null;
	}
	
	public void movimentaPinoCaminhoColorido(Pino pinoEmMovimento, int valorDado) {
		int indiceEquipe = getIndiceCor(pinoEmMovimento.getCor());
		int posicaoCasaDestino = ((pinoEmMovimento.qtdCasasAndadas + valorDado) - 52);
		System.out.println(pinoEmMovimento.qtdCasasAndadas);
		System.out.println(valorDado);
		int posicaoCasaDestinoArrayPanel = posicaoCasaDestino + (indiceEquipe * 6);
		System.out.println(posicaoCasaDestinoArrayPanel);
		
		if(pinoEmMovimento.estaCaminhoColorido() && (pinoEmMovimento.qtdCasasAndadas + valorDado) == 57) {
			equipes.get(indiceEquipe).addPinoCasaFinal();
			Casa casaDestino = casasColoridas.get(indiceEquipe)[posicaoCasaDestino];
			casaDestino.adicionaPino(pinoEmMovimento);
			System.out.println("Pino casa final");
			
			
			
//			pino
			
		} else if(!pinoEmMovimento.estaCaminhoColorido()){
			System.out.println(posicaoCasaDestino);
			pinoEmMovimento.casaAtual = posicaoCasaDestinoArrayPanel;
			Casa casaDestino = casasColoridas.get(indiceEquipe)[posicaoCasaDestino];
			casaDestino.adicionaPino(pinoEmMovimento);
			
			pinoEmMovimento.qtdCasasAndadas += valorDado;
		}
		
	}

	public Pino achaPino(double x, double y) {
		Casa casa;
		Double[] coord;
		for(int i = 0; i < casas.size(); i++) {
			casa = casas.get(i);
			coord = casa.getCoord();
			if((x >= coord[0] && x<= coord[0] + 40) && (y >= coord[1] && y <= coord[1] + 40)) {
				if(!casa.getPinos().isEmpty()) {
					return casa.getPinos().get(0);
				}
			}
		}
		System.out.println(x+" "+y);
		for(int j = 0; j < casasColoridas.size(); j++) {
			Casa[] arrayCasas = casasColoridas.get(j);
			System.out.println(j);
			for(int k = 0; k < arrayCasas.length; k++) {
				casa = arrayCasas[k];
				coord = casa.getCoord();
				System.out.println(casa.cor);
				System.out.println(coord[0]+" "+coord[1]);
				if((x >= coord[0] && x<= coord[0] + 40) && (y >= coord[1] && y <= coord[1] + 40)) {
					if(casa.getPinos().isEmpty()) {
						System.out.println("pinto");
						return null;
					} else {
						System.out.println("pino");
						return casa.getPinos().get(0);
					}
				}
			}
			
			if((x >= 0.0 && x <= 240.0) && (y >= 0.0 && y <= 240.0)) {
				return equipes.get(1).getPinoCasaInicial();
			} 
			
			if((x >= 360.0 && x <= 600.0) && (y >= 0.0 && y <= 240.0)) {
				return equipes.get(0).getPinoCasaInicial();
			}
			
			if((x >= 0.0 && x <= 240.0) && (y >= 360.0 && y <= 600.0)) {
				return equipes.get(2).getPinoCasaInicial();
			}
			
			if((x >= 360.0 && x <= 600.0) && (y >= 360.0 && y <= 600.0)) {
				return equipes.get(3).getPinoCasaInicial();
			}
		}
		
		return null;
	}
	
	private void criaEquipes() {
		Equipe equipeAzul = new Equipe(Cor.Azul);
		Equipe equipeAmarelo = new Equipe(Cor.Amarelo);
		Equipe equipeVerde = new Equipe(Cor.Verde);
		Equipe equipeVermelho = new Equipe(Cor.Vermelho);
		
		equipes.add(equipeVermelho);
		equipes.add(equipeVerde);
		equipes.add(equipeAmarelo);
		equipes.add(equipeAzul);
		
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
		
		
		for(int i = 0; i < 4; i++) {
			coordAdd = 0.0;
			Casa[] arrayCasas = new Casa[6];
			for(int j = 0; j < 6; j++) {
				
			
				if(i == 0) {		
					coordAdd += 40.0;
					Casa casa = new Casa(280.0, coordAdd, Cor.Vermelho);
					arrayCasas[j] = casa;
					System.out.println(coordAdd);
				} else if(i == 1) {
					coordAdd += 40.0;
					Casa casa = new Casa(coordAdd, 280.0, Cor.Verde);
					arrayCasas[j] = casa;
					System.out.println(coordAdd);
				} else if(i == 2) {
					coordSub -= 40.0;
					Casa casa = new Casa(280.0, coordSub, Cor.Amarelo);
					arrayCasas[j] = casa;
					System.out.println(coordSub);
				} else if(i == 3) {
					coordSub += 40.0;
					Casa casa = new Casa(coordSub, 280.0, Cor.Azul);
					arrayCasas[j] = casa;
					System.out.println(coordSub);
				}
			}
			if(arrayCasas[0].cor == Cor.Amarelo) {
				coordSub -= 40;
			}
			System.out.println(arrayCasas[0].cor);
			casasColoridas.add(arrayCasas);
		}
		System.out.println(casasColoridas.size());
	}
	
	public void setupInicial() {
		for(int i = 0; i < equipes.size(); i++) {
			Pino pino = equipes.get(i).getPinos().get(0);
			casas.get(pino.casaAtual).adicionaPino(pino);
		}
	}
	
	private void dadosDiferentes(int i, int j) {
		if(equipes.get(j).dado == equipes.get(i).dado) {
			while(equipes.get(j).dado == equipes.get(i).dado) {
				equipes.get(j).dado = rolarDados();
				equipes.get(i).dado = rolarDados();
			}
		}
		if(j != 0) {
			dadosDiferentes(i, j-1);
		}
	}
	
	public void geraDadoInicialDasEquipes() {
		for(int i = 0; i < equipes.size(); i++) {
			equipes.get(i).dado = rolarDados();
			if(i != 0) {
				dadosDiferentes(i, i-1);
			}
		}
		corEquipedaVez = Cor.Verde;
		for(Equipe eq : equipes) {
			if(eq.dado > equipes.get(0).dado) {
				corEquipedaVez = eq.getCor();
			}
			System.out.println(eq.dado);
		}
	}
	
	public String getEquipedaVez() {
		String cor;
		if(corEquipedaVez == Cor.Amarelo){
			cor = "Amarelo";
			return cor;
		}
		
		if(corEquipedaVez == Cor.Azul){
			cor = "Azul";
			return cor;
		}
		
		if(corEquipedaVez == Cor.Vermelho){
			cor = "Vermelho";
			return cor;
		} 
		
		if(corEquipedaVez == Cor.Verde) {
			cor = "Verde";
			return cor;
		}
		
		return null;
	}
	
	public void trocaTurno() {
		tirou6 = 0;
		if(corEquipedaVez == Cor.Vermelho) {
			corEquipedaVez = Cor.Verde;
			
		} else if(corEquipedaVez == Cor.Verde) {
			corEquipedaVez = Cor.Amarelo;
			
		} else if(corEquipedaVez == Cor.Amarelo) {
			corEquipedaVez = Cor.Azul;
			
		} else if(corEquipedaVez == Cor.Azul) {
			corEquipedaVez = Cor.Vermelho;
		}	
	}
	
	
	//Método que retorna o Singleton
	public static Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public int getIndiceCor(Cor cor) {
		switch(cor) {
			case Vermelho:
				return 0;
			
			case Verde:
				return 1;
			
			case Amarelo:
				return 2;
			
			default:
				return 3;
		}
	}
	
	public List<Pino> getPinos() {
		List<Pino> pinos = new ArrayList<Pino>();
		
		for(int i = 0; i < equipes.size(); i++) {
			List<Pino> pinosFor = equipes.get(i).getPinos();
			
			for(int j = 0; j < pinosFor.size(); j++) {
				pinos.add(pinosFor.get(j));
			}
		}
		
		return pinos;
	}
	
	public boolean checaVencedor(Pino pino) {
		int indiceEquipe = getIndiceCor(pino.getCor());
		if(equipes.get(indiceEquipe).qtdPinosCasaFinal == 4) {
			return true;
		} 
		
		return false;
	}
	
	public List<Cor> defineColocados() {
		int[] pontos = {0,0,0,0};
		int[] pontos2 = {0,0,0,0};
		int index = 0;
		List<Cor> coresColoc = new ArrayList<Cor>();
		for(Equipe e: equipes) {
			for(Pino p: e.getPinos()) {
				index = equipes.indexOf(e);
				pontos[index] += p.qtdCasasAndadas; 
				}
		pontos2[index] = pontos[index];
		}
		Arrays.sort(pontos);
		int j = 0;
		for(int ponto: pontos2) {
			for(int i = 0; i< pontos.length; i++) {
				if(ponto == pontos[i]) {
					if(j == 0) {
						coresColoc.add(i, Cor.Vermelho);
					} else if(j == 1) {
						coresColoc.add(i, Cor.Verde);
					} else if(j == 2) {
						coresColoc.add(i, Cor.Amarelo);
					} else {
						coresColoc.add(i, Cor.Azul);
					}
				}
			}
		j++;
		}
	
	return coresColoc;
		
	}
}
