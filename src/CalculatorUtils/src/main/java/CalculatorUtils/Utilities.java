package CalculatorUtils;

import java.lang.Math;

/**
 *
 * @author Pavel Bobčík, xbobci03
 * @author Tomáš Daněk, xdanek23
 */
public class Utilities {
    /**
    * @param first - addend
    * @param second - addend
    * @return sum
    */
    public double add(double first, double second) throws Exception{
        double result = first + second; 

        if(result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY){
            throw new Exception("Infinity value");
        }
        
        return result;
    }
    
    /**
    * @param first - addend
    * @param second - addend
    * @return sum
    */
    public int add(int first, int second) throws Exception{
        int result = first + second; 

        if(first > 0 && second > 0 && result < 0){
            throw new Exception("Value overflow");
        }else if(first < 0 && second < 0 && result > 0){
            throw new Exception("Value underflow");
        }
        
        return result;
    }
    
    /**
    * @param first - minuend
    * @param second - subtrahend
    * @return difference
    */
    public double sub(double first, double second) throws Exception{
        double result = first - second; 

        if(result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY){
            throw new Exception("Infinity value");
        }
        
        return result;
    }
    
    /**
    * @param first - minuend
    * @param second - subtrahend
    * @return difference
    */
    public int sub(int first, int second) throws Exception{
        int result = first - second; 

        if(first > 0 && second < 0 && result < 0){
            throw new Exception("Value overflow");
        }else if(first < 0 && second > 0 && result > 0){
            throw new Exception("Value underflow");
        }
        
        return result;
    }
    
    /**
    * @param first - multiplier
    * @param second - multiplicand
    * @return product
    */
    public double mul(double first, double second) throws Exception{
        double result = first * second; 

        if(result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY){
            throw new Exception("Infinity value");
        }
        
        return result;
    }
    
    /**
    * @param first - multiplier
    * @param second - multiplicand
    * @return product
    */
    public int mul(int first, int second) throws Exception{
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
    * @param first - dividend
    * @param second - divisor
    * @result quotient
    */
    public double div(double first, double second) throws Exception{  
        if(second == 0.0){
            throw new Exception("Dividing by zero value");
        }
        
        double result = first / second;
        
        if(result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY){
            throw new Exception("Infinity value");
        }
        
        return result;
    }
    
    public int fact(int first) throws Exception{
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
    * @param first - power
    * @param second - exponent
    */
    public double exp(double first, int second) throws Exception{
        double result = 1;
        
        if(second == 0){
            if(first > 0){
                return result;
            }else{
                return -result;
            }
        }else if(second < 0){
            throw new Exception("Not supported");
        }
        
        for(int i = second; i > 0; i--){
            result = result * first;
        }
        
        if(result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY){
            throw new Exception("Infinity value");
        }
        
        return result;
    }
    
    /**
    * @param first - power
    * @param second - exponent
    */
    public int exp(int first, int second) throws Exception{
        int result = 1;
        
        if(second == 0){
            if(first > 0){
                return result;
            }else{
                return -result;
            }
        }else if(second < 0){
            throw new Exception("Not supported");
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
    
    /**
    * @param first - power
    * @param second - exponent
    */
    public double exp(int first, double second) throws Exception{
        double result = 1;
        
        if(second != 0.0){
            throw new Exception("We don't do that here.");
        }
        
        if(first > 0){
            return result;
        }else{
            return -result;
        }
    }
    
    /**
    * @param first - root
    * @param second - index of the root
    */
    public double radical(double first, int second) throws Exception{
        
        if(second == 0){
            throw new Exception("Zero root number");
        }else if(first < 0){
            throw new Exception("Negative value");
        }

        double toRound = Math.pow(first, ((double) 1 / second));
        double result = (double)Math.round(toRound * 1000d) / 1000d;
        
        return result;
    }
    
    public double abs(double first){
        if(first < 0){
            return -(first);
        }
        
        return first;
    }
    
    public int abs(int first){
        if(first < 0){
            return -(first);
        }
        
        return first;
    }
    
}
