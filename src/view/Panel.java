package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.geom.*;
import java.util.*;

public class Panel extends JPanel {

	public List<Double[]> casasCoord = new ArrayList<Double[]>();
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;

		fazCasaFinal(g2d);
		fazCasaInicial(g2d);
		
		// Time Verde
		criaEspaçoPinos(50.0, 50.0, g2d);
		// Time Vermelho
		criaEspaçoPinos(410.0, 50.0, g2d);
		// Time Amarelo
		criaEspaçoPinos(50.0, 410.0, g2d);
		// Time Azul
		criaEspaçoPinos(410.0, 410.0, g2d);
		
		fazCaminhoBranco(g2d);

//		// Faz casas vermelhas
//		Double i;
//		for (i = 0.0; i < 240; i+= 40) {
//			fazRetangulo(240.0, i, 40.0, 40.0, Color.WHITE, g2d);
//			
//			Color cor = Color.WHITE;
//			if (i >= 40) {
//				cor = Color.RED;
//			}
//			
//			fazRetangulo(280.0, i, 40.0, 40.0, cor, g2d);
//			
//			if (i == 40) {
//				cor = Color.RED;
//			} else {
//				cor = Color.WHITE;
//			}
//			
//			fazRetangulo(320.0, i, 40.0, 40.0, cor, g2d);
//		}
//
//		// Faz casas verdes
//		for (i = 0.0; i < 240; i+= 40) {
//			Color cor = Color.WHITE;
//			
//			if (i == 40) {
//				cor = Color.GREEN;
//				
//			} else {
//				cor = Color.WHITE;
//			}
//			
//			fazRetangulo(i, 240.0, 40.0, 40.0, cor, g2d);
//			
//			if (i >= 40) {
//				cor = Color.GREEN;
//			}
//			
//			fazRetangulo(i, 280.0, 40.0, 40.0, cor, g2d);
//			
//			fazRetangulo(i, 320.0, 40.0, 40.0, Color.WHITE, g2d);
//			
//		}
//		
//		// Faz casas amarelas
//		for (i = 560.0; i >= 360.0; i += - 40.0) {
//			fazRetangulo(320.0, i, 40.0, 40.0, Color.WHITE, g2d);
//			
//			Color cor = Color.WHITE;
//			if (i <= 520) {
//				cor = Color.YELLOW;
//			}
//			
//			fazRetangulo(280.0, i, 40.0, 40.0, cor, g2d);
//			
//			if (i == 520) {
//				cor = Color.YELLOW;
//			} else {
//				cor = Color.WHITE;
//			}
//			
//			fazRetangulo(240.0, i, 40.0, 40.0, cor, g2d);
//		}
//		
//		// Faz casas azuis
//		for (i = 360.0; i < 600; i+= 40) {
//			Color cor = Color.WHITE;
//			
//			if (i == 520) {
//				cor = Color.BLUE;
//			} else {
//				cor = Color.WHITE;
//			}
//			
//			fazRetangulo(i, 320.0, 40.0, 40.0, cor, g2d);
//			
//			if (i <= 520) {
//				cor = Color.BLUE;
//			}
//			
//			fazRetangulo(i, 280.0, 40.0, 40.0, cor, g2d);
//			
//			fazRetangulo(i, 240.0, 40.0, 40.0, Color.WHITE, g2d);
//			
//		}
		
		fazSetaCasaSaida(g2d);
	}
	
	void fazCaminhoBranco(Graphics2D g2d) {
		double size = 40.0;
		Color cor;
		for(int i = 0; i < casasCoord.size(); i++) {
			cor = Color.WHITE;
			if (i == 51) {
				cor = Color.GREEN;
			} else if(i == 12) {
				cor = Color.YELLOW;
			} else if(i == 25) {
				cor = Color.BLUE;
			} else if(i == 38) {
				cor = Color.RED;
			}
			
			Double[] coord = casasCoord.get(i);
			double x = coord[0];
			double y = coord[1];
			fazRetangulo(x, y, size, size, cor, g2d);
			
		}
	}
	
	void fazPino(){
		
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
		g2d.setPaint(Color.WHITE);
		g2d.fill(e);
	}
	
	void criaEspaçoPinos(Double origemX, Double origemY, Graphics2D g2d) {
		fazCirculo(origemX, origemY, 35.0, 35.0, Color.WHITE, g2d);
		fazCirculo(origemX + 100, origemY, 35.0, 35.0, Color.WHITE, g2d);
		fazCirculo(origemX, origemY + 100, 35.0, 35.0, Color.WHITE, g2d);
		fazCirculo(origemX + 100, origemY + 100, 35.0, 35.0, Color.WHITE, g2d);
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
