import java.text.DecimalFormat; // to format the output

import uwcse.io.Input; // use the Input class

/**
 * Homework 3 <br>
 * Simulating a purchase in Canada paid in US dollars
 * 
 * @author Yifan Yu
 */

public class CanadianGiftShop {

	// Constants
	/** Exchange rate 1 US dollar = RATE Canadian dollar */
	public final double RATE = 1.16;

	/** Price of a jar of maple syrup in Canadian dollars before taxes */
	public final double JAR_PRICE = 5.95;

	/**
	 * Price of photograph of the city of Victoria in Canadian dollars before
	 * taxes
	 */
	public final double PHOTO_PRICE = 7.65;

	/** Price of a beaver hat in Canadian dollars before taxes */
	public final double HAT_PRICE = 16.35;

	/** Maximum allowed number of purchased items for each item */
	public final int MAX_ITEM = 100;

	/** Tax rate */
	public final double TAX_RATE = 0.093;

	// set total amount in Canadian dollar
	public double Total;
	// set charge in Canadian dollar
	public double Change;

	// instance variables
	// number of purchased jars of maple syrup
	private int jarNumber;

	// number of purchased photographs of the city of Victoria
	private int photoNumber;

	// number of purchased beaver hats
	private int hatNumber;

	// 2 digits after the decimal point for doubles
	private DecimalFormat twoDigits = new DecimalFormat("0.00");

	public double payUS, costCA;

	private Input in = new Input();

	/**
	 * Takes and processes the order from the customer
	 */
	public void takeAndProcessOrder() {
		// Here is a possible series of steps: call some other (private)
		// methods to do each step.

		// Display the items and their prices
		System.out.println("                Welcome to Victoria's Shop ");
		System.out.println("                **************************");
		System.out
				.println("Here is a price list of our most popular products (in Canadian dollars)"
						+ "\nJar of Maple Syrup: $5.95 \n Photograph of Victoria: $7.65 \n Beaver Hat: $16.35\nThe above prices don't include taxes.\n The tax rate is 9.30%\n"
						+ "Our exchange rate is 1 US dollar =1.16 Canadian dollars");
		itemList();
		// Get the Customer's order
		input();
		
		Total = (jarNumber * JAR_PRICE + photoNumber * PHOTO_PRICE + hatNumber
				* HAT_PRICE)
				* (1 + TAX_RATE);
		
		itemList();
		// Get the user's USD payment

		payUS = in.readDouble("Please, enter the US dollar amount to pay for the order:");

		// Give the change back in Canadian dollars
		
		changeinCAD(payUS,costCA);

	}

	// Some ideas for some private methods
	// You don't have to use exactly these same methods.

	/**
	 * Displays the items for sale and their prices in Canadian dollars
	 */
	private void itemList() {
		System.out.println("Your purchase total is " + twoDigits.format(Total)
				+ "CA(tax included)");

	}

	/**
	 * Gets the customer's order Precondition: none Postcondition: jarNumber,
	 * photoNumber and hatNumber are initialized to a value between 0 and
	 * MAX_ITEM
	 */
	private void input() {
		jarNumber = in.readInt("How many jars of maple syrup would you like? ");
		jarNumber = checkInput(jarNumber);
		photoNumber = in
				.readInt("How many photographs of Victoria would you like? ");
		photoNumber = checkInput(photoNumber);
		hatNumber = in.readInt("How many beaver hats would you like? ");
		hatNumber = checkInput(hatNumber);
	}

	private int checkInput(int n) {
		if (n > MAX_ITEM) {
			System.out.println("Sry, we don't have that many in stock.");
			return 0;
		} else if (n < 0) {
			System.out.println("The number" + hatNumber
					+ " is not a valid number of items!");
			return 0;
		} else {
			return n;
		}
	}

	/**
	 * Given a purchase in canadian dollars and a payment in US dollars,
	 * displays the change amount in canadian dollars and cents
	 * 
	 * @param payUS
	 *            payment in US dollars
	 * @param costCA
	 *            purchase amount in Canadia dollars
	 */
	private void changeinCAD(double payUS,double costCA) {
		costCA = payUS / RATE;
		Change = costCA - Total / RATE;
		// change into cents
		int InCent = (int)(Double.valueOf(twoDigits.format(Change)).doubleValue() * 100);
		
		if (Change < 0) {
			System.out.println("Sry, it's not enough. :<");
			System.exit(0);
		} else {
			System.out.println("You give" + payUS + ", which is"
					+ twoDigits.format(costCA));
			System.out.println("Here is your change "
					+ twoDigits.format(Change) + "(tax included)");
			
			int bill20 = InCent / 2000;
			int bill10 = InCent % 2000 / 1000;
			int bill5 = InCent % 1000 / 500;
			int bill1 = InCent % 1000 % 500 / 100;
			int coin25 = InCent % 1000 % 500 / 100;
			int coin10 = InCent % 1000 % 500 % 100 / 10;
			int coin5 = InCent % 1000 % 500 % 100 % 25 % 10 / 5;
			int coin1 = InCent % 1000 % 500 % 100 % 25 % 10 % 5 /1;
			
			if (bill20> 1){
				System.out.println(bill20 + " $20 bills.");
				}else if(bill20 == 1){
				System.out.println(bill20 + " $20 bill.");
				}

				if (bill10> 1){
				System.out.println(bill10 + " $10 bills.");
				}else if(bill10 == 1){
				System.out.println(bill10 + " $10 bill.");
				}

				if (bill5> 1){
				System.out.println(bill5 + " $5 bills.");
				}else if(bill5 == 1){
				System.out.println(bill5 + " $5 bill.");
				}

				if (bill1> 1){
				System.out.println(bill1 + " $1 bills.");
				}else if(bill1 == 1){
				System.out.println(bill1 + " $1 bill.");
				}
				if (coin25> 1){
				System.out.println(coin25 + " $25C cents.");
				}else if(coin25 == 1){
				System.out.println(coin25 + " $25C cent.");
				}

				if (coin10> 1){
				System.out.println(coin10 + " $10C cents.");
				}else if(coin10 == 1){
				System.out.println(coin10 + " $10C cent.");
				}

				if (coin5> 1){
				System.out.println(coin5 + " $5C cents.");
				}else if(coin5 == 1){
				System.out.println(coin5 + " $5C cent.");
				}

				if (coin1> 1){
				System.out.println(coin1 + " $1C cents.");
				}else if(coin1 == 1){
				System.out.println(coin1 + " $1C cent.");
				}
			System.out.println("==============================");
			System.out.println("Thank you for your business!");
			System.exit(0);
		}
	}

	/**
	 * Entry point of the program
	 */
	public static void main(String[] args) {
		new CanadianGiftShop().takeAndProcessOrder();
	}

}
