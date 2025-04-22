package ui;

import javax.swing.*;
import java.awt.*;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

import db.MongoConnection;
import model.User;
import util.Session;

public class LoginFrame extends JFrame {
    JTextField usernameField;
    JPasswordField passwordField;

    public LoginFrame() {
        setTitle("AccoHunt Login");
        setSize(400, 250);
        setLayout(null);
        getContentPane().setBackground(Color.DARK_GRAY);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(50, 50, 100, 30);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 180, 30);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(50, 100, 100, 30);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 180, 30);
        add(passwordField);

        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setBounds(150, 150, 100, 30);
        loginBtn.setBackground(Color.RED);
        loginBtn.setForeground(Color.CYAN);

        add(loginBtn);

        loginBtn.addActionListener(e -> login());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void login() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());

        MongoDatabase db = MongoConnection.getDatabase();
        MongoCollection<Document> users = db.getCollection("users");

        Document found = users.find(and(eq("username", user), eq("password", pass))).first();

        if (found != null) {
            Session.currentUser = new User(user, pass, found.getString("role"));
            dispose();
            if (found.getString("role").equals("admin")) {
                new AdminDashboard();
            } else {
                new UserDashboard();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials");
        }
    }
}
