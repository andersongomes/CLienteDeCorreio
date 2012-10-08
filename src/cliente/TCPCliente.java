package cliente;
/**
 *
 * @author Anderson Gomes
 */
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import utils.Recebedor;

public class TCPCliente {
    public static void main(String[] args) throws UnknownHostException,
                    IOException {
            // dispara o cliente
            // alterem esse ip de acordo com o ip do servidor que está rodando  
            new TCPCliente("192.168.0.177", 12345).executa();
    }

    private String host;
    private int porta;

    public TCPCliente(String host, int porta) {
            this.host = host;
            this.porta = porta;
    }

    private void executa() throws UnknownHostException, IOException {
            Socket cliente = new Socket(this.host, this.porta);
            System.out.println("O cliente se conectou ao servidor!");

            // thread para receber mensagens do servidor
            Recebedor r = new Recebedor(cliente.getInputStream());
            new Thread(r).start();

            // le msgs do teclado e manda pro servidor
            Scanner teclado = new Scanner(System.in);
            PrintStream saida = new PrintStream(cliente.getOutputStream());
            while (teclado.hasNextLine()) {
                    saida.println(teclado.nextLine());
            }

            saida.close();
            teclado.close();
            cliente.close();
    }

    public void enviaMsg(String text, String h, int p) throws UnknownHostException, IOException {
       
        Socket cliente = new Socket(h, p);
        
        System.out.println("O cliente se conectou ao servidor!");
        
        // le msgs do teclado e manda pro servidor
        if(cliente.isConnected()){
            DataOutputStream outToServer = new DataOutputStream(
				cliente.getOutputStream());
            //Troquei o writeBytes pelo writeUTF porque o writebytes não permitia 
            //acentuação e o utf so não aceite caracteres maiusculos acentuados
            outToServer.writeUTF(text);
        }
        
        cliente.close();
        System.out.println("Mensagem Enviada");
    }
    
    public String recebeMsg(String h, int p) throws UnknownHostException, IOException {
        Socket cliente = new Socket(h, p);
        String modifiedSentence = "";
        System.out.println("O cliente se conectou ao servidor!");
        
        // le msgs do teclado e manda pro servidor
        if(cliente.isConnected()){
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
				cliente.getInputStream()));
            while(modifiedSentence == ""){
                modifiedSentence = inFromServer.readLine();
            }
        }
        return modifiedSentence;
    }
    
    public int getPorta(){
        return porta;
    }

    public String getHost() {
        return host;
    }
}
