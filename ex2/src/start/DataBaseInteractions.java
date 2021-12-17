package start;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DataBaseInteractions {

    Connection conn = null;

    public static Connection dbConnector(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:exo2.db");
            System.out.println("Connection succesfull");
            return conn;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    public static Map<String, Spell> listSpellQuery(String query) throws SQLException {

        Connection conn = null;
        Map<String, Spell> resultValues = new HashMap();

        conn = DataBaseInteractions.dbConnector();
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Spell _spell = new Spell(rs.getString("name"),
                                         rs.getInt("level"),
                                         rs.getString("components"),
                                         rs.getString("creatures"));
                _spell.buildListOfCreatures();
                resultValues.put(rs.getString("name"), _spell);
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            System.out.println("Connection close");
            conn.close();
        }

        return resultValues;

    }

    public static Spell getSpellInformations(String _SpellName) throws SQLException {
        Spell _spell = new Spell();

        if(_SpellName.contains("'")){
            _SpellName = Utils.replaceSimpleQuote(_SpellName);
        }

        String query = "Select s.name, s.level, s.description, s.components, s.spell_resistance, s.class_linked, s.url as URLSpell, s.creatures, u.URL as ImageURL " +
                        " from spell s " +
                        " join ImageUrl u on LOWER(s.name) = lower(u.ID)" +
                        " where name = '"+_SpellName+"'";

        Connection conn = null;
        conn = DataBaseInteractions.dbConnector();
        try {

            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                _spell = new Spell(rs.getString("name"),
                        rs.getInt("level"),
                        rs.getString("description"),
                        rs.getString("components"),
                        rs.getInt("spell_resistance"),
                        rs.getString("class_linked"),
                        rs.getString("URLSpell"),
                        rs.getString("creatures"),
                        rs.getString("ImageURL"));
                _spell.buildListOfCreatures();
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            System.out.println("Connection close");
            conn.close();
        }

        return _spell;
    }



    public static Creatures getCreatureInformations(String _CreatureName) throws SQLException {
        Creatures _creature = new Creatures();

        if(_CreatureName.contains("'")){
            _CreatureName = Utils.replaceSimpleQuote(_CreatureName);
        }

        String query = "Select c.name,c.url" +
                " from creature c " +
                " where name = '"+_CreatureName+"'";
        Connection conn = null;
        conn = DataBaseInteractions.dbConnector();
        try {

            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                _creature = new Creatures(rs.getString("name"),
                        rs.getString("url"));
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            System.out.println("Connection close");
            conn.close();
        }

        return _creature;
    }

    public static void executeSimpleQuery(String query) throws SQLException {
        Connection conn = null;
        conn = DataBaseInteractions.dbConnector();
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            System.out.println("Connection close");
            conn.close();
        }
    }

    public static Map<String, Spell> fullTextQuery(String param) throws SQLException {
        executeSimpleQuery("DROP TABLE IF EXISTS tableFullText");
        executeSimpleQuery("CREATE VIRTUAL TABLE tableFullText\n" +
                "    USING FTS5(name, level, components,  spell_resistance, class_linked, url, description, creatures)");
        executeSimpleQuery("insert into tableFullText\n" +
                "select s.name, s.level, s.components,  s.spell_resistance, s.class_linked, s.url, s.description, s.creatures from spell s");

        Connection conn = null;
        Map<String, Spell> resultValues = new HashMap();

        conn = DataBaseInteractions.dbConnector();
        try {
            PreparedStatement pst = conn.prepareStatement(
                            "SELECT * " +
                                "FROM tableFullText " +
                                "WHERE tableFullText MATCH '"+param+"' " +
                                "ORDER BY rank ");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Spell _spell = new Spell(rs.getString("name"),
                        rs.getInt("level"),
                        rs.getString("components"),
                        rs.getString("creatures"));
                _spell.buildListOfCreatures();
                resultValues.put(rs.getString("name"), _spell);
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            System.out.println("Connection close");
            conn.close();
        }

        return resultValues;

    }
}
