public class L4Q3{

	static void knapsack(int table[][], int weight[], int sum){
		table[0][0]=1;
		for (int i=1; i<weight.length; ++i){
			for (int k=0; k<sum+1; ++k){
				if (table[i-1][k]==1){
					table[i][k]=1;
				} else if (k-weight[i]>=0){
					if (table[i-1][k-weight[i]]==1){
						table[i][k]=1;
					}
				}
			}
		}
	}

	static void print(int[][] table, int sum, int n, Arquivo arq, StringBuffer sb){
		boolean notFirst = false;
		for (int i=1; i<sum+1; ++i){
			if (table[n][i]==1 && !notFirst){
				sb.append(i);
				notFirst = true;
			} else if (table[n][i]==1 && notFirst) {
				sb.append(" ").append(i);
			}
		}
		arq.println(sb.toString());
	}

	public static void main(String[] args) {
		Arquivo arq = new Arquivo("L4Q3E3.in", "L4Q3.out");
		int n, q, k, sum, aux, i;
		int table[][]; 
		int weight[];
		StringBuffer sb;
		while (!arq.isEndOfFile()){
			sb = new StringBuffer();
			n=0;
			q=0;
			k=0;
			sum=0;
			aux=0;
			i=1;
			n = arq.readInt();
			weight = new int[n+1];
			if (n!=0){
				while (aux<n){
					q = arq.readInt();
					k = arq.readInt();
					if (q>1){
						int a = q;
						while (a>0){
							weight[i]=k;
							--a;
							++i;
						}
					} else {
						weight[i] = k;
						++i;
					}
					aux+=q;					
					sum+=(q*k);
				}
				if (sum>10000)sum=10000;
				table = new int[weight.length][sum+1];
				knapsack(table, weight, sum);
				print(table, sum, n, arq, sb);
			}
		}

	}
}