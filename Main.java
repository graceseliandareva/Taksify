package main;

import javax.swing.*;
import java.awt.CardLayout;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Task Management");

      MenuHome menuHome = new MenuHome();
MenuTask menuTask = new MenuTask(menuHome);
        NewTask newTask = new NewTask();

        // Set parent references
        newTask.setParentMenuTask(menuTask);
        newTask.setParentMenuHome(menuHome);
        menuHome.setNewTaskPanel(newTask); // Assuming you have this method to setNewTask panel

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new CardLayout());
        frame.add(menuHome, "menuHome");
        frame.add(menuTask, "menuTask");
        frame.add(newTask, "newTask");

        // Navigation panel with buttons
        JPanel navigationPanel = new JPanel();
        JButton btnShowMenuTask = new JButton("Menu Task");
        JButton btnShowNewTask = new JButton("New Task");
        JButton btnShowMenuHome = new JButton("Home"); // Button to show MenuHome

        navigationPanel.add(btnShowMenuTask);
        navigationPanel.add(btnShowNewTask);
        navigationPanel.add(btnShowMenuHome); // Adding button to navigation panel

        frame.add(navigationPanel, "navigation");

        btnShowMenuTask.addActionListener(e -> {
            CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
            cl.show(frame.getContentPane(), "menuTask");
        });

        btnShowNewTask.addActionListener(e -> {
            CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
            cl.show(frame.getContentPane(), "newTask");
        });

        btnShowMenuHome.addActionListener(e -> {
            CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
            cl.show(frame.getContentPane(), "menuHome");
        });

        frame.setVisible(true);

        // Initially show the MenuHome panel
        CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
        cl.show(frame.getContentPane(), "menuHome");
    }
}
