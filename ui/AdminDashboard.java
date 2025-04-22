package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import org.bson.Document;
import com.mongodb.client.*;
import db.MongoConnection;

import static com.mongodb.client.model.Filters.*;

public class AdminDashboard extends JFrame {
    JTable table;
    DefaultTableModel model;
    JTextField nameField, typeField, addressField;

    MongoCollection<Document> accommodations;

    public AdminDashboard() {
        setTitle("Admin Dashboard - AccoHunt");
        setSize(700, 500);
        setLayout(null);
        getContentPane().setBackground(Color.DARK_GRAY);

        MongoDatabase db = MongoConnection.getDatabase();
        accommodations = db.getCollection("accommodations");

        JLabel nameLbl = new JLabel("Name:");
        nameLbl.setBounds(20, 20, 80, 25);
        nameLbl.setForeground(Color.WHITE);
        add(nameLbl);

        nameField = new JTextField();
        nameField.setBounds(100, 20, 150, 25);
        add(nameField);

        JLabel typeLbl = new JLabel("Type:");
        typeLbl.setBounds(270, 20, 80, 25);
        typeLbl.setForeground(Color.WHITE);
        add(typeLbl);

        typeField = new JTextField();
        typeField.setBounds(330, 20, 150, 25);
        add(typeField);

        JLabel addrLbl = new JLabel("Address:");
        addrLbl.setBounds(20, 60, 80, 25);
        addrLbl.setForeground(Color.WHITE);
        add(addrLbl);

        addressField = new JTextField();
        addressField.setBounds(100, 60, 380, 25);
        add(addressField);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(500, 20, 80, 25);
        addBtn.setBackground(Color.CYAN);
        addBtn.setForeground(Color.BLACK);
        add(addBtn);

        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(500, 60, 80, 25);
        updateBtn.setBackground(Color.CYAN);
        updateBtn.setForeground(Color.BLACK);
        add(updateBtn);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(590, 60, 80, 25);
        deleteBtn.setBackground(Color.CYAN);
        deleteBtn.setForeground(Color.BLACK);
        add(deleteBtn);

        model = new DefaultTableModel(new String[]{"ID", "Name", "Type", "Address"}, 0);
        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 110, 650, 300);
        add(pane);

        loadAccommodations();

        addBtn.addActionListener(e -> addAccommodation());
        updateBtn.addActionListener(e -> updateAccommodation());
        deleteBtn.addActionListener(e -> deleteAccommodation());

        table.getSelectionModel().addListSelectionListener(event -> fillFields());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadAccommodations() {
        model.setRowCount(0);
        for (Document doc : accommodations.find()) {
            model.addRow(new Object[]{doc.getObjectId("_id"), doc.getString("name"), doc.getString("type"), doc.getString("address")});
        }
    }

    private void addAccommodation() {
        Document doc = new Document("name", nameField.getText())
                .append("type", typeField.getText())
                .append("address", addressField.getText());
        accommodations.insertOne(doc);
        loadAccommodations();
    }

    private void updateAccommodation() {
        int row = table.getSelectedRow();
        if (row == -1) return;
        Object id = model.getValueAt(row, 0);
        accommodations.updateOne(eq("_id", id),
                new Document("$set", new Document("name", nameField.getText())
                        .append("type", typeField.getText())
                        .append("address", addressField.getText())));
        loadAccommodations();
    }

    private void deleteAccommodation() {
        int row = table.getSelectedRow();
        if (row == -1) return;
        Object id = model.getValueAt(row, 0);
        accommodations.deleteOne(eq("_id", id));
        loadAccommodations();
    }

    private void fillFields() {
        int row = table.getSelectedRow();
        if (row == -1) return;
        nameField.setText(model.getValueAt(row, 1).toString());
        typeField.setText(model.getValueAt(row, 2).toString());
        addressField.setText(model.getValueAt(row, 3).toString());
    }
}
