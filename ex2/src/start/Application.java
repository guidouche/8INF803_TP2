package start;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class Application extends JFrame {

    private JPanel magrille1;
    private JPanel topPanel;
    private JPanel CritRecherchePanel;
    private JTextField txtFieldMinLevel ;
    private JTextField txtFieldMaxLevel;
    private JCheckBox sorcererCheckBox;
    private JPanel panelClasses;
    private JCheckBox wizardCheckBox;
    private JCheckBox clericCheckBox;
    private JCheckBox druidCheckBox;
    private JCheckBox rangerCheckBox;
    private JCheckBox alchemistCheckBox;
    private JCheckBox witchCheckBox;
    private JCheckBox summonerCheckBox;
    private JCheckBox bardCheckBox;
    private JCheckBox paladinCheckBox;
    private JCheckBox inquisitorCheckBox;
    private JCheckBox bloodragerCheckBox;
    private JCheckBox oracleCheckBox;
    private JCheckBox shamannCheckBox;
    private JCheckBox antipaladinCheckBox;
    private JCheckBox magusCheckBox;
    private JCheckBox adeptCheckBox;
    private JRadioButton orRadioButtonClasses;
    private JRadioButton andRadioButtonClasses;
    private JPanel SpellNamePanel;
    private JTextField spellNametextField;
    private JRadioButton beginWithRadioButtonSpellName;
    private JRadioButton endWithRadioButtonSpellName;
    private JRadioButton containsRadioButtonSpellName;
    private JPanel SpellTextPanel;
    private JTextField spellTextTextField;
    private JRadioButton exactPhraseRadioButtonSpellText;
    private JRadioButton keywordsRadioButtonSpellText;
    private JPanel VSMPanel;
    private JCheckBox verbalCheckBox;
    private JCheckBox somaticCheckBox;
    private JCheckBox materialCheckBox;
    private JRadioButton orRadioButtonVSM;
    private JRadioButton andRadioButtonVSM;
    private JPanel ButtonResearchPanel;
    private JButton researchButton;
    private JTable tableResult;
    static ResearchValues myResearchValues;
    private JPanel ResultPanel;
    private JLabel labeltitleCreatureList;
    private JTable tableCreatures;
    private JTextField fulltTextTextFiled;
    private JButton buttonFullText;


    public Application() {
        super("Exercice 2");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(magrille1);
        this.setResizable(false);
        this.pack();
        myResearchValues = new ResearchValues();
        createAndShowGUI();
    }


    private void createAndShowGUI() {

        Component[] tabLayouts;
        tabLayouts = this.getContentPane().getComponents();

       for (var panel : tabLayouts) {
            if(panel.getClass() == JPanel.class){
                JPanel _panel = (JPanel) panel;
                Component[] tabComponents = _panel.getComponents();
                for (var item : tabComponents) {
                    if (item.getClass() == JCheckBox.class) {
                        ((JCheckBox) item).addItemListener(
                                e -> checkBoxEvent(e, _panel));
                    }
                }
            }
        }

        String descriptionCreatureListFormated = String.format("<html><div style=\"width:%dpx;\">%s</div></html>", 190, "List of creatures that have at least one of these spells :");
        labeltitleCreatureList.setText(descriptionCreatureListFormated);



       researchButton.addActionListener(e -> buttonResearchEvent());

       buttonFullText.addActionListener(e -> buttonFullTextEvent());

       tableResult.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                int row = table.rowAtPoint(mouseEvent.getPoint());
                String value = table.getModel().getValueAt(row, 0).toString();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1 && row!=-1) {
                    displaySpellDetails(value);
                }
            }
       });


        tableCreatures.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                int row = table.rowAtPoint(mouseEvent.getPoint());
                String value = table.getModel().getValueAt(row, 0).toString();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1 && row!=-1) {
                    Utils.openWebBrowserFromCreatureName(value);
                }
            }
        });
    }

    public  void checkBoxEvent(ItemEvent e,  JPanel _panel) {
        JCheckBox _ckhBox = (JCheckBox) e.getItem();
        ParamInformation _ckhValue = new ParamInformation(_ckhBox.getText(), _ckhBox.isSelected());


        switch(_panel.getName()){

            case "PanelClasses" :
                if (myResearchValues.classesValues.contains(_ckhBox.getText())) {
                    myResearchValues.classesValues.remove(_ckhBox.getText());
                } else {
                    myResearchValues.classesValues.add(_ckhBox.getText());
                }
                break;

            case "VSMPanel" :
                if (myResearchValues.vsmValues.contains(_ckhBox.getText()) ) {
                    myResearchValues.vsmValues.remove(_ckhBox.getText());
                } else {
                    myResearchValues.vsmValues.add(_ckhBox.getText());
                }
                break;

            default:
                break;
        }

    }


    public  void buttonFullTextEvent() {
        if(fulltTextTextFiled.getText().length()>0){
            JOptionPane.showMessageDialog(null, "Caution! A full text search will not filter on any of the other parameters entered. ");
            try {
                Map<String, Spell> resultValues = DataBaseInteractions.fullTextQuery(fulltTextTextFiled.getText());
                buildTable(resultValues);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public  void buttonResearchEvent() { // RESEARCH ONLY
        fulltTextTextFiled.setText("");
       buildParameters();
    }

    private  void buildParameters(){
        //Récupération des paramètres textuels
        try {
            if(!txtFieldMinLevel.getText().equals(""))
                myResearchValues.setMinLevel(Integer.parseInt(txtFieldMinLevel.getText()));
        }catch(Exception exception){
            JOptionPane.showMessageDialog(null,
                    "Error: Please enter min spell level bigger than 0", "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        }
        try {
            if(!txtFieldMaxLevel.getText().equals(""))
                myResearchValues.setMaxLevel(Integer.parseInt(txtFieldMaxLevel.getText()));
        }catch(Exception exception){
            JOptionPane.showMessageDialog(null,
                    "Error: Please enter max spell level bigger than 0", "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        }

        myResearchValues.setSpellName(spellNametextField.getText());

        myResearchValues.setSpellText(spellTextTextField.getText());

        //Récupération des valeurs des radio buttons
        myResearchValues.setClassesmodeOr(orRadioButtonClasses.isSelected());
        myResearchValues.setClassesmodeAnd(!myResearchValues.getClassesmodeOr());


        myResearchValues.setVsmmodeOr(orRadioButtonVSM.isSelected());
        myResearchValues.setVsmmodeAnd(!myResearchValues.getVsmmodeOr());

        myResearchValues.setSpellNameBeginWith(beginWithRadioButtonSpellName.isSelected());
        myResearchValues.setSpellNameEndWith(endWithRadioButtonSpellName.isSelected());
        myResearchValues.setSpellNameContains(containsRadioButtonSpellName.isSelected());


        myResearchValues.setSpellTextExactPhrase(exactPhraseRadioButtonSpellText.isSelected());
        myResearchValues.setSpellTextKeywords(keywordsRadioButtonSpellText.isSelected());


        Map<String, Spell> resultValues = null;
        try {
            resultValues = DataBaseInteractions.listSpellQuery(buildQuery());
            buildTable(resultValues);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private String buildQuery(){
        String query ="";

        query = "Select name, level, components, creatures from spell where ";


        if(!myResearchValues.getVsmValues().isEmpty()){
            query += "(";
            String operator = myResearchValues.getVsmmodeOr() ? "OR " : "AND ";
            for(int i = 0 ; i<myResearchValues.getVsmValues().size() ; i++){
                query += " components like '" + "%" +myResearchValues.getVsmValues().get(i).substring(0, 1)+"%" + "' " +operator;

            }
            query = query.endsWith("AND ")  ? query.substring(0, query.length()-4): query;
            query = query.endsWith("OR ")  ? query.substring(0, query.length()-3): query;
            query += ") ";

        }
        query = query.endsWith(") ")  ? query += " AND " : query;

        if(!myResearchValues.getClassesValues().isEmpty()){
            query += "(";
            String operator =myResearchValues.getClassesmodeOr()  ? "OR " : "AND ";
            for(int i = 0 ; i<myResearchValues.getClassesValues().size() ; i++){
                query += " class_linked like '" + "%"
                        +myResearchValues.getClassesValues().get(i).toLowerCase()+"%" + "' " +operator;
            }
            query = query.endsWith("AND ")  ? query.substring(0, query.length()-4): query;
            query = query.endsWith("OR ")  ? query.substring(0, query.length()-3): query;
            query += ") ";

        }

        query = query.endsWith(") ")  ? query += " AND " : query;

        if(myResearchValues.getMinLevel() != 0){
            query += "( level >= " + myResearchValues.getMinLevel() + ") ";
        }

        query = query.endsWith(") ")  ? query += " AND " : query;

        if(myResearchValues.getMaxLevel() != 0){
            query += "( level <= " + myResearchValues.getMaxLevel() + ") ";
        }

        query = query.endsWith(") ")  ? query += " AND " : query;

        if(myResearchValues.getSpellName().length()>0){
            if(myResearchValues.spellNameBeginWith){
                query += "( LOWER(name)  like '"+myResearchValues.getSpellName().toLowerCase()+"%' ) ";
            }else if(myResearchValues.spellNameContains){
                query += "( LOWER(name) like '%"+myResearchValues.getSpellName().toLowerCase()+"%' ) ";
            }else if(myResearchValues.spellNameEndWith){
                query += "( LOWER(name)  like '%"+myResearchValues.getSpellName().toLowerCase()+"' ) ";
            }
        }

        query = query.endsWith(") ") ? query += " AND " : query;

        if(myResearchValues.getSpellText().length()>0){
            if(myResearchValues.spellTextExactPhrase){
                query += "( LOWER(description) like '%"+myResearchValues.getSpellText().toLowerCase()+"%' ) ";
            }else {
                String[] parts = myResearchValues.getSpellText().split(" ");
                query += "(";
                for(int i = 0 ; i<parts.length ; i++){
                    query += " LOWER(description) like '%" + parts[i] +  "%' OR ";
                }
                if(query.endsWith("OR ")){
                    query = query.substring(0, query.length()-3);
                }
                query += ") ";
            }
        }

        query = query.endsWith("AND ")  ? query.substring(0, query.length()-4): query;
        query = query.endsWith("where ")  ? query.substring(0, query.length()-6): query;

        return query;
    }



    private void buildTable(Map<String, Spell> mapData){


        String columnSpellNames[] = { "Spell Name", "Spell Level", "Components" };
        String columnCreatureNames[] = { "Creature Name"};
        ArrayList<String> creaturesName = new ArrayList<String>();
        Object[][] dataSpell = new Object[mapData.size()][3];

        int i = 0;
        for(Map.Entry<String, Spell> entry : mapData.entrySet()) {

            dataSpell[i][0] = entry.getKey();
            dataSpell[i][1] = entry.getValue().getLevel();
            dataSpell[i][2] = entry.getValue().getComponents();
            for (int j = 0; j < entry.getValue().getListCreatures().size(); j++) {
                if(!creaturesName.contains(entry.getValue().listCreatures.get(j).name)){
                    creaturesName.add(entry.getValue().listCreatures.get(j).name);
                }
            }
            i++;
        }

        Object[][] dataCreatures = new Object[creaturesName.size()][1];
        for (int j = 0; j < creaturesName.size(); j++) {
            dataCreatures[j][0] = creaturesName.get(j);
        }

        tableResult.setModel(Utils.buildTableModel(dataSpell, columnSpellNames));
        tableCreatures.setModel(Utils.buildTableModel(dataCreatures, columnCreatureNames));




    }

    private void displaySpellDetails(String spell_key){
        JFrame frame = new SpellPage(spell_key);
        frame.setVisible(true);
    }



}