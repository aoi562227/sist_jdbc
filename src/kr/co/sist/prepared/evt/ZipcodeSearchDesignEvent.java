package kr.co.sist.prepared.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.design.ZipcodeSearchDesign;
import kr.co.sist.prepared.dao.ZipcodeDAO;
import kr.co.sist.vo.ZipcodeVO;

public class ZipcodeSearchDesignEvent extends WindowAdapter implements ActionListener {
	private ZipcodeSearchDesign zsd;

	public ZipcodeSearchDesignEvent(ZipcodeSearchDesign zsd) {
		this.zsd = zsd;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		zsd.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String dong = zsd.getJtfDong().getText().trim();
		if (dong.isEmpty()) {
			JOptionPane.showMessageDialog(zsd, "동 이름은 필수입력");
		}
		setZipcode(dong);
		
		zsd.getJtfDong().setText("");
	}

	private void setZipcode(String dong) {
		ZipcodeDAO zDAO = ZipcodeDAO.getInstance();
		
		try {
			List<ZipcodeVO> list = zDAO.selectZipcode(dong);
			
			DefaultTableModel dtm = zsd.getDtmJtabResult();
			
			dtm.setRowCount(0);
			
			String[] rowData = null;
			ZipcodeVO zVO=null;
			StringBuilder sbAddr = new StringBuilder();
			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(zsd, "검색결과가 없습니다");
				return;
			}
			for (int i = 0; i < list.size(); i++) {
				
				zVO=list.get(i);
				sbAddr.append(zVO.getSido()).append(" ").append(zVO.getGugun()).append(" ").append(zVO.getDong()).append(" ").append(zVO.getBunji());
				
				rowData = new String[2];
				rowData[0] = zVO.getZipcode();//우편번호 할당
				rowData[1] = sbAddr.toString();//주소할당
				
				dtm.addRow(rowData);
				
				sbAddr.delete(0, sbAddr.length());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
