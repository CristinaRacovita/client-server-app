# Bakery Chain Shop Management
For this app I used:
- SQLite to store information about the employees and cakes
- Socket communication between server and client
- JFreeChart to create statistics of cakes and shops
- Observer Design Pattern to change the app language
- Serialisation for loading and saving word in different languages
- SQLite DB

# Introduction
There are two types of users: the employee and the admin. The admin can add/delete/edit information about an employee.
The employee can:
- access the cakes of every shops
- create charts, can saves the reports in csv, json and xml format 
- delete, insert and edit a cake from a specific shop

Two different parts:
- Client - graphic interface & controller. Every button sends a string which is handled by server.
- Server - makes the operations on database based on the received string

# Pre-requisites
- IDE: IntelliJ
- Java JDK 8

# Getting Started
- Download the code.
- Open the code in a Java IDE (like Eclipse or Intellij)
- Run the server-code first.
- Run the client-code.
