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
 * @author Martin Mlýnek (xmlyne06)
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
        
        Deviation d = new Deviation();
        double output = d.standardDeviation(inputValues);
        System.out.println(output);
        
    }
    
    
}
