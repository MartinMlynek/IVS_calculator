package Calculator;

import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Display extends javax.swing.JFrame {

    /**
     * @author Pavel Bobƒç√≠k, xbobci03
     * @author tomalatomas, xtomal02
     */
    public Display() {
        initComponents();
        this.setTitle("OAGUH Calculator");
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menuHelp = new JMenu("N√°povƒõda");
        
        menuHelp.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                int sizeOfArray = getHelpArray().size();
                int indexOfFirst = 0;
                Help help = new Help();
                help.setHelp(getHelpArray(), sizeOfArray);
                help.showHelp(indexOfFirst);
                help.setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });
        
        this.setJMenuBar(menuBar);
        menuBar.add(menuHelp);
    }
    /**
    *@name setEquation
    * \~english @brief Sets equation into the display of the calculator
    * \~english @param equation
    * \~czech @brief NastavÌ rovnici do displeje kalkulaËky
    * \~czech @param rovnice 
    */
    public void setEquation(String equation){
        this.tvDisplay.setText(equation);
    }
    /**
    *@name getOperatorPriority
    *@brief Returns the priority of operator in form of number
    *@param operator
    *@return priority
    */
    private int getOperatorPriority(char operator) {
        switch (operator) {
            case '!':
                return 1;
            case '/':
                return 2;
            case '*':
                return 2;
            case '+':
                return 3;
            case '$':
                return 3;
            default:
                return 0;
        }
    }
    
     /**
    *name solveFactorials
    * \~english @brief Finds every factorial in equation and calls method solveOperator. 
    * \~english @param equation
    * \~english @return equation Returns equation with solved factorials.
    * \~czech @brief Najde kaûd˝ faktori·l a spustÌ metodu solveOperator. 
    * \~czech @param rovnice 
    * \~czech @return void Vr·tÌ rovnici s vypoËÌtan˝mi faktori·ly.
    */
    private String solveFactorials(String equation) {
        for (int i = 0; i < equation.length(); i++) {
            char o = equation.charAt(i);
            if (this.getOperatorPriority(o) != 0) {
                switch (this.getOperatorPriority(o)) {
                    case 1:
                        equation = solveOperator(equation, i);
                }
            }
        }
        return equation;
    }
     /**
    *@name solveOperators
    * \~english @brief Finds every operator in equation and calls method solveOperator. 
    *@param equation
    * \~english @return equation Return equation with solved operators.
    * \~czech @brief Najde kaûd˝ oper·tor a zavol· metodu solveOperator.
    * \~czech @return equation Vr·tÌ rovnici s vypoËÌtan˝mi oper·tory.
    */
    private String solveOperators(String equation) {
        for (int i = 0; i < equation.length(); i++) {
            char o = equation.charAt(i);
            if (this.getOperatorPriority(o) != 0) {
                switch (this.getOperatorPriority(o)) {
                    case 2:
                        equation = solveOperator(equation, i);
                }
            }
        }

        for (int i = 0; i < equation.length(); i++) {
            char o = equation.charAt(i);
            if (this.getOperatorPriority(o) != 0) {
                switch (this.getOperatorPriority(o)) {
                    case 3:
                        equation = solveOperator(equation, i);
                }
            }
        }
        return equation;

    }
    /**
    *@name checkDoubleOperators
    * \~english @brief Checks equation for operators next to each other.
    * \~czech @brief ZjistÌ, jestli jsou v rovnici dva oper·tory vedle sebe. 
    *@param equation
    *@return boolean
    */
    private boolean checkDoubleOperators(String equation) {
        for (int i = 0; i < equation.length() - 1; i++) {
            if (equation.charAt(i) == equation.charAt(i + 1)) {
                return true;
            }
        }
        return false;

    }
    /**
    *@name checkInvalidChars
    * \~english @brief Checks equation for invalid characters specified in method.
    * \~english @brief ZjistÌ, jestli jsou v rovnici nepovolenÈ znaky
    *@param equation
    *@return boolean
    */
    public boolean checkInvalidChars(String equation) {
        final String validChars = "0123456789+-*/!.$";
        for (int i = 0; i < equation.length(); i++) {
            if (!validChars.contains(Character.toString(equation.charAt(i)))) {
                return true;
            }
        }
        return false;
    }

    /**
    *@name getStringNumberBeforeOper
    *@param equation
    * \~english @param operIndex index of the operator in the equation
    * \~czech @param operIndex index oper·toru v rovnici
    * \~english @return number number before the operator stored in String
    * \~czech @return number ËÌslo p¯ed oper·torem 
    */
    private String getStringNumberBeforeOper(String equation, int operIndex) {
        String number = "";
        for (int i = operIndex - 1; i >= 0; i--) {
            if (getOperatorPriority(equation.charAt(i)) == 0) {
                number = equation.charAt(i) + number;
            } else {
                return number;
            }
        }
        return number;
    }
     /**
    *@name getStringNumberAfterOper
    *@param equation
    * \~english @param operIndex index of the operator in the equation
    * \~czech @param operIndex index oper·toru v rovnici
    * \~english @return number number after the operator stored in String
    * \~czech @return number ËÌslo za oper·torem 

    */
    private String getStringNumberAfterOper(String equation, int operIndex) {
        String number = "";
        for (int i = operIndex + 1; i < equation.length(); i++) {
            if (getOperatorPriority(equation.charAt(i)) == 0) {
                number = number + equation.charAt(i);
            } else {
                return number;
            }
        }
        return number;
    }
    
     /**
    *@name calculateOperator
    * \~english @brief Calls appropriate library method CalculatorUtils 
    * \~czech @brief Vol· spr·vnou metodu z knihovny CalculatorUtils
    *@param equation
    * \~english @param operIndex index of the operator in the equation
    * \~czech @param operIndex index oper·toru v rovnici
    * \~english @return solvedOperator solved subequation
    * \~czech @return solvedOperator V˝sledek oper·toru
    */
    private String calculateOperator(String equation, int operIndex) throws Exception {
        //Method check the operator, calls the appropriate library method and returns the number in String
        String numberBef = getStringNumberBeforeOper(equation, operIndex);
        String numberAft = getStringNumberAfterOper(equation, operIndex);
        switch (equation.charAt(operIndex)) {
            case '!':
                if (numberBef.contains(Character.toString('.'))) {

                    return "" + CalculatorUtils.Utilities.fact((int) Double.parseDouble(numberBef));
                } else {
                    return "" + CalculatorUtils.Utilities.fact(Integer.parseInt(numberBef));
                }
            case '/':
                if (numberBef.contains(Character.toString('.')) || numberAft.contains(Character.toString('.'))) {
                    return "" + CalculatorUtils.Utilities.div(Double.parseDouble(numberBef), Double.parseDouble(numberAft));
                } else {
                    return "" + CalculatorUtils.Utilities.div(Integer.parseInt(numberBef), Integer.parseInt(numberAft));
                }

            case '*':
                if (numberBef.contains(Character.toString('.')) || numberAft.contains(Character.toString('.'))) {
                    return "" + CalculatorUtils.Utilities.mul(Double.parseDouble(numberBef), Double.parseDouble(numberAft));
                } else {
                    return "" + CalculatorUtils.Utilities.mul(Integer.parseInt(numberBef), Integer.parseInt(numberAft));
                }

            case '+':
                if (numberBef.contains(Character.toString('.')) || numberAft.contains(Character.toString('.'))) {
                    return "" + CalculatorUtils.Utilities.add(Double.parseDouble(numberBef), Double.parseDouble(numberAft));
                } else {
                    return "" + CalculatorUtils.Utilities.add(Integer.parseInt(numberBef), Integer.parseInt(numberAft));
                }

            case '$':
                if (numberBef.contains(Character.toString('.')) || numberAft.contains(Character.toString('.'))) {
                    return "" + CalculatorUtils.Utilities.sub(Double.parseDouble(numberBef), Double.parseDouble(numberAft));
                } else {
                    return "" + CalculatorUtils.Utilities.sub(Integer.parseInt(numberBef), Integer.parseInt(numberAft));
                }
            default:
                return "";
        }

    }
     /**
    *@name solveOperator
    * \~english @brief Method gets number for the operator, solves subequation and replaces the subequation with result
    * \~czech @brief Metoda zjistÌ ËÌsla pro v˝poËet oper·toru, vypoËÌt· subrovnici a nahradÌ subrovnici v˝sledkem
    *@param equation
    *@param operIndex
    * \~english @return equation Equation with solved subEquation 
    * \~czech @return equation Rovnice s vypoËÌtan˝m oper·torem
    */ 
    private String solveOperator(String equation, int operIndex) {
        //Method replaces the operator and its arguments with a result
        String subEquation = "";
        //System.out.println("operator:"+operIndex+",----------Equation:"+equation);
        //System.out.println("operator:"+operIndex+",subEquation:"+subEquation);

        if (equation.charAt(operIndex) == '!') {
            subEquation = getStringNumberBeforeOper(equation, operIndex) + equation.charAt(operIndex);
        } else {
            subEquation = getStringNumberBeforeOper(equation, operIndex) + equation.charAt(operIndex) + getStringNumberAfterOper(equation, operIndex);
        }
        String solvedSubEquation;
        try {
            solvedSubEquation = calculateOperator(equation, operIndex);
        //    System.out.println("operator:"+operIndex+",solvedSubEquation:"+solvedSubEquation);
            equation = equation.replace(subEquation, solvedSubEquation);
        //    System.out.println("operator:"+operIndex+",Equation after:"+subEquation);
            return equation;
        } catch (Exception ex) {
            return "Error";
        }
    }
     /**
    *@name findOperator
    * \~english @brief Checks if equation contains any operator
    * \~czech @brief ZjistÌ, jestli rovnice obsahuje oper·tor
    *@param equation
    * \~english @return boolean True if equation contains any operator
    * \~czech @return boolean  navr·tÌ true pokud rovnice obsahuje nÏjak˝ oper·tor
    */
    private boolean findOperator(String equation) {
        for (int i = 0; i < equation.length(); i++) {
            char o = equation.charAt(i);
            if (this.getOperatorPriority(o) == 3 || this.getOperatorPriority(o) == 2) {
                return true;
            }
        }
        return false;
    }
     /**
    *@name checkNegativeEquation
    * \~english @brief Checks if equation starts with minus
    * \~czech @brief ZjistÌ, jestli rovnice zaËÌn· mÌnusem
    *@param equation
    * \~english  @return boolean returns true if equation starts with minus
    * \~czech  @return boolean navr·tÌ true pokud rovnice zaËÌn· mÌnusem

    */
    private boolean checkNegativeEquation(String equation) {
        if (equation.charAt(0) == '-') {
            return true;
        }
        return false;
    }
     /**
    *@name checkInvalidEquation
    * \~english @brief Sets error message into the display if error occurs
    * \~czech @brief Do displeje kalkulaËky nastavÌ chybovÈ hl·öenÌ, pokud nÏjakÈ nastane
    *@param equation
    *@return void
    */
    private void checkInvalidEquation(String equation) {
         if (checkDoubleOperators(equation)) {
            tvDisplay.setText("Error:Stacked operators");
        } else if (checkInvalidChars(equation)) {
            tvDisplay.setText("Error:Invalid characters");
        }
    }
     /**
    *@name replaceMinuses
    * \~english @brief Replaces all the minuses with another symbol to prevent confusion between operator and negative numbers.
    * \~czech @brief  NahradÌ vöechny oper·tory mÌnus jin˝m znakem, aby nedoölo k z·mÏnÏ oper·toru a negativnÌho ËÌsla
    *@param equation
    *@ \~english return equation modified equation
    *@ \~czech return equation upraven· rovnice
    */
     private String replaceMinuses(String equation) {
        equation = equation.replace('-', '$'); //Replaces every minus to prevent confusion between negative number and operator
        return equation;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnRoot = new javax.swing.JButton();
        btnPower = new javax.swing.JButton();
        btnEight = new javax.swing.JButton();
        btnNine = new javax.swing.JButton();
        btnSeven = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnFive = new javax.swing.JButton();
        btnSix = new javax.swing.JButton();
        btnFour = new javax.swing.JButton();
        btnDivision = new javax.swing.JButton();
        btnTimes = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnPlus = new javax.swing.JButton();
        btnMinus = new javax.swing.JButton();
        btnTwo = new javax.swing.JButton();
        btnThree = new javax.swing.JButton();
        btnOne = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnZero = new javax.swing.JButton();
        btnDot = new javax.swing.JButton();
        btnEquals = new javax.swing.JButton();
        tvDisplay = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        btnDeleteAll = new javax.swing.JButton();
        btnDeleteLast = new javax.swing.JButton();
        btnABS = new javax.swing.JButton();
        btnFact = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(200, 200, 200));

        jPanel2.setBackground(new java.awt.Color(200, 200, 200));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnRoot.setBackground(new java.awt.Color(0, 0, 0));
        btnRoot.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRoot.setForeground(new java.awt.Color(240, 240, 240));
        btnRoot.setText("‚?ö");
        btnRoot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRootActionPerformed(evt);
            }
        });

        btnPower.setBackground(new java.awt.Color(0, 0, 0));
        btnPower.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnPower.setForeground(new java.awt.Color(240, 240, 240));
        btnPower.setText("^");
        btnPower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPowerActionPerformed(evt);
            }
        });

        btnEight.setBackground(new java.awt.Color(75, 75, 75));
        btnEight.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEight.setForeground(new java.awt.Color(240, 240, 240));
        btnEight.setText("8");
        btnEight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEightActionPerformed(evt);
            }
        });

        btnNine.setBackground(new java.awt.Color(75, 75, 75));
        btnNine.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNine.setForeground(new java.awt.Color(240, 240, 240));
        btnNine.setText("9");
        btnNine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNineActionPerformed(evt);
            }
        });

        btnSeven.setBackground(new java.awt.Color(75, 75, 75));
        btnSeven.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSeven.setForeground(new java.awt.Color(240, 240, 240));
        btnSeven.setText("7");
        btnSeven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSevenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSeven, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEight, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNine, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRoot, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPower, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRoot, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPower, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEight, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNine, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeven, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(200, 200, 200));

        btnFive.setBackground(new java.awt.Color(75, 75, 75));
        btnFive.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnFive.setForeground(new java.awt.Color(240, 240, 240));
        btnFive.setText("5");
        btnFive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiveActionPerformed(evt);
            }
        });

        btnSix.setBackground(new java.awt.Color(75, 75, 75));
        btnSix.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSix.setForeground(new java.awt.Color(240, 240, 240));
        btnSix.setText("6");
        btnSix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSixActionPerformed(evt);
            }
        });

        btnFour.setBackground(new java.awt.Color(75, 75, 75));
        btnFour.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnFour.setForeground(new java.awt.Color(240, 240, 240));
        btnFour.setText("4");
        btnFour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFourActionPerformed(evt);
            }
        });

        btnDivision.setBackground(new java.awt.Color(0, 0, 0));
        btnDivision.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDivision.setForeground(new java.awt.Color(240, 240, 240));
        btnDivision.setText("/");
        btnDivision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDivisionActionPerformed(evt);
            }
        });

        btnTimes.setBackground(new java.awt.Color(0, 0, 0));
        btnTimes.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnTimes.setForeground(new java.awt.Color(240, 240, 240));
        btnTimes.setText("*");
        btnTimes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFour, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFive, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSix, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimes, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDivision, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFour, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFive, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSix, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDivision, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(200, 200, 200));

        btnPlus.setBackground(new java.awt.Color(0, 0, 0));
        btnPlus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnPlus.setForeground(new java.awt.Color(240, 240, 240));
        btnPlus.setText("+");
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusActionPerformed(evt);
            }
        });

        btnMinus.setBackground(new java.awt.Color(0, 0, 0));
        btnMinus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnMinus.setForeground(new java.awt.Color(240, 240, 240));
        btnMinus.setText("-");
        btnMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusActionPerformed(evt);
            }
        });

        btnTwo.setBackground(new java.awt.Color(75, 75, 75));
        btnTwo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnTwo.setForeground(new java.awt.Color(240, 240, 240));
        btnTwo.setText("2");
        btnTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTwoActionPerformed(evt);
            }
        });

        btnThree.setBackground(new java.awt.Color(75, 75, 75));
        btnThree.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnThree.setForeground(new java.awt.Color(240, 240, 240));
        btnThree.setText("3");
        btnThree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThreeActionPerformed(evt);
            }
        });

        btnOne.setBackground(new java.awt.Color(75, 75, 75));
        btnOne.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnOne.setForeground(new java.awt.Color(240, 240, 240));
        btnOne.setText("1");
        btnOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOne, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThree, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThree, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOne, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(200, 200, 200));

        btnZero.setBackground(new java.awt.Color(75, 75, 75));
        btnZero.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnZero.setForeground(new java.awt.Color(240, 240, 240));
        btnZero.setText("0");
        btnZero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZeroActionPerformed(evt);
            }
        });

        btnDot.setBackground(new java.awt.Color(0, 0, 0));
        btnDot.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDot.setForeground(new java.awt.Color(240, 240, 240));
        btnDot.setText(".");
        btnDot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDotActionPerformed(evt);
            }
        });

        btnEquals.setBackground(new java.awt.Color(0, 0, 0));
        btnEquals.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEquals.setForeground(new java.awt.Color(240, 240, 240));
        btnEquals.setText("=");
        btnEquals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEqualsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDot, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnZero, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnEquals, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDot, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnZero, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEquals, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tvDisplay.setEditable(false);
        tvDisplay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tvDisplay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jPanel10.setBackground(new java.awt.Color(200, 200, 200));
        jPanel10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnDeleteAll.setBackground(new java.awt.Color(0, 0, 0));
        btnDeleteAll.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDeleteAll.setForeground(new java.awt.Color(230, 76, 0));
        btnDeleteAll.setText("CE");
        btnDeleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAllActionPerformed(evt);
            }
        });

        btnDeleteLast.setBackground(new java.awt.Color(0, 0, 0));
        btnDeleteLast.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDeleteLast.setForeground(new java.awt.Color(230, 76, 0));
        btnDeleteLast.setText("C");
        btnDeleteLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteLastActionPerformed(evt);
            }
        });

        btnABS.setBackground(new java.awt.Color(0, 0, 0));
        btnABS.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnABS.setForeground(new java.awt.Color(240, 240, 240));
        btnABS.setText("ABS");
        btnABS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnABSActionPerformed(evt);
            }
        });

        btnFact.setBackground(new java.awt.Color(0, 0, 0));
        btnFact.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnFact.setForeground(new java.awt.Color(240, 240, 240));
        btnFact.setText("!");
        btnFact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFactActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDeleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteLast, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(btnABS, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFact, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteLast, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnABS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFact, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tvDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tvDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAllActionPerformed
        tvDisplay.setText("");
    }//GEN-LAST:event_btnDeleteAllActionPerformed

    private void btnDeleteLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteLastActionPerformed
        if (tvDisplay.getText().length() != 0) {
            tvDisplay.setText("" + tvDisplay.getText().substring(0, tvDisplay.getText().length() - 1));
        }
    }//GEN-LAST:event_btnDeleteLastActionPerformed

    private void btnFiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiveActionPerformed
        if (checkInvalidChars(tvDisplay.getText())) {
            tvDisplay.setText("");
        }
        tvDisplay.setText(tvDisplay.getText() + "5");
    }//GEN-LAST:event_btnFiveActionPerformed

    private void btnEightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEightActionPerformed
        if (checkInvalidChars(tvDisplay.getText())) {
            tvDisplay.setText("");
        }
        tvDisplay.setText(tvDisplay.getText() + "8");
    }//GEN-LAST:event_btnEightActionPerformed

    private void btnFactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFactActionPerformed
        if (checkInvalidChars(tvDisplay.getText())) {
            tvDisplay.setText("");
        }
        tvDisplay.setText(tvDisplay.getText() + "!");
    }//GEN-LAST:event_btnFactActionPerformed

    private void btnPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusActionPerformed
         if (checkInvalidChars(tvDisplay.getText())) {
            tvDisplay.setText("");
        }tvDisplay.setText(tvDisplay.getText() + "+");
    }//GEN-LAST:event_btnPlusActionPerformed

    private void btnZeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZeroActionPerformed
         if (checkInvalidChars(tvDisplay.getText())) {
            tvDisplay.setText("");
        }
        tvDisplay.setText(tvDisplay.getText() + "0");
    }//GEN-LAST:event_btnZeroActionPerformed

    private void btnMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusActionPerformed
         if (checkInvalidChars(tvDisplay.getText())) {
            tvDisplay.setText("");
        }
        tvDisplay.setText(tvDisplay.getText() + "-");
    }//GEN-LAST:event_btnMinusActionPerformed

    private void btnABSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnABSActionPerformed
        btnEqualsActionPerformed(evt);
        if (tvDisplay.getText().contains(Character.toString('.'))) {
            tvDisplay.setText("" + CalculatorUtils.Utilities.abs(Double.parseDouble(tvDisplay.getText())));

        } else {
            tvDisplay.setText("" + CalculatorUtils.Utilities.abs(Integer.parseInt(tvDisplay.getText())));
        }
    }//GEN-LAST:event_btnABSActionPerformed

    private void btnOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOneActionPerformed
         if (checkInvalidChars(tvDisplay.getText())) {
            tvDisplay.setText("");
        }
        tvDisplay.setText(tvDisplay.getText() + "1");
    }//GEN-LAST:event_btnOneActionPerformed

    private void btnTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTwoActionPerformed
         if (checkInvalidChars(tvDisplay.getText())) {
            tvDisplay.setText("");
        }
        tvDisplay.setText(tvDisplay.getText() + "2");
    }//GEN-LAST:event_btnTwoActionPerformed

    private void btnThreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThreeActionPerformed
         if (checkInvalidChars(tvDisplay.getText())) {
            tvDisplay.setText("");
        }
        tvDisplay.setText(tvDisplay.getText() + "3");
    }//GEN-LAST:event_btnThreeActionPerformed

    private void btnFourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFourActionPerformed
         if (checkInvalidChars(tvDisplay.getText())) {
            tvDisplay.setText("");
        }
        tvDisplay.setText(tvDisplay.getText() + "4");
    }//GEN-LAST:event_btnFourActionPerformed

    private void btnSixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSixActionPerformed
         if (checkInvalidChars(tvDisplay.getText())) {
            tvDisplay.setText("");
        }
        tvDisplay.setText(tvDisplay.getText() + "6");
    }//GEN-LAST:event_btnSixActionPerformed

    private void btnSevenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSevenActionPerformed
         if (checkInvalidChars(tvDisplay.getText())) {
            tvDisplay.setText("");
        }
         tvDisplay.setText(tvDisplay.getText() + "7");
    }//GEN-LAST:event_btnSevenActionPerformed

    private void btnNineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNineActionPerformed
         if (checkInvalidChars(tvDisplay.getText())) {
            tvDisplay.setText("");
        }
        tvDisplay.setText(tvDisplay.getText() + "9");
    }//GEN-LAST:event_btnNineActionPerformed

    private void btnTimesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimesActionPerformed
         if (checkInvalidChars(tvDisplay.getText())) {
            tvDisplay.setText("");
        }
        tvDisplay.setText(tvDisplay.getText() + "*");
    }//GEN-LAST:event_btnTimesActionPerformed

    private void btnDivisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDivisionActionPerformed
        if (checkInvalidChars(tvDisplay.getText())) {
            tvDisplay.setText("");
        } 
        tvDisplay.setText(tvDisplay.getText() + "/");

    }//GEN-LAST:event_btnDivisionActionPerformed

    private void btnPowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPowerActionPerformed
        btnEqualsActionPerformed(evt);
        if(!checkInvalidChars(tvDisplay.getText())){
        Power pwr = new Power();
        pwr.setLocation(this.getLocation());
        pwr.setEquation(tvDisplay.getText());
        pwr.setVisible(true);
        this.dispose();
        }
    }//GEN-LAST:event_btnPowerActionPerformed

    private void btnEqualsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEqualsActionPerformed
        String equation = tvDisplay.getText();
        if (equation.equalsIgnoreCase("")) {
            tvDisplay.setText("No equation to be solved");
        } else{
        if (checkNegativeEquation(equation)) {
            equation = "0" + equation;
        }
        equation=replaceMinuses(equation);
        checkInvalidEquation(equation);
        equation = solveFactorials(equation);
        
        while (findOperator(equation)) {
            equation = solveOperators(equation);
        }
        tvDisplay.setText(equation);
        }
    }//GEN-LAST:event_btnEqualsActionPerformed

    private void btnRootActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRootActionPerformed
        btnEqualsActionPerformed(evt);
        if(!checkInvalidChars(tvDisplay.getText())){
        Root root = new Root();
        root.setLocation(this.getLocation());
        root.setEquation(tvDisplay.getText());
        root.setVisible(true);
        this.dispose();
        }
    }//GEN-LAST:event_btnRootActionPerformed

    private void btnDotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDotActionPerformed
         if (checkInvalidChars(tvDisplay.getText())) {
            tvDisplay.setText("");
        }
         tvDisplay.setText(tvDisplay.getText() + ".");
    }//GEN-LAST:event_btnDotActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Display().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnABS;
    private javax.swing.JButton btnDeleteAll;
    private javax.swing.JButton btnDeleteAll1;
    private javax.swing.JButton btnDeleteAll2;
    private javax.swing.JButton btnDeleteAll3;
    private javax.swing.JButton btnDeleteLast;
    private javax.swing.JButton btnDeleteLasta1;
    private javax.swing.JButton btnDeleteLasta2;
    private javax.swing.JButton btnDeleteLasta3;
    private javax.swing.JButton btnDivision;
    private javax.swing.JButton btnDot;
    private javax.swing.JButton btnEight;
    private javax.swing.JButton btnEquals;
    private javax.swing.JButton btnFact;
    private javax.swing.JButton btnFive;
    private javax.swing.JButton btnFour;
    private javax.swing.JButton btnMinus;
    private javax.swing.JButton btnNine;
    private javax.swing.JButton btnOne;
    private javax.swing.JButton btnOne1;
    private javax.swing.JButton btnOne2;
    private javax.swing.JButton btnOne3;
    private javax.swing.JButton btnPlus;
    private javax.swing.JButton btnPower;
    private javax.swing.JButton btnRoot;
    private javax.swing.JButton btnSeven;
    private javax.swing.JButton btnSix;
    private javax.swing.JButton btnThree;
    private javax.swing.JButton btnThree1;
    private javax.swing.JButton btnThree2;
    private javax.swing.JButton btnThree3;
    private javax.swing.JButton btnTimes;
    private javax.swing.JButton btnTwo;
    private javax.swing.JButton btnTwo1;
    private javax.swing.JButton btnTwo2;
    private javax.swing.JButton btnTwo3;
    private javax.swing.JButton btnZero;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField tvDisplay;
    // End of variables declaration//GEN-END:variables

  /**
    * @brief Metoda vytvo¯Ì pole textovÈho ¯etÏzce s n·povÏdu
    * 
    * @return VracÌ pole s n·povÏdou 
    */
    private ArrayList<String> getHelpArray(){
        String addFunction = "SËÌt·nÌ \"+\"";
        String addHelp = "Funkce vyûaduje minim·lnÏ dva ËÌselnÈ vstupy\n"
                + "oddÏlenÈ zmanÈnkem \"+\".";
        String subFunction = "OdËÌt·nÌ \"-\"";
        String subHelp = "Funkce vyûaduje minim·lnÏ dva ËÌselnÈ vstupy\n"
                + "oddÏlenÈ zmanÈnkem \"-\".\n\n"
                + "P¯i vloûenÌ znamÈnka \"-\" n·sledovenÈho ËÌslem,\n"
                + "lze zÌskat z·pornÈ ËÌslo.";
        String mulFunction = "N·sobenÌ \"*\"";
        String mulHelp = "Funkce vyûaduje minim·lnÏ dva ËÌselnÈ vstupy\n"
                + "oddÏlenÈ zmanÈnkem \"*\".";
        String divFunction = "DÏlenÌ \"/\"";
        String divHelp = "Funkce vyûaduje minim·lnÏ dva ËÌselnÈ vstupy\n"
                + "oddÏlenÈ zmanÈnkem \"/\".\n\n"
                + "DÏlenÌ nulou bohuûel umÌ pouze Chuck Norris.";
        String powFunction = "Mocnina \"^\"";
        String powHelp = "Funkce vyûaduje ËÌseln˝ vstup reprezentujÌcÌ\n"
                + "hodnotu k umocnÏnÌ.\n\n"
                + "Po kliknutÌ na tlaËÌtko \"^\" dojde k otev¯enÌ novÈho\n"
                + "okna pro v˝bÏr exponentu.";
        String rootFunction = "Odmocnina \"?\"";
        String rootHelp = "Funkce vyûaduje ËÌseln˝ vstup reprezentujÌcÌ\n"
                + "hodnotu pod odmocninou.\n\n"
                + "Po kliknutÌ na tlaËÌtko \"?\" dojde k otev¯enÌ novÈho\n"
                + "okna pro v˝bÏr odmocniny.";
        String factFunction = "Faktori·l \"!\"";
        String factHelp = "Funkce vyûaduje ËÌseln˝ vstup reprezentujÌcÌ\n"
                + "hodnotu faktori·lu n·sledovanou znamÈnkem \"!\".";
        String absFunction = "AbsolutnÌ hodnota (\"ABS\")";
        String absHelp = "Funkce vyûaduje ËÌseln˝ vstup reprezentujÌcÌ\n"
                + "hodnotu absolutnÌ hodnoty.";

        ArrayList<String> help = new ArrayList<>();
        help.add(addFunction);
        help.add(addHelp);
        help.add(subFunction);
        help.add(subHelp);
        help.add(mulFunction);
        help.add(mulHelp);
        help.add(divFunction);
        help.add(divHelp);
        help.add(powFunction);
        help.add(powHelp);
        help.add(rootFunction);
        help.add(rootHelp);
        help.add(factFunction);
        help.add(factHelp);
        help.add(absFunction);
        help.add(absHelp);

        return help;
    }
}
