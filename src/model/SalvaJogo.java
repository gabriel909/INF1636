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
					writer.write("C"+pino.casaAtual);
					
//					System.out.println("entrou if 2");
				}
				
				if(pino.estaCasaInicial) {
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
			char[] arrayChar = new char[200];
			Scanner s = new Scanner(new BufferedReader(reader));
			String next;
			Equipe equipe = null;
			Casa casa = null;
			Pino pino = null;
			int i = 0;
			while(s.hasNext()) {
				next = s.next();
				switch(next) {
					case "Vermelho":
						equipe = tabuleiro.getEquipes().get(0);
						break;
					case "Verde":
						equipe = tabuleiro.getEquipes().get(1);
						break;
					case "Amarelo":
						equipe = tabuleiro.getEquipes().get(2);
						break;
					case "Azul":
						equipe = tabuleiro.getEquipes().get(3);
						break;
				}
				if(next.contains("I")) {
					pino = equipe.getPinos().get(i);
					pino.estaCasaInicial = true;
					i++;
				} else if(next.contains("C")) {
					pino = equipe.getPinos().get(i);
					
				}
				
			}
			
			reader.read(arrayChar);
			
			
		} catch(FileNotFoundException e) {
			
		}
	}
}
