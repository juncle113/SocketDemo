import java.io.BufferedWriter;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class LoginServerThread implements Runnable {

    private static final String USERNAME = "yxxy";
    private static final String PASSWORD = "yxxy123";

    private Socket client;

    public LoginServerThread(Socket client) {
        this.client = client;
    }

    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
            User user = (User) objectInputStream.readObject();
            String clientIp = client.getInetAddress().getHostAddress();
            String msg = null;

            if (USERNAME.equals(user.getUsername()) && PASSWORD.equals(user.getPassword())) {
                msg = clientIp + " 登录成功";
            } else {
                msg = clientIp + " 登录失败";
            }

            BufferedWriter clientBufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            clientBufferedWriter.write(msg);

            clientBufferedWriter.close();
            objectInputStream.close();
            client.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
