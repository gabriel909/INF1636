package model;
import java.util.concurrent.ThreadLocalRandom;

public class Dado {
	public static int rolarDados() {
		int dado = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		
		return dado;
	}
}
