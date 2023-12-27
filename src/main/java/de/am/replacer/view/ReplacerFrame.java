package de.am.replacer.view;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.awt.event.KeyEvent.VK_ESCAPE;
import static javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
import static javax.swing.KeyStroke.getKeyStroke;

/**
 * @author Martin Absmeier
 */
@Component
@Getter
@Log4j2
public class ReplacerFrame extends JDialog {

    private JPanel replacerPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea searchText;
    private JTextArea replaceText;
    private JButton buttonChoose;
    private JTextField selectedDirectory;
    private JTextField fileExtension;

    public ReplacerFrame() {
        setContentPane(replacerPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(event -> onOK());
        buttonCancel.addActionListener(event -> onCancel());
        buttonChoose.addActionListener(event -> onChoose());

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

    // #################################################################################################################

    private void onOK() {
        String directory = selectedDirectory.getText();

        try (Stream<Path> fileStream = Files.walk(Paths.get(directory))) {
            String extension = fileExtension.getText();

            List<File> files = fileStream.filter(Files::isRegularFile)
                    .filter(Files::isWritable)
                    .map(Path::toFile)
                    .filter(file -> file.getName().endsWith(extension))
                    .toList();

            replaceHeader(files);
        } catch (IOException ex) {
            log.error(ex);
        }
    }

    private void onCancel() {
        dispose();
    }

    private void onChoose() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(replacerPane);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            selectedDirectory.setText(file.getAbsolutePath());
        }
    }

    private void replaceHeader(List<File> files) {
        String searchTxt = searchText.getText();
        String replaceTxt = replaceText.getText();



    }
}