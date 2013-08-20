class Ret{
	Apartamento root;
	String name;
	boolean hchanged;
	Ret(Apartamento root){
		this.root = root;
		this.hchanged = false;
	}
}

class Apartamento{
	int price;
	String name;
	int bf;
	Apartamento left;
	Apartamento right;
	Apartamento(int price, String name){
		this.bf = 0;
		this.name = name;
		this.price = price;
		this.right = null;
		this.left = null;
	}
	
	int height(AVL avl, int price){
		int h=0;
		Apartamento aux = avl.root;
		boolean p = true;
		while (aux!=null && price!=aux.price && p){
			if (price == aux.price) {
				p = false;
			} else if (price<aux.price){
				aux=aux.left;
				h++;
			} else {
				aux=aux.right;
				h++;
			}
		}
		if (aux==null && p==true){
			h=-1;
		}
		return h;
	}
	
}

class AVL{
	Apartamento root;
	AVL(){
	}
	
	Apartamento rotate_left(Apartamento curr){
		Apartamento right = curr.right;
		Apartamento right_left = curr.right.left;
		right.left = curr;
		curr.right = right_left;
		if (right.bf>0){
			curr.bf = curr.bf - 1 - right.bf;
		} else {
			curr.bf = curr.bf - 1;
		}
		if (curr.bf<0){
			right.bf = right.bf - 1 + curr.bf;
		} else {
			right.bf = right.bf -1;
		}
		return right;
	}

	Apartamento rotate_right(Apartamento curr){
		Apartamento left = curr.left;
		Apartamento left_right = curr.left.right;
		left.right = curr;
		curr.left = left_right;
		if (left.bf >= 0){
			curr.bf = curr.bf + 1;
		} else {
			curr.bf = curr.bf - left.bf + 1;
		}
		if (curr.bf <= 0){
			left.bf = left.bf + 1;
		} else {
			left.bf = left.bf + 1 + curr.bf;
		}
		return left;
	}

	Ret insert(Apartamento root, int price, String name){
		Ret ret = new Ret(root);
		if (root==null){
			Apartamento n = new Apartamento(price, name);
			ret.hchanged = true;
			ret.root = n;
			return 	ret;
		} else if (root.price == price){
			ret.hchanged = false;
			ret.root	 = root;
			return ret;
		} else if (price < root.price){
			ret.root = root.left;
			ret = insert(root.left, price, name);
			root.left = ret.root;
			if (ret.hchanged){
				if (root.bf ==0){
					root.bf = -1;
					ret.root = root;
					ret.hchanged = true;
					return ret;
				} else if (root.bf == 1){
					root.bf = 0;
					ret.root = root;
					ret.hchanged = false;
					return ret;
				}  else {
					root.bf = -2;
					if (root.left.bf == 1){
						root.left = rotate_left(root.left);
					}
					ret.root = rotate_right(root);
					ret.hchanged = false;
					return ret;
				}
			} else {
				ret.root = root;
				ret.hchanged = false;
				return ret;
			}
		} else {
			ret.root = root.right;
			ret = insert(root.right, price, name);
			root.right = ret.root;
			if (ret.hchanged){
				if (root.bf == -1){
					root.bf = 0;
					ret.root = root;
					ret.hchanged = false;
					return ret;
				} else if (root.bf ==0){
					root.bf = 1;
					ret.root = root;
					ret.hchanged = true;
					return ret;
				} else {
					root.bf = 2;
					if (root.right.bf == -1){
						root.right = rotate_right(root.right);
					}
					ret.root = rotate_left(root);
					ret.hchanged = false;
					return ret;
				}
			} else {
				ret.root = root;
				ret.hchanged = false;
				return ret;
			}
		}
	}

	Ret insert_name(Apartamento root, int price, String name){
		Ret ret = new Ret(root);
		if (root==null){
			Apartamento n = new Apartamento(price, name);
			ret.hchanged = true;
			ret.root = n;
			return 	ret;
		} else if (root.name.compareTo(name)==0){
			ret.hchanged = false;
			ret.root	 = root;
			return ret;
		} else if (name.compareTo(root.name)<0){
			ret.root = root.left;
			ret = insert_name(root.left, price, name);
			root.left = ret.root;
			if (ret.hchanged){
				if (root.bf ==0){
					root.bf = -1;
					ret.root = root;
					ret.hchanged = true;
					return ret;
				} else if (root.bf == 1){
					root.bf = 0;
					ret.root = root;
					ret.hchanged = false;
					return ret;
				}  else {
					root.bf = -2;
					if (root.left.bf == 1){
						root.left = rotate_left(root.left);
					}
					ret.root = rotate_right(root);
					ret.hchanged = false;
					return ret;
				}
			} else {
				ret.root = root;
				ret.hchanged = false;
				return ret;
			}
		} else {
			ret.root = root.right;
			ret = insert_name(root.right, price, name);
			root.right = ret.root;
			if (ret.hchanged){
				if (root.bf == -1){
					root.bf = 0;
					ret.root = root;
					ret.hchanged = false;
					return ret;
				} else if (root.bf ==0){
					root.bf = 1;
					ret.root = root;
					ret.hchanged = true;
					return ret;
				} else {
					root.bf = 2;
					if (root.right.bf == -1){
						root.right = rotate_right(root.right);
					}
					ret.root = rotate_left(root);
					ret.hchanged = false;
					return ret;
				}
			} else {
				ret.root = root;
				ret.hchanged = false;
				return ret;
			}
		}
	}
	
	void getFinalTree(AVL finalTree, Apartamento root, int a, int b){

		if (root==null)return;
		if (root.price >= a && root.price<= b){
			if (finalTree.root==null){
				finalTree.root = new Apartamento(root.price, root.name);
			} else {
				finalTree.root = finalTree.insert_name(finalTree.root, root.price, root.name).root;
			}
			getFinalTree(finalTree, root.left, a, b);
			getFinalTree(finalTree, root.right, a, b);
		} else if (root.price < a){
			getFinalTree(finalTree, root.right, a, b);
		} else {
			getFinalTree(finalTree, root.left, a, b);
		}
	}

	void print(AVL avl, Apartamento root, Arquivo arq){
		if (root==null) return;
		print(avl, root.left, arq);
		arq.println(root.name + " " + root.price + " " + root.height(avl, root.price));
		print(avl, root.right, arq);
		
	}
	
}

public  class L2Q3 {
	public static void main(String[] args) {	
		AVL avl = new AVL();
		/*AVL finalTree = new AVL();
		
		Arquivo arq = new Arquivo("L2Q3.in", "L2Q3.out");
		while (!arq.isEndOfFile()){
			String entrada = arq.readString();
			if (entrada.equals("INSERT")){
				String name = arq.readString();
				int price = arq.readInt();
				if (avl.root==null){
					avl.root = new Apartamento(price, name);
					arq.println(avl.root.height(avl, price));
				} else {
					avl.root = avl.insert(avl.root, price, name).root;
					arq.println(avl.root.height(avl, price));
				}
			} else if (entrada.equals("SELECT")){
				String range = arq.readString();
				String num1 = range.substring(1, range.indexOf(','));
				String num2 = range.substring(range.indexOf(',')+1, range.length()-1);
				int a = Integer.parseInt(num1);
				int b = Integer.parseInt(num2);
				finalTree.getFinalTree(finalTree, avl.root, a, b);
				if (finalTree.root==null){
					arq.println("VOID");
				} else {
					finalTree.print(avl, finalTree.root, arq);
					finalTree.root = null;
				}
			}
			
		}*/
	}
	
	
}
