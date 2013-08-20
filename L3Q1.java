class max{
	static int n = 100;
	static int v = 10000;
}

class No{
	No next;
	int val;
	No (int val){
		this.next=null;
		this.val=val;
	}
}

class NoHeap{
	int target;
	int custo;
	NoHeap(int target, int custo){
		this.target=target;
		this.custo=custo;
	}
}


class Heap{
	NoHeap[] h;
	int heap_size;
	Heap(){
		h = new NoHeap[max.n];
		this.heap_size=0;
	}
	
	void swap(int i, int j){
		NoHeap aux = h[i];
		h[i] = h[j];
		h[j] = aux;
	}
	
	void bubbleup(){
		int i = heap_size;
		while (i>1 && h[i].custo<=h[i/2].custo){
			swap(i, i/2);
			i = i/2;			
		}
	}

	void insert_num(NoHeap no){
		heap_size++;
		h[heap_size]=no;
		bubbleup();
	}

	void min_heapify(int i){
		int l=2*i;
		int r=(2*i)+1;
		int min = i;
		if (heap_size >= l && h[l].custo<h[min].custo){
			min = l;
		}
		if (r <= heap_size && h[r].custo<h[min].custo){
			min = r;
		}
		if (min!=i){
			NoHeap aux = h[min];
			h[min] = h[i];
			h[i] = aux;
			min_heapify(min);
		}
	}

	void build_minheap(){
		for (int i=(heap_size/2); i>0 ; i--){
			min_heapify(i);
		}
	}

	NoHeap extract_min(){
		NoHeap r = h[1];
		NoHeap aux = h[1];
		h[1] = h[heap_size];
		h[heap_size]=aux;
		h[heap_size]=new NoHeap(-1, max.n);
		min_heapify(1);
		return r;
	}
	
	boolean isEmpty(){
		return (heap_size==0);
	}

}

class Lista{
	No head;
	No tail;
	Lista(){
		this.head=null;
		this.tail=null;
	}	
	
	boolean isEmpty(){
		return (head==null);
	}
	
	No enqueue(int val){
		if (head == null){
			No novo = new No(val);
			head = novo;
			tail = novo;
			novo.next = null;
		} else {
			if (head!=null && head.next==null){
				No novo = new No(val);
				head.next = novo;
				novo.next = null;
				tail = novo;
			} else {
				No novo = new No(val);
				tail.next = novo;
				novo.next = null;
				tail = novo;
			}
		}
		return head;
	}

	No dequeue (){
		No aux = head;
		if (head!=null){
			if (head.next == null){
				head = null;
			} else {
				head = head.next;
			}
		}
		return aux;
	}
}

class Grafo{
	Lista[] adj = new Lista[max.n];
	int[][] custos = new int[max.n][max.n];
	Grafo(){
		for (int i=0; i<adj.length; i++){
			adj[i] = new Lista();
		}
	}

	void insert (int source, int target, int cost){
		adj[source].enqueue(target);
		adj[target].enqueue(source);
		custos[target][source] = cost;
		custos[source][target] = cost;
	}
	
	void init(int[] dist, int source){
		for (int i=0; i<dist.length; ++i){
			dist[i] = max.v;
		}
		dist[source]=0;
	}
	
	//dijkstra a ser ajeitado ainda
	void dijkstra(int source, int target){
		int[] dist = new int[max.n];
        Heap heap = new Heap();
        for(int i = 0;i<dist.length;++i){
                dist[i] = Integer.MAX_VALUE;
                //vis[i]='0';
        }
        dist[source] = 0;
        heap.insert_num(new NoHeap(source, dist[source]));
        while(!heap.isEmpty()){
                NoHeap n = heap.extract_min();
                //vis[n.val] = '1';
                if(!adj[n.target].isEmpty()){
                        No no = adj[n.target].head;
                        while(no!=null){
                                if(dist[no.val]>dist[n.target] + custos[n.target][no.val]){
                                        dist[no.val] = dist[n.target] + custos[n.target][no.val];
                                        heap.insert_num(new NoHeap(no.val,dist[no.val]));
                                }
                                no = no.next;
                        }
                }
        }
}

}


public class L3Q1 {
	public static void main(String[] args) {
		Grafo g = new Grafo();
		g.insert(1, 5, 6);
		g.insert(1, 2, 1);
		g.insert(1, 4, 1);
		g.insert(2, 5, 2);
		g.insert(4, 3, 1);
		g.insert(3, 5, 3);
		g.dijkstra(1, 5);
		
		//int ret[] = g.dijsktra(g.adj[2].head);
		//System.out.println(ret[3]);
		
		
		


		
		
		
	}
	
}
