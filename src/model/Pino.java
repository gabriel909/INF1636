package model;

public class Pino {
	
	private Cor cor;
	private int casaDeSaida;
	protected int casaAtual;
	protected boolean estaCasaInicial;
	
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
		// temporariamente todos os pinos estaram na casa de saida
		estaCasaInicial = false;
		casaAtual = casaDeSaida;
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
	public int getNumeroCasaDeSaida(){
		return casaDeSaida;
	}
	

}
