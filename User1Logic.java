import java.io.*;
import java.net.*;

public class User1Logic {

    private ServerSocket socketListener;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public void start(int port) {
        try {
            socketListener = new ServerSocket(port);
            System.out.println("User1 waiting for User2...");
            socket = socketListener.accept();
            setupStreams();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setupStreams() {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message) {
        output.println(message);
    }

    public String readMessage() {
        try {
            return input.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
