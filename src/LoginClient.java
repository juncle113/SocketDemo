import java.io.*;
import java.net.Socket;

public class LoginClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 7777);
        OutputStream clientOutputStream = client.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientOutputStream);

        BufferedReader inputBufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("请输入用户名：");
        String username = inputBufferedReader.readLine();
        System.out.println("请输入密码：");
        String password = inputBufferedReader.readLine();

        User user = new User(username, password);
        objectOutputStream.writeObject(user);

        client.shutdownOutput();

        BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        System.out.println(clientBufferedReader.readLine());

        inputBufferedReader.close();
        objectOutputStream.close();
        clientBufferedReader.close();
        clientOutputStream.close();
        client.close();

    }
}
