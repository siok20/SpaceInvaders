package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import spaceinvaders.Table;

public class TCPServerThread50 extends Thread {
    
    private Socket client;
    private TCPServer50 tcpserver;
    private int clientID;
    private boolean running = false;
    public PrintWriter mOut;
    public BufferedReader in;
    private TCPServer50.OnMessageReceived messageListener = null;
    private String message;
    TCPServerThread50[] cli_amigos;
    String[][] mitabla;
    ServerPlay miForm;

    public TCPServerThread50(Socket client_, TCPServer50 tcpserver_, int clientID_, TCPServerThread50[] cli_ami_, String[][] tabla, ServerPlay Form) {
        this.client = client_;
        this.tcpserver = tcpserver_;
        this.clientID = clientID_;
        this.cli_amigos = cli_ami_;
        this.mitabla = tabla;
        this.miForm = Form;
    }

    public void trabajen(int cli) {
        mOut.println("TRABAJAMOS [" + cli + "]...");
    }

    public void run() {
        running = true;
        try {
            try {
                boolean soycontador = false;
                mOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
                System.out.println("TCP Server" + "C: Sent.");
                messageListener = tcpserver.getMessageListener();
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                while (running) {
                    message = in.readLine();

                    if (message != null && messageListener != null) {
                        tcpserver.tb.updateFrame();
                        //////////////////////
                        if (message.contains("LEFT")) {
                            tcpserver.tb.moveStarship(clientID, "LEFT");
                        }
                        else if (message.contains("UP")) {
                            tcpserver.tb.moveStarship(clientID, "UP");
                        }
                        else if (message.contains("DOWN")) {
                            tcpserver.tb.moveStarship(clientID, "DOWN");
                        }
                        else if (message.contains("RIGHT")) {
                            tcpserver.tb.moveStarship(clientID, "RIGHT");
                        }
                        //////////////////////    
                        messageListener.messageReceived(message);
                        
                        tcpserver.sendMessageTCPServer(tcpserver.tb.toString());
                        
                    }

                    message = null;
                }
                System.out.println("RESPONSE FROM CLIENT" + "S: Received Message: '" + message + "'");
            } catch (Exception e) {
                System.out.println("TCP Server" + "S: Error" + e);
            } finally {
                client.close();
            }

        } catch (Exception e) {
            System.out.println("TCP Server" + "C: Error" + e);
        }
    }

    public void stopClient() {
        running = false;
    }

    public void sendMessage(String message) {//funcion de trabajo
        if (mOut != null && !mOut.checkError()) {
            mOut.println(message);
            mOut.flush();
        }
    }

}
