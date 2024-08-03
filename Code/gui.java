import javax.swing.*;


import java.awt.*;


class gui {


    public static void main(String args[]) {
        //Create the Frame
        JFrame jframe = new JFrame("DSA FILE EXPLORERS");
        //colour of GUI
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(400, 400);

//     create two menubar button FILE and HELP
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("FILE");
        JMenu helpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

//      create two more option in FILE button
        JMenuItem fileMenu1 = new JMenuItem("new file");
        JMenuItem fileMenu2 = new JMenuItem("Delete");
        JMenuItem fileMenu3 = new JMenuItem("Move file");
        JMenuItem fileMenu4 = new JMenuItem("Save as");

        fileMenu.add(fileMenu1);
        fileMenu.add(fileMenu2);
        fileMenu.add(fileMenu3);
        fileMenu.add(fileMenu4);

        // Text Area at the Center
        JTextArea textArea = new JTextArea();
        //Create the panel at bottom and add label, textArea and buttons
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Please Enter Text");
        JTextField textField = new JTextField(30); // accepts upto 15 characters
        JButton btn_enter = new JButton("Enter");
        JButton btn_refresh = new JButton("Refresh");

        panel.add(label); // Components Added using Flow Layout
        panel.add(textField);
        panel.add(btn_refresh);

        //Adding Components to the frame.
        jframe.getContentPane().add(BorderLayout.SOUTH, panel);
        jframe.getContentPane().add(BorderLayout.NORTH, menuBar);
        jframe.getContentPane().add(BorderLayout.CENTER, textArea);
        jframe.getContentPane().setBackground((new Color(255, 251, 0)));
        jframe.setVisible(true);
    }


}
