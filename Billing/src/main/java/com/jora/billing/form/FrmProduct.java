package com.jora.billing.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.jora.billing.common.ApplicationCommon;
import com.jora.billing.common.ComboBox;
import com.jora.billing.common.FormAction;
import com.jora.billing.common.TextField;
import com.jora.billing.common.JTextFieldEnum.TextInputType;
import com.jora.billing.common.JTextFieldEnum.TextSpaceReq;
import com.jora.billing.connection.ApplicationConfig;
import com.jora.billing.logic.LogicCommon;
import com.jora.billing.logic.LogicHsnBasic;
import com.jora.billing.logic.LogicProduct;

import lombok.RequiredArgsConstructor;

@Component
public class FrmProduct extends JInternalFrame
		implements KeyListener, ActionListener, FormAction, InternalFrameListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelMain, panelEntry, panelView;
	private TextField txtHsnCode, txtProdDesc, txtSgst, txtCgst, txtIgst;
	private JCheckBox chkActive;
	private ComboBox<Object> cmbCatDesc, cmbSaleType, cmbPurType, cmbPrdGrp, cmbTaxBasedOn, cmbUnit;
	private JLabel lblInfo;

	@Autowired
	private FrmMdi frmMdi;

	private final LogicCommon logicCommon;
	private final LogicProduct logicProduct;

	public FrmProduct(LogicCommon logicCommon, LogicProduct logicProduct) throws Exception {
		getContentPane().setPreferredSize(
				new Dimension(ApplicationCommon.getInternalFrameWidth(), ApplicationCommon.getInternalFrameHeight()));
		getContentPane().setSize(
				new Dimension(ApplicationCommon.getInternalFrameWidth(), ApplicationCommon.getInternalFrameHeight()));
		pack();
		setTitle("CATEGORY");
		setMaximum(true);
		setResizable(false);
		addInternalFrameListener(this);
		this.logicCommon = logicCommon;
		this.logicProduct = logicProduct;

	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		componentCreation();
		loadInitials();
	}

	public void loadInitials() {
		try {
			txtHsnCode.setText("");
			txtProdDesc.setText("");
			txtSgst.setText("");
			txtCgst.setText("");
			txtIgst.setText("");
			chkActive.setSelected(false);
			logicCommon.loadCategory(cmbCatDesc, false);
			logicCommon.loadSPType(cmbSaleType, false);
			logicCommon.loadSPType(cmbPurType, false);
			logicProduct.loadTaxBasedOn(cmbTaxBasedOn);

			txtCgst.setEditable(false);
			txtSgst.setEditable(false);
			txtIgst.setEditable(false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(panelMain, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
		}

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
		JLabel lblCategory, lblHsnCode, lblProdDesc, lblSgst, lblCgst, lblIgst, lblActive, lblSaleType, lblPurType,
				lblPrdGrp, lblTaxBased, lblUnit;
		JPanel panelSubEntry;

		panelEntry = new JPanel(null);
		panelEntry.setBounds(0, 0, ApplicationCommon.getInternalFrameWidth(),
				ApplicationCommon.getInternalFrameHeight());
		panelEntry.setBackground(Color.decode("#FFFFFF"));
		panelEntry.setVisible(true);
		panelMain.add(panelEntry);

		panelSubEntry = new JPanel(null);
		panelSubEntry.setBounds(150, 130, 1600, 650);
		panelSubEntry.setBackground(Color.decode("#FFFFFF"));
		panelSubEntry.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
		panelSubEntry.setVisible(true);
		panelEntry.add(panelSubEntry);

		lblCategory = new JLabel();
		lblCategory = labelCreation("Category ", 40, 60, 100, 20, new Font("Calibri", Font.PLAIN, 20), Color.black,
				true);
		panelSubEntry.add(lblCategory);

		cmbCatDesc = comboBoxCreation(cmbCatDesc, lblCategory.getX() + 120, lblCategory.getY(), 500, 30, Color.WHITE,
				true, new Font("Ebrima", Font.BOLD, 16));

		panelSubEntry.add(cmbCatDesc);

		lblHsnCode = new JLabel();
		lblHsnCode = labelCreation("HSNCode ", cmbCatDesc.getX() + 700, lblCategory.getY(), 100, 20,
				new Font("Calibri", Font.PLAIN, 20), Color.black, true);
		panelSubEntry.add(lblHsnCode);

		txtHsnCode = textFieldCreation(txtHsnCode, lblHsnCode.getX() + 160, lblHsnCode.getY() - 10, 300,
				ApplicationCommon.frameHeight * 3 / 100, TextInputType.ALPHANUMBER, 50, TextSpaceReq.NOTREQUIRED, true,
				new Font("Calibri", Font.PLAIN, 20));
		panelSubEntry.add(txtHsnCode);

		lblProdDesc = new JLabel();
		lblProdDesc = labelCreation("Product ", lblCategory.getX(), lblCategory.getY() + 100, 100, 20,
				new Font("Calibri", Font.PLAIN, 20), Color.black, true);
		panelSubEntry.add(lblProdDesc);

		txtProdDesc = textFieldCreation(txtProdDesc, cmbCatDesc.getX(), lblProdDesc.getY() - 10, 500,
				ApplicationCommon.frameHeight * 3 / 100, TextInputType.ALPHANUMBER, 20, TextSpaceReq.REQUIRED, true,
				new Font("Calibri", Font.PLAIN, 20));
		panelSubEntry.add(txtProdDesc);

		lblPrdGrp = new JLabel();
		lblPrdGrp = labelCreation("Product Group  ", lblHsnCode.getX(), lblProdDesc.getY(), 150, 20,
				new Font("Calibri", Font.PLAIN, 20), Color.black, true);
		panelSubEntry.add(lblPrdGrp);

		cmbPrdGrp = comboBoxCreation(cmbPrdGrp, txtHsnCode.getX(), lblPrdGrp.getY(), 500, 30, Color.WHITE, true,
				new Font("Ebrima", Font.BOLD, 16));
		panelSubEntry.add(cmbPrdGrp);

		lblSaleType = new JLabel();
		lblSaleType = labelCreation("Sales Type ", lblCategory.getX(), lblProdDesc.getY() + 100, 100, 20,
				new Font("Calibri", Font.PLAIN, 20), Color.black, true);
		panelSubEntry.add(lblSaleType);

		cmbSaleType = comboBoxCreation(cmbSaleType, txtProdDesc.getX(), lblSaleType.getY(), 500, 30, Color.WHITE, true,
				new Font("Ebrima", Font.BOLD, 16));

		panelSubEntry.add(cmbSaleType);

		lblPurType = new JLabel();
		lblPurType = labelCreation("Purchase Type ", lblHsnCode.getX(), lblSaleType.getY(), 150, 20,
				new Font("Calibri", Font.PLAIN, 20), Color.black, true);
		panelSubEntry.add(lblPurType);

		cmbPurType = comboBoxCreation(cmbPurType, txtHsnCode.getX(), lblPurType.getY(), 500, 30, Color.WHITE, true,
				new Font("Ebrima", Font.BOLD, 16));

		panelSubEntry.add(cmbPurType);

		lblActive = new JLabel();
		lblActive = labelCreation("Active ", lblCategory.getX(), lblSaleType.getY() + 100, 100, 20,
				new Font("Calibri", Font.PLAIN, 20), Color.black, true);
		panelSubEntry.add(lblActive);

		chkActive = checkBoxCreation(chkActive, cmbSaleType.getX(), lblActive.getY() - 13,
				ApplicationCommon.frameWidth * 8 / 100, ApplicationCommon.frameHeight * 4 / 100, "YES", Color.black,
				true, new Font("Calibri", Font.PLAIN, 20));
		panelSubEntry.add(chkActive);

		lblTaxBased = new JLabel();
		lblTaxBased = labelCreation("Tax Based On ", lblHsnCode.getX(), lblActive.getY(), 150, 20,
				new Font("Calibri", Font.PLAIN, 20), Color.black, true);
		panelSubEntry.add(lblTaxBased);

		cmbTaxBasedOn = comboBoxCreation(cmbTaxBasedOn, txtHsnCode.getX(), lblTaxBased.getY(), 500, 30, Color.WHITE,
				true, new Font("Ebrima", Font.BOLD, 16));

		panelSubEntry.add(cmbTaxBasedOn);

		lblSgst = new JLabel();
		lblSgst = labelCreation("SGST ", lblCategory.getX(), lblActive.getY() + 100, 100, 20,
				new Font("Calibri", Font.PLAIN, 20), Color.black, true);
		panelSubEntry.add(lblSgst);

		txtSgst = textFieldCreation(txtSgst, cmbCatDesc.getX(), lblSgst.getY() - 10, 180,
				ApplicationCommon.frameHeight * 3 / 100, TextInputType.NUMERIC, 6, TextSpaceReq.NOTREQUIRED, true,
				new Font("Calibri", Font.PLAIN, 20));
		panelSubEntry.add(txtSgst);

		lblCgst = new JLabel();
		lblCgst = labelCreation("CGST ", lblHsnCode.getX(), lblSgst.getY(), 100, 20,
				new Font("Calibri", Font.PLAIN, 20), Color.black, true);
		panelSubEntry.add(lblCgst);

		txtCgst = textFieldCreation(txtCgst, txtHsnCode.getX(), lblCgst.getY() - 10, 150,
				ApplicationCommon.frameHeight * 3 / 100, TextInputType.NUMERIC, 6, TextSpaceReq.NOTREQUIRED, true,
				new Font("Calibri", Font.PLAIN, 20));
		panelSubEntry.add(txtCgst);

		lblIgst = new JLabel();
		lblIgst = labelCreation("IGST ", lblCategory.getX(), lblSgst.getY() + 100, 100, 20,
				new Font("Calibri", Font.PLAIN, 20), Color.black, true);
		panelSubEntry.add(lblIgst);

		txtIgst = textFieldCreation(txtIgst, cmbCatDesc.getX(), lblIgst.getY() - 10, 150,
				ApplicationCommon.frameHeight * 3 / 100, TextInputType.NUMERIC, 6, TextSpaceReq.NOTREQUIRED, true,
				new Font("Calibri", Font.PLAIN, 20));
		panelSubEntry.add(txtIgst);

		comboBoxEditorComponent();

		ApplicationCommon.componentEnableDisable(getContentPane().getComponents(), false);

	}

	private void comboBoxEditorComponent() {
		cmbCatDesc.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (String.valueOf(cmbCatDesc.getSelectedItemValue()).equalsIgnoreCase("")
							|| String.valueOf(cmbCatDesc.getSelectedItemValue()).equalsIgnoreCase("null")) {
						JOptionPane.showMessageDialog(panelMain, "Select Valid Category...!", getTitle(),
								JOptionPane.WARNING_MESSAGE);
						cmbCatDesc.requestFocus();
						return;
					}
					enterFromCatDesc();
					txtProdDesc.requestFocus();
				}

			}
		});

		cmbSaleType.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (String.valueOf(cmbSaleType.getSelectedItemValue()).equalsIgnoreCase("")
							|| String.valueOf(cmbSaleType.getSelectedItemValue()).equalsIgnoreCase("null")) {
						JOptionPane.showMessageDialog(panelMain, "Select Valid SaleType...!", getTitle(),
								JOptionPane.WARNING_MESSAGE);
						cmbSaleType.requestFocus();
						return;
					}
					cmbPurType.requestFocus();
				}

			}
		});

		cmbPurType.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (String.valueOf(cmbPurType.getSelectedItemValue()).equalsIgnoreCase("")
							|| String.valueOf(cmbPurType.getSelectedItemValue()).equalsIgnoreCase("null")) {
						JOptionPane.showMessageDialog(panelMain, "Select Valid Purchase Type...!", getTitle(),
								JOptionPane.WARNING_MESSAGE);
						cmbPurType.requestFocus();
						return;
					}
					chkActive.requestFocus();
				}

			}
		});

		cmbTaxBasedOn.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (String.valueOf(cmbTaxBasedOn.getSelectedItemValue()).equalsIgnoreCase("")
							|| String.valueOf(cmbTaxBasedOn.getSelectedItemValue()).equalsIgnoreCase("null")) {
						JOptionPane.showMessageDialog(panelMain, "Select Tax Based On...!", getTitle(),
								JOptionPane.WARNING_MESSAGE);
						cmbTaxBasedOn.requestFocus();
						return;
					}

					enterFromTaxBasedOn();
					txtSgst.requestFocus();
				}

			}

		});
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

	private void enterFromTaxBasedOn() {
		if (String.valueOf(cmbTaxBasedOn.getSelectedItemValue()).equalsIgnoreCase("C")) {
			txtCgst.setEditable(false);
			txtSgst.setEditable(false);
			txtIgst.setEditable(false);
		} else if (String.valueOf(cmbTaxBasedOn.getSelectedItemValue()).equalsIgnoreCase("P")) {
			txtCgst.setEditable(true);
			txtSgst.setEditable(true);
			txtIgst.setEditable(true);
		}

	}

	@Override
	public void add() {
		ApplicationCommon.componentEnableDisable(getContentPane().getComponents(), true);
		cmbCatDesc.requestFocus();
	}

	@Override
	public void save() {
		try {
			Map<String, Object> mapProduct = new HashMap<>();

			if (String.valueOf(cmbCatDesc.getSelectedItemValue()).equalsIgnoreCase("")
					|| String.valueOf(cmbCatDesc.getSelectedItemValue()).equalsIgnoreCase("null")) {
				JOptionPane.showMessageDialog(panelMain, "Select Valid Category...!", getTitle(),
						JOptionPane.WARNING_MESSAGE);
				cmbCatDesc.requestFocus();
				return;
			}

			if (String.valueOf(cmbSaleType.getSelectedItemValue()).equalsIgnoreCase("")
					|| String.valueOf(cmbSaleType.getSelectedItemValue()).equalsIgnoreCase("null")) {
				JOptionPane.showMessageDialog(panelMain, "Select Valid SaleType...!", getTitle(),
						JOptionPane.WARNING_MESSAGE);
				cmbSaleType.requestFocus();
				return;
			}

			if (String.valueOf(cmbPurType.getSelectedItemValue()).equalsIgnoreCase("")
					|| String.valueOf(cmbPurType.getSelectedItemValue()).equalsIgnoreCase("null")) {
				JOptionPane.showMessageDialog(panelMain, "Select Valid Purchase Type...!", getTitle(),
						JOptionPane.WARNING_MESSAGE);
				cmbPurType.requestFocus();
				return;
			}

			if (String.valueOf(cmbTaxBasedOn.getSelectedItemValue()).equalsIgnoreCase("")
					|| String.valueOf(cmbTaxBasedOn.getSelectedItemValue()).equalsIgnoreCase("null")) {
				JOptionPane.showMessageDialog(panelMain, "Select Tax Based On...!", getTitle(),
						JOptionPane.WARNING_MESSAGE);
				cmbTaxBasedOn.requestFocus();
				return;
			}

			if (txtHsnCode.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(panelMain, "HSN Code Should not be Empty...!", getTitle(),
						JOptionPane.WARNING_MESSAGE);
				txtHsnCode.requestFocus();
				return;
			}

			if (txtProdDesc.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(panelMain, "Product Descripton Should not be Empty...!", getTitle(),
						JOptionPane.WARNING_MESSAGE);
				txtProdDesc.requestFocus();
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

			mapProduct.put("catno", cmbCatDesc.getSelectedItemValue());
			mapProduct.put("hsncode", txtHsnCode.getText());
			mapProduct.put("proddesc", txtProdDesc.getText());
			mapProduct.put("salestype", cmbSaleType.getSelectedItemValue());
			mapProduct.put("puchasetype", cmbPurType.getSelectedItemValue());
			mapProduct.put("sgst", txtSgst.getText().equalsIgnoreCase("") ? 0 : txtSgst.getText());
			mapProduct.put("cgst", txtCgst.getText().equalsIgnoreCase("") ? 0 : txtSgst.getText());
			mapProduct.put("igst", txtIgst.getText().equalsIgnoreCase("") ? 0 : txtSgst.getText());
			mapProduct.put("active", chkActive.isSelected() ? "Y" : "N");
			mapProduct.put("table", "Product");
			mapProduct.put("companytag", ApplicationConfig.companyTag);
			mapProduct.put("companyflag", ApplicationConfig.companyFlag);
			mapProduct.put("createdby", ApplicationCommon.getMapOperDetails().get("OperatorCode"));

			String proNo = logicProduct.saveProduct(mapProduct);

			if (!proNo.equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(panelMain, "Product Saved SuccessFully...!", getTitle(),
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
		this.setVisible(false);
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
		try {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_ENTER: {
				if (e.getSource() == txtHsnCode) {
					txtProdDesc.requestFocus();
				} else if (e.getSource() == txtProdDesc) {
					cmbSaleType.requestFocus();
				} else if (e.getSource() == txtSgst) {
					txtCgst.requestFocus();
				} else if (e.getSource() == txtCgst) {
					txtIgst.requestFocus();
				} else if (e.getSource() == txtIgst) {
					frmMdi.getBtnSave().requestFocus();
				} else if (e.getSource() == chkActive) {
					cmbTaxBasedOn.requestFocus();
				}

				break;
			}

			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(panelMain, e2.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
		}

	}

	private void enterFromCatDesc() {
		try {

			logicProduct.enterFromCatDesc(txtHsnCode, cmbSaleType, txtSgst, txtCgst, txtIgst,
					Integer.parseInt(String.valueOf(cmbCatDesc.getSelectedItemValue())));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(panelMain, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
			cmbCatDesc.requestFocus();
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

	private ComboBox<Object> comboBoxCreation(ComboBox<Object> cmbComboBox, int x, int y, int width, int height,
			Color color, boolean edit, Font font) {
		cmbComboBox = new ComboBox<Object>();

		cmbComboBox.setBounds(x, y, width, height);
		cmbComboBox.setBackground(color);
		cmbComboBox.setEditable(edit);
		cmbComboBox.addKeyListener(this);
		cmbComboBox.setFont(font);

		return cmbComboBox;
	}

}
