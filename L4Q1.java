public class L4Q1{
	
	static boolean isAttacked(int[] cols, int tryrow) {
		int row, col = cols[tryrow];
		for (int prev = 1; prev <= tryrow; prev++) {
			row = cols[tryrow - prev];
			if (row==col || row==col-prev || row==col+prev) return true;
		}
		return false;
	}

	public static int getThisSolution(int[][] table, int[] cols) {
		int max=0;
		for (int i=0; i<8; ++i){
			for (int j=0; j<8; ++j) {
				if ((cols[i] == j))max+=table[i][j];
			}
		}
		return max;
	}

	public static int getOptimal(int[] cols, int col, int aux, int[][] table, int max){
		cols[0] = -1;
		while (col >= 0) {
			do cols[col]++; while ((cols[col] < 8) && isAttacked(cols, col));
			if (cols[col] < 8) {
				if (col < 7) cols[++col] = -1;
				else {
					aux = getThisSolution(table, cols);
					if (aux>max) max=aux;
				}
			} else col--;
		}
		return max;
	}

	public static void main(String[] args) {
		Arquivo arq = new Arquivo("L4Q1E2.in", "L4Q1.out");
		int[] cols = new int[8];
		int[][] table = new int[8][8];
		int col, max, aux;
		while (!arq.isEndOfFile()){
			for (int i=0; i<8; ++i){
				for (int j=0; j<8; ++j){
					table[i][j] = arq.readInt();
				}
			}
			col=0; max=0; aux=0; max=getOptimal(cols, col, aux, table, max);
			arq.println(max);
		}
	}
}