package playerpaczka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerName extends JFrame implements ActionListener {
    public String playern;
    private JTextField nameField;
    private JLabel nameLabe;
    private JButton bokey;
    private JButton bcancel;
    public GameWindow gameWindow;
    /**
     * Klasa odpowiedzialna za tworzenie okna w ktorym mozna zapisac nick gracza i zapisujaca ten nick
     */
    public PlayerName(){
        Container containLabel = getContentPane();
        SpringLayout layout=new SpringLayout();
        containLabel.setLayout(layout);
        setTitle("Nazwa gracza");

        nameLabe = new JLabel("Podaj nazwe gracza: ");
        nameField=new JTextField(30);
        containLabel.add(nameLabe);
        containLabel.add(nameField);
        layout.putConstraint(SpringLayout.WEST, nameLabe,5, SpringLayout.WEST, containLabel);
        layout.putConstraint(SpringLayout.NORTH, nameLabe,5, SpringLayout.NORTH, containLabel);
        layout.putConstraint(SpringLayout.WEST, nameField,5, SpringLayout.EAST, nameLabe);
        layout.putConstraint(SpringLayout.NORTH, nameField,5, SpringLayout.NORTH, containLabel);

        bokey = new JButton("OK");
        bcancel = new JButton("Wyjscie");

        bokey.addActionListener(this);
        bcancel.addActionListener(this);

        containLabel.add(bokey);
        containLabel.add(bcancel);
        //polozenie bokey
        layout.putConstraint(SpringLayout.NORTH, bokey, 10, SpringLayout.SOUTH, nameField);
        layout.putConstraint(SpringLayout.WEST, bokey, 90, SpringLayout.WEST, containLabel);
        // polozenie bcancel
        layout.putConstraint(SpringLayout.NORTH, bcancel, 10, SpringLayout.SOUTH, nameField);
        layout.putConstraint(SpringLayout.WEST, bcancel, 75, SpringLayout.WEST, bokey);

        // Okreslanie granic okna
        layout.putConstraint(SpringLayout.EAST, containLabel,5, SpringLayout.EAST, nameField);
        layout.putConstraint(SpringLayout.SOUTH, containLabel,5, SpringLayout.SOUTH , bcancel);

        setResizable(false);
        pack();
        setVisible(true);

    }
    public String getName(){
        return nameField.getText();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == bokey) {
            /**
             * jesli nic nie zostało wpisane klawisz ok nic nie robi
             * jesli nick został wpisany przechodzimy do okna z gra
             */
            playern=nameField.getText();
            if(playern.length()!=0){
                System.out.println(playern);
                gameWindow= new GameWindow(playern);
                dispose();
            }
        }
        else if(source == bcancel) {
            dispose();
        }
    }
}
