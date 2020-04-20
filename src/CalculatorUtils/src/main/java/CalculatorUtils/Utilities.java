package CalculatorUtils;

import java.lang.Math;

/**
 * Math library
 *
 * @author Pavel Bobčík, xbobci03
 * @author Tomáš Daněk, xdanek23
 */
public class Utilities {
   /**
    * Addition
    *
    * @param first Expecting addend
    * @param second Expecting addend
    * @return Grand total
    * @throw Overflow error
    */
    public static double add(double first, double second) throws Exception{
        double result = first + second; 

        if(result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY){
            throw new Exception("Infinity value");
        }
        
        return result;
    }
    
    public static int add(int first, int second) throws Exception{
        int result = first + second; 

        if(first > 0 && second > 0 && result < 0){
            throw new Exception("Value overflow");
        }else if(first < 0 && second < 0 && result > 0){
            throw new Exception("Value underflow");
        }
        
        return result;
    }
    
    /**
    * Subtraction
    *
    * @param first Expecting minuend
    * @param second Expecting subtrahend
    * @return Difference
    * @throw Overflow error
    */
    public static double sub(double first, double second) throws Exception{
        double result = first - second; 

        if(result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY){
            throw new Exception("Infinity value");
        }
        
        return result;
    }
    
    public static int sub(int first, int second) throws Exception{
        int result = first - second; 

        if(first > 0 && second < 0 && result < 0){
            throw new Exception("Value overflow");
        }else if(first < 0 && second > 0 && result > 0){
            throw new Exception("Value underflow");
        }
        
        return result;
    }
    

    /**
    * Multiplication
    *
    * @param first Expecting multiplier
    * @param second Expecting multiplicand
    * @return Product
    * @throw Overflow error
    */

    public static double mul(double first, double second) throws Exception{
        double result = first * second; 

        if(result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY){
            throw new Exception("Infinity value");
        }
        
        return result;
    }
    
    public static int mul(int first, int second) throws Exception{
        int result = first * second; 

        if(first > 0 && second > 0 && result < 0){
            throw new Exception("Value overflow");
        }else if(first < 0 && second < 0 && result < 0){
            throw new Exception("Value overflow");
        }else if(first < 0 && second > 0 && result > 0){
            throw new Exception("Value underflow");
        }else if(first > 0 && second < 0 && result > 0){
            throw new Exception("Value underflow");
        }
        
        return result;
    }
    
    /**
    * Division
    *
    * param first Expecting dividend
    * @param second Expecting divisor
    * @return Quotient
    * @throw Overflow error and dividing by zero
    */
    public static double div(double first, double second) throws Exception{  
        if(second == 0.0){
            throw new Exception("Dividing by zero value");
        }
        
        double result = first / second;
        
        if(result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY){
            throw new Exception("Infinity value");
        }
        
        return result;
    }
    
    public static double div(int first, int second) throws Exception{  
        if(second == 0){
            throw new Exception("Dividing by zero value");
        }
        
        double result = first / second;
        
        if(result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY){
            throw new Exception("Infinity value");
        }
        
        return result;
    }

    /**
    * Factorial
    *
    * @param first Expecting positive number
    * @return The result of a mathematica
    * @throw Overflow error and negative number
    */

    public static int fact(int first) throws Exception{
        if(first < 0){
            throw new Exception("Value under zero");
        }else if(first > 12){
            throw new Exception("Value overflow");
        }
        
        int result = 1;
        for(int i = first; i > 1; i--){
            result = result * i;
        }
        
        return result;
    }
    
    
    /**
    * Power
    *
    * @todo Negative exponent
    * @param first Expecting number under exponent
    * @param second Expecting exponent
    * @return The result of a mathematical operation
    * @throw Overflow error, math error for 0^0, and negative exponent is not supported
    */
    public static double exp(double first, int second) throws Exception{
        double result = 1;
        
        if(second == 0){
            if(first == 0.0){
                throw new Exception("Math error");
            }else if(first > 0){
                return result;
            }else{
                return -result;
            }
        }else if(second < 0){
            throw new IllegalArgumentException("Not supported");
        }
        
        for(int i = second; i > 0; i--){
            result = result * first;
        }
        
        if(result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY){
            throw new Exception("Infinity value");
        }
        
        return result;
    }


    public static int exp(int first, int second) throws Exception{
        int result = 1;
        
        if(second == 0){
            if(first == 0){
                throw new Exception("Math error");
            }else if(first > 0){
                return result;
            }else{
                return -result;
            }
        }else if(second < 0){
            throw new IllegalArgumentException("Not supported");
        }
        
        int tmpPrevStep=first;
        int tmpDivide=1;
        for(int i = second; i > 0; i--){
            result = result * first;
            if((result / tmpDivide) != tmpPrevStep){
                throw new Exception("Value overflow");
            }
            tmpDivide=first;
            tmpPrevStep = result;
        }
        
        return result;
    }

    public static double exp(int first, double second) throws Exception{
        double result = 1;
        
        if(second != 0.0){
            throw new IllegalArgumentException("We don't do that here.");
        }
        
        if(first == 0){
                throw new Exception("Math error");
        }else if(first > 0){
            return result;
        }else{
            return -result;
        }
    }
   
    public static double exp(double first, double second) throws Exception{

        double result = 1;
        
        if(second != 0.0){
            throw new IllegalArgumentException("We don't do that here.");
        }
        
        if(first == 0.0){
                throw new Exception("Math error");
        }else if(first > 0){
            return result;
        }else{
            return -result;
        }
    }
    
    /**
    * Root
    *
    * @param first Expecting number under root
    * @param second Expecting index of root
    * @return The result of a mathematical operation
    * @throw Zero root number and negative value under root
    */
    public static double radical(double first, int second) throws Exception{
        
        if(second == 0){
            throw new Exception("Zero root number");
        }else if(first < 0){
            throw new Exception("Negative value");
        }

        double toRound = Math.pow(first, ((double) 1 / second));
        double result = (double)Math.round(toRound * 1000000000d) / 1000000000d;
        
        return result;
    }
    
    /**
    * Absolute value
    *
    * @param first Expecting number
    * @return Absolute value of number
    * @throw Overflow error
    */
    public static double abs(double first) throws Exception{
       
       if(first == Double.POSITIVE_INFINITY || first == Double.NEGATIVE_INFINITY){
            throw new Exception("Infinity value");
        } 
       
       if(first < 0){

            return -(first);
        }
        
        return first;
    }
    
    public static int abs(int first) throws Exception{
       
       if(first > Integer.MAX_VALUE || first < Integer.MIN_VALUE){
            throw new Exception("Value overflow/underflow");
        }
       

        if(first < 0){
            return -(first);
        }
        
        return first;
    }
    
}
