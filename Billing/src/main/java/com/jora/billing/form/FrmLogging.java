package com.jora.billing.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.springframework.stereotype.Component;

import com.jora.billing.BillingApplication;
import com.jora.billing.common.ApplicationCommon;
import com.jora.billing.common.ComboBox;
import com.jora.billing.common.PasswordField;
import com.jora.billing.common.TextFieldInputSpecs.TextInputType;
import com.jora.billing.logic.LoginLogic;

@Component
public class FrmLogging extends JFrame implements KeyListener, ActionListener, FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final LoginLogic loginLogic;
	private JPanel panelMain;
	private JButton btnLogin, btnExit;
	private PasswordField txtPassword;
	private ComboBox<String> cmbOperator;
	private transient ClassLoader classLoader = FrmLogging.class.getClassLoader();

	public FrmLogging(LoginLogic loginLogic) {
		this.loginLogic = loginLogic;
		try {
			getContentPane().setPreferredSize(new Dimension(1000, 700));
			setUndecorated(true);
			pack();
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setTitle("LOGIN");
			componentCreation();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage());
		}
	}

	private void componentCreation() throws Exception {

		JPanel panelSubMain, panelHead, panelBottom;
		JLabel lblLoginGif, lblUserIcon, lblHead, lblDevelopedBy, lblCompLogo, lblOperator, lblPassword;
		BufferedImage compImage;

		panelMain = new JPanel(null);
		panelMain.setBounds(0, 0, getContentPane().getWidth(), getContentPane().getHeight());
		panelMain.setBackground(Color.decode("#ffffff"));
		panelMain.setBorder(
				BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(ApplicationCommon.getBackGroundColour())));
		panelMain.setVisible(true);
		getContentPane().add(panelMain);

		panelHead = new JPanel(null);
		panelHead.setBounds(0, 0, getContentPane().getWidth(), 60);
		panelHead.setBackground(Color.decode(ApplicationCommon.getBackGroundColour()));
		panelHead.setVisible(true);
		panelMain.add(panelHead);

		lblHead = labelCreation("LOGIN", 0, 20, getContentPane().getWidth(), 20, new Font("Arial Black", Font.BOLD, 24),
				Color.white, true);
		lblHead.setHorizontalAlignment(SwingConstants.CENTER);
		panelHead.add(lblHead);

		panelBottom = new JPanel(null);
		panelBottom.setBounds(0, getContentPane().getHeight() - 60, getContentPane().getWidth(), 60);
		panelBottom.setBackground(Color.decode(ApplicationCommon.getBackGroundColour()));
		panelBottom.setVisible(true);
		panelMain.add(panelBottom);

		panelSubMain = new JPanel(null);
		panelSubMain.setBounds(50, 70, 900, 560);
		panelSubMain.setBackground(Color.decode("#ffffff"));
		panelSubMain.setBorder(
				BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode(ApplicationCommon.getBackGroundColour())));
		panelSubMain.setVisible(true);
		panelMain.add(panelSubMain);

		lblLoginGif = new JLabel("");
		lblLoginGif.setBounds(20, 10, 400, 400);
		lblLoginGif.setBackground(Color.decode("#ffffff"));
		ApplicationCommon.setIconGif("resources/loginGif1.gif", lblLoginGif);
		lblLoginGif.setVisible(true);
		panelSubMain.add(lblLoginGif);

		lblUserIcon = new JLabel("");
		lblUserIcon.setBounds(550, -30, 200, 200);
		lblUserIcon.setBackground(Color.decode("#ffffff"));
		ApplicationCommon.setIconGif("resources/ProfileIcon.png", lblUserIcon);
		lblUserIcon.setVisible(true);
		panelSubMain.add(lblUserIcon);

		lblOperator = labelCreation("OPERATOR", lblUserIcon.getX() - 60, lblUserIcon.getY() + 175, 150, 20,
				new Font("Calibric", Font.BOLD, 18), Color.decode(ApplicationCommon.getBackGroundColour()), true);
		panelSubMain.add(lblOperator);

		cmbOperator = comboBoxCreation(cmbOperator, lblOperator.getX(), lblOperator.getY() + 30, 280, 30, Color.WHITE,
				true, new Font("Ebrima", Font.BOLD, 16));
		cmbOperator.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtPassword.requestFocus();
				}

			}
		});
		panelSubMain.add(cmbOperator);

		lblPassword = labelCreation("PASSWORD", lblOperator.getX(), cmbOperator.getY() + 50, 150, 30,
				new Font("Calibric", Font.BOLD, 18), Color.decode(ApplicationCommon.getBackGroundColour()), true);
		panelSubMain.add(lblPassword);

		txtPassword = txtBoxCreation(txtPassword, "", lblOperator.getX(), lblPassword.getY() + 30, 280, 30, true,
				new Font("Ebrima", Font.PLAIN, 14), 10, true, TextInputType.ALPHANUMBER, true);

		panelSubMain.add(txtPassword);

		btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(lblOperator.getX(), txtPassword.getY() + 70, 121, 33);
//		btnLogin.setBackground(Color.decode(backGroundColour));
		btnLogin.setHorizontalAlignment(SwingConstants.CENTER);
		btnLogin.setVerticalAlignment(SwingConstants.CENTER);
		btnLogin.setBorder(BorderFactory.createEmptyBorder());
		btnLogin.setVisible(true);
		btnLogin.addKeyListener(this);
		btnLogin.addActionListener(this);
		btnLogin.addFocusListener(this);
		btnLogin.setOpaque(true);
		btnLogin.setMnemonic(KeyEvent.VK_L);
		panelSubMain.add(btnLogin);

		btnExit = new JButton("EXIT");
		btnExit.setBounds(btnLogin.getX() + 150, btnLogin.getY(), 121, 33);
//		btnExit.setBackground(Color.decode(backGroundColour));
		btnExit.setHorizontalAlignment(SwingConstants.CENTER);
		btnExit.setVerticalAlignment(SwingConstants.CENTER);
		btnExit.setBorder(BorderFactory.createEmptyBorder());
		btnExit.setVisible(true);
		btnExit.addKeyListener(this);
		btnExit.addActionListener(this);
		btnExit.addFocusListener(this);
		btnExit.setOpaque(true);
		btnExit.setMnemonic(KeyEvent.VK_X);
		panelSubMain.add(btnExit);

//		lblDevelopedBy = new JLabel();
//		lblDevelopedBy = labelCreation("Developed by", 600, 25, 100, 20, new Font("Calibri", Font.PLAIN, 13),
//				Color.BLACK, true);
//		panelBottom.add(lblDevelopedBy);
//
//		compImage = ImageIO.read(classLoader.getResource("resources/companylogo.png"));
//		lblCompLogo = new JLabel(new ImageIcon(compImage));
//		lblCompLogo.setBounds(lblDevelopedBy.getX() + 70, 10, 120, 40);
//		panelBottom.add(lblCompLogo);

	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getComponent() == cmbOperator) {
			cmbOperator.setPopupVisible(true);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			if (e.getSource() == btnExit) {
				if (JOptionPane.showConfirmDialog(panelMain, "Are You Sure to Exit?", "EXIT", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION) {
					return;
				}
				System.exit(0);
			} else if (e.getSource() == btnLogin) {

				if (cmbOperator.getSelectedItemValue() == null) {
					loadInitials();
					cmbOperator.requestFocus();
					throw new Exception("Invalid Operator...!");
				}

				boolean chkOperator = loginLogic.checkOperator(
						Integer.parseInt(String.valueOf(cmbOperator.getSelectedItemValue())), txtPassword.getText());

				if (chkOperator) {
					FrmMdi frmMdi = BillingApplication.applicationContext.getBean(FrmMdi.class);
					frmMdi.setVisible(true);
					this.dispose();
//					this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(panelMain, "Password Mismatched...!", "Login",
							JOptionPane.WARNING_MESSAGE);
					SwingUtilities.invokeLater(() -> txtPassword.requestFocus());
					return;
				}
			}

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(panelMain, e2.getMessage(), "Login", JOptionPane.WARNING_MESSAGE);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (e.getSource() == cmbOperator) {
				txtPassword.requestFocus();
			} else if (e.getSource() == txtPassword) {
				btnLogin.requestFocus();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (e.getSource() == btnLogin) {
				btnExit.requestFocus();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (e.getSource() == btnExit) {
				btnLogin.requestFocus();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	private JLabel labelCreation(String lblName, int x, int y, int widthPer, int heightPer, Font font, Color color,
			boolean visible) {
		JLabel label = new JLabel(lblName);
		label.setBounds(x, y, widthPer, heightPer);
		label.setFont(font);
		label.setForeground(color);
		label.setVisible(visible);

		return label;

	}

	private ComboBox<String> comboBoxCreation(ComboBox<String> cmbComboBox, int x, int y, int width, int height,
			Color color, boolean edit, Font font) {
		cmbComboBox = new ComboBox<String>();

		cmbComboBox.setBounds(x, y, width, height);
		cmbComboBox.setBackground(color);
		cmbComboBox.setEditable(edit);
		cmbComboBox.addKeyListener(this);
		cmbComboBox.setFont(font);

		return cmbComboBox;
	}

	private PasswordField txtBoxCreation(PasswordField txtBox, String text, int x, int y, int widthPer, int heightPer,
			boolean editable, Font font, int maxLength, boolean inputVerifier, TextInputType textInputType,
			boolean visible) {

		txtBox = new PasswordField();
		txtBox.setBounds(x, y, widthPer, heightPer);
		txtBox.setText(text);
		txtBox.setEditable(editable);
		txtBox.setFont(font);
		txtBox.setMaxLength(maxLength);
		txtBox.setVerifyInputWhenFocusTarget(inputVerifier);
		txtBox.addKeyListener(this);
		txtBox.setVisible(visible);

		return txtBox;
	}

	public void loadInitials() throws Exception {
		boolean loadOperator = loginLogic.loadOperator(cmbOperator);
		txtPassword.setText("");
		cmbOperator.requestFocus();
	}
}
