import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {

    private MainMenuButton startButton;
    private MainMenuButton helpButton;
    private MainMenuButton exitButton;
    MenuPanel(){
        // set the panel properties
//        setLayout(new GridLayout(3, 1));
        setLayout(null);
        setBounds(200,200, 400,300);
        setPreferredSize(new Dimension(400,300));
        // create and add button
        startButton = new MainMenuButton("Start");
        helpButton = new MainMenuButton("Help");
        exitButton = new MainMenuButton("Exit");
        startButton.setBounds(0, 0, 400, 100);
        helpButton.setBounds(0, 100, 400, 100);
        exitButton.setBounds(0, 200, 400, 100);
        add(startButton);
        add(helpButton);
        add(exitButton);

        // add action listener to the button
        startButton.addActionListener(this);
        helpButton.addActionListener(this);
        exitButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton){
            System.out.println("Starting game...");
        }
        else if (e.getSource() == helpButton) {
            // TODO: implement help screen logic
            System.out.println("Opening help screen...");
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
}
