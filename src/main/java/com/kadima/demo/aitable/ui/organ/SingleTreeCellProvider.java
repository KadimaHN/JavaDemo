package com.kadima.demo.aitable.ui.organ;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JXTree;
import org.jdesktop.swingx.renderer.CellContext;
import org.jdesktop.swingx.renderer.ComponentProvider;
import org.jdesktop.swingx.renderer.TreeCellContext;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

/**
 * 树节点显示类型
 * @author Kadima
 *
 */
@SuppressWarnings("serial")
public class SingleTreeCellProvider extends ComponentProvider<JPanel>{

	
	private JLabel _label;

	public SingleTreeCellProvider() {
		this._label = new JLabel();
	}
	
	@Override
	protected void format(CellContext arg0) {
		DefaultMutableTreeTableNode node = (DefaultMutableTreeTableNode) arg0.getValue();
		Object[] obj = (Object[]) node.getUserObject();
		_label.setText(obj[0]==null ? "":obj[0].toString());
//		_label.setIcon(arg0.getIcon());
		if(arg0 instanceof TreeCellContext) {
			TreePath path = ((TreeCellContext) arg0).getTreePath();
			if(path!=null && path.getPathCount()==2) {
				_label.setBackground(XTreeTableManager.GROUP_HEADER_BACKGROUND);
				_label.setOpaque(true);
			}else {
				_label.setBackground(XTreeTableManager.LEFT_HEADER_BACKGROUND);
				_label.setOpaque(true);
			}
		}
		// 使用BorderLayout布局，依次放置TristateCheckBox和JLabel
		rendererComponent.setLayout(new BorderLayout());
		rendererComponent.add(_label, BorderLayout.CENTER);
		rendererComponent.setToolTipText(obj[0]==null ? "":obj[0].toString());

	}

	/**
	 * 总在format前执行，因此可以提前做一些事情，比如单元格样式等
	 */
	@Override
	protected void configureState(CellContext arg0) {
		if(arg0==null) {
			return ;
		}
		arg0.getComponent();
		if(arg0 instanceof TreeCellContext) { //在这里对控件进行定制
			TreeCellContext context = (TreeCellContext) arg0;
			TreePath path = context.getTreePath();
			JXTree tree = (JXTree) context.getComponent();
			rendererComponent.setPreferredSize(new Dimension(155, tree.getRowHeight()));
			rendererComponent.setFont(arg0.getComponent().getFont());
			if(path!=null && path.getPathCount()==2) {
				//在这里可以添加tree的底板颜色
				rendererComponent.setOpaque(true);
				rendererComponent.setBackground(XTreeTableManager.GROUP_ROW_BACKGROUND);
				rendererComponent.setBorder(new CompoundBorder(new EmptyBorder(20, 0, 0, 0), 
						new MatteBorder(0, 0, 1, 1, new Color(128, 128, 128))));
			}
		}
		rendererComponent.removeAll();
	}

	@Override
	protected JPanel createRendererComponent() {
		JPanel panel = new JPanel();
		return panel;
	}

}
