package main;

import javax.swing.*;

public interface IPanel {

    public void showPanel(Boolean show);

    public void initialize();

    public void design();

    void putPaneltoFrame(JFrame frame);

}
