package com.github.tiim.updater;

import javax.swing.*;
import java.awt.*;

public class ProgressDialog extends JPanel {

    final JProgressBar progressBar;
    final JTextArea taskOutput;

    public ProgressDialog() {
        super(new BorderLayout());
        setSize(300, 100);
        //Create the demo's UI.
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        taskOutput = new JTextArea(5, 20);
        taskOutput.setMargin(new Insets(5, 5, 5, 5));
        taskOutput.setEditable(false);

        final JPanel panel = new JPanel();
        panel.add(progressBar);

        add(panel, BorderLayout.PAGE_START);
        add(new JScrollPane(taskOutput), BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        final JFrame frame = new JFrame("Update");

        //Create and set up the content pane.
        setOpaque(true); //content panes must be opaque
        frame.setContentPane(this);
        frame.setMinimumSize(new Dimension(500, 300));
        //Display the window.
//        frame.pack();
        frame.setVisible(true);
    }


    public void setProgress(final int i) {
        progressBar.setValue(i);
    }

    public void message(final String s) {
        taskOutput.append(s + "\n");
    }

}