package persistenta;
import connection.ConnectionFactory;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersistentaCont {

    public List<ContUser> vizualizare() {

        List<ContUser> users = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM conturi";
        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                users.add(new ContUser(resultSet.getInt("id_cont"),resultSet.getString("username"),resultSet.getString("parola")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return users;
    }

    public boolean stergere(int id) {
        PreparedStatement statement = null;
        String query = "DELETE FROM conturi WHERE id_cont =" + id;
        //System.out.println(query);
        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return false;
    }

    public String generareParola(){
        int leftlimit = 97; //a
        int rightlimit = 122; //z
        int targetLength = 10;
        Random random = new Random();
        return random.ints(leftlimit,rightlimit+1).limit(targetLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }
    private int indexMaxim(){
        Statement statement = null;
        String query = "SELECT MAX(id_salariat) FROM salariati";
        ResultSet resultSet = null;
        //System.out.println(query);
        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;
    }

    public ContUser salvare(User user){
        Statement statement = null;
        //ResultSet resultSet = null;
        String username = user.getNume()+"-"+user.getPrenume()+"@"+user.getFunctie()+".com";
        String parola = generareParola();

        String query = "INSERT INTO conturi VALUES ("+(indexMaxim()+1)+",'"+username+"','"+parola+"')";

        //System.out.println(query);
        ContUser contUser = null;
        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            statement.execute(query);
            contUser = new ContUser(username,parola);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return contUser;
    }

    public ContUser editeaza(User user){
        Statement statement = null;
        //ResultSet resultSet = null;
        String username = user.getNume()+"-"+user.getPrenume()+"@"+user.getFunctie()+".com";
        String parola = generareParola();

        String query = "UPDATE conturi SET username = '"+username+"', parola = '"+parola+"' WHERE id_cont = " + user.getId_salariat();

        //System.out.println(query);
        ContUser contUser = null;
        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            statement.execute(query);
            contUser = new ContUser(username,parola);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return  contUser;
    }
}
