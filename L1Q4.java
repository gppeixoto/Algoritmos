class Musico{
	Musico next;
	String name;
	int timbre;

	Musico(String name, int timbre){
		this.name = name;
		this.timbre = timbre;
		this.next = null;
	}
}

class Queue{
	Musico tail;
	Musico head;

	Queue(){
		this.tail = null;
		this.head = null;
	}

	void enqueue(String name, int timbre){
		Musico novo = new Musico(name, timbre);
		if (head == null){
			head = novo;
			tail = novo;
			tail.next = null;
		} else {
			if (head.next==null){
				head.next = novo;
				tail = novo;
				tail.next = null;
			} else {
				tail.next = novo;
				novo = tail;
				tail = tail.next;
			}
		}
	}

	boolean isEmpty(){
		if (head==null) return true;
		return false;
	}

	void dequeue(){
		if (!isEmpty()){
			Musico aux = head;
			head = head.next;
			enqueue(aux.name, aux.timbre);
		}
	}
	
}

public class L1Q4 {

	public static void main(String[] args) {
		Queue[] tbs = new Queue[20];
		int tbCurr = 0;
		for (int i=0; i<20; i++){
			tbs[i] = new Queue();
		}

		Arquivo arq = new Arquivo("L1Q4.in", "L1Q4.out");
		while (!arq.isEndOfFile()){
			int a = arq.readInt();
			switch (a){
			case 1: //adcionar músico
				String name = arq.readString();
				int timbre = arq.readInt();
				switch (timbre){
				case 1:
					tbs[0].enqueue(name, timbre);
					break;
				case 2:
					tbs[1].enqueue(name, timbre);
					break;
				case 3:
					tbs[2].enqueue(name, timbre);
					break;
				case 4:
					tbs[3].enqueue(name, timbre);
					break;
				case 5:
					tbs[4].enqueue(name, timbre);
					break;
				case 6:
					tbs[5].enqueue(name, timbre);
					break;
				case 7:
					tbs[6].enqueue(name, timbre);
					break;
				case 8:
					tbs[7].enqueue(name, timbre);
					break;
				case 9:
					tbs[8].enqueue(name, timbre);
					break;
				case 10:
					tbs[9].enqueue(name, timbre);
					break;
				case 11:
					tbs[10].enqueue(name, timbre);
					break;
				case 12:
					tbs[11].enqueue(name, timbre);
					break;
				case 13:
					tbs[12].enqueue(name, timbre);
					break;
				case 14:
					tbs[13].enqueue(name, timbre);
					break;
				case 15:
					tbs[14].enqueue(name, timbre);
					break;
				case 16:
					tbs[15].enqueue(name, timbre);
					break;
				case 17:
					tbs[16].enqueue(name, timbre);
					break;	
				case 18:
					tbs[17].enqueue(name, timbre);
					break;
				case 19:
					tbs[18].enqueue(name, timbre);
					break;
				case 20:
					tbs[19].enqueue(name, timbre);
					break;
				}
				break;
			case 2:
				int aumentar = arq.readInt();
				if (tbCurr+aumentar>19){ //aumentou para acima de 19
					tbCurr = 19;
					while (tbs[tbCurr].head==null){
						tbCurr--;
					}
					arq.println(tbs[tbCurr].head.name);
				} else if (tbs[tbCurr + aumentar].head==null){ //não encontrou 
					tbCurr = tbCurr + aumentar;//músico no timbre desejado
					boolean out = false;
					while (tbs[tbCurr].head==null && !out){
						if (tbCurr==19){
							out = true; //chegou em i=19
						} else {
							tbCurr++;							
						}
					}//e não encontrou ninguém
					if (out){
						while (tbs[tbCurr].head==null){
							tbCurr--;
						}
						arq.println(tbs[tbCurr].head.name);
					} else { //encontrou antes de chegar no i=0
						arq.println(tbs[tbCurr].head.name);
					}
				} else if (tbs[tbCurr+aumentar].head!=null) { //encontrou de primeira
					tbCurr = tbCurr + aumentar;
					arq.println(tbs[tbCurr].head.name);
				}
				tbs[tbCurr].dequeue();
				break;
				/*tbCurr = tbCurr + arq.readInt();
				if (tbs[tbCurr].head!=null) arq.println(tbs[tbCurr].head.name);
				tbs[tbCurr].dequeue();
				break;*/
			case 3:
				int baixar = arq.readInt();
				if (tbCurr-baixar<0){ //abaixou para abaixo de 0
					tbCurr = 0;
					while (tbs[tbCurr].head==null){
						tbCurr++;
					}
					arq.println(tbs[tbCurr].head.name);
				} else if (tbs[tbCurr-baixar].head==null){ //não encontrou 
					tbCurr = tbCurr - baixar;//músico no timbre desejado
					boolean out = false;
					while (tbs[tbCurr].head==null && !out){
						if (tbCurr==0){
							out = true; //chegou em i=0
						} else {
							tbCurr--;							
						}
					}//e não encontrou ninguém
					if (out){
						while (tbs[tbCurr].head==null){
							tbCurr++;
						}
						arq.println(tbs[tbCurr].head.name);
					} else { //encontrou antes de chegar no i=0
						arq.println(tbs[tbCurr].head.name);
					}
				} else if (tbs[tbCurr-baixar].head!=null) { //encontrou de primeira
					tbCurr = tbCurr - baixar;
					arq.println(tbs[tbCurr].head.name);
				}
				tbs[tbCurr].dequeue();
				break;
			case 4:
				while (tbs[tbCurr].head==null){
					tbCurr++;
				}
				arq.println(tbs[tbCurr].head.name);
				tbs[tbCurr].dequeue();
				break;
			case 0:
				tbs = new Queue[20];
				tbCurr = 0;
				for (int i=0; i<20; i++){
					tbs[i] = new Queue();
				}
				if (!arq.isEndOfFile()) arq.println();
				break;
			}
		}
		arq.close();
	}
}
