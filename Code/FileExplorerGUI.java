import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileExplorerGUI {

    private static DefaultListModel<String> fileListModel = new DefaultListModel<>();
    private static JTextArea rightPaneTextArea = new JTextArea();
    private static JTextField pathTextField = new JTextField(30);

    public static void main(String args[]) {
        Directory.populateDefaultStructure();  // Populate the directory with default folders and files

        // Create the Frame
        JFrame jframe = new JFrame("DSA FILE EXPLORERS");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(800, 400);

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

        JMenuItem helpMenu1 = new JMenuItem("Help");
        helpMenu.add(helpMenu1);

        // Text Area at the Center
        rightPaneTextArea.setEditable(false);
        JScrollPane rightScrollPane = new JScrollPane(rightPaneTextArea);

        // Create the panel at bottom and add label, textArea and buttons
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Path:");
        JButton btn_refresh = new JButton("Refresh");

        panel.add(label); // Components Added using Flow Layout
        panel.add(pathTextField);
        panel.add(btn_refresh);

        // Adding Components to the frame.
        jframe.getContentPane().add(BorderLayout.SOUTH, panel);
        jframe.getContentPane().add(BorderLayout.NORTH, menuBar);
        jframe.getContentPane().add(BorderLayout.EAST, rightScrollPane);
        jframe.getContentPane().setBackground(new Color(255, 255, 255));
        jframe.setVisible(true);

        // Add file list display
        JList<String> fileList = new JList<>(fileListModel);
        fileList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String selectedValue = fileList.getSelectedValue();
                    if (selectedValue != null) {
                        pathTextField.setText(selectedValue);
                    }
                } else if (e.getClickCount() == 2) {
                    String selectedValue = fileList.getSelectedValue();
                    if (selectedValue != null) {
                        if (selectedValue.endsWith("/")) {
                            displayFolderContents(selectedValue);
                        } else {
                            displayFileContents(selectedValue);
                        }
                    }
                }
            }
        });
        JScrollPane fileScrollPane = new JScrollPane(fileList);
        jframe.getContentPane().add(BorderLayout.WEST, fileScrollPane);

        // Adding Action Listeners
        fileMenu1.addActionListener(e -> createNewFile());
        fileMenu2.addActionListener(e -> createNewFolder());
        fileMenu3.addActionListener(e -> deleteFile());
        fileMenu4.addActionListener(e -> moveFile());
        fileMenu5.addActionListener(e -> saveAsFile(rightPaneTextArea));
        fileMenu6.addActionListener(e -> searchFiles());
        fileMenu7.addActionListener(e -> sortFiles());

        helpMenu1.addActionListener(e -> displayHelp());

        btn_refresh.addActionListener(e -> refreshFileExplorer());

        // Initial file list population
        refreshFileList();
    }

    private static void createNewFile() {
        String filePath = JOptionPane.showInputDialog("Enter file path:");
        if (filePath != null && !filePath.isEmpty()) {
            try {
                String filename = JOptionPane.showInputDialog("Enter filename:");
                if (Directory.pathExists(filePath)) {
                    Directory.createFile(filePath, filename);
                    JOptionPane.showMessageDialog(null, "File created: " + filePath);
                    refreshFileList();
                } else {
                    JOptionPane.showMessageDialog(null, "Directory does not exist. Create the directory first.");
                }
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
                refreshFileList();
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
                if (Directory.isFile(filePath)) {
                    Directory.deleteFile(filePath);
                    JOptionPane.showMessageDialog(null, "Deleted the file: " + filePath);
                } else if (Directory.pathExists(filePath)) {
                    Directory.deleteDirectory(filePath);
                    JOptionPane.showMessageDialog(null, "Deleted the directory: " + filePath);
                } else {
                    JOptionPane.showMessageDialog(null, "Path does not exist: " + filePath);
                }
                refreshFileList();
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
                refreshFileList();
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
                refreshFileList();
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
                List<FileExplorerElement> results = Directory.search(attribute, value);
                if (results.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No results found for " + attribute + " = " + value);
                } else {
                    StringBuilder resultMessage = new StringBuilder("Search results:\n");
                    for (FileExplorerElement element : results) {
                        resultMessage.append(element.getName()).append("\n");
                    }
                    rightPaneTextArea.setText(resultMessage.toString());
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
                refreshFileList();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occurred while sorting.");
                e.printStackTrace();
            }
        }
    }

    private static void refreshFileExplorer() {
        refreshFileList();
        JOptionPane.showMessageDialog(null, "File explorer refreshed.");
    }

    private static void refreshFileList() {
        fileListModel.clear();
        addFilesToModel(Directory.root, "");
    }

    private static void addFilesToModel(Folder folder, String path) {
        for (FileExplorerElement element : folder.getContents()) {
            String fullPath = path + "/" + element.getName();
            fileListModel.addElement(fullPath);
            if (element instanceof Folder) {
                addFilesToModel((Folder) element, fullPath);
            }
        }
    }

    private static void displayFolderContents(String folderPath) {
        rightPaneTextArea.setText("");
        Folder folder = Directory.findDirectory(Directory.root, folderPath.replaceFirst("/", ""));
        if (folder != null) {
            for (FileExplorerElement element : folder.getContents()) {
                rightPaneTextArea.append(element.getName() + "\n");
            }
        }
    }

    private static void displayFileContents(String filePath) {
        rightPaneTextArea.setText("");
        Directory.viewContent(filePath.replaceFirst("/", ""));
    }

    private static void displayHelp() {
        rightPaneTextArea.setText(
            "Available commands:\n" +
            "1. New File: Create a new file.\n" +
            "2. New Folder: Create a new folder.\n" +
            "3. Delete: Delete a file or folder.\n" +
            "4. Move File: Move a file to a new location.\n" +
            "5. Save As: Save the content as a new file.\n" +
            "6. Search: Search for files or folders by attribute.\n" +
            "7. Sort: Sort files and folders by attribute.\n" +
            "8. Refresh: Refresh the file explorer view.\n"
        );
    }
}
