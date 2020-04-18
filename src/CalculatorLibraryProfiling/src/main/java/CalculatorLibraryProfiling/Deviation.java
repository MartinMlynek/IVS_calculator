/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculatorLibraryProfiling;

import java.util.ArrayList;

/**
 *
 * @author Martin Mlýnek
 */
public class Deviation {
    /**
     * Calculate average
     * @param arr - ArrayList which is used to calculate his average 
     * @return average of arrayList values
     * @throws Exception 
     */
    public double average(ArrayList<Double> arr) throws Exception {
        double sum = this.sum(arr);
        int size = arr.size();
        double result;
     
        
        
        result = CalculatorUtils.Utilities.div(sum, size);

        
        return result;
    }
    /**
     * Calculate summarization
     * @param arr - ArrayList which is used to summarize his values
     * @return - sum
     * @throws Exception 
     */
    public double sum(ArrayList<Double> arr) throws Exception {
    
        double sumResult = 0;
   
        for (double i : arr) {
            
            sumResult = CalculatorUtils.Utilities.add(sumResult, i);
            
        }
       return sumResult;
    }
    /**
     * Square values of arrayList
     * @param arr - ArrayList which is used to square values
     * @return arraylist with squared values
     * @throws Exception 
     */
    public ArrayList<Double> squareArrayLIst(ArrayList<Double> arr) throws Exception {
        
        ArrayList<Double> expTwo = new ArrayList<>();
        
        for (double i: arr)
        {
          expTwo.add(CalculatorUtils.Utilities.exp(i, 2));  
        }
        
        return expTwo;
    }
    
    /**
     * Calculate standard deviation
     * @param arr - ArrayList used to standard deviations calculation
     * @throws Exception 
     */
    public double standardDeviation(ArrayList<Double> arr) throws Exception {
        double size = arr.size();
        double sizeMinusOne = CalculatorUtils.Utilities.sub(size, 1);
        ArrayList<Double> expTwo = this.squareArrayLIst(arr);
        double sum = this.sum(expTwo);
        
        double average = this.average(arr);
        double averageExp = CalculatorUtils.Utilities.exp(average, 2);
        
        double bracketSub = CalculatorUtils.Utilities.sub(sum, averageExp);
        
        double underSqrtResult = CalculatorUtils.Utilities.div(bracketSub, sizeMinusOne);
        
        double result = CalculatorUtils.Utilities.radical(underSqrtResult, 2);
        return result;
    }
    
}
