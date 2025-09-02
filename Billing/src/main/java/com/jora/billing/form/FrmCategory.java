package com.jora.billing.form;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import org.springframework.stereotype.Component;

import com.jora.billing.common.ApplicationCommon;
import com.jora.billing.common.FormAction;
import com.jora.billing.common.TextField;

@Component
public class FrmCategory extends JInternalFrame
		implements KeyListener, ActionListener, FormAction, InternalFrameListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelMain, panelEntry, panelView;
	private TextField txtProName, txtPrice;
	private JCheckBox chkActive, chkStone;
	private JLabel lblInfo;
	private int procode;

//	@Autowired
//	private FrmMdi frmMdi;

//	@Autowired
//	private ProductLogic productLogic;

	public FrmCategory() throws Exception {
//		getContentPane().setPreferredSize(
//				new Dimension(ApplicationCommon.getInternalFrameWidth(), ApplicationCommon.getInternalFrameHeight()));
//		getContentPane().setSize(
//				new Dimension(ApplicationCommon.getInternalFrameWidth(), ApplicationCommon.getInternalFrameHeight()));
//		pack();
		setTitle("HSN BASICS");
		setMaximum(true);
		setResizable(false);
		addInternalFrameListener(this);
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		componentCreation();
	}

	private void componentCreation() {

		panelMain = new JPanel(null);
		panelMain.setBounds(0, 0, ApplicationCommon.getInternalFrameWidth(),
				ApplicationCommon.getInternalFrameHeight());
		panelMain.setVisible(true);
		panelMain.setBackground(Color.black);
		getContentPane().add(panelMain);

		panelEntryCreation();
		panelViewCreation();

	}

	private void panelViewCreation() {
		panelView = new JPanel(null);
		panelView.setBounds(0, 0, ApplicationCommon.getInternalFrameWidth(),
				ApplicationCommon.getInternalFrameHeight());
		panelView.setBackground(Color.BLUE);
		panelView.setVisible(false);
		panelMain.add(panelView);

	}

	private void panelEntryCreation() {
		JLabel lblProname, lblStone, lblPrice, lblActive, lblTitle;

		panelEntry = new JPanel(null);
		panelEntry.setBounds(0, 0, ApplicationCommon.getInternalFrameWidth(),
				ApplicationCommon.getInternalFrameHeight());
		panelEntry.setBackground(Color.red);
		panelEntry.setVisible(true);
		panelMain.add(panelEntry);

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

	}

	@Override
	public void save() {

	}

	@Override
	public void update() {

	}

	@Override
	public void view() {

	}

	@Override
	public void clear() {

	}

	@Override
	public void close() {

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

}
