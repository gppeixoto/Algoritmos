public class L2Q1 {
	public static void main(String[] args) {
		Arquivo arq = new Arquivo("L2Q1.in", "L2Q1.out");
		while (!arq.isEndOfFile()){
			int x = arq.readInt();
			while (x!=0){
				int verde = 1;
				int red = 999;
				int blue = 500;
				int i=0;
				while (blue!= x){
					if (x>blue){
						verde = blue+1;
						blue = (verde+red)/2;
						i++;
					} else 	{
						red = blue;
						blue = (verde+red)/2;
						i++;
					}
				}
				arq.println(i*10);
				x=arq.readInt();
			}
		}
	}
}