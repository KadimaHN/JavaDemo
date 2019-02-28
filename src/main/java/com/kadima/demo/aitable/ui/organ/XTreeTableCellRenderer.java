package com.kadima.demo.aitable.ui.organ;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JXTreeTable;

@SuppressWarnings("serial")
public class XTreeTableCellRenderer extends DefaultTableCellRenderer {
	private JTree tree;
	private JXTreeTable treetable;
	
	XTreeTableCellRenderer(JXTreeTable treetable){
		this.tree = (JTree) treetable.getCellRenderer(0, 0);
		this.treetable = treetable;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {	
		Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if(table instanceof JXTreeTable) {
			TreePath path = tree.getPathForRow(row);
			if(path!=null) {
				if(path.getPathCount()==2) { //如果是一级节点
					JPanel panel = new JPanel(new BorderLayout());
					panel.add(comp,BorderLayout.CENTER);
					panel.setOpaque(true);
					panel.setBackground(XTreeTableManager.GROUP_ROW_BACKGROUND);
					panel.setBorder(new CompoundBorder(new EmptyBorder(20, 0, 0, 0), 
							new MatteBorder(0, 0, 1, 1, (Color) new Color(128, 128, 128))));
					return panel;
				}else {
					//对二级表格进行定制，以达到区分目的
					TreePath parentpath = path.getParentPath();
//					int parentrow = xtreetable.getRowForPath(parentpath);
					int childcount = tree.getModel().getChildCount(parentpath.getLastPathComponent());
					int indexofparent = tree.getModel().getIndexOfChild(parentpath.getLastPathComponent(),
							path.getLastPathComponent());
					Color matteColot = XTreeTableManager.LEFT_BORDER_BACKGROUND;
					int top=0,left=0,bottom=0,right=0;
					int width = 4;
					if(indexofparent==0) {
						top = width;
					}
					if(indexofparent==childcount-1) {
						bottom = width;
					}
					if(column==1) {
						left = width;
					}
					if(column==table.getColumnCount()-1) {
						right = width;
					}
					((JLabel)comp).setBorder(new MatteBorder(top, left, bottom, right, matteColot));
					if(((Integer)value)%2==0) {
						
					}
				}
			}

		}

		return comp;
	}
}
