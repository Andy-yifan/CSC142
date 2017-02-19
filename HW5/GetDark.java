public class GetDark implements Filter {

	@Override
	public void filter(PixelImage pi) {
		
			Pixel[][] data = pi.getData();
			for (int i = 0;i<pi.getHeight();i++){
				for(int j = 0; j<pi.getWidth();j++){
					// set the rgb in the same value
					data[i][j].red = (data[i][j].red+data[i][j].green+data[i][j].blue)/4;
					data[i][j].green = (data[i][j].red+data[i][j].green+data[i][j].blue)/4;
					data[i][j].blue = (data[i][j].red+data[i][j].green+data[i][j].blue)/4;
				}
			}
			pi.setData(data);
		}
}
