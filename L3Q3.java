class Node{
	int val;
	Node next;
	Node (int val){
		this.val = val;
		this.next = null;
	}
}

class Graph{
	int[] marks;
	int[] pais;
	List[] listAdj;
	Graph(int n){
		this.listAdj = new List[n];
		this.marks = new int[n];
		this.pais = new int[n];
		for (int i=0; i<n; ++i){
			this.listAdj[i] = new List();
			this.marks[i] = 0;
			this.pais[i] = 0;
		}
	}
	
	Ret dfs(int source, List[] grafo, int[] pais, int[] marks, int cnt){
		Ret ret = new Ret(grafo, pais, marks, cnt);
		ret.marks[source] = 1;
		Node aux = ret.grafo[source].begin;
		while (aux!=null){
			if (marks[aux.val]==0){
				pais[aux.val]=source;
				ret = dfs(aux.val, ret.grafo, ret.parents, ret.marks, ret.cnt);
				ret.marks[aux.val]=0; //0:white 1:gray 2:black
			} else if (ret.marks[aux.val]==1){
				if (aux.val!=ret.parents[source]){
					++ret.cnt;
				}
			}
			aux=aux.next;
		}
		ret.marks[source]=2;
		return ret;
	}
}

class Ret{
	int[] marks;
	int[] parents;
	List[] grafo;
	int cnt;
	Ret(List[] grafo, int[] pais, int[] marks, int cnt){
		this.grafo = grafo;
		this.parents = pais;
		this.marks = marks;
		this.cnt = cnt;
	}
	
}

class List{
	Node begin;
	List(){
		this.begin = null;
	}
	
	void insert(int val){
		Node novo = new Node(val);
		if (begin == null){
			begin = novo;
		} else {
			Node aux = begin;
			while (aux.next!=null){
				aux=aux.next;
			}
			aux.next = novo;
			novo.next = null;
		}
	}	
}

public class L3Q3{
	
	static void initialize(Graph g, int n){
		for (int i=0; i<n; ++i){
			g.listAdj[i] = new List();
			g.pais[i]=0;
			g.marks[i]=0;
		}
	}
	
	static void insertGrafo(Graph g, int u, int v){
		g.listAdj[u].insert(v);
		g.listAdj[v].insert(u);
	}
	
	public static void main(String[] args) {
		Arquivo arq = new Arquivo("L3Q3.in", "L3Q3.out");
		int n, m, cases, u, v;
		cases =1;
		int cnt;
		Graph g;
		while (!arq.isEndOfFile()){
			n=arq.readInt();
			m=arq.readInt();
			g = new Graph(n);
			initialize(g, n);
			for (int i=0; i<m; ++i){
				u=arq.readInt();
				v=arq.readInt();
				insertGrafo(g, u, v);
			}
			cnt=0;
			Ret ret = g.dfs(0, g.listAdj, g.pais, g.marks, cnt);
			arq.println("Caso " + cases + ": " + ret.cnt);
			++cases;
		}
	}
}