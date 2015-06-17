import org.junit.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class AddBooksTest{

	private AddBooks addBooks;

	@Before
	public void setup (){

		addBooks = new AddBooks();
	}

	@Test
	/*
	 * Checks the return of the method isCorrect, should return false if no
	 * Exception is raised
	 */
	public void testSoma () throws Exception{

		try{

			assertEquals( false , addBooks.isCorrect() );
		}catch ( Exception e ){

			assertEquals( true , addBooks.isCorrect() );
		}
	}

	public void clearTextFieldTest (){
		
		addBooks.clearTextField();
		assertThat( addBooks.getTxtShelfNo() , not( equalTo( null ) ) );
	}

}
