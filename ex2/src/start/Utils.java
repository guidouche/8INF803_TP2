package start;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class Utils {

    public static DefaultTableModel buildTableModel(Object[][] data, String columnNames[]){
        return new DefaultTableModel(data, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
    }


    public static void openWebBrowser(String URL){
        try {
            Desktop.getDesktop().browse(new URI(URL));
        }
        catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }


    public static void openWebBrowserFromCreatureName(String _creatureName){
        try {
            Creatures _currentCreature = DataBaseInteractions.getCreatureInformations((_creatureName));
            if(_creatureName != null && _currentCreature.getUrl().length() > 0){
                openWebBrowser(_currentCreature.getUrl());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String replaceSimpleQuote(String param){
        return param.replace("'", "''");
    }
}
