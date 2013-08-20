class Pac{
	String code;
	int outRec;
	int outEnf;
	int timeIn;
	boolean isPref;

	Pac(){
		this.code = null;
		this.outRec = 0;
		this.outEnf = 0;
		this.timeIn = 0;
		this.isPref = false;
	}
}

	class Rec{
		int time;
		boolean isPref;
		Rec(){
			this.time = 0;
			this.isPref = false;
		}
	}



public class L1Q3 {

	static void print(Pac[] pac_arr, Arquivo arq){
		for (int i=0; i<pac_arr.length; i++){
			arq.println(pac_arr[i].code + " " + (i*2 + pac_arr[i].timeIn + 7) + " " + pac_arr[i].timeIn);
		}
	}
	
	public static void sort_in (Pac[] pac_arr){
		boolean trocou = true;
		while (trocou) {
			trocou = false;
			for (int i = 0; i < (pac_arr.length)-1; i++){
				if (pac_arr[i].timeIn > pac_arr[i+1].timeIn){
					Pac aux = pac_arr[i+1];
					pac_arr[i+1] = pac_arr[i];
					pac_arr[i] = aux;
					trocou = true;
				}
			}
		}               
	}


	public static void main(String[] args) {
		Arquivo arq = new Arquivo("L1Q3.in", "L1Q3.out");
		int nrec;
		int nenf;
		int npac;
		int count = 0;

		while (!arq.isEndOfFile()){
			int iPac=1;
			int iPref=1;
			nrec = arq.readInt();
			nenf = arq.readInt();
			npac = arq.readInt();

			Rec[] cxa_arr = new Rec[nrec];
			Rec[] enf_arr = new Rec[nenf];
			Pac[] pac_arr = new Pac[npac];

			for (int i	=0; i<nrec-1 ; i++){
				cxa_arr[i] = new Rec();
				cxa_arr[i].time = arq.readInt();
			}
			cxa_arr[nrec-1] = new Rec();
			cxa_arr[nrec-1].time = arq.readInt();
			cxa_arr[nrec-1].isPref = true;
			for (int i=0; i<nenf; i++){
				enf_arr[i] = new Rec();
				enf_arr[i].time = arq.readInt();
			}
			for (int i=0; i<npac; i++){
				pac_arr[i] = new Pac();
				pac_arr[i].timeIn = arq.readInt();
				char c = arq.readString().charAt(0);
				if (c=='P'){
					pac_arr[i].isPref = true;
					pac_arr[i].code = "P" + iPref;
					iPref++;
				} else {
					pac_arr[i].code = "C" + iPac;
					iPac++;
				}
			}
			count++;
			sort_in(pac_arr);
			arq.println("Dia #" + count);
			arq.println();
			print(pac_arr, arq);
			arq.println();
			arq.println();


		}
		arq.close();

	}
}
