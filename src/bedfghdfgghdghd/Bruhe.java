
package bedfghdfgghdghd;

//GUI Components from Swing library
import javax.swing.*;
//AWT for basic windowing components
import java.awt.*;
//ActionEvent and ActionListener for handling events
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//DefaultTableModel for managing table data
import javax.swing.table.DefaultTableModel;

//For working with dates
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


//For file-related operations
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

//ArrayList for dynamic arrays
import java.util.ArrayList;

//Input/Output classes for handling file operations
import java.io.*;

//WindowAdapter and WindowEvent for handling window events
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;




class PelicanFunction {

    public int cottageCount;

 // Function to get the current date in the format "MM/dd/yy".
    private String GetCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        return currentDate.format(formatter);
    }

    // Function to show a save dialog and save the text from a JEditorPane to a file.
    public void showSaveDialogAndSaveText(JEditorPane textArea) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(null, "Text saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving text to file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Additional miscellaneous function to calculate fees based on application type and parameter.
    
    private int AdditionalMisc(String application, Boolean param) {
    	if (application == "electric")
    	{
    		if (param == true) 
        		return 50;
    		return 0;
    	}
    	else if (application == "karaoke")
    	{
    		if (param == true) 
        		return 250;	    		
    		return 0;
    	}
    	else    	
    		return 0;    	
    }
    
    
 // Function to save the contents of a JTable to a file.
    public void saveTable(JTable table, String filePath) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write column headers
            for (int i = 0; i < model.getColumnCount(); i++) {
                writer.write(model.getColumnName(i));
                if (i < model.getColumnCount() - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();

            // Write data
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    Object value = model.getValueAt(row, col);
                    if (value != null) {
                        writer.write(value.toString());
                    }
                    if (col < model.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }

            System.out.println("Table saved successfully to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 // Function to load data from a file into a JTable.
    public void loadTable(JTable table, String filePath) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Clear existing rows in the table
        model.setRowCount(0);

        try {
            // Read all lines from the file
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8));

            // Read column headers
            String[] headers = lines.get(0).split(",");
            model.setColumnIdentifiers(headers);

            // Read data starting from the second line
            for (int i = 1; i < lines.size(); i++) {
                String[] data = lines.get(i).split(",");
                model.addRow(data);
            }

            System.out.println("Table loaded successfully from: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        
 // Function to add a reservation to a JTable and return a receipt as a string.
    public String Add_Reservation(JTable table, int CottageNo, String name, String Date, String CottageType,
            boolean IsKaraokeAccess, boolean IsElectricAccess) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[] { CottageNo, name, Date, CottageType, IsKaraokeAccess, IsElectricAccess, "Reserved" });

        if (table.getSelectedRow() == -1) {
            if (table.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Reservation confirmed", "Oceanview Resort Reservation System",
                        JOptionPane.OK_OPTION);
                cottageCount = cottageCount + 1;
            }
        }
        int ElectricBill = AdditionalMisc("electric", IsElectricAccess);
        int KaraokeBill = AdditionalMisc("karaoke", IsKaraokeAccess);
        

        int cottagevalue;
    	if (CottageType == "Small (200)")
    		cottagevalue = 200;
    	else
    		cottagevalue = 400;
    	
        int TotalFeez = ElectricBill + KaraokeBill + cottagevalue + 50;

        
        //Official Receipt
        String Receipt = 
        		  "-----------------------------------------------------\n"
        		+ "              [ Reservation Receipt ]                \n"
        		+ "-----------------------------------------------------\n"
        		+ "Customer Name: " + name + "\n"
        		+ "Date Reserved: " + GetCurrentDate() + "\n"
        		+ "Date Use: " + Date + "\n"
        		+ "Cottage No.: " + CottageNo + "\n"
        		+ "\n"
        		+ "Cottage Type: " + CottageType + "\n"
        		+ "Electric Fee: " +  ElectricBill + "\n"
        		+ "Karaoke Fee: " +  KaraokeBill + "\n"
        		+ "Entrance Fee: " +  50 + "\n"
        		+ "\n"
        		+ "Total Fee: " + TotalFeez + "\n"
        		+ "Status: Reserved \n"
        		+ "\n"
        		+ "-----------------------------------------------------\n"
        		+ "Thank you for choosing Oceanview Resort\n";
        
        return Receipt;
    }
}

public class Bruhe extends JFrame {
    private JButton deleteButton, addReservationsButton;
    private JLabel titleLabel, versionLabel;
    private JTable bruhtable;
    private JScrollPane scrollPane;

    PelicanFunction pelicanFunction = new PelicanFunction();

    public Bruhe() {
    	setResizable(false);
        setTitle("Oceanview Resort Reservation System");
        // Add a WindowAdapter to handle window closing
        this.addWindowListener(new MyWindowAdapter());
        
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kerle\\Downloads\\OIG (4) - Copy.jpg"));
        initComponents();
        
    }
    JEditorPane printLog = new JEditorPane();
    private static String filePath = "Database_Oceanview.csv";
    
    private class MyWindowAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            // Code to be executed when the window is closing
            System.out.println("Closing the application. Performing cleanup tasks.");
            // You can add your cleanup code or any other code here
        	pelicanFunction.saveTable(bruhtable, filePath);
            // Exiting the application
            System.exit(0);
        }
    }
    private void createDynamicForm() {
        JFrame dynamicForm = new JFrame("Add Reservation");


        String[] cottageItems = new String[20];
        for (int i = 1; i <= 20; i++) {
            cottageItems[i - 1] = "Cottage " + i;
        }

        JComboBox<String> comboBox2 = new JComboBox<>(cottageItems);

        JRadioButton radioButton1 = new JRadioButton("Small Cottage ( 200 Pesos )");
        JRadioButton radioButton2 = new JRadioButton("Large Cottage ( 400 Pesos )");
        ButtonGroup cottageTypeGroup = new ButtonGroup();
        cottageTypeGroup.add(radioButton1);
        cottageTypeGroup.add(radioButton2);

        JCheckBox checkBox1 = new JCheckBox("Access to Electricity (+50 Pesos)");
        JCheckBox checkBox2 = new JCheckBox("Access to Karaoke (+250 Pesos)");

        JLabel label3 = new JLabel("Cottage:");
        JTextField CustomerNameInput = new JTextField();
        JLabel CustomerName = new JLabel("Customer Name:");
        JLabel label1 = new JLabel("Date (mm/dd/yy):");
        JTextField DateUsage = new JTextField();
        JButton button2 = new JButton("Add to List");

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            	String CottageTypeC;
            	if (radioButton1.isSelected())
            		CottageTypeC = "Small (200)";
            	else if (radioButton2.isSelected()) 
            		CottageTypeC = "Large (400)";
            	else
            	{
     	           JOptionPane.showMessageDialog(null, "Please Select Cottage Type","Oceanview Resort Reservation System", JOptionPane.ERROR_MESSAGE);
                   return;
            	}
                int selectedIndex = comboBox2.getSelectedIndex();                
            	boolean IsElectricAccessCheck = checkBox1.isSelected();
            	boolean checkBox2 = checkBox1.isSelected();        	
            	int value_cottage = selectedIndex + 1;
            	
            	String ReceiptTotal = printLog.getText() + pelicanFunction.Add_Reservation(bruhtable, value_cottage, CustomerNameInput.getText(), DateUsage.getText(), CottageTypeC, IsElectricAccessCheck, checkBox2);
            	printLog.setText(ReceiptTotal); 
            	dynamicForm.dispose();
            }
        });

        GroupLayout layout = new GroupLayout(dynamicForm.getContentPane());
        dynamicForm.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(CustomerName)
                                                        .addComponent(label1)
                                                        .addComponent(label3))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(comboBox2, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(DateUsage, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(CustomerNameInput)))
                                        .addComponent(checkBox1)
                                        .addComponent(checkBox2)
                                        .addComponent(radioButton1)
                                        .addComponent(radioButton2)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(CustomerNameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CustomerName))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(DateUsage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label1))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label3))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioButton1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioButton2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkBox1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkBox2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button2, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                .addContainerGap())
        );

        dynamicForm.pack();
        dynamicForm.setLocationRelativeTo(null);
        dynamicForm.setSize(339, 346);
        dynamicForm.setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(984, 614);
        getContentPane().setBackground(new Color(22, 22, 22));
        getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(22, 22, 22));
        mainPanel.setLayout(null);

        addReservationsButton = new JButton("Add Reservations");
        addReservationsButton.setBounds(22, 445, 190, 56);
        addReservationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle add reservations button click
                createDynamicForm();
            }
        });
        mainPanel.add(addReservationsButton);


        
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(222, 445, 190, 56);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle delete button click
                DefaultTableModel model = (DefaultTableModel) bruhtable.getModel();
                if (bruhtable.getSelectedRow() == -1) {
                    if (bruhtable.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(null, "No data to delete ", "Oceanview Resort Reservation System",
                                JOptionPane.OK_OPTION);
                    } else {
                        JOptionPane.showMessageDialog(null, "Select a row to delete ", "Oceanview Resort Reservation System",
                                JOptionPane.OK_OPTION);
                    }
                } else {
                    model.removeRow(bruhtable.getSelectedRow());
                }
            }
        });
        mainPanel.add(deleteButton);

        titleLabel = new JLabel("Oceanview Resort ( Reservation System )");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(255, 128, 0));
        titleLabel.setBounds(119, 22, 437, 33);
        mainPanel.add(titleLabel);

        versionLabel = new JLabel("Version: 1.0.0");
        versionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        versionLabel.setForeground(new Color(255, 128, 0));
        versionLabel.setBounds(119, 49, 139, 24);
        mainPanel.add(versionLabel);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(22, 125, 876, 309);
        mainPanel.add(scrollPane);

        bruhtable = new JTable();
        scrollPane.setViewportView(bruhtable);
        bruhtable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Cottage No", "Name of Reservant",
                "Date Use", "Cottage Type", "Karaoke Access", "Electricity", "STATUS" }) {
            Class[] columnTypes = new Class[] { String.class, String.class, Object.class, Object.class, Object.class,
                    Object.class, Object.class };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        bruhtable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\kerle\\OneDrive\\Pictures\\gdfghdfgdfgdfg.jpg"));
        lblNewLabel.setBounds(22, 22, 87, 80);
        mainPanel.add(lblNewLabel);

        JLabel lblCopyrightcPandadevelopment = new JLabel(
                "Copyright (C) Panda-Development, All Right in Service");
        lblCopyrightcPandadevelopment.setForeground(new Color(255, 128, 0));
        lblCopyrightcPandadevelopment.setFont(new Font("Arial", Font.BOLD, 9));
        lblCopyrightcPandadevelopment.setBounds(119, 66, 270, 24);
        mainPanel.add(lblCopyrightcPandadevelopment);

        tabbedPane.addTab("Main", mainPanel);


        JPanel adminPanel = new JPanel();
        adminPanel.setBackground(new Color(22, 22, 22));

        tabbedPane.addTab("Admin Panel", adminPanel);
        adminPanel.setLayout(null);
        
        JLabel lblOceanviewResort = new JLabel("Oceanview Resort ( Administration )");
        lblOceanviewResort.setForeground(new Color(255, 128, 0));
        lblOceanviewResort.setFont(new Font("Arial", Font.BOLD, 22));
        lblOceanviewResort.setBounds(119, 22, 437, 33);
        adminPanel.add(lblOceanviewResort);
        
        JLabel versionLabel_1 = new JLabel("Version: 1.0.0");
        versionLabel_1.setForeground(new Color(255, 128, 0));
        versionLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
        versionLabel_1.setBounds(119, 49, 139, 24);
        adminPanel.add(versionLabel_1);
        
        JLabel lblCopyrightcPandadevelopment_1 = new JLabel("Copyright (C) Panda-Development, All Right in Service");
        lblCopyrightcPandadevelopment_1.setForeground(new Color(255, 128, 0));
        lblCopyrightcPandadevelopment_1.setFont(new Font("Arial", Font.BOLD, 9));
        lblCopyrightcPandadevelopment_1.setBounds(119, 66, 270, 24);
        adminPanel.add(lblCopyrightcPandadevelopment_1);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\kerle\\OneDrive\\Pictures\\gdfghdfgdfgdfg.jpg"));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1.setBounds(22, 22, 87, 80);
        adminPanel.add(lblNewLabel_1);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(35, 142, 872, 301);
        adminPanel.add(scrollPane_1);
        printLog.setEditable(false);
        scrollPane_1.setViewportView(printLog);
        
        JLabel versionLabel_1_1 = new JLabel("Logbook Reservation's Receipt");
        versionLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        versionLabel_1_1.setForeground(new Color(255, 128, 0));
        versionLabel_1_1.setFont(new Font("Arial", Font.BOLD, 14));
        versionLabel_1_1.setBounds(32, 113, 875, 24);
        adminPanel.add(versionLabel_1_1);
        
        JButton btnSaveReceipt = new JButton("Save Receipt");
        btnSaveReceipt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Shows Save Prompt so we can save the Receipt
        		pelicanFunction.showSaveDialogAndSaveText(printLog);
        	}
        });
        btnSaveReceipt.setBounds(599, 453, 149, 56);
        adminPanel.add(btnSaveReceipt);
        
        JButton btnClearReceipt = new JButton("Clear Receipt");
        btnClearReceipt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		printLog.setText(""); //Clear the Receipt
        	}
        });
        btnClearReceipt.setBounds(440, 453, 149, 56);
        adminPanel.add(btnClearReceipt);
        
        JButton btnDeleteDatabase = new JButton("Reset Database");
        btnDeleteDatabase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                // Create a File object
                File fileToDelete = new File(filePath);

                // Check if the file exists
                if (fileToDelete.exists()) {
                    // Ask the user for confirmation using a message box
                    int result = JOptionPane.showConfirmDialog(
                            null,
                            "Do you want to Reset the database?",
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (result == JOptionPane.YES_OPTION) {
                        // Delete the file
                        if (fileToDelete.delete()) {
                            JOptionPane.showMessageDialog(null, "Database Reset successfully.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Unable to Reset the Database.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Reset canceled by the user.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Database does not exist.");
                }
        	}
        });
        btnDeleteDatabase.setBounds(758, 453, 149, 56);
        adminPanel.add(btnDeleteDatabase);

        tabbedPane.setBounds(10, 11, 940, 553);
        getContentPane().add(tabbedPane);

        setVisible(true);
        


        // Check if the file exists, then load the table, otherwise save the table
        if (new File(filePath).exists()) {
            pelicanFunction.loadTable(bruhtable, filePath);
        } 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Bruhe();
            }
        });
    }
}
