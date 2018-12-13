package ticTacToe;

import javax.swing.*;
import java.awt.*;

/**
 *  Class for Game Text field ui element, contains field and its parameters
 * @see UserInterface
 */
public class GameTextField extends JFrame {

    private JTextField text = new JTextField();

    GameTextField(String text, boolean setEditable, boolean enableBorders, Font font) {
        Dimension dimension = new Dimension(135,25);
        this.text.setText(text);
        this.text.setHorizontalAlignment(JTextField.CENTER);
        this.text.setEditable(setEditable);
        this.text.setPreferredSize(dimension);
        this.text.setFont(font);
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
