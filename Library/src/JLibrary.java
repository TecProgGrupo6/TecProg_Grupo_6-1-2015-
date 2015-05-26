import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * A public class
 */
public class JLibrary extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Log system from AddBooks Class
	private final static Logger LOGGER = Logger.getLogger( JLibrary.class.getName() );
		
	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	// For creating the JPanel
	private JPanel searchPanel = new JPanel();

	// For creating the JToolBar for the program
	private JToolBar searchToolBar = new JToolBar();

	// For creating the label
	private JLabel searchLabel = new JLabel( "Book title: " );

	// For creating the JTextField to use it on the searchToolBar
	private JTextField searchTextField = new JTextField( 15 );

	// For creating the JButton to use it on the searchToolBar
	private JButton goButton = new JButton( "Go" );

	// Variable to add objects from others classes
	JDesktopPane desktop = new JDesktopPane();

	/***************************************************************************
	 * create objects from another classes for using them in the ActionListener
	 * *
	 ***************************************************************************/
	// To create the menubar
	private Menubar menu;

	// To create a toolbar
	private Toolbar toolbar;

	// To create a statusbar
	private StatusBar statusbar = new StatusBar();

	// To list the books
	private ListBooks listBooks;

	// To add books
	AddBooks addBooks;

	// To borrow books
	private BorrowBooks borrowBooks;

	// To return books
	private ReturnBooks returnBooks;

	// To add members
	AddMembers addMembers;

	// To list members
	private ListMembers listMembers;

	// To search books
	private SearchBooksAndMembers search;
	
	
	void setMenu(Menubar menu){
	
		// Menu bar Action.........
		setJMenuBar( menu );
		menu.exit.addActionListener( this );
		menu.addBook.addActionListener( this );
		menu.listBook.addActionListener( this );
		menu.addMember.addActionListener( this );
		menu.listMember.addActionListener( this );
		menu.searchBooksAndMembers.addActionListener( this );
		menu.borrowBook.addActionListener( this );
		menu.returnBook.addActionListener( this );
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Menubar created");

	}
	// Constructor of JLibrary
	public JLibrary (){

		// For setting the title for the frame
		super( "Library System" );

		// For setting the size
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

		setExtendedState( Frame.MAXIMIZED_BOTH );

		Toolkit kit = Toolkit.getDefaultToolkit();
		Image image = kit.getImage( ClassLoader.getSystemResource( "images/Host16.gif" ) );
		setIconImage( image );

		// Creating a Menubar and a Toolbar
		this.menu = new Menubar();
		this.toolbar = new Toolbar();

		setMenu(this.menu);

		// Get the graphical user interface components display the desktop
		Container cp = getContentPane();

		this.desktop.setBackground( Color.GRAY );

		cp.add( "Center" , this.desktop );

		// For setting the font
		this.searchLabel.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );

		// For setting the font
		this.searchTextField.setFont( new Font( "Tahoma" , Font.PLAIN , 12 ) );

		this.goButton.setFont( new Font( "Tahoma" , Font.BOLD , 9 ) );

		// For adding the searchLable to the searchToolBar
		this.searchToolBar.add( this.searchLabel );

		// For adding the searchTextField to searchToolBar
		this.searchToolBar.add( this.searchTextField );

		// For adding the goButton to searchToolBar
		this.searchToolBar.add( this.goButton );

		// For adding listenerAction for the button
		this.goButton.addActionListener( this );

		// For setting the layout
		this.searchPanel.setLayout( new BorderLayout() );

		// For adding the toolBar to the searchPanel
		this.searchPanel.add( "Center" , this.toolbar );

		// For adding the searchPanel to the Container
		cp.add( "North" , this.searchPanel );

		// For adding the statusbar to the Container
		cp.add( "South" , this.statusbar );

		for ( int i = 0 ; i < this.toolbar.imageName24.length ; i++ ){

			// For adding the action to the button
			this.toolbar.button[i].addActionListener( this );

		}

		// For adding WindowListener to the program
		addWindowListener( new WindowAdapter(){

			public void windowClosing ( WindowEvent e ){

				System.exit( 0 );

			}
		} );

		// Show the program
		show();
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("JLibrary created");
	}

	/**
	 * This method is invoked when an action occurs.
	 *
	 * @param ae
	 *            the action event.
	 */
	public void actionPerformed ( ActionEvent ae ){

		if ( ae.getSource() == this.menu.addBook || ae.getSource() == this.toolbar.button[0] ){

			Thread runner = new Thread(){

				public void run (){

					JLibrary.this.addBooks = new AddBooks();
					JLibrary.this.desktop.add( JLibrary.this.addBooks );

					try{

						JLibrary.this.addBooks.setSelected( true );

					}catch ( java.beans.PropertyVetoException e ){

						// Do nothing

					}
				}
			};
			runner.start();
		}else if ( ae.getSource() == this.menu.listBook || ae.getSource() == this.toolbar.button[1] ){

			Thread runner = new Thread(){

				public void run (){

					JLibrary.this.setListBooks(new ListBooks());
					JLibrary.this.desktop.add( getListBooks() );

					try{

						getListBooks().setSelected( true );

					}catch ( java.beans.PropertyVetoException e ){

						// Do nothing

					}
				}
			};
			runner.start();
		}else if ( ae.getSource() == this.menu.addMember || ae.getSource() == this.toolbar.button[2] ){

			Thread runner = new Thread(){

				public void run (){

					JLibrary.this.addMembers = new AddMembers();
					JLibrary.this.desktop.add( JLibrary.this.addMembers );

					try{

						JLibrary.this.addMembers.setSelected( true );

					}catch ( java.beans.PropertyVetoException e ){

						// Do nothing

					}
				}
			};
			runner.start();
		}

		else if ( ae.getSource() == this.menu.listMember || ae.getSource() == this.toolbar.button[3] ){

			Thread runner = new Thread(){

				public void run (){

					JLibrary.this.setListMembers(new ListMembers());
					JLibrary.this.desktop.add( JLibrary.this.getListMembers() );

					try{

						getListMembers().setSelected( true );

					}catch ( java.beans.PropertyVetoException e ){

						// Do nothing

					}
				}
			};
			runner.start();
		}
		if ( ae.getSource() == this.menu.searchBooksAndMembers || ae.getSource() == this.toolbar.button[4] ){
			Thread runner = new Thread(){

				public void run (){

					JLibrary.this.setSearch(new SearchBooksAndMembers());
					JLibrary.this.desktop.add( getSearch() );
					try{
						getSearch().setSelected( true );
					}catch ( java.beans.PropertyVetoException e ){

						// Do nothing

					}
				}
			};
			runner.start();
		}else if ( ae.getSource() == this.menu.borrowBook || ae.getSource() == this.toolbar.button[5] ){

			Thread runner = new Thread(){

				public void run (){

					JLibrary.this.setBorrowBooks(new BorrowBooks());
					JLibrary.this.desktop.add( JLibrary.this.getBorrowBooks() );

					try{

						getBorrowBooks().setSelected( true );

					}catch ( java.beans.PropertyVetoException e ){

						// Do nothing

					}
				}
			};
			runner.start();
		}else if ( ae.getSource() == this.menu.returnBook || ae.getSource() == this.toolbar.button[6] ){

			Thread runner = new Thread(){

				public void run (){

					JLibrary.this.setReturnBooks(new ReturnBooks());
					JLibrary.this.desktop.add( JLibrary.this.getReturnBooks() );

					try{

						getReturnBooks().setSelected( true );

					}catch ( java.beans.PropertyVetoException e ){

						// Do nothing

					}
				}
			};
			runner.start();
		}else if ( ae.getSource() == this.menu.exit || ae.getSource() == this.toolbar.button[7] ){

			dispose();
			System.exit( 0 );

		}else{
			// No action
		}
	}
	public ListBooks getListBooks(){

		return this.listBooks;
	}
	public void setListBooks(ListBooks listBooks){

		this.listBooks = listBooks;
	}
	public ListMembers getListMembers(){

		return this.listMembers;
	}
	public void setListMembers(ListMembers listMembers){

		this.listMembers = listMembers;
	}
	public SearchBooksAndMembers getSearch(){

		return this.search;
	}
	public void setSearch(SearchBooksAndMembers search){

		this.search = search;
	}
	public BorrowBooks getBorrowBooks(){

		return this.borrowBooks;
	}
	public void setBorrowBooks(BorrowBooks borrowBooks){

		this.borrowBooks = borrowBooks;
	}
	public ReturnBooks getReturnBooks(){

		return this.returnBooks;
	}
	public void setReturnBooks(ReturnBooks returnBooks){

		this.returnBooks = returnBooks;
	}
}
