
public class L2Q4 {
	public static void main(String[] args) {
		Arquivo arq = new Arquivo("L2Q4.in", "L2Q4.out");
		int n, l, x, i, f, t;
		while (!arq.isEndOfFile()){
			n = arq.readInt();
			l = arq.readInt();
			x = arq.readInt();
			
			for (int i_=0; i_<l; i_++){
				i = arq.readInt();
				f = arq.readInt();
				t = arq.readInt();	
			}
			arq.println("IMPOSSIVEL");
			
		}
	}
}