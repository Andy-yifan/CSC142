
public class LaplacianFile implements Filter {

	@Override
	public void filter(PixelImage pi) {
		int[][] weights = {{-1,-1,-1},{-1,8,-1},{-1,-1,-1}};
		pi.transformImage(weights);
		//transformImages is where the computation of the 
		// new pixels is done
	}

}
