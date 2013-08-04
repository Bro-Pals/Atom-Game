/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.launcher;

import atomgameproject.launcher.config.KeyBindWrapper;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Jonathon
 */
public class KeyBindingDialog extends JDialog {
    
    private LauncherFrame frame;
    private KeyBindWrapper wrapper;
    private JLabel[] labels;
    private JButton[] buttons;
    private ActionListener[] list;
    protected KeyBindingDialog d;
    
    public KeyBindingDialog(LauncherFrame frame, KeyBindWrapper wrapper) {
        this.frame = frame;
        d = this;
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.wrapper = wrapper;
        setTitle("Control setup");
        setResizable(false);
        setLayout(new GridLayout(6, 2, 30, 30));
        setSize(250, 400);
        labels = new JLabel[6];
        buttons = new JButton[6];
        labels[0] = new JLabel("Move up");
        labels[1] = new JLabel("Move down");
        labels[2] = new JLabel("Move left");
        labels[3] = new JLabel("Move right");
        labels[4] = new JLabel("Shoot Neutron");
        labels[5] = new JLabel("Shoot Antineutron");
        buttons[0] = new JButton(KeyEvent.getKeyText(wrapper.getMovementBinding()[0]));
        buttons[1] = new JButton(KeyEvent.getKeyText(wrapper.getMovementBinding()[1]));
        buttons[2] = new JButton(KeyEvent.getKeyText(wrapper.getMovementBinding()[2]));
        buttons[3] = new JButton(KeyEvent.getKeyText(wrapper.getMovementBinding()[3]));
        buttons[4] = new JButton("" + mouseCodeToString(wrapper.getShootBinding()[0]) + "");
        buttons[5] = new JButton("" + mouseCodeToString(wrapper.getShootBinding()[0]) + "");
        list = new ActionListener[6];
        list[0] = new KeySetUpListener(0);
        list[1] = new KeySetUpListener(1);
        list[2] = new KeySetUpListener(2);
        list[3] = new KeySetUpListener(3);
        list[4] = new MouseSetUpListener(4);
        list[5] = new MouseSetUpListener(5);
        buttons[0].addActionListener(list[0]);
        buttons[1].addActionListener(list[1]);
        buttons[2].addActionListener(list[2]);
        buttons[3].addActionListener(list[3]);
        buttons[4].addActionListener(list[4]);
        buttons[5].addActionListener(list[5]);
        for (int i=0; i<6; i++) {
            add(labels[i]);
            add(buttons[i]);
        }
        setLocationRelativeTo(frame);
        setVisible(true);
    }
    
    private String mouseCodeToString(int i) {
        String text = "";
        if (i==MouseEvent.BUTTON1) {
            text = "BUTTON1";
        }
        if (i==MouseEvent.BUTTON2) {
            text = "BUTTON2";
        }
        if (i==MouseEvent.BUTTON3) {
            text = "BUTTON3";
        }
        return text;
    }
    
    class KeySetUpListener implements ActionListener {
        private int index;
        public KeySetUpListener(int index) {
            this.index = index;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
             
        }  
    }
    
    class MouseSetUpListener implements ActionListener {
        private int index;
        public MouseSetUpListener(int index) {
            this.index = index;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }  
    }
}
