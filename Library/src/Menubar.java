// Import the packages for using the classes in them into the program.
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings ( "serial" )
public class Menubar extends JMenuBar{
	
	// Log system from Menubar class
	private final static Logger LOGGER = Logger.getLogger( Menubar.class.getName() );

	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	// For creating the JMenu for the program.
	public JMenu fileMenu;
	public JMenu bookMenu;
	public JMenu memberMenu;
	public JMenu searchMenu;
	public JMenu loanMenu;

	// For creating the JMenuItem for JMenu.
	public JMenuItem exit;
	public JMenuItem addBook;
	public JMenuItem listBook;
	public JMenuItem addMember;
	public JMenuItem listMember;
	public JMenuItem searchBooksAndMembers;
	public JMenuItem borrowBook;
	public JMenuItem returnBook;

	// For creating an imageIcon.
	public ImageIcon[] icons;

	// For creating the name of the image file 16*16.
	public String[] imageName16 = { "images/Print16.gif" , "images/Exit16.gif" , "images/Add16.gif" , "images/List16.gif" ,
			"images/Edit16.gif" , "images/Delete16.gif" , "images/Information16.gif" , "images/Find16.gif" , "images/Export16.gif" ,
			"images/Import16.gif" ,

	};

	// For adding book, member, search, loan & help Menus to the menu bar.
	void addOptionsToMenuBar(){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Options were added to menu bar");
		
		this.add( this.fileMenu = new JMenu( "File" ) );
		this.add( this.bookMenu = new JMenu( "Books" ) );
		this.add( this.memberMenu = new JMenu( "Members" ) );
		this.add( this.searchMenu = new JMenu( "Search" ) );
		
		this.add( this.loanMenu = new JMenu( "Loan" ) );
	}
	
	// For setting the Mnemonic
	void settingMnemonics(){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Mnemonics were setted");
		
		this.fileMenu.setMnemonic( 'f' );
		this.bookMenu.setMnemonic( 'b' );
		this.memberMenu.setMnemonic( 'm' );
		this.searchMenu.setMnemonic( 's' );
		this.loanMenu.setMnemonic( 'l' );
	
	}
	
	public Menubar (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Menubar created.");

		addOptionsToMenuBar();

		settingMnemonics();

		// For setting the image icons.
		this.icons = new ImageIcon[12];
		for ( int i = 0 ; i < this.imageName16.length ; i++ ){
			this.icons[i] = new ImageIcon( ClassLoader.getSystemResource( this.imageName16[i] ) );
		}

		// For adding print books & exit.
		this.fileMenu.add( this.exit = new JMenuItem( "Exit" , this.icons[1] ) );

		// For adding add, list, listAvailble, listBorrowed, edit & remove
		// Books.
		// And book information to the bookMenu.
		this.bookMenu.add( this.addBook = new JMenuItem( "Add Book" , this.icons[2] ) );
		this.bookMenu.add( this.listBook = new JMenuItem( "List All Books" , this.icons[3] ) );

		// For adding add, list, edit & remove Members and member information
		// to.
		// The memberMenu.
		this.memberMenu.add( this.addMember = new JMenuItem( "Add Member" , this.icons[2] ) );
		this.memberMenu.add( this.listMember = new JMenuItem( "List All Members" , this.icons[3] ) );

		// For adding add, list & remove Members to the memberMenu.
		this.searchMenu.add( this.searchBooksAndMembers = new JMenuItem( "Search" , this.icons[7] ) );

		// For adding borrow & return books to the loanMenu.
		this.loanMenu.add( this.borrowBook = new JMenuItem( "Borrow a Book" , this.icons[8] ) );
		this.loanMenu.add( this.returnBook = new JMenuItem( "Return a Book" , this.icons[9] ) );
		this.exit.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_F4 , ActionEvent.ALT_MASK ) );
		this.searchBooksAndMembers.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_S , ActionEvent.CTRL_MASK ) );

		this.addBook.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_A , ActionEvent.CTRL_MASK ) );
		this.listBook.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_L , ActionEvent.CTRL_MASK ) );

		this.addMember.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_M , ActionEvent.CTRL_MASK ) );
		this.listMember.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_I , ActionEvent.CTRL_MASK ) );

		this.borrowBook.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_B , ActionEvent.CTRL_MASK ) );
		this.returnBook.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_R , ActionEvent.CTRL_MASK ) );
	}
}
