package servidor;
/**
 *
 * @author Anderson Gomes
 */
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import utils.TrataCliente;

public class TCPServidor {
	public static void main(String[] args) throws IOException {
            // inicia o servidor
            new TCPServidor(12345).executa();
	}

	private int porta;
	private List<PrintStream> clientes;

	public TCPServidor(int porta) {
            this.porta = porta;
            this.clientes = new ArrayList<PrintStream>();
	}

	public void executa() throws IOException {
            ServerSocket servidor = new ServerSocket(this.porta);
            System.out.println("Porta 12345 aberta!");

            while (true) {
                // aceita um cliente
                Socket cliente = servidor.accept();
                System.out.println("Nova conexão com o cliente "
                                + cliente.getInetAddress().getHostAddress());
                // adiciona saida do cliente a lista
                PrintStream ps = new PrintStream(cliente.getOutputStream());
                this.clientes.add(ps);

                // cria tratador de cliente numa nova thread
                TrataCliente tc = new TrataCliente(cliente.getInputStream(), this);
                new Thread(tc).start();

            }
	}

	public void distribuiMensagem(String msg) {
            //envia mensagem para todo mundo
            for(PrintStream cliente : this.clientes ){
                cliente.println(msg);
            }
	}

}
