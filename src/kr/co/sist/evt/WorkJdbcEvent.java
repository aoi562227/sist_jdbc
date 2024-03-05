package kr.co.sist.evt;

import javax.swing.*;

import kr.co.sist.dao.DBConnection;
import kr.co.sist.design.WorkJdbcDesign;
import kr.co.sist.vo.WorkJdbcVO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkJdbcEvent {
    private WorkJdbcDesign design;
    private DBConnection dbConnection;


    public WorkJdbcEvent(WorkJdbcDesign design) {
        this.design = design;

        design.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = design.getNameTextField().getText();
                int age = Integer.parseInt(design.getAgeTextField().getText());
                // Handle image selection here
                // byte[] image = getImageBytes(); // Implement this method
                // WorkJdbcVO vo = new WorkJdbcVO(0, name, age, image, getCurrentDate());
                // jdbcManager.addRecord(vo);
                // refreshList();
            }
        });

        design.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle update action
            }
        });

        design.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle delete action
            }
        });

        design.getDataList().addListSelectionListener(e -> {
            WorkJdbcVO selectedVO = design.getDataList().getSelectedValue();
            if (selectedVO != null) {
                design.getNameTextField().setText(selectedVO.getName());
                design.getAgeTextField().setText(String.valueOf(selectedVO.getAge()));
            }
        });
    }
}

