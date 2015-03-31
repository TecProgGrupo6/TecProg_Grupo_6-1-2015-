// Import the packages for using the classes in them into the program.

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;

/**
 * A public class
 */
public class ListSearchBooks extends JInternalFrame{
	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	// For creating the North Panel.
	private JPanel northPanel = new JPanel();
	// For creating the Center Panel.
	private JPanel centerPanel = new JPanel();
	// For creating the label.
	private JLabel label = new JLabel("THE LIST FOR THE SEARCHED BOOKS");
	// For creating the button.
	private JButton printButton;
	// For creating the table.
	private JTable table;
	// For creating the TableColumn.
	private TableColumn column = null;
	// For creating the JScrollPane.
	private JScrollPane scrollPane;

	// For creating an object for the ResultSetTableModel class.
	private ResultSetTableModel tableModel;

	// Constructor of listSearchBooks.
	public ListSearchBooks(String query){
		// For setting the title for the internal frame.
		super("Searched Books", false, true, false, true);
		// For setting the icon.
		setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("images/List16.gif")));
		// For getting the graphical user interface components display area.
		Container cp = getContentPane();

		/***********************************************************************
		 * for setting the required information for the ResultSetTableModel
		 * class*
		 ************************************************************************/
		final String JDBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
		final String DATABASE_URL = "jdbc:odbc:JLibrary";
		final String DEFAULT_QUERY = query;

		// For bassing the required information to the ResultSetTableModel.
		// Object.
		try{
			tableModel = new ResultSetTableModel(JDBC_DRIVER, DATABASE_URL, DEFAULT_QUERY);
			// For setting the Query.
			try{
				tableModel.setQuery(DEFAULT_QUERY);
			}catch (SQLException sqlException){
				
			}
		}catch (ClassNotFoundException classNotFound){
			
		}catch (SQLException sqlException){
			
		}

		// For setting the table with the information.
		table = new JTable(tableModel);
		// For setting the size for the table.
		table.setPreferredScrollableViewportSize(new Dimension(990, 200));
		// For setting the font.
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		// For setting the scrollpane to the table.
		scrollPane = new JScrollPane(table);

		// For setting the size for the table columns.
		for ( int i = 0 ; i < 13 ; i++ ){
			column = table.getColumnModel().getColumn(i);
			if ( i == 0 ){ // BookID.
				column.setPreferredWidth(20);
			}
			if ( i == 1 ){ // Subject.
				column.setPreferredWidth(100);
			}
			if ( i == 2 ){ // Title.
				column.setPreferredWidth(150);
			}
			if ( i == 3 ){ // Auther.
				column.setPreferredWidth(50);
			}
			if ( i == 4 ){ // Publisher.
				column.setPreferredWidth(70);
			}
			if ( i == 5 ){ // Copyright.
				column.setPreferredWidth(40);
			}
			if ( i == 6 ){ // Edition.
				column.setPreferredWidth(40);
			}
			if ( i == 7 ){ // Pages.
				column.setPreferredWidth(40);
			}
			if ( i == 8 ){ // NumberOfBooks.
				column.setPreferredWidth(80);
			}
			if ( i == 9 ){ // ISBN.
				column.setPreferredWidth(70);
			}
			if ( i == 10 ){ // Library.
				column.setPreferredWidth(30);
			}
			if ( i == 11 ){ // Availble.
				column.setPreferredWidth(30);
			}
			if ( i == 12 ){ // ShelfNo.
				column.setPreferredWidth(30);
			}
		}
		// For setting the font to the label.
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		// For setting the layout to the panel.
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		// For adding the label to the panel.
		northPanel.add(label);
		// For adding the panel to the container.
		cp.add("North", northPanel);

		// For setting the layout to the panel.
		centerPanel.setLayout(new BorderLayout());
		// For creating an image for the button.
		ImageIcon printIcon = new ImageIcon(ClassLoader.getSystemResource("images/Print16.gif"));
		// For adding the button to the panel.
		printButton = new JButton("print the books", printIcon);
		// For setting the tip text.
		printButton.setToolTipText("Print");
		// For setting the font to the button.
		printButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		// For adding the button to the panel.
		centerPanel.add(printButton, BorderLayout.NORTH);
		// For adding the scrollpane to the panel.
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		// For setting the border to the panel.
		centerPanel.setBorder(BorderFactory.createTitledBorder("Books:"));
		// For adding the panel to the container.
		cp.add("Center", centerPanel);

		// For adding the actionListener to the button.
		printButton.addActionListener(new ActionListener(){
			public void actionPerformed( ActionEvent ae ){
				Thread runner = new Thread(){
					public void run(){
						try{
							PrinterJob prnJob = PrinterJob.getPrinterJob();
							prnJob.setPrintable(new PrintingBooks(DEFAULT_QUERY));
							if ( !prnJob.printDialog() ){
								return;
							}else{
								
							}
							setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							prnJob.print();
							setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						}catch (PrinterException ex){
							System.out.println("Printing error: " + ex.toString());
						}
					}
				};
				runner.start();
			}
		});
		// For setting the visible to true.
		setVisible(true);
		// To show the frame.
		pack();
	}
}