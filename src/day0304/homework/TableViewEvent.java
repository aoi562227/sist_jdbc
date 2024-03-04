package day0304.homework;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TableViewEvent extends WindowAdapter{
	TableView tv;
	public TableViewEvent(TableView tv) {
		this.tv = tv;
	}
	

	@Override
	public void windowClosing(WindowEvent e) {
		
	}
	
	
}
