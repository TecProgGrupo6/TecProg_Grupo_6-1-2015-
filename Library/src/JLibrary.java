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
	private JDesktopPane desktop = new JDesktopPane();

	// Does nothing...
	private JSplitPane splitPane;

	// Does nothing...
	private JScrollPane desktopScrollPane;

	// Does nothing...
	private JScrollPane treeScrollPane;

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
	private AddBooks addBooks;

	// To borrow books
	private BorrowBooks borrowBooks;

	// To return books
	private ReturnBooks returnBooks;

	// To add members
	private AddMembers addMembers;

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

		setExtendedState( JFrame.MAXIMIZED_BOTH );

		Toolkit kit = Toolkit.getDefaultToolkit();
		Image image = kit.getImage( ClassLoader.getSystemResource( "images/Host16.gif" ) );
		setIconImage( image );

		// Creating a Menubar and a Toolbar
		menu = new Menubar();
		toolbar = new Toolbar();

		setMenu(menu);

		// Get the graphical user interface components display the desktop
		Container cp = getContentPane();

		desktop.setBackground( Color.GRAY );

		cp.add( "Center" , desktop );

		// For setting the font
		searchLabel.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );

		// For setting the font
		searchTextField.setFont( new Font( "Tahoma" , Font.PLAIN , 12 ) );

		goButton.setFont( new Font( "Tahoma" , Font.BOLD , 9 ) );

		// For adding the searchLable to the searchToolBar
		searchToolBar.add( searchLabel );

		// For adding the searchTextField to searchToolBar
		searchToolBar.add( searchTextField );

		// For adding the goButton to searchToolBar
		searchToolBar.add( goButton );

		// For adding listenerAction for the button
		goButton.addActionListener( this );

		// For setting the layout
		searchPanel.setLayout( new BorderLayout() );

		// For adding the toolBar to the searchPanel
		searchPanel.add( "Center" , toolbar );

		// For adding the searchPanel to the Container
		cp.add( "North" , searchPanel );

		// For adding the statusbar to the Container
		cp.add( "South" , statusbar );

		for ( int i = 0 ; i < toolbar.imageName24.length ; i++ ){

			// For adding the action to the button
			toolbar.button[i].addActionListener( this );

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

		if ( ae.getSource() == menu.addBook || ae.getSource() == toolbar.button[0] ){

			Thread runner = new Thread(){

				public void run (){

					addBooks = new AddBooks();
					desktop.add( addBooks );

					try{

						addBooks.setSelected( true );

					}catch ( java.beans.PropertyVetoException e ){

						// Do nothing

					}
				}
			};
			runner.start();
		}else if ( ae.getSource() == menu.listBook || ae.getSource() == toolbar.button[1] ){

			Thread runner = new Thread(){

				public void run (){

					listBooks = new ListBooks();
					desktop.add( listBooks );

					try{

						listBooks.setSelected( true );

					}catch ( java.beans.PropertyVetoException e ){

						// Do nothing

					}
				}
			};
			runner.start();
		}else if ( ae.getSource() == menu.addMember || ae.getSource() == toolbar.button[2] ){

			Thread runner = new Thread(){

				public void run (){

					addMembers = new AddMembers();
					desktop.add( addMembers );

					try{

						addMembers.setSelected( true );

					}catch ( java.beans.PropertyVetoException e ){

						// Do nothing

					}
				}
			};
			runner.start();
		}

		else if ( ae.getSource() == menu.listMember || ae.getSource() == toolbar.button[3] ){

			Thread runner = new Thread(){

				public void run (){

					listMembers = new ListMembers();
					desktop.add( listMembers );

					try{

						listMembers.setSelected( true );

					}catch ( java.beans.PropertyVetoException e ){

						// Do nothing

					}
				}
			};
			runner.start();
		}
		if ( ae.getSource() == menu.searchBooksAndMembers || ae.getSource() == toolbar.button[4] ){
			Thread runner = new Thread(){

				public void run (){

					search = new SearchBooksAndMembers();
					desktop.add( search );
					try{
						search.setSelected( true );
					}catch ( java.beans.PropertyVetoException e ){

						// Do nothing

					}
				}
			};
			runner.start();
		}else if ( ae.getSource() == menu.borrowBook || ae.getSource() == toolbar.button[5] ){

			Thread runner = new Thread(){

				public void run (){

					borrowBooks = new BorrowBooks();
					desktop.add( borrowBooks );

					try{

						borrowBooks.setSelected( true );

					}catch ( java.beans.PropertyVetoException e ){

						// Do nothing

					}
				}
			};
			runner.start();
		}else if ( ae.getSource() == menu.returnBook || ae.getSource() == toolbar.button[6] ){

			Thread runner = new Thread(){

				public void run (){

					returnBooks = new ReturnBooks();
					desktop.add( returnBooks );

					try{

						returnBooks.setSelected( true );

					}catch ( java.beans.PropertyVetoException e ){

						// Do nothing

					}
				}
			};
			runner.start();
		}else if ( ae.getSource() == menu.exit || ae.getSource() == toolbar.button[7] ){

			dispose();
			System.exit( 0 );

		}else{
			// No action
		}
	}
}
