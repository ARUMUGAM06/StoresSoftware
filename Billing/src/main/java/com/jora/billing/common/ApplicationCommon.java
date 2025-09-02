package com.jora.billing.common;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ApplicationCommon {

	public static int frameWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static int frameHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static FormAction currentForm;

	private static int internalFrameHeight, internalFrameWidth;

	private static DecimalFormat atf = new DecimalFormat("#0.00");
	private static DecimalFormat wtf = new DecimalFormat("#0.000");
	private static ClassLoader classLoader = ApplicationCommon.class.getClassLoader();

	private static String backGroundColour = "#4495e2";

	private static Map<String, Object> mapOperDetails = new HashMap<>();

	public static int getInternalFrameHeight() {
		return internalFrameHeight;
	}

	public static void setInternalFrameHeight(int internalFrameHeight) {
		ApplicationCommon.internalFrameHeight = internalFrameHeight;
	}

	public static int getInternalFrameWidth() {
		return internalFrameWidth;
	}

	public static void setInternalFrameWidth(int internalFrameWidth) {
		ApplicationCommon.internalFrameWidth = internalFrameWidth;
	}

	public static FormAction getCurrentForm() {
		return currentForm;
	}

	public static void setCurrentForm(FormAction currentForm) {
		ApplicationCommon.currentForm = currentForm;
	}

	public static String getWeightFormat(Object obj) {
		if (obj instanceof String) {
			return wtf.format(Double.parseDouble(String.valueOf(obj)));
		} else if (obj instanceof Integer) {
			return wtf.format(Integer.parseInt(String.valueOf(obj)));
		} else {
			return wtf.format(obj);
		}
	}

	public static String getAmountFormat(Object obj) {
		if (obj instanceof String) {
			return atf.format(Double.parseDouble(String.valueOf(obj)));
		} else if (obj instanceof Integer) {
			return atf.format(Integer.parseInt(String.valueOf(obj)));
		} else {
			return atf.format(obj);
		}
	}

	public static void setIconGif(String imgResName, JComponent jComponent) {
		try {
			/*** Make sure the component is not null ***/
			if (jComponent == null) {
				return;
			}

			/*** Load the GIF image using ImageIcon ***/
			ImageIcon icon = new ImageIcon(classLoader.getResource(imgResName));

			/*** If the GIF couldn't be loaded, return ***/
			if (icon == null) {
				return;
			}

			/*** Set the icon based on the type of JComponent passed ***/
			if (jComponent instanceof JLabel) {
				((JLabel) jComponent).setIcon(icon);
				/*** Make sure the label is visible and updates properly ***/
				((JLabel) jComponent).setText(""); // Clear text if needed (for better GIF visibility)
			} else if (jComponent instanceof JButton) {
				((JButton) jComponent).setIcon(icon);
			}

			/***
			 * Force a revalidation and repaint of the component to ensure the GIF animates
			 ***/
			jComponent.revalidate();
			jComponent.repaint();

		} catch (Exception e) {
			throw e;
		}
	}

	public static void componentEnableDisable(Component[] components, boolean enable) {
		try {
			for (Component component : components) {
				/*
				 * if (component instanceof JLabel) continue;
				 */
				if (component instanceof JPanel) {
					componentEnableDisable(((JPanel) component).getComponents(), enable);
					continue;
				} else if (component instanceof JTabbedPane) {
					componentEnableDisable(((JTabbedPane) component).getComponents(), enable);
					continue;
//				} else if (component instanceof ComboBox<E>) {
//					component.setEnabled(enable);
//					continue;
				}
				for (Component component2 : ((Container) component).getComponents()) {
					for (Component component3 : ((Container) component2).getComponents())
						component3.setEnabled(enable);
				}
				component.setEnabled(enable);
			}
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static Map<String, Object> getMapOperDetails() {
		return mapOperDetails;
	}

	public static void setMapOperDetails(Map<String, Object> mapOperDetails) {
		ApplicationCommon.mapOperDetails = mapOperDetails;
	}

	public static String getBackGroundColour() {
		return backGroundColour;
	}

}
