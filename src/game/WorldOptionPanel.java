package game;

import entity.Rumah;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class WorldOptionPanel extends JPanel implements ActionListener {
    public JButton toMainMenuButton = new JButton("<html>Back to<br>Main Menu</html>");
    public JButton addHouseButton = new JButton("Add House");
    public JSlider volumeSlider = new JSlider(500, 860, 700);
    public MainPanel mp;
    public WorldPanel wp;
    public WorldOptionPanel(MainPanel mainPanel, WorldPanel worldPanel){
        mp = mainPanel; wp = worldPanel;

//        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(null);
        Dimension size = new Dimension((mp.getWidth() - 7*wp.getWidth()/5)/2 -4, mp.getHeight()/2);
        this.setPreferredSize(size);
        this.setBounds(0,mp.getHeight()/4, (mp.getWidth() - 7*wp.getWidth()/5)/2 -4, mp.getHeight()/2);
        this.setBackground(Color.white);
        toMainMenuButton.setFocusable(false);
        toMainMenuButton.setHorizontalTextPosition(JButton.CENTER);
        toMainMenuButton.setVerticalTextPosition(JButton.CENTER);
        toMainMenuButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

        toMainMenuButton.addActionListener(this);
        toMainMenuButton.setBackground(Color.yellow);
        toMainMenuButton.setBounds(0, 0,
                this.getWidth(),
                toMainMenuButton.getFontMetrics(toMainMenuButton.getFont()).getHeight()*2+5);
        addHouseButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        addHouseButton.addActionListener(this);
        addHouseButton.setFocusable(false);
        addHouseButton.setHorizontalTextPosition(JButton.CENTER);
        addHouseButton.setVerticalTextPosition(JButton.CENTER);
        addHouseButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        addHouseButton.setBounds(0,
                toMainMenuButton.getFontMetrics(toMainMenuButton.getFont()).getHeight()*2,
                this.getWidth(),
                addHouseButton.getFontMetrics(addHouseButton.getFont()).getHeight()+5);
        add(toMainMenuButton);
        add(addHouseButton);
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                float volume = (float) volumeSlider.getValue()/10f;
                if (!(volume < 61f)){
                    wp.sound.setVolume(-80f+volume);
                }
                else{
                    wp.sound.setVolume(-86f);
                }

            }
        });
        JLabel music = new JLabel("Music Volume");
        music.setFont(new Font("Courier New", Font.BOLD, 10));
        music.setHorizontalTextPosition(SwingConstants.CENTER);
        music.setBackground(Color.white);
        music.setOpaque(true);
        music.setBounds((this.getWidth()-music.getFontMetrics(music.getFont()).stringWidth(music.getText()))/2,
                addHouseButton.getY()+addHouseButton.getHeight(),
                this.getWidth(),
                music.getFontMetrics(music.getFont()).getHeight()+3);
        add(music);
        volumeSlider.setBounds(0, music.getY()+music.getHeight(), this.getWidth(), 15);
        add(volumeSlider);
        wp.wop = this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toMainMenuButton){
            System.out.println("Exit Game");
            wp.stopMusic();
            wp.mainThread.interrupt();
            wp.mainThread =null;
            mp.remove(toMainMenuButton);
            mp.add(wp.mmp, BorderLayout.CENTER);
            mp.remove(this);
            mp.remove(wp);
            mp.revalidate();
            mp.repaint();
        }
        else if (e.getSource() == addHouseButton){
            JPanel panel = new JPanel(new GridLayout(0, 1));
            JLabel label = new JLabel("<html>Set the coordinate of your house:<br></html>");
            JTextField xField = new JTextField();
            JTextField yField = new JTextField();
            JButton colorButton = new JButton("Choose Color");
            colorButton.setFocusable(false);
            final Color[] color = new Color[1];
            colorButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Color chosenColor = JColorChooser.showDialog(null, "Choose a color", Color.white);
                    color[0] = chosenColor;
                }
            });
            panel.add(label);
            panel.add(new JLabel("<html>X Coordinate:<br></html>"));
            panel.add(xField);
            panel.add(new JLabel("<html>Y Coordinate:<br></html>"));
            panel.add(yField);
            panel.add(new JLabel("<html>Color:<br></html>"));
            panel.add(colorButton);
            int result = JOptionPane.showConfirmDialog(null, panel, "Add House",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                int x = Integer.parseInt(xField.getText());
                int y = Integer.parseInt(yField.getText());
                // Do something with x and y...
                Rumah rumahBaru = new Rumah(x, y,null, color[0],wp.getWorld());
                wp.getWorld().tambahRumah(rumahBaru);
            }
        }
    }
}
