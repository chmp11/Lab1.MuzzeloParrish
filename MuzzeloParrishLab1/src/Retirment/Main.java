package Retirment;
// next line imports the FinaceLib from apache
import org.apache.poi.ss.formula.functions.FinanceLib;
//next line imports the scanner to interact with users
import java.util.Scanner; 

public class Main {
	public static void main(String[] args) {
		//this defines the types of all of the variables needed for calculations
		double rate;
		double numberofyears;
		double yearlypayment;
		double futurevalue;
		boolean type;
		
		double PV;
		//next line starts the scanner
		Scanner input = new Scanner(System.in);
		//asks the user for the r value which is used for the rate calculation
		System.out.print("Enter the annual rate of interest while retired as a decimal(between .00-.03: ");
		double r = input.nextDouble();
		//Loop ensures the user enters a rate within the defined range
		while (r<.0 || r>.03){
			System.out.print("The value you entered is not in the acceptable range, please enter a value between .00 and .03");
			r=input.nextDouble();
		}
		//asks the user to enter value that will be used for the number of years calculation
		System.out.print("Enter the number of years you expect to be retired for: ");
		double n = input.nextDouble();
		//asks the user for a value that determines how much money they need to support themselves
		System.out.print("Enter the the monthly amount of money you expect to need while retired: ");
		double m = input.nextDouble();
		//asks the user for their SSI benefits
		System.out.print("Enter your expected social security benefits: ");
		double s = input.nextDouble();
		//ensures the user does not exceed the maximum value of SSI benefits
		while (s>2642){
			System.out.print("The value you enter exceeds the maximum dolled out by Uncle Sam, please enter a number less than $2642");
			s=input.nextDouble(); }
		//the PV function requires a monthly rate, so the annual rate must be divided by 12
		rate=(r/12);
		//the PV function requires the number of months so years must be multiplied by 12
		numberofyears=(n*12);
		//the money that must be saved for each month is the m-s to account for SSI benefits
		yearlypayment=(m-s);
		//this assumes the user has not saved any money for retirement yet
		futurevalue=0.0;
		//false ensures that it is run at the after the scanner asks the questions
		type=false;
		//uses the FinanceLib function to determine the amount of money you will need for retirement
		PV=FinanceLib.pv(rate,numberofyears,yearlypayment,futurevalue,type);
		
		/*System.out.println(PV); 
		 we used this to make sure the PV function was working correctly
		 */
		
		double PMT;
		//asks the user for the rate of interest during the investment period
		System.out.print("Enter the annual rate of interest while investing as a decimal(between .00-.20): ");
		double r2 = input.nextDouble();
		//Loop ensures the user enters a rate within the defined range for investing
		while (r2<.0 || r2>.20){
			System.out.print("The value you entered is not in the acceptable range, please enter a value between .00 and .20");
			r2=input.nextDouble();
		}
		System.out.print("Enter the number of years you expect to work for: ");
		double x = input.nextDouble();
		//the PV function requires a monthly rate, so the annual rate must be divided by 12
		rate=(r2/12);
		//the PV function requires the number of months so years must be multiplied by 12
		numberofyears=(x*12);
		//the yearly payment during investment is zero because you are not removing anything from the bank account
		yearlypayment=(0);
		//future value is the value we calculated from the PV function
		futurevalue=PV;
		//false ensures that it is run at the after the scanner asks the questions
		type=false;
		//uses the FinanceLib function to determine the amount of money you will need to save each month
		PMT=FinanceLib.pmt(rate,numberofyears,yearlypayment,futurevalue,type);
		
		/*System.out.println(PMT);
		 we used this to test that the PMT function was working correctly
		 */
		//next line prints a string that tells the user how much they need to save each month to meet their goals
		System.out.printf("The monthly amount you need to save is $%.2f", PMT);
}
}