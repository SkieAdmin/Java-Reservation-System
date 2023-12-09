package bedfghdfgghdghd;

import java.awt.BorderLayout;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Scanner;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddReservation extends JFrame {
    DefaultTableModel tableModel;

    Double ThetotalCost; 
    Double remainCost;
    JTextPane tempReceipt = new JTextPane();
    String official_receipt = "Washbuck Official Receipt\n"
    		+ "------------------------------------\n"
    		+ "Order:\n";
    
	private JPanel contentPane;
	private JFrame frame;
	private JTable table;
	private JTextField jtxtRefNo;
	private JTextField jtxtFirstname;
	private JTextField jtxtSurname;
	private JTextField jtxtAddress;
	private JTextField jtxtPostCode;
	private JTextField jtxtTelephone;
	private JTextField jtxtDateReg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddReservation frame = new AddReservation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
    JLabel resultLabel = new JLabel("Result:");
    
    public void showPaymentDialog() {
        // Create an input dialog
        String inputAmount = JOptionPane.showInputDialog(null, "Enter the amount to pay:");

        // Check if the user clicked "Cancel" or closed the dialog
        if (inputAmount == null) {
            System.out.println("Payment canceled.");
        } else {
            // Process the entered amount (you can perform any further actions here)
            double amountToPay = Double.parseDouble(inputAmount);
            

            // Show a confirmation dialog
            int option = JOptionPane.showConfirmDialog(null, "Do you want to proceed with the payment?", "Payment Confirmation", JOptionPane.YES_NO_OPTION);

            // Check if the user clicked "Yes"
            if (option == JOptionPane.YES_OPTION) {
            	remainCost = ThetotalCost - amountToPay;
                resultLabel.setText("Total Exchage: (₱" + remainCost + ")");
                System.out.println("Payment successful!");
                
            } else {
                System.out.println("Payment canceled.");
            }
        }
    }
	public AddReservation() {
        JFrame frame = new JFrame("WashBucks");
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel welcomeLabel = new JLabel("Welcome to Washbucks Ordering System!");
        welcomeLabel.setBounds(0, 0, 0, 0);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel itemPanel = new JPanel();
        itemPanel.setBounds(0, 552, 1486, 249);

        JCheckBox pastaCheckBox = new JCheckBox("Pasta      ₱20");
        pastaCheckBox.setBounds(0, 13, 129, 21);


        // Existing Food Items
        JCheckBox croissantCheckBox = new JCheckBox("Bread Croissant - ₱62");
        croissantCheckBox.setBounds(0, 36, 129, 21);
        JLabel croissantQuantityLabel = new JLabel("Enter Quantity:");
        croissantQuantityLabel.setBounds(139, 36, 88, 21);
        JTextField croissantQuantityField = new JTextField(5);
        croissantQuantityField.setBounds(237, 36, 239, 21);

        JCheckBox cakeCheckBox = new JCheckBox("Red Velvet Cake - ₱142");
        cakeCheckBox.setBounds(0, 59, 133, 21);
        JLabel cakeQuantityLabel = new JLabel("Enter Quantity:");
        cakeQuantityLabel.setBounds(139, 59, 88, 21);
        JTextField cakeQuantityField = new JTextField(5);
        cakeQuantityField.setBounds(237, 59, 239, 21);

        JCheckBox coffeeCheckBox = new JCheckBox("Cappuccino      ₱79");
        coffeeCheckBox.setBounds(0, 82, 129, 21);
        JLabel coffeeQuantityLabel = new JLabel("Enter Quantity:");
        coffeeQuantityLabel.setBounds(139, 82, 88, 21);
        JTextField coffeeQuantityField = new JTextField(5);
        coffeeQuantityField.setBounds(237, 82, 239, 21);

        // New Food Items
        JCheckBox cookiesCheckBox = new JCheckBox("Cookies - ₱20");
        cookiesCheckBox.setBounds(0, 105, 102, 21);
        JLabel cookiesQuantityLabel = new JLabel("Enter Quantity:");
        cookiesQuantityLabel.setBounds(139, 105, 88, 21);
        JTextField cookiesQuantityField = new JTextField(5);
        cookiesQuantityField.setBounds(237, 105, 239, 21);

        JCheckBox cinnamonRollsCheckBox = new JCheckBox("Cinnamon Rolls      ₱58");
        cinnamonRollsCheckBox.setBounds(0, 128, 133, 21);
        JLabel cinnamonRollsQuantityLabel = new JLabel("Enter Quantity:");
        cinnamonRollsQuantityLabel.setBounds(139, 128, 88, 21);
        JTextField cinnamonRollsQuantityField = new JTextField(5);
        cinnamonRollsQuantityField.setBounds(237, 128, 239, 21);

        JCheckBox cupcakeCheckBox = new JCheckBox("Cupcake      ₱30");
        cupcakeCheckBox.setBounds(0, 151, 133, 21);
        JLabel cupcakeQuantityLabel = new JLabel("Enter Quantity:");
        cupcakeQuantityLabel.setBounds(139, 151, 88, 21);
        JTextField cupcakeQuantityField = new JTextField(5);
        cupcakeQuantityField.setBounds(237, 151, 239, 21);

        JCheckBox LatteCheckBox = new JCheckBox("Latte      ₱63");
        LatteCheckBox.setBounds(0, 174, 102, 21);
        JLabel LatteQuantityLabel = new JLabel("Enter Quantity:");
        LatteQuantityLabel.setBounds(139, 182, 88, 21);
        JTextField LatteQuantityField = new JTextField(5);
        LatteQuantityField.setBounds(237, 182, 239, 21);

        JCheckBox exteriorBox = new JCheckBox("Exterior Wash   ₱500");
        exteriorBox.setBounds(482, 13, 129, 21);


        JCheckBox fullBox = new JCheckBox("Full Car Wash  ₱750");
        fullBox.setBounds(482, 59, 129, 21);
   

        JCheckBox oilBox = new JCheckBox("Change Oil  ₱ 450");
        oilBox.setBounds(482, 105, 239, 21);
        JLabel oilQuantityLabel = new JLabel("Enter Quantity:");
        frame.getContentPane().setLayout(null);


       
        
        frame.getContentPane().add(welcomeLabel);
        itemPanel.setLayout(null);
        

        tempReceipt.setEditable(false);
        tempReceipt.setBounds(1119, 130, 335, 100);
        itemPanel.add(tempReceipt);
        itemPanel.add(pastaCheckBox);
        JLabel pastaQuantityLabel = new JLabel("Enter Quantity:");
        pastaQuantityLabel.setBounds(139, 13, 88, 21);
        itemPanel.add(pastaQuantityLabel);
        JTextField pastaQuantityField = new JTextField(5);
        pastaQuantityField.setBounds(237, 13, 239, 21);
        itemPanel.add(pastaQuantityField);
        itemPanel.add(croissantCheckBox);
        itemPanel.add(croissantQuantityLabel);
        itemPanel.add(croissantQuantityField);
        itemPanel.add(cakeCheckBox);
        itemPanel.add(cakeQuantityLabel);
        itemPanel.add(cakeQuantityField);
        itemPanel.add(coffeeCheckBox);
        itemPanel.add(coffeeQuantityLabel);
        itemPanel.add(coffeeQuantityField);
        itemPanel.add(cookiesCheckBox);
        itemPanel.add(cookiesQuantityLabel);
        itemPanel.add(cookiesQuantityField);
        itemPanel.add(cinnamonRollsCheckBox);
        itemPanel.add(cinnamonRollsQuantityLabel);
        itemPanel.add(cinnamonRollsQuantityField);
        itemPanel.add(cupcakeCheckBox);
        itemPanel.add(cupcakeQuantityLabel);
        itemPanel.add(cupcakeQuantityField);
        itemPanel.add(LatteCheckBox);
        itemPanel.add(LatteQuantityLabel);
        itemPanel.add(LatteQuantityField);
        itemPanel.add(exteriorBox);
        itemPanel.add(fullBox);
        itemPanel.add(oilBox);
     

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 801, 1486, 31);
        JButton orderButton = new JButton("Order");
        JButton clearButton = new JButton("Clear");
        buttonPanel.add(orderButton);
        buttonPanel.add(clearButton);

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                pastaCheckBox.setSelected(false);
                pastaQuantityField.setText("");
                croissantCheckBox.setSelected(false);
                croissantQuantityField.setText("");
                cakeCheckBox.setSelected(false);
                cakeQuantityField.setText("");
                coffeeCheckBox.setSelected(false);
                coffeeQuantityField.setText("");
                cookiesCheckBox.setSelected(false);
                cookiesQuantityField.setText("");
                cinnamonRollsCheckBox.setSelected(false);
                cinnamonRollsQuantityField.setText("");
                cupcakeCheckBox.setSelected(false);
                cupcakeQuantityField.setText("");
                LatteCheckBox.setSelected(false);
                LatteQuantityField.setText("");
                exteriorBox.setSelected(true);
                fullBox.setSelected(true);
                oilBox.setSelected(true);
                frame.setVisible(true);
                resultLabel.setText("Result:");
                tableModel.setRowCount(0); // Clear the table when Clear button is clicked
            }
        });

        tableModel = new DefaultTableModel();
        JTable receiptTable = new JTable(tableModel);
        tableModel.addColumn("Item");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Cost");

        JScrollPane scrollPane = new JScrollPane(receiptTable);
        scrollPane.setBounds(0, 0, 1486, 550);

        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                tableModel.setRowCount(0); // Clear table before adding items
                double totalCost = 0;
                tempReceipt.setText(official_receipt);
                addRowToTable(pastaCheckBox, pastaQuantityField, 20, "Pasta");
                addRowToTable(croissantCheckBox, croissantQuantityField, 69, "Bread Croissant");
                addRowToTable(cakeCheckBox, cakeQuantityField, 142, "Red Velvet Cake");
                addRowToTable(coffeeCheckBox, coffeeQuantityField, 79, "Cappuccino");
                addRowToTable(cookiesCheckBox, cookiesQuantityField, 20, "Cookies");
                addRowToTable(cinnamonRollsCheckBox, cinnamonRollsQuantityField, 50, "Cinnamon Rolls");
                addRowToTable(cupcakeCheckBox, cupcakeQuantityField, 30, "Cupcake");
                addRowToTable(LatteCheckBox, LatteQuantityField, 63, "Latte");
                //addRowToTable(exteriorBox, exteriorQuantityField, 500, "Exterior Car Wash");
                //addRowToTable(fullBox, fullQuantityField, 750, "Full CarWash");
                //addRowToTable(oilBox, oilQuantityField, 450, "Oil Change");
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    totalCost += Double.parseDouble(
                            tableModel.getValueAt(i, 2).toString().substring(1)
                    ); // Calculate total cost
                }
                resultLabel.setText("Total Cost: ₱" + totalCost);
                ThetotalCost = totalCost;
            }
        });

        frame.getContentPane().add(itemPanel);
        

        resultLabel.setBounds(10, 213, 446, 17);
        itemPanel.add(resultLabel);
        
        JButton btnPay = new JButton("Pay");
        btnPay.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showPaymentDialog();
        	}
        });
        btnPay.setBounds(486, 162, 121, 44);
        itemPanel.add(btnPay);
        
        JButton btnAdmin = new JButton("Admin");
        btnAdmin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		createAndShowGUI();
        	}
        });
        btnAdmin.setBounds(611, 162, 121, 44);
        itemPanel.add(btnAdmin);
        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(buttonPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500,879);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Admin Panel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        JTextArea transactionDetailsArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(transactionDetailsArea);
        JButton clearButton = new JButton("Clear Receipt");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transactionDetailsArea.setText(""); // Clear the text area
            }
        });
        JButton printButton = new JButton("Print Receipt");
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your code here for printing the receipt
                // This is just a placeholder
                System.out.println("Printing receipt...");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(clearButton);
        buttonPanel.add(printButton);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    
    private void addRowToTable(JCheckBox checkBox, JTextField quantityField, double costPerItem, String itemName) {

    	if (checkBox.isSelected() && !quantityField.getText().isEmpty()) {
            Scanner scanner = new Scanner(quantityField.getText());
            if (scanner.hasNextInt()) {
                int quantity = scanner.nextInt();
                double itemCost = costPerItem * quantity;
                tableModel.addRow(new Object[]{itemName, quantity, "₱" + itemCost});
            }
            String Brub = "[ " + itemName + " ] - ₱" + costPerItem + " - x" + quantityField.getText() + "\n";
            tempReceipt.setText(tempReceipt.getText() + "\n " + Brub + tempReceipt.getText());
            scanner.close();
        }
    }
}