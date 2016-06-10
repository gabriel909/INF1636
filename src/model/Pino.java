package model;

public class Pino {
	
	private Cor cor;
	private int casaDeSaida;
	protected int casaAtual;
	protected boolean estaCasaInicial = false;
	
	public Pino(Cor cor) {
		this.cor = cor;
		if(cor == Cor.Verde) {
			casaDeSaida = 51;
		}
		if(cor == Cor.Amarelo) {
			casaDeSaida = 12;
		}		
		if(cor == Cor.Azul) {
			casaDeSaida = 25;
		}
		if(cor == Cor.Vermelho) {
			casaDeSaida = 38;
		}
	}
	
	/*
	 * Get cor
	 */
	public Cor getCor() {
		return cor;
	}
	
	/*
	 * Get numeroCasa
	 */
	public int getNumeroCasaDeSaida() {
		return casaDeSaida;
	}
}
