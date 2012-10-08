package utils;
/**
 *
 * @author Anderson Gomes
 */
import java.io.InputStream;
import java.util.Scanner;
import servidor.TCPServidor;

public class TrataCliente implements Runnable {

	private InputStream cliente;
	private TCPServidor servidor;

	public TrataCliente(InputStream cliente, TCPServidor servidor) {
            this.cliente = cliente;
            this.servidor = servidor;
	}

	@Override
	public void run() {
            Scanner s = new Scanner(this.cliente);
            while (s.hasNextLine()) {
                    servidor.distribuiMensagem(s.nextLine());
            }
            s.close();
	}

}
