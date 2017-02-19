
public class Negative implements Filter {

	@Override
	public void filter(PixelImage pi) {
		Pixel[][] data = pi.getData();
		for (int i = 0;i<pi.getHeight();i++){
			for(int j = 0; j<pi.getWidth();j++){
				// change the color in the negative one
				int negR = 255-data[i][j].red;
				int negG = 255-data[i][j].green;
				int negB = 255-data[i][j].blue;
				// set color negative
				data[i][j].red = negR;
				data[i][j].green = negG;
				data[i][j].blue = negB;
			}
		}
		pi.setData(data);
	}

}