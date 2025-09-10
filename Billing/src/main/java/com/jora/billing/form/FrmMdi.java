package com.jora.billing.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jora.billing.BillingApplication;
import com.jora.billing.common.ApplicationCommon;
import com.jora.billing.common.FormAction;

@Component
public class FrmMdi extends JFrame implements KeyListener, ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnAdd, btnSave, btnView, btnClear, btnClose, btnPosting;
	private JMenuItem mnuItemOperator, mnuItemProduct, mnuItemHsnBasics, mnuItemCategory, mnuItemTagedView,
			mnuItemSalesEntry, mnuItemSalesView;
	private JPanel panelForm, panelMain, panelMenu, panelMenuDetails, panelButton, panelSubButton;
	private JMenu mnuLogout, mnuTaged, mnuSales;
	private JMenuBar menuBar;

//	@Autowired
	private FrmOperator frmOperator;

	private FrmCategory frmCategory;

	private FrmHsnBasics frmHsnBasics;

//	private FrmProduct frmProduct;

	public FrmMdi() {
		getContentPane().setPreferredSize(new Dimension(ApplicationCommon.frameWidth, ApplicationCommon.frameHeight));
		setUndecorated(true);
		pack();
		setTitle("MDI");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		componentCreation();
	}

	private void componentCreation() {

		panelMain = new JPanel(null);
		panelMain.setBounds(0, 0, ApplicationCommon.frameWidth, ApplicationCommon.frameHeight);
		panelMain.setVisible(true);
		getContentPane().add(panelMain);

		panelMenuCreation();
		panelButtonCreation();
		panelFormCreation();

	}

	private void panelFormCreation() {
		panelForm = new JPanel(null);
		panelForm.setBounds(0, panelMenu.getY() + panelMenu.getHeight(), ApplicationCommon.frameWidth,
				panelButton.getY() - (panelMenu.getY() + panelMenu.getHeight()));
		panelForm.setBackground(Color.decode("#FFFFFF"));
		panelForm.setVisible(true);

		ApplicationCommon.setInternalFrameHeight(panelForm.getHeight());
		ApplicationCommon.setInternalFrameWidth(panelForm.getWidth());

		panelMain.add(panelMenu);
		panelMain.add(panelButton);
		panelMain.add(panelForm);
		panelMain.setVisible(true);

	}

	private void panelButtonCreation() {
		panelButton = new JPanel(null);
		panelButton.setBounds(0, (int) (ApplicationCommon.frameHeight / 1.07), ApplicationCommon.frameWidth,
				(int) ((ApplicationCommon.frameWidth * 3.67) / 100));
		panelButton.setBackground(Color.decode("#AAB99A"));
		panelButton.setVisible(true);

		panelSubButton = new JPanel(null);
		panelSubButton.setBounds(0, 0, panelButton.getWidth(), panelButton.getHeight());
		panelSubButton.setBackground(Color.decode("#AAB99A"));
		panelSubButton.setVisible(false);

		btnAdd = buttonCreation(btnAdd, "ADD", (int) ((ApplicationCommon.frameWidth * 3.13) / 100), 10,
				(int) (ApplicationCommon.frameWidth * 7.6 / 100), (int) (ApplicationCommon.frameHeight * 4 / 100),
				KeyEvent.VK_A, true, true);
		panelSubButton.add(btnAdd);

		btnSave = buttonCreation(btnSave, "SAVE", btnAdd.getX() + (int) ((ApplicationCommon.frameWidth * 15.625) / 100),
				btnAdd.getY(), btnAdd.getWidth(), btnAdd.getHeight(), KeyEvent.VK_S, true, true);
		panelSubButton.add(btnSave);

		btnView = buttonCreation(btnView, "VIEW",
				btnSave.getX() + (int) ((ApplicationCommon.frameWidth * 15.625) / 100), btnSave.getY(),
				btnAdd.getWidth(), btnAdd.getHeight(), KeyEvent.VK_V, false, true);
		panelSubButton.add(btnView);

		btnPosting = buttonCreation(btnPosting, "Posting",
				btnView.getX() + (int) ((ApplicationCommon.frameWidth * 15.625) / 100), btnSave.getY(),
				btnAdd.getWidth(), btnAdd.getHeight(), KeyEvent.VK_P, false, true);
		panelSubButton.add(btnPosting);

		btnClear = buttonCreation(btnClear, "CLEAR",
				btnPosting.getX() + (int) ((ApplicationCommon.frameWidth * 15.625) / 100), btnView.getY(),
				btnAdd.getWidth(), btnAdd.getHeight(), KeyEvent.VK_L, false, true);
		panelSubButton.add(btnClear);

		btnClose = buttonCreation(btnClose, "CLOSE",
				btnClear.getX() + (int) ((ApplicationCommon.frameWidth * 15.625) / 100), btnClear.getY(),
				btnAdd.getWidth(), btnAdd.getHeight(), KeyEvent.VK_C, false, true);
		panelSubButton.add(btnClose);

		panelButton.add(panelSubButton);

	}

	private void panelMenuCreation() {

		JMenu mnuDirectory, mnuTransaction;

		panelMenu = new JPanel(null);
		panelMenu.setBounds(0, 0, ApplicationCommon.frameWidth, (int) ((ApplicationCommon.frameWidth * 4.2) / 100));
		panelMenu.setBackground(Color.decode("#AAB99A"));
		panelMenu.setVisible(true);
		getContentPane().add(panelMain);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, (int) ((ApplicationCommon.frameWidth * 1.6) / 100), panelMenu.getWidth(),
				panelMenu.getHeight());
		menuBar.setBackground(Color.decode("#AAB99A"));

		mnuDirectory = new JMenu("Directory");
		mnuDirectory.setMnemonic(KeyEvent.VK_D);
		mnuDirectory.setFont(new Font("", Font.PLAIN, 20));
		mnuDirectory.addMouseListener(this);
		mnuDirectory.addActionListener(this);

		mnuItemHsnBasics = new JMenuItem("HSN Basics", KeyEvent.VK_H);
		mnuItemHsnBasics.setFont(new Font("", Font.PLAIN, 18));
		mnuItemHsnBasics.addMouseListener(this);
		mnuItemHsnBasics.addActionListener(this);
		mnuDirectory.add(mnuItemHsnBasics);
		menuBar.add(mnuDirectory);

		mnuItemCategory = new JMenuItem("Category", KeyEvent.VK_C);
		mnuItemCategory.setFont(new Font("", Font.PLAIN, 18));
		mnuItemCategory.addMouseListener(this);
		mnuItemCategory.addActionListener(this);
		mnuDirectory.add(mnuItemCategory);

		mnuItemProduct = new JMenuItem("Product", KeyEvent.VK_P);
		mnuItemProduct.setFont(new Font("", Font.PLAIN, 18));
		mnuItemProduct.addMouseListener(this);
		mnuItemProduct.addActionListener(this);
		mnuDirectory.add(mnuItemProduct);

		mnuItemOperator = new JMenuItem("Operator", KeyEvent.VK_O);
		mnuItemOperator.setFont(new Font("", Font.PLAIN, 18));
		mnuItemOperator.addMouseListener(this);
		mnuItemOperator.addActionListener(this);
//		mnuDirectory.add(mnuItemOperator);
		menuBar.add(mnuDirectory);

		mnuTransaction = new JMenu("Transaction");
		mnuTransaction.setMnemonic(KeyEvent.VK_T);
		mnuTransaction.setFont(new Font("", Font.PLAIN, 20));
		mnuTransaction.addMouseListener(this);
		mnuTransaction.addActionListener(this);
		menuBar.add(mnuTransaction);

		mnuTaged = new JMenu("Taged");
		mnuTaged.setFont(new Font("", Font.PLAIN, 18));
		mnuTaged.addMouseListener(this);
		mnuTaged.addActionListener(this);
//		mnuTransaction.add(mnuTaged);

		mnuItemTagedView = new JMenuItem("View");
		mnuItemTagedView.setMnemonic(KeyEvent.VK_A);
		mnuItemTagedView.setFont(new Font("", Font.PLAIN, 18));
		mnuItemTagedView.addMouseListener(this);
		mnuItemTagedView.addActionListener(this);
//		mnuTaged.add(mnuItemTagedView);

		mnuSales = new JMenu("Sales");
		mnuSales.addMouseListener(this);
		mnuSales.addActionListener(this);
//		mnuTransaction.add(mnuSales);

		mnuItemSalesEntry = new JMenuItem("Entry");
		mnuItemSalesEntry.addMouseListener(this);
		mnuItemSalesEntry.addActionListener(this);
//		mnuSales.add(mnuItemSalesEntry);

		mnuItemSalesView = new JMenuItem("View");
		mnuItemSalesView.setMnemonic(KeyEvent.VK_A);
		mnuItemSalesView.addMouseListener(this);
		mnuItemSalesView.addActionListener(this);
//		mnuSales.add(mnuItemSalesView);

		mnuLogout = new JMenu("Logout");
		mnuLogout.setMnemonic(KeyEvent.VK_L);
		mnuLogout.setFont(new Font("", Font.PLAIN, 20));
		mnuLogout.addMouseListener(this);
		mnuLogout.addActionListener(this);
		mnuLogout.setVerifyInputWhenFocusTarget(false);
		menuBar.add(mnuLogout);

		panelMenu.add(menuBar);
		menuBar.setVisible(true);
		panelMenu.setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			if (e.getSource() == mnuLogout) {
				if (JOptionPane.showConfirmDialog(panelMain, "Are You Sure to Logout?", "LOGOUT",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION) {
					return;
				}

				this.dispose();
				FrmLogging frmLogging = BillingApplication.applicationContext.getBean(FrmLogging.class);
				frmLogging.loadInitials();
				frmLogging.setVisible(true);
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(panelMain, e2.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			if (e.getSource() == mnuLogout) {
				if (JOptionPane.showConfirmDialog(panelMain, "Are You Sure to Logout?", "LOGOUT",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION) {
					return;
				}

				this.dispose();
				FrmLogging frmLogging = BillingApplication.applicationContext.getBean(FrmLogging.class);
				frmLogging.loadInitials();
				frmLogging.setVisible(true);
			} else if (e.getSource() == mnuItemOperator) {

				panelForm.removeAll();
				frmOperator = BillingApplication.applicationContext.getBean(FrmOperator.class);
				((javax.swing.plaf.basic.BasicInternalFrameUI) frmOperator.getUI()).setNorthPane(null);
				frmOperator.setVisible(true);
//				frmOperator.formDisable();
//				frmOperator.getPanelMain().setEnabled(false);
				panelForm.add(frmOperator);
				panelButton.setVisible(true);
				btnAdd.requestFocus();
				ApplicationCommon.setCurrentForm((FormAction) frmOperator);

			} else if (e.getSource() == mnuItemCategory) {
				panelForm.removeAll();
				frmCategory = BillingApplication.applicationContext.getBean(FrmCategory.class);
				((javax.swing.plaf.basic.BasicInternalFrameUI) frmCategory.getUI()).setNorthPane(null);
				frmCategory.setVisible(true);
				menuBar.setVisible(false);
				panelSubButton.setVisible(true);
				panelForm.add(frmCategory);
				btnAdd.requestFocus();
				ApplicationCommon.setCurrentForm((FormAction) frmCategory);
			} else if (e.getSource() == mnuItemHsnBasics) {
				panelForm.removeAll();
				frmHsnBasics = BillingApplication.applicationContext.getBean(FrmHsnBasics.class);
				((javax.swing.plaf.basic.BasicInternalFrameUI) frmHsnBasics.getUI()).setNorthPane(null);

//				frmHsnBasics.setBounds(panelForm.getBounds());
				panelForm.add(frmHsnBasics);
				frmHsnBasics.setVisible(true);
				menuBar.setVisible(false);
				panelButton.setVisible(true);
				panelSubButton.setVisible(true);
				btnAdd.requestFocus();
				ApplicationCommon.setCurrentForm((FormAction) frmHsnBasics);
			} else if (e.getSource() == mnuItemProduct) {

//				panelForm.removeAll();
//				FrmProduct frmProduct = BillingApplication.applicationContext.getBean(FrmProduct.class);
////				((javax.swing.plaf.basic.BasicInternalFrameUI) frmProduct.getUI()).setNorthPane(null);
//				panelForm.add(frmProduct);
//				frmProduct.setVisible(true);
//				panelButton.setVisible(true);
//				btnAdd.requestFocus();
//				ApplicationCommon.setCurrentForm((FormAction) frmProduct);

				panelForm.removeAll();
				FrmProduct frmProduct = BillingApplication.applicationContext.getBean(FrmProduct.class);
				((javax.swing.plaf.basic.BasicInternalFrameUI) frmProduct.getUI()).setNorthPane(null);
				panelForm.add(frmProduct);
				frmProduct.setVisible(true);
				ApplicationCommon.setCurrentForm((FormAction) frmProduct);
				menuBar.setVisible(false);
				panelButton.setVisible(true);
				panelSubButton.setVisible(true);
				btnAdd.requestFocus();
			} else if (e.getSource() == mnuLogout) {
				System.exit(0);
			} else if (e.getSource() == btnAdd) {
				ApplicationCommon.getCurrentForm().add();
			} else if (e.getSource() == btnSave) {
				ApplicationCommon.getCurrentForm().save();
			} else if (e.getSource() == btnView) {
				ApplicationCommon.getCurrentForm().view();
			} else if (e.getSource() == btnClear) {
				ApplicationCommon.getCurrentForm().clear();
			} else if (e.getSource() == btnClose) {
				ApplicationCommon.getCurrentForm().close();
			}

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(panelMain, e2.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (e.getSource() == btnAdd) {
				btnSave.requestFocus();
			} else if (e.getSource() == btnSave) {
				btnView.requestFocus();
			} else if (e.getSource() == btnView) {
				btnPosting.requestFocus();
			} else if (e.getSource() == btnPosting) {
				btnClear.requestFocus();
			} else if (e.getSource() == btnClear) {
				btnClose.requestFocus();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (e.getSource() == btnClose) {
				btnClear.requestFocus();
			} else if (e.getSource() == btnClear) {
				btnPosting.requestFocus();
			} else if (e.getSource() == btnPosting) {
				btnView.requestFocus();
			} else if (e.getSource() == btnView) {
				btnSave.requestFocus();
			} else if (e.getSource() == btnSave) {
				btnAdd.requestFocus();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JButton getBtnClear() {
		return btnClear;
	}

	public JButton getBtnClose() {
		return btnClose;
	}

	public JPanel getPanelForm() {
		return panelForm;
	}

	public JPanel getPanelMain() {
		return panelMain;
	}

	public JPanel getPanelMenu() {
		return panelMenu;
	}

	public JPanel getPanelButton() {
		return panelButton;
	}

	public JMenu getMnuExit() {
		return mnuLogout;
	}

	private JButton buttonCreation(JButton button, String btnName, int x, int y, int btnWidth, int btnHeight,
			int keycode, boolean inputVerifier, boolean visible) {
		button = new JButton();
		button.setBounds(x, y, btnWidth, btnHeight);
		button.setText(btnName);
		button.setMnemonic(keycode);
		button.addKeyListener(this);
		button.addActionListener(this);
		button.setVerifyInputWhenFocusTarget(inputVerifier);
		button.setVisible(visible);

		return button;
	}

	public JMenuItem getMnuItemStnProduct() {
		return mnuItemOperator;
	}

	public void setMnuItemStnProduct(JMenuItem mnuItemStnProduct) {
		this.mnuItemOperator = mnuItemStnProduct;
	}

	public JMenuItem getMnuItemProduct() {
		return mnuItemProduct;
	}

	public void setMnuItemProduct(JMenuItem mnuItemProduct) {
		this.mnuItemProduct = mnuItemProduct;
	}

	public void CloseMethod() {
		panelButton.setVisible(true);
		panelSubButton.setVisible(false);
		menuBar.setVisible(true);
		this.panelForm.removeAll();
	}

	public JButton getBtnView() {
		return btnView;
	}

}
