package main;


import transfer.Server;

import java.sql.SQLException;

public class MainClass {
    public static void main(String[] args) throws SQLException {
        Server server = new Server(5000);
    }
}
