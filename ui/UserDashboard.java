package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import org.bson.Document;
import com.mongodb.client.*;
import db.MongoConnection;

public class UserDashboard extends JFrame {
    JTable table;
    DefaultTableModel model;

    MongoCollection<Document> accommodations;

    public UserDashboard() {
        setTitle("User Dashboard - AccoHunt");
        setSize(600, 400);
        setLayout(null);
        getContentPane().setBackground(Color.DARK_GRAY);

        MongoDatabase db = MongoConnection.getDatabase();
        accommodations = db.getCollection("accommodations");

        model = new DefaultTableModel(new String[]{"Name", "Type", "Address"}, 0);
        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 50, 550, 280);
        add(pane);

        JButton refreshBtn = new JButton("REFRESH");
        refreshBtn.setBounds(250, 10, 100, 30);
        // Set the button's background and foreground color
        refreshBtn.setBackground(Color.BLUE);  // Set background color
        refreshBtn.setForeground(Color.WHITE); // Set text color

        add(refreshBtn);

        refreshBtn.addActionListener(e -> loadAccommodations());

        loadAccommodations();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadAccommodations() {
        model.setRowCount(0);
        for (Document doc : accommodations.find()) {
            model.addRow(new Object[]{doc.getString("name"), doc.getString("type"), doc.getString("address")});
        }
    }
}
