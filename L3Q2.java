class Edge{
	int u, v, fav, cost;
	Edge(int u, int v, int cost, int fav){
		this.u = u;
		this.v = v;
		this.fav = fav;
		this.cost = cost;
	}
}

class EdgeList{
	Edge[] heap;
	int heap_size;
	EdgeList(int n){
		heap = new Edge[n];
		this.heap_size=0;
	}

	void swap(int i, int j){
		Edge aux = heap[i];
		heap[i] = heap[j];
		heap[j] = aux;
	}

	void bubbleup(){
		int i = heap_size;
		while (i>1 && heap[i].cost<=heap[i/2].cost){
			swap(i, i/2);
			i = i/2;			
		}
	}

	void insert_num(int u, int v, int c, int d){
		Edge no = new Edge(u, v, c, d);
		heap_size++;
		heap[heap_size]=no;
		bubbleup();
	}

	void max_heapify(int i){
		int l=2*i;
		int r=(2*i)+1;
		int min = i;
		if (heap_size >= l && heap[l].cost>heap[min].cost){
			min = l;
		}
		if (r <= heap_size && heap[r].cost>heap[min].cost){
			min = r;
		}
		if (min!=i){
			Edge aux = heap[min];
			heap[min] = heap[i];
			heap[i] = aux;
			max_heapify(min);
		}
	}

	void build_maxheap(){
		for (int i=(heap_size/2); i>0 ; i--){
			max_heapify(i);
		}
	}

	Edge extract_max(){
		Edge r = heap[1];
		Edge aux = heap[1];
		heap[1] = heap[heap_size];
		heap[heap_size]=aux;
		heap[heap_size]=new Edge(-1, -1, 0, 0);
		max_heapify(1);
		return r;
	}

	boolean isEmpty(){
		return (heap_size==0);
	}

	void heapsort(){
		build_maxheap();
		int a = heap_size;
		for (int i=a; i>0; --i){
			swap(1, i);
			--heap_size;
			max_heapify(1);
		}
		heap_size=a;
	}

}

public class L3Q2 {
	static void clean(int[] rep, int[] alfa, int m){
		for (int i=0; i<m+10; ++i){
			rep[i]=0;
			alfa[i]=0;
		}
	}


	static void initSet(EdgeList edges, int[] rep, int[] alfa){
		for (int i=1; i<500000; ++i){
			rep[i]=i;
			alfa[i]=0;
		}
	}

	static int findPc(int u, int[] rep){
		if (rep[u] != u){
			rep[u] = findPc(rep[u], rep);
		}
		return rep[u];
	}

	static void link(int u, int v, int[] rep, int[] alfa){
		if (u==v){
			return;
		}
		if (alfa[v] > alfa[u]){
			rep[u] = v;
		} else {
			rep[v] = u;
			if (alfa[v] == alfa[u]){
				++alfa[u];
			}
		}
	}

	static void union (int u, int v, int[] rep, int[] alfa){
		link(findPc(u, rep), findPc(v, rep), rep, alfa);
	}

	static int kruskal(EdgeList edges, int[] rep, int[] alfa){
		edges.heapsort();
		int cnt=0;
		for (int i=1; i<=edges.heap_size; ++i){
			if (findPc(edges.heap[i].u, rep)!=findPc(edges.heap[i].v, rep)){
				cnt+=edges.heap[i].cost;
				union(edges.heap[i].u, edges.heap[i].v, rep, alfa);
			}
		}
		return cnt;
	}

	public static void main(String[] args) {
		int[] rep, alfa;
		int n=100000, m=100000, u=0, v=0, c=0, d=0;
		int weight=0;
		int cnt=0;
		EdgeList edges;
		Arquivo arq = new Arquivo("L3Q2.in", "L3Q2.out");
		while (!arq.isEndOfFile()){
			n = arq.readInt();
			m = arq.readInt();
			edges = new EdgeList(m+1);
			rep = new int[500000];
			alfa = new int[500000];
			if (n!=0 && m!=0){
				for (int i=0; i<m; ++i){
					u = arq.readInt();
					v = arq.readInt();
					c = arq.readInt();
					weight+=c;
					d = arq.readInt();
					edges.insert_num(u, v, c, d);
				}
				initSet(edges, rep, alfa);
				for (int i=1; i<m+1; ++i){
					if (edges.heap[i].fav==1){
						cnt+=edges.heap[i].cost;
						union(edges.heap[i].u, edges.heap[i].v, rep, alfa);
					}
				}
				cnt += kruskal(edges, rep, alfa);
				arq.println(weight-cnt + " " + cnt);
				cnt=0;
				weight=0;
			}
		}
	}
}
