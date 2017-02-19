
public class Try implements Filter {
	public void filter(PixelImage pi) {
		int[][] weights = {{1,2,2,1},{4,2,2,4},{2,4,4,2},{1,2,2,1}};
		pi.transformImage(weights);
		//transformImages is where the computation of the 
		// new pixels is done
	}
}
