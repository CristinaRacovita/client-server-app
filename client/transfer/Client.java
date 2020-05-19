package transfer;


import model.Prajitura;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private Socket socket = null;
    private ObjectInputStream input = null;//citeste de la server
    private DataOutputStream out = null;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Incerc sa ma conectez...");

            input = new ObjectInputStream((new BufferedInputStream(socket.getInputStream())));

            out = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<? extends Serializable> transfer(String line) {
        while (!line.equals("Over")) {
            try {
                out.writeUTF(line);
                if (line.equals("salariati")) {
                    //System.out.println("Le-am primit salariatii");
                    List<model.User> users = (List<model.User>) input.readObject();
                    /*for (model.User u : users) {
                        System.out.println(u);
                    }*/
                    return users;
                }
                else if (line.equals("conturi")) {
                    //System.out.println("Le-am primit conturile");
                    List<model.ContUser> contUsers = (List<model.ContUser>) input.readObject();
                    /*for (model.ContUser u : contUsers) {
                        System.out.println(u);
                    }*/
                    return contUsers;
                }
                else if(line.equals("angajati")){
                    //System.out.println("Am primit angajatii");
                    List<model.User> users = (List<model.User>) input.readObject();
                    /*for (model.User u : users) {
                        System.out.println(u);
                    }*/
                    return users;
                }
                else if(line.length() > 9 && line.substring(0,8).equals("stergere")){
                    //System.out.println("Stergem angajatul gt "+line);
                    return null;
                }
                else if(line.length() > 8 && line.substring(0,7).equals("salvare")){
                    //System.out.println("Adaugam angajatul gt "+line);
                    model.ContUser c = (model.ContUser) input.readObject();
                    //System.out.println(c);
                    List<model.ContUser> contUsers = new ArrayList<>();
                    contUsers.add(c);
                    return contUsers;
                }
                else if(line.length() > 9 && line.substring(0,8).equals("editeaza")){
                    //System.out.println("Editam angajatul gt "+line);
                    model.ContUser c = (model.ContUser) input.readObject();
                    //System.out.println(c);
                    List<model.ContUser> contUsers = new ArrayList<>();
                    contUsers.add(c);
                    return contUsers;
                }
                else if(line.length() > 11 && line.substring(0,10).equals("prajiturif")){
                    //System.out.println(line+"CRITERIU");
                    List<model.Prajitura> prajituri = (List<Prajitura>) input.readObject();
                    /*for(Prajitura p : prajituri){
                        System.out.println(p);
                    }*/
                    return prajituri;
                }
                else if(line.length() > 12 && line.substring(0,11).equals("prajiturimm")){
                    //System.out.println(line+"MINMAX");
                    List<model.Prajitura> prajituri = (List<Prajitura>) input.readObject();
                    /*for(Prajitura p : prajituri){
                        System.out.println(p);
                    }*/
                    return prajituri;
                }
                else if(line.length() > 14 && line.substring(0,13).equals("prajituridisp")){
                    //System.out.println(line+"DISPONIBILITATE");
                    List<model.Prajitura> prajituri = (List<Prajitura>) input.readObject();
                    /*for(Prajitura p : prajituri){
                        System.out.println(p);
                    }*/
                    return prajituri;
                }
                else if(line.length() > 10 && line.substring(0,9).equals("prajituri")){
                    //System.out.println(line+"SIMPLU");
                    List<model.Prajitura> prajituri = (List<Prajitura>) input.readObject();
                    /*for(Prajitura p : prajituri){
                        System.out.println(p);
                    }*/
                    return prajituri;
                }
                else if(line.length() > 10 && line.substring(0,9).equals("stergpraj")){
                    //System.out.print("Sterg prajitura");
                    return null;
                }
                else if(line.length() > 8 && line.substring(0,7).equals("editprj")){
                    //System.out.print("Editez prajitura");
                    return null;
                }
                else if(line.length() > 8 && line.substring(0,7).equals("salvprj")){
                    //System.out.print("Salvez prajitura");
                    return null;
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Client-ul s-a deconectat.");
        try {
            this.out.close();
            this.input.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
