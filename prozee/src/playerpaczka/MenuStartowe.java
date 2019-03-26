package playerpaczka;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *klasa wyswietlajaca menu poczatkowe
 */
public class MenuStartowe extends JFrame implements ActionListener {
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;

    public MenuStartowe() {

        setBackground(Color.BLACK);
        setTitle("Menu");
        setBounds(300, 200, 400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel przyciski = new JPanel();

        b1 = new JButton("Gra lokalna");
        b2 = new JButton("Gra sieciowa");
        b3 = new JButton("Wysokie wyniki");
        b4 = new JButton("Informacje");
        b5 = new JButton("Wyjscie");

        b1.setBounds(125, 30, 150,40);
        b2.setBounds(125, 100, 150,40);
        b3.setBounds(125, 170, 150,40);
        b4.setBounds(125, 240, 150,40);
        b5.setBounds(125, 310, 150,40);


        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);

        przyciski.setBackground(Color.black);
        przyciski.add(b1);
        przyciski.add(b2);
        przyciski.add(b3);
        przyciski.add(b4);
        przyciski.add(b5);
        przyciski.setLayout(null);

        add(przyciski);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == b1) {
            PlayerName nick= new PlayerName();
        }
        else if(source == b2) {
        }
        else if(source == b3) {
            HighScores2 wyniki=new HighScores2();
            ScoresWindow window=new ScoresWindow(wyniki);
        }
        else if(source == b4) {
            InstructionWindow instruction= new InstructionWindow();
        }
        else if (source== b5) {
            System.exit(0);
        }
    }
}