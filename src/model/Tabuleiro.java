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
			
			if (i > 13 && i < 16) {
				x += 40;
			}
			
			if  (i > 15 && i < 21) {
				y -= 40;
			}
			
			if (i > 20 && i < 27) {
				x += 40;
			}
			
			if (i > 26 && i < 29) {
				y -= 40;
			}
			
			if (i > 28 && i < 34) {
				x -= 40;
			}
			
			if (i > 33 && i < 40) {
				y -= 40;
			}
			
			 if (i > 39 && i < 42) {
				 x -= 40; 
			 }
			
			 if (i > 41 && i < 47) {
				 y += 40;
			 }
			 
			 if (i > 46 && i < 52) {
				 x -= 40;
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
