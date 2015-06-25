import static org.junit.Assert.*;

import org.junit.Test;



public class CenterTest{

	@Test
	public void testLibraryCenter () throws Exception{
		
	Center center = new Center( new JLibrary() );
	
		center.LibraryCenter();
		assertTrue( center.l != null );
	
	}

}
