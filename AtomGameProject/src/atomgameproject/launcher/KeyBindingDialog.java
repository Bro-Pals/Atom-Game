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
        setLayout(new GridLayout(7, 2, 30, 30));
        setSize(250, 440);
        labels = new JLabel[6];
        buttons = new JButton[6];
        labels[0] = new JLabel("Move up");
        labels[1] = new JLabel("Move right");
        labels[2] = new JLabel("Move down");
        labels[3] = new JLabel("Move left");
        labels[4] = new JLabel("Shoot Neutron");
        labels[5] = new JLabel("Shoot Antineutron");
        buttons[0] = new JButton(KeyEvent.getKeyText(wrapper.getMovementBinding()[0]));
        buttons[1] = new JButton(KeyEvent.getKeyText(wrapper.getMovementBinding()[1]));
        buttons[2] = new JButton(KeyEvent.getKeyText(wrapper.getMovementBinding()[2]));
        buttons[3] = new JButton(KeyEvent.getKeyText(wrapper.getMovementBinding()[3]));
        buttons[4] = new JButton("" + mouseCodeToString(wrapper.getShootBinding()[0]) + "");
        buttons[5] = new JButton("" + mouseCodeToString(wrapper.getShootBinding()[1]) + "");
        list = new ActionListener[6];
        list[0] = new KeySetUpListener(0, this);
        list[1] = new KeySetUpListener(1, this);
        list[2] = new KeySetUpListener(2, this);
        list[3] = new KeySetUpListener(3, this);
        list[4] = new MouseSetUpListener(0, this);
        list[5] = new MouseSetUpListener(1, this);
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
        JButton accept = new JButton("Done");
        accept.addActionListener(new DoneListener(this));
        add(accept);
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

    void refreshDisplay() {
        buttons[0].setText(KeyEvent.getKeyText(wrapper.getMovementBinding()[0]));
        buttons[1].setText(KeyEvent.getKeyText(wrapper.getMovementBinding()[1]));
        buttons[2].setText(KeyEvent.getKeyText(wrapper.getMovementBinding()[2]));
        buttons[3].setText(KeyEvent.getKeyText(wrapper.getMovementBinding()[3]));
        buttons[4].setText("" + mouseCodeToString(wrapper.getShootBinding()[0]) + "");
        buttons[5].setText("" + mouseCodeToString(wrapper.getShootBinding()[1]) + "");
    }
    
    class DoneListener implements ActionListener {
        private JDialog parent;
        public DoneListener(JDialog parent) {
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            parent.dispose();
        }
    }
    
    class KeySetUpListener implements ActionListener {
        private int index;
        private KeyBindingDialog parent;
        public KeySetUpListener(int index, KeyBindingDialog parent) {
            this.index = index;
            this.parent = parent;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
             JDialog d = new JDialog(parent, "Select a key");
             d.setSize(300, 100);
             d.setLocationRelativeTo(parent);
             d.setResizable(false);
             d.setModal(true);
             d.add(frame.getSpace().createGamePanel(d.getWidth(), d.getHeight(), new KeySetupState(wrapper, "Select a key", index, d, parent)));
             d.setVisible(true);
        }  
    }
    
    class MouseSetUpListener implements ActionListener {
        private int index;
        private KeyBindingDialog parent;
        public MouseSetUpListener(int index, KeyBindingDialog parent) {
            this.index = index;
            this.parent = parent;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
             JDialog d = new JDialog(parent, "Click a mouse button");
             d.setSize(300, 100);
             d.setLocationRelativeTo(parent);
             d.setResizable(false);
             d.setModal(true);
             d.add(frame.getSpace().createGamePanel(d.getWidth(), d.getHeight(), new MouseSetupState(wrapper, "Click a mouse button", index, d, parent)));
             d.setVisible(true);
        }  
    }
}
