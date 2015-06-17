import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MenubarTest{
	
	private Menubar menubar;
	
	@Before
	public void setup (){

		menubar = new Menubar();
	}

	@Test
	// Checks if the publics JMenus were created successfully
	public void addOptionsToMenuBarTest (){
		setup();
		assertThat( menubar.fileMenu , not( equalTo( null ) ) );
		assertThat( menubar.bookMenu , not( equalTo( null ) ) );
		assertThat( menubar.memberMenu , not( equalTo( null ) ) );
		assertThat( menubar.searchMenu , not( equalTo( null ) ) );
		assertThat( menubar.loanMenu , not( equalTo( null ) ) );
	}

	@Test
	// Checks if the publics JMenuItems were created succesfully
	public void MenubarTest (){
		
		assertThat( menubar.addBook , not( equalTo( null ) ) );
		assertThat( menubar.exit , not( equalTo( null ) ) );
		assertThat( menubar.listBook , not( equalTo( null ) ) );
		assertThat( menubar.addMember , not( equalTo( null ) ) );
		assertThat( menubar.listMember , not( equalTo( null ) ) );
		assertThat( menubar.searchBooksAndMembers , not( equalTo( null ) ) );
		assertThat( menubar.borrowBook , not( equalTo( null ) ) );
		assertThat( menubar.returnBook , not( equalTo( null ) ) );
	}

}
