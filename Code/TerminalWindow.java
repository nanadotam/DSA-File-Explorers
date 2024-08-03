import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TerminalWindow extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextArea outputArea;
    private JTextField inputField;
    private JButton executeButton;

    public TerminalWindow() {
        setTitle("Custom Terminal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set up dark mode colors
        Color backgroundColor = new Color(43, 43, 43);
        Color textColor = new Color(187, 187, 187);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(backgroundColor);
        outputArea.setForeground(textColor);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(backgroundColor);

        inputField = new JTextField();
        inputField.setBackground(backgroundColor);
        inputField.setForeground(textColor);
        executeButton = new JButton("Execute");
        executeButton.addActionListener(this);

        panel.add(inputField, BorderLayout.CENTER);
        panel.add(executeButton, BorderLayout.EAST);

        add(panel, BorderLayout.SOUTH);

        // Display welcome message and usage instructions
        outputArea.append("Welcome to the Custom Terminal!\n");
        showUsage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = inputField.getText();
        if (!command.isEmpty()) {
            executeCommand(command);
            inputField.setText("");
        }
    }

    private void executeCommand(String command) {
        String[] args = command.split(" ");
        if (args.length == 0) {
            return;
        }

        String cmd = args[0];
        try {
            switch (cmd) {
                case "create_file":
                    if (args.length != 2) {
                        outputArea.append("Usage: create_file <file_path>\n");
                    } else {
                        createFile(args[1]);
                    }
                    break;
                case "create_dir":
                    if (args.length != 2) {
                        outputArea.append("Usage: create_dir <dir_path>\n");
                    } else {
                        createDirectory(args[1]);
                    }
                    break;
                case "delete":
                    if (args.length != 2) {
                        outputArea.append("Usage: delete <path>\n");
                    } else {
                        delete(args[1]);
                    }
                    break;
                case "move":
                    if (args.length != 3) {
                        outputArea.append("Usage: move <source_path> <destination_path>\n");
                    } else {
                        move(args[1], args[2]);
                    }
                    break;
                case "search":
                    if (args.length != 3) {
                        outputArea.append("Usage: search <attribute> <value>\n");
                    } else {
                        search(args[1], args[2]);
                    }
                    break;
                case "sort":
                    if (args.length != 2) {
                        outputArea.append("Usage: sort <attribute>\n");
                    } else {
                        sort(args[1]);
                    }
                    break;
                case "show_structure":
                    if (args.length != 1) {
                        outputArea.append("Usage: show_structure\n");
                    } else {
                        showStructure();
                    }
                    break;
                default:
                    outputArea.append("Unknown command: " + cmd + "\n");
                    showUsage();
                    break;
            }
        } catch (Exception e) {
            outputArea.append("An error occurred: " + e.getMessage() + "\n");
        }
    }

    private void createFile(String filePath) throws Exception {
        if (!Directory.pathExists(filePath)) {
            Directory.createFile(filePath);
            outputArea.append("File created: " + filePath + "\n");
        } else {
            outputArea.append("File already exists: " + filePath + "\n");
        }
    }

    private void createDirectory(String dirPath) throws Exception {
        if (!Directory.pathExists(dirPath)) {
            Directory.createDirectory(dirPath);
            outputArea.append("Directory created: " + dirPath + "\n");
        } else {
            outputArea.append("Directory already exists: " + dirPath + "\n");
        }
    }

    private void delete(String path) throws Exception {
        if (Directory.isFile(path)) {
            Directory.deleteFile(path);
            outputArea.append("File deleted: " + path + "\n");
        } else if (Directory.pathExists(path)) {
            Directory.deleteDirectory(path);
            outputArea.append("Directory deleted: " + path + "\n");
        } else {
            outputArea.append("Path does not exist: " + path + "\n");
        }
    }

    private void move(String sourcePath, String destPath) throws Exception {
        if (Directory.pathExists(sourcePath)) {
            Directory.moveFileOrDirectory(sourcePath, destPath);
            outputArea.append("Moved " + sourcePath + " to " + destPath + "\n");
        } else {
            outputArea.append("Source path does not exist: " + sourcePath + "\n");
        }
    }

    private void search(String attribute, String value) throws Exception {
        ArrayList<FileExplorerElement> results = Directory.search(attribute, value);
        if (results.isEmpty()) {
            outputArea.append("No results found for " + attribute + " = " + value + "\n");
        } else {
            outputArea.append("Search results:\n");
            for (FileExplorerElement element : results) {
                outputArea.append(element.getName() + "\n");
            }
        }
    }

    private void sort(String attribute) throws Exception {
        Directory.sort(attribute);
        outputArea.append("Sorted by " + attribute + "\n");
    }

    private void showStructure() throws Exception {
        outputArea.append("Directory structure:\n");
        Directory.displayDirectoryOutline();
    }

    private void showUsage() {
        outputArea.append("Usage:\n");
        outputArea.append("  create_file <file_path>: Create a new file at the specified path.\n");
        outputArea.append("  create_dir <dir_path>: Create a new directory at the specified path.\n");
        outputArea.append("  delete <path>: Delete the file or directory at the specified path.\n");
        outputArea.append("  move <source_path> <destination_path>: Move a file or directory from the source path to the destination path.\n");
        outputArea.append("  search <attribute> <value>: Search for files or directories based on a specified attribute and value.\n");
        outputArea.append("  sort <attribute>: Sort files and directories by the specified attribute.\n");
        outputArea.append("  show_structure: Display the current directory and file structure in a tree format.\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TerminalWindow terminal = new TerminalWindow();
            terminal.setVisible(true);
        });
    }
}
