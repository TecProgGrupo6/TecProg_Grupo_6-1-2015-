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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Log system from SearchBooksAndMembers class
	private final static Logger LOGGER = Logger.getLogger( SearchBooksAndMembers.class.getName() );

	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	// For creating the North Panel.
	private JPanel northPanel = new JPanel();

	// For creating the label.
	private JLabel title1 = new JLabel( "Search for Books and Members" ); //$NON-NLS-1$

	// For creating the center.
	private JPanel center = new JPanel();

	// For creating the Center Panel.
	private JPanel centerBooksPanel = new JPanel();

	// For creating an Internal Panel in the center panel.
	private JPanel searchBooksPanel = new JPanel();

	// For creating an Internal Panel in the center panel.
	private JPanel searchBooksButtonPanel = new JPanel();

	// For creating the table.
	private JLabel searchBooksLabel = new JLabel( " Search by: " ); //$NON-NLS-1$

	// For creating JComboBox.
	private JComboBox<?> searchBooksTypes;

	// For creating String[].
	private String[] booksTypes = { "BookID" ,  //$NON-NLS-1$
									"Subject" ,  //$NON-NLS-1$
									"Title" ,  //$NON-NLS-1$
									"Author" ,  //$NON-NLS-1$
									"Publisher" ,  //$NON-NLS-1$
									"ISBN" }; //$NON-NLS-1$

	// For creating the label.
	private JLabel booksKey = new JLabel( " Write the Keyword: " ); //$NON-NLS-1$

	// For cearting the text field.
	private JTextField booksKeyTextField = new JTextField();

	// For creating the button.
	private JButton searchBooksButton = new JButton( "Search" ); //$NON-NLS-1$

	// For creating the Center Panel.
	private JPanel centerMembersPanel = new JPanel();

	// For creating an Internal Panel in the center panel.
	private JPanel searchMembersPanel = new JPanel();

	// For creating an Internal Panel in the center panel.
	private JPanel searchMembersButtonPanel = new JPanel();

	// For creating the table.
	private JLabel searchMembersLabel = new JLabel( " Search by: " ); //$NON-NLS-1$

	// For creating JComboBox.
	private JComboBox<?> searchMembersTypes;

	// For creating String[].
	private String[] membersTypes = { "MemberID" , //$NON-NLS-1$
									  "Name" ,  //$NON-NLS-1$
									  "E-Mail" ,  //$NON-NLS-1$
									  "Major" }; //$NON-NLS-1$

	// For creating the label.
	private JLabel membersKey = new JLabel( " Write the Keyword: " ); //$NON-NLS-1$

	// For cearting the text field.
	private JTextField membersKeyTextField = new JTextField();

	// For creating the button.
	private JButton searchMembersButton = new JButton( "Search" ); //$NON-NLS-1$

	// For creating the south panel.
	private JPanel southPanel = new JPanel();

	// For creating the button.
	private JButton cancelButton = new JButton( "Cancel" ); //$NON-NLS-1$

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
		LOGGER.info("Checked information from book text field"); //$NON-NLS-1$
		
		this.setBooksData(new String[2]);
		this.getBooksData()[0] = this.searchBooksTypes.getSelectedItem().toString();

		for ( int i = 1 ; i < this.getBooksData().length ; i++ ){
			if ( !this.getBooksKeyTextField().getText().equals( "" ) ){ //$NON-NLS-1$

				if ( this.searchBooksTypes.getSelectedItem().toString().equals( "BookID" ) ){ //$NON-NLS-1$

					this.getBooksData()[i] = this.getBooksKeyTextField().getText();

				}else{
					this.getBooksData()[i] = "'%" + this.getBooksKeyTextField().getText() + "%'";  //$NON-NLS-1$//$NON-NLS-2$
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
		LOGGER.info("Checked information from member text field"); //$NON-NLS-1$
		
		this.setMembersData(new String[2]);
		this.getMembersData()[0] = this.searchMembersTypes.getSelectedItem().toString();

		for ( int i = 1 ; i < this.getMembersData().length ; i++ ){
			if ( !this.getMembersKeyTextField().getText().equals( "" ) ){ //$NON-NLS-1$
				if ( this.searchMembersTypes
						.getSelectedItem().toString().equals( "MemberID" ) ){ //$NON-NLS-1$
					this.getMembersData()[i] = this.getMembersKeyTextField().getText();
				}else
					this.getMembersData()[i] = "'%" + this.getMembersKeyTextField().getText() + "%'"; //$NON-NLS-1$ //$NON-NLS-2$
			}else
				return false;
		}
		return true;
	}
	
	void settingTheFonts(){
	
		/**
		 * for setting the font to the lables & buttons
		 */
		this.searchBooksLabel.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) ); //$NON-NLS-1$
		this.searchBooksTypes.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) ); //$NON-NLS-1$
		this.booksKey.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) ); //$NON-NLS-1$
		this.getBooksKeyTextField().setFont( new Font( "Tahoma" , Font.PLAIN , 11 ) ); //$NON-NLS-1$
		this.searchBooksButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) ); //$NON-NLS-1$
		this.cancelButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) ); //$NON-NLS-1$
		this.searchMembersLabel.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) ); //$NON-NLS-1$
		this.searchMembersTypes.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) ); //$NON-NLS-1$
		this.membersKey.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) ); //$NON-NLS-1$
		this.getMembersKeyTextField().setFont( new Font( "Tahoma" , Font.PLAIN , 11 ) ); //$NON-NLS-1$
		this.searchMembersButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) ); //$NON-NLS-1$
		this.cancelButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) ); //$NON-NLS-1$
		
	}

	// Constructor of searchBooksAndMembers.
	public SearchBooksAndMembers (){

		// For setting the title for the internal frame.
		super( "Search" , false , true , false , true ); //$NON-NLS-1$
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("SearchBooksAndMembers was created."); //$NON-NLS-1$

		// For setting the icon.
		setFrameIcon( new ImageIcon( ClassLoader.getSystemResource( "images/Find16.gif" ) ) ); //$NON-NLS-1$

		// For getting the graphical user interface components display area.
		Container cp = getContentPane();

		// For setting the layout.
		this.northPanel.setLayout( new FlowLayout( FlowLayout.CENTER ) );

		// For setting the font.
		this.title1.setFont( new Font( "Tahoma" , Font.BOLD , 14 ) ); //$NON-NLS-1$

		// For adding the label.
		this.northPanel.add( this.title1 );

		// For adding the north panel to the container.
		cp.add( "North" , this.northPanel ); //$NON-NLS-1$

		// For setting the layout.
		this.center.setLayout( new BorderLayout() );

		// For setting the layout.
		this.centerBooksPanel.setLayout( new BorderLayout() );

		// For setting the layout.
		this.searchBooksPanel.setLayout( new GridLayout( 2 , 2 , 1 , 1 ) );

		// For adding the label.
		this.searchBooksPanel.add( this.searchBooksLabel );

		// For adding the JComboBos[].

		// For adding the label.
		this.searchBooksPanel.add( this.booksKey );

		// For adding the text field.
		this.searchBooksPanel.add( this.getBooksKeyTextField() );

		// For adding the internal panel to the panel.
		this.centerBooksPanel.add( "North" , this.searchBooksPanel ); //$NON-NLS-1$

		// For setting the layout.
		this.searchBooksButtonPanel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );

		// For adding the button.
		this.searchBooksButtonPanel.add( this.searchBooksButton );

		// For adding the internal panel to the center panel.
		this.centerBooksPanel.add( "South" , this.searchBooksButtonPanel ); //$NON-NLS-1$

		// For setting the border.
		this.centerBooksPanel.setBorder( BorderFactory.createTitledBorder( "Search for a books:" ) ); //$NON-NLS-1$

		// For adding center panel to the center.
		this.center.add( "West" , this.centerBooksPanel ); //$NON-NLS-1$

		// For setting the layout.
		this.centerMembersPanel.setLayout( new BorderLayout() );

		// For setting the layout.
		this.searchMembersPanel.setLayout( new GridLayout( 2 , 2 , 1 , 1 ) );

		// For adding the label.
		this.searchMembersPanel.add( this.searchMembersLabel );

		// For adding the JComboBos[].

		// For adding the label.
		this.searchMembersPanel.add( this.membersKey );

		// For adding the text field.
		this.searchMembersPanel.add( this.getMembersKeyTextField() );

		// For adding the internal panel to the panel.
		this.centerMembersPanel.add( "North" , this.searchMembersPanel ); //$NON-NLS-1$

		// For setting the layout.
		this.searchMembersButtonPanel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );

		// For adding the button.
		this.searchMembersButtonPanel.add( this.searchMembersButton );

		// For adding the internal panel to the center panel.
		this.centerMembersPanel.add( "South" , this.searchMembersButtonPanel ); //$NON-NLS-1$

		// For setting the border.
		this.centerMembersPanel.setBorder( BorderFactory.createTitledBorder( "Search for a members:" ) ); //$NON-NLS-1$

		// For adding center panel to the center.
		this.center.add( "East" , this.centerMembersPanel ); //$NON-NLS-1$

		// For adding the center to the container.
		cp.add( "Center" , this.center ); //$NON-NLS-1$

		settingTheFonts();
		
		// For setting the layout.
		this.southPanel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );

		// For adding the button.
		this.southPanel.add( this.cancelButton );

		// For setting the border.
		this.southPanel.setBorder( BorderFactory.createEtchedBorder() );

		// For adding the south panel to the container.
		cp.add( "South" , this.southPanel ); //$NON-NLS-1$

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField and passing them to listSearchBooks object*
		 ***********************************************************************/
		this.searchBooksButton.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed ( ActionEvent ae ){

				// For checking if there is a missing information.
				if ( isBooksDataCorrect() ){
					SearchBooksAndMembers.this.setBook(new Books());
					String bookQuery = "SELECT BookID, Subject, Title, Author, Publisher," //$NON-NLS-1$
							+ "Copyright, Edition, Pages, NumberOfBooks,ISBN,Library,Availble,ShelfNo FROM Books" + " WHERE "  //$NON-NLS-1$//$NON-NLS-2$
							+ SearchBooksAndMembers.this.getBooksData()[0] + " LIKE " + SearchBooksAndMembers.this.getBooksData()[1]; //$NON-NLS-1$

					getBook().connection( bookQuery );

					int bookID = getBook().getBookID();
					if ( bookID != 0 ){
						SearchBooksAndMembers.this.setListBooks(new ListSearchBooks( bookQuery ));
						getParent().add( getListBooks() );
						try{
							getListBooks().setSelected( true );
						}catch ( java.beans.PropertyVetoException e ){
						}
						dispose();
					}else{
						JOptionPane.showMessageDialog( null , "No Match(es)" , "Error" , JOptionPane.ERROR_MESSAGE ); //$NON-NLS-1$ //$NON-NLS-2$
						SearchBooksAndMembers.this.getBooksKeyTextField().setText( null );
					}
				}else{
					JOptionPane.showMessageDialog( null , "Please, complete the information" , "Warning" , JOptionPane.WARNING_MESSAGE );  //$NON-NLS-1$//$NON-NLS-2$
				}
			}
		} );

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField and passing them to listSearchBooks object*
		 ***********************************************************************/
		this.searchMembersButton.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed ( ActionEvent ae ){

				if ( isMembersDataCorrect() ){

					SearchBooksAndMembers.this.setMember(new Members());
					String memberQuery = "SELECT MemberID, ID, Name, EMail, Major, Expired" + " FROM Members WHERE " + SearchBooksAndMembers.this.getMembersData()[0]  //$NON-NLS-1$//$NON-NLS-2$
							+ " LIKE " + getMembersData()[1]; //$NON-NLS-1$
					getMember().connection( memberQuery );
					int memberID = getMember().getMemberID();

					if ( memberID != 0 ){

						SearchBooksAndMembers.this.setListMembers(new ListSearchMembers( memberQuery ));
						getParent().add( getListMembers() );

						try{
							getListMembers().setSelected( true );
						}catch ( java.beans.PropertyVetoException e ){
						}
						dispose();
					}else{
						JOptionPane.showMessageDialog( null , "No Match(es)" , "Error" , JOptionPane.ERROR_MESSAGE ); //$NON-NLS-1$ //$NON-NLS-2$
						SearchBooksAndMembers.this.getMembersKeyTextField().setText( null );
					}
				}else{
					JOptionPane.showMessageDialog( null , "Please, complete the information" , "Warning" , JOptionPane.WARNING_MESSAGE ); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		} );

		// For adding the action listener for the button to dispose the frame.
		this.cancelButton.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed ( ActionEvent ae ){

				dispose();
			}
		} );
		// For setting the visible to true.
		setVisible( true );
		// Show the internal frame.
		pack();
	}

	public Books getBook(){
		return this.book;
	}

	public void setBook( Books book ){
		this.book = book;
	}

	public String[] getBooksData(){
		return this.booksData;
	}

	public void setBooksData( String[] booksData ){
		this.booksData = booksData;
	}

	public ListSearchBooks getListBooks(){
		return this.listBooks;
	}

	public void setListBooks( ListSearchBooks listBooks ){
		this.listBooks = listBooks;
	}

	public JTextField getBooksKeyTextField(){
		return this.booksKeyTextField;
	}

	public void setBooksKeyTextField( JTextField booksKeyTextField ){
		this.booksKeyTextField = booksKeyTextField;
	}

	public Members getMember(){
		return this.member;
	}

	public void setMember( Members member ){
		this.member = member;
	}

	public String[] getMembersData(){
		return this.membersData;
	}

	public void setMembersData( String[] membersData ){
		this.membersData = membersData;
	}

	public ListSearchMembers getListMembers(){
		return this.listMembers;
	}

	public void setListMembers( ListSearchMembers listMembers ){
		this.listMembers = listMembers;
	}

	public JTextField getMembersKeyTextField(){
		return this.membersKeyTextField;
	}

	public void setMembersKeyTextField( JTextField membersKeyTextField ){
		this.membersKeyTextField = membersKeyTextField;
	}
}
