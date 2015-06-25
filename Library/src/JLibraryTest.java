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

			if ( menu.exit != null && menu.addBook != null && menu.listBook != null && menu.addMember != null && menu.listMember != null
					&& menu.searchBooksAndMembers != null && menu.borrowBook != null && menu.returnBook != null ){

				assertTrue( true );
			}else{
				assertTrue( false );
			}
		}catch ( Exception e ){
			fail( e.toString() );
		}

	}

	public void getSetListBooksTest (){

		ListBooks listBooks = new ListBooks();

		try{
			this.jLibrary.setListBooks( listBooks );
			assertTrue( this.jLibrary.getListBooks() == listBooks );
		}catch ( Exception e ){
			fail( e.toString() );
		}
	}

	public void getSetListMembersTest (){

		ListMembers listMembers = new ListMembers();

		try{
			this.jLibrary.setListMembers( listMembers );
			assertTrue( this.jLibrary.getListMembers() == listMembers);
		}catch ( Exception e ){
			fail( e.toString() );
		}
	}
	
	public void getSetSearchBooksAnsMembersTest (){

		SearchBooksAndMembers searchBooksAndMembers = new SearchBooksAndMembers();

		try{
			this.jLibrary.setSearch( searchBooksAndMembers );
			assertTrue( this.jLibrary.getSearch() == searchBooksAndMembers);
		}catch ( Exception e ){
			fail( e.toString() );
		}
	}
	
	public void getSetBorrowBooksTest (){

		BorrowBooks borrowBooks = new BorrowBooks();

		try{
			this.jLibrary.setBorrowBooks( borrowBooks );
			assertTrue( this.jLibrary.getBorrowBooks() == borrowBooks);
		}catch ( Exception e ){
			fail( e.toString() );
		}
	}
	
	public void getSetReturnBooksTest (){

		ReturnBooks returnBooks = new ReturnBooks();

		try{
			this.jLibrary.setReturnBooks( returnBooks );
			assertTrue( this.jLibrary.getReturnBooks() == returnBooks);
		}catch ( Exception e ){
			fail( e.toString() );
		}
	}
}
