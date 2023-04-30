package game;

import entity.Kasur;
import entity.Kompor;
import entity.Perabotan;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AksiAktifPanel extends JPanel implements ActionListener {
    HousePanel housePanel;
    Perabotan perabotan;

    JButton OKButton;
    JButton cancelButton;
    JTextField durasiText;
    AksiAktifPanel(HousePanel housePanel, Perabotan perabotan, String aksi){
        this.housePanel = housePanel;
        this.perabotan = perabotan;
        setBackground(new Color(150, 178, 102, 150));
        setOpaque(true);
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(housePanel.centerPanel.getWidth()/2, housePanel.centerPanel.getHeight()/2));
        setBounds(housePanel.centerPanel.getWidth()/4, housePanel.centerPanel.getHeight()/4
                , housePanel.centerPanel.getWidth()/2,housePanel.centerPanel.getHeight()/2);
        setBorder(new LineBorder(Color.BLACK, 4, true));
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, housePanel.centerPanel.getWidth()/32, 5, housePanel.centerPanel.getWidth()/32);
        gbc.gridx = 0;
        int gridy = 0;
        JLabel aksiLabel = new JLabel("Aksi " + aksi);
        JLabel _aksiLabel = new JLabel("Durasi");
        durasiText = new JTextField();
        IntegerFilter _if = new IntegerFilter();
        ((AbstractDocument) durasiText.getDocument()).setDocumentFilter(_if);
        durasiText.setPreferredSize(new Dimension(housePanel.centerPanel.getWidth()/4, housePanel.unitSize/2));
        aksiLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 27));
        aksiLabel.setAlignmentX(SwingConstants.CENTER);
        _aksiLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        _aksiLabel.setAlignmentX(SwingConstants.CENTER);
        OKButton = new JButton("OK");
        OKButton.setPreferredSize(new Dimension(housePanel.centerPanel.getWidth()/8, 2*housePanel.unitSize/3));
        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(housePanel.centerPanel.getWidth()/8, 2*housePanel.unitSize/3));
        add(aksiLabel, gbc);
        gbc.gridy = ++gridy;
        add(_aksiLabel, gbc);
        gbc.gridy = ++gridy;
        add(durasiText, gbc);
        gbc.gridy = ++gridy;
        add(OKButton, gbc);
        gbc.gridy = ++gridy;
        add(cancelButton, gbc);
        OKButton.addActionListener(this);
        cancelButton.addActionListener(this);
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == OKButton){
            int duration = Integer.parseInt(durasiText.getText());
            //check apakah durasi valid
            if (duration == 0){
                JOptionPane.showMessageDialog(null, "Input durasi tidak boleh nol");
            }
            else{
                if (perabotan instanceof Kasur kasur){
                    kasur.tidur(duration, housePanel.selectedSim.sim);
                }
                else if (perabotan instanceof Kompor){

                }
            }
            housePanel.centerPanel.remove(this);
        }
        else if (e.getSource() == cancelButton){
            housePanel.centerPanel.remove(this);
        }
    }
}
