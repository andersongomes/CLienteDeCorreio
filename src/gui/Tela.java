package gui;
/**
 *
 * @author Anderson Gomes
 */
import cliente.TCPCliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

//Ainda falta desabilitar o botão receber pra ele num possa receber mais de uma vez quando for startado
//Falta tirar o botão start
//Falta fazer com que a mensagem não seja mostradaq quando o ip enviado na mensagem não for igual ao ip de destino
//Falta fazer uma função para pegar todos os ips da rede local e colocar no combo box
/**
 *
 * @author Anderson
 */
public class Tela extends javax.swing.JFrame {
    //Classe para tornar a leitura de mensagens um processo diferente
    public class LeitorMensagens implements Runnable{

        @Override
        public void run() {
            TCPCliente cli = new TCPCliente("192.168.0.177", 12345);
            String texto = "";
            try {
                //texto = cli.recebeMsg(cli.getHost(), cli.getPorta());
                Socket cliente = new Socket(cli.getHost(), cli.getPorta());
                //String modifiedSentence = "";
                System.out.println("O cliente se conectou ao servidor!");

                // le msgs do teclado e manda pro servidor
                if(cliente.isConnected()){
                    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
                                        cliente.getInputStream()));
                    while("".equals(texto)){
                        //Recebe a string enviada pelo servidor linha por linha
                        //lê enquanto num for vazia
                        texto += inFromServer.readLine();
                        //Remove espaços em branco do começo da string
                        texto = texto.trim(); 
                        //Pegar o tamanho da string texto
                        int size = texto.length();
                        //Fluxo para tratar ip de destino
                        int i, contador, j;
                        String textoFinal = "", ipDestino = "";
                        if(size > 1){ 
                            for(i = 0; i < size; i++){
                                if("-".equals(texto.substring(i, i+1))){
                                    contador = i+1;
                                    for(j = i + 1; j < size; j++){
                                        ipDestino += texto.substring(j, j+1); 
                                    }
                                    i = size;
                                }else{
                                    textoFinal += texto.substring(i, i+1);
                                }
                            }
                        }
                       
                        //Verifica se o ip local(pega o ip da maquina) é igual ao ip escolhido para receber a mensagem
                        System.out.println(InetAddress.getLocalHost().getHostAddress());
                        if(ipDestino.equals(InetAddress.getLocalHost().getHostAddress())){
                            //Verificação para retirar os caracteres do buffer que estão
                            //aparecendo na string
                            if("#".equals(textoFinal.substring(0,1))
                                    || "%".equals(textoFinal.substring(0,1)) 
                                    || "$".equals(textoFinal.substring(0,1))
                                    || "(".equals(textoFinal.substring(0,1))
                                    || ")".equals(textoFinal.substring(0,1))
                                    || "'".equals(textoFinal.substring(0,1))
                                    || "+".equals(textoFinal.substring(0,1))
                                    || "*".equals(textoFinal.substring(0,1))
                                    || "&".equals(textoFinal.substring(0,1))
                                    || "@".equals(textoFinal.substring(0,1))
                                    || "\"".equals(textoFinal.substring(0,1))
                                    || "0".equals(textoFinal.substring(0,1))
                                    || "1".equals(textoFinal.substring(0,1))
                                    || "2".equals(textoFinal.substring(0,1))
                                    || "3".equals(textoFinal.substring(0,1))
                                    || "4".equals(textoFinal.substring(0,1))
                                    || "5".equals(textoFinal.substring(0,1))
                                    || "6".equals(textoFinal.substring(0,1))
                                    || "7".equals(textoFinal.substring(0,1))
                                    || "8".equals(textoFinal.substring(0,1))
                                    || "9".equals(textoFinal.substring(0,1))
                                    || ",".equals(textoFinal.substring(0,1))
                                    || ".".equals(textoFinal.substring(0,1))
                                    || "!".equals(textoFinal.substring(0,1))){
                                textoFinal = textoFinal.substring(1);
                            }
                            messageReceive.setText(textoFinal + "\n" + messageReceive.getText());
                        }

                        texto = "";
                        textoFinal = "";
                    }
                } 
            } catch (UnknownHostException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Tela() {
        initComponents();
        
        jLabel6.setIcon(new javax.swing.ImageIcon("images/linux.png")); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        send = new javax.swing.JButton();
        from = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        message = new javax.swing.JTextArea();
        clear = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        messageReceive = new javax.swing.JTextArea();
        receive = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        ip = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java MailClient");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setText("De:");

        jLabel2.setText("IP do destinatário:");

        jLabel4.setText("Mensagem:");

        send.setText("Enviar");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        message.setColumns(20);
        message.setRows(5);
        jScrollPane1.setViewportView(message);

        clear.setText("Limpar");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        jLabel5.setText("Mensagem Recebida:");

        messageReceive.setColumns(20);
        messageReceive.setRows(5);
        jScrollPane2.setViewportView(messageReceive);

        receive.setText("Iniciar Chat");
        receive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(344, 344, 344))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(from, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(26, 26, 26)
                                                    .addComponent(receive)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(ip, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(send)
                            .addComponent(receive)
                            .addComponent(clear)))
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        TCPCliente cli = new TCPCliente("192.168.0.177", 12345);
        String remetente = "";
        try {   
            //Tira as quebras de linha e substitui por " "
            int i, tamanho = message.getText().length();
            String textoSaida = message.getText();
            String textoFinal = "";

            for(i = 0; i < tamanho; i++){        
                if("\n".equals(textoSaida.substring(i, i+1))){
                    textoFinal += " ";
                }else{
                    textoFinal += textoSaida.substring(i, i+1);
                }
            }
            //cli.enviaMsg(from.getText() + " disse:  " + textoFinal + "-" + ip.getSelectedItem()  , cli.getHost(), cli.getPorta());
            remetente = from.getText() + " disse: ";
            //falta adicionar o tratamento para o ip vazio
            cli.enviaMsg(remetente + textoFinal + "-" + ip.getText()  , cli.getHost(), cli.getPorta());
              
        } catch (UnknownHostException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
        message.setText("");
    }//GEN-LAST:event_sendActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        //Atividade de limpar os inputs da tela
        from.setText(null);
        //to.setText(nullto);
        message.setText(null);
        messageReceive.setText(null);
        String x = tempo();
        System.out.println("Horário em que a tela foi limpa = " + x);
    }//GEN-LAST:event_clearActionPerformed

    private void receiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiveActionPerformed
        LeitorMensagens leitor = new LeitorMensagens();
        //desabilita o botão de iniciar a conversa
        receive.setVisible(false);
	new Thread(leitor).start();
    }//GEN-LAST:event_receiveActionPerformed

    public static String tempo(){  
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());  
    }  
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnknownHostException, IOException {
        
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Tela().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clear;
    private javax.swing.JTextField from;
    private javax.swing.JTextField ip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea message;
    private javax.swing.JTextArea messageReceive;
    private javax.swing.JButton receive;
    private javax.swing.JButton send;
    // End of variables declaration//GEN-END:variables
    
    //pegador de IP
    private InetAddress ipLocal;
    public void setMessageReceive(String message){
        messageReceive.setText(message);
    }
}
