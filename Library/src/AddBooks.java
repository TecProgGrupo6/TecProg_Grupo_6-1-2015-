//import the packages for using the classes in them into the program

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A public class
 */
public class AddBooks extends JInternalFrame{

	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	// For creating the North Panel.

	private JPanel northPanel = new JPanel();
	// For creaing the North Label.
	private JLabel northLabel = new JLabel( "BOOK INFORMATION" );

	// For creating the Center Panel.
	private JPanel centerPanel = new JPanel();

	// For creating an Internal Panel in the center panel.
	private JPanel informationLabelPanel = new JPanel();

	// For creating an array of JLabel.
	private JLabel[] informationLabel = new JLabel[10];
	private JLabel lblShelfNo = new JLabel( " Shelf No" );
	private JTextField txtShelfNo = new JTextField();

	// For creating an array of String.
	private String[] informationString = { " The book subject: " , " The book title: " , " The name of the Author(s): " ,
			" The name of the Publisher: " , " Copyright for the book: " , " The edition number: " , " The number of Pages: " ,
			" ISBN for the book: " , " The number of copies: " , " The name of the Library: " };

	// For creating an Internal Panel in the center panel.
	private JPanel informationTextFieldPanel = new JPanel();

	// For creating an array of JTextField.
	private JTextField[] informationTextField = new JTextField[10];

	// For creating an Internal Panel in the center panel.
	private JPanel insertInformationButtonPanel = new JPanel();

	// For creating a button.
	private JButton insertInformationButton = new JButton( "Insert the Information" );

	// For creating South Panel.
	private JPanel southPanel = new JPanel();

	// For creating a button.
	private JButton OKButton = new JButton( "Exit" );

	// Create objects from another classes for using them in the ActionListener.
	private Books book;

	// For creating an array of string to store the data.
	private String[] data;

	// For setting availble option to true.
	private boolean availble = true;

	// For checking the information from the text field.
	double informationLabelLength = informationLabel.length;

	public boolean isCorrect (){

		data = new String[10];
		for ( int i = 0 ; i < informationLabelLength ; i++ ){

			if ( !informationTextField[i].getText().equals( "" ) ){

				data[i] = informationTextField[i].getText();

			}else{
				return false;
			}
		}
		return true;
	}

	// For setting the array of JTextField to empty.
	double informationTextFieldLength = informationTextField.length;

	public void clearTextField (){

		for ( int i = 0 ; i < informationTextFieldLength ; i++ ){

			informationTextField[i].setText( null );
		}
		txtShelfNo.setText( null );
	}
	
	
	void settingTheLayoutForThePanel( Container cp){
	
		/***********************************************************************
		 * for setting the layout for the panel,setting the font for the button*
		 * and adding the button to the panel. * finally adding the panel to the
		 * container *
		 ***********************************************************************/
		insertInformationButtonPanel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );
		insertInformationButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
		insertInformationButtonPanel.add( insertInformationButton );
		centerPanel.add( "South" , insertInformationButtonPanel );
		cp.add( "Center" , centerPanel );
	
		/***********************************************************************
		 * for setting the layout for the panel,setting the font for the button*
		 * adding the button to the panel & setting the border. * finally adding
		 * the panel to the container *
		 ***********************************************************************/
		southPanel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );
		OKButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
		southPanel.add( OKButton );
		southPanel.setBorder( BorderFactory.createEtchedBorder() );
		cp.add( "South" , southPanel );
	
	}

	// Constructor of addBooks.
	public AddBooks (){

		// For setting the title for the internal frame.
		super( "Add Books" , false , true , false , true );

		// For setting the icon.
		setFrameIcon( new ImageIcon( ClassLoader.getSystemResource( "images/Add16.gif" ) ) );

		// For getting the graphical user interface components display area.
		Container cp = getContentPane();

		// For setting the layout.
		northPanel.setLayout( new FlowLayout( FlowLayout.CENTER ) );

		// For setting the font for the North Panel.
		northLabel.setFont( new Font( "Tahoma" , Font.BOLD , 14 ) );

		// For adding the label in the North Panel.
		northPanel.add( northLabel );

		// For adding the north panel to the container.
		cp.add( "North" , northPanel );

		// For setting the layout.
		centerPanel.setLayout( new BorderLayout() );

		// For setting the border to the panel.
		centerPanel.setBorder( BorderFactory.createTitledBorder( "Add a new book:" ) );

		// For setting the layout.
		informationLabelPanel.setLayout( new GridLayout( 11 , 1 , 1 , 1 ) );

		/***********************************************************************
		 * for adding the strings to the labels, for setting the font * and
		 * adding these labels to the panel. * finally adding the panel to the
		 * container *
		 ***********************************************************************/
		for ( int i = 0 ; i < informationLabel.length ; i++ ){

			informationLabelPanel.add( informationLabel[i] = new JLabel( informationString[i] ) );
			informationLabel[i].setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );

		}
		centerPanel.add( "West" , informationLabelPanel );

		// For setting the layout.
		informationTextFieldPanel.setLayout( new GridLayout( 11 , 1 , 1 , 1 ) );
		/***********************************************************************
		 * for adding the strings to the labels, for setting the font * and
		 * adding these labels to the panel. * finally adding the panel to the
		 * container *
		 ***********************************************************************/
		for ( int i = 0 ; i < informationTextField.length ; i++ ){
			informationTextFieldPanel.add( informationTextField[i] = new JTextField( 25 ) );
			informationTextField[i].setFont( new Font( "Tahoma" , Font.PLAIN , 11 ) );
		}

		lblShelfNo.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
		informationLabelPanel.add( lblShelfNo );
		txtShelfNo.setFont( new Font( "Tahoma" , Font.PLAIN , 11 ) );
		informationTextFieldPanel.add( txtShelfNo );
		centerPanel.add( "East" , informationTextFieldPanel );

		settingTheLayoutForThePanel(cp);

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField[] and make the connection for database, *
		 * after that update the table in the database with the new value *
		 ***********************************************************************/
		insertInformationButton.addActionListener( new ActionListener(){

			public void actionPerformed ( ActionEvent ae ){

				// For checking if there is a missing information.
				if ( isCorrect() ){
					Thread runner = new Thread(){

						public void run (){

							book = new Books();
							// For checking if there is no double information in
							// the database.
							book.connection( "SELECT BookID FROM Books WHERE ISBN = '" + data[7] + "'" );
							String ISBN = book.getISBN();
							if ( !data[7].equalsIgnoreCase( ISBN ) ){

								try{
									String sql = "INSERT INTO Books (Subject,Title,Author,Publisher,Copyright,"
											+ "Edition,Pages,ISBN,NumberOfBooks,NumberOfAvailbleBooks,Library,Availble,ShelfNo) VALUES "
											+ " (?,?,?,?,?,?,?,?,?,?,?,?,?)";
									Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
									Connection con = DriverManager.getConnection( "jdbc:odbc:JLibrary" );
									PreparedStatement ps = con.prepareStatement( sql );
									ps.setString( 1 , data[0] );
									ps.setString( 2 , data[1] );
									ps.setString( 3 , data[2] );
									ps.setString( 4 , data[3] );
									ps.setInt( 5 , Integer.parseInt( data[4] ) );
									ps.setInt( 6 , Integer.parseInt( data[5] ) );
									ps.setInt( 7 , Integer.parseInt( data[6] ) );
									ps.setString( 8 , data[7] );
									ps.setInt( 9 , Integer.parseInt( data[8] ) );
									ps.setInt( 10 , Integer.parseInt( data[8] ) );
									ps.setString( 11 , data[9] );
									ps.setBoolean( 12 , availble );
									ps.setInt( 13 , Integer.parseInt( txtShelfNo.getText() ) );
									ps.executeUpdate();

								}catch ( Exception ex ){

									JOptionPane.showMessageDialog( null , ex.toString() );

								}

								/*
								 * String sql=
								 * "INSERT INTO Books (Subject,Title,Author,Publisher,Copyright,"
								 * +
								 * "Edition,Pages,ISBN,NumberOfBooks,NumberOfAvailbleBooks,Library,Availble,ShelfNo) VALUES ('"
								 * + data[0] + "','" + data[1] + "','" + data[2]
								 * + "','" + data[3] + "', " + data[4] + ", " +
								 * data[5] + ", " + data[6] + ", '" + data[7] +
								 * "', " + data[8] + "," + data[8] + ",'" +
								 * data[9] + "', " + availble + ", '" +
								 * txtShelfNo.getText() + "')";
								 * book.update(sql);
								 */
								// JOptionPane.showMessageDialog(null, sql);
								// For setting the array of JTextField to empty.
								clearTextField();
							}else{

								JOptionPane.showMessageDialog( null , "The book is in the library" , "Error" , JOptionPane.ERROR_MESSAGE );

							}
						}
					};
					runner.start();
				}
				// If there is a missing data, then display Message Dialog.
				else{
					JOptionPane.showMessageDialog( null , "Please, complete the information" , "Warning" , JOptionPane.WARNING_MESSAGE );
				}
			}
		} );

		// For adding the action listener for the button to dispose the frame.
		OKButton.addActionListener( new ActionListener(){

			public void actionPerformed ( ActionEvent ae ){

				dispose();
			}
		} );

		// For setting the visible to true.
		setVisible( true );

		// Show the internal frame.
		pack();
	}
}
