class No{
	int v;
	No next;
	No(int v){
		this.next = null;
		this.v = v;
	}
}

class Lista{
	No head;
	Lista(){
		this.head = null;
	}	
	void push (int v){
		No novo = new No(v);
		if (head == null){
			head = novo;
		} else {
			No aux = head;
			while (aux.next!=null){
				aux = aux.next;
			}
			aux.next = novo;
		}
	}
	
}

public class L4Q2{
	
	static void push(Lista[] graph, int u, int v){
		graph[u].push(v);
		graph[v].push(u);
	}
	
	static boolean corAdj(Lista[] graph, int[] cores, int v, int cor){
		No aux = graph[v].head;
		while(aux!= null){
			if(cores[aux.v] == cor) return true;
			aux = aux.next;
		}
		return false;
	}
	
	static boolean ncoloring(Lista[] graph, int[] cores, int v, int nColours, int n){
		if(v == n) return true;
		int w = v+1;
		for(int i = 1; i<= nColours; ++i){
			if(!corAdj(graph, cores, w, i)){
				cores[w] = i;
				if(ncoloring(graph, cores, w, nColours, n))return true;
			}
		}
		cores[w] = 0;
		return false;
	}

	static void print(int[] colours, Arquivo arq){
		boolean notFirst = false;
		for (int i=1; i<colours.length; ++i){	
				if (!notFirst){
					arq.print(colours[i]);
					notFirst = true;
				} else {
					arq.print(" " + colours[i]);
				}
			}
	}
	
	public static void main(String[] args) {
		int n, m, u, v, nColours;
		Lista[] graph;
		int[] colours;
		
		Arquivo arq = new Arquivo("L4Q2.in", "L4Q2.out");
		while (!arq.isEndOfFile()){
			n = arq.readInt();
			m = arq.readInt();
			graph = new Lista[n+1];
			colours = new int[n+1];
			for (int i=1; i<n+1; ++i){
				graph[i] = new Lista();
			}
			for (int i=0; i<m; ++i){
				u = arq.readInt();
				v = arq.readInt();
				push(graph, u, v);
			}
			colours[1]=1;
			nColours = 1;
			while (!ncoloring(graph, colours, 1, nColours, n)){
				nColours++;
			}
			print(colours, arq);
			arq.println();
		}
	}
}