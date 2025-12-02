import java.io.*;
import java.net.*;

public class User2Logic {
    
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public void connect(String host, int port) {
        try {
            socket = new Socket(host, port);
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
