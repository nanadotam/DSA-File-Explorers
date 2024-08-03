import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
// import java.util.ArrayList;

public class FileExplorerGUI {

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
        JMenuItem fileMenu1 = new JMenuItem("New File");
        JMenuItem fileMenu2 = new JMenuItem("New Folder");
        JMenuItem fileMenu3 = new JMenuItem("Delete");
        JMenuItem fileMenu4 = new JMenuItem("Move File");
        JMenuItem fileMenu5 = new JMenuItem("Save As");
        JMenuItem fileMenu6 = new JMenuItem("Search");
        JMenuItem fileMenu7 = new JMenuItem("Sort");

        fileMenu.add(fileMenu1);
        fileMenu.add(fileMenu2);
        fileMenu.add(fileMenu3);
        fileMenu.add(fileMenu4);
        fileMenu.add(fileMenu5);
        fileMenu.add(fileMenu6);
        fileMenu.add(fileMenu7);

        // Text Area at the Center
        JTextArea textArea = new JTextArea();
        // Adding a scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Create the panel at bottom and add label, textArea and buttons
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Please Enter Text");
        JTextField textField = new JTextField(30); // accepts up to 30 characters
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
        fileMenu2.addActionListener(e -> createNewFolder());
        fileMenu3.addActionListener(e -> deleteFile());
        fileMenu4.addActionListener(e -> moveFile());
        fileMenu5.addActionListener(e -> saveAsFile(textArea));
        fileMenu6.addActionListener(e -> searchFiles());
        fileMenu7.addActionListener(e -> sortFiles());

        btn_refresh.addActionListener(e -> refreshFileExplorer());
    }

    private static void createNewFile() {
        String filePath = JOptionPane.showInputDialog("Enter file path:");
        if (filePath != null && !filePath.isEmpty()) {
            try {
                String filename = JOptionPane.showInputDialog("Enter filename:");
                Directory.createFile(filePath, filename);
                JOptionPane.showMessageDialog(null, "File created: " + filePath);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occurred.");
                e.printStackTrace();
            }
        }
    }

    private static void createNewFolder() {
        String dirPath = JOptionPane.showInputDialog("Enter directory path:");
        if (dirPath != null && !dirPath.isEmpty()) {
            try {
                Directory.createDirectory(dirPath);
                JOptionPane.showMessageDialog(null, "Directory created: " + dirPath);
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

    private static void searchFiles() {
        String attribute = JOptionPane.showInputDialog("Enter search attribute (name, size, item type, date modified):");
        String value = JOptionPane.showInputDialog("Enter search value:");
        if (attribute != null && !attribute.isEmpty() && value != null && !value.isEmpty()) {
            try {
                java.util.List<FileExplorerElement> results = Directory.search(attribute, value);
                if (results.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No results found for " + attribute + " = " + value);
                } else {
                    StringBuilder resultMessage = new StringBuilder("Search results:\n");
                    for (FileExplorerElement element : results) {
                        resultMessage.append(element.getName()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, resultMessage.toString());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occurred while searching.");
                e.printStackTrace();
            }
        }
    }

    private static void sortFiles() {
        String attribute = JOptionPane.showInputDialog("Enter sort attribute (name, size, item type, date modified):");
        if (attribute != null && !attribute.isEmpty()) {
            try {
                Directory.sort(attribute);
                JOptionPane.showMessageDialog(null, "Files and directories sorted by " + attribute);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occurred while sorting.");
                e.printStackTrace();
            }
        }
    }

    private static void refreshFileExplorer() {
        JOptionPane.showMessageDialog(null, "File explorer refreshed.");
    }
}
