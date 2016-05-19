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
	
	// Get casas
	public List<Casa> getCasas() {
		return casas;
	}
	
	public static Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
}
