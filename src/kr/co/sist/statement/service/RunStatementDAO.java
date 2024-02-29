package kr.co.sist.statement.service;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.statement.dao.StatementDAO;
import kr.co.sist.statement.vo.EmployeeVO;



import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.jar.JarInputStream;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;

public class RunStatementDAO {
	public void menu() {
		boolean exitFlag = false;
		String inputMenu = "";

		do {
			inputMenu = JOptionPane.showInputDialog("사원정보 메뉴 선택\n1.추가 2.변경 3.삭제 4.전체사원조회 5.단일검색");
			if (inputMenu != null) {
				switch (inputMenu) {
				case "1":
					addEmp();
					break;
				case "2":
					modifyEmp();
					break;
				case "3":
					removeEmp();
					break;
				case "4":
					searchAllEmp();
					break;
				case "5":
					searchOneEmp();
					break;
				case "6":
					exitFlag = true;
					break;

				default:
					JOptionPane.showMessageDialog(null, "숫자중에 입력해주세요");
				}

			}
		} while (!exitFlag);
	}

	public void addEmp() {
		String inputData = JOptionPane.showInputDialog
				("추가할 사원 정보 입력\n사원번호, 사원명, 직무, 연봉");
		if (inputData!=null) {
			String[] tempData = inputData.split(", ");
			if(tempData.length!=4) {
				JOptionPane.showMessageDialog(null, "입력형식확인");
				return;
			}
			
			try {
				int empno = parseInt(tempData[0]);
				String ename = tempData[1];
				String job = tempData[2];
				double sal = parseDouble(tempData[3]);
				
				EmployeeVO employeeVO = new EmployeeVO(empno, ename, job, sal, null);
				
				StatementDAO statementDAO = new StatementDAO();
				try {
					statementDAO.insertEmp(employeeVO);
					JOptionPane.showMessageDialog(null, tempData[0]+"번 사원정보 추가");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "사원번호, 연봉은 숫자");
			}
			
		}
	}

	public void searchAllEmp() {
		StatementDAO statementDAO = new StatementDAO();
		try {
			List<EmployeeVO> listAllEmp = statementDAO.selectAllEmp();
			StringBuilder output = new StringBuilder();
			output.append("사원번호\t사원명\t직무\t연봉\t입사일\n");
			if (listAllEmp.isEmpty()) {
				output.append("데이터 empty");
			} else {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
				for (EmployeeVO employeeVO : listAllEmp) {
					output.append(employeeVO.getEmpno()).append("\t")
					.append(employeeVO.getEname()).append("\t")
					.append(employeeVO.getJob()).append("\t")
					.append(employeeVO.getSal()).append("\t")
					.append(simpleDateFormat.format(employeeVO.getHiredate())).append("\n");
				}
			}
			JTextArea jTextArea = new JTextArea(output.toString(), 10, 80);
			JScrollPane jScrollPane = new JScrollPane(jTextArea);
			JOptionPane.showMessageDialog(null, jScrollPane);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void searchOneEmp() {
		String inputData = JOptionPane.showInputDialog("사원검색\n사원번호입력");
		if (inputData == null) {
			JOptionPane.showMessageDialog(null, "사원번호 입력");
			return;
		}
		try {
			int empno = parseInt(inputData);
			StatementDAO statementDAO = new StatementDAO();
			EmployeeVO employeeVO = statementDAO.selectOneEmp(empno);
			StringBuilder output = new StringBuilder();
			output.append(empno).append("번 사원번호 검색 결과\n");
			if (employeeVO == null) {
				output.append("사원번호 제대로");
			}else {
				output.append("사원명 : ").append(employeeVO.getEname()).append("\n");
				output.append("직무 : ").append(employeeVO.getJob()).append("\n");
				output.append("연봉 : ").append(employeeVO.getSal()).append("\n");
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
				output.append("입사일 : ").append(simpleDateFormat.format(employeeVO.getHiredate())).append("\n");
			}
			JTextArea jTextArea = new JTextArea(output.toString(), 10, 80);
			JScrollPane jScrollPane = new JScrollPane(jTextArea);
			JOptionPane.showMessageDialog(null, jScrollPane);
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "숫자입력");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void modifyEmp() {
		String inputData = JOptionPane.showInputDialog("변경\n사원번호, 직무, 연봉 입력시 변경");
		if (inputData!=null) {
			String[] tempData=inputData.split(", ");
			if (tempData.length!=3) {
				JOptionPane.showConfirmDialog(null, "입력 똑바로");
				return;
			}
			
			try {
				EmployeeVO employeeVO = new EmployeeVO(parseInt(tempData[0]), null, tempData[1], parseDouble(tempData[2]), null);
				
				StatementDAO statementDAO = new StatementDAO();
				int cnt = statementDAO.updateEmp(employeeVO);
				
				String msg = tempData[0]+"번 사원은 존재하지 않음";
				if (cnt != 0) {
					msg=tempData[0]+"번으로 " + cnt + "건 변경됨";
				}
				JOptionPane.showMessageDialog(null, msg);
				
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "똑바로 입력 숫자");
			} catch (SQLException e) {
				e.printStackTrace();
				
				
			}
			
		}
	}

	public void removeEmp() {
	    String inputData = JOptionPane.showInputDialog("삭제할 사원번호 입력");
	    if (inputData == null) {
	        JOptionPane.showMessageDialog(null, "사원번호를 입력하세요.");
	        return;
	    }
	    try {
	        int empno = Integer.parseInt(inputData);
	        StatementDAO statementDAO = new StatementDAO();
	        int result = statementDAO.deleteEmp(empno); // deleteEmp 메서드 호출하여 사원 삭제
	        if (result > 0) {
	            JOptionPane.showMessageDialog(null, "사원이 삭제되었습니다.");
	        } else {
	            JOptionPane.showMessageDialog(null, "삭제할 사원이 없습니다.");
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "숫자를 입력하세요.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "데이터베이스 오류가 발생했습니다.");
	    }
	}


	public static void main(String[] args) {
		RunStatementDAO runStatementDAO = new RunStatementDAO();
		runStatementDAO.menu();
	}// main
}
