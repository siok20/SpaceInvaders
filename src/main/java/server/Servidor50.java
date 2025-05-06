package server;

import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import spaceinvaders.Table;

public class Servidor50 {
    public Table tb = new Table();
    TCPServer50 mTcpServer;
    Scanner sc;
    int mipuerto;
    ServerPlay miForm;

    //private int contador;
    private String[][] tablero = new String[5][8];

    public Servidor50(ServerPlay Form, int puerto) {
        miForm = Form;
        mipuerto = puerto;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                tablero[i][j] = ".";
            }
        }
    }

//   public static void main(String[] args) {
//       Servidor50 objser = new Servidor50();
//       objser.iniciar();
//   }
    void iniciar() {
        new Thread(
                new Runnable() {

            @Override
            public void run() {
                mTcpServer = new TCPServer50(
                        new TCPServer50.OnMessageReceived() {
                    @Override
                    public void messageReceived(String message) {
                        ServidorRecibe(message);
                    }
                },
                        mipuerto, tablero, miForm);
                mTcpServer.run();
            }
        }
        ).start();
        //-----------------
        String salir = "n";
        sc = new Scanner(System.in);
        System.out.println("Servidor SpaceInvader inciado... Esperando jugadores");
        while (!salir.equals("s")) {
            salir = sc.nextLine();
            ServidorEnvia(salir);
        }
    }

    void ServidorRecibe(String llego) {
        System.out.println("SERVIDOR40 El mensaje:" + llego);
        //miForm.PrintTextJTextArea1(llego);
        //miForm.PrintTextJTextArea1("hfduskl");
        String mitexto = miForm.getTextjTextArea1();

        miForm.PrintTextJTextArea1("<-" + llego + "\n" + mitexto);

        
        
        miForm.PrintTextJTextArea2(mTcpServer.tb.toString());
        ServidorEnvia(mTcpServer.tb.toString());
        //JTextArea ;
        //miForm.jTextArea();
        //String = miForm.
    }

    void ServidorEnvia(String envia) {
        if (mTcpServer != null) {
            mTcpServer.sendMessageTCPServer(envia);
        }
    }
}
