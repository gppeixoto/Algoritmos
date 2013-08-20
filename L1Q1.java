class Disco{
	int diam;
	Disco next;
	Disco(int diam){
		this.diam = diam;
		this.next = null;
	}
}

class Stack{
	Disco top;
	Stack(){
		this.top = null;
	}

	public void push(int diam){
		Disco novo = new Disco(diam);
		novo.next = top;
		top = novo;
	}

	public int pop(){	
		if (top==null) return -1;
		int r=top.diam;
		top = top.next;
		return r;
	}

	public int top(){
		if (top!=null) return top.diam;
		return -1;
	}
}

public class L1Q1 {

	public static void main(String[] args) {
		Arquivo arq = new Arquivo("L1Q1.in", "L1Q1.out");
		int n;
		int nmaior;

		while(!arq.isEndOfFile()){
			Stack a = new Stack();
			int cases = arq.readInt();
			for (int i=0; i<cases; i++){
				n = arq.readInt();
				nmaior = n;
				for (int j=0; j<n; j++){
					a.push(arq.readInt());
				}
				int[]numPassado=new int[210];
				int[]quemPraquem=new int[210]; 
				int indice=0;	

				Stack b = new Stack();
				Stack c = new Stack();

				while (a.top()!=-1){
					numPassado[indice]=a.top();
					quemPraquem[indice]=0;
					indice++;
					b.push(a.pop());
					while (b.top()==nmaior) {
						numPassado[indice]=b.top();
						quemPraquem[indice]=1;
						indice++;
						c.push(b.pop());
						nmaior--;
					}
				}
				boolean impossivel = false;
				while (b.top()!=-1 && impossivel==false){
					if (b.top()>c.top()){
						impossivel = true;
						arq.println("impossivel");
					} else {
						c.push(b.pop());
					}
				}

				//------------print-------------//
				if (!impossivel){
					for (int j=0; numPassado[j]!=0; j++){
						//StringBuffer sb = new StringBuffer();
						if (quemPraquem[j]==1){
							//sb.append("tirar ").append(numPassado[j]).append(" de B e colocar em C");
							arq.println("tirar " + numPassado[j]
									+ " de B e colocar em C");
						} else {
							//sb.append("tirar ").append(numPassado[j]).append(" de A e colocar em B");
							arq.println("tirar " + numPassado[j]
									+ " de A e colocar em B");
						}
					}
				}
				arq.println();
			}
		}
		arq.close();
	}
}