import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Objects;

public class Display3x7 extends JComponent {
    private int value = 0;
    private boolean stopTimer = false;
    private final Image[] digits = new Image[11];
    private final Image logo;

    public Display3x7(int posX, int posY, String logoName) {
        this.setBounds (posX, posY, 78, 32);
        for (int i = 0; i < 11; i++)
            digits[i] = new ImageIcon (Objects.requireNonNull (getClass ().getResource ("digits/" + i + ".png"))).getImage ();
        logo = new ImageIcon (Objects.requireNonNull (getClass ().getResource (logoName))).getImage ();
        //setTimeout(this,3000,10);
    }

    public void setValue(int v) {
        if (!stopTimer) {
            value = v;
            repaint ();
        }
    }

    public void countTimer() {
        if (!stopTimer)
            setTimeout (this, 1000, value + 1);
    }

    public void stopTimer() {
        stopTimer = true;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage (logo, 0, 4, this);
        int[] tab = {0, 0, 0};
        if (value != 0) {
            tab[0] = value / 100;
            tab[1] = (value % 100) / 10;
            tab[2] = value % 10;
        }
        for (int i = 0; i < 3; i++)
            g.drawImage (digits[tab[i]], 30 + i * 16, 0, this);

    }

    public static void setTimeout(Object o, int delay, int v) {
        new Thread (() -> {
            try {
                Thread.sleep (delay);
                ((Display3x7) o).setValue (v);
                ((Display3x7) o).countTimer ();
            } catch (Exception e) {
                System.err.println (e);
            }
        }).start ();
    }
}
