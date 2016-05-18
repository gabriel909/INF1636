package model;

import java.util.*;

public class Tabuleiro {
	
	private List<Casa> casas = new ArrayList<Casa>();
	private float x, y = 240;
	public Tabuleiro() {
	
		for(int i = 0; i < 52; i++) {
			
			if(i >= 0 && i < 3) {
				x = 0;
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
	
	
}
