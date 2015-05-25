//import the packages for using the classes in them into the program

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A public class
 */
@SuppressWarnings ( "serial" )
public class AddMembers extends JInternalFrame{
	
	
	// Log system from AddBooks Class
	private final static Logger LOGGER = Logger.getLogger( AddMembers.class.getName() );

	/***************************************************************************
	 *** declaration of the private variables used in the program ***
	 ***************************************************************************/

	// For creating the North Panel.
	private JPanel northPanel = new JPanel();

	// For creating the North Label.
	private JLabel northLabel = new JLabel( "MEMBER INFORMATION" );

	// For creating the Center Panel.
	private JPanel centerPanel = new JPanel();

	// For creating an Internal Panel in the center panel.
	private JPanel informationLabelPanel = new JPanel();

	// For creating an array of JLabel.
	private JLabel[] informationLabel = new JLabel[7];

	// For creating an array of String.
	private String[] informaionString = { " Member ID: " , " The Password: " , " Rewrite the password: " , " The Name: " , " E-MAIL: " ,
			" Major Branch:" , " Expired: " };

	// For creating an Internal Panel in the center panel.
	private JPanel informationTextFieldPanel = new JPanel();

	// For creating an array of JTextField.
	private JTextField[] informationTextField = new JTextField[5];

	// For creating an array of JPasswordField.
	private JPasswordField[] informationPasswordField = new JPasswordField[2];

	// For creating an Internal Panel in the center panel.
	private JPanel insertInformationButtonPanel = new JPanel();

	// For creating a button.
	private JButton insertInformationButton = new JButton( "Insert the Information" );

	// For creating the South Panel.
	private JPanel southPanel = new JPanel();

	// For creating a button.
	private JButton OKButton = new JButton( "Exit" );

	// Create objects from another classes for using them in the ActionListener.
	private Members member;

	// For creating an array of string to store the data.
	private String[] data;

	// For checking the password.
	@SuppressWarnings ( "deprecation" )
	public boolean isPasswordCorrect (){

		if ( this.informationPasswordField[0].getText().equals( this.informationPasswordField[1].getText() ) ){

			this.data[1] = this.informationPasswordField[1].getText();
			
			LOGGER.setLevel( Level.INFO );
			LOGGER.info("Password Correct");

		}else if ( !this.informationPasswordField[0].getText().equals( this.informationPasswordField[1].getText() ) ){
			
			LOGGER.setLevel( Level.INFO );
			LOGGER.info("Information is incorrect");
			
			return false;
		}

		return true;
	}

	// For checking the information from the text field.
	double informationLabelLength = this.informationLabel.length;

	@SuppressWarnings ( "deprecation" )
	public boolean isCorrect (){

		this.data = new String[6];

		for ( int i = 0 ; i < this.informationLabelLength ; i++ ){
			if ( i == 0 ){
				if ( !this.informationTextField[i].getText().equals( "" ) ){

					this.data[i] = this.informationTextField[i].getText();

				}else{
					return false;
				}
			}
			if ( i == 1 || i == 2 ){

				if ( this.informationPasswordField[i - 1].getText().equals( "" ) ){

					return false;

				}
			}
			if ( i == 3 || i == 4 || i == 5 || i == 6 ){
				if ( !this.informationTextField[i - 2].getText().equals( "" ) ){

					this.data[i - 1] = this.informationTextField[i - 2].getText();

				}else{
					return false;
				}
			}
		}
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Information is Correct");
		
		return true;
	}

	// For setting the array of JTextField & JPasswordField to null.
	public void clearTextField (){

		for ( int i = 0 ; i < this.informationLabelLength ; i++ ){
			if ( i == 0 ){

				this.informationTextField[i].setText( null );

			}
			if ( i == 1 || i == 2 ){

				this.informationPasswordField[i - 1].setText( null );

			}
			if ( i == 3 || i == 4 || i == 5 || i == 6 ){

				this.informationTextField[i - 2].setText( null );

			}
		}
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("TextField is clear");
	}
	
	
	/***********************************************************************
	 * for adding the JTextField and JPasswordField to the panel and *
	 * setting the font to the JTextField and JPasswordField. Finally *
	 * adding the panel to the centerPanel *
	 ***********************************************************************/
	void addFieldsToThePanel(){

		for ( int i = 0 ; i < this.informationLabel.length ; i++ ){
			if ( i == 1 || i == 2 ){

				this.informationTextFieldPanel.add( this.informationPasswordField[i - 1] = new JPasswordField( 25 ) );
				this.informationPasswordField[i - 1].setFont( new Font( "Tahoma" , Font.PLAIN , 11 ) );

			}
			if ( i == 0 ){

				this.informationTextFieldPanel.add( this.informationTextField[i] = new JTextField( 25 ) );
				this.informationTextField[i].setFont( new Font( "Tahoma" , Font.PLAIN , 11 ) );

			}
			if ( i == 3 || i == 4 || i == 5 || i == 6 ){

				this.informationTextFieldPanel.add( this.informationTextField[i - 2] = new JTextField( 25 ) );
				this.informationTextField[i - 2].setFont( new Font( "Tahoma" , Font.PLAIN , 11 ) );

			}
		}
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Fields added to the panel");
	
	}
	
	
	void settingTheLayoutPanel( Container cp ){
	
		/***********************************************************************
		 * for setting the layout for the panel,setting the font for the button*
		 * and adding the button to the panel. * finally adding the panel to the
		 * container *
		 ***********************************************************************/
		this.insertInformationButtonPanel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );
		this.insertInformationButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
		this.insertInformationButtonPanel.add( this.insertInformationButton );
		this.centerPanel.add( "South" , this.insertInformationButtonPanel );
		cp.add( "Center" , this.centerPanel );
	
		/***********************************************************************
		 * for setting the layout for the panel,setting the font for the button*
		 * adding the button to the panel & setting the border. * finally adding
		 * the panel to the container *
		 ***********************************************************************/
		this.southPanel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );
		this.OKButton.setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );
		this.southPanel.add( this.OKButton );
		this.southPanel.setBorder( BorderFactory.createEtchedBorder() );
		cp.add( "South" , this.southPanel );
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Layout setted");
		
	}
	

	// Constructor of addMembers.
	public AddMembers (){

		// For setting the title for the internal frame.
		super( "Add Members" , false , true , false , true );

		// For setting the icon.
		setFrameIcon( new ImageIcon( ClassLoader.getSystemResource( "images/Add16.gif" ) ) );

		// For getting the graphical user interface components display area.
		Container cp = getContentPane();

		// For setting the layout.
		this.northPanel.setLayout( new FlowLayout( FlowLayout.CENTER ) );

		// For setting the font.
		this.northLabel.setFont( new Font( "Tahoma" , Font.BOLD , 14 ) );

		// For adding the label to the panel.
		this.northPanel.add( this.northLabel );

		// For adding the panel to the container.
		cp.add( "North" , this.northPanel );

		// For setting the layout.
		this.centerPanel.setLayout( new BorderLayout() );

		// For setting the border to the panel.
		this.centerPanel.setBorder( BorderFactory.createTitledBorder( "Add a new member:" ) );

		// For setting the layout.
		this.informationLabelPanel.setLayout( new GridLayout( 7 , 1 , 1 , 1 ) );

		// For setting the layout.
		this.informationTextFieldPanel.setLayout( new GridLayout( 7 , 1 , 1 , 1 ) );

		/***********************************************************************
		 * for adding the strings to the labels, for setting the font * and
		 * adding these labels to the panel. * finally adding the panel to the
		 * container *
		 ***********************************************************************/
		for ( int i = 0 ; i < this.informationLabel.length ; i++ ){

			this.informationLabelPanel.add( this.informationLabel[i] = new JLabel( this.informaionString[i] ) );
			this.informationLabel[i].setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );

		}

		// For adding the panel to the centerPanel.
		this.centerPanel.add( "West" , this.informationLabelPanel );
		
		addFieldsToThePanel();
		
		this.centerPanel.add( "East" , this.informationTextFieldPanel );

		settingTheLayoutPanel( cp );

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField[] and make the connection for database, *
		 * after that update the table in the database with the new value *
		 ***********************************************************************/
		this.insertInformationButton.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed ( ActionEvent ae ){

				// For checking if there is a missing information.
				if ( isCorrect() ){
					if ( isPasswordCorrect() ){
						Thread runner = new Thread(){

							@SuppressWarnings ( "synthetic-access" )
							@Override
							public void run (){

								AddMembers.this.member = new Members();
								@SuppressWarnings ( "unused" )
								Date d = new Date();

								// For checking if there is no same information
								// in the database.
								AddMembers.this.member.connection( "SELECT * FROM Members WHERE ID = " + AddMembers.this.data[0] );
								int ID = AddMembers.this.member.getID();

								if ( Integer.parseInt( AddMembers.this.data[0] ) != ID ){

									AddMembers.this.member.update( "INSERT INTO Members (ID,Password,Name,EMail,Major,Expired) VALUES (" + AddMembers.this.data[0] + ", '"
											+ AddMembers.this.data[1] + "','" + AddMembers.this.data[2] + "','" + AddMembers.this.data[3] + "','" + AddMembers.this.data[4] + "','" + AddMembers.this.data[5] + "')" );

									// For setting the array of JTextField &
									// JPasswordField to null.
									clearTextField();

								}else{
									JOptionPane.showMessageDialog( null , "Member is in the Library" , "Error" , JOptionPane.ERROR_MESSAGE );
								}
							}
						};
						runner.start();
					}

					// If the password is wrong.
					else{
						JOptionPane.showMessageDialog( null , "the passowrd is wrong" , "Error" , JOptionPane.ERROR_MESSAGE );
					}
				}

				// If there is a missing data, then display Message Dialog.
				else{
					JOptionPane.showMessageDialog( null , "Please, complete the information" , "Warning" , JOptionPane.WARNING_MESSAGE );
				}
			}
		} );

		// For adding the action listener for the button to dispose the frame.
		this.OKButton.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed ( ActionEvent ae ){

				dispose();
			}
		} );

		// For setting the visible to true.
		setVisible( true );

		// Show the internal frame.
		pack();
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Members added");
	}
	

}
