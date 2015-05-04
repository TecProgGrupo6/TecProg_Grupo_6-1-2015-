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

/**
 * A public class
 */
public class AddMembers extends JInternalFrame{

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
	public boolean isPasswordCorrect (){

		if ( informationPasswordField[0].getText().equals( informationPasswordField[1].getText() ) ){

			data[1] = informationPasswordField[1].getText();

		}else if ( !informationPasswordField[0].getText().equals( informationPasswordField[1].getText() ) ){
			return false;
		}

		return true;
	}

	// For checking the information from the text field.
	double informationLabelLength = informationLabel.length;

	public boolean isCorrect (){

		data = new String[6];

		for ( int i = 0 ; i < informationLabelLength ; i++ ){
			if ( i == 0 ){
				if ( !informationTextField[i].getText().equals( "" ) ){

					data[i] = informationTextField[i].getText();

				}else{
					return false;
				}
			}
			if ( i == 1 || i == 2 ){

				if ( informationPasswordField[i - 1].getText().equals( "" ) ){

					return false;

				}
			}
			if ( i == 3 || i == 4 || i == 5 || i == 6 ){
				if ( !informationTextField[i - 2].getText().equals( "" ) ){

					data[i - 1] = informationTextField[i - 2].getText();

				}else{
					return false;
				}
			}
		}
		return true;
	}

	// For setting the array of JTextField & JPasswordField to null.
	public void clearTextField (){

		for ( int i = 0 ; i < informationLabelLength ; i++ ){
			if ( i == 0 ){

				informationTextField[i].setText( null );

			}
			if ( i == 1 || i == 2 ){

				informationPasswordField[i - 1].setText( null );

			}
			if ( i == 3 || i == 4 || i == 5 || i == 6 ){

				informationTextField[i - 2].setText( null );

			}
		}
	}
	
	
	/***********************************************************************
	 * for adding the JTextField and JPasswordField to the panel and *
	 * setting the font to the JTextField and JPasswordField. Finally *
	 * adding the panel to the centerPanel *
	 ***********************************************************************/
	void addFieldsToThePanel(){

		for ( int i = 0 ; i < informationLabel.length ; i++ ){
			if ( i == 1 || i == 2 ){

				informationTextFieldPanel.add( informationPasswordField[i - 1] = new JPasswordField( 25 ) );
				informationPasswordField[i - 1].setFont( new Font( "Tahoma" , Font.PLAIN , 11 ) );

			}
			if ( i == 0 ){

				informationTextFieldPanel.add( informationTextField[i] = new JTextField( 25 ) );
				informationTextField[i].setFont( new Font( "Tahoma" , Font.PLAIN , 11 ) );

			}
			if ( i == 3 || i == 4 || i == 5 || i == 6 ){

				informationTextFieldPanel.add( informationTextField[i - 2] = new JTextField( 25 ) );
				informationTextField[i - 2].setFont( new Font( "Tahoma" , Font.PLAIN , 11 ) );

			}
		}
	
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
		northPanel.setLayout( new FlowLayout( FlowLayout.CENTER ) );

		// For setting the font.
		northLabel.setFont( new Font( "Tahoma" , Font.BOLD , 14 ) );

		// For adding the label to the panel.
		northPanel.add( northLabel );

		// For adding the panel to the container.
		cp.add( "North" , northPanel );

		// For setting the layout.
		centerPanel.setLayout( new BorderLayout() );

		// For setting the border to the panel.
		centerPanel.setBorder( BorderFactory.createTitledBorder( "Add a new member:" ) );

		// For setting the layout.
		informationLabelPanel.setLayout( new GridLayout( 7 , 1 , 1 , 1 ) );

		// For setting the layout.
		informationTextFieldPanel.setLayout( new GridLayout( 7 , 1 , 1 , 1 ) );

		/***********************************************************************
		 * for adding the strings to the labels, for setting the font * and
		 * adding these labels to the panel. * finally adding the panel to the
		 * container *
		 ***********************************************************************/
		for ( int i = 0 ; i < informationLabel.length ; i++ ){

			informationLabelPanel.add( informationLabel[i] = new JLabel( informaionString[i] ) );
			informationLabel[i].setFont( new Font( "Tahoma" , Font.BOLD , 11 ) );

		}

		// For adding the panel to the centerPanel.
		centerPanel.add( "West" , informationLabelPanel );
		
		addFieldsToThePanel();
		
		centerPanel.add( "East" , informationTextFieldPanel );

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

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField[] and make the connection for database, *
		 * after that update the table in the database with the new value *
		 ***********************************************************************/
		insertInformationButton.addActionListener( new ActionListener(){

			public void actionPerformed ( ActionEvent ae ){

				// For checking if there is a missing information.
				if ( isCorrect() ){
					if ( isPasswordCorrect() ){
						Thread runner = new Thread(){

							public void run (){

								member = new Members();
								Date d = new Date();

								// For checking if there is no same information
								// in the database.
								member.connection( "SELECT * FROM Members WHERE ID = " + data[0] );
								int ID = member.getID();

								if ( Integer.parseInt( data[0] ) != ID ){

									member.update( "INSERT INTO Members (ID,Password,Name,EMail,Major,Expired) VALUES (" + data[0] + ", '"
											+ data[1] + "','" + data[2] + "','" + data[3] + "','" + data[4] + "','" + data[5] + "')" );

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
