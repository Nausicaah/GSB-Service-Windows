package local.gsb.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private JList list1;
    private JButton button;

    public App() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.runTimer();
            }
        });
    }


    public static void main(String[] args) {
        App app = new App();
        MajFiches fichefrais = new MajFiches();
        app.table1 = new JTable(fichefrais);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.getContentPane().add(app.button, BorderLayout.NORTH);
        app.getContentPane().add(new JScrollPane(app.table1), BorderLayout.SOUTH);
        app.pack();
        app.setVisible(true);
    }
}
