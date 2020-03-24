import CalculatorUtils.Utilities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @authors tomalatomas     (xtomal02)
 *          Martin Mlýnek   (xmlyne06) 
 */

public class UtilitiesTest {
    private final static double EPSILON = 0.000001;

    Utilities utils;
    
    public UtilitiesTest() {
        utils = new Utilities();
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    
    @Test
    public void testAdd() {
        //Equals
        assertEquals(42, utils.add(42,0));
        assertEquals(42, utils.add(21,21));
        assertEquals(-42, utils.add(-21,-21));
        assertEquals(-543, utils.add(957,-1500));
        assertEquals(0, utils.add(0,0));
        assertEquals(-1, utils.add(-1,0));
        assertEquals(109283, utils.add(84759,24524));
        assertEquals(-109283, utils.add(-84759,-24524));
        assertEquals(-60235, utils.add(-84759,24524));
        assertEquals(0.05, utils.add(0.05,0),EPSILON);
        assertEquals(8.95, utils.add(-0.05,9),EPSILON);
        assertEquals(0.15, utils.add(0.05,0.1),EPSILON);
        assertEquals(-0.05, utils.add(0.05,-0.1),EPSILON);
        assertEquals(238.1195, utils.add(6.423,-244.5425),EPSILON);
        assertEquals(0, utils.add(244.5425,-244.5425),EPSILON);
        assertEquals(2147483646, utils.add(2147183646, 300000));

        //Doesnt equal
        assertFalse(utils.add(0, 0)==1);
        assertFalse(utils.add(0, -1)==1);
        assertFalse(utils.add(-1, 1)==1);
        assertFalse(utils.add(-1, -1)==2);
        assertFalse(109283==utils.add(-84759,-24524));
        assertFalse(0==utils.add(2147183646, 300000));
        
    }
    
    @Test
    public void testSub() {
        //Equals
        assertEquals(42, utils.sub(42,0));
        assertEquals(0, utils.sub(21,21));
        assertEquals(0, utils.sub(-21,-21));
        assertEquals(2457, utils.sub(957,-1500));
        assertEquals(0, utils.sub(0,0));
        assertEquals(-1, utils.sub(-1,0));
        assertEquals(60235, utils.sub(84759,24524));
        assertEquals(-60235, utils.sub(-84759,-24524));
        assertEquals(-109283, utils.sub(-84759,24524));
        assertEquals(0.05, utils.sub(0.05,0),EPSILON);
        assertEquals(-9.05, utils.sub(-0.05,9),EPSILON);
        assertEquals(-0.05, utils.sub(0.05,0.1),EPSILON);
        assertEquals(0.15, utils.sub(0.05,-0.1),EPSILON);
        assertEquals(250.9655, utils.sub(6.423,-244.5425),EPSILON);
        assertEquals(489.085, utils.sub(244.5425,-244.5425),EPSILON);
        assertEquals(-2147483647, utils.sub(-2147183646, 300000));
        //Doesnt equal
        assertFalse(utils.sub(0, 0)==-1);
        assertFalse(utils.sub(0, -1)==-1);
        assertFalse(utils.sub(-1, 1)==1);
        assertFalse(utils.sub(-1, -1)==2);
        assertFalse(0==utils.sub(-2147183646,300000));
    }
}
