package de.am.replacer.view;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

import static java.awt.event.KeyEvent.VK_ESCAPE;
import static javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
import static javax.swing.KeyStroke.getKeyStroke;

/**
 * @author Martin Absmeier
 */
@Component
@Getter
public class ReplacerFrame extends JDialog {

    private JPanel replacerPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea searchText;
    private JTextArea replaceText;

    public ReplacerFrame() {
        setContentPane(replacerPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(event -> onOK());
        buttonCancel.addActionListener(event -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        replacerPane.registerKeyboardAction(event -> onCancel(), getKeyStroke(VK_ESCAPE, 0), WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        String searchTxt = searchText.getText();
        String replaceTxt = replaceText.getText();

        System.out.println("--------------------------------------------------------------");
        System.out.println("searchText = " + searchTxt);
        System.out.println("replaceText = " + replaceTxt);
        System.out.println("--------------------------------------------------------------");
    }

    private void onCancel() {
        dispose();
    }

    /*
    private void onChoose() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(replacerPane);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            directoryText.setText(file.getName());
        }
    }
     */
}
