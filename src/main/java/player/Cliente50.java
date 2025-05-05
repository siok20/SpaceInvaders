package player;

import java.util.Scanner;

class Cliente50{
    TCPClient50 mTcpClient;
    Scanner sc;
//    public static void main(String[] args)  {
//        Cliente50 objcli = new Cliente50();
//        objcli.iniciar();    
//    }
    int mipuerto;
    String miip;
    ClientePlay miForm;
    public Cliente50(ClientePlay Form,int puerto,String ip){
        miForm = Form;
        mipuerto = puerto;
        miip = ip;
    }

    void iniciar() {
        new Thread(
                new Runnable() {
                    
                    @Override
                    public void run() {
                        mTcpClient = new TCPClient50(//34.57.114.221",//192.168.100.109
                                new TCPClient50.OnMessageReceived(){
                                    @Override
                                    public void messageReceived(String message){
                                        ClienteRecibe(message);                   
                                    }
                                }
                        ,mipuerto//5123
                        ,miip//"192.168.37.108"        
                        );
                        mTcpClient.run();
                    }
                }
        ).start();
        //---------------------------
       
        String salir = "n";
        sc = new Scanner(System.in);
        System.out.println("Cliente bandera 01");
        while( !salir.equals("s")){
            salir = sc.nextLine();
            ClienteEnvia(salir);
        }
        System.out.println("Cliente bandera 02");
    }
    String tmp;
    void ClienteRecibe(String llego){
        System.out.println("CLINTE50 El mensaje::" + llego);
        //String mitexto = miForm.getTextjTextArea1();
       
        //miForm.PrintTextJTextArea2(llego);
        tmp = miForm.getTextjTextArea2();
        tmp =  tmp + "\n" + llego;
        miForm.PrintTextJTextArea2(tmp);
        //miForm.PrintTextJTextArea2("aquiii 1546746");
        

    }
    void ClienteEnvia(String envia){
        if (mTcpClient != null) {
            mTcpClient.sendMessage(envia);
        }
    }

}
