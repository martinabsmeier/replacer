package de.am.replacer;

import de.am.replacer.controller.ReplacerController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

/**
 *
 * @author Martin Absmeier
 */
@SpringBootApplication
public class ReplacerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = createApplicationContext(args);
        displayReplacerFrame(context);
    }

    private static ConfigurableApplicationContext createApplicationContext(String[] args) {
        return new SpringApplicationBuilder(ReplacerApplication.class)
                .headless(false)
                .run(args);
    }

    private static void displayReplacerFrame(ConfigurableApplicationContext context) {
        SwingUtilities.invokeLater(() -> {
            ReplacerController controller = context.getBean(ReplacerController.class);
            controller.prepareAndOpenFrame();
        });
    }

}