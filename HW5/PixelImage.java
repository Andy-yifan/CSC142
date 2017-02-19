import java.awt.image.*;

/**
 * Provides an interface to a picture as an array of Pixels
 */
public class PixelImage {
	private BufferedImage myImage;
	private int width;
	private int height;
	

	/**
	 * Map this PixelImage to a real image
	 * 
	 * @param bi
	 *            The image
	 */
	public PixelImage(BufferedImage bi) {
		// initialise instance variables
		this.myImage = bi;
		this.width = bi.getWidth();
		this.height = bi.getHeight();
	}

	/**
	 * Return the width of the image
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Return the height of the image
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Return the BufferedImage of this PixelImage
	 */
	public BufferedImage getImage() {
		return this.myImage;
	}

	/**
	 * Return the image's pixel data as an array of Pixels. The first coordinate
	 * is the x-coordinate, so the size of the array is [width][height], where
	 * width and height are the dimensions of the array
	 * 
	 * @return The array of pixels
	 */
	public Pixel[][] getData() {
		Raster r = this.myImage.getRaster();
		Pixel[][] data = new Pixel[r.getHeight()][r.getWidth()];
		int[] samples = new int[3];

		for (int row = 0; row < r.getHeight(); row++) {
			for (int col = 0; col < r.getWidth(); col++) {
				samples = r.getPixel(col, row, samples);
				Pixel newPixel = new Pixel(samples[0], samples[1], samples[2]);
				data[row][col] = newPixel;
			}
		}

		return data;
	}

	/**
	 * Set the image's pixel data from an array. This array matches that
	 * returned by getData(). It is an error to pass in an array that does not
	 * match the image's dimensions or that has pixels with invalid values (not
	 * 0-255)
	 * 
	 * @param data
	 *            The array to pull from
	 */
	public void setData(Pixel[][] data) {
		int[] pixelValues = new int[3]; // a temporary array to hold r,g,b
										// values
		WritableRaster wr = this.myImage.getRaster();

		if (data.length != wr.getHeight()) {
			throw new IllegalArgumentException("Array size does not match");
		} else if (data[0].length != wr.getWidth()) {
			throw new IllegalArgumentException("Array size does not match");
		}

		for (int row = 0; row < wr.getHeight(); row++) {
			for (int col = 0; col < wr.getWidth(); col++) {
				pixelValues[0] = data[row][col].red;
				pixelValues[1] = data[row][col].green;
				pixelValues[2] = data[row][col].blue;
				wr.setPixel(col, row, pixelValues);
			}
		}
	}

	

	

	// add a method to compute a new image given weighted averages
	public void transformImage(int[][] weights) {
		Pixel[][] data1 = getData();
		Pixel[][] data2 = getData();
		int total = 0;// this is the total weights, use for loop that i don't need to create several transformImage method
		for (int i = 0; i<3;i++){
			for (int j = 0; j<weights.length;j++){
				total += weights[i][j];
			}
		}
		if(total == 0){
			total = 1;
		}
		
		
		//
		for(int i = 1; i <getHeight()-1;i++){
			for(int j = 1; j< getWidth()-1;j++){
				// choose the center one as the original point
				// by 3*3, each row or col move 1 pixel in x, y direction
				Pixel p = data1[i][j];
				Pixel p1 = data1[i-1][j-1];
				Pixel p2 = data1[i-1][j];
				Pixel p3 = data1[i-1][j+1];
				Pixel p4 = data1[i][j-1];
				Pixel p5 = data1[i][j+1];
				Pixel p6 = data1[i+1][j-1];
				Pixel p7 = data1[i+1][j];
				Pixel p8 = data1[i+1][j+1];
				//average the rgb
				int avgr = ((p1.red*weights[0][0]+p2.red*weights[0][1]+p3.red*weights[0][2]+ //first line
						p4.red*weights[1][0]+p.red*weights[1][1]+p5.red*weights[1][2]+        //second line
						p6.red*weights[2][0]+p7.red*weights[2][1]+p8.red*weights[2][2])/total); // third line add get avg
				
				int avgg = ((p1.green*weights[0][0]+p2.green*weights[0][1]+p3.green*weights[0][2]+
						p4.green*weights[1][0]+p.green*weights[1][1]+p5.green*weights[1][2]+
						p6.green*weights[2][0]+p7.green*weights[2][1]+p8.green*weights[2][2])/total);
				
				int avgb = ((p1.blue*weights[0][0]+p2.blue*weights[0][1]+p3.blue*weights[0][2]+
						p4.blue*weights[1][0]+p.blue*weights[1][1]+p5.blue*weights[1][2]+
						p6.blue*weights[2][0]+p7.blue*weights[2][1]+p8.blue*weights[2][2])/total);
				
				// in case the rgb don't over count
				if (avgr<0){
					avgr = 0;
				}if(avgr>255){
					avgr = 255;
				}
				if (avgg<0){
					avgg = 0;
				}if(avgg>255){
					avgg = 255;
				}
				if (avgb<0){
					avgb = 0;
				}if(avgb>255){
					avgb = 255;
				}
				//store new values
				data2[i][j]= new Pixel(avgr,avgg,avgb);
				
			}
		}
		setData(data2);
	}
}


