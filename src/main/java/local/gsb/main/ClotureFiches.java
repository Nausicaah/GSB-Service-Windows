package local.gsb.main;

import local.gsb.main.data.DateManager;
import local.gsb.main.database.MysqlConnection;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ClotureFiches extends AbstractTableModel {

    //déclaration des variables
    private Map<Integer,ResultRequete> map = new HashMap<>();

    //création d'en tête
    private final String[] entetes = {"ID", "Mois"};

    //constructeur
    public ClotureFiches(){
        //Connection Mysql
        MysqlConnection gsb_frais = MysqlConnection.condb();

        Statement statement = gsb_frais.executeRaw("SELECT idvisiteur, mois FROM `fichefrais` WHERE mois = \"" + DateManager.reqaaaamm() + "\" AND idetat = \"VA\"");

        try {
            ResultSet result = statement.getResultSet();
            if (result != null) {
                int index = 0;
                while (result.next()) {
                    String idvisiteur = result.getString("idvisiteur");
                    String mois = result.getString("mois");
                    ResultRequete rr = new ResultRequete(index, idvisiteur, mois);
                    map.put(index, rr);
                    index++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int getRowCount() {
        return map.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0 :
                //ResultRequete rr = map.get(rowIndex);
                //String id = rr.getIdvisiteur();
                return map.get(rowIndex).getIdvisiteur();

            case 1 :
                return map.get(rowIndex).getMois();

            default :
                throw new IllegalArgumentException();

        }
    }

    @Override
    public String getColumnName(int columnIndex){
        return entetes[columnIndex];
    }

}
