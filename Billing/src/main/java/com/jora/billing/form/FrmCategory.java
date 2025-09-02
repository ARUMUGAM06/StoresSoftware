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
import com.jora.billing.common.ComboBox;
import com.jora.billing.common.FormAction;
import com.jora.billing.common.JTextFieldEnum.TextInputType;
import com.jora.billing.common.JTextFieldEnum.TextSpaceReq;
import com.jora.billing.common.ListItem;
import com.jora.billing.common.TextField;
import com.jora.billing.connection.ApplicationConfig;
import com.jora.billing.logic.CategoryLogic;

@Component
public class FrmCategory extends JInternalFrame
		implements KeyListener, ActionListener, FormAction, InternalFrameListener {

	private static final long serialVersionUID = 1L;
	private JPanel panelMain, panelEntry, panelView;
	private TextField txtCatGroup, txtCatName;
	private ComboBox<ListItem> cmbHsnCode, cmbSaleType;
	private JCheckBox chkActive;

	private final CategoryLogic categoryLogic;

	private final FrmMdi frmMdi;

	public FrmCategory(CategoryLogic categoryLogic, FrmMdi frmMdi) throws Exception {
		this.categoryLogic = categoryLogic;
		this.frmMdi = frmMdi;

		getContentPane().setPreferredSize(
				new Dimension(ApplicationCommon.getInternalFrameWidth(), ApplicationCommon.getInternalFrameHeight()));
		getContentPane().setSize(
				new Dimension(ApplicationCommon.getInternalFrameWidth(), ApplicationCommon.getInternalFrameHeight()));
		pack();
		setTitle("Category");
		setMaximum(true);
		setResizable(false);
		addInternalFrameListener(this);
		componentCreation();
		componentListener();
	}

	private void componentListener() throws Exception {
		txtCatName.addKeyListener(this);
		cmbHsnCode.addKeyListener(this);
		cmbSaleType.addKeyListener(this);
		chkActive.addKeyListener(this);
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		try {
			categoryLogic.loadHsnBasics(cmbHsnCode);
			categoryLogic.loadSaletype(cmbSaleType);
			loadInitials();
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, e2.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
			e2.printStackTrace();
		}
	}

	public void loadInitials() {
		txtCatGroup.setText("");
		txtCatName.setText("");
		cmbHsnCode.setSelectedIndex(0);
		cmbSaleType.setSelectedIndex(0);
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
		JLabel lblCatName, lblCatGroup, lblHsnCode, lblSaleType, lblActive;
		JPanel panelSubEntry;

		panelEntry = new JPanel(null);
		panelEntry.setBounds(0, 0, ApplicationCommon.getInternalFrameWidth(),
				ApplicationCommon.getInternalFrameHeight());
		panelEntry.setBackground(Color.decode("#FFFFFF"));
		panelEntry.setVisible(true);
		panelMain.add(panelEntry);

		panelSubEntry = new JPanel(null);
		panelSubEntry.setBounds(20 * panelEntry.getWidth() / 100, 20 * panelEntry.getHeight() / 100,
				60 * panelEntry.getWidth() / 100, 60 * panelEntry.getHeight() / 100);
		panelSubEntry.setBackground(Color.decode("#FFFFFF"));
		panelSubEntry.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
		panelSubEntry.setVisible(true);
		panelEntry.add(panelSubEntry);

		int xGap = panelSubEntry.getWidth() * 2 / 100;
		int yGap = panelSubEntry.getHeight() * 5 / 100;

		lblCatGroup = new JLabel();
		lblCatGroup = labelCreation("Category Group", panelSubEntry.getWidth() * 20 / 100,
				panelSubEntry.getHeight() * 20 / 100, panelSubEntry.getWidth() * 20 / 100,
				ApplicationCommon.frameHeight * 4 / 100, new Font("Calibri", Font.PLAIN, 20), Color.black, true);

		txtCatGroup = textFieldCreation(txtCatGroup, lblCatGroup.getX() + lblCatGroup.getWidth() + xGap,
				lblCatGroup.getY(), panelSubEntry.getWidth() * 20 / 100, lblCatGroup.getHeight(),
				TextInputType.ALPHANUMBER, 20, TextSpaceReq.NOTREQUIRED, true, new Font("Calibri", Font.PLAIN, 20));

		lblCatName = new JLabel();
		lblCatName = labelCreation("Category Name", lblCatGroup.getX(),
				lblCatGroup.getY() + lblCatGroup.getHeight() + yGap, lblCatGroup.getWidth(),
				ApplicationCommon.frameHeight * 5 / 100, new Font("Calibri", Font.PLAIN, 20), Color.black, true);

		txtCatName = textFieldCreation(txtCatName, lblCatName.getX() + lblCatName.getWidth() + xGap, lblCatName.getY(),
				panelSubEntry.getWidth() * 38 / 100, lblCatName.getHeight(), TextInputType.ALPHANUMBER, 30,
				TextSpaceReq.REQUIRED, true, new Font("Calibri", Font.PLAIN, 20));

		lblHsnCode = new JLabel();
		lblHsnCode = labelCreation("HSNCode", lblCatName.getX(), lblCatName.getY() + lblCatName.getHeight() + yGap,
				lblCatName.getWidth(), lblCatName.getHeight(), new Font("Calibri", Font.PLAIN, 20), Color.black, true);

		cmbHsnCode = new ComboBox<ListItem>();
		cmbHsnCode.setBounds(lblHsnCode.getX() + lblHsnCode.getWidth() + xGap, lblHsnCode.getY(), txtCatName.getWidth(),
				lblHsnCode.getHeight());
		cmbHsnCode.setFont(new Font("Calibri", Font.PLAIN, 20));

		lblSaleType = new JLabel();
		lblSaleType = labelCreation("SaleType", lblHsnCode.getX(), lblHsnCode.getY() + lblHsnCode.getHeight() + yGap,
				lblHsnCode.getWidth(), lblHsnCode.getHeight(), new Font("Calibri", Font.PLAIN, 20), Color.black, true);

		cmbSaleType = new ComboBox<ListItem>();
		cmbSaleType.setBounds(lblSaleType.getX() + lblHsnCode.getWidth() + xGap, lblSaleType.getY(),
				panelSubEntry.getWidth() * 20 / 100, lblSaleType.getHeight());
		cmbSaleType.setFont(new Font("Calibri", Font.PLAIN, 20));

		lblActive = new JLabel();
		lblActive = labelCreation("Active", lblSaleType.getX(), lblSaleType.getY() + lblSaleType.getHeight() + yGap,
				lblSaleType.getWidth(), ApplicationCommon.frameHeight * 4 / 100, new Font("Calibri", Font.PLAIN, 20),
				Color.black, true);

		chkActive = checkBoxCreation(chkActive, lblActive.getX() + lblActive.getWidth() + xGap, lblActive.getY(),
				lblActive.getWidth(), lblActive.getHeight(), "YES", Color.black, true,
				new Font("Calibri", Font.PLAIN, 20));

		panelSubEntry.add(lblCatGroup);
		panelSubEntry.add(txtCatGroup);
		panelSubEntry.add(lblCatName);
		panelSubEntry.add(txtCatName);
		panelSubEntry.add(lblHsnCode);
		panelSubEntry.add(cmbHsnCode);
		panelSubEntry.add(lblSaleType);
		panelSubEntry.add(cmbSaleType);
		panelSubEntry.add(lblActive);
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
		txtCatGroup.setEnabled(false);
		txtCatName.requestFocus();
	}

	@Override
	public void save() {
		try {
			if (txtCatName.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(panelMain, "Category Name Should not be Empty...!", getTitle(),
						JOptionPane.WARNING_MESSAGE);
				txtCatName.requestFocus();
				return;
			}
			Map<String, Object> saveMap = new HashMap<>();
			saveMap.put("catname", txtCatName.getText());
			saveMap.put("hsncode", cmbHsnCode.getSelectedItemValue());
			saveMap.put("saletype", cmbSaleType.getSelectedItemValue());
			saveMap.put("active", chkActive.isSelected() ? "Y" : "N");
			saveMap.put("table", "category");
			saveMap.put("companytag", ApplicationConfig.companyTag);
			saveMap.put("companyflag", ApplicationConfig.companyFlag);
			saveMap.put("createdby", ApplicationCommon.getMapOperDetails().get("OperatorCode"));

			boolean save = categoryLogic.saveCategory(saveMap);

			if (save != false) {
				JOptionPane.showMessageDialog(panelMain, "Category Saved SuccessFully...!", getTitle(),
						JOptionPane.INFORMATION_MESSAGE);
				loadInitials();
				txtCatName.requestFocus();
			}

		} catch (Exception e) {
			e.printStackTrace();
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
			if (e.getSource() == txtCatName) {
				cmbHsnCode.requestFocus();
			} else if (e.getSource() == cmbHsnCode) {
				cmbSaleType.requestFocus();
			} else if (e.getSource() == cmbSaleType) {
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
		txtField.setVisible(visible);

		return txtField;
	}

	private JCheckBox checkBoxCreation(JCheckBox chkBox, int x, int y, int compWidth, int compHeight, String chkName,
			Color fontColor, boolean visible, Font font) {
		chkBox = new JCheckBox();
		chkBox.setBounds(x, y, compWidth, compHeight);
		chkBox.setText(chkName);
		chkBox.setForeground(fontColor);
		chkBox.setVisible(true);
		chkBox.setFont(font);

		return chkBox;
	}

}
