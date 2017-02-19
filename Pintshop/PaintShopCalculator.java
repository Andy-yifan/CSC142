import java.text.DecimalFormat; // to format numbers (check the documentation)

/**
 * Compute the amount of paint needed to paint a room
 */

public class PaintShopCalculator {

	// Constants
	// Prices of the paint containers in dollars
	public final double FIVEGALLONS = 116.00;

	public final double ONEGALLON = 23.20;

	public final double HALFGALLON = 11.60;

	public final double QUART = 5.80;

	public final double PINT = 2.90;

	public final double HALFPINT = 1.45;

	// Area that can be painted with one gallon of paint (in square inches)
	public final double AREA_PER_GALLON = 25000.0;
	
	private double TotalPaint,TotalPrice,TotalHalfPint;
	
	private int fiveGa,oneGa,halfGa,quart,pint,halfPint;
	
	private double remainderA,remainderB,remainderC,remainderD,remainderE,remainderF;
	
	private String b,c,d,e,f,g;
	
		

	/**
	 * Initialize this PaintShopCalculator with the room measurements. For
	 * example, if the height is 10'2'', heightFeet is 10 and heightInches is 2.
	 * 
	 * @param heightFeet
	 *            the number of feet of the height measurement
	 * @param heightInches
	 *            the number of inches of the height measurement
	 * @param widthFeet
	 *            the number of feet of the width measurement
	 * @param widthInches
	 *            the number of inches of the width measurement
	 * @param lengthFeet
	 *            the number of feet of the length measurement
	 * @param lengthInches
	 *            the number of inches of the length measurement
	 */
	public PaintShopCalculator(int heightFeet, int heightInches,
			int lengthFeet, int lengthInches, int widthFeet, int widthInches) {
		// Calculate Total Area divided AREA_PER_GALLON
		// Get Total Paint
		TotalPaint = ((lengthFeet*12+lengthInches)*(widthFeet*12+widthInches)+((heightFeet*12+heightInches)*2*(lengthFeet*12+lengthInches))
				+((heightFeet*12+heightInches)*2*(widthFeet*12+widthInches)))/AREA_PER_GALLON;
	}

	/**
	 * Return as a string the result of the computation. The string should list
	 * the exact amount of paint needed (with 3 digits after the decimal point),
	 * the number and type of paint containers needed, and the price (with 2
	 * digits after the decimal point). Pay attention to the spelling (container
	 * versus containers) and the quality of the output (no 0 one gallon
	 * container).
	 * 
	 * Here is an example with height=4'3'', length=5'4'' and width=6'5'':
	 * 
	 * For this job, you need 0.772 gallons of paint. You will need to purchase
	 * 1 one half gallon container 1 one quart container 1 one half pint
	 * container
	 * 
	 * The total price is $18.85
	 * 
	 * Thank you for your business!
	 * 
	 * 
	 */
	public String toString() {
		
		// Format TotalPaint with 3 digits
		String a = String.format("%.3f",TotalPaint);
		
		// Gets total half pint 
		TotalHalfPint = TotalPaint*16; //41.880*16=670.08
		
		// initialize some number
		fiveGa=0;oneGa=0;halfGa=0;quart=0;pint=0;halfPint=0;
		
		// using loop to break down total half pint
		while(TotalHalfPint>1){
			
			// if TotalHalfPint > 80 , it will continue
			while(TotalHalfPint>80){
				
				
				TotalHalfPint=TotalHalfPint-80;
				
				// if TotalHalfPint still > 80, fiveGa will add 1.
				fiveGa=fiveGa+1;
			}
			// if TotalHalfPint > 16 , it will continue
			while(TotalHalfPint>16){
				TotalHalfPint=TotalHalfPint-16;
				oneGa=oneGa+1;
			}
			// if TotalHalfPint > 8 , it will continue
			while(TotalHalfPint>8){
				TotalHalfPint=TotalHalfPint-8;
				halfGa=halfGa+1;
			}
			// if TotalHalfPint > 4 , it will continue
			while(TotalHalfPint>4){
				TotalHalfPint=TotalHalfPint-4;
				quart=quart+1;
			}
			// if TotalHalfPint > 2 , it will continue
			while(TotalHalfPint>2){
				TotalHalfPint=TotalHalfPint-2;
				pint=pint+1;
			}
			// if TotalHalfPint > 1 , it will continue
			while(TotalHalfPint>1){
				TotalHalfPint=TotalHalfPint-1;
				halfPint=halfPint+1;
			}
		}
		if(TotalHalfPint>0 && TotalHalfPint<1){
			halfPint=1;
		}
		
		TotalPrice = fiveGa*FIVEGALLONS+oneGa*ONEGALLON+halfGa*HALFGALLON+quart*QUART+pint*PINT+halfPint*HALFPINT;
		String h = String.format("\n        The total price is $%.2f\n",TotalPrice);
		if(fiveGa!=0){
			b = "        " + fiveGa + " five gallons containers\n";
		}else{
			b ="";
		}
		if(oneGa!=0){
			c = "        " + oneGa + " one gallons containers\n";
		}else{
			c ="";
		}
		if(halfGa!=0){
			d = "        " + halfGa + " one half gallons containers\n";
		}else{
			d = "";
		}
		if(quart!=0){
			e = "        " + quart + " one quart container\n";
		}else{
			e = "";
		}
		if(pint!=0){
			f = "        " + pint + " one pint container\n";
		}else{
			f = "";
		}
		if(halfPint!=0){
			g = "        " + halfPint + " one half pint container";
		}else{
			g = "";
		}

		String s = "For this job, you need "+ a +" gallons of paint.\nYou will need to purchase\n"+b+c+d+e+f+g+h+"        Thank you for your business!"
				; // CHANGE THIS!
		
		return s;
	}
}
