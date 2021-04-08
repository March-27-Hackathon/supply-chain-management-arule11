
package edu.ucalgary.ensf409;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;


public class GUI extends JFrame implements ActionListener{

  private String furnitureRequest;
  private String requestType;
  private int requestNum;

  private JLabel instructions;
  private JLabel frLabel;
  private JLabel rtLabel;
  private JLabel numLabel;

  String[] furnitureTypes = new String[]{"Select","Chair", "Desk", "Lamp", "Filing"};
  Integer[] numbers = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};

  private JComboBox<String> frInput;
  private JComboBox<String> rtInput;
  private JComboBox<Integer> numInput;

  public GUI(){
      super("Group 62 - Supply Chain Management");
      setupGUI();
      //setSize(600,170);
      setSize(400,200);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
  * Sets up the GUI for the system, so an order can be placed
  */
  public void setupGUI(){
      instructions = new JLabel("Please Select Your Furniture Order.");
      instructions.setFont(new Font("Times", Font.BOLD, 16));
      frLabel = new JLabel("Furniture:");
      frLabel.setFont(new Font("Arial", Font.PLAIN, 14));
      rtLabel = new JLabel("Category:");
      rtLabel.setFont(new Font("Arial", Font.PLAIN, 14));
      numLabel = new JLabel("Amount:");
      numLabel.setFont(new Font("Arial", Font.PLAIN, 14));

      frInput = new JComboBox<>(furnitureTypes);
      rtInput = new JComboBox<>();
      rtInput.addItem("Select a Furniture");
      numInput = new JComboBox<>(numbers);

      frInput.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          String source = (String) frInput.getSelectedItem();
          if(source.equals("Chair")){
        	 rtInput.removeAllItems();
        	 rtInput.addItem("Kneeling");
           	 rtInput.addItem("Task");
           	 rtInput.addItem("Mesh");
           	 rtInput.addItem("Executive");
          	 rtInput.addItem("Ergonomic");
          }else if(source.equals("Desk")){
        	 rtInput.removeAllItems();
         	 rtInput.addItem("Standing");
          	 rtInput.addItem("Adjustable");
          	 rtInput.addItem("Traditional");
          }else if(source.equals("Lamp")){
        	 rtInput.removeAllItems();
        	 rtInput.addItem("Desk");
         	 rtInput.addItem("Study");
         	 rtInput.addItem("Swing Arm");
          }else if(source.equals("Filing")){
        	 rtInput.removeAllItems();
        	 rtInput.addItem("Small");
        	 rtInput.addItem("Medium");
        	 rtInput.addItem("Large");
          }else{
        	 rtInput.removeAllItems();
        	 rtInput.addItem("Select a Furniture");
          }
        }
      });

      JButton submitInfo = new JButton("Submit");
      submitInfo.addActionListener(this);

      JPanel headerPanel = new JPanel();
      headerPanel.setLayout(new FlowLayout());
      //headerPanel.setBackground(new Color(224,224,224));
      headerPanel.setBackground(new Color(186,213,255));
      //headerPanel.setBackground(new Color(165, 214, 109));

      JPanel clientPanel = new JPanel();
      clientPanel.setLayout(new FlowLayout());
      //clientPanel.setBackground(new Color(203,203,203));
      clientPanel.setBackground(new Color(209,227,255));
      //clientPanel.setBackground(new Color(209,240,175));

      JPanel submitPanel = new JPanel();
      submitPanel.setLayout(new FlowLayout());
      //submitPanel.setBackground(new Color(224,224,224));
      submitPanel.setBackground(new Color(186,213,255));
      //submitPanel.setBackground(new Color(165, 214, 109));

      headerPanel.add(instructions);
      clientPanel.add(frLabel);
      clientPanel.add(frInput);
      clientPanel.add(rtLabel);
      clientPanel.add(rtInput);
      clientPanel.add(numLabel);
      clientPanel.add(numInput);
      submitPanel.add(submitInfo);

      this.add(headerPanel, BorderLayout.NORTH);
      this.add(clientPanel, BorderLayout.CENTER);
      this.add(submitPanel, BorderLayout.PAGE_END);
  }


  /**
  * Produces the order and displays it in a new window on the screen
  * @param action : the action event
  */
  public void actionPerformed(ActionEvent event){
      furnitureRequest = (String)frInput.getSelectedItem();
      requestType = (String)rtInput.getSelectedItem();
      requestNum = (Integer)numInput.getSelectedItem();

      if(validInput()){
        FurnitureDataBase item = new FurnitureDataBase(requestType, furnitureRequest, requestNum);
        item.initializeConnection();
        item.addFurniture();
        String output = item.runGUI();

        JOptionPane.showMessageDialog(this, output, "Order Output", JOptionPane.PLAIN_MESSAGE);
      }
  }


  /**
  * Checks that an order was selected
  */
  private boolean validInput(){
      boolean allInputValid = true;

      if(furnitureRequest.equals("Select")){
        allInputValid = false;
        JOptionPane.showMessageDialog(this, "Invalid order request. Please select a furniture, category and amount.");
      }

      return allInputValid;
  }

  public static void main(String[] args) {
      EventQueue.invokeLater(() -> {
          new GUI().setVisible(true);
      });
  }

}
