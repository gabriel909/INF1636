import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.geom.*;

public class Panel extends JPanel {

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
		
		int[][] arrayPontos = {{45, 45, 75}, {245, 275, 260}};
		fazTriangulo(arrayPontos[0], arrayPontos[1], Color.BLACK, g2d);
		
		int[][] arrayPontosX = {{240, 240, 300}, {360, 360, 300}, {240, 360, 300}, {240, 360, 300}};
		int[][] arrayPontosY = {{240, 360, 300}, {240, 360, 300}, {360, 360, 300}, {240, 240, 300}};
		
		fazTriangulo(arrayPontosX[0], arrayPontosY[0], Color.GREEN, g2d);
		fazTriangulo(arrayPontosX[1], arrayPontosY[1], Color.BLUE, g2d);
		fazTriangulo(arrayPontosX[2], arrayPontosY[2], Color.YELLOW, g2d);
		fazTriangulo(arrayPontosX[3], arrayPontosY[3], Color.RED, g2d);
		
		// Faz os quadrados maiores
		fazRetangulo(0.0, 0.0, 240.0, 240.0, Color.GREEN, g2d);
		fazRetangulo(360.0, 0.0, 240.0, 240.0, Color.RED, g2d);
		fazRetangulo(0.0, 360.0, 240.0, 240.0, Color.YELLOW, g2d);
		fazRetangulo(360.0, 360.0, 240.0, 240.0, Color.BLUE, g2d);
		
		// Faz os pinos
		criaPinos(50.0, 50.0, g2d);
		criaPinos(410.0, 50.0, g2d);
		criaPinos(50.0, 410.0, g2d);
		criaPinos(410.0, 410.0, g2d);
		
		// Faz casas vermelhas
		Double i;
		for (i = 0.0; i < 240; i+= 40) {
			fazRetangulo(240.0, i, 40.0, 40.0, Color.WHITE, g2d);
			
			Color cor = Color.WHITE;
			if (i >= 40) {
				cor = Color.RED;
			}
			
			fazRetangulo(280.0, i, 40.0, 40.0, cor, g2d);
			
			if (i == 40) {
				cor = Color.RED;
			} else {
				cor = Color.WHITE;
			}
			
			fazRetangulo(320.0, i, 40.0, 40.0, cor, g2d);
		}

		// Faz casas verdes
		for (i = 0.0; i < 240; i+= 40) {
			Color cor = Color.WHITE;
			
			if (i == 40) {
				cor = Color.GREEN;
				
			} else {
				cor = Color.WHITE;
			}
			
			fazRetangulo(i, 240.0, 40.0, 40.0, cor, g2d);
			
			if (i >= 40) {
				cor = Color.GREEN;
			}
			
			fazRetangulo(i, 280.0, 40.0, 40.0, cor, g2d);
			
			fazRetangulo(i, 320.0, 40.0, 40.0, Color.WHITE, g2d);
			
		}
		
		// Faz casas amarelas
		for (i = 560.0; i >= 360.0; i += - 40.0) {
			fazRetangulo(320.0, i, 40.0, 40.0, Color.WHITE, g2d);
			
			Color cor = Color.WHITE;
			if (i <= 520) {
				cor = Color.YELLOW;
			}
			
			fazRetangulo(280.0, i, 40.0, 40.0, cor, g2d);
			
			if (i == 520) {
				cor = Color.YELLOW;
			} else {
				cor = Color.WHITE;
			}
			
			fazRetangulo(240.0, i, 40.0, 40.0, cor, g2d);
		}
		
		// Faz casas azuis
		for (i = 360.0; i < 600; i+= 40) {
			Color cor = Color.WHITE;
			
			if (i == 520) {
				cor = Color.BLUE;
			} else {
				cor = Color.WHITE;
			}
			
			fazRetangulo(i, 320.0, 40.0, 40.0, cor, g2d);
			
			if (i <= 520) {
				cor = Color.BLUE;
			}
			
			fazRetangulo(i, 280.0, 40.0, 40.0, cor, g2d);
			
			fazRetangulo(i, 240.0, 40.0, 40.0, Color.WHITE, g2d);
			
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
		g2d.setPaint(Color.WHITE);
		g2d.fill(e);
	}
	
	void criaPinos(Double origemX, Double origemY, Graphics2D g2d) {
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
}
