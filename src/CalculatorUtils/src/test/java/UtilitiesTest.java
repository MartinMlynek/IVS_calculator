
import CalculatorUtils.Utilities;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @authors tomalatomas (xtomal02) 
 *          Martin Mlýnek (xmlyne06)
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
    public void testAdd() throws Exception{
        //Equals
        assertEquals(42, utils.add(42, 0));
        assertEquals(42, utils.add(21, 21));
        assertEquals(-42, utils.add(-21, -21));
        assertEquals(-543, utils.add(957, -1500));
        assertEquals(0, utils.add(0, 0));
        assertEquals(-1, utils.add(-1, 0));
        assertEquals(109283, utils.add(84759, 24524));
        assertEquals(-109283, utils.add(-84759, -24524));
        assertEquals(-60235, utils.add(-84759, 24524));
        assertEquals(0.05, utils.add(0.05, 0), EPSILON);
        assertEquals(5.05, utils.add(5, 0.05), EPSILON);
        assertEquals(0.15, utils.add(0.05, 0.1), EPSILON);
        assertEquals(-0.05, utils.add(0.05, -0.1), EPSILON);
        assertEquals(-238.1195, utils.add(6.423, -244.5425), EPSILON);
        assertEquals(0, utils.add(244.5425, -244.5425), EPSILON);
        assertEquals(2147483646, utils.add(2147183646, 300000));

        //Doesnt equal
        assertFalse(utils.add(0, 0) == 1);
        assertFalse(utils.add(0, -1) == 1);
        assertFalse(utils.add(-1, 1) == 1);
        assertFalse(utils.add(-1, -1) == 2);
        assertFalse(109283 == utils.add(-84759, -24524));
        assertFalse(0 == utils.add(2147183646, 300000));

    }

    @Test
    public void testSub() throws Exception{
        //Equals
        assertEquals(42, utils.sub(42, 0));
        assertEquals(0, utils.sub(21, 21));
        assertEquals(0, utils.sub(-21, -21));
        assertEquals(2457, utils.sub(957, -1500));
        assertEquals(0, utils.sub(0, 0));
        assertEquals(-1, utils.sub(-1, 0));
        assertEquals(60235, utils.sub(84759, 24524));
        assertEquals(-60235, utils.sub(-84759, -24524));
        assertEquals(-109283, utils.sub(-84759, 24524));
        assertEquals(0.05, utils.sub(0.05, 0), EPSILON);
        assertEquals(9.05, utils.sub(9, -0.05), EPSILON);
        assertEquals(-0.05, utils.sub(0.05, 0.1), EPSILON);
        assertEquals(0.15, utils.sub(0.05, -0.1), EPSILON);
        assertEquals(250.9655, utils.sub(6.423, -244.5425), EPSILON);
        assertEquals(489.085, utils.sub(244.5425, -244.5425), EPSILON);
        
        //Doesnt equal
        assertFalse(utils.sub(0, 0) == -1);
        assertFalse(utils.sub(0, -1) == -1);
        assertFalse(utils.sub(-1, 1) == 1);
        assertFalse(utils.sub(-1, -1) == 2);
        assertFalse(0 == utils.sub(-2147183646, 300000));
    }

    @Test
    public void testMultiply() throws Exception{
        //Equals
        assertEquals(0, utils.mul(0, 1));
        assertEquals(0, utils.mul(1, 0));
        assertEquals(0, utils.mul(0, -1));
        assertEquals(1, utils.mul(1, 1));
        assertEquals(-1, utils.mul(1, -1));
        assertEquals(-9, utils.mul(1, -9));
        assertEquals(524288, utils.mul(-1024, -512));
        assertEquals(0, utils.mul(56252, 0));
        assertEquals(1, utils.mul(2, 0.5), EPSILON);
        assertEquals(0, utils.mul(0, 0.5), EPSILON);
        assertEquals(1047, utils.mul(523.5, 2), EPSILON);
        assertEquals(0, utils.mul(523.5, 0), EPSILON);
        assertEquals(1099.35, utils.mul(523.5, 2.1), EPSILON);
        assertEquals(-1099.35, utils.mul(523.5, -2.1), EPSILON);
        assertEquals(1099.35, utils.mul(-523.5, -2.1), EPSILON);
        assertEquals(-2147183646, utils.mul(-2147183646, 1));
        assertEquals(2147183646, utils.mul(-2147183646, -1));

        //Doesnt equal
        assertFalse(0 == utils.mul(-2147183646, 1));
        assertFalse(0 == utils.mul(-2147183646, -1));
        assertFalse(utils.mul(0, 0) == 1);
        assertFalse(utils.mul(1, 0) == -1);
        assertFalse(utils.mul(56245.5, 0.5) == 0);
        assertFalse(utils.mul(0.5, 56245.5) == 0);
        assertFalse(utils.mul(1, -1) == 1);
    }

    @Test
    public void testDivide(){
        try{
            //Equals
            assertEquals(1, utils.div(42, 42));
            assertEquals(42, utils.div(42, 1));
            assertEquals(-1, utils.div(42, -42));
            assertEquals(2, utils.div(42, 21));
            assertEquals(10, utils.div(100, 10));
            assertEquals(1, utils.div(2147483646, 2147483646));
            assertEquals(2, utils.div(1, 0.5), EPSILON);
            assertEquals(-2, utils.div(1, -0.5), EPSILON);
            assertEquals(1.54, utils.div(804.496, 522.4), EPSILON);
            assertEquals(0.88, utils.div(459.712, 522.4), EPSILON);
            assertEquals(4.5, utils.div(9, 2));

            //Doesnt equal
            assertFalse(0 == utils.mul(1, 1));
            assertFalse(1 == utils.mul(1, 0.5));
            assertFalse(0 == utils.mul(1, 0.5));
            assertFalse(-2 == utils.mul(-1, -0.5));
            assertFalse(-2 == utils.mul(-1, -0.5));
        } catch (Exception ex) {
            //Throws exception
            assertThrows(ArithmeticException.class, () -> {
                utils.div(1, 0);
            });
            assertThrows(ArithmeticException.class, () -> {
                utils.div(42, 0);
            });
            assertThrows(ArithmeticException.class, () -> {
                utils.div(0.5, 0);
            });
        }
    }

    @Test
    public void testFact(){
        try{
            assertEquals(1, utils.fact(0));
            assertEquals(1, utils.fact(1));
            assertEquals(120, utils.fact(5));
            assertEquals(3628800, utils.fact(10));
            assertEquals(479001600, utils.fact(12));

            assertFalse(0 ==utils.fact(0));
        } catch (Exception ex) {
            //Throws exception
            assertThrows(IllegalArgumentException.class, () -> {
                assertEquals(1, utils.fact(-1));
            });

            assertThrows(IllegalArgumentException.class, () -> {
                assertEquals(6, utils.fact(-3));
            });
        }
    }
    
    @Test
    public void testExp(){
        try{
            //Eqauls
            assertEquals(0, utils.exp(0,1));
            assertEquals(1, utils.exp(1,0));
            assertEquals(4, utils.exp(2,2));
            assertEquals(16, utils.exp(4,2));
            assertEquals(1, utils.exp(-1,2));
            assertEquals(-1, utils.exp(-1,3));        
            assertEquals(100000, utils.exp(10,5));
            assertEquals(1, utils.exp(2,0));
            assertEquals(1, utils.exp(10,0));
            assertEquals(-1, utils.exp(-10,0));
            assertEquals(3.375,utils.exp(1.5,3));
            assertEquals(-3.375,utils.exp(-1.5,3));
            assertEquals(1,utils.exp(1.5,0));
            assertEquals(68.89,utils.exp(8.3,2), EPSILON);
            assertEquals(68.89,utils.exp(-8.3,2), EPSILON);

            //False
            assertFalse(0==utils.exp(0,0));
            assertFalse(1==utils.exp(-10,0));
            assertFalse(0 ==utils.exp(10,0));
        } catch (Exception ex) {
            //Throws
            assertThrows(IllegalArgumentException.class, () -> {
                assertEquals(1,utils.exp(5,-1));
            });

            assertThrows(IllegalArgumentException.class, () -> {
                assertEquals(1,utils.exp(5.1,-2));
            });

            assertThrows(IllegalArgumentException.class, () -> {
                assertEquals(1,utils.exp(5,2.3));
            });
        }
    }
    
    @Test
    public void testRadical(){
        try{
            //Equals
            assertEquals(1, utils.radical(1, 2), EPSILON); 
            assertEquals(utils.radical(1, 2),  utils.radical(1, 3), EPSILON);
            assertEquals(0, utils.radical(0, 2), EPSILON);
            assertEquals(0, utils.radical(0, 1), EPSILON);
            assertEquals(4, utils.radical(16, 2), EPSILON);
            assertEquals(2, utils.radical(4096, 12), EPSILON);
            assertEquals(9, utils.radical(729, 3), EPSILON);
            assertEquals(2345, utils.radical(2345, 1), EPSILON);


            //False
            assertFalse(-6 == utils.radical(25, 42));
            assertFalse(0 == utils.radical(25, 2));
            assertFalse(-4 == utils.radical(64, 3));
        } catch (Exception ex) {
            
        //Throws
            assertThrows(ArithmeticException.class, () -> {
                utils.radical(20, -5);
            });
            assertThrows(ArithmeticException.class, () -> {
                utils.radical(1, -3);
            });
            assertThrows(ArithmeticException.class, () -> {
                utils.radical(-4, 2);
            });
            assertThrows(ArithmeticException.class, () -> {
                utils.radical(-625, 4);
            });
        }
    }
    
    @Test
    public void testAbs(){
        try{
            //Equals
            assertEquals(1, utils.abs(1));
            assertEquals(5, utils.abs(5));
            assertEquals(4, utils.abs(-4));
            assertEquals(12, utils.abs(-12));
            assertEquals(10, utils.abs(10));
            assertEquals(250015564, utils.abs(250015564));
            assertEquals(utils.abs(2335), utils.abs(-2335));
            assertEquals(0, utils.abs(0));
            assertEquals(135.54, utils.abs(135.54));
            assertEquals(0.000002, utils.abs(0.000002));

            //Not equals
            assertNotEquals(1203, utils.abs(1202.5));
            assertNotEquals(-33, utils.abs(-33));
            assertNotEquals(-656, utils.abs(123));

            //True
            assertTrue(2 == utils.abs(2));
            assertTrue(utils.abs(-2445.2345) == utils.abs(2445.2345));
            assertTrue(25322.15454 == utils.abs(-25322.15454));
        }catch (Exception ex){
            //Throws
            assertThrows(ArithmeticException.class, () -> {
            assertEquals(1,utils.abs(888888888));
            });
        }
    }

}