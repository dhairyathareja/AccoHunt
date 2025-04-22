package db;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MongoConnection {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    // Static block to initialize MongoDB connection
    static {
        mongoClient = MongoClients.create("mongodb://localhost:27017"); // Connect to MongoDB server
        database = mongoClient.getDatabase("accohunt"); // Access 'accohunt' database (will be created if not exists)
    }

    // Method to get the database
    public static MongoDatabase getDatabase() {
        return database; // Return the database instance
    }

    // Method to ensure the database is created by inserting a document into a collection
    public static void createDatabase() {
        MongoCollection<Document> usersCollection = database.getCollection("users");

        // Insert a sample document to ensure the database is created
        Document doc = new Document("username", "admin")
                            .append("password", "admin123")
                            .append("role", "admin");
        usersCollection.insertOne(doc); // Insert the document into the 'users' collection
    }
}
