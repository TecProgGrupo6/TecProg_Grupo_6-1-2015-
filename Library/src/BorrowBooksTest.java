import static org.junit.Assert.*;

import org.junit.Test;



public class BorrowBooksTest{
	
	BorrowBooks borrowBooks = new BorrowBooks();

	@Test
	public void clearTextFieldAndisCorrectTest (){

		try{
			this.borrowBooks.clearTextField();
			assertTrue( !this.borrowBooks.isCorrect() );
		}catch(Exception e){
			fail(e.toString());
		}
		
		
		
	}

}
