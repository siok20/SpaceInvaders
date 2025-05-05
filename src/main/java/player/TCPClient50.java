package player;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient50 {

    private String servermsj;
    public  String SERVERIP;
    public int SERVERPORT;// = 5123;//4444
    private OnMessageReceived mMessageListener = null;
    private boolean mRun = false;

    PrintWriter out;
    BufferedReader in;
    
    //int mipuerto;
    //int miip;

    public TCPClient50(OnMessageReceived listener, int puerto, String ip) {
        SERVERIP = ip;
        mMessageListener = listener;
        SERVERPORT = puerto;
    }
    public void sendMessage(String message){
        if (out != null && !out.checkError()) {
            out.println(message);
            out.flush();
        }
    }
    public void stopClient(){
        mRun = false;
    }
    public void run() {
        mRun = true;
        try {
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);
            System.out.println("TCP Client"+ "C: Conectando...");
            Socket socket = new Socket(serverAddr, SERVERPORT);
            try {
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                System.out.println("TCP Client"+ "C: Sent.");
                System.out.println("TCP Client"+ "C: Done.");
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (mRun) {
                    servermsj = in.readLine();
                    if (servermsj != null && mMessageListener != null) {
                        mMessageListener.messageReceived(servermsj);
                    }
                    servermsj = null;
                }
            } catch (Exception e) {
                System.out.println("TCP"+ "S: Error"+e);

            } finally {
                socket.close();
            }
        } catch (Exception e) {
            System.out.println("TCP"+ "C: Error"+ e);
        }
    }
    public interface OnMessageReceived {
        public void messageReceived(String message);
    }
}