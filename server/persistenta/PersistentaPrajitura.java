package persistenta;

import connection.ConnectionFactory;
import model.Prajitura;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersistentaPrajitura {

    public List<Prajitura> vizualizare(String numeCofetarie) {
        List<Prajitura> prajituri = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "";
        if (!numeCofetarie.equals("Toate")) {
            query = "SELECT * FROM cofetarii WHERE cofetarie = '" + numeCofetarie + "'";
        } else {
            query = "SELECT * FROM cofetarii";
        }

        //System.out.println(query

        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                boolean disp = false;
                if (resultSet.getString("disponibilitate").equals("pe stoc")) {
                    disp = true;
                }
                prajituri.add(new Prajitura(resultSet.getInt("id_prajitura"),resultSet.getString("cofetarie"), resultSet.getString("denumire"), resultSet.getFloat("pret"), disp, resultSet.getString("valabilitate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
     finally {
        ConnectionFactory.close(resultSet);
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
    }
        return prajituri;
    }

    public List<Prajitura> vizualizare(String numeCofetarie, int pretMin, int pretMax) {
        List<Prajitura> prajituri = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "";
        if (!numeCofetarie.equals("Toate")) {
            query = "SELECT * FROM cofetarii WHERE cofetarie = '" + numeCofetarie + "' AND pret >= " + pretMin + " AND pret <= " + pretMax;
        } else {
            query = "SELECT * FROM cofetarii WHERE pret >= " + pretMin + " AND pret <= " + pretMax;
        }

        //System.out.println(query);

        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                boolean disp = false;
                if (resultSet.getString("disponibilitate").equals("pe stoc")) {
                    disp = true;
                }
                prajituri.add(new Prajitura(resultSet.getInt("id_prajitura"),resultSet.getString("cofetarie"), resultSet.getString("denumire"), resultSet.getFloat("pret"), disp, resultSet.getString("valabilitate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return prajituri;
    }

    public List<Prajitura> vizualizare(String numeCofetarie, boolean disp) {
        List<Prajitura> prajituri = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "";

        if (!numeCofetarie.equals("Toate")) {
            if (disp) {
                query = "SELECT * FROM cofetarii WHERE cofetarie = '" + numeCofetarie + "' AND disponibilitate = 'pe stoc'";
            } else {
                query = "SELECT * FROM cofetarii WHERE cofetarie = '" + numeCofetarie + "' AND disponibilitate = 'stoc epuizat'";
            }
        } else {
            if (disp) {
                query = "SELECT * FROM cofetarii WHERE disponibilitate = 'pe stoc'";
            } else {
                query = "SELECT * FROM cofetarii WHERE disponibilitate = 'stoc epuizat'";
            }
        }

        //System.out.println(query);

        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                boolean disponibilitate = false;
                if (resultSet.getString("disponibilitate").equals("pe stoc")) {
                    disponibilitate = true;
                }
                prajituri.add(new Prajitura(resultSet.getInt("id_prajitura"),resultSet.getString("cofetarie"), resultSet.getString("denumire"), resultSet.getFloat("pret"), disponibilitate, resultSet.getString("valabilitate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return prajituri;
    }


    public boolean stergere(int id) {
        Statement statement = null;
        String query = "DELETE FROM cofetarii WHERE id_prajitura = '" + id +"'";

        //System.out.println(query);

        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            statement.execute(query);
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

    private int indexMaxim(){
        Statement statement = null;
        String query = "SELECT MAX(id_prajitura) FROM cofetarii";
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

    public boolean salvare(Prajitura prajitura){
        Statement statement = null;
        String disp = "stoc epuizat";
        if(prajitura.isDisponibilitateProdus()){
            disp = "pe stoc";
        }
        String query = "INSERT INTO cofetarii(id_prajitura,cofetarie,denumire,pret,disponibilitate,valabilitate) VALUES ("+(indexMaxim()+1)+",'"+prajitura.getNumeCofetarie()+"','"+prajitura.getNumePrajitura()+"',"+(int)prajitura.getPret()+",'"+disp+"','"+prajitura.getValabilitate()+"')";

        //System.out.println(query);

        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            statement.execute(query);
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

    public void editeaza(Prajitura prajitura){
        Statement statement = null;
        String disp = "stoc epuizat";
        if(prajitura.isDisponibilitateProdus()){
            disp = "pe stoc";
        }
        String query = "UPDATE cofetarii SET cofetarie = '"+prajitura.getNumeCofetarie()+"', denumire = '"+prajitura.getNumePrajitura()+"', pret = "+prajitura.getPret()+", disponibilitate = '"+disp+"', valabilitate = '"+prajitura.getValabilitate()+"' WHERE id_prajitura = " + prajitura.getId_prajitura();

        //System.out.println(query);

        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public List<Prajitura> vizualizare (String numeCofetarie,String criteriu, boolean ok){
        List<Prajitura> prajituri = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "";

        if (!numeCofetarie.equals("Toate")) {
            if (ok) { //nume
                query = "SELECT * FROM cofetarii WHERE cofetarie = '" + numeCofetarie + "' AND denumire LIKE '"+criteriu+"%'";
            } else { //valabilitate
                query = "SELECT * FROM cofetarii WHERE cofetarie = '" + numeCofetarie + "' AND valabilitate LIKE '"+criteriu+"%'";
            }
        } else {
            if (ok) { //nume
                query = "SELECT * FROM cofetarii WHERE denumire LIKE '"+criteriu+"%'";
            } else { //valabilitate
                query = "SELECT * FROM cofetarii WHERE valabilitate LIKE '"+criteriu+"%'";
            }
        }

        //System.out.println(query);

        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                boolean disponibilitate = false;
                if (resultSet.getString("disponibilitate").equals("pe stoc")) {
                    disponibilitate = true;
                }
                prajituri.add(new Prajitura(resultSet.getInt("id_prajitura"),resultSet.getString("cofetarie"), resultSet.getString("denumire"), resultSet.getFloat("pret"), disponibilitate, resultSet.getString("valabilitate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return prajituri;
    }
}
