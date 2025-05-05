package server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer50 {

    private String message;
    int nrcli = 0;
    public static final int SERVERPORT = 5123;//4444
    private OnMessageReceived messageListener = null;
    private boolean running = false;
    TCPServerThread50[] sendclis = new TCPServerThread50[10];
    PrintWriter mOut;
    BufferedReader in;
    ServerSocket serverSocket;
    int mipuerto;
    String[][] mitabla;
    ServerPlay miForm;

    //el constructor pide una interface OnMessageReceived
    //public TCPServer50(OnMessageReceived messageListener, int puerto, String[][] tabla) {
    public TCPServer50(OnMessageReceived messageListener, int puerto, String[][] tabla, ServerPlay Form) {
        this.messageListener = messageListener;
        mipuerto = puerto;
        mitabla = tabla;
        miForm = Form;
    }

    public OnMessageReceived getMessageListener() {
        return this.messageListener;
    }

    public void sendMessageTCPServer(String message) {
        for (int i = 1; i <= nrcli; i++) {
            sendclis[i].sendMessage(message);
            System.out.println("ENVIANDO A JUGADOR " + (i));
        }
    }

    public void run() {
        running = true;
        try {
            System.out.println("TCP Server" + "S : Connecting...");
            //serverSocket = new ServerSocket(SERVERPORT);
            serverSocket = new ServerSocket(mipuerto);

            while (running) {
                Socket client = serverSocket.accept();
                System.out.println("TCP Server" + "S: Receiving...");
                nrcli++;
                System.out.println("Engendrado " + nrcli);
                //sendclis[nrcli] = new TCPServerThread50(client, this, nrcli, sendclis, mitabla);
                sendclis[nrcli] = new TCPServerThread50(client, this, nrcli, sendclis, mitabla, miForm);
                Thread t = new Thread(sendclis[nrcli]);
                t.start();
                System.out.println("Nuevo conectado:" + nrcli + " jugadores conectados");

            }

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {

        }
    }

    public TCPServerThread50[] getClients() {
        return sendclis;
    }

    public interface OnMessageReceived {

        public void messageReceived(String message);
    }
}
