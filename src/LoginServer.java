import java.io.IOException;
import java.net.ServerSocket;

public class LoginServer {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(7777);

        while (true) {
            new Thread(new LoginServerThread(server.accept())).start();
        }
    }
}
