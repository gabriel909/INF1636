package model;
import java.io.*;
import java.util.*;

public class SalvaJogo {
	private static SalvaJogo salvaJogo = new SalvaJogo();
	
	private SalvaJogo() { }	

	public static SalvaJogo getSingleton() {
		return salvaJogo;
	}
	
	public void escreveArquivo(List<Pino> pinos) throws IOException {
		FileWriter writer = null;
		try {
			File file = new File("save.txt");
			writer = new FileWriter(file);
			
//			System.out.println(file.getAbsolutePath());
			
			Pino pino;
			Cor cor;
			for(int i = 0; i < pinos.size(); i++) {
				pino = pinos.get(i);
				if(i == 0 || i == 4 || i == 8 || i == 12) {
					cor = pino.getCor();
					
					System.out.println("entrou if"+i);
					
					switch(cor) {
					case Amarelo:
						writer.write("Amarelo\n");
						break;
					case Vermelho:
						writer.write("Vermelho\n");
						break;
					case Azul:
						writer.write("Azul\n");
						break;
					case Verde:
						writer.write("Verde\n");
						break;
					}
				} 
				
				if(pino.estaCaminhoColorido()) {
					writer.write("C\n"+pino.casaAtual+"\n");
					
//					System.out.println("entrou if 2");
				} else if(pino.estaCasaInicial) {
					writer.write("I\n");
					
//					System.out.println("entrou if 3");
				} else {
					writer.write(pino.casaAtual+"\n");
					
//					System.out.println("entrou if 4");
				}
				
			}
		} catch(IOException e) {
			System.out.println(e);
		} finally {
			if(writer != null) {
				writer.close();
			} 
		}
	}
	
	public void leArquivo(Tabuleiro tabuleiro) throws IOException{
		try {
			File file = new File("save.txt");
			FileReader reader = new FileReader(file);
			Scanner s = new Scanner(new BufferedReader(reader));
			String next;
			Equipe equipe = null;
			Casa casa = null;
			Pino pino = null;
			int i = 0;
			while(s.hasNext()) {
				next = s.next();
				System.out.println(next);
				System.out.println(i);
				if(next.equals("Vermelho")) {
					equipe = tabuleiro.getEquipes().get(0);
					System.out.println("1");
				} else if(next.equals("Verde")) {
					equipe = tabuleiro.getEquipes().get(1);

				} else if(next.equals("Amarelo")) {
					equipe = tabuleiro.getEquipes().get(2);

				} else if(next.equals("Azul")) {
					equipe = tabuleiro.getEquipes().get(3);

				} else if(next.contains("I")) {
					pino = equipe.getPinos().get(i);
					pino.estaCasaInicial = true;
					i++;
				} else if(next.contains("C")) {
					int j = s.nextInt();
					
					pino = equipe.getPinos().get(i);
					pino.casaAtual = j;
					int indice = tabuleiro.getIndiceCor(pino.getCor());
					pino.qtdCasasAndadas = j - (indice * 6) + 52;
					tabuleiro.getCasasColoridas().get(indice)[j - (indice * 6)].adicionaPino(pino);
					pino.estaCasaInicial = false;
					if(j == 5 || j == 11 || j == 17 || j == 23) {
						equipe.qtdPinosCasaFinal++;
					}
					i++;
					
				} else {
					System.out.println("5");
					int j = Integer.parseInt(next);
					pino = equipe.getPinos().get(i);
					pino.casaAtual = j;
					tabuleiro.getCasas().get(j).adicionaPino(pino);
					pino.estaCasaInicial = false;
					i++;
				}
				if(i == 4) {
					i = 0;
				}
			}
		} catch(FileNotFoundException e) {
			
		}
	}
}
