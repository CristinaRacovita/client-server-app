# client-server-app
Bakery Chain Shop Management
For this app I use:
- SQLite for store data about the employees and cakes
- Sockets for communication between server and client
- JFreeChart to create statistics of cakes and shops
- Observer Design Pattern for change the language
- Serialisation for loading and saving word in different languages

# Introduction
There are two users : the common employee and the admin. The employee can access the cakes of every shops, can create charts, can saves the reports in csv, json and xml format and also can delete, insert and edit a cake from a specific shop. The admin can add/delete/edit a common employee.
Two different part:
- Client: graphic interface & controller. Every button is pushed a string will be send to server.
- Server: A specific string send a spesific data to client.
IDE: IntelliJ
Programming Language: Java

# Pre-requisites
- This app is independent of the operating system, but it needs Java.
- SQLite

# Getting Started
- Download the code.
- Open code in a Java IDE (like Eclipse or Intellij)
- Run the server-code first.
- Run the client-code.
