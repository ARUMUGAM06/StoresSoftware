package com.jora.billing.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import org.springframework.stereotype.Component;

import com.jora.billing.common.ApplicationCommon;
import com.jora.billing.common.FormAction;
import com.jora.billing.common.JTextFieldEnum.TextInputType;
import com.jora.billing.common.JTextFieldEnum.TextSpaceReq;
import com.jora.billing.common.TextField;
import com.jora.billing.connection.ApplicationConfig;
import com.jora.billing.logic.LogicHsnBasic;

@Component
public class FrmHsnBasics extends JInternalFrame
		implements KeyListener, ActionListener, FormAction, InternalFrameListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelMain, panelEntry, panelView;
	private TextField txtHsnCode, txtHsnName, txtSgst, txtCgst, txtIgst;
	private JCheckBox chkActive;
	private JLabel lblInfo;
	private final LogicHsnBasic logicHsnBasic;
	private final FrmMdi frmMdi;

	public FrmHsnBasics(LogicHsnBasic logicHsnBasic, FrmMdi frmMdi) throws Exception {
		getContentPane().setPreferredSize(
				new Dimension(ApplicationCommon.getInternalFrameWidth(), ApplicationCommon.getInternalFrameHeight()));
		getContentPane().setSize(
				new Dimension(ApplicationCommon.getInternalFrameWidth(), ApplicationCommon.getInternalFrameHeight()));
		pack();
		setTitle("HSN BASICS");
		setMaximum(true);
		setResizable(false);
		addInternalFrameListener(this);
		this.logicHsnBasic = logicHsnBasic;
		this.frmMdi = frmMdi;
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		componentCreation();
		loadInitials();
	}

	public void loadInitials() {
		txtHsnCode.setText("");
		txtHsnName.setText("");
		txtSgst.setText("");
		txtCgst.setText("");
		txtIgst.setText("");
		chkActive.setSelected(false);

	}

	private void componentCreation() {

		panelMain = new JPanel(null);
		panelMain.setBounds(0, 0, ApplicationCommon.getInternalFrameWidth(),
				ApplicationCommon.getInternalFrameHeight());
		panelMain.setBackground(Color.decode("#FFFFFF"));
		panelMain.setVisible(true);
		getContentPane().add(panelMain);

		panelEntryCreation();
		panelViewCreation();

	}

	private void panelViewCreation() {
		panelView = new JPanel(null);
		panelView.setBounds(0, 0, ApplicationCommon.getInternalFrameWidth(),
				ApplicationCommon.getInternalFrameHeight());
		panelView.setBackground(Color.decode("#FFFFFF"));
		panelView.setVisible(false);
		panelMain.add(panelView);

	}

	private void panelEntryCreation() {
		JLabel lblHsnCode, lblHsnName, lblSgst, lblCgst, lblIgst, lblActive;
		JPanel panelSubEntry;

		panelEntry = new JPanel(null);
		panelEntry.setBounds(0, 0, ApplicationCommon.getInternalFrameWidth(),
				ApplicationCommon.getInternalFrameHeight());
		panelEntry.setBackground(Color.decode("#FFFFFF"));
		panelEntry.setVisible(true);
		panelMain.add(panelEntry);

		panelSubEntry = new JPanel(null);
		panelSubEntry.setBounds(500, 100, 900, 550);
		panelSubEntry.setBackground(Color.decode("#FFFFFF"));
		panelSubEntry.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
		panelSubEntry.setVisible(true);
		panelEntry.add(panelSubEntry);

		lblHsnCode = new JLabel();
		lblHsnCode = labelCreation("HSNCode ", 40, 60, 100, 20, new Font("Calibri", Font.PLAIN, 20), Color.black, true);
		panelSubEntry.add(lblHsnCode);

		txtHsnCode = textFieldCreation(txtHsnCode, lblHsnCode.getX() + 100, lblHsnCode.getY() - 10, 300,
				ApplicationCommon.frameHeight * 3 / 100, TextInputType.ALPHANUMBER, 20, TextSpaceReq.NOTREQUIRED, true,
				new Font("Calibri", Font.PLAIN, 20));
		panelSubEntry.add(txtHsnCode);

		lblHsnName = new JLabel();
		lblHsnName = labelCreation("HSN Name ", lblHsnCode.getX(), lblHsnCode.getY() + 100, 100, 20,
				new Font("Calibri", Font.PLAIN, 20), Color.black, true);
		panelSubEntry.add(lblHsnName);

		txtHsnName = textFieldCreation(txtHsnName, txtHsnCode.getX(), lblHsnName.getY() - 10, 500,
				ApplicationCommon.frameHeight * 3 / 100, TextInputType.ALPHANUMBER, 20, TextSpaceReq.REQUIRED, true,
				new Font("Calibri", Font.PLAIN, 20));
		panelSubEntry.add(txtHsnName);

		lblSgst = new JLabel();
		lblSgst = labelCreation("SGST ", lblHsnCode.getX(), lblHsnName.getY() + 100, 100, 20,
				new Font("Calibri", Font.PLAIN, 20), Color.black, true);
		panelSubEntry.add(lblSgst);

		txtSgst = textFieldCreation(txtSgst, txtHsnCode.getX(), lblSgst.getY() - 10, 150,
				ApplicationCommon.frameHeight * 3 / 100, TextInputType.NUMERIC, 6, TextSpaceReq.NOTREQUIRED, true,
				new Font("Calibri", Font.PLAIN, 20));
		panelSubEntry.add(txtSgst);

		lblCgst = new JLabel();
		lblCgst = labelCreation("CGST ", txtSgst.getX() + 200, lblSgst.getY(), 100, 20,
				new Font("Calibri", Font.PLAIN, 20), Color.black, true);
		panelSubEntry.add(lblCgst);

		txtCgst = textFieldCreation(txtCgst, lblCgst.getX() + 100, lblCgst.getY() - 10, 150,
				ApplicationCommon.frameHeight * 3 / 100, TextInputType.NUMERIC, 6, TextSpaceReq.NOTREQUIRED, true,
				new Font("Calibri", Font.PLAIN, 20));
		panelSubEntry.add(txtCgst);

		lblIgst = new JLabel();
		lblIgst = labelCreation("IGST ", lblHsnCode.getX(), lblSgst.getY() + 100, 100, 20,
				new Font("Calibri", Font.PLAIN, 20), Color.black, true);
		panelSubEntry.add(lblIgst);

		txtIgst = textFieldCreation(txtIgst, txtHsnCode.getX(), lblIgst.getY() - 10, 150,
				ApplicationCommon.frameHeight * 3 / 100, TextInputType.NUMERIC, 6, TextSpaceReq.NOTREQUIRED, true,
				new Font("Calibri", Font.PLAIN, 20));
		panelSubEntry.add(txtIgst);

		lblActive = new JLabel();
		lblActive = labelCreation("Active ", lblCgst.getX(), lblIgst.getY(), 100, 20,
				new Font("Calibri", Font.PLAIN, 20), Color.black, true);
		panelSubEntry.add(lblActive);

		chkActive = checkBoxCreation(chkActive, txtCgst.getX(), lblActive.getY() - 13,
				ApplicationCommon.frameWidth * 8 / 100, ApplicationCommon.frameHeight * 4 / 100, "YES", Color.black,
				true, new Font("Calibri", Font.PLAIN, 20));
		panelSubEntry.add(chkActive);

		ApplicationCommon.componentEnableDisable(getContentPane().getComponents(), false);

	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {

	}

	@Override
	public void add() {
		ApplicationCommon.componentEnableDisable(getContentPane().getComponents(), true);
		txtHsnCode.requestFocus();
	}

	@Override
	public void save() {
		try {
			Map<String, Object> mapHsn = new HashMap<>();

			if (txtHsnCode.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(panelMain, "HSN Code Should not be Empty...!", getTitle(),
						JOptionPane.WARNING_MESSAGE);
				txtHsnCode.requestFocus();
				return;
			}

			if (txtHsnName.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(panelMain, "HSN Name Should not be Empty...!", getTitle(),
						JOptionPane.WARNING_MESSAGE);
				txtHsnName.requestFocus();
				return;
			}

			if (Double.parseDouble(txtSgst.getText()) > 100) {
				JOptionPane.showMessageDialog(panelMain, "SGST Should be Less than (OR) Equal to 100", getTitle(),
						JOptionPane.WARNING_MESSAGE);
				txtSgst.requestFocus();
				return;
			}

			if (Double.parseDouble(txtCgst.getText()) > 100) {
				JOptionPane.showMessageDialog(panelMain, "CGST Should be Less than (OR) Equal to 100", getTitle(),
						JOptionPane.WARNING_MESSAGE);
				txtCgst.requestFocus();
				return;
			}

			if (Double.parseDouble(txtIgst.getText()) > 100) {
				JOptionPane.showMessageDialog(panelMain, "SGST Should be Less than (OR) Equal to 100", getTitle(),
						JOptionPane.WARNING_MESSAGE);
				txtIgst.requestFocus();
				return;
			}

			mapHsn.put("hsncode", txtHsnCode.getText());
			mapHsn.put("hsnname", txtHsnName.getText());
			mapHsn.put("sgst", txtSgst.getText().equalsIgnoreCase("") ? 0 : txtSgst.getText());
			mapHsn.put("cgst", txtCgst.getText().equalsIgnoreCase("") ? 0 : txtSgst.getText());
			mapHsn.put("igst", txtIgst.getText().equalsIgnoreCase("") ? 0 : txtSgst.getText());
			mapHsn.put("active", chkActive.isSelected() ? "Y" : "N");
			mapHsn.put("companytag", ApplicationConfig.companyTag);
			mapHsn.put("companyflag", ApplicationConfig.companyFlag);
			mapHsn.put("createdby", ApplicationCommon.getMapOperDetails().get("OperatorCode"));

			boolean save = logicHsnBasic.saveHsnBasic(mapHsn);

			if (save) {
				JOptionPane.showMessageDialog(panelMain, "HSNCode Saved SuccessFully...!", getTitle(),
						JOptionPane.INFORMATION_MESSAGE);
				loadInitials();
				txtHsnCode.requestFocus();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(panelMain, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
		}

	}

	@Override
	public void update() {

	}

	@Override
	public void view() {

	}

	@Override
	public void clear() {
		loadInitials();
		ApplicationCommon.componentEnableDisable(getContentPane().getComponents(), false);
		frmMdi.getBtnAdd().requestFocus();
	}

	@Override
	public void close() {
		loadInitials();
		this.dispose();
		frmMdi.getBtnAdd().setEnabled(true);
		frmMdi.getBtnSave().setText("Save");
		frmMdi.getBtnSave().setMnemonic(KeyEvent.VK_S);
		ApplicationCommon.setCurrentForm(null);
		frmMdi.getPanelButton().setVisible(false);
		frmMdi.CloseMethod();
	}

	@Override
	public void posting() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER: {
			if (e.getSource() == txtHsnCode) {
				txtHsnName.requestFocus();
			} else if (e.getSource() == txtHsnName) {
				txtSgst.requestFocus();
			} else if (e.getSource() == txtSgst) {
				txtCgst.requestFocus();
			} else if (e.getSource() == txtCgst) {
				txtIgst.requestFocus();
			} else if (e.getSource() == txtIgst) {
				chkActive.requestFocus();
			} else if (e.getSource() == chkActive) {
				frmMdi.getBtnSave().requestFocus();
			}

			break;
		}
		default:

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public JPanel getPanelMain() {
		return panelMain;
	}

	public void setPanelMain(JPanel panelMain) {
		this.panelMain = panelMain;
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

	private TextField textFieldCreation(TextField txtField, int x, int y, int compWidth, int compHeight,
			TextInputType txtInputType, int maxLength, TextSpaceReq txtSpaceReq, boolean visible, Font font) {

		txtField = new TextField();
		txtField.setBounds(x, y, compWidth, compHeight);
		txtField.setTextType(txtInputType);
		txtField.setMaxLength(maxLength);
		txtField.setTextSpaceReq(txtSpaceReq);
		txtField.setFont(font);
		txtField.addKeyListener(this);
		txtField.setVisible(visible);

		return txtField;
	}

	private JCheckBox checkBoxCreation(JCheckBox chkBox, int x, int y, int compWidth, int compHeight, String chkName,
			Color fontColor, boolean visible, Font font) {
		chkBox = new JCheckBox();
		chkBox.setBounds(x, y, compWidth, compHeight);
		chkBox.setText(chkName);
		chkBox.setForeground(fontColor);
		chkBox.addKeyListener(this);
		chkBox.setVisible(true);
		chkBox.setFont(font);

		return chkBox;
	}

}
