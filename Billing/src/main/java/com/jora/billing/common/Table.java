package com.jora.billing.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Table extends JTable {

	private static final long serialVersionUID = 1L;
	private final List<Column> columns;
	private final DefaultTableModel model;
	private final TableConfig config;

	public static class Column {
		String name;
		int width;
		int alignment;
		boolean resizable;
		boolean editable;

		public Column(String name, int width, int alignment) {
			this(name, width, alignment, false, false);
		}

		public Column(String name, int width, int alignment, boolean resizable) {
			this(name, width, alignment, resizable, false);
		}

		public Column(String name, int width, int alignment, boolean resizable, boolean editable) {
			this.name = name;
			this.width = width;
			this.alignment = alignment;
			this.resizable = resizable;
			this.editable = editable;
		}
	}

	public static class TableConfig {
		public Color headerBgColor;
		public Color headerFgColor;
		public Font headerFont;

		public Color contentBgColor;
		public Color contentFgColor;
		public Font contentFont;

		public Color selectionBgColor;
		public Color selectionFgColor;

		public boolean allowColumnReordering = true;
		public boolean showGrid = true;
		public int autoResizeMode = JTable.AUTO_RESIZE_OFF;
	}

	public Table(List<Column> columns, TableConfig config) {
		super();
		this.columns = columns;
		this.config = config;

		// Table model
		this.model = new DefaultTableModel(new Object[][] {},
				columns.stream().map(c -> c.name).toArray(String[]::new)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return columns.get(column).editable;
			}
		};
		setModel(model);

		// Apply config
		applyConfig();

		// Disable FlatLaf hover automatically
		disableFlatLafHeaderHover();
	}

	private void applyConfig() {
		// Selection colors
		if (config.selectionBgColor != null)
			setSelectionBackground(config.selectionBgColor);
		if (config.selectionFgColor != null)
			setSelectionForeground(config.selectionFgColor);

		// Grid lines
		setShowHorizontalLines(config.showGrid);
		setShowVerticalLines(config.showGrid);

		// Column reordering
		getTableHeader().setReorderingAllowed(config.allowColumnReordering);

		// Auto resize mode
		setAutoResizeMode(config.autoResizeMode);

		// Content font
		if (config.contentFont != null) {
			setFont(config.contentFont);
			setRowHeight(config.contentFont.getSize() + 8);
		}

		refreshRenderers();
		configureColumns();
	}

	private void disableFlatLafHeaderHover() {
		JTableHeader header = getTableHeader();
		header.putClientProperty("TableHeader.hoverable", Boolean.FALSE);
		header.putClientProperty("TableHeader.cellHoverBackground",
				config.headerBgColor != null ? config.headerBgColor : UIManager.getColor("TableHeader.background"));
		header.putClientProperty("TableHeader.cellPressedBackground",
				config.headerBgColor != null ? config.headerBgColor : UIManager.getColor("TableHeader.background"));
	}

	public void addRow(List<Object> row) {
		model.addRow(row.toArray());
	}

	public void addRows(List<Map<String, Object>> rows) {
		for (Map<String, Object> row : rows) {
			Object[] rowData = columns.stream().map(c -> row.getOrDefault(c.name, "")).toArray();
			model.addRow(rowData);
		}
	}

	public void clear() {
		model.setRowCount(0);
	}

	private void refreshRenderers() {
		JTableHeader header = getTableHeader();
		header.setOpaque(true);
		header.setDefaultRenderer(new HeaderRenderer(header.getDefaultRenderer(), config.headerBgColor,
				config.headerFgColor, config.headerFont));
	}

	private void configureColumns() {
		TableColumnModel columnModel = getColumnModel();
		for (int i = 0; i < columns.size(); i++) {
			Column col = columns.get(i);
			TableColumn tableCol = columnModel.getColumn(i);

			if (col.width > 0) {
				tableCol.setPreferredWidth(col.width);
				if (!col.resizable) {
					tableCol.setMinWidth(col.width);
					tableCol.setMaxWidth(col.width);
				}
			}
			tableCol.setResizable(col.resizable);

			tableCol.setCellRenderer(new RowRenderer(
					config.selectionBgColor != null ? config.selectionBgColor : getSelectionBackground(),
					config.selectionFgColor != null ? config.selectionFgColor : getSelectionForeground(),
					config.contentBgColor, config.contentFgColor, config.contentFont, col.alignment));
		}
	}

	private static class HeaderRenderer implements TableCellRenderer {
		private final TableCellRenderer delegate;
		private final Color bg;
		private final Color fg;
		private final Font font;

		public HeaderRenderer(TableCellRenderer delegate, Color bg, Color fg, Font font) {
			this.delegate = delegate;
			this.bg = bg;
			this.fg = fg;
			this.font = font;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Component comp = delegate.getTableCellRendererComponent(table, value, false, false, row, column);

			if (bg != null)
				comp.setBackground(bg);
			if (fg != null)
				comp.setForeground(fg);
			if (font != null)
				comp.setFont(font);
			if (comp instanceof JLabel lbl)
				lbl.setHorizontalAlignment(SwingConstants.CENTER);

			return comp;
		}
	}

	private static class RowRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;
		private final Color focusBg;
		private final Color focusFg;
		private final Color normalBg;
		private final Color normalFg;
		private final Font font;
		private final int alignment;

		public RowRenderer(Color focusBg, Color focusFg, Color normalBg, Color normalFg, Font font, int alignment) {
			this.focusBg = focusBg;
			this.focusFg = focusFg;
			this.normalBg = normalBg;
			this.normalFg = normalFg;
			this.font = font;
			this.alignment = alignment;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			if (font != null)
				comp.setFont(font);

			if (isSelected) {
				comp.setBackground(focusBg != null ? focusBg : table.getSelectionBackground());
				comp.setForeground(focusFg != null ? focusFg : table.getSelectionForeground());
			} else {
				comp.setBackground(normalBg != null ? normalBg : Color.WHITE);
				comp.setForeground(normalFg != null ? normalFg : Color.BLACK);
			}

			if (comp instanceof JLabel lbl)
				lbl.setHorizontalAlignment(alignment);
			setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210)));
			return comp;
		}
	}

	public void selectFirstRow() {
		SwingUtilities.invokeLater(() -> {
			setRowSelectionInterval(0, 0);
		    setColumnSelectionInterval(0, 0);
		    scrollRectToVisible(getCellRect(0, 0, true));
		    requestFocusInWindow();
		});

	}
}
