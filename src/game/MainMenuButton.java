package game;
import javax.swing.*;
import java.awt.*;

public class MainMenuButton extends JButton {
    public MainMenuButton(String text){
        this.setText(text);
        this.setFocusable(false);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);
        this.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
    }
}
