package com.kadima.demo.aitable.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class XTableModel extends DefaultTableModel {
	private List<Object[]> rowdata;
	private List<String> columns;
	private final int ROWS_ = 100;
	
	public XTableModel() {
		init();
	}
	
	private void init() {
		Calendar now = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("MM-dd");
		now.add(Calendar.DAY_OF_YEAR,20);
		Date end = now.getTime();
		now = Calendar.getInstance();
		
		do {
			if(columns==null) {
				columns = new ArrayList<String>();
			}
			columns.add(df.format(now.getTime()));
			now.add(Calendar.DAY_OF_YEAR, 1);
		}while(!now.getTime().after(end));
		
		Random random = new Random(1);
		if(columns!=null && columns.size()>0) {
			rowdata = new ArrayList<Object[]>();
			for(int row=0;row<ROWS_;row++) {
				Object[] data = new Object[columns.size()];
				for(int col=0;col<columns.size();col++) {
					data[col] = random.nextInt(200);
					
				}
				rowdata.add(data);
			}
		}
		
	}

	@Override
	public int getColumnCount() {
		return columns==null ?0:columns.size();
	}
	
	@Override
	public String getColumnName(int column) {
		if(columns==null) {
			return "";
		}else {
			return columns.get(column);
		}
		
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		if(rowdata==null || rowdata.size()==0 ) {
			return null;
		}else {
			return rowdata.get(row)[column];
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
//		return super.isCellEditable(row, column);
		return false;
	}
	
	@Override
	public int getRowCount() {
		if(rowdata==null || rowdata.size()==0 ) {
			return 0;
		}else {
			return rowdata.size();
		}
	}

//	@Override
//	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
//			int row, int column) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
