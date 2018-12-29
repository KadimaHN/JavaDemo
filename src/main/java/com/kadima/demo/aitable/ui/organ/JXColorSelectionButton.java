package com.kadima.demo.aitable.ui.organ;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdesktop.swingx.color.ColorUtil;
import org.jdesktop.swingx.color.EyeDropperColorChooserPanel;
import org.jdesktop.swingx.plaf.UIManagerExt;
import org.jdesktop.swingx.util.OS;

@SuppressWarnings("serial")
public class JXColorSelectionButton extends JButton {
    private BufferedImage colorwell;
    private JDialog dialog = null;
    private JColorChooser chooser = null;
//    private Color bindingcolor = null;
    
    /**
     * Creates a new instance of JXColorSelectionButton
     */
    public JXColorSelectionButton() {
        this(Color.red,null);
    }
    public JXColorSelectionButton(String text) {
        this(Color.red,text);
    }
    /**
     * Creates a new instance of JXColorSelectionButton set to the specified color.
     * @param col The default color
     */
    public JXColorSelectionButton(Color col,String text) {
        setBackground(col);
        this.addActionListener(new ActionHandler());
        this.setContentAreaFilled(true);
        this.setOpaque(true);
        this.setText(text);
        
        try {
            colorwell = ImageIO.read(this.getClass().getResourceAsStream("color/colorwell.png"));
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
        
        this.addPropertyChangeListener("background",new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                getChooser().setColor(getBackground());
            }
        });
    }
    @Override
    public void setBackground(Color bg) {
//    	this.bindingcolor = bg;
    	super.setBackground(bg);
    }
    @Override
    public Color getBackground() {
    	// TODO Auto-generated method stub
    	return super.getBackground();
    }
    
    /**
     * A listener class to update the button's background when the selected
     * color changes.
     */
    private class ColorChangeListener implements ChangeListener {
        public JXColorSelectionButton button;
        public ColorChangeListener(JXColorSelectionButton button) {
            this.button = button;
        }
        public void stateChanged(ChangeEvent changeEvent) {
            button.setBackground(button.getChooser().getColor());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(Graphics g) {
        // want disabledForeground when disabled, current colour otherwise
        final Color FILL_COLOR = isEnabled() ? ColorUtil.removeAlpha(getBackground())
                : UIManagerExt.getSafeColor("Button.disabledForeground", Color.LIGHT_GRAY);
        // draw the colorwell image (should only be on OSX)
        if(OS.isMacOSX() && colorwell != null) {
            Insets ins = new Insets(5,5,5,5);
            ColorUtil.tileStretchPaint(g, this, colorwell, ins);
            
            // fill in the color area
            g.setColor(FILL_COLOR);
            g.fillRect(ins.left, ins.top,
                    getWidth()  - ins.left - ins.right,
                    getHeight() - ins.top - ins.bottom);
            // draw the borders
            g.setColor(ColorUtil.setBrightness(FILL_COLOR,0.85f));
            g.drawRect(ins.left, ins.top,
                    getWidth() - ins.left - ins.right - 1,
                    getHeight() - ins.top - ins.bottom - 1);
            g.drawRect(ins.left + 1, ins.top + 1,
                    getWidth() - ins.left - ins.right - 3,
                    getHeight() - ins.top - ins.bottom - 3);
        }else{
            Graphics2D g2 = (Graphics2D) g.create();
            
            try {
                g2.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
                g2.setColor(Color.LIGHT_GRAY);
                final int DIAM = Math.min(getWidth(), getHeight());
                final int inset = 3;
                g2.fill(new Ellipse2D.Float(inset, inset, DIAM-2*inset, DIAM-2*inset));
                g2.setColor(FILL_COLOR);
                final int border = 1;
                g2.fill(new Ellipse2D.Float(inset+border, inset+border, DIAM-2*inset-2*border, DIAM-2*inset-2*border));
                g2.drawString(getText()+FILL_COLOR.toString(), DIAM,  getHeight()/2+inset);
            } finally {
                g2.dispose();
            }
            
        }
        
    }

//    /**
//     * Sample usage of JXColorSelectionButton
//     * @param args not used
//     */
//    public static void main(String[] args) {
//        javax.swing.JFrame frame = new javax.swing.JFrame("Color Button Test");
//        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
//        javax.swing.JPanel panel = new javax.swing.JPanel();
//        javax.swing.JComponent btn = new JXColorSelectionButton("测试");
//        btn.setEnabled(true);
//        panel.add(btn);
//        panel.add(new javax.swing.JLabel("ColorSelectionButton test"));
//        
//        frame.add(panel);
//        frame.pack();
//        frame.setVisible(true);
//    }

    /**
     * Conditionally create and show the color chooser dialog.
     */
    private void showDialog() {
        final Color oldColor = getBackground();
        
        if (dialog == null) {
            dialog = JColorChooser.createDialog(JXColorSelectionButton.this,
                    "Choose a color", true, getChooser(),
                    new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    Color color = getChooser().getColor();
                    if (color != null) {
                        setBackground(color);
                    }
                }
            },
            new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    setBackground(oldColor);
                }
            });
            dialog.getContentPane().add(getChooser());
            getChooser().getSelectionModel().addChangeListener(
                    new ColorChangeListener(JXColorSelectionButton.this));
        }
        
        dialog.setVisible(true);

    }
    
    /**
     * Get the JColorChooser that is used by this JXColorSelectionButton. This
     * chooser instance is shared between all invocations of the chooser, but is unique to
     * this instance of JXColorSelectionButton.
     * @return the JColorChooser used by this JXColorSelectionButton
     */
    public JColorChooser getChooser() {
        if(chooser == null) {
            chooser = new JColorChooser();
            // add the eyedropper color chooser panel
            chooser.addChooserPanel(new EyeDropperColorChooserPanel());
        }
        return chooser;
    }
    
    /**
     * Set the JColorChooser that is used by this JXColorSelectionButton.
     * chooser instance is shared between all invocations of the chooser,
     * but is unique to
     * this instance of JXColorSelectionButton.
     * @param chooser The new JColorChooser to use.
     */
    public void setChooser(JColorChooser chooser) {
        JColorChooser oldChooser = getChooser();
        this.chooser = chooser;
        firePropertyChange("chooser",oldChooser,chooser);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getPreferredSize() {
        if (isPreferredSizeSet() || colorwell == null) {
            return super.getPreferredSize();
        }
        
        return new Dimension(colorwell.getWidth(), colorwell.getHeight());
    }

    /**
     * A private class to conditionally create and show the color chooser
     * dialog.
     */
    private class ActionHandler implements ActionListener {
        
        public void actionPerformed(ActionEvent actionEvent) {
            showDialog();
        }
    }
}
