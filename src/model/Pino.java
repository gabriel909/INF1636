package model;

public class Pino {
	
	private Cor cor;
	private int casaDeSaida;
	protected int casaAtual;
	protected boolean estaCasaInicial = false;
	protected int qtdCasasAndadas = 0;
	
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
	
	public boolean estaCaminhoColorido() {
		if(qtdCasasAndadas >= 52) {
			return true;
		} else {
			return false;
		}
	}
}
