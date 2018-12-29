package com.kadima.demo.aitable.ui;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;

public class XTreeTableModel extends DefaultTreeTableModel {
//	private List<Object[]> rowdata;
	private List<String> columns;
	private int ROWS_ = 50;
	
	public XTreeTableModel() {
		init();
	}
	
	public XTreeTableModel(TreeTableNode node) {
		super(node);
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
				columns.add("");
			}
			columns.add(df.format(now.getTime()));
			now.add(Calendar.DAY_OF_YEAR, 1);
		}while(!now.getTime().after(end));
		
		root = new DefaultMutableTreeTableNode(new Object[columns.size()]);
		
		Random random = new Random(1);
		if(columns!=null && columns.size()>0) {
//			rowdata = new ArrayList<Object[]>();
			DefaultMutableTreeTableNode node = null ;
			for(int row=0;row<ROWS_;row++) {
				Object[] data = new Object[columns.size()];
				data[0] = GenerateChineseWord(random.nextInt(3)+3);
//				data[0] = "是啊";
				for(int col=1;col<columns.size();col++) {
					if(row%5==0 && row%3==1) {
						
						int limit = random.nextInt(8)+1;
						int tallavail = random.nextInt(200)+10;
						data[col] = String.format("%d(%d)",tallavail,limit);
					}else {
						data[col] = ""+random.nextInt(1000);
					}
				}
				if(row%5==0) {
					node = new DefaultMutableTreeTableNode(data);
					((DefaultMutableTreeTableNode)root).add(node);
				}else {
					DefaultMutableTreeTableNode lef = new DefaultMutableTreeTableNode(data);
					node.add(lef);
				}
//				rowdata.add(data);
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
	public Object getValueAt(Object node, int column) {
		 Object value = null;
	        if (node instanceof DefaultMutableTreeTableNode) {
	        	
	            DefaultMutableTreeTableNode mutableNode = (DefaultMutableTreeTableNode) node;
	            Object[] o = (Object[]) mutableNode.getUserObject();
	            return o[column];
	        }
	        return value;
	}
	
	public static String hexStringToString(String s) {
	    if (s == null || s.equals("")) {
	        return null;
	    }
	    s = s.replace(" ", "");
	    byte[] baKeyword = new byte[s.length() / 2];
	    for (int i = 0; i < baKeyword.length; i++) {
	        try {
	            baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    try {
	        s = new String(baKeyword, "UTF-8");
	        new String();
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
	    return s;
	}
	
	@Override
	public boolean isCellEditable(Object node, int column) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/// <summary>
    /// 随机产生常用汉字
    /// </summary>
    /// <param name="count">要产生汉字的个数</param>
    /// <returns>常用汉字</returns>
    public static String GenerateChineseWord(int count)
    {
        String chineseWords = "";
        Random rm = new Random();

        for (int i = 0; i < count; i++)
        {
            // 获取区码(常用汉字的区码范围为16-55)
            int regionCode = rm.nextInt(39)+16;

            // 获取位码(位码范围为1-94 由于55区的90,91,92,93,94为空,故将其排除)
            int positionCode;
            if (regionCode == 55)
            {
                // 55区排除90,91,92,93,94
                positionCode = rm.nextInt(89)+1;
            }
            else
            {
                positionCode = rm.nextInt(94)+1;
            }

            // 转换区位码为机内码
            int regionCode_Machine = regionCode + 160;// 160即为十六进制的20H+80H=A0H
            int positionCode_Machine = positionCode + 160;// 160即为十六进制的20H+80H=A0H

            // 转换为汉字
            byte[] bytes = new byte[] { (byte)regionCode_Machine, (byte)positionCode_Machine };
            try {
				chineseWords += new String(bytes,"GBK");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return chineseWords;
    }
	

}
