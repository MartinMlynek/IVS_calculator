package CalculatorUtils;

import java.lang.Math;

/**
 * @brief Knihovna matematických funkci
 * @author Pavel Bobčík, xbobci03
 * @author Tomáš Daněk, xdanek23
 */
public class Utilities {

   /**
    * @brief Metoda pro sčítání
    *
    * @param first Očekává sčítanec
    * @param second Očekává sčítanec
    * @return Vrací součet
    * @throw Vrací chybu při překročení velikosti datového typu
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
    * @brief Metoda pro odčítání
    *
    * @param first Očekává menšenec
    * @param second Očekává menšitel
    * @return Vrací rozdíl
    * @throw Vrací chybu při překročení velikosti datového typu
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
    * @brief Metoda pro násobení
    *
    * @param first Očekává činitel
    * @param second Očekává činitel
    * @return Vrací součin
    * @throw Vrací chybu při překročení velikosti datového typu
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
    * @brief Metoda pro dělení
    *
    * @param first Očekává dělenec
    * @param second Očekává dělitel
    * @return Vrací podíl
    * @throw Vrací chybu při překročení velikosti datového typu a při dělení nulou
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
    * @brief Metoda pro faktoriál
    *
    * @param first Očekává číslo pro výpočet faktoriálu
    * @return Vrací výsledek výpočtu
    * @throw Vrací chybu při překročení velikosti datového typu a při záporné hodnotě
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
    * @brief Metoda pro mocninu
    *
    * @todo Záporný exponent
    * @param first Očekává číslo k umocnění
    * @param second Očekává exponent mocniny
    * @return Vrací výsledek výpočtu
    * @throw Vrací chybu při překročení velikosti datového typu, matematickou chybu při 0^0 a nepodporuje záporný exponent
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
    * @brief Metoda pro odmocninu
    *
    * @param first Očekává číslo pod odmocninou
    * @param second Očekává index odmocniny
    * @return Vrací výsledek výpočtu
    * @throw Vrací chybu při nulovém indexu nebo negativní hodnotě
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
    * @brief Metoda pro absolutní hodnotu
    *
    * @param first Očekává číslo ke zpracování
    * @return Vrací absolutní hodnotu
    * @throw Vrací chybu při překročení velikosti datového typu
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
