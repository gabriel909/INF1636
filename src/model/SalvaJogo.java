package model;
import java.io.*;
import java.util.List;

public class SalvaJogo {
	private static SalvaJogo salvaJogo = new SalvaJogo();
	
	private SalvaJogo() { }	

	public static SalvaJogo getSingleton() {
		return salvaJogo;
	}
	
	public void escreveArquivo(List<Pino> pinos) throws IOException {
		try {
			File file = new File("save.txt");
			FileWriter writer = new FileWriter(file);
			
			Pino pino;
			Cor cor;
			for(int i = 0; i <= pinos.size(); i++) {
				pino = pinos.get(i);
				if(i == 0 || i == 4 || i == 8 || i == 12) {
					cor = pino.getCor();
					
					switch(cor) {
					case Amarelo:
						writer.write("Amarelo\n");
					case Vermelho:
						writer.write("Vermelho\n");
					case Azul:
						writer.write("Azul\n");
					case Verde:
						writer.write("Verde\n");
					}
				}
				
				if(pino.estaCaminhoColorido()) {
					writer.write("C"+pino.casaAtual);
				}
				
				if(pino.estaCasaInicial) {
					writer.write("I\n");
				} else {
					writer.write(pino.casaAtual+"\n");
				}
				
			}
		} catch(FileNotFoundException e ) {
			System.out.println("Arquivo não Encontrado");
		}	
	}
	
	public void leArquivo()
}
