import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class JLibraryTest{

	JLibrary jLibrary = new JLibrary();

	@Test
	public void setMenutest (){
		
		Menubar menu = new Menubar();
		
		try{
			this.jLibrary.setMenu( menu );
			
			if(menu.exit != null &&	
					menu.addBook != null && 
					menu.listBook != null && 
					menu.addMember != null && 
					menu.listMember != null && 
					menu.searchBooksAndMembers != null && 
					menu.borrowBook != null && 	
					menu.returnBook != null){
				
				assertTrue(true );
			}else{
				assertTrue( false );
			}
		}catch(Exception e){
			fail(e.toString());
		}

		
	}
}
