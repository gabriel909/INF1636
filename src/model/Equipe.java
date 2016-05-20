package model;

import java.util.*;;

public class Equipe {
	
	private List<Pino> pinos;
	private Cor cor;
	private int qtdPinosCasaFinal;
	
	public Equipe(Cor cor) {
		this.cor = cor;
		criaPinos(cor);
		
	}
	
	private void criaPinos(Cor cor) {
		pinos = new ArrayList<Pino>();
		for(int i = 0; i < 4; i++) {
			Pino pino = new Pino(cor);
			pinos.add(pino);
		}
	}	

}
