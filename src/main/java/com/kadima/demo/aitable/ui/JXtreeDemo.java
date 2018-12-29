package com.kadima.demo.aitable.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.dnd.DragSource;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import org.jdesktop.swingx.JXTree;

public class JXtreeDemo {

	private JFrame frame;
	private DragSource ds;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JXtreeDemo window = new JXtreeDemo();
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
	public JXtreeDemo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JXTree tree = new JXTree();
		tree.setShowsRootHandles(true);
		tree.setRootVisible(false);
		tree.setDragEnabled(true);
		frame.getContentPane().add(tree, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBorder(new CompoundBorder(new EmptyBorder(10, 0, 0, 0), new MatteBorder(0, 1, 0, 1, (Color) new Color(128, 128, 128))));
		lblNewLabel.setBounds(158, 5, 58, 17);
		panel.add(lblNewLabel);
	}

}
