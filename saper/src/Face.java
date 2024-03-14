import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import java.awt.Image;
import java.util.Objects;

public class Face extends JComponent {
    private String name = "smile.png";
    private GUI gui;


    public Face(int posX, int posY, int w, int h, Object g, boolean can) {
        this.setBounds (posX, posY, w, h);
        FaceMouse ml = new FaceMouse ();
        this.addMouseListener (ml);
        if (can)
            gui = (GUI) g;
    }

    public void changeFace(String str) {
        name = str;
        repaint ();
    }

    @Override
    public void paint(Graphics g) {
        ImageIcon icon = new ImageIcon (Objects.requireNonNull (getClass ().getResource (name)));
        Image img1 = icon.getImage ();
        g.drawImage (img1, 0, 0, this);
    }

    public class FaceMouse implements MouseInputListener {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton (e)) {
                gui.dispose ();
                new Menu ();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // useless, but without is giving syntax
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // useless, but without is giving syntax
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // useless, but without is giving syntax
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // useless, but without is giving syntax
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // useless, but without is giving syntax
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // useless, but without is giving syntax
        }
    }
}
