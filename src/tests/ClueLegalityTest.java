package tests;
import org.junit.Test;

/*
 * @author Aaron & Hollis
 */
public class ClueLegalityTest {
	@Test
	public void testClueLegality(){
		ClueLegality a = new ClueLegality();
		String[][] board = {{"log"},{"tree"},{"leaf"}};
		a.add("log");
		ClueLegality b = new ClueLegality();
		b.add("wood");
		
	
		assertEquals("Your turn is forfeited!",a.equals(board),board);
		assertEquals("You can let your field operatives pick a codename.",board);

}
}
