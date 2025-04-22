import db.MongoConnection;
import ui.LoginFrame;

public class Main {
    public static void main(String[] args) {
        // Initialize the MongoConnection
        MongoConnection mongoConnection = new MongoConnection();
        
        // Call the createDatabase method to ensure the database is created
        mongoConnection.createDatabase();  // This will insert a sample document into the 'users' collection
        
        // Launch the login frame (for user login or admin)
        new LoginFrame();  // This opens the login screen
    }
}
