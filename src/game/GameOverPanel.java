package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel implements ActionListener {
    
    private MainPanel mp;
    private MainMenuPanel mmp;
    private JButton backButton;

    public GameOverPanel(MainPanel mp) {
        this.mp = mp;
        setLayout(null);
        setPreferredSize(mp.getPreferredSize());
        setBounds(0,0, mp.width, mp.height);
        setVisible(true);
        setFocusable(false);
        backButton = new JButton("Back to Main Menu");
        backButton.setBounds((mp.width -200)/2, mp.height-150, 200, 100);
        backButton.setFocusable(false);
        backButton.setHorizontalTextPosition(JButton.CENTER);
        backButton.setVerticalTextPosition(JButton.CENTER);
        backButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        backButton.setForeground(Color.black);
        backButton.addActionListener(this);
        add(backButton);
    }

    public void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(Color.black);
        g2D.setFont(new Font("Comic Sans MS", Font.BOLD, 80));
        g2D.drawString("GAME OVER", (mp.width-g2D.getFontMetrics(g2D.getFont()).stringWidth("GAME OVER"))/2, 120);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton){
            mp.add(mmp,BorderLayout.CENTER);
            mp.remove(this);
            mp.revalidate();
            mp.repaint();
        }
    }
}
