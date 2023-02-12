import javax.swing.* ;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/*
Next Steps:

1. Adjust the View Reports tab
2. Use sample data to input and transport into csv file
3. Sync the adminAcct panel (maybe turn it into its own JFrame) // add action even to close this JFrame instance of login
into the Admin Frame
4. Enhance Readabiliy and Record Demo

 */



// EXPERIENCE LEVEL : This ratio can be utilized by any company helps with bias, includes education & work exp
/* NONE = 0 , HS/GED = 1 , BS = 2 , MS = 3 , PHD = 4 , un-related Intern/un-related experience = +0.25 , related Intern / related experience = +0.5*/
/* Position Rank : Intern, Junior, Intermediate, Senior 1, Senior 2, Board, CEO --> 0 - 6 */
/*make an array with each office*/
/*Duration of Employment: 0-3 months, 6-9 months, 9- 12 months, <2 years, <3 years, <5 years, <10 years, <20 years, 20+ years*/
/*Annual Salary: 20-35, <50, <75, <100, <125, <150, <175, <200, 200+  This would be for a smaller company*/
/*Hourly Salary: 10-100 hrly*/
/*Benefit Plan: Based on the company but Basic, Main, Exclusive, Premium, 0-3 */
// Other companies may include sign on bonus, stocks etc.
// <first_name,last_name,gender,age,sexuality,religion,experience_level,position,political_office,duration_of_employment,annual_salary,hourly_salary,benefit_plan>
public class DashGUI extends JFrame implements ActionListener, ComponentListener  {
    final int buttonWIDTH = 20;
    final int buttonHEIGHT = 25;
    final int frameWIDTH = 700;
    final int frameHEIGHT = 500;

    static JTextField usernameText;
    static JPasswordField passwordText;
    static JButton loginButton;
    static JPanel mainPanel;
    static JPanel loginPanel;
    static JPanel reportPanel;
    static JFrame dashboard;
    static JPanel adminDashboard;
    static JMenuBar menuBar;
    static JMenu dbMenu;

    static JMenuItem inputMenu, createMenu, viewMenu;
    static boolean adminScreen = false;



    public JFrame dashboard() { // dashboard design
        dashboard = new JFrame();
        dashboard.setResizable(false);
        dashboard.setLocation(new Point(400,100));
        dashboard.setLayout(new BorderLayout());
        dashboard.setTitle("The DAshBoard");
        dashboard.setSize(frameWIDTH, frameHEIGHT);

        ///login panel
        this.mainPanel = new JPanel(new CardLayout());
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setOpaque(true); mainPanel.setPreferredSize(getPreferredSize());

        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        usernameText = new JTextField(20);
        passwordText = new JPasswordField(); // This text field will be shown twice, to reenter password
        loginButton = new JButton("Login");
        menuBar = new JMenuBar();
        dbMenu = new JMenu("☰");
        viewMenu = new JMenuItem("View Reports");
        dbMenu.add(viewMenu);
        menuBar.add(dbMenu);
        dashboard.setJMenuBar(menuBar);

        Container c = getContentPane();
        CardLayout card = new CardLayout();
        c.setLayout(card);
        c.add(mainPanel);


        // Login
        mainPanel = login_acct(usernameText, passwordText, usernameLabel, passwordLabel);
        //mainPanel = admin_acct();
        dashboard.add(mainPanel, BorderLayout.CENTER);
        dashboard.setVisible(true);

        loginButton.addActionListener(this);



        dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return dashboard;
    }

//    private void setUpDB(){
//        mainPanel.add(loginPanel);
//        //mainPanel.add(adminPanel);
//        mainPanel.add(reportPanel);
//
//    }

    public JButton ButtonLayout(JButton bt) {
        bt.setPreferredSize(new Dimension(buttonWIDTH, buttonHEIGHT));
        bt.setForeground(Color.gray);
        return bt;
    }public JTextField TextLayout(JTextField tf) {
        tf.setPreferredSize(new Dimension(buttonWIDTH, buttonHEIGHT));
        tf.setForeground(Color.gray);
        return tf;
    } public static JLabel FontSize(JLabel jl) {
        jl.setFont(new Font ("Arial", Font.PLAIN, 18));
        return jl;
    }


    @Override
    public void actionPerformed(ActionEvent e) { // My action listener to add to my panel
        if ( e.getActionCommand().equals("Login")) { // once the login button is selected
            account_info checkAcct = new account_info();

            int id = checkAcct.check_admin(usernameText.getText(),passwordText.getText());

               // JOptionPane.showMessageDialog(null, "Login Successful");

               // mainPanel = admin_acct();
                //dashboard.add(mainPanel);


                // JOptionPane.showMessageDialog(null, "Username or Password is incorrect");
        }
           // myJDBC db = new myJDBC();

    }

    public static JPanel login_acct(JTextField usernameText, JPasswordField passwordText, JLabel usernameLabel, JLabel passwordLabel) {
        JPanel loginPanel = new JPanel();
        loginPanel = new JPanel(null);
        loginPanel.setBackground(Color.pink);
        loginPanel.setOpaque(true);
        usernameLabel.setBounds(250, 48, 70, 20);
        loginPanel.add(usernameLabel);
        usernameText.setBounds(250, 67, 193, 28);
        loginPanel.add(usernameText);
        passwordLabel.setBounds(250, 100, 70, 20);
        loginPanel.add(passwordLabel);
        passwordText.setBounds(250, 125, 193, 28);
        loginPanel.add(passwordText);
        loginButton.setBounds(293, 160, 90, 25);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);
        loginPanel.add(loginButton);

        return loginPanel;

    }




    public static JPanel admin_acct(){ // designing what the admin account dashboard looks like looks like
        JPanel createPanel = new JPanel(null);
        createPanel.setBackground(Color.pink);
        createPanel.setOpaque(true);
        inputMenu = new JMenuItem("New Employee");
        createMenu = new JMenuItem("Create Admin");
        dbMenu.add(inputMenu);
        dbMenu.add(createMenu);
        menuBar.add(dbMenu);
        String [] GENDER = {"None","Cisgender Woman","Cisgender Man","Transgender","Non-Binary"};
        String [] SEXUALITY = {"None","Straight","Bisexual", "Gay" , "Lesbian", "Asexual", "Pansexual", "Queer"};
        String [] RELIGION = {"None","Christian", "Catholic", "Jewish"};
        String [] EXPERIENCE_LEVEL = {"None", "High School/GED", "Bachelor's", "Masters", "PHD"};
        String [] TOTAL_POS = {"0","1","2","3","4","5"};
        String [] POSITION_RANK = {"None","Intern", "Junior", "Intermediate", "Senior 1", "Senior 2", "Board", "CEO"};
        String [] POLS_OFFICE = {"None","Democratic", "Republican", "NONE"};
        String [] DURATION_EMP = {"0-3 months","6-9 months","<1 year","<2 years","<3 years","<5 years","<10 years","<20 years","20+ years"};
        String [] ANNUAL_SALARY = {"20-35","<50","<75","<100","<125","<150","<175","<200","200+"};
        String [] BENEFIT_PLAN = {"Basic", "Main", "Exclusive", "Premium"};

        // Input employee data Labels
        JLabel createLabel = new JLabel("Input New Employee Data"); createLabel = FontSize(createLabel);
        JLabel fnLabel = new JLabel("First Name");
        JLabel lnLabel = new JLabel("Last Name");
        JLabel genderLabel = new JLabel("Gender");
        JLabel ageLabel = new JLabel("Age");
        JLabel sexLabel = new JLabel("Sexuality");
        JLabel relgLabel = new JLabel("Religion");
        JLabel expLabel = new JLabel("Education");
        JLabel unPosLabel = new JLabel("UR Positions");
        JLabel PosLabel = new JLabel("R Positions");
        JLabel rankLabel = new JLabel("Position Rank");
        JLabel officeLabel = new JLabel("Political Office");
        JLabel durLabel = new JLabel("Time Employed");
        JLabel annualLabel = new JLabel("Annual Salary");
        JLabel hourlyLabel = new JLabel("Hourly Salary");
        JLabel benefitLabel = new JLabel("Benefit Plan");

        // Employee data textfields
        JTextField input_first = new JTextField(20);
        JTextField input_last = new JTextField(20);
        JComboBox<String> gender_list = new JComboBox<>(GENDER);
        JTextField age_list = new JTextField(5);
        JComboBox<String> sex_list = new JComboBox<>(SEXUALITY);
        JComboBox<String> relg_list = new JComboBox<>(RELIGION);
        JComboBox<String> explevel_list = new JComboBox<>(EXPERIENCE_LEVEL);
        JComboBox<String> unrelated_list = new JComboBox<>(TOTAL_POS);
        JComboBox<String> related_list = new JComboBox<>(TOTAL_POS);
        JComboBox<String> rank_list = new JComboBox<>(POSITION_RANK);
        JComboBox<String> pols_list = new JComboBox<>(POLS_OFFICE);
        JComboBox<String> duremp_list = new JComboBox<>(DURATION_EMP);
        JComboBox<String> annual_list = new JComboBox<>(ANNUAL_SALARY);
        JTextField hourly_list = new JTextField(5);
        JComboBox<String> benefit_list = new JComboBox<>(BENEFIT_PLAN);
        JButton submit = new JButton("Submit");

        // ComboBox Layout
        gender_list.setBackground(Color.black);
        gender_list.setForeground(Color.white);
        sex_list.setBackground(Color.black);
        sex_list.setForeground(Color.white);
        relg_list.setBackground(Color.black);
        relg_list.setForeground(Color.white);
        explevel_list.setBackground(Color.black);
        explevel_list.setForeground(Color.white);
        unrelated_list.setBackground(Color.black);
        unrelated_list.setForeground(Color.white);
        related_list.setBackground(Color.black);
        related_list.setForeground(Color.white);
        rank_list.setBackground(Color.black);
        rank_list.setForeground(Color.white);
        pols_list.setBackground(Color.black);
        pols_list.setForeground(Color.white);
        duremp_list.setBackground(Color.black);
        duremp_list.setForeground(Color.white);
        annual_list.setBackground(Color.black);
        annual_list.setForeground(Color.white);
        benefit_list.setBackground(Color.black);
        benefit_list.setForeground(Color.white);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);



        // Label Bounds
        createLabel.setBounds(200, 20, 250, 20);
        createPanel.add(createLabel);
        fnLabel.setBounds(100, 70, 100, 20);
        createPanel.add(fnLabel);
        input_first.setBounds(200, 70, 100, 20);
        createPanel.add(input_first);
        lnLabel.setBounds(350, 70, 100, 20);
        createPanel.add(lnLabel);
        input_last.setBounds(450, 70, 100, 20);
        createPanel.add(input_last);
        genderLabel.setBounds(100, 110, 100, 20);
        createPanel.add(genderLabel);
        gender_list.setBounds(200, 110, 100, 20);
        createPanel.add(gender_list);
        ageLabel.setBounds(350, 110, 100, 20);
        createPanel.add(ageLabel);
        age_list.setBounds(450, 110, 100, 20);
        createPanel.add(age_list);
        sexLabel.setBounds(100, 150, 100, 20);
        createPanel.add(sexLabel);
        sex_list.setBounds(200, 150, 100, 20);
        createPanel.add(sex_list);
        relgLabel.setBounds(350, 150, 100, 20);
        createPanel.add(relgLabel);
        relg_list.setBounds(450, 150, 100, 20);
        createPanel.add(relg_list);
        expLabel.setBounds(100, 190, 100, 20);
        createPanel.add(expLabel);
        explevel_list.setBounds(200, 190, 100, 20);
        createPanel.add(explevel_list);
        unPosLabel.setBounds(350, 190, 100, 20);
        createPanel.add(unPosLabel);
        unrelated_list.setBounds(450, 190, 100, 20);
        createPanel.add(unrelated_list);
        PosLabel.setBounds(100, 230, 100, 20);
        createPanel.add(PosLabel);
        related_list.setBounds(200, 230, 100, 20);
        createPanel.add(related_list);
        rankLabel.setBounds(350, 230, 100, 20);
        createPanel.add(rankLabel);
        rank_list.setBounds(450, 230, 100, 20);
        createPanel.add(rank_list);
        officeLabel.setBounds(100, 270, 100, 20);
        createPanel.add(officeLabel);
        pols_list.setBounds(200, 270, 100, 20);
        createPanel.add(pols_list);
        durLabel.setBounds(350, 270, 100, 20);
        createPanel.add(durLabel);
        duremp_list.setBounds(450, 270, 100, 20);
        createPanel.add(duremp_list);
        annualLabel.setBounds(100, 310, 100, 20);
        createPanel.add(annualLabel);
        annual_list.setBounds(200, 310, 100, 20);
        createPanel.add(annual_list);
        hourlyLabel.setBounds(350, 310, 100, 20);
        createPanel.add(hourlyLabel);
        hourly_list.setBounds(450, 310, 100, 20);
        createPanel.add(hourly_list);
        benefitLabel.setBounds(250, 350, 100, 20);
        createPanel.add(benefitLabel);
        benefit_list.setBounds(325, 350, 100, 20);
        createPanel.add(benefit_list);
        submit.setBounds(300, 390, 100, 20);
        createPanel.add(submit);
        // Add to Panel





        return createPanel;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50,10);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub

    }







}