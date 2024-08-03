import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class gui {

    public static void main(String args[]) {
        // Create the Frame
        JFrame jframe = new JFrame("DSA FILE EXPLORERS");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(600, 400);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // Create menu items for FILE menu
        JMenuItem fileMenu1 = new JMenuItem("New file");
        JMenuItem fileMenu2 = new JMenuItem("Delete");
        JMenuItem fileMenu3 = new JMenuItem("Move file");
        JMenuItem fileMenu4 = new JMenuItem("Save As");

        fileMenu.add(fileMenu1);
        fileMenu.add(fileMenu2);
        fileMenu.add(fileMenu3);
        fileMenu.add(fileMenu4);

        // Text Area at the Center
        JTextArea textArea = new JTextArea();
        // Adding a scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Create the panel at bottom and add label, textArea and buttons
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Please Enter Text");
        JTextField textField = new JTextField(30); // accepts up to 30 characters
        JButton btn_enter = new JButton("Enter");
        JButton btn_refresh = new JButton("Refresh");

        panel.add(label); // Components Added using Flow Layout
        panel.add(textField);
        panel.add(btn_refresh);

        // Adding Components to the frame.
        jframe.getContentPane().add(BorderLayout.SOUTH, panel);
        jframe.getContentPane().add(BorderLayout.NORTH, menuBar);
        jframe.getContentPane().add(BorderLayout.CENTER, scrollPane);
        jframe.getContentPane().setBackground(new Color(255, 251, 0));
        jframe.setVisible(true);

        // Adding Action Listeners
        fileMenu1.addActionListener(e -> createNewFile());
        fileMenu2.addActionListener(e -> deleteFile());
        fileMenu3.addActionListener(e -> moveFile());
        fileMenu4.addActionListener(e -> saveAsFile(textArea));

        btn_refresh.addActionListener(e -> refreshFileExplorer());
    }
    
    private static void createNewFile() {
        String filePath = JOptionPane.showInputDialog("Enter file path:");
        if (filePath != null && !filePath.isEmpty()) {
            try {
                // Check if file already exists
                if (!Directory.pathExists(filePath)) {
                    String filename = JOptionPane.showInputDialog("Enter filename:");
                    // Create file
                    Directory.createFile(filePath, filename);
                    JOptionPane.showMessageDialog(null, "File created: " + filePath);
                } else {
                    JOptionPane.showMessageDialog(null, "File already exists.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occurred.");
                e.printStackTrace();
            }
        }
    }
    

    private static void deleteFile() {
        String filePath = JOptionPane.showInputDialog("Enter file path to delete:");
        if (filePath != null && !filePath.isEmpty()) {
            try {
                Directory.deleteFile(filePath);
                JOptionPane.showMessageDialog(null, "Deleted the file: " + filePath);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Failed to delete the file.");
                e.printStackTrace();
            }
        }
    }

    private static void moveFile() {
        String sourcePath = JOptionPane.showInputDialog("Enter source file path:");
        String destPath = JOptionPane.showInputDialog("Enter destination file path:");
        if (sourcePath != null && !sourcePath.isEmpty() && destPath != null && !destPath.isEmpty()) {
            try {
                Directory.moveFileOrDirectory(sourcePath, destPath);
                JOptionPane.showMessageDialog(null, "File moved to: " + destPath);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occurred.");
                e.printStackTrace();
            }
        }
    }

    private static void saveAsFile(JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save As");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                textArea.write(writer);
                JOptionPane.showMessageDialog(null, "File saved: " + fileToSave.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred while saving the file.");
                ex.printStackTrace();
            }
        }
    }

    private static void refreshFileExplorer() {
        JOptionPane.showMessageDialog(null, "File explorer refreshed.");
    }
}
