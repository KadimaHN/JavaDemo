package com.kadima.demo.aitable.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import org.jdesktop.swingx.JXTreeTable;

import com.kadima.demo.aitable.ui.organ.JXColorSelectionButton;
import com.kadima.demo.aitable.ui.organ.XTreeTableManager;

public class MainWindow {

	private JFrame frame;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					MainWindow window = new MainWindow();
					window.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // 设置窗口最大化
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 546, 381);
		frame.setLocationRelativeTo(null);
//		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JXTreeTable treetable = new JXTreeTable(new XTreeTableModel()); // JXTreeTable构造方法
		new XTreeTableManager(treetable);
		treetable.getColumnModel().getColumn(0).setPreferredWidth(150); // 设置第一列宽度
		treetable.getTableHeader().setResizingAllowed(false);
		treetable.setShowGrid(true);
		treetable.getTableHeader().setReorderingAllowed(false);//设置表头不能拖动
		
		JScrollPane scrollpane = new JScrollPane(treetable);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollpane, BorderLayout.CENTER);

		JPanel navigationPanel = new JPanel();
		navigationPanel.setPreferredSize(new Dimension(0, 50));
		frame.getContentPane().add(navigationPanel, BorderLayout.NORTH);
		navigationPanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(200, 10));
		frame.getContentPane().add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(null);
		
		JXColorSelectionButton colorSelectionButton = new JXColorSelectionButton(XTreeTableManager.GROUP_ROW_BACKGROUND,"组行背景色");
		colorSelectionButton.addPropertyChangeListener("background",new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				XTreeTableManager.GROUP_ROW_BACKGROUND = ((JXColorSelectionButton)evt.getSource()).getBackground();
			}
		});
		colorSelectionButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		colorSelectionButton.setContentAreaFilled(false);
		colorSelectionButton.setBounds(10, 10, 168, 23);
		panel_1.add(colorSelectionButton);
		
		JXColorSelectionButton colorSelectionButton_2 = new JXColorSelectionButton(XTreeTableManager.GROUP_HEADER_BACKGROUND,"组头背景色");
		colorSelectionButton_2.addPropertyChangeListener("background",new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				XTreeTableManager.GROUP_HEADER_BACKGROUND = ((JXColorSelectionButton)evt.getSource()).getBackground();
			}
		});
		colorSelectionButton_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		colorSelectionButton_2.setContentAreaFilled(false);
		colorSelectionButton_2.setBounds(10, 39, 168, 23);
		panel_1.add(colorSelectionButton_2);
		
		JXColorSelectionButton colorSelectionButton_3 = new JXColorSelectionButton(XTreeTableManager.LEFT_BORDER_BACKGROUND,"成员区域边框背景色");
		colorSelectionButton_3.addPropertyChangeListener("background",new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				XTreeTableManager.LEFT_BORDER_BACKGROUND = ((JXColorSelectionButton)evt.getSource()).getBackground();
			}
		});
		colorSelectionButton_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		colorSelectionButton_3.setContentAreaFilled(false);
		colorSelectionButton_3.setBounds(10, 68, 168, 23);
		panel_1.add(colorSelectionButton_3);
		
		JXColorSelectionButton colorSelectionButton_4 = new JXColorSelectionButton(XTreeTableManager.LEFT_HEADER_BACKGROUND,"成员头背景色");
		colorSelectionButton_4.addPropertyChangeListener("background",new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				XTreeTableManager.LEFT_HEADER_BACKGROUND = ((JXColorSelectionButton)evt.getSource()).getBackground();

			}
		});
		colorSelectionButton_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		colorSelectionButton_4.setContentAreaFilled(false);
		colorSelectionButton_4.setBounds(10, 97, 168, 23);
		panel_1.add(colorSelectionButton_4);
		
		JXColorSelectionButton colorSelectionButton_5 = new JXColorSelectionButton(XTreeTableManager.CELL_SELECTED_BACKGROUND,"单元格选择背景色");
		colorSelectionButton_5.addPropertyChangeListener("background",new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				XTreeTableManager.CELL_SELECTED_BACKGROUND = ((JXColorSelectionButton)evt.getSource()).getBackground();
			}
		});
		colorSelectionButton_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		colorSelectionButton_5.setContentAreaFilled(false);
		colorSelectionButton_5.setBounds(10, 128, 168, 23);
		panel_1.add(colorSelectionButton_5);

	}
}
