import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    private final int[] lines = {8, 16, 24};
    private final int[] mines = {8, 48, 128};

    public Menu() {
        this.setTitle ("Saper - start new game");
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.setResizable (false);
        this.setPreferredSize (new Dimension (300, 400));

        this.setBackground (Color.LIGHT_GRAY);
        Board board = new Board ();
        this.add (board);
        this.setContentPane (board);
        JButton but0 = new JButton ("Beginner");
        JButton but1 = new JButton ("Intermediate");
        JButton but2 = new JButton ("Expert");
        but0.setBounds (90, 200, 120, 40);
        but1.setBounds (90, 250, 120, 40);
        but2.setBounds (90, 300, 120, 40);
        but0.addActionListener (e -> {
            new GUI (lines[0], mines[0]);
            dispose ();
        });
        but1.addActionListener (e -> {
            new GUI (lines[1], mines[1]);
            dispose ();
        });
        but2.addActionListener (e -> {
            new GUI (lines[2], mines[2]);
            dispose ();
        });
        Face titleD = new Face (50, 20, 200, 120, this, false);
        String title = "title.png";
        titleD.changeFace (title);
        this.add (but0);
        this.add (but1);
        this.add (but2);
        this.add (titleD);
        this.pack ();
        this.setVisible (true);
    }

    public static class Board extends JComponent {
    }
}

