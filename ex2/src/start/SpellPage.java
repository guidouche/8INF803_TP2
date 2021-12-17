package start;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;

public class SpellPage extends JFrame {


    private JPanel magrille1;
    private JLabel labelSpellName;
    private JLabel labelTitleClasses;
    private JLabel labelClasses;
    private JLabel labelTitleComponents;
    private JLabel labelComponents;
    private JLabel labelDescription;
    private JButton buttonOpenWeb;
    private JPanel imagePanel;
    private JLabel imagelbl;
    private JLabel labelSpellResistance;
    private JPanel CreaturesPanel;
    private JTable tableCreatures;
    private Spell currentSpell = null;

    public SpellPage(String spell_key) {
        super("Spell details : " + spell_key);
        this.setContentPane(magrille1);
        this.setResizable(false);
        this.pack();
        try {
            currentSpell = DataBaseInteractions.getSpellInformations(spell_key);
            createAndShowGUI();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void createAndShowGUI() {

        Component[] tabLayouts;
        tabLayouts = this.getContentPane().getComponents();
        labelSpellName.setText("Spell name : " + currentSpell.getName() + "   (Spell Level  : " + currentSpell.getLevel() + ")");
        String descriptionFormated = String.format("<html><div style=\"width:%dpx;\">%s</div></html>", 400, currentSpell.getDescription());
        labelDescription.setText(descriptionFormated);
        buttonOpenWeb.setText("Open web page of " + currentSpell.getName());
        labelSpellResistance.setText("Spell resistance : " + currentSpell.getSpell_resistance());
        String classLinkedFormated = String.format("<html><div style=\"width:%dpx;\">%s</div></html>", 400, currentSpell.getClass_linked());
        labelClasses.setText(classLinkedFormated);
        labelComponents.setText(currentSpell.getComponents());


        BufferedImage img = null;
        if(currentSpell.getUrlImage() != null && currentSpell.getUrlImage().length()>0){
            try {
                img = ImageIO.read(new URL(
                        currentSpell.getUrlImage()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ImageIcon icon = new ImageIcon(img);
            Image image = icon.getImage();
            Image newimg = image.getScaledInstance(252, 360,  java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            imagelbl.setIcon(icon);
            imagelbl.setText("");
        }
        else{
            imagelbl.setText("Unfortunately we don't have any picture for this spell...");
        }

        buildTable();

        buttonOpenWeb.addActionListener(e -> {
            if (currentSpell != null && currentSpell.url != null && currentSpell.getUrl().length() > 0) {
                Utils.openWebBrowser(currentSpell.getUrl());
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

    public  void buttonOpenBrowserEvent() {
        if(currentSpell !=null && currentSpell.url != null && currentSpell.getUrl().length() > 0){
            try {
                Desktop.getDesktop().browse(new URI(currentSpell.getUrl()));
            }
            catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        }

    }

    private void buildTable(){

        if(!currentSpell.listCreatures.isEmpty()){
            String columnNames[] = {"Creature Name"};
            Object[][] data = new Object[currentSpell.listCreatures.size()][1];


            for (int i = 0; i < currentSpell.listCreatures.size(); i++) {
                data[i][0] = currentSpell.listCreatures.get(i).name;
            }

            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            };

            tableCreatures.setModel(tableModel);
        }
        else{
            tableCreatures.setVisible(false);
        }


    }

}
