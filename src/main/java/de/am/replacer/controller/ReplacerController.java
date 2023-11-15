package de.am.replacer.controller;

import de.am.replacer.view.ReplacerFrame;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 * @author Martin Absmeier
 */
@Controller
@AllArgsConstructor
public class ReplacerController {

    private final ReplacerFrame replacerFrame;

    public void prepareAndOpenFrame() {
        replacerFrame.pack();
        replacerFrame.setVisible(true);
    }
}