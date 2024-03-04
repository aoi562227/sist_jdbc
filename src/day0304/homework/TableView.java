// TableView.java
package day0304.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class TableView extends JFrame {
    private JRadioButton[] radioButtons;
    private ButtonGroup buttonGroup;

    public TableView() {
        setTitle("Table View");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        // 테이블명 조회
        List<String> tableNames = null;
        try {
            tableNames = TableDAO.getInstance().selectAllTab();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "테이블을 조회할 수 없습니다.");
            System.exit(1);
        }

        buttonGroup = new ButtonGroup();
        radioButtons = new JRadioButton[tableNames.size()];

        for (int i = 0; i < tableNames.size(); i++) {
            radioButtons[i] = new JRadioButton(tableNames.get(i));
            panel.add(radioButtons[i]);
            buttonGroup.add(radioButtons[i]);
        }

        // 확인 버튼 추가
        JButton confirmButton = new JButton("확인");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JRadioButton radioButton : radioButtons) {
                    if (radioButton.isSelected()) {
                        JOptionPane.showMessageDialog(null, "선택된 테이블명: " + radioButton.getText());
                        break;
                    }
                }
            }
        });
        panel.add(confirmButton);

        getContentPane().add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TableView();
    }
}
