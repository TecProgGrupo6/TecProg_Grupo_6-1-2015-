//import the packages for using the classes in them into the program

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A public class
 */
public class ReturnBooks extends JInternalFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Log system from ReturnBooks class
	private final static Logger LOGGER = Logger.getLogger( ReturnBooks.class.getName() );

	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	// For creating the North Panel
	private JPanel northPanel = new JPanel();

	// For creating the label
	private JLabel title1 = new JLabel( "BOOK INFORMATION" );

	// For creating the Center Panel
	private JPanel centerPanel = new JPanel();

	// For creating an Internal Panel in the center panel
	private JPanel informationPanel = new JPanel();

	// For creating an array of JLabel
	private JLabel[] informationLabel = new JLabel[2];

	// For creating an array of String
	private String[] informationString = { " Write the Book ID:" , " Write the Member ID:" };

	// For creating an array of JTextField
	private JTextField[] informationTextField = new JTextField[2];

	// For creating an array of string to store the data
	private String[] data;
	private JLabel lblFinePerDay = new JLabel( "Fine/Day" );
	private JTextField txtFinePerDay = new JTextField();
	private JLabel lblTotalFineAmt = new JLabel( "Total fine amount" );
	private JTextField txtTotalFineAmt = new JTextField();

	// For creating an Internal Panel in the center panel
	private JPanel returnButtonPanel = new JPanel();

	// For creating the button
	private JButton returnButton = new JButton( "Return" );

	// For creating the panel
	private JPanel southPanel = new JPanel();

	// For creating the button
	private JButton cancelButton = new JButton( "Cancel" );

	// For creating an object
	private Books book;
	private Members member;
	private Borrow borrow;

	// For checking the information from the text field
	public boolean isCorrect (){

		this.setData(new String[2]);

		for ( int i = 0 ; i < this.informationLabel.length ; i++ ){

			if ( !this.getInformationTextField()[i].getText().equals( "" ) ){

				this.getData()[i] = this.getInformationTextField()[i].getText();

			}else{

				return false;

			}
		}
		return true;
	}

	// For setting the array of JTextField to null
	public void clearTextField (){

		for ( int i = 0 ; i < this.getInformationTextField().length ; i++ ){

			if ( i != 2 ){

				this.getInformationTextField()[i].setText( null );

			}else{
				// No action
			}
			this.getTxtFinePerDay().setText( null );
			this.getTxtTotalFineAmt().setText( null );
		}
	}

	void addStringsAndSettingFonts(){
	
		/***********************************************************************
		 * For adding the strings to the labels, for setting the font * and
		 * adding these labels to the panel. * finally adding the panel to the
		 * container *
		 ***********************************************************************/
	
		for ( int i = 0 ; i < this.informationLabel.length ; i++ ){
	
			this.informationPanel.add( this.informationLabel[i] = new JLabel( this.informationString[i] ) );
			this.informationLabel[i].setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
			this.informationPanel.add( this.getInformationTextField()[i] = new JTextField() );
			this.getInformationTextField()[i].setFont( new Font( "Tahoma" , Font.PLAIN , 11 ) );
	
		}
	
		this.informationPanel.add( this.lblFinePerDay );
		this.informationPanel.add( this.getTxtFinePerDay() );
		this.informationPanel.add( this.lblTotalFineAmt );
		this.informationPanel.add( this.getTxtTotalFineAmt() );
		this.getTxtTotalFineAmt().setEditable( false );
		this.getTxtFinePerDay().addKeyListener( new keyListener() );
		this.centerPanel.add( "Center" , this.informationPanel );
	
	}
	
	// Constructor of returnBooks
	public ReturnBooks (){

		// For setting the title for the internal frame
		super( "Return books" , false , true , false , true );
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("ReturnBooks was used.");

		// For setting the icon
		setFrameIcon( new ImageIcon( ClassLoader.getSystemResource( "images/Import16.gif" ) ) );

		// For getting the graphical user interface components display area
		Container cp = getContentPane();

		// For setting the layout
		this.northPanel.setLayout( new FlowLayout( FlowLayout.CENTER ) );

		// For setting the font
		this.title1.setFont( new Font( "Tahoma" , Font.BOLD , 14 ) );

		// For adding the label
		this.northPanel.add( this.title1 );

		// For adding the north panel to the container
		cp.add( "North" , this.northPanel );

		// For setting the layout
		this.centerPanel.setLayout( new BorderLayout() );

		// For setting the layout for the internal panel
		this.informationPanel.setLayout( new GridLayout( 4 , 2 , 1 , 1 ) );

		addStringsAndSettingFonts();

		// For setting the layout
		this.returnButtonPanel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );

		// For adding the button
		this.returnButtonPanel.add( this.returnButton );

		// For setting the font to the button
		this.returnButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );

		// For adding the internal panel to the panel
		this.centerPanel.add( "South" , this.returnButtonPanel );

		// For setting the border
		this.centerPanel.setBorder( BorderFactory.createTitledBorder( "Return a book:" ) );

		// For adding the center panel to the container
		cp.add( "Center" , this.centerPanel );

		// For setting the layout
		this.southPanel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );

		// For adding the button
		this.southPanel.add( this.cancelButton );

		// For setting the font to the button
		this.cancelButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );

		// For setting the border
		this.southPanel.setBorder( BorderFactory.createEtchedBorder() );

		// For adding the south panel to the container
		cp.add( "South" , this.southPanel );

		/***********************************************************************
		 * For adding the action listener to the button,first the text will be *
		 * taken from the JTextField and make the connection for database, *
		 * after that update the table in the database with the new value *
		 ***********************************************************************/

		this.returnButton.addActionListener( this );

		// For adding the action listener for the button to dispose the frame
		this.cancelButton.addActionListener( this );

		// For setting the visible to true
		setVisible( true );

		// Show the internal frame
		pack();
	}

	@Override
	public void actionPerformed ( ActionEvent ae ){

		if ( ae.getSource() == this.returnButton ){

			// For checking if there is a missing information
			if ( isCorrect() ){
				Thread runner = new Thread(){

					@Override
					public void run (){

						ReturnBooks.this.setBook(new Books());
						ReturnBooks.this.setMember(new Members());
						ReturnBooks.this.setBorrow(new Borrow());
						getBook().connection( "SELECT * FROM Books WHERE BookID = " + ReturnBooks.this.getData()[0] );
						getMember().connection( "SELECT * FROM Members WHERE MemberID = " + getData()[1] );
						int numberOfAvailbleBooks = getBook().getNumberOfAvailbleBooks();
						int numberOfBorrowedBooks = getBook().getNumberOfBorrowedBooks() - 1;
						int numberOfBooks = getMember().getNumberOfBooks();

						// For checking if there is no same information in the
						// database
						if ( numberOfAvailbleBooks == 0 && numberOfBooks > 0 ){

							numberOfAvailbleBooks += 1;
							numberOfBooks -= 1;

							getBook().update( "UPDATE Books SET NumberOfAvailbleBooks =" + numberOfAvailbleBooks + ",NumberOfBorrowedBooks ="
									+ numberOfBorrowedBooks + ",Availble = true WHERE BookID =" + getData()[0] );
							getMember().update( "UPDATE Members SET NumberOfBooks =" + numberOfBooks + " WHERE MemberID =" + getData()[1] );
							getBorrow().update( "DELETE FROM Borrow WHERE BookID =" + getData()[0] + " AND MemberID =" + getData()[1] );

							// For setting the array of JTextField to null
							JOptionPane.showMessageDialog( null , "The book is Successfully returned" , "Success" ,
									JOptionPane.INFORMATION_MESSAGE );
							clearTextField();

						}else if ( numberOfAvailbleBooks > 0 && numberOfBooks > 0 ){

							numberOfAvailbleBooks += 1;
							numberOfBooks -= 1;

							getBook().update( "UPDATE Books SET NumberOfAvailbleBooks =" + numberOfAvailbleBooks + ",NumberOfBorrowedBooks ="
									+ numberOfBorrowedBooks + " WHERE BookID =" + getData()[0] );
							getMember().update( "UPDATE Members SET NumberOfBooks =" + numberOfBooks + " WHERE MemberID =" + getData()[1] );
							getBorrow().update( "DELETE FROM Borrow WHERE BookID =" + getData()[0] + " AND MemberID =" + getData()[1] );

							// For setting the array of JTextField to null
							JOptionPane.showMessageDialog( null , "The book is Successfully Returned" , "Success" ,
									JOptionPane.INFORMATION_MESSAGE );
							clearTextField();

						}else{
							JOptionPane.showMessageDialog( null , "The book is not borrowed" , "Warning" , JOptionPane.WARNING_MESSAGE );
						}
					}
				};
				runner.start();
			}
			// If there is a missing data, then display Message Dialog
			else{
				JOptionPane.showMessageDialog( null , "Please, complete the information" , "Warning" , JOptionPane.WARNING_MESSAGE );
			}
		}
		if ( ae.getSource() == this.cancelButton ){
			dispose();
		}else{
			// No action
		}
	}

	public Books getBook(){
		return this.book;
	}

	public void setBook( Books book ){
		this.book = book;
	}

	public Members getMember(){
		return this.member;
	}

	public void setMember( Members member ){
		this.member = member;
	}

	public Borrow getBorrow(){
		return this.borrow;
	}

	public void setBorrow( Borrow borrow ){
		this.borrow = borrow;
	}

	public String[] getData(){
		return this.data;
	}

	public void setData( String[] data ){
		this.data = data;
	}

	public JTextField getTxtFinePerDay(){
		return this.txtFinePerDay;
	}

	public void setTxtFinePerDay( JTextField txtFinePerDay ){
		this.txtFinePerDay = txtFinePerDay;
	}

	public JTextField[] getInformationTextField(){
		return this.informationTextField;
	}

	public void setInformationTextField( JTextField[] informationTextField ){
		this.informationTextField = informationTextField;
	}

	public JTextField getTxtTotalFineAmt(){
		return this.txtTotalFineAmt;
	}

	public void setTxtTotalFineAmt( JTextField txtTotalFineAmt ){
		this.txtTotalFineAmt = txtTotalFineAmt;
	}

	class keyListener extends KeyAdapter{

		@Override
		public void keyPressed ( KeyEvent k ){

			java.sql.Date da = null;

			if ( k.getKeyCode() == KeyEvent.VK_ENTER ){

				try{

					int fineamt = Integer.parseInt( ReturnBooks.this.getTxtFinePerDay().getText() );
					Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
					Connection con = DriverManager.getConnection( "jdbc:odbc:JLibrary" );
					Statement st = con.createStatement();
					int bookid = Integer.parseInt( ReturnBooks.this.getInformationTextField()[0].getText() );
					int memid = Integer.parseInt( getInformationTextField()[1].getText() );

					try{

						String sql = "SELECT DayOfReturn from Borrow where MemberID=" + memid + " and BookID=" + bookid;
						ResultSet rs = st.executeQuery( sql );

						if ( rs.next() ){

							da = rs.getDate( 1 );
							java.util.Date today = new java.util.Date();
							/*
							 * java.util.Date retdate=new
							 * java.util.Date(da.getYear
							 * (),da.getMonth(),da.getDate());
							 * JOptionPane.showMessageDialog(null, "today=" +
							 * today + "\nRet date=" + retdate);
							 */

							System.out.println( today.after( da ) );

							if ( today.after( da ) ){

								long finedays = today.getTime() - da.getTime();
								final int MINUTES = 60;
								final int SECONDS = 60;
								final int HOURS = 24;
								int days = (int) ( finedays / ( 1000 * SECONDS * MINUTES * HOURS ) );
								System.out.println( days );
								ReturnBooks.this.getTxtTotalFineAmt().setText( String.valueOf( fineamt * days ) );

							}else{
								getTxtTotalFineAmt().setText( "0" );
							}
						}else{
							JOptionPane.showMessageDialog( null , "Member ID entered not found on databse" );
						}

					}catch ( Exception ex1 ){
						JOptionPane.showMessageDialog( null , "Error, Cannot retrieve date value from table" + ex1.toString() );
					}

				}catch ( Exception ex ){
					JOptionPane.showMessageDialog( null , "Error, cannot connect to database" + ex.toString() );
				}
			}
		}
	}// Inner class closed
}// Class closed
