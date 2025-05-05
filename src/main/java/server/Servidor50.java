package server;

import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Servidor50 {

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
        System.out.println("Servidor bandera 01");
        while (!salir.equals("s")) {
            salir = sc.nextLine();
            ServidorEnvia(salir);
        }
        System.out.println("Servidor bandera 02");

    }

    void ServidorRecibe(String llego) {
        System.out.println("SERVIDOR40 El mensaje:" + llego);
        //miForm.PrintTextJTextArea1(llego);
        //miForm.PrintTextJTextArea1("hfduskl");
        String mitexto = miForm.getTextjTextArea1();

        miForm.PrintTextJTextArea1("<-" + llego + "\n" + mitexto);


        /* String arrayString[] = llego.split("\\s+");
        int x = Integer.parseInt(arrayString[0]);
        int y = Integer.parseInt(arrayString[1]);

        if (contador == 1) {
            tablero[x][y] = "a";
        } else if (contador == 2) {
            tablero[x][y] = "b";
        } else if (contador == 3) {
            tablero[x][y] = "c";
        } else if (contador == 4) {
            tablero[x][y] = "d";
        } else if (contador == 5) {
            tablero[x][y] = "e";
        }
         */
        String rpta = "";
        rpta = rpta + "\r\n";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(tablero[i][j]);
                //out.print(mitabla[i][j]);
                rpta = rpta + tablero[i][j];

            }
            rpta = rpta + "\r\n";
            System.out.println("");
        }
        miForm.PrintTextJTextArea2(rpta);
        ServidorEnvia(rpta);
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
