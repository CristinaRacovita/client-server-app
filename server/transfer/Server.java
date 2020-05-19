package transfer;

import model.Persoana;
import model.Prajitura;
import model.User;
import persistenta.PersistentaCont;
import persistenta.PersistentaPrajitura;
import persistenta.PersistentaUser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;

    private ObjectOutputStream outputStream = null; //ce dai spre client
    private DataInputStream in = null; //data pe care o citesti de la client

    public Server(int port){
        try {
            server = new ServerSocket(port);
            System.out.println("Serverul a pornit");

            System.out.println("Astept sa se conecteze cineva....");

            socket = server.accept();
            System.out.println("Cineva s-a conectat!");

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            outputStream = new ObjectOutputStream(socket.getOutputStream());


            String line = "";

        while(!line.equals("Over")){
            try {
                line = in.readUTF();

                if(line.equals("salariati")){
                   // System.out.println("Dau date acum...");
                    outputStream.writeObject(new PersistentaUser().vizualizare());
                }
                else if(line.equals("conturi")){
                   // System.out.println("Dau conturile acum...");
                    outputStream.writeObject(new PersistentaCont().vizualizare());
                }
                else if(line.equals("angajati")){
                    //System.out.println("Dau date acum...");
                    outputStream.writeObject(new PersistentaUser().vizualizareAngajati());
                }
                else if(line.length() > 9 && line.substring(0,8).equals("stergere")){
                    new PersistentaUser().stergere(Integer.parseInt(line.substring(8,line.length())));
                   // System.out.println("EXACT ASA TREBUIE BV "+Integer.parseInt(line.substring(8,line.length())));
                }
                else if(line.length() > 8 && line.substring(0,7).equals("salvare")){
                    String[] userData = line.substring(7,line.length()).split(",");
                    User user = new User(new Persoana(userData[0],userData[1],Integer.parseInt(userData[2]),userData[3]),"angajat");
                    outputStream.writeObject(new PersistentaUser().salvare(user));
                }
                else if(line.length() > 9 && line.substring(0,8).equals("editeaza")){
                    String[] userData = line.substring(8,line.length()).split(",");
                    User user = new User(Integer.parseInt(userData[0]),new Persoana(userData[1],userData[2],Integer.parseInt(userData[3]),userData[4]),"angajat");
                    outputStream.writeObject(new PersistentaUser().editeaza(user));
                }
                else if(line.length() > 11 && line.substring(0,10).equals("prajiturif")){
                    String[] prjData = line.substring(10,line.length()).split(",");
                    for(String s : prjData){
                        System.out.print(s+" ");
                    }
                    if(prjData[1].equals("nume")) {
                        outputStream.writeObject(new PersistentaPrajitura().vizualizare(prjData[0], prjData[2], true));
                    }else{
                        outputStream.writeObject(new PersistentaPrajitura().vizualizare(prjData[0], prjData[2], false));
                    }
                }
                else if(line.length() > 12 && line.substring(0,11).equals("prajiturimm")){
                    String[] prjData = line.substring(11,line.length()).split(",");
                    for(String s : prjData){
                        System.out.print(s+" ");
                    }
                    outputStream.writeObject(new PersistentaPrajitura().vizualizare(prjData[0],Integer.parseInt(prjData[1]),Integer.parseInt(prjData[2])));
                }
                else if(line.length() > 14 && line.substring(0,13).equals("prajituridisp")){
                    outputStream.writeObject(new PersistentaPrajitura().vizualizare(line.substring(13,line.length()),true));
                }
                else if(line.length() > 10 && line.substring(0,9).equals("prajituri")){
                    outputStream.writeObject(new PersistentaPrajitura().vizualizare(line.substring(9,line.length())));
                }
                else if(line.length() > 10 && line.substring(0,9).equals("stergpraj")){
                    new PersistentaPrajitura().stergere(Integer.parseInt(line.substring(9,line.length())));
                }
                else if(line.length() > 8 && line.substring(0,7).equals("editprj")){
                    String[] prjData = line.substring(7,line.length()).split(",");
                    boolean disp = false;
                    if(prjData[4].equals("pe stoc")){
                        disp = true;
                    }
                    //System.out.print(Integer.parseInt(prjData[1]));
                    new PersistentaPrajitura().editeaza(new Prajitura(Integer.parseInt(prjData[1]),prjData[0],prjData[2],Float.parseFloat(prjData[3]),disp,prjData[5]));
                }
                else if(line.length() > 8 && line.substring(0,7).equals("salvprj")){
                    String[] prjData = line.substring(7,line.length()).split(",");
                    boolean disp = false;
                    if(prjData[4].equals("pe stoc")){
                        disp = true;
                    }
                    new PersistentaPrajitura().salvare(new Prajitura(prjData[0],prjData[1],Float.parseFloat(prjData[2]),disp,prjData[4]));
                }


            } catch (IOException e ) {
                e.printStackTrace();
            }
        }

        System.out.println("Server-ul s-a deconectat.");

        in.close();
        outputStream.close();
        socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
