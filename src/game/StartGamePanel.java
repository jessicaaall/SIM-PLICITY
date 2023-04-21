package game;

import entity.Rumah;
import entity.Sim;
import entity.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.Random;

public class StartGamePanel extends JPanel implements ActionListener {
    public MainPanel mp;
    public MainMenuPanel mmp;
    private World worldChoice;
    public JButton newWorldButton;
    public JButton loadWorldButton;
    public JButton backButton;


    public StartGamePanel(MainPanel mp,  MainMenuPanel mmp){
        this.mp = mp;
        this.mmp = mmp;
        mmp.startGamePanel = this;
        setLayout(new GridLayout(0, 1));
        setPreferredSize(new Dimension(mp.width/2, mp.height/2));
        setDoubleBuffered(true);
        newWorldButton = new JButton("New World");
        loadWorldButton = new JButton("Load World");
        backButton = new JButton("To Main Menu");
        Font thisFont = new Font("Comic Sans MS", Font.BOLD, 25);
        newWorldButton.setFont(thisFont);
        loadWorldButton.setFont(thisFont);
        backButton.setFont(thisFont);
        newWorldButton.setFocusable(false);
        loadWorldButton.setFocusable(false);
        backButton.setFocusable(false);
        newWorldButton.setHorizontalAlignment(JButton.CENTER);
        newWorldButton.setVerticalAlignment(JButton.CENTER);
//        newWorldButton.setBounds((mp.getWidth()-mp.getWidth()/4)/2,
//                mp.getHeight()/10,
//                mp.getWidth()/4,
//                100);
        newWorldButton.addActionListener(this);
        newWorldButton.setBackground(Color.lightGray);

        //create load button
        loadWorldButton.setHorizontalAlignment(JButton.CENTER);
        loadWorldButton.setVerticalAlignment(JButton.CENTER);
//        loadWorldButton.setBounds((mp.getWidth()-mp.getWidth()/4)/2,
//                newWorldButton.getY()+newWorldButton.getHeight(),
//                mp.getWidth()/4,
//                100);
        loadWorldButton.addActionListener(this);
        loadWorldButton.setBackground(Color.lightGray);

        //create back button
        backButton.setHorizontalAlignment(JButton.CENTER);
        backButton.setVerticalAlignment(JButton.CENTER);
//        backButton.setBounds((mp.getWidth()-mp.getWidth()/4)/2,
//                loadWorldButton.getY()+loadWorldButton.getHeight(),
//                mp.getWidth()/4,
//                100);
        backButton.addActionListener(this);
        backButton.setBackground(Color.lightGray);
        add(newWorldButton);
        add(loadWorldButton);
        add(backButton);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newWorldButton){

            JPanel newSimPanel = new JPanel(new GridLayout(0,1));
            newSimPanel.setBackground(Color.pink);
            JTextField namaSim = new JTextField();
            JLabel message = new JLabel("<html> Masukkan nama Sim mu<br></hmtl>");
            newSimPanel.add(message);
            newSimPanel.add(namaSim);
            int res = JOptionPane.showConfirmDialog(null, newSimPanel, "Create a New Sim",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (res == JOptionPane.OK_OPTION){
                String stringNamaSim = namaSim.getText();
                worldChoice = new World();
                Sim addedSim = new Sim(stringNamaSim, worldChoice);
                worldChoice.tambahSim(addedSim);
                Random random = new Random();
                Rumah rumahBaru = new Rumah(0,0, addedSim, new Color(random.nextInt(16777216)), worldChoice);
                addedSim.setLocRuang(rumahBaru.getDaftarRuangan().get(0));
                worldChoice.tambahRumah(rumahBaru);
                showWorldPanel();
            }

        } else if (e.getSource() == backButton) {
            mp.remove(this);
            mp.add(mmp);
            mp.revalidate();
            mp.repaint();

        } else if (e.getSource() == loadWorldButton) {
            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public World getWorldChoice(){
        return worldChoice;
    }
    public void showWorldPanel() {
        WorldPanel worldPanel = new WorldPanel(worldChoice, mp, mmp);
        mp.remove(this);
        worldPanel.startMainThread();
        mp.add(worldPanel, BorderLayout.CENTER);
        mp.revalidate(); // to update the layout
        mp.repaint(); // to repaint the panel
    }
}
