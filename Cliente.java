import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PORTA = 12345;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(HOST, PORTA);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            
            String mensagem;
            do {
                System.out.println("Digite uma mensagem");
                mensagem = stdin.readLine();
                out.println(mensagem);
                String resposta = in.readLine();
                System.out.println("Resposta do servidor: " + resposta);
                
            } while (!mensagem.equals("sair"));
            
            socket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
