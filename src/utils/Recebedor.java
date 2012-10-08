package utils;
/**
 *
 * @author Anderson Gomes
 */
import java.io.InputStream;
import java.util.Scanner;

public class Recebedor implements Runnable {

	private InputStream servidor;

	public Recebedor(InputStream servidor) {
		this.servidor = servidor;
	}

	@Override
	public void run() {
		Scanner s = new Scanner(this.servidor);
		while (s.hasNextLine()) {
			System.out.println("Mensagem " + s.nextLine());
		}
	}

}
