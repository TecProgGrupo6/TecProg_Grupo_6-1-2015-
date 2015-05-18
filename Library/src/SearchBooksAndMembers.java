// Import the packages for using the classes in them into the program.

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A public class
 */
public class SearchBooksAndMembers extends JInternalFrame{
	
	// Log system from SearchBooksAndMembers class
	private final static Logger LOGGER = Logger.getLogger( SearchBooksAndMembers.class.getName() );

	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	// For creating the North Panel.
	private JPanel northPanel = new JPanel();

	// For creating the label.
	private JLabel title = new JLabel( "Search for Books and Members" );

	// For creating the center.
	private JPanel center = new JPanel();

	// For creating the Center Panel.
	private JPanel centerBooksPanel = new JPanel();

	// For creating an Internal Panel in the center panel.
	private JPanel searchBooksPanel = new JPanel();

	// For creating an Internal Panel in the center panel.
	private JPanel searchBooksButtonPanel = new JPanel();

	// For creating the table.
	private JLabel searchBooksLabel = new JLabel( " Search by: " );

	// For creating JComboBox.
	private JComboBox searchBooksTypes;

	// For creating String[].
	private String[] booksTypes = { "BookID" , "Subject" , "Title" , "Author" , "Publisher" , "ISBN" };

	// For creating the label.
	private JLabel booksKey = new JLabel( " Write the Keyword: " );

	// For cearting the text field.
	private JTextField booksKeyTextField = new JTextField();

	// For creating the button.
	private JButton searchBooksButton = new JButton( "Search" );

	// For creating the Center Panel.
	private JPanel centerMembersPanel = new JPanel();

	// For creating an Internal Panel in the center panel.
	private JPanel searchMembersPanel = new JPanel();

	// For creating an Internal Panel in the center panel.
	private JPanel searchMembersButtonPanel = new JPanel();

	// For creating the table.
	private JLabel searchMembersLabel = new JLabel( " Search by: " );

	// For creating JComboBox.
	private JComboBox searchMembersTypes;

	// For creating String[].
	private String[] membersTypes = { "MemberID" , "Name" , "E-Mail" , "Major" };

	// For creating the label.
	private JLabel membersKey = new JLabel( " Write the Keyword: " );

	// For cearting the text field.
	private JTextField membersKeyTextField = new JTextField();

	// For creating the button.
	private JButton searchMembersButton = new JButton( "Search" );

	// For creating the south panel.
	private JPanel southPanel = new JPanel();

	// For creating the button.
	private JButton cancelButton = new JButton( "Cancel" );

	// For creating an array of string to store the data.
	private String[] booksData;
	private String[] membersData;

	// Create objects from another classes for using them in the ActionListener.
	private ListSearchBooks listBooks;
	private ListSearchMembers listMembers;
	private Books book;
	private Members member;

	// For checking the information from the text field.
	public boolean isBooksDataCorrect (){

		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Checked information from book text field");
		
		booksData = new String[2];
		booksData[0] = searchBooksTypes.getSelectedItem().toString();

		for ( int i = 1 ; i < booksData.length ; i++ ){
			if ( !booksKeyTextField.getText().equals( "" ) ){

				if ( searchBooksTypes.getSelectedItem().toString().equals( "BookID" ) ){

					booksData[i] = booksKeyTextField.getText();

				}else{
					booksData[i] = "'%" + booksKeyTextField.getText() + "%'";
				}
			}else{
				return false;
			}
		}
		return true;
	}

	// For checking the information from the text field.
	public boolean isMembersDataCorrect (){

		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Checked information from member text field");
		
		membersData = new String[2];
		membersData[0] = searchMembersTypes.getSelectedItem().toString();

		for ( int i = 1 ; i < membersData.length ; i++ ){
			if ( !membersKeyTextField.getText().equals( "" ) ){
				if ( searchMembersTypes.getSelectedItem().toString().equals( "MemberID" ) ){
					membersData[i] = membersKeyTextField.getText();
				}else
					membersData[i] = "'%" + membersKeyTextField.getText() + "%'";
			}else
				return false;
		}
		return true;
	}
	
	void settingTheFonts(){
	
		/**
		 * for setting the font to the lables & buttons
		 */
		searchBooksLabel.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
		searchBooksTypes.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
		booksKey.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
		booksKeyTextField.setFont( new Font( "Tahoma" , Font.PLAIN , 11 ) );
		searchBooksButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
		cancelButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
		searchMembersLabel.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
		searchMembersTypes.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
		membersKey.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
		membersKeyTextField.setFont( new Font( "Tahoma" , Font.PLAIN , 11 ) );
		searchMembersButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
		cancelButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
		
	}

	// Constructor of searchBooksAndMembers.
	public SearchBooksAndMembers (){

		// For setting the title for the internal frame.
		super( "Search" , false , true , false , true );
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("SearchBooksAndMembers was created.");

		// For setting the icon.
		setFrameIcon( new ImageIcon( ClassLoader.getSystemResource( "images/Find16.gif" ) ) );

		// For getting the graphical user interface components display area.
		Container cp = getContentPane();

		// For setting the layout.
		northPanel.setLayout( new FlowLayout( FlowLayout.CENTER ) );

		// For setting the font.
		title.setFont( new Font( "Tahoma" , Font.BOLD , 14 ) );

		// For adding the label.
		northPanel.add( title );

		// For adding the north panel to the container.
		cp.add( "North" , northPanel );

		// For setting the layout.
		center.setLayout( new BorderLayout() );

		// For setting the layout.
		centerBooksPanel.setLayout( new BorderLayout() );

		// For setting the layout.
		searchBooksPanel.setLayout( new GridLayout( 2 , 2 , 1 , 1 ) );

		// For adding the label.
		searchBooksPanel.add( searchBooksLabel );

		// For adding the JComboBos[].
		searchBooksPanel.add( searchBooksTypes = new JComboBox( booksTypes ) );

		// For adding the label.
		searchBooksPanel.add( booksKey );

		// For adding the text field.
		searchBooksPanel.add( booksKeyTextField );

		// For adding the internal panel to the panel.
		centerBooksPanel.add( "North" , searchBooksPanel );

		// For setting the layout.
		searchBooksButtonPanel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );

		// For adding the button.
		searchBooksButtonPanel.add( searchBooksButton );

		// For adding the internal panel to the center panel.
		centerBooksPanel.add( "South" , searchBooksButtonPanel );

		// For setting the border.
		centerBooksPanel.setBorder( BorderFactory.createTitledBorder( "Search for a books:" ) );

		// For adding center panel to the center.
		center.add( "West" , centerBooksPanel );

		// For setting the layout.
		centerMembersPanel.setLayout( new BorderLayout() );

		// For setting the layout.
		searchMembersPanel.setLayout( new GridLayout( 2 , 2 , 1 , 1 ) );

		// For adding the label.
		searchMembersPanel.add( searchMembersLabel );

		// For adding the JComboBos[].
		searchMembersPanel.add( searchMembersTypes = new JComboBox( membersTypes ) );

		// For adding the label.
		searchMembersPanel.add( membersKey );

		// For adding the text field.
		searchMembersPanel.add( membersKeyTextField );

		// For adding the internal panel to the panel.
		centerMembersPanel.add( "North" , searchMembersPanel );

		// For setting the layout.
		searchMembersButtonPanel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );

		// For adding the button.
		searchMembersButtonPanel.add( searchMembersButton );

		// For adding the internal panel to the center panel.
		centerMembersPanel.add( "South" , searchMembersButtonPanel );

		// For setting the border.
		centerMembersPanel.setBorder( BorderFactory.createTitledBorder( "Search for a members:" ) );

		// For adding center panel to the center.
		center.add( "East" , centerMembersPanel );

		// For adding the center to the container.
		cp.add( "Center" , center );

		settingTheFonts();
		
		// For setting the layout.
		southPanel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );

		// For adding the button.
		southPanel.add( cancelButton );

		// For setting the border.
		southPanel.setBorder( BorderFactory.createEtchedBorder() );

		// For adding the south panel to the container.
		cp.add( "South" , southPanel );

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField and passing them to listSearchBooks object*
		 ***********************************************************************/
		searchBooksButton.addActionListener( new ActionListener(){

			public void actionPerformed ( ActionEvent ae ){

				// For checking if there is a missing information.
				if ( isBooksDataCorrect() ){
					book = new Books();
					String bookQuery = "SELECT BookID, Subject, Title, Author, Publisher,"
							+ "Copyright, Edition, Pages, NumberOfBooks,ISBN,Library,Availble,ShelfNo FROM Books" + " WHERE "
							+ booksData[0] + " LIKE " + booksData[1];

					book.connection( bookQuery );

					int bookID = book.getBookID();
					if ( bookID != 0 ){
						listBooks = new ListSearchBooks( bookQuery );
						getParent().add( listBooks );
						try{
							listBooks.setSelected( true );
						}catch ( java.beans.PropertyVetoException e ){
						}
						dispose();
					}else{
						JOptionPane.showMessageDialog( null , "No Match(es)" , "Error" , JOptionPane.ERROR_MESSAGE );
						booksKeyTextField.setText( null );
					}
				}else{
					JOptionPane.showMessageDialog( null , "Please, complete the information" , "Warning" , JOptionPane.WARNING_MESSAGE );
				}
			}
		} );

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField and passing them to listSearchBooks object*
		 ***********************************************************************/
		searchMembersButton.addActionListener( new ActionListener(){

			public void actionPerformed ( ActionEvent ae ){

				if ( isMembersDataCorrect() ){

					member = new Members();
					String memberQuery = "SELECT MemberID, ID, Name, EMail, Major, Expired" + " FROM Members WHERE " + membersData[0]
							+ " LIKE " + membersData[1];
					member.connection( memberQuery );
					int memberID = member.getMemberID();

					if ( memberID != 0 ){

						listMembers = new ListSearchMembers( memberQuery );
						getParent().add( listMembers );

						try{
							listMembers.setSelected( true );
						}catch ( java.beans.PropertyVetoException e ){
						}
						dispose();
					}else{
						JOptionPane.showMessageDialog( null , "No Match(es)" , "Error" , JOptionPane.ERROR_MESSAGE );
						membersKeyTextField.setText( null );
					}
				}else{
					JOptionPane.showMessageDialog( null , "Please, complete the information" , "Warning" , JOptionPane.WARNING_MESSAGE );
				}
			}
		} );

		// For adding the action listener for the button to dispose the frame.
		cancelButton.addActionListener( new ActionListener(){

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
