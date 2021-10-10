import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;
import java.io.*; 
import java.time.LocalDate;
import java.text.DecimalFormat;

class ATMGUI extends JFrame implements ActionListener
{
   JLabel labelA, title, labelB, labels, output, openLabel, Label, Confirm; 
   JTextField firstIn, secondIn, openDepo, input;
   JPanel p1, p2, p3, p4, p5, p6, p7, closeP, menuP;
   JButton login, openAcc, openOK, bBalanceInquiry, bDeposit, bWithdraw, bCloseAccount, bClose, bBackMenu, bOut;
   JButton depositOK, withdrawOK, NO;
   String accountNumber, password;
   File accounts;
   boolean isOpen = false;
   Container c = getContentPane();
   public ATMGUI(){
      super("Automated Teller Machine");
      labelA = new JLabel("Account Number: "); labelB = new JLabel("Password: ");
      title = new JLabel("SaveFrom ATM", JLabel.CENTER);
      title.setFont(new Font("Verdana", Font.BOLD, 20));
      firstIn = new JTextField(10);
      secondIn = new JTextField(10);
      login = new JButton("Login");
      login.setBackground(new java.awt.Color(81, 164, 82));
      login.addActionListener(this);
      openAcc = new JButton("Open Account");
      openAcc.addActionListener(this);
      //header
      p1 = new JPanel();
      p1.setLayout(new GridLayout(1, 1));
      p1.add(title);
      p1.setVisible(true);
      //start display
      p2 = new JPanel(); 
      p2.setLayout(new GridLayout(7, 1));
      p2.add(title);
      p2.add(labelA);
      p2.add(firstIn);
      p2.add(labelB);
      p2.add(secondIn);
      p2.setVisible(true);
      //start navigational
      p3 = new JPanel();
      p3.setLayout(new GridLayout(1, 2));
      p3.add(login);
      p3.add(openAcc);
      p3.setVisible(true);
      //new Account Input
      p4 = new JPanel();
      p4.setLayout(new GridLayout(7,1));
      openLabel = new JLabel("Enter deposit amount: ");
      openDepo = new JTextField(10);
      p4.add(openLabel);
      p4.add(openDepo);
      p4.setVisible(false);
      //
      p5 = new JPanel();
      p5.setLayout(new GridLayout(1,1));
      openOK = new JButton("OK");
      openOK.addActionListener(this);
      p5.add(openOK);
      p5.setVisible(false);
      //
      p6 = new JPanel();
      p6.setLayout(new GridLayout(2,2));
      bBalanceInquiry = new JButton("Balance Inquiry");
      bBalanceInquiry.addActionListener(this);
      bDeposit = new JButton("Deposit");
      bDeposit.addActionListener(this);
      bWithdraw = new JButton("Withdraw");
      bWithdraw.addActionListener(this);
      bCloseAccount = new JButton("Close Account");
      bCloseAccount.addActionListener(this);
      p6.add(bBalanceInquiry);
      p6.add(bDeposit);
      p6.add(bWithdraw);
      p6.add(bCloseAccount);
      p6.setVisible(false);
      //
      p7 = new JPanel();
      p7.setLayout(new GridLayout(4,1));
      p7.setVisible(false);
      depositOK = new JButton("OK");
      depositOK.addActionListener(this);
      withdrawOK = new JButton("OK");
      withdrawOK.addActionListener(this);
      Label = new JLabel("Enter Deposit Amount");
      Confirm = new JLabel("Click OK to confirm deposit");
      input = new JTextField(10);
      p7.add(Label);
      p7.add(input);
      p7.add(Confirm);
      p7.add(depositOK);
      NO = new JButton("No");
     //------------------
      NO.addActionListener(this);
      JLabel sel = new JLabel("Select an option:");
      JLabel sol = new JLabel("");
      closeP = new JPanel();
      closeP.setLayout(new GridLayout(2,2));
      bClose = new JButton("Close"); bClose.addActionListener(this);
      bBackMenu = new JButton("Back"); bBackMenu.addActionListener(this);
      bOut = new JButton("Exit"); bOut.addActionListener(this);
      closeP.add(sel);closeP.add(sol);
      closeP.add(bBackMenu);
      closeP.add(bOut);
      closeP.setVisible(false);
      c.add(p1, BorderLayout.NORTH);
      c.add(p2, BorderLayout.CENTER);
      c.add(p3, BorderLayout.SOUTH);
           
      setSize(500, 500);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setResizable(false); setVisible(true);
    }
    //-----------------------------------------------------
      public void	actionPerformed(ActionEvent e)   {
      ATM atm = new ATM();
      

      
      //----------------------
      if(e.getSource()==bBackMenu)
      {
         c.removeAll();
         repaint();
         c.add(p1,BorderLayout.NORTH);
         c.add(p6,BorderLayout.CENTER);
         p1.setVisible(true);
         p6.setVisible(true); 
         repaint(); 
      }
      if(e.getSource()==bOut)
      {
        c.removeAll();
        repaint();
  
        c.add(p1, BorderLayout.NORTH); 
        c.add(p2, BorderLayout.CENTER);
        c.add(p3, BorderLayout.SOUTH); 
        p1.setVisible(true);
        p2.setVisible(true);
        p3.setVisible(true);
        repaint(); 
      }
      if(e.getSource()==bCloseAccount){
        c.removeAll();
        p7.remove(depositOK); p7.remove(Confirm);
        c.add(p7,BorderLayout.CENTER);
        p7.setVisible(true);
        repaint();
        input.setEditable(false);
        repaint();
        if(atm.closeAccount(accountNumber,input,accounts)){
        c.removeAll();
        p7.remove(Confirm);
        closeP.remove(bBackMenu);
        input.setText("Account closed!");
        c.add(p7, BorderLayout.CENTER);
        c.add(closeP, BorderLayout.SOUTH);
        p7.setVisible(true);
        closeP.setVisible(true);
        repaint();
         }
         else{
              c.removeAll();
            p7.remove(Confirm);
            c.add(p7, BorderLayout.CENTER);
            c.add(closeP, BorderLayout.SOUTH);
            p7.setVisible(true);
            closeP.setVisible(true);
            repaint();
         }
      }
      if(e.getSource()==bBalanceInquiry)
      {
         c.removeAll();
         c.add(p7,BorderLayout.CENTER);
         p7.remove(depositOK); p7.remove(Confirm);
         Label.setText("Remaining balance");
         input.setEditable(false);
         p7.setVisible(true);
         atm.balanceInquiry(input, firstIn);
         c.add(closeP, BorderLayout.SOUTH);
         closeP.setVisible(true);
         repaint();
      }
      if(e.getSource() == depositOK){
         double temp = 0;
      try{
         
         temp = Double.parseDouble(input.getText());
      }
      catch (Exception ea) { 
           input.setText("INCORRECT INPUT!");
        } 
      if(atm.deposit(accountNumber, input, accounts) == true){
        p7.remove(depositOK);
        input.setText("Deposit successful!");
         c.add(p7, BorderLayout.CENTER);
         c.add(closeP, BorderLayout.SOUTH);
         p7.setVisible(true);
         closeP.setVisible(true);
         repaint();
         
         }
      }
      if(e.getSource() == bDeposit){
         c.removeAll();
         p7.removeAll();
         p7.add(Label);
         p7.add(input);
         input.setText("");
         input.setEditable(true);
         p7.add(Confirm);
         p7.add(depositOK);
         p7.setVisible(true);
         c.add(p7,BorderLayout.CENTER);
         Label.setText("Enter deposit amount");
        Confirm.setText("Click OK to confirm deposit");
         p7.setVisible(true);
         repaint();
         
      }
      if(e.getSource()==bWithdraw){
         c.removeAll();
          p7.removeAll();
          p7.add(Label);
          p7.add(input);
          input.setText("");
          input.setEditable(true);
          p7.add(Confirm);
          p7.add(withdrawOK);
          c.add(p7,BorderLayout.CENTER);
            p7.setVisible(true);
           Label.setText("Enter withdrawal amount");
          Confirm.setText("Click OK to confirm withdrawal");
         repaint();
      }
      if(e.getSource()==withdrawOK){
         double temp = 0;
      try{
         
         temp = Double.parseDouble(input.getText());
      }
      catch (Exception ea) { 
           input.setText("INCORRECT INPUT!");
        } 
      if(atm.withdraw(accountNumber, input, accounts) == true){
           p7.remove(withdrawOK);
        input.setText("Withdrawal successful!");
         c.add(p7, BorderLayout.CENTER);
         c.add(closeP, BorderLayout.SOUTH);
         p7.setVisible(true);
         closeP.setVisible(true);
         repaint();
         }
      }

      if(e.getSource() == login){
        accountNumber = firstIn.getText();
        password = secondIn.getText();
         String fName = "AC" + accountNumber + ".txt";
      File accounts = new File(fName);
      if (atm.Login(firstIn,secondIn,accounts)) {
         c.removeAll();
         c.add(p1,BorderLayout.NORTH);
         p1.setVisible(true);
         c.add(p6,BorderLayout.CENTER);
         p6.setVisible(true);
         repaint();
        }  
      }
      if(e.getSource() == openOK){
      double temp = 0;
      try{
         
         temp = Double.parseDouble(openDepo.getText());
      }
      catch (Exception ea) { 
           openDepo.setText("INCORRECT INPUT!");
        } 
    
        
        
        if(atm.openAccount(accountNumber,temp,accounts) == true){
         c.remove(p4);
         c.remove(p5);
         c.add(p1,BorderLayout.NORTH);
         p1.setVisible(true);
         c.add(p6,BorderLayout.CENTER);
         p6.setVisible(true);
         repaint();
        }
      }
      
      if(e.getSource() == openAcc){
         accountNumber = firstIn.getText();
         password = secondIn.getText();
         String fName = "AC" + accountNumber + ".txt";
        try {
        File accounts = new File(fName);
        if (accounts.createNewFile() && password.equals("1234")) {
         c.removeAll();
         c.add(p4, BorderLayout.CENTER); p4.setVisible(true);
         c.add(p5, BorderLayout.SOUTH); p5.setVisible(true);
         if(!(password.equals("1234"))){
         secondIn.setText("PASSWORD INCORRECT");
        }
        
      } else {
        firstIn.setText("ACCOUNT ALREADY EXISTS");
        if(!(password.equals("1234"))){
         secondIn.setText("PASSWORD INCORRECT");
        }
      }
    } catch (IOException ae) {
      System.out.println("An error occurred.");
      ae.printStackTrace();
    }
         repaint();
         
      } 
   }
      
   }
 
class ATM{
//-------------------------
   public boolean openAccount(String accountNumber, double temp, File accounts){
   String fName = "AC" + accountNumber + ".txt";
   
   accounts = new File(fName);
      FileWriter f2;
      LocalDate date = LocalDate.now();
      DecimalFormat f = new DecimalFormat("#.00"); 
   try {
      f2 = new FileWriter(accounts,true);
      f2.write(date + "\t" + "OpenAccount" + "?" + f.format(temp) + "\t");
      
      f2.close(); f2 = null;
} catch (IOException e) {
        e.printStackTrace();
}
   return true;
   
   }
   public boolean Login(JTextField firstIn, JTextField secondIn, File accounts){
      
         boolean isLogin = false;
         String output = "";
         String temp = "";
          String f = "AC"+firstIn.getText() +".txt";
         try   {
         BufferedReader b = new BufferedReader(new FileReader(f));
         String line = null;   
         while((line = b.readLine()) != null) {
            String tmp[] = line.split("\t");
            temp = tmp[tmp.length-1];
         }
         b.close(); b = null;
      } catch(IOException ae)  {
         ae.printStackTrace();
      }
       output = temp;
       if(output.equals("Closed Account"))
       {
         firstIn.setText("Account is already closed");
       }
      else{
         String password = secondIn.getText();
      if (accounts.exists()==true && password.equals("1234")) {
         
         if(!(password.equals("1234"))){
         secondIn.setText("PASSWORD INCORRECT");
        }
        isLogin = true;
        
      } else {
        if(!(accounts.exists())){
        firstIn.setText("ACCOUNT DOESN'T EXIST");
        }
        if(!(password.equals("1234"))){
         secondIn.setText("PASSWORD INCORRECT");
        }
      }
      }
    return isLogin;
   }
   //----------------------
   public void balanceInquiry(JTextField input, JTextField firstIn ){
      String temp="";
      String f = "AC"+firstIn.getText() +".txt";
      String output="";
      String last = "";
      int num = 0;   
      try   {
         BufferedReader b = new BufferedReader(new FileReader(f));
         String line = null;   
         while((line = b.readLine()) != null) {
            String tmp[] = line.split("\t");
            temp = tmp[tmp.length-1];
         }
         b.close(); b = null;
      } catch(IOException ae)  {
         ae.printStackTrace();
      }
       output = temp;
      num = output.lastIndexOf("?");
      last = output.substring(num+1, output.length());
      if(last.length()==0)
      {
         last = "0.00";
      }
      if(last.length()!=0)
      {
         if(Double.parseDouble(last)==0)
         {
            last = "0.00";
         } 
      }
      input.setText("P "+last);
   }
   public boolean deposit(String accountNumber, JTextField input, File accounts){
      boolean isDeposit = false;
      String fName = "AC" + accountNumber + ".txt";
      String file = "";
      String tempa = "";
      double Prevb =0;
      accounts = new File(fName);
      FileWriter f2;
    
   try {
       LocalDate date = LocalDate.now();
       DecimalFormat f = new DecimalFormat("#.00"); 
       FileReader f1 = new FileReader(fName); 
      int x; 
      while ((x=f1.read()) != -1) 
      file+=(char) x;   
      f1.close();  f1=null;
      if(file.length()!=0)
       {
        Prevb = 0 ;
        tempa = "";
        int y = file.lastIndexOf('?'); 
        tempa = file.substring(y+1,file.length());
        Prevb = Double.parseDouble(tempa);
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } 
    try {
       LocalDate date = LocalDate.now();
       DecimalFormat f = new DecimalFormat("#.00"); 
      double dep = Double.parseDouble(input.getText());
       if(dep<0)
      {
        input.setText("Do not enter negative digits");
      }
      else{
      f2 = new FileWriter(accounts,true);
      if(Prevb==0){
      f2.write(date + "\t" + "Deposit" + "?" + f.format(dep) + "\t");
      }
      else{
      f2.write(date + "\t" + "Deposit" + "?" + f.format(dep+Prevb) + "\t"); 
      }
      f2.close(); f2 = null;
      isDeposit = true;
      }
} catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
}
    catch(NumberFormatException nfe){
            input.setText("Digits only");
      } 
   
   return isDeposit;
   }
   public boolean withdraw(String accountNumber, JTextField input, File accounts){
    boolean isWithdraw = false;
      String fName = "AC" + accountNumber + ".txt";
      String file = "";
      String tempa = "";
      double Prevb =0;
      double with = 0;
      accounts = new File(fName);
      FileWriter f2;
   try {
       LocalDate date = LocalDate.now();
       DecimalFormat f = new DecimalFormat("#.00"); 
       FileReader f1 = new FileReader(fName); 
      int x; 
      while ((x=f1.read()) != -1) 
      file+=(char) x;   
      f1.close();  f1=null;
      if(file.length()!=0)
       {
        Prevb = 0 ;
        tempa = "";
        int y = file.lastIndexOf('?'); 
        tempa = file.substring(y+1,file.length());
        Prevb = Double.parseDouble(tempa);
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } 
    try {
       LocalDate date = LocalDate.now();
       DecimalFormat f = new DecimalFormat("#.00"); 
       with = Double.parseDouble(input.getText());
      if(with<0)
      {
        input.setText("Do not enter negative digits");
      }
      else{
      f2 = new FileWriter(accounts,true);
      if(Prevb< with){
        input.setText("Not enough funds");
      }
      else{
      f2.write(date + "\t" + "Withdraw" + "?" + f.format(Prevb - with) + "\t"); 
      f2.close(); f2 = null;
      isWithdraw = true;
      }
      }
} catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
}
    catch(NumberFormatException nfe){
            input.setText("Digits only");
      } 
   return isWithdraw;
   }
   public boolean closeAccount(String accountNumber, JTextField input, File accounts){
      boolean isClose = false;
      String fName = "AC" + accountNumber + ".txt";
      String file = "";
      String tempa = "";
      double Prevb =0;
      accounts = new File(fName);
      FileWriter f2;
    
   try {
       LocalDate date = LocalDate.now();
       DecimalFormat f = new DecimalFormat("#.00"); 
       FileReader f1 = new FileReader(fName); 
      int x; 
      while ((x=f1.read()) != -1) 
      file+=(char) x;   
      f1.close();  f1=null;
      if(file.length()!=0)
       {
        Prevb = 0 ;
        tempa = "";
        int y = file.lastIndexOf('?'); 
        tempa = file.substring(y+1,file.length());
        Prevb = Double.parseDouble(tempa);
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } 
    try {
       LocalDate date = LocalDate.now();
       DecimalFormat f = new DecimalFormat("#.00"); 
       if(Prevb==0)
      {
        f2 = new FileWriter(accounts,true);
      f2.write(date + "\t" + "Closed Account");
      f2.close(); f2 = null;
      isClose = true;
       
      }
      else{
      input.setText("Withdraw remaining balance");
      }
} catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
}

   return isClose;
   }
   }
   

public class CempronDemo
{
   public static void main(String args[])
   {
        new ATMGUI();
   }
}