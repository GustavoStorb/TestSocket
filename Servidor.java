import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final int PORTA = 12345;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORTA);
            System.out.println("Servidor esperando conexões na porta " + PORTA + "...");
            Socket cliente1 = serverSocket.accept();
            System.out.println("Cliente 1 conectado: " + cliente1.getInetAddress().getHostAddress());
            Socket cliente2 = serverSocket.accept();
            System.out.println("Cliente 2 conectado: " + cliente2.getInetAddress().getHostAddress());
            
            BufferedReader in1 = new BufferedReader(new InputStreamReader(cliente1.getInputStream()));
            PrintWriter out1 = new PrintWriter(cliente1.getOutputStream(), true);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(cliente2.getInputStream()));
            PrintWriter out2 = new PrintWriter(cliente2.getOutputStream(), true);
            
            out1.println("Você é o Cliente 1. Aguardando o Cliente 2...");
            out2.println("Você é o Cliente 2. Aguardando o Cliente 1...");
            
            String msgCliente1, msgCliente2;
            
            do {
                msgCliente1 = in1.readLine();
                System.out.println("Cliente 1: " + msgCliente1);
                out2.println(msgCliente1);
                
                msgCliente2 = in2.readLine();
                System.out.println("Cliente 2: " + msgCliente2);
                out1.println(msgCliente2);
                
            } while (!msgCliente1.equals("!sair") && !msgCliente2.equals("!sair"));
            
            cliente1.close();
            cliente2.close();
            serverSocket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
