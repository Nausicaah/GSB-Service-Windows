package local.gsb.main.database;

import java.sql.*;

/**
 * Classe de gestion de la connexion SLQ qui
 * utilise un constructeur pour créer une connexion.
 * <p></p>
 * Méthode executeRaw(String query) : permet d'executer une requête en passant par un Statement
 * <p></p>
 * Methode mysqlClose() : permet de cloturer la connexion à la base de donnée
 */

public class MysqlConnection {

    private Connection conn = null;

    public static MysqlConnection condb(){
        MysqlConnection gsb_frais = new MysqlConnection("51.77.194.225", "gsb_java2", "userGsb", "secret");
        return gsb_frais;
    }


    /**
     * Construteur de la connexion SQL :
     * permet à l'objet de se connecter à une base SQL et
     * renvoie un message de connexion réussie.
     *
     * @param String url : url de la base sql
     * @param String database : nom de la base
     * @param String user : login de l'utilisateur
     * @param String password : mot de passe de l'utilisateur
     */
    public MysqlConnection(String url, String database, String user, String password) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + url + "/" + database + "?user=" + user + "&password=" + password);
            System.out.println("Connection réussie");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }

    /**
     * Méthode Statement (objet pour requête à mySQL) :
     * permet à l'objet d'executer une query et la retourne.
     *
     * @param String query : requête SQL
     * @return statement (boolean + infos additionnels selon la requête)
     */
    public Statement executeRaw(String query) {
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Méthode de déconnexion de la base :
     * permet de fermer la connexion SQLr eta
     * renvoi un message de déconnexion.
     */
    public void mysqlClose() {
        try {
            conn.close();
            System.out.println("Déconnection réussie");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void curseur() {
        //Connection Mysql
        MysqlConnection gsb_frais = new MysqlConnection("51.77.194.225", "gsb_java2", "userGsb", "secret");
        Statement statement = gsb_frais.executeRaw("SELECT * FROM `vehicule`");
        try {
            ResultSet result = statement.getResultSet();
            if (result != null) {
                while (result.next()) {
                    String id = result.getString("id");
                    String libelle = result.getString("libelle");
                    double montant = result.getDouble("montant");
                    System.out.println("ID = " + id + " libelle = " + libelle + " montant = " + montant);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
