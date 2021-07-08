package question1;

public class EnsembleTest extends junit.framework.TestCase {

	/**
	 * Test Union.
	 */
	public void testUnion() {
		question1.Ensemble<Integer> e1, e2;
		e1 = new question1.Ensemble<Integer>();
		assertEquals(true, e1.add(2));
		assertEquals(true, e1.add(3));

		e2 = new question1.Ensemble<Integer>();
		assertEquals(true, e2.add(3));
		assertEquals(true, e2.add(4));

		question1.Ensemble<Integer> union = e1.union(e2);
		assertEquals(3, union.size());
		assertTrue(union.contains(2));
		assertTrue(union.contains(3));
		assertTrue(union.contains(4));
	}
	
	/**
     * Tests de la méthode Add dans la classe Ensemble.
     */
    public void testAdd() {
        question1.Ensemble<Integer> e1, e2;
        e1 = new question1.Ensemble();
        e2 = new question1.Ensemble();
        assertEquals(true, e1.add(2));
        assertEquals(true, e1.add(3));
        assertEquals(2, e1.size());

        assertEquals(true, e2.add(3));
        assertEquals(true, e2.add(4));
        assertEquals(2, e2.size());

        assertEquals(false, e1.add(2));
        assertEquals(2, e1.size());
        
        assertTrue(e1.contains(2));
        assertTrue(e1.contains(3));
        assertTrue(e2.contains(4));
         
        assertEquals(true, e1.add(6));
        assertEquals(3, e1.size());
    }

	
    /**
     * Tests de la méthode intersection dans la classe Ensemble. 
     */
    public void testInter() {
        question1.Ensemble<Integer> e1, e2;
        e1 = new question1.Ensemble();
        assertEquals(true, e1.add(2));
        assertEquals(true, e1.add(3));

        e2 = new question1.Ensemble();
        assertEquals(true, e2.add(3));
        assertEquals(true, e2.add(4));

        question1.Ensemble<Integer> inter = e1.inter(e2);
        assertEquals(1, inter.size());
        assertFalse(inter.contains(2));
        assertTrue(inter.contains(3));
        assertFalse(inter.contains(4));
    }
    /**
     * Tests de la méthode différence dans la classe Ensemble. 
     */
    public void testDiff() {
        question1.Ensemble<Integer> e1, e2;
        e1 = new question1.Ensemble();
        assertEquals(true, e1.add(2));
        assertEquals(true, e1.add(3));
        assertEquals(true, e1.add(5));

        e2 = new question1.Ensemble();
        assertEquals(true, e2.add(3));
        assertEquals(true, e2.add(4));

        question1.Ensemble<Integer> diff = e1.diff(e2);
        assertEquals(2, diff.size());
        assertTrue(diff.contains(2));
        assertFalse(diff.contains(3));
        assertFalse(diff.contains(4));
        assertTrue(diff.contains(5));
    }
    /**
     * test de la méthode différence symétrique dans la classe Ensemble. 
     */
    public void testDiffSym() {
        question1.Ensemble<Integer> e1, e2;
        e1 = new question1.Ensemble();
        assertEquals(true, e1.add(2));
        assertEquals(true, e1.add(3));

        e2 = new question1.Ensemble();
        assertEquals(true, e2.add(3));
        assertEquals(true, e2.add(4));

        question1.Ensemble<Integer> diffSym = e1.diffSym(e2);
        assertEquals(2, diffSym.size());
        assertTrue(diffSym.contains(2));
        assertFalse(diffSym.contains(3));
        assertTrue(diffSym.contains(4));
    }
}