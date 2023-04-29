package game;

import javax.swing.*;
import java.awt.*;


/**
 * Kelas Button untuk membentuk tombol Aksi SIm
 * @see JButton
 */
public class AksiButton extends JButton {
    /**
     * Konstruktor Kelas AksiButton
     * @param text
     * @see AksiButton
     */
    public AksiButton(String text){
        super(text);
        setBackground(Color.white);
        setForeground(Color.black);
        setFont(new Font("SansSerif", Font.PLAIN, 12));
        setPreferredSize(new Dimension(150, 30));
    }
}
