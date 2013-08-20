class Livro{
	String code;
	Livro next;
	Livro prev;
	Livro(String code){
		this.code = code;
		this.next = null;
		this.prev = null;
	}
}

class Lista{
	Livro head;
	Livro sent = new Livro("-1");
	Livro mark;
	boolean first;
	Lista(){
		this.head = null;
		this.sent.next=head;
		this.sent.prev=null;
		this.sent.code="-1";
		this.first = true;
		this.mark = head;
	}

	void printLista(Arquivo arq){
		Livro aux = head;
		while (aux!=null){
			arq.println("[" + aux.code + "]");
			aux = aux.next;
		}
	}

	boolean isEmpty(){
		if (head!=null) return false;
		return true;
	}

	String pass(String code){
		String str = code.substring(1, code.length()-1);
		return str;
	}

	void add(String code){
		code = pass(code);
		Livro novo = new Livro(code);
		if (head==null){ //lista vazia
			head = novo;
			novo.prev = sent;
			novo.next = null;
			mark = head;
		} else { //lista não vazia
			Livro curr = head;
			while (curr.next!=null && code.compareToIgnoreCase(curr.code)>0){
				curr = curr.next;
			}
			if (curr==head){ //lista com somente head
				if (code.compareToIgnoreCase(head.code)>0){ //inserir antes do head
					curr.next = novo;
					novo.prev = curr;
					novo.next = null;
				} else { //inserir depois do head
					curr.prev = novo;
					head = novo;
					head.next = curr;
					mark = head;
				}
			} else if (curr.next==null){ //chegou no final da lista
				if (code.compareToIgnoreCase(curr.code)>0){ //inserir no final da lista
					curr.next = novo;
					novo.prev = curr;
					novo.next = null;
				} else { //inserir logo antes do final da lista
					curr.prev.next=novo;
					novo.next=curr;
					novo.prev = curr.prev;
					curr.prev=novo;
				}
			} else if (curr.next!=null && curr.prev!=null){ //inserir no meio
				curr.prev.next=novo;
				novo.next=curr;
				novo.prev = curr.prev;
				curr.prev=novo;
			}
		}
	}

	void remove(int n){
		if (!isEmpty()){
			if (this.first){ //primeira remoção
				while (n>0){
					if (head!=null && head.next!=null) head.next.prev=head.prev;
					if (!isEmpty()) head = head.next;
					n--;
				}
				mark = head;
				this.first = false;
			} else { //a partir da segunda
				while (n>0){
					if (!isEmpty()){
						if (mark.next!=null && mark!=head){ //remoção no meio						
							mark.prev.next = mark.next;
							mark.next.prev = mark.prev;
							mark = mark.next;
						} else { //mark.next==null || mark==head
							if (mark==head && mark.next!=null){
								head.next.prev=head.prev;
								head = head.next;
								mark = head;
							} else { //mark.next=null
								if (mark==head){
									mark.prev = sent;
									sent.next = head = mark = null;
								} else {								
									mark.prev.next=null;
									mark = head;
								}
							}
						}
						n--;
					}
				}
			}
		} else {
			return;//isEmpty
		}
	}//remover

}

public class L1Q2 {
	public static void main(String[] args) {
		Arquivo arq = new Arquivo("L1Q2.in", "L1Q2.out");
		Lista l = new Lista();
		while (!arq.isEndOfFile()){
			char in = arq.readString().charAt(0);
			if (in!= '0'){
				switch (in){
				case 'A':
					l.add(arq.readString());
					break;
				case 'B':
					l.remove((arq.readInt()));
					break;
				}
			} else {
				l.printLista(arq);
				if (!arq.isEndOfFile()) arq.println();
				l = new Lista();
			}
		}
		arq.close();
	}
}