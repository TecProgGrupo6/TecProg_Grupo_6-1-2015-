import org.junit.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class AddBooksTest{

	private AddBooks addBooks;

	@Before
	public void setup (){

		this.addBooks = new AddBooks();
	}

	@SuppressWarnings ( "boxing" )
	@Test
	/*
	 * Checks the return of the method isCorrect, should return false if no
	 * Exception is raised
	 */
	public void testSoma () throws Exception{

		try{

			assertEquals( false , this.addBooks.isCorrect() );
		}catch ( Exception e ){

			assertEquals( true , this.addBooks.isCorrect() );
		}
	}

	public void clearTextFieldTest (){
		
		this.addBooks.clearTextField();
		assertThat( this.addBooks.getTxtShelfNo() , not( equalTo( null ) ) );
	}

}
