import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

class Login extends JFrame {
	/*
	 * This is the login class which will be the first frame of the application. The
	 * user will enter their username and password and proceed to the Bank Menu.
	 * User will be able to exit the application by clicking on the 'EXIT' JButton.
	 */

	JPanel panel, imageArea, user, pass, buttonArea, panelRight, panelLeft;
	JTextField username;
	JPasswordField password;
	JLabel labelUser, labelPass, image;
	JButton signIn, exit;
	ImageIcon logo;
	String realUser = "123";
	String realPassword = "123";

	public Login() {
		panel = new JPanel();
		imageArea = new JPanel();
		user = new JPanel();
		pass = new JPanel();
		panelRight = new JPanel();
		buttonArea = new JPanel();
		username = new JTextField(20);
		password = new JPasswordField(20);
		labelUser = new JLabel("Username:");
		labelPass = new JLabel("Password");
		image = new JLabel();
		signIn = new JButton("SIGN IN");
		exit = new JButton("EXIT");
		ImageIcon img = new ImageIcon(getClass().getResource("logo.jpg"));

		signIn.addActionListener(l -> accountChecker());
		exit.addActionListener(l -> end());

		user.setBackground(Color.CYAN);
		user.add(labelUser);
		user.add(username);
		pass.setBackground(Color.CYAN);
		pass.add(labelPass);
		pass.add(password);
		buttonArea.setBackground(Color.CYAN);
		buttonArea.add(signIn);
		buttonArea.add(exit);
		image.setIcon(img);

		
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		panelRight.add(user);
		panelRight.add(pass);
		panelRight.add(buttonArea);

		panel.setBackground(Color.CYAN);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(image);
		panel.add(panelRight);
		this.setContentPane(panel);
		this.setSize(520, 230);
		this.setTitle("BANK LOGIN");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	void accountChecker() {
		/*
		 * This method will check if the username and password entered by the user are
		 * correct. If they are correct the user will proceed to the Banking Menu page,
		 * if it is not the user will be alerted of incorrect username/password and will
		 * be asked to re-enter correct username and password.
		 */
		if (username.getText().equals(realUser) && String.valueOf(password.getPassword()).equals(realPassword)) {
			Menu firstMenu = new Menu();
			firstMenu.timer.start();
			firstMenu.setSize(300, 400);
			firstMenu.setVisible(true);
			firstMenu.setTitle("MENU PAGE");
			firstMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Incorrect Passowrd or Username!", "ERROR!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	void end() {
		// When this method is called the program will end.
		System.exit(0);
	}
}

class Menu extends JFrame {
	/*
	 * This is the bulk of the application. This includes the coded Banking Menu
	 * which displays the Menu options, which when clicked will lead you to the
	 * respective banking session.
	 */

	JPanel panel, panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8, panel9, panel10, panel11;
	JLabel name, id, timerLabel, title, options, timerText;
	JButton deposit, withdraw, transactions, interest, balance, endSession;
	Timer timer;
	int second = 0, minute = 1;
	String ddSecond, ddMinute;
	DecimalFormat dFormat = new DecimalFormat("00");
	String[] dates = new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
			"15", "16", "17", "18", "19", "20", "21", "23", "24", "25", "26", "28", "29", "30", "31" };

	String[] months = new String[] { "January", "February", "March", "April", "June", "July", "August", "September",
			"October", "November", "December" };

	JComboBox<String> comboDate = new JComboBox<String>(dates);
	JComboBox<String> comboMonth = new JComboBox<String>(months);

	File transactionsFile = new File("resource/transactionHistory.txt");
	FileWriter out;
	BufferedWriter writeFile;

	public Menu() {
		panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel1 = new JPanel();
		panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel5 = new JPanel();
		panel6 = new JPanel();
		panel7 = new JPanel();
		panel8 = new JPanel();
		panel9 = new JPanel();
		panel10 = new JPanel();
		panel11 = new JPanel();
		timerLabel = new JLabel("01:00");
		timerLabel.setFont(new Font("Verdana", Font.BOLD, 14));
		timerLabel.setBackground(Color.RED);
		name = new JLabel("NAME: Mrs. Manoil");
		title = new JLabel("WELCOME TO OUR BANK!!!!");
		title.setFont(new Font("American Typewriter", Font.BOLD, 18));
		id = new JLabel("ID: 093248092");
		options = new JLabel("Please select an option to proceed:");
		timerText = new JLabel("Time until inactivity: ");
		balance = new JButton("Balance Inquiry");
		deposit = new JButton("Deposit Money");
		withdraw = new JButton("Withdraw Money");
		transactions = new JButton("Transaction History");
		interest = new JButton("Interest Calculator");
		endSession = new JButton("END SESSION");

		panel1.setBackground(Color.LIGHT_GRAY);
		panel1.add(title);
		panel2.setBackground(Color.LIGHT_GRAY);
		panel2.add(name);
		panel3.setBackground(Color.LIGHT_GRAY);
		panel3.add(id);
		panel4.setBackground(Color.LIGHT_GRAY);
		panel4.add(timerText);
		panel4.add(timerLabel);
		countDowntimer();
		panel5.setBackground(Color.CYAN);
		panel5.add(options);
		panel6.setBackground(Color.CYAN);
		panel6.add(balance);
		panel7.setBackground(Color.CYAN);
		panel7.add(deposit);
		panel8.setBackground(Color.CYAN);
		panel8.add(withdraw);
		panel9.setBackground(Color.CYAN);
		panel9.add(transactions);
		panel10.setBackground(Color.CYAN);
		panel10.add(interest);
		panel11.setBackground(Color.DARK_GRAY);
		panel11.add(endSession);

		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		panel.add(panel6);
		panel.add(panel7);
		panel.add(panel8);
		panel.add(panel9);
		panel.add(panel10);
		panel.add(panel11);

		/*
		 * The following line of codes redirect to the different banking sessions using
		 * lambda functions.
		 */

		deposit.addActionListener(l -> depositClick());
		withdraw.addActionListener(l -> withdrawClick());
		transactions.addActionListener(l -> transactionsClick());
		interest.addActionListener(l -> interestClick());
		balance.addActionListener(l -> balanceClick());
		endSession.addActionListener(l -> end());
		this.setContentPane(panel);

	}

	Menu balanceMenu;
	JPanel balancePanel;
	JLabel balanceTitle, totalBalance;

	void balanceClick() {
		/*
		 * This is the method which will display a frame created as a object of the Menu
		 * class. The frame of the BALANCE option will be created. This frame will
		 * display the current balance of the user.
		 * 
		 * The user will be able to move back to Main Menu by clicking on the back
		 * JButton.
		 */

		timer.stop();
		this.dispose();
		balanceMenu = new Menu();
		balancePanel = new JPanel();
		balanceTitle = new JLabel("Your CURRENT BALANCE IS:");
		totalBalance = new JLabel("$" + String.valueOf(Balance.currentBalance));
		back = new JButton("BACK/EXIT");
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();

		panel1.add(balanceTitle);
		panel2.add(totalBalance);
		panel3.add(back);

		back.addActionListener(l -> backMenu(balanceMenu));
		totalBalance.setFont(new Font("American Typewriter", Font.BOLD, 18));

		balancePanel.setLayout(new BoxLayout(balancePanel, BoxLayout.Y_AXIS));
		balancePanel.add(panel1);
		balancePanel.add(panel2);
		balancePanel.add(panel3);
		balanceMenu.setContentPane(balancePanel);
		balanceMenu.setSize(300, 150);
		balanceMenu.setVisible(true);
		balanceMenu.setTitle("BALANCE INQUIRY");
		balanceMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	Menu depositMenu;
	JPanel depositPanel;
	JButton back, enterDeposit;
	JLabel currBalance, enterAmount, depositTitle, titleDate, titleMonth;
	JTextField depositAmount;

	void depositClick() {
		/*
		 * This is the method which will display a frame created as a object of the Menu
		 * class. The frame of the DEPOSIT option will be created.
		 * 
		 * In this frame the user will be able to view his/her current balance. They
		 * will enter the amount of money they want to deposit and the date and month at
		 * which the deposit is being done.
		 * 
		 * After this information is entered the 'DEPOSIT AMOUNT' JButton will call the
		 * addDeposit() method and will record the deposit into the
		 * transactionsFile.txt. Errors will be checked in the
		 * 
		 * The user will be able to move back to Main Menu by clicking on the back
		 * JButton.
		 */

		timer.stop();
		this.dispose();
		depositMenu = new Menu();
		depositPanel = new JPanel();
		depositTitle = new JLabel("This is where you can deposit money into your Bank Account");
		currBalance = new JLabel("Your current balance is :" + Balance.currentBalance);
		enterAmount = new JLabel("Enter Amount to be deposited: ");
		titleDate = new JLabel("Enter Date: ");
		titleMonth = new JLabel("Enter Month: ");
		depositAmount = new JTextField(15);
		back = new JButton("BACK/EXIT");
		enterDeposit = new JButton("Deposit Amount");
		panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel7 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		panel1.add(depositTitle);
		panel2.add(currBalance);
		panel3.add(titleDate);
		panel3.add(comboDate);
		panel4.add(titleMonth);
		panel4.add(comboMonth);
		panel5.add(enterAmount);
		panel5.add(depositAmount);
		panel6.add(enterDeposit);
		panel7.add(back);

		back.addActionListener(l -> backMenu(depositMenu));
		enterDeposit.addActionListener(l -> addDeposit());

		depositPanel.setLayout(new BoxLayout(depositPanel, BoxLayout.Y_AXIS));
		depositPanel.add(panel1);
		depositPanel.add(panel2);
		depositPanel.add(panel3);
		depositPanel.add(panel4);
		depositPanel.add(panel5);
		depositPanel.add(panel6);
		depositPanel.add(panel7);

		depositMenu.setContentPane(depositPanel);
		depositMenu.setSize(400, 300);
		depositMenu.setVisible(true);
		depositMenu.setTitle("DEPOSIT PAGE");
		depositMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	Menu withdrawMenu;
	JPanel withdrawPanel;
	JLabel withdrawTitle, enterWithdrawal;
	JButton confirmWithdrawal;
	JTextField withdrawalAmount;

	void withdrawClick() {
		/*
		 * This is the method which will display a frame created as a object of the Menu
		 * class. The frame of the WITHDRAW option will be created.
		 * 
		 * In this frame the user will be able to view his/her current balance. They
		 * will enter the amount of money they want to WITHDRAW and the date and month
		 * at which the withdrawal is being done.
		 * 
		 * After this information is entered the 'Process WIthdrawal' JButton will call
		 * the addWithdrawal() method and will record the deposit into the
		 * transactionsFile.txt. Errors will be checked in the addWithdrawal() method.
		 * 
		 * The user will be able to move back to Main Menu by clicking on the back
		 * JButton.
		 */

		timer.stop();
		this.dispose();
		withdrawMenu = new Menu();
		withdrawPanel = new JPanel();
		withdrawTitle = new JLabel("You can make Withdrawals from your account here");
		titleDate = new JLabel("Enter Date: ");
		titleMonth = new JLabel("Enter Month: ");
		back = new JButton("BACK/EXIT");
		confirmWithdrawal = new JButton("Process Withdrawal");
		currBalance = new JLabel("Your current balance is :" + Balance.currentBalance);
		withdrawalAmount = new JTextField(15);
		enterWithdrawal = new JLabel("Enter withdrawal Amount: ");

		panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel7 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		panel1.add(withdrawTitle);
		panel2.add(currBalance);
		panel3.add(titleDate);
		panel3.add(comboDate);
		panel4.add(titleMonth);
		panel4.add(comboMonth);
		panel5.add(enterWithdrawal);
		panel5.add(withdrawalAmount);
		panel6.add(confirmWithdrawal);
		panel7.add(back);

		back.addActionListener(l -> backMenu(withdrawMenu));
		confirmWithdrawal.addActionListener(l -> addWithdrawal());

		withdrawPanel.setLayout(new BoxLayout(withdrawPanel, BoxLayout.Y_AXIS));
		withdrawPanel.add(panel1);
		withdrawPanel.add(panel2);
		withdrawPanel.add(panel3);
		withdrawPanel.add(panel4);
		withdrawPanel.add(panel5);
		withdrawPanel.add(panel6);
		withdrawPanel.add(panel7);

		withdrawMenu.setContentPane(withdrawPanel);
		withdrawMenu.setSize(400, 300);
		withdrawMenu.setVisible(true);
		withdrawMenu.setTitle("WITHDRAW PAGE");
		withdrawMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	Menu transactionsMenu;
	JPanel transactionsPanel;
	JLabel transactionsTitle;
	JTextArea displayTransactions;
	JButton viewHistory;
	JScrollPane scrollPane;

	void transactionsClick() {
		/*
		 * This is the method which will display a frame created as a object of the Menu
		 * class. The frame of the TRANSACTIONS HISTORY option will be created.
		 * 
		 * When the JButton 'VIEW HISTORY' is clicked it will read the
		 * transactionsFile.txt and will display all the transactions to this date.
		 * 
		 * The user will be able to move back to Main Menu by clicking on the back
		 * JButton.
		 */
		timer.stop();
		this.dispose();
		transactionsMenu = new Menu();
		transactionsPanel = new JPanel();
		transactionsTitle = new JLabel("Here you can view all of your TRANSACTION HISTORY");
		displayTransactions = new JTextArea(20, 27);
		scrollPane = new JScrollPane(displayTransactions);
		viewHistory = new JButton("VIEW HISTORY");
		back = new JButton("BACK/EXIT");
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();

		panel1.add(transactionsTitle);
		panel2.add(viewHistory);
		panel3.add(scrollPane);
		panel4.add(back);

		back.addActionListener(l -> backMenu(transactionsMenu));
		viewHistory.addActionListener(l -> {
			// This reads the transactionsFile.txt file and displays the content onto
			// JTextArea

			try {
				BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(transactionsFile)));
				displayTransactions.read(input, "READING FILE :-)");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		transactionsPanel.setLayout(new BoxLayout(transactionsPanel, BoxLayout.Y_AXIS));
		transactionsPanel.add(panel1);
		transactionsPanel.add(panel2);
		transactionsPanel.add(panel3);
		transactionsPanel.add(panel4);

		transactionsMenu.setContentPane(transactionsPanel);
		transactionsMenu.setSize(400, 475);
		transactionsMenu.setVisible(true);
		transactionsMenu.setTitle("TRANSACTION HISTORY");
		transactionsMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	Menu interestMenu;
	JPanel interestPanel;
	JLabel interestTitle, interestAmount, interestPercentage, interestYears, outputInterest;
	JTextField enterInterest, enterPercentage, enterYears;
	JButton calculateInterest;

	void interestClick() {
		/*
		 * This is the method which will display a frame created as a object of the Menu
		 * class. The frame of the INTEREST CALCULATOR option will be created.
		 * 
		 * In this frame the user will be able to view his balance, and then proceed to
		 * calculate simple interest on his amount or any other amount of money after
		 * entering the rate of interest and the amount of time the interest is to be
		 * calculated/. When this information is entered errors will be checked in the
		 * interestCalculator() method
		 * 
		 * After the interest is calculated, the total amount including interest will be
		 * displayed on the frame.
		 * 
		 * The user will be able to move back to Main Menu by clicking on the back
		 * JButton.
		 */

		timer.stop();
		this.dispose();
		interestMenu = new Menu();
		interestPanel = new JPanel();
		interestTitle = new JLabel("INTEREST CALCULATOR");
		balanceTitle = new JLabel("Your CURRENT BALANCE is: $" + String.valueOf(Balance.currentBalance));
		interestAmount = new JLabel("Enter amount to calculate interest of:");
		enterInterest = new JTextField(10);
		interestPercentage = new JLabel("Enter Interest percentage (e.g 1.5):");
		enterPercentage = new JTextField(10);
		interestYears = new JLabel("Enter number of years:");
		enterYears = new JTextField(10);
		outputInterest = new JLabel();
		back = new JButton("BACK/EXIT");
		calculateInterest = new JButton("CALCULATE INTEREST");

		panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel8 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		panel1.add(interestTitle);
		panel2.add(balanceTitle);
		panel3.add(interestAmount);
		panel3.add(enterInterest);
		panel4.add(interestPercentage);
		panel4.add(enterPercentage);
		panel5.add(interestYears);
		panel5.add(enterYears);
		panel6.add(calculateInterest);
		panel7.add(outputInterest);
		panel8.add(back);

		back.addActionListener(l -> backMenu(interestMenu));
		calculateInterest.addActionListener(l -> interestCalculator());

		interestPanel.setLayout(new BoxLayout(interestPanel, BoxLayout.Y_AXIS));
		interestPanel.add(panel1);
		interestPanel.add(panel2);
		interestPanel.add(panel3);
		interestPanel.add(panel4);
		interestPanel.add(panel5);
		interestPanel.add(panel6);
		interestPanel.add(panel7);
		interestPanel.add(panel8);

		interestMenu.setContentPane(interestPanel);
		interestMenu.setSize(390, 400);
		interestMenu.setVisible(true);
		interestMenu.setTitle("INTEREST CALCULATOR");
		interestMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void addDeposit() {
		/*
		 * This method is called when the user is depositing money into his/her account.
		 * It will get the information entered by the user and store it into their
		 * respective variables If the user does not enter numbers into the JTextField a
		 * JOptionPane window will be called and the user will be told to re-enter
		 * correct values.
		 */

		String dateSelected = String.valueOf(comboDate.getSelectedItem()); // Stores date
		String monthSelected = String.valueOf(comboMonth.getSelectedItem()); // Stores month
		double amount; // Stores amount
		depositMenu.dispose();
		try {
			// Will attempt to write deposit into transactionsFile.txt

			amount = Double.parseDouble(depositAmount.getText());
			Balance.currentBalance += amount;
			out = new FileWriter(transactionsFile, true);
			writeFile = new BufferedWriter(out);
			writeFile.write("Date: " + dateSelected + " ");
			writeFile.write("Month: " + monthSelected + " ");
			writeFile.write("Amount: + $" + String.valueOf(amount));
			writeFile.newLine();
			writeFile.close();
			out.close();
			depositClick();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ERROR", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
		} catch (NumberFormatException e) {
			// If numbers are not entered, it will not write in the file and
			// will notify user

			JOptionPane.showMessageDialog(null, "Please Enter numbers only!!", "ERROR!",
					JOptionPane.INFORMATION_MESSAGE);
			depositClick();

		}

	}

	void addWithdrawal() {
		/*
		 * This method is called when the user is withdrawing money from his/her
		 * account. It will get the information entered by the user and store it into
		 * their respective variables If the user does not enter numbers into the
		 * JTextField a JOptionPane window will be called and the user will be told to
		 * re-enter correct values.
		 * 
		 * If the user enters a withdrawal amount greater than the amount of money in
		 * his/her account a JOptionPane will be created to alert the user and prompted
		 * to re-enter correct information for a successful withdrawal to occur.
		 */

		String dateSelected = String.valueOf(comboDate.getSelectedItem()); // Stores date
		String monthSelected = String.valueOf(comboMonth.getSelectedItem()); // Stores month
		double amount; // Stores amount
		withdrawMenu.dispose();
		try {
			// Will attempt to write withdrawal onto transactionsFile.txt

			amount = Double.parseDouble(withdrawalAmount.getText());
			if (amount > Balance.currentBalance) {
				throw new IllegalArgumentException("You do not have enough money in the Bank!!");
			}
			Balance.currentBalance -= amount;
			out = new FileWriter(transactionsFile, true);
			writeFile = new BufferedWriter(out);
			writeFile.write("Date: " + dateSelected + " ");
			writeFile.write("Month: " + monthSelected + " ");
			writeFile.write("Amount: - $" + String.valueOf(amount));
			writeFile.newLine();
			writeFile.close();
			out.close();
			withdrawClick();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please Enter numbers only!!", "ERROR!",
					JOptionPane.INFORMATION_MESSAGE);
			withdrawClick();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ERROR", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
		} catch (IllegalArgumentException e) {
			// Will display JOptionPane which tells user that there is not enough
			// money in their bank account to process withdrawal

			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR!", JOptionPane.INFORMATION_MESSAGE);
			withdrawClick();

		}

	}

	void interestCalculator() {
		/*
		 * This method will be called when the 'CALCULATE INTEREST' JButton is clicked
		 * in the Interest Calculator frame.
		 * 
		 * It will calculate the interest and display on the frame the calculated amount
		 * with interest added.
		 */

		double amount;
		double rate;
		double years;
		double calculatedAmount;

		try {
			amount = Double.parseDouble(enterInterest.getText());
			rate = (Double.parseDouble(enterPercentage.getText())) / 100;
			years = Double.parseDouble(enterYears.getText());

			// If the interest rate is higher that 10% (0.1) the user will be prompted to
			// enter
			// an interest rate below 10%
			if (rate > 0.1) {
				JOptionPane.showMessageDialog(null, "Please Enter interest rate below 10%", "ERROR!",
						JOptionPane.INFORMATION_MESSAGE);
				interestClick();
				return;
			}

			// If the number of years is greater than 50 years, the user will be prompted to
			// enter number of years less than 50.

			if (years > 50) {
				JOptionPane.showMessageDialog(null,
						"We cannot calculate interest on money on a period of time longer than 40 years", "ERROR!",
						JOptionPane.INFORMATION_MESSAGE);
				interestClick();
				return;
			}

			calculatedAmount = amount + (amount * rate * years);

			outputInterest.setText("<html>After " + years + " years, at an interest rate of " + rate * 100
					+ "%, your<br> total amount will be $" + calculatedAmount + "</html>");

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please Enter numbers only!!", "ERROR!",
					JOptionPane.INFORMATION_MESSAGE);
			interestClick();
		}

	}

	void backMenu(JFrame frame) {
		// This method is called when the BACK JButton is clicked and will take
		// the user back to the main banking menu page

		frame.dispose();
		Menu firstMenu = new Menu();
		resetTimer();
		firstMenu.timer.start();
		firstMenu.setSize(300, 400);
		firstMenu.setVisible(true);
		firstMenu.setTitle("Bank Menu");
		firstMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void resetTimer() {
		// This method will reset the timer
		minute = 1;
		second = 0;
	}

	void end() {
		// This method will exit the whole application
		System.exit(0);
	}

	public void countDowntimer() {
		// This method includes the coded timer.
		// After one minute of inactivity the user will be taken back to the
		// login page to re-enter username and password

		timer = new Timer(1000, l -> {
			second--;
			ddSecond = dFormat.format(second);
			ddMinute = dFormat.format(minute);
			timerLabel.setText(ddMinute + ":" + ddSecond);

			if (second == -1) {
				second = 59;
				minute--;
				ddSecond = dFormat.format(second);
				ddMinute = dFormat.format(minute);
				timerLabel.setText(ddMinute + ":" + ddSecond);
			}
			if (minute == 0 && second == 0) {
				timer.stop();
				JOptionPane.showMessageDialog(null,
						"You have been INACTIVE for 1 minutes. Due to security purposes, please re-login", "Times UP!",
						JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				new Login();
			}
		});

	}
}

public class BankingApp {
	public static void main(String[] args) {
		new Login();
	}
}
