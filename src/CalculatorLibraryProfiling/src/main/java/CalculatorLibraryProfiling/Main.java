/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculatorLibraryProfiling;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Martin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        int a = 2;
        int b = 4;

        int add = CalculatorUtils.Utilities.add(a, b);

        ArrayList<Double> inputValues = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            double actualLine = Double.parseDouble(input.nextLine());
            inputValues.add(actualLine);
            
        }
    }
    
    public double average(ArrayList<Double> arr) throws Exception {
        double sum = this.sum(arr);
        int size = arr.size();
        double result;
     
        
        
        result = CalculatorUtils.Utilities.div(sum, size);

        
        return result;
    }
    
    public double sum(ArrayList<Double> arr) throws Exception {
    
        double sumResult = 0;
   
        for (double i : arr) {
            
            sumResult = CalculatorUtils.Utilities.add(sumResult, i);
            
        }
       return sumResult;
    }
    
    
    public void standardDeviation(ArrayList<Double> arr) throws Exception {
        double size = arr.size();
        double sizeMinusOne = CalculatorUtils.Utilities.sub(size, 1);
        
        double sum = this.sum(arr);
        double sumExp = CalculatorUtils.Utilities.exp(sum, 2);
        
        double average = this.average(arr);
        double averageExp = CalculatorUtils.Utilities.exp(average, 2);
        
        double bracketSub = CalculatorUtils.Utilities.sub(sumExp, averageExp);
        
        double underSqrtResult = CalculatorUtils.Utilities.div(bracketSub, sizeMinusOne);
        
        double result = CalculatorUtils.Utilities.radical(underSqrtResult, 2);
        
    }
    
}
