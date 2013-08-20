public class L3Q4 {

	static int hash1(int cpf, int w, int n){
		int hash = (cpf * w)%n;
		return hash;
	}

	static int hash2(int hash, int z, int n){
		int foo = (hash + z)%n;
		return foo;
	}

	static int zBob(int cpf, int m, int o){
		return (cpf%m)+o;
	}

	public static void main(String[] args) {
		Arquivo arq = new Arquivo("L3Q4.in", "L3Q4.out");
		int m, n, o, w, z, pessoas;

		while (!arq.isEndOfFile()){
			m = arq.readInt();
			n = arq.readInt();
			o = arq.readInt();
			w = arq.readInt();
			z = arq.readInt();
			pessoas = arq.readInt();

			boolean[] hash_table = new boolean[n];
			int[] cpfs = new int[pessoas];
			int countBob=0;
			int countAlice=0;
			for (int i=0; i<pessoas; i++){
				cpfs[i] = arq.readInt();
			}
			for (int i=0; i<pessoas; i++){
				int hash1 = hash1(cpfs[i], w, n);
				if (!hash_table[hash1]){
					hash_table[hash1] = true;
				} else {
					while (hash_table[hash1]){
						hash1 = hash2(hash1, z, n);
						++countAlice;
					}
					hash_table[hash1]=true;
				}
			}
			//zera array
			for (int i=0; i<n; i++){
				hash_table[i]=false;
			}
			//zera array

			for (int i=0; i<pessoas; i++){
				int hash1 = hash1(cpfs[i], w, n);
				if (!hash_table[hash1]){
					hash_table[hash1] = true;
				} else {
					//zBob
					int zBob = zBob(cpfs[i], m, o);
					while (hash_table[hash1]==true){
						hash1 = hash2(hash1, zBob, n);
						++countBob;
					}
					hash_table[hash1]=true;
				}
			}
			arq.println(countAlice + " " + countBob);   
			for (int i=0; i<n; i++){
				hash_table[i]=false;
			}
		}
		arq.close();
	}
}