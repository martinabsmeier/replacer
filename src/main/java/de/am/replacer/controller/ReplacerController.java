package de.am.replacer.controller;

import de.am.replacer.view.ReplacerFrame;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.awt.*;

/**
 * @author Martin Absmeier
 */
@Controller
@AllArgsConstructor
public class ReplacerController {

    private final ReplacerFrame replacerFrame;

    public void prepareAndOpenFrame() {
        int width = 640;
        int height = 480;

        replacerFrame.setTitle("Replacer main dialog");
        replacerFrame.setPreferredSize(new Dimension(width, height));
        // Center the panel on screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xPos = screenSize.width/2 - width/2;
        int yPos = screenSize.height/2 - height/2;
        replacerFrame.setLocation(xPos, yPos);

        // replacerFrame.getDirectoryText().setEditable(false);

        replacerFrame.pack();
        replacerFrame.setVisible(true);
    }
}