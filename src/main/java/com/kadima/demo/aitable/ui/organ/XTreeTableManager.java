package com.kadima.demo.aitable.ui.organ;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.renderer.DefaultTreeRenderer;

public class XTreeTableManager extends MouseAdapter  {

	private JXTreeTable treetable;
	private JTree tree;
//	private DefaultMutableTreeTableNode root;
	/**
	 *  分组行背景色
	 */
	public static Color GROUP_ROW_BACKGROUND = Color.LIGHT_GRAY;
	/**
	 *  数据表格选择事件背景色
	 */
	public static Color CELL_SELECTED_BACKGROUND = Color.CYAN;
	/**
	 * 成员边框色
	 */
	public static Color LEFT_BORDER_BACKGROUND = Color.BLUE;
	
	/**
	 *  分组行头背景色
	 */
	public static Color GROUP_HEADER_BACKGROUND = Color.GREEN;
	/**
	 * 成员头背景色
	 */
	public static Color LEFT_HEADER_BACKGROUND = Color.LIGHT_GRAY;

	
	public XTreeTableManager(JXTreeTable treeTable) {
		this.treetable = treeTable;
		this.tree = (JTree) treeTable.getCellRenderer(0, 0);
		this.tree.putClientProperty("JTree.lineStyle","None");
		this.tree.setOpaque(true);

//		this.root = (DefaultMutableTreeTableNode) tree.getModel().getRoot();
		
		this.treetable.setRowHeight(50);
		
		tree.setShowsRootHandles(false);//隐藏折叠按钮
		tree.setRootVisible(false); //隐藏根节点
		tree.setBackground(new Color(128, 128, 128));
		//表格整体样式绘制
		this.treetable.setDefaultRenderer(Object.class, new XTreeTableCellRenderer());
		this.treetable.setSelectionBackground(CELL_SELECTED_BACKGROUND);
		//重绘树节点
		tree.setCellRenderer(new DefaultTreeRenderer(new SingleTreeCellProvider()));
		//增加树节点选择改变事件
//		this.treetable.addTreeSelectionListener(this);
		this.treetable.addMouseListener(this);
		this.treetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//禁止行选事件
//		this.treetable.setRowSelectionAllowed(false);
//		this.treetable.setColumnSelectionAllowed(false);
		this.treetable.setCellSelectionEnabled(true);
		tree.setDragEnabled(true);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		TreePath path = tree.getPathForLocation(e.getX(), e.getY());
		int col = treetable.columnAtPoint(e.getPoint());
		if(path==null) {
			return ;
		}
		// 获取当前选择的第一个结点中的最后一个路径组件
//        DefaultMutableTreeTableNode dmt = (DefaultMutableTreeTableNode) path.getLastPathComponent();
        if(path.getPathCount()==2 && col==0) {
        	if(tree.isExpanded(path)) {
        		tree.collapsePath(path);
        	}else {
        		tree.expandPath(path);
        	}
        }
		
	}
}
