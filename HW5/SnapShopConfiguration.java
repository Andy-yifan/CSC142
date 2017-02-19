// Write your short report here (-2 if there is no report)
// try to create class Negative, blackWhite, getDark, used to create a class that can rotate 90бу but doesn't work
// Used to get stuck in the Negative class, but by search on net, finally find out.
// The blackWhite one spent me long time to think if the rgb larger than a number then turn to 255,and less that 
// number turn to 0, but doesn't work, and finally got that just change the rgb into lower number ,that's it.
// then, I find if rgb gets 0 is dark, so i create getDark:P
// this project is very interesting, but i still want to see how other transformations can be done.
// difficult to find good value can change the picture meaningful...
/**
 * A class to configure the SnapShop application
 * 
 * @author Yifan	Yu
 * @version 3/13/2015
 */
public class SnapShopConfiguration {
	/**
	 * Method to configure the SnapShop. Call methods like addFilter and
	 * setDefaultFilename here.
	 * 
	 * @param theShop
	 *            A pointer to the application
	 */
	public static void configure(SnapShop theShop) {

		theShop.setDefaultFilename("billg.jpg");
		theShop.addFilter(new FlipHorizontalFilter(), "Flip Horizontal");
		theShop.addFilter(new FlipVerticalFilter(), "Flip Horizontal");
		// add your other filters below
		theShop.addFilter(new GaussianFilter(), "Flip Gaussian");
		theShop.addFilter(new UnsharpMaskingFilter(), "Flip UnsharpMasking");
		theShop.addFilter(new LaplacianFile(), "Flip Laplacian");
		theShop.addFilter(new Negative(), "Flip negative");
		theShop.addFilter(new EdgyFile(), "Flip Edgy");
		theShop.addFilter(new BlackWhite(), "Flip BlackWhite");
		theShop.addFilter(new GetDark(), "Flip GetDark");
		//theShop.addFilter(new Try(), "Flip try");
	}
}
