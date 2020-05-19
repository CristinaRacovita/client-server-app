package persistenta;

import connection.ConnectionFactory;
import model.ContUser;
import model.Persoana;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersistentaUser {

    public List<User> vizualizare() {
        List<User> useri = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM salariati";

        //System.out.println(query);

        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                useri.add(new User(resultSet.getInt("id_salariat"),new Persoana(resultSet.getString("nume"), resultSet.getString("prenume"), resultSet.getInt("varsta"), resultSet.getString("data_angajarii")),resultSet.getString("functie")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return useri;
    }

    public List<User> vizualizareAngajati() {
        List<User> useri = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM salariati WHERE functie = 'angajat'";

        //System.out.println(query);

        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                useri.add(new User(resultSet.getInt("id_salariat"),new Persoana(resultSet.getString("nume"), resultSet.getString("prenume"), resultSet.getInt("varsta"), resultSet.getString("data_angajarii")),resultSet.getString("functie")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return useri;
    }

    public boolean stergere(int id) {
        boolean ok = false;
        Statement statement = null;
        //ResultSet resultSet = null;
        String query = "DELETE FROM salariati WHERE id_salariat = "+id;

        //System.out.println(query);

        Connection connection = null;

        try {
            PersistentaCont persistentaCont = new PersistentaCont();
            persistentaCont.stergere(id);
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            statement.execute(query);
            ok = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return ok;
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

    public ContUser salvare(User user) {
        boolean ok = false;
        Statement statement = null;
        //ResultSet resultSet = null;
        String query = "INSERT INTO salariati VALUES ("+(indexMaxim()+1)+",'"+user.getNume()+"','"+user.getPrenume()+"',"+user.getVarsta()+",'"+user.getDataAgajarii()+"','"+user.getFunctie()+"')";

        //System.out.println(query);
        ContUser contUser = null;
        Connection connection = null;

        try {
            PersistentaCont persistentaCont = new PersistentaCont();
            contUser = persistentaCont.salvare(user);
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            statement.execute(query);
            ok = true;

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
        String query = "UPDATE salariati SET nume = '"+user.getNume()+"', prenume = '"+user.getPrenume()+"', varsta = "+user.getVarsta()+", data_angajarii = '"+user.getDataAgajarii()+"', functie = '"+user.getFunctie()+"' WHERE id_salariat = " + user.getId_salariat();

        //System.out.println(query);
        ContUser contUser = null;
        Connection connection = null;

        try {
            PersistentaCont persistentaCont = new PersistentaCont();
            contUser = persistentaCont.editeaza(user);
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            statement.execute(query);

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
}


