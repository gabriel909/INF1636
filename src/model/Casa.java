package model;

import java.util.*;

public class Casa {
	
	private Double x, y;
	private List<Pino> pinos = new ArrayList<Pino>();
	private Cor cor;
	
	public Casa(double x, double y, Cor cor) {
		this.x = x;
		this.y = y;
		this.cor = cor;
	}
	
	
	
	/*
	 * Método que retorna os pinos que estão nesta casa
	*/
	public List<Pino> getPinos() {
		if(pinos.size() > 0) {
			return pinos;
		} else {
			return null;
		}
	}
	
	/*
	 * Método que adiciona um pino
	*/
	public boolean adicionaPino(Pino pino) {
		if(pinos.size() < 2) {
			pinos.add(pino);
			return true;
		} else {
			return false;
		}
	}
	
	public Double[] getCoord() {
		Double[] coord = {x,y};
		return coord;
	}

}
