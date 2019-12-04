package project;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.swt.custom.StyledText;

public class Design extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Design(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());

		ViewForm viewForm = new ViewForm(this, SWT.NONE);
		FormData fd_viewForm = new FormData();
		fd_viewForm.top = new FormAttachment(0, 10);
		fd_viewForm.left = new FormAttachment(0, 10);
		viewForm.setLayoutData(fd_viewForm);

		text = new Text(this, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(0, 259);
		fd_text.top = new FormAttachment(0, 40);
		fd_text.left = new FormAttachment(0, 10);
		text.setLayoutData(fd_text);

		Button btnUpload = new Button(this, SWT.NONE);
		FormData fd_btnUpload = new FormData();
		fd_btnUpload.right = new FormAttachment(0, 370);
		fd_btnUpload.top = new FormAttachment(0, 38);
		fd_btnUpload.left = new FormAttachment(0, 265);
		btnUpload.setLayoutData(fd_btnUpload);
		btnUpload.setText("Upload");

		Label lblLoadFile = new Label(this, SWT.NONE);
		FormData fd_lblLoadFile = new FormData();
		fd_lblLoadFile.right = new FormAttachment(0, 91);
		fd_lblLoadFile.top = new FormAttachment(0, 10);
		fd_lblLoadFile.left = new FormAttachment(0, 10);
		lblLoadFile.setLayoutData(fd_lblLoadFile);
		lblLoadFile.setText("Load File");

		Label lblAppendData = new Label(this, SWT.NONE);
		FormData fd_lblAppendData = new FormData();
		fd_lblAppendData.right = new FormAttachment(0, 123);
		fd_lblAppendData.top = new FormAttachment(0, 77);
		fd_lblAppendData.left = new FormAttachment(0, 9);
		lblAppendData.setLayoutData(fd_lblAppendData);
		lblAppendData.setText("Append Data");

		text_1 = new Text(this, SWT.BORDER);
		FormData fd_text_1 = new FormData();
		fd_text_1.right = new FormAttachment(0, 259);
		fd_text_1.top = new FormAttachment(0, 108);
		fd_text_1.left = new FormAttachment(0, 10);
		text_1.setLayoutData(fd_text_1);

		Button btnUpload_1 = new Button(this, SWT.NONE);
		FormData fd_btnUpload_1 = new FormData();
		fd_btnUpload_1.right = new FormAttachment(0, 370);
		fd_btnUpload_1.top = new FormAttachment(0, 106);
		fd_btnUpload_1.left = new FormAttachment(0, 265);
		btnUpload_1.setLayoutData(fd_btnUpload_1);
		btnUpload_1.setText("Upload");

		Label lblEnterDatapointkeyboard = new Label(this, SWT.NONE);
		FormData fd_lblEnterDatapointkeyboard = new FormData();
		fd_lblEnterDatapointkeyboard.right = new FormAttachment(0, 285);
		fd_lblEnterDatapointkeyboard.top = new FormAttachment(0, 145);
		fd_lblEnterDatapointkeyboard.left = new FormAttachment(0, 10);
		lblEnterDatapointkeyboard.setLayoutData(fd_lblEnterDatapointkeyboard);
		lblEnterDatapointkeyboard.setText("Enter datapoint (keyboard)");

		text_2 = new Text(this, SWT.BORDER);
		FormData fd_text_2 = new FormData();
		fd_text_2.top = new FormAttachment(0, 176);
		fd_text_2.left = new FormAttachment(0, 10);
		text_2.setLayoutData(fd_text_2);

		Button btnUpload_2 = new Button(this, SWT.NONE);
		FormData fd_btnUpload_2 = new FormData();
		fd_btnUpload_2.right = new FormAttachment(0, 201);
		fd_btnUpload_2.top = new FormAttachment(0, 176);
		fd_btnUpload_2.left = new FormAttachment(0, 96);
		btnUpload_2.setLayoutData(fd_btnUpload_2);
		btnUpload_2.setText("Upload");

		Label lblDeleteFromDataset = new Label(this, SWT.NONE);
		FormData fd_lblDeleteFromDataset = new FormData();
		fd_lblDeleteFromDataset.right = new FormAttachment(0, 655);
		fd_lblDeleteFromDataset.top = new FormAttachment(0, 10);
		fd_lblDeleteFromDataset.left = new FormAttachment(0, 391);
		lblDeleteFromDataset.setLayoutData(fd_lblDeleteFromDataset);
		lblDeleteFromDataset.setText("Delete from dataset (keyboard)");

		text_3 = new Text(this, SWT.BORDER);
		FormData fd_text_3 = new FormData();
		fd_text_3.top = new FormAttachment(0, 40);
		fd_text_3.left = new FormAttachment(0, 391);
		text_3.setLayoutData(fd_text_3);

		Button btnDelete = new Button(this, SWT.NONE);
		FormData fd_btnDelete = new FormData();
		fd_btnDelete.right = new FormAttachment(0, 582);
		fd_btnDelete.top = new FormAttachment(0, 36);
		fd_btnDelete.left = new FormAttachment(0, 477);
		btnDelete.setLayoutData(fd_btnDelete);
		btnDelete.setText("Delete");

		Label lblSetBoundaries = new Label(this, SWT.NONE);
		FormData fd_lblSetBoundaries = new FormData();
		fd_lblSetBoundaries.right = new FormAttachment(0, 543);
		fd_lblSetBoundaries.top = new FormAttachment(0, 91);
		fd_lblSetBoundaries.left = new FormAttachment(0, 391);
		lblSetBoundaries.setLayoutData(fd_lblSetBoundaries);
		lblSetBoundaries.setText("Set Boundaries");

		Label lblLowValue = new Label(this, SWT.NONE);
		FormData fd_lblLowValue = new FormData();
		fd_lblLowValue.right = new FormAttachment(0, 471);
		fd_lblLowValue.top = new FormAttachment(0, 122);
		fd_lblLowValue.left = new FormAttachment(0, 390);
		lblLowValue.setLayoutData(fd_lblLowValue);
		lblLowValue.setText("Low Value");

		Label lblHighValue = new Label(this, SWT.NONE);
		FormData fd_lblHighValue = new FormData();
		fd_lblHighValue.right = new FormAttachment(0, 594);
		fd_lblHighValue.top = new FormAttachment(0, 122);
		fd_lblHighValue.left = new FormAttachment(0, 501);
		lblHighValue.setLayoutData(fd_lblHighValue);
		lblHighValue.setText("High Value");

		Spinner spinner = new Spinner(this, SWT.BORDER);
		FormData fd_spinner = new FormData();
		fd_spinner.bottom = new FormAttachment(0, 192);
		fd_spinner.right = new FormAttachment(0, 473);
		fd_spinner.top = new FormAttachment(0, 159);
		fd_spinner.left = new FormAttachment(0, 391);
		spinner.setLayoutData(fd_spinner);

		Spinner spinner_1 = new Spinner(this, SWT.BORDER);
		FormData fd_spinner_1 = new FormData();
		fd_spinner_1.top = new FormAttachment(0, 159);
		fd_spinner_1.left = new FormAttachment(0, 501);
		spinner_1.setLayoutData(fd_spinner_1);

		Label label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		FormData fd_label = new FormData();
		fd_label.bottom = new FormAttachment(0, 297);
		fd_label.right = new FormAttachment(0, 1014);
		fd_label.top = new FormAttachment(0, 284);
		fd_label.left = new FormAttachment(0, 10);
		label.setLayoutData(fd_label);

		Button btnRunAnalysis = new Button(this, SWT.NONE);
		FormData fd_btnRunAnalysis = new FormData();
		fd_btnRunAnalysis.right = new FormAttachment(0, 123);
		fd_btnRunAnalysis.top = new FormAttachment(0, 303);
		fd_btnRunAnalysis.left = new FormAttachment(0, 10);
		btnRunAnalysis.setLayoutData(fd_btnRunAnalysis);
		btnRunAnalysis.setText("Run Analysis");

		ListViewer listViewer = new ListViewer(this, SWT.BORDER | SWT.V_SCROLL);
		List list = listViewer.getList();
		FormData fd_list = new FormData();
		fd_list.bottom = new FormAttachment(0, 461);
		fd_list.right = new FormAttachment(0, 544);
		fd_list.top = new FormAttachment(0, 345);
		fd_list.left = new FormAttachment(0, 10);
		list.setLayoutData(fd_list);

		Scale scale = new Scale(this, SWT.NONE);
		FormData fd_scale = new FormData();
		fd_scale.top = new FormAttachment(0, 198);
		fd_scale.left = new FormAttachment(0, 360);
		scale.setLayoutData(fd_scale);

		Button btnDisplayData = new Button(this, SWT.NONE);
		FormData fd_btnDisplayData = new FormData();
		fd_btnDisplayData.right = new FormAttachment(0, 123);
		fd_btnDisplayData.top = new FormAttachment(0, 467);
		fd_btnDisplayData.left = new FormAttachment(0, 10);
		btnDisplayData.setLayoutData(fd_btnDisplayData);
		btnDisplayData.setText("Display Data");

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 508, 64, 64);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(100, -6);
		fd_composite.right = new FormAttachment(lblSetBoundaries, 0, SWT.RIGHT);
		fd_composite.top = new FormAttachment(0, 508);
		fd_composite.left = new FormAttachment(0, 10);
		composite.setLayoutData(fd_composite);
		TableColumnLayout tcl_composite = new TableColumnLayout();
		composite.setLayout(tcl_composite);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnColumn = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tblclmnColumn, new ColumnPixelData(122, true, true));
		tblclmnColumn.setText("Column1");

		TableColumn tblclmnColumn_1 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tblclmnColumn_1, new ColumnPixelData(135, true, true));
		tblclmnColumn_1.setText("Column 2");

		TableColumn tblclmnColumn_2 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tblclmnColumn_2, new ColumnPixelData(127, true, true));
		tblclmnColumn_2.setText("Column 3");

		TableColumn tblclmnColumn_3 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tblclmnColumn_3, new ColumnPixelData(150, true, true));
		tblclmnColumn_3.setText("Column 4");

		Label lblErrors = new Label(this, SWT.NONE);
		FormData fd_lblErrors = new FormData();
		fd_lblErrors.top = new FormAttachment(lblLoadFile, 0, SWT.TOP);
		lblErrors.setLayoutData(fd_lblErrors);
		lblErrors.setText("Errors");

		ListViewer listViewer_1 = new ListViewer(this, SWT.BORDER | SWT.V_SCROLL);
		List list_1 = listViewer_1.getList();
		fd_lblErrors.left = new FormAttachment(list_1, 0, SWT.LEFT);
		FormData fd_list_1 = new FormData();
		fd_list_1.left = new FormAttachment(scale, 83);
		fd_list_1.right = new FormAttachment(100, -10);
		fd_list_1.top = new FormAttachment(text, 0, SWT.TOP);
		fd_list_1.bottom = new FormAttachment(label, -6);
		list_1.setLayoutData(fd_list_1);

		Button btnDisplayGraph = new Button(this, SWT.NONE);
		FormData fd_btnDisplayGraph = new FormData();
		fd_btnDisplayGraph.top = new FormAttachment(btnRunAnalysis, 0, SWT.TOP);
		btnDisplayGraph.setLayoutData(fd_btnDisplayGraph);
		btnDisplayGraph.setText("Display Graph");

		StyledText styledText = new StyledText(this, SWT.BORDER);
		fd_btnDisplayGraph.left = new FormAttachment(styledText, 0, SWT.LEFT);
		FormData fd_styledText = new FormData();
		fd_styledText.bottom = new FormAttachment(100, -224);
		fd_styledText.top = new FormAttachment(btnDisplayGraph, 7);
		fd_styledText.left = new FormAttachment(list, 25);
		fd_styledText.right = new FormAttachment(100, -29);
		styledText.setLayoutData(fd_styledText);

		Button btnCreateReport = new Button(this, SWT.NONE);
		FormData fd_btnCreateReport = new FormData();
		fd_btnCreateReport.top = new FormAttachment(styledText, 45);
		fd_btnCreateReport.bottom = new FormAttachment(100, -30);
		fd_btnCreateReport.left = new FormAttachment(composite, 100);
		fd_btnCreateReport.right = new FormAttachment(100, -61);
		btnCreateReport.setLayoutData(fd_btnCreateReport);
		btnCreateReport.setText("Create Report");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}