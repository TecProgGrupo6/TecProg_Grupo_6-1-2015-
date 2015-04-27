//import the packages for using the classes in them into this class

import java.awt.*;

/**
 *A PUBLIC CLASS FOR CENTER.JAVA
 */
public class Center {
	
	JLibrary l; //For using the class in JLibrary.java

	public Center( JLibrary l ) {
		
		this.l = l;
		
	}

	//For centering the window
	public void LibraryCenter() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		l.setLocation(( screenSize.width - l.getWidth()) / 2, (screenSize.height - l.getHeight()) / 2 );
		
	}
}