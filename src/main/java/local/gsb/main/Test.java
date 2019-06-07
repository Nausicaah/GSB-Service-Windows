package local.gsb.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private JList list1;
    private JButton button;

    public Test() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.runTimer();
            }
        });
    }


    public static void main(String[] args) {
        Test test = new Test();
        MajFiches fichefrais = new MajFiches();
        test.table1 = new JTable(fichefrais);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.getContentPane().add(test.button, BorderLayout.NORTH);
        test.getContentPane().add(new JScrollPane(test.table1), BorderLayout.SOUTH);
        test.pack();
        test.setVisible(true);
    }
}
