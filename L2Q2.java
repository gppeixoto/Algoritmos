class Retorno{
	Dep root;
	int v;
	BSTProd bstprod;
	Retorno(Dep root, int v, BSTProd bstprod){
		this.root = null;
		this.v = v;
		this.bstprod = new BSTProd();
	}
}

class RetornoProd{
	Prod root;
	int v;
	int qtd;
	RetornoProd(Prod root, int v, int qtd){
		this.root = root;
		this.v = v;
		this.qtd = qtd;
	}
}

class Prod{
	Prod left;
	Prod right;
	int codigo;
	int qtd;
	Prod (int codigo, int qtd){
		this.left = null;
		this.right = null;
		this.codigo = codigo;
		this.qtd = qtd;
	}
}

class Dep{
	Dep left;
	Dep right;
	BSTProd bst_prod;
	int code;
	Dep(int code){
		this.left = null;
		this.right = null;
		this.code = code;
		this.bst_prod = new BSTProd();
	}
}

class BSTProd{
	Prod root;
	BSTProd(){
		this.root = null;
	}

	Prod search(Prod root, int codigo){
		Prod aux = null;
		if (root!=null){
			if (root.codigo==codigo){
				return root;
			} else if (codigo < root.codigo){
				aux = search(root.left, codigo);
				return aux;
			} else {
				aux = search(root.right, codigo);
				return aux;
			}
		} else {
			return aux;
		}

	}

	Prod insert(Prod novo, Prod curr){
		if (curr==null){
			return novo;
		} else {
			if (novo.codigo < curr.codigo){
				curr.left = insert(novo, curr.left);
			} else if (novo.codigo==curr.codigo){ 
				curr.qtd = curr.qtd + novo.qtd;	
			} else {
				curr.right = insert(novo, curr.right);
			}
			return curr;
		}
	}

	RetornoProd del_min(Prod root){
		if (root.left == null){
			RetornoProd ret = new RetornoProd(root.right, root.codigo, root.qtd);
			root = null;
			return ret;
		} else {
			RetornoProd ret = del_min(root.left);
			root.left = ret.root;
			ret.root = root;
			return ret;
		}
	}

	Prod del(Prod in, int codigo){
		if (in == null) { }
		else if (in.codigo == codigo) {
			if (in.left == null && in.right == null) {
				in = null;
			}
			else if (in.left != null && in.right != null) {
				RetornoProd ret = del_min(in.right);
				in.right = ret.root;
				in.codigo = ret.v;
			} else if (in.left != null && in.right == null) {
				in = in.left;                              
			} else if (in.left == null && in.right != null) {
				in = in.right;
			}
		} else if (in.codigo > codigo) {
			in.left = del(in.left, codigo);
		} else if (in.codigo < codigo) {
			in.right = del(in.right, codigo);
		}
		return in;
	}

	void print(Prod root, Arquivo arq){
		if (root==null) return;
		print(root.left, arq);
		arq.println(root.codigo + " " + root.qtd);
		print(root.right, arq);
	}

}

class BSTDep {
	Dep root;
	BSTDep(){
	}

	Dep search(Dep root, int code){
		Dep aux = null;
		if (root==null){
			return root;
		}
		if (root.code==code){
			return root;
		} else if (code < root.code){
			aux = search(root.left, code);
			return aux;
		} else {
			aux = search(root.right, code);
			return aux;
		}
	}

	Dep insert(Dep novo, Dep att){
		if (att == null){
			return novo;
		} else {
			if (novo.code<att.code){
				att.left = insert(novo, att.left);
			} else {
				att.right = insert(novo, att.right);
			}
			return att;
		}
	}

	Retorno del_min(Dep root){
		if (root.left == null){
			Retorno ret = new Retorno(root.right, root.code, root.bst_prod);
			root = null;
			return ret;
		} else {
			Retorno ret = del_min(root.left);
			root.left = ret.root;
			ret.root = root;
			return ret;
		}
	}

	Dep del(Dep in, int code){
		if (in == null) { }
		else if (in.code == code) {
			if (in.left == null && in.right == null) {
				in = null;
			}
			else if (in.left != null && in.right != null) {
				Retorno ret = del_min(in.right);
				in.right = ret.root;
				in.code = ret.v;
				in.bst_prod = ret.bstprod;
			} else if (in.left != null && in.right == null) {
				in = in.left;                              
			} else if (in.left == null && in.right != null) {
				in = in.right;
			}
		} else if (in.code > code) {
			in.left = del(in.left, code);
		} else if (in.code < code) {
			in.right = del(in.right, code);
		}
		return in;
	}

	void print(Dep root, Arquivo arq){
		if (root==null)return;
		print(root.left, arq);
		arq.println("DEPARTAMENTO " + root.code);
		root.bst_prod.print(root.bst_prod.root, arq);
		print(root.right, arq);
	}

}


public class L2Q2{
	public static void main(String[] args) {

		Arquivo arq = new Arquivo("L2Q2.in", "L2Q2.out");
		BSTDep bstdep = new BSTDep();
		int cases=1;
		while (!arq.isEndOfFile()){
			char entrada = arq.readString().charAt(0);
			switch (entrada){
			case 'I': //inserir
				char in = arq.readString().charAt(0);
				if (in == 'D'){ //departamento
					int code = arq.readInt();
					if (bstdep.root==null){
						bstdep.root=bstdep.insert(new Dep(code), null);
					} else {
						bstdep.insert(new Dep(code), bstdep.root);
					}
				} else { //produto
					int codigo = arq.readInt();
					int qtd = arq.readInt();
					int depCode = arq.readInt();
					BSTProd bprod = bstdep.search(bstdep.root, depCode).bst_prod;
					if (bprod.root==null){
						bprod.root = bprod.insert(new Prod(codigo, qtd), null);
					} else {
						bprod.insert(new Prod(codigo, qtd), bprod.root);
					}

				}
				break;
			case 'R': //remover
				in = arq.readString().charAt(0);
				if (in == 'D'){//remover departamento
					int code = arq.readInt();
					bstdep.root = bstdep.del(bstdep.root, code);
				} else {//remover produto 
					int codigo = arq.readInt();
					int qtd = arq.readInt();
					int depCode = arq.readInt();
					BSTProd bprodFound = bstdep.search(bstdep.root, depCode).bst_prod; //acha a árvore de produtos daquele departamento	
					Prod prodFound = bprodFound.search(bprodFound.root, codigo); //acha o produto específico
					if (prodFound!= null){
						if (qtd >= prodFound.qtd){ //qtd a deletar maior que o que tem daquele produto deleta o produto
							bprodFound.root = bprodFound.del(bprodFound.root, codigo);
						} else {
							prodFound.qtd = prodFound.qtd - qtd;
						}						
					}
				}
				break;
			case 'F':
				arq.print("CASO #" + cases);
				arq.println();
				arq.println();
				if (bstdep.root!=null) bstdep.print(bstdep.root, arq);
				cases = cases + 1;
				bstdep.root = null;
				arq.println();
				break;
			}


		}//arquivo EOF


	}
}