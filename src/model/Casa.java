package model;

import java.util.*;

public class Casa {
	
	private Double x, y;
	private List<Pino> pinos = new ArrayList<Pino>();
	public Cor cor;
//	private boolean barreira = false;
	private boolean abrigo = false;
	
	public Casa(double x, double y, Cor cor) {
		this.x = x;
		this.y = y;
		this.cor = cor;
		if((x == 40 && y == 320) || (x == 320 && y == 520) || (x == 520 && y == 240) || (x == 240 && y == 40)) {
			abrigo = true;
		} else {
			abrigo = false;
		}
	}
	
	
	
	/*
	 * Método que retorna os pinos que estão nesta casa
	*/
	public List<Pino> getPinos() {
		return pinos;
	}
	
	/*
	 * Método que adiciona um pino
	*/
	public boolean adicionaPino(Pino pino) {
		if(pinos.size() < 2) {
			if(pinos.size() == 1) {
//				barreira = true;
			}
			
			pinos.add(pino);
			
			return true;
		} else {
//			barreira = true;
			return false;
		}
	}
	
	public Double[] getCoord() {
		Double[] coord = {x,y};
		return coord;
	}
	
	public boolean getBarreira() {
		if(pinos.size() >= 2) {
			return true;
		} else {
		return false;
		}
	}
	
	public void removerPinos(Pino pino) {
		System.out.println("Qtd pinos antes: "+pinos.size());
		pinos.remove(pino);
		System.out.println("Qtd pinos: "+pinos.size());
	}
	
	public boolean getAbrigo() {
		return abrigo;		
	}
	
	public void setAbrigo() {
		abrigo = true;
	}
	

}
