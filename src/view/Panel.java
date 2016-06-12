package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

//import model.Casa;

import java.awt.geom.*;
import java.util.*;

public class Panel extends JPanel {

	public List<Double[]> casasCoord = new ArrayList<Double[]>();
	public List<Integer> pinosCoord = new ArrayList<Integer>();
	public List<Double[]> casasIniciaisCood = new ArrayList<Double[]>();
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;

		fazCasaFinal(g2d);
		fazCasaInicial(g2d);
		
		// Time Amarelo
		criaEspacoPinos(50.0, 410.0, g2d);

		// Time Azul
		criaEspacoPinos(410.0, 410.0, g2d);

		// Time Verde
		criaEspacoPinos(50.0, 50.0, g2d);

		// Time Vermelho
		criaEspacoPinos(410.0, 50.0, g2d);
		
		fazCaminhoBranco(g2d);
		fazCaminhoColorido(g2d);
		fazSetaCasaSaida(g2d);
		criaPino(g2d);
	}
	
	void fazCaminhoBranco(Graphics2D g2d) {
		double size = 40.0;
		Color cor;

		for(int i = 0; i < casasCoord.size(); i++) {
			Double[] coord = casasCoord.get(i);
			cor = Color.WHITE;
			if (i == 51) {
				cor = Color.GREEN;
				
			} else if(i == 12) {
				cor = Color.YELLOW;
				
			} else if(i == 25) {
				cor = Color.BLUE;
				
			} else if(i == 38) {
				cor = Color.RED;
				
			} else if(i == 3 || i == 16 || i == 29 || i == 42) {
				cor = Color.BLACK;
			}
			
			double x = coord[0];
			double y = coord[1];
			fazRetangulo(x, y, size, size, cor, g2d);
			
		}
	}
	
	void fazCaminhoColorido(Graphics2D g2d) {
		double size = 40.0;
		
		for(int i = 0; i < 4; i++) {
			double coordAdd = 0.0, coordSub = 560.0;
			
			for(int j = 0; j < 5; j++) {
				if(i == 0) {
					coordAdd += 40.0;
					fazRetangulo(coordAdd, 280.0, size, size, Color.GREEN, g2d);
				}
				
				if(i == 1) {
					coordSub -= 40.0;
					fazRetangulo(280.0, coordSub, size, size, Color.YELLOW, g2d);
				}
				
				if(i == 2) {
					coordSub -= 40.0;
					fazRetangulo(coordSub, 280.0, size, size, Color.BLUE, g2d);
				}
				
				if(i == 3) {
					coordAdd += 40.0;
					fazRetangulo(280.0, coordAdd, size, size, Color.RED, g2d);
				}
			}
		}
	}
	
	void criaPino(Graphics2D g2d) {
		// TODO
		int j;
		Double[] coord;
		double y, x, raio = 35.0;
		
		for(int i = 0; i < pinosCoord.size(); i++) {
			j = pinosCoord.get(i);
			Color cor = null;
			
			if(i < 4) {
//				cor = Color.YELLOW.darker();
				cor = Color.RED.darker();

			}
			
			if(i > 3 && i < 8) {
//				cor = Color.BLUE.darker();
				cor = Color.GREEN.darker();
			}
			
			if(i > 7 && i < 12) {
//				cor = Color.GREEN.darker();
				cor = Color.YELLOW.darker();			}
			
			if(i > 11 && i < 16) {
//				cor = Color.RED.darker();
				cor = Color.BLUE.darker();
			}
			
			if(j != 60) {
				coord = casasCoord.get(j);
				y = coord[0] + 2.5;
				x = coord[1] + 2.5;
				
				fazCirculo(y, x, raio, raio, cor, g2d);
			} else {
				
				coord = casasIniciaisCood.get(i);
//				System.out.println("coords: "+coord[0]+" "+coord[1]+" "+"casasIniciasCoord: "+casasIniciaisCood.get(i)[0]+" "+casasIniciaisCood.get(i)[1]+" i: "+i);
				y = coord[0] + 2.5;
				x = coord[1] + 2.5;
				
//				System.out.println(x+" "+y);
				
				fazCirculo(x, y, raio, raio, cor, g2d);
			}
		}
	}
	
	
	void fazRetangulo(Double left, Double top, Double larg, Double alt, Color cor, Graphics2D g2d) {
		Rectangle2D rt = new Rectangle2D.Double(left, top, larg, alt);
		g2d.setPaint(Color.BLACK);
		g2d.draw(rt);
		g2d.setPaint(cor);
		g2d.fill(rt);
	}
	
	void fazCirculo(Double left, Double top, Double larg, Double alt, Color cor, Graphics2D g2d) {
		Ellipse2D e = new Ellipse2D.Double(left, top, larg, alt);
		g2d.setPaint(Color.BLACK);
		g2d.draw(e);
		g2d.setPaint(cor);
		g2d.fill(e);
	}
	
	void criaEspacoPinos(Double origemX, Double origemY, Graphics2D g2d) {
		Double[] coord = {origemY, origemX};
		fazCirculo(coord[1], coord[0], 40.0, 40.0, Color.WHITE, g2d);
		casasIniciaisCood.add(coord);
		
		Double[] coord2 = {0.0, 0.0};
		coord2[0] = origemY;
		coord2[1] = origemX + 100;
		fazCirculo(coord2[1], coord2[0], 40.0, 40.0, Color.WHITE, g2d);
		casasIniciaisCood.add(coord2);
		
		Double[] coord3 = {0.0, 0.0};
		coord3[0] = origemY + 100;
		coord3[1] = origemX;
		fazCirculo(coord3[1], coord3[0], 40.0, 40.0, Color.WHITE, g2d);
		casasIniciaisCood.add(coord3);
		
		Double[] coord4 = {0.0, 0.0};
		coord4[0] = origemY + 100;
		coord4[1] = origemX + 100;
		fazCirculo(coord4[1], coord4[0], 40.0, 40.0, Color.WHITE, g2d);
		casasIniciaisCood.add(coord4);
	}
	
	void fazTriangulo(int[] arrayPontosX, int[] arrayPontosY, Color cor, Graphics2D g2d) {
		g2d.setPaint(Color.BLACK);
		g2d.drawPolygon(arrayPontosX, arrayPontosY, 3);
		g2d.setPaint(cor);
		g2d.fillPolygon(arrayPontosX, arrayPontosY, 3);
	}
	
	void fazSetaCasaSaida(Graphics2D g2d) {
		int[][] arrayPontos = {{75, 75, 45}, {245, 275, 260}, {325, 355, 340}, {75, 75, 45}, {525, 525, 555}, {325, 355, 340}, {245, 275, 260}, {525, 525, 555}};
		fazTriangulo(arrayPontos[0], arrayPontos[1], Color.WHITE, g2d);
		fazTriangulo(arrayPontos[2], arrayPontos[3], Color.WHITE, g2d);
		fazTriangulo(arrayPontos[4], arrayPontos[5], Color.WHITE, g2d);
		fazTriangulo(arrayPontos[6], arrayPontos[7], Color.WHITE, g2d);
	}

	void fazCasaFinal(Graphics2D g2d) {
		int[][] arrayPontosX = {{240, 240, 300}, {360, 360, 300}, {240, 360, 300}, {240, 360, 300}};
		int[][] arrayPontosY = {{240, 360, 300}, {240, 360, 300}, {360, 360, 300}, {240, 240, 300}};
		
		fazTriangulo(arrayPontosX[0], arrayPontosY[0], Color.GREEN, g2d);
		fazTriangulo(arrayPontosX[1], arrayPontosY[1], Color.BLUE, g2d);
		fazTriangulo(arrayPontosX[2], arrayPontosY[2], Color.YELLOW, g2d);
		fazTriangulo(arrayPontosX[3], arrayPontosY[3], Color.RED, g2d);
	}
	
	void fazCasaInicial(Graphics2D g2d) {
		fazRetangulo(0.0, 0.0, 240.0, 240.0, Color.GREEN, g2d);
		fazRetangulo(360.0, 0.0, 240.0, 240.0, Color.RED, g2d);
		fazRetangulo(0.0, 360.0, 240.0, 240.0, Color.YELLOW, g2d);
		fazRetangulo(360.0, 360.0, 240.0, 240.0, Color.BLUE, g2d);
	}
}
