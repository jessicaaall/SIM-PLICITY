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
        setBackground(Color.green);
        setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
    }
}
