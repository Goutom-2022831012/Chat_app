import javax.swing.*;
import java.awt.*;

public class User1 extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;
    private User1Logic logic;

    public User1() {
        logic = new User1Logic();
        logic.start(5000);

        setTitle("User1 Chat");
        setSize(400, 400);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);

        inputField = new JTextField();
        JButton sendButton = new JButton("Send");

        sendButton.addActionListener(e -> {
            String msg = inputField.getText();
            logic.sendMessage(msg);
            chatArea.append("User1: " + msg + "\n");
            inputField.setText("");
        });

        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(inputField, BorderLayout.CENTER);
        bottom.add(sendButton, BorderLayout.EAST);

        add(bottom, BorderLayout.SOUTH);

        new Thread(() -> {
            while (true) {
                String msg = logic.readMessage();
                if (msg != null) {
                    chatArea.append("Client: " + msg + "\n");
                }
            }
        }).start();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new User1();
    }
}
