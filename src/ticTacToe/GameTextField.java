package ticTacToe;

import javax.swing.*;
import java.awt.*;

public class GameTextField extends JFrame {

    private JTextField text = new JTextField();

    GameTextField(String text, boolean setEditable, boolean enableBorders, int height) {
        Dimension dimension = new Dimension(90,height);
        this.text.setText(text);
        this.text.setHorizontalAlignment(JTextField.CENTER);
        this.text.setEditable(setEditable);
        this.text.setPreferredSize(dimension);
        if (!enableBorders) {
            this.text.setBorder(null);
        }
    }

    public JTextField getTextField() {
        return text;
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public String getText() {
        return this.text.getText();
    }
}
