package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    GridBagConstraints gbc;
    private MainPanel mainPanel;
    private HelpPanel helpPanel;
    private MainMenuPanel mmp = this;
    private class MenuPanel extends JPanel implements ActionListener {

        private MainMenuButton startButton;
        private MainMenuButton helpButton;
        private MainMenuButton exitButton;
        MenuPanel(){
            // set the panel properties
//        setLayout(new GridLayout(3, 1));
            setLayout(null);
            setBounds((mainPanel.width-400)/2,160, 400,240);
            setPreferredSize(new Dimension(400,240));
            setDoubleBuffered(true);
            // create and add button
            startButton = new MainMenuButton("Start");
            helpButton = new MainMenuButton("Help");
            exitButton = new MainMenuButton("Exit");
            startButton.setBounds(0, 0, 400, 80);
            helpButton.setBounds(0, 80, 400, 80);
            exitButton.setBounds(0, 160, 400, 80);
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
            if (e.getSource().equals(startButton) ){
                System.out.println("Starting game...");
                mainPanel.showWorldPanel();
            }
            else if (e.getSource().equals(helpButton) ) {
                // TODO: implement help screen logic
                System.out.println("Opening help screen...");
                mainPanel.remove(mmp);
                mainPanel.add(helpPanel, BorderLayout.CENTER);
                mainPanel.revalidate();
                mainPanel.repaint();

            } else if (e.getSource() == exitButton) {
                System.out.println("Exit the program...");
                System.exit(0);
            }
        }
    }
    MainMenuPanel(MainPanel mainPanel){
        this.mainPanel = mainPanel;
        this.helpPanel = new HelpPanel(mainPanel);
        helpPanel.mmp = this;
        setLayout(null);
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx=0;
//        gbc.gridy=1;
//        gbc.fill= GridBagConstraints.HORIZONTAL;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.insets = new Insets(50,0,0,0);
        MenuPanel menuPanel = new MenuPanel();
        this.add(menuPanel);
        setPreferredSize(new Dimension(mainPanel.width, mainPanel.height));
        setDoubleBuffered(true);

    }
    public void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(Color.black);
        g2D.setFont(new Font("Comic Sans", Font.BOLD, 80));
        g2D.drawString("Sim-Plicity", 32* mainPanel.width/100, 120);
//        g2D.dispose();
    }


}
