package main;
import javax.swing.*;
import java.awt.*;
public class MainPanel {
public MainPanel() {
JPanel parentPanel = new JPanel(new CardLayout());
CardLayout cardLayout = (CardLayout) parentPanel.getLayout();
NewTask newTaskPanel = new NewTask();
MenuTask menuTaskPanel = new MenuTask(parentPanel, cardLayout, newTaskPanel);
newTaskPanel.setParentMenuTask(menuTaskPanel);
parentPanel.add(menuTaskPanel, "menuTaskPanel");
parentPanel.add(newTaskPanel, "newTaskPanel");
JFrame frame = new JFrame();
frame.add(parentPanel);
frame.setTitle("Task Manager");
frame.setSize(600, 400);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setLocationRelativeTo(null);
frame.setVisible(true);
}
public static void main(String[] args) {
SwingUtilities.invokeLater(() -> {
new MainPanel();
});
}
}