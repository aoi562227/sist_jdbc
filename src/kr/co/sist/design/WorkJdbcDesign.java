package kr.co.sist.design;

import javax.swing.*;

import kr.co.sist.dao.DBConnection;
import kr.co.sist.vo.WorkJdbcVO;

import java.awt.*;

public class WorkJdbcDesign extends JFrame {
    private JTextField nameTextField, ageTextField;
    private JButton addButton, updateButton, deleteButton;
    private JList<WorkJdbcVO> dataList;
    private DefaultListModel<WorkJdbcVO> listModel;
    private DBConnection dbConnection;

    public WorkJdbcDesign(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        setTitle("Work JDBC App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Components initialization
        nameTextField = new JTextField(20);
        ageTextField = new JTextField(5);
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        listModel = new DefaultListModel<>();
        dataList = new JList<>(listModel);

        // Layout setup
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameTextField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageTextField);
        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);

        JScrollPane listScrollPane = new JScrollPane(dataList);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(listScrollPane, BorderLayout.CENTER);

        add(mainPanel);

        // Add action listeners for buttons
        addButton.addActionListener(e -> {
            String name = nameTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());
            // Implement database insertion here
        });

        updateButton.addActionListener(e -> {
            // Implement database update here
        });

        deleteButton.addActionListener(e -> {
            // Implement database deletion here
        });

        // Add list selection listener
        dataList.addListSelectionListener(e -> {
            WorkJdbcVO selectedVO = dataList.getSelectedValue();
            if (selectedVO != null) {
                nameTextField.setText(selectedVO.getName());
                ageTextField.setText(String.valueOf(selectedVO.getAge()));
            }
        });
    }

    public DefaultListModel<WorkJdbcVO> getListModel() {
        return listModel;
    }
    public static void main(String[] args) {
		new WorkJdbcDesign();
	}//main
}


