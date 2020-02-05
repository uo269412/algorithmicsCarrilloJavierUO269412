package session0;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatrixOperationsTest {

	@Test
	public void testRandomConstructor() {
		MatrixOperations mo = new MatrixOperations(10);
		mo.write();
	}
	
	@Test
	public void testFileConstructor() {
		MatrixOperations mo = new MatrixOperations("matrix");
		mo.write();
	}
	
	@Test
	public void testDiagonal() {
		MatrixOperations mo = new MatrixOperations("matrix");
		assertEquals(mo.sumDiagonal1(), mo.sumDiagonal2());
		assertEquals(25, mo.sumDiagonal2());
	}
	
	@Test
	public void testTravelPath() {
		MatrixOperations mo = new MatrixOperations("matrix");
		mo.travelPath(3, 0);
		mo.write();
	}
}
