package CalculatorUtils;

import java.lang.Math;

/**
 * \~czech @brief Knihovna matematických funkci
 * \~english @brief Math library
 *
 * @author Pavel Bobčík, xbobci03
 * @author Tomáš Daněk, xdanek23
 */
public class Utilities {

   /**
    * \~czech @brief Metoda pro sčítání
    * \~english @brief Addition
    *
    * \~czech @param first Očekává sčítanec
    * \~english @param first Expecting addend
    * \~czech @param second Očekává sčítanec
    * \~english @param second Expecting addend
    * \~czech @return Vrací součet
    * \~english @return Grand total
    * \~czech @throw Vrací chybu při překročení velikosti datového typu
    * \~english @throw Overflow error
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
    * \~czech @brief Metoda pro odčítání
    * \~english @brief Subtraction
    *
    * \~czech @param first Očekává menšenec
    * \~english @param first Expecting minuend
    * \~czech @param second Očekává menšitel
    * \~english @param second Expecting subtrahend
    * \~czech @return Vrací rozdíl
    * \~english @return Difference
    * \~czech @throw Vrací chybu při překročení velikosti datového typu
    * \~english @throw Overflow error
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
    * \~czech @brief Metoda pro násobení
    * \~english @brief Multiplication
    *
    * \~czech @param first Očekává činitel
    * \~english @param first Expecting multiplier
    * \~czech @param second Očekává činitel
    * \~english @param second Expecting multiplicand
    * \~czech @return Vrací součin
    * \~english @return Product
    * \~czech @throw Vrací chybu při překročení velikosti datového typu
    * \~english @throw Overflow error
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
    * \~czech @brief Metoda pro dělení
    * \~english @brief Division
    *
    * \~czech @param first Očekává dělenec
    * \~english param first Expecting dividend
    * \~czech @param second Očekává dělitel
    * \~english @param second Expecting divisor
    * \~czech @return Vrací podíl
    * \~english @return Quotient
    * \~czech @throw Vrací chybu při překročení velikosti datového typu a při dělení nulou
    * \~english @throw Overflow error and dividing by zero
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
    * \~czech @brief Metoda pro faktoriál
    * \~english @brief Factorial
    *
    * \~czech @param first Očekává číslo pro výpočet faktoriálu
    * \~english @param first Expecting positive number
    * \~czech @return Vrací výsledek výpočtu
    * \~english @return The result of a mathematica
    * \~czech @throw Vrací chybu při překročení velikosti datového typu a při záporné hodnotě
    * \~english @throw Overflow error and negative number
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
    * \~czech @brief Metoda pro mocninu
    * \~english @brief Power
    *
    * \~czech @todo Záporný exponent
    * \~english @todo Negative exponent
    * \~czech @param first Očekává číslo k umocnění
    * \~english @param first Expecting number under exponent
    * \~czech @param second Očekává exponent mocniny
    * \~english @param second Expecting exponent
    * \~czech @return Vrací výsledek výpočtu
    * \~english @return The result of a mathematical operation
    * \~czech @throw Vrací chybu při překročení velikosti datového typu, matematickou chybu při 0^0 a nepodporuje záporný exponent
    * \~english @throw Overflow error, math error for 0^0, and negative exponent is not supported
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

    public static double exp(int first, double second) throws Exception{
        double result = 1;
        
        if(second != 0.0){
            throw new Exception("We don't do that here.");
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
            throw new Exception("We don't do that here.");
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
    * \~czech @brief Metoda pro odmocninu
    * \~english @brief Root
    *
    * \~czech @param first Očekává číslo pod odmocninou
    * \~english @param first Expecting number under root
    * \~czech @param second Očekává index odmocniny
    * \~english @param second Expecting index of root
    * \~czech @return Vrací výsledek výpočtu
    * \~english @return The result of a mathematical operation
    * \~czech @throw Vrací chybu při nulovém indexu nebo negativní hodnotě
    * \~english @throw Zero root number and negative value under root
    */
    public static double radical(double first, int second) throws Exception{
        
        if(second == 0){
            throw new Exception("Zero root number");
        }else if(first < 0){
            throw new Exception("Negative value");
        }

        double toRound = Math.pow(first, ((double) 1 / second));
        double result = (double)Math.round(toRound * 1000d) / 1000d;
        
        return result;
    }
    
    /**
    * \~czech @brief Metoda pro absolutní hodnotu
    * \~english @brief Absolute value
    *
    * \~czech @param first Očekává číslo ke zpracování
    * \~english @param first Expecting number
    * \~czech @return Vrací absolutní hodnotu
    * \~english @return Absolute value of number
    * \~czech @throw Vrací chybu při překročení velikosti datového typu
    * \~english @throw Overflow error
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
