//Shant Narkizian
//snarkizi
//Matrix.java 
//pa3 
class Matrix{

	private class Entry{

		//Fields
		int Column;
		double Value;

		//Constructor
		Entry(int x, double y){
			this.Column = x;
			this.Value = y;
		}

		// toString()
		// Overrides Object's toString() method.
		public String toString(){
			return String.valueOf("("+Column+", "+Value+")");
		}

		// equals(): overrides Object's equals() method
		public boolean equals(Object x){
			//System.out.println("here1");
			boolean eq = false;
			Entry that;
			if(x instanceof Entry){
				that = (Entry)x;
				eq = (this.Value == that.Value);
				eq = (this.Column == that.Column);
			}
			return eq;
		}
	}


	//Fields
	private int arr_len;  //this is n (so col length) 
	private List[] row;


	// Constructor
	// Makes a new n x n zero Matrix. pre: n>=1
	Matrix(int n){
		arr_len = n;
		row = new List[n + 1]; //array of lists with n Col
		for(int i = 1;i < n + 1;i++){
			row[i] = new List();
		}
	}

	// Access functions --------------------------------------------------------

	// Returns n, the number of rows and columns of this Matrix
	int getSize(){
		return arr_len;
	}

	// Returns the number of non-zero entries in this Matrix
	int getNNZ(){
		int temp = 0;
		for(int i = 1; i < arr_len + 1; i++){
			temp += row[i].length();
		}
		return temp;
	}

	// overrides Object's equals() method
	public boolean equals(Object x){
		//System.out.println("here");
		boolean eq = false;
		Matrix S;
		if(x instanceof Matrix){
			S = (Matrix)x;
			eq = (this.arr_len == S.arr_len);
			int i = 1;
			while(eq == true && i < (arr_len + 1)){
				eq = this.row[i].equals(S.row[i]); //check every row to see if eq   
				i++;
			}
		}
		return eq;
	}


	// Manipulation Procedures -------------------------------------------------

	// sets this Matrix to the zero state
	void makeZero(){
		for(int i = 1;i < arr_len + 1; i++){
			row[i] = null;
			row[i] = new List();
		}
		//Matrix(arr_len);
	}

	// returns a new Matrix having the same entries as this Matrix
	Matrix copy(){
		Matrix S = new Matrix(this.arr_len);
		int i = 1;
		List A = this.row[i];
		while(row[i].length() != 0){
			A = row[i];
			this.row[i].moveFront();
			while( A.index() != -1){
				Entry T = (Entry)this.row[i].get();
				Entry R = new Entry(T.Column, T.Value);
				S.row[i].append(R);  //copy the object first
				this.row[i].moveNext();
			}
			i++;
		}
		return S;
	}

	// changes ith row, jth column of this Matrix to x
	// pre: 1<=i<=getSize(), 1<=j<=getSize()
	void changeEntry(int i, int j, double x){
		if(i >= 1 && i <= this.getSize() && j >= 1 && j <= this.getSize()){ //precondition
			int flag = 0;
			Entry T = new Entry(j, x);
			Entry S;
			if(x != 0){
				row[i].moveFront(); //get to front of list 
				if(row[i].length() == 0){ // if list is empty just insert it
					row[i].append(T);
				}else if(row[i].length() == 1){
					S = (Entry)row[i].get();
					if(S.Column == j){
						row[i].insertBefore(T);
						row[i].moveBack();
						row[i].delete();
					}else if(S.Column < j){ //put it in back
						row[i].append(T);
					}else{ //put it in front
						row[i].prepend(T);
					}
				}else if(row[i].length() > 1){
					row[i].moveFront();
					while(row[i].index() !=  -1){ //while its not at the end
						S = (Entry)row[i].get();
						if(S.Column == j){ //if its found set a flag
							flag = 1;
							break;
						}
						row[i].moveNext();
					}
					// }   

					if(flag == 1){ //it was found (cursor points to it)
						row[i].insertBefore(T);
						row[i].delete();
					}else{  //not found
						row[i].moveFront();
						S = (Entry)row[i].get();

						while(S.Column < j && row[i].index() != -1){ 
							row[i].moveNext(); //cursor is after elemen
							if(row[i].index() != -1){
								S = (Entry)row[i].get();
							}
						}
						if(row[i].index() ==  -1){ //it went off end
							row[i].append(T);
						}else{ //cursor points to the larger element
							row[i].insertBefore(T);
						}
					}
			} //here
		}else if(x == 0){
			row[i].moveFront();
			// S = (Entry)row[i].get();
			if(row[i].length() == 1){
				S = (Entry)row[i].get();
				if(S.Column == j){
					row[i].moveFront();
					row[i].delete();
				}
			}else if(row[i].length() > 1){
				row[i].moveFront();
				S = (Entry)row[i].get();
				while(row[i].index() !=  -1){ //while its not at the end
					S = (Entry)row[i].get();
					if(S.Column == j){
						row[i].delete();
					}
					row[i].moveNext();
				}
			}
		}
	}else{
		throw new RuntimeException("Matrix Error: changeEntry() called on invalid index");
	}
}

// returns a new Matrix that is the scalar product of this Matrix with x
Matrix scalarMult(double x){
	Matrix S = new Matrix(this.arr_len);
	if(x == 0){
		S.makeZero();
		return S;
	}
	int i = 1;
	List A = this.row[i];
	int temp = this.arr_len + 1;
	while(i != temp){
		A = row[i];
		this.row[i].moveFront();
		while( A.index() != -1){
			Entry T = (Entry)this.row[i].get();
			Entry R = new Entry(T.Column, T.Value);
			R.Value = R.Value * x;
			S.row[i].append(R);
			this.row[i].moveNext();
		}
		i++;
	}
	return S;
}

// returns a new Matrix that is the sum of this Matrix with M
// pre: getSize()==M.getSize()
Matrix add(Matrix M){
	if(this.equals(M)){
		return scalarMult(2);
	}
	if(getSize()==M.getSize()){
		Matrix S = new Matrix(this.arr_len);
		int temp = this.arr_len + 1;//M.getNNZ();
		int temp2 = this.arr_len + 1;//this.getNNZ();

		temp = Math.max(temp, temp2);
		int i = 1; //represents row
		int j = 1; //represents col
		int flag = 0;

		List A = this.row[i];
		List B = M.row[i];

		int len = A.length();
		int len2 = B.length();
		len = Math.max(len, len2);
		boolean found1 = false;
		boolean found2 = false;
		while(i != temp){
			A = this.row[i];
			B = M.row[i];
			if(A.length() == 0 && B.length() != 0){
				M.row[i].moveFront();
				while(B.index() != -1){
					Entry T = (Entry)M.row[i].get();
					Entry RS = new Entry(T.Column, T.Value);
					S.row[i].append(RS);
					M.row[i].moveNext();
				}
			}else if(A.length() != 0 && B.length() == 0){;
				this.row[i].moveFront();
				while(A.index() != -1){
					Entry T = (Entry)this.row[i].get();
					Entry RS = new Entry(T.Column, T.Value);
					S.row[i].append(RS);
					this.row[i].moveNext();
				}
			}else if(A.length() == 0 && B.length() == 0){
				//do nothing
			}else{
				M.row[i].moveFront();
				this.row[i].moveFront();
				A = this.row[i];
				B = M.row[i];

				while(A.index() != -1 && B.index() != -1){
					Entry T = (Entry)this.row[i].get();
					Entry T2 = (Entry)M.row[i].get();
					if(T.Column == T2.Column){
						Entry R = new Entry(T.Column, T.Value);
						Entry R2 = new Entry(T2.Column, T2.Value);
						R.Value = R.Value + R2.Value;
						//System.out.println("here");
						if(R.Value != 0){
							S.row[i].append(R);
						}
						M.row[i].moveNext();
						this.row[i].moveNext();
					}else if(T.Column > T2.Column){
						Entry R2 = new Entry(T2.Column, T2.Value);
						S.row[i].append(R2);
						M.row[i].moveNext();
					}else if(T.Column < T2.Column){
						Entry R = new Entry(T.Column, T.Value);
						S.row[i].append(R);
						this.row[i].moveNext();
					}
				}

				if(A.index() == -1){
					while(B.index() != -1){
						Entry T = (Entry)M.row[i].get();
						Entry RS = new Entry(T.Column, T.Value);
						S.row[i].append(RS);
						M.row[i].moveNext();
					}
				}else if(B.index() == -1){
					while(A.index() != -1){
						Entry T = (Entry)this.row[i].get();
						Entry RS = new Entry(T.Column, T.Value);
						S.row[i].append(RS);
						this.row[i].moveNext();
					}
				}
			}
			i++;
		}
		return S;
	}else{
		throw new RuntimeException("Matrix Error: add() called with invalid paramaters");
	}
}

// returns a new Matrix that is the difference of this Matrix with M
// pre: getSize()==M.getSize()
Matrix sub(Matrix M){
	// if(this.equals(M)){
	//    Matrix S = new Matrix(this.arr_len);
	//    return S; //return an empty matrix
	// }
	if(getSize()==M.getSize()){
		Matrix S = new Matrix(this.arr_len);
		int temp = this.arr_len + 1;//M.getNNZ();
		int temp2 = this.arr_len + 1;//this.getNNZ();

		temp = Math.max(temp, temp2);
		int i = 1; //represents row
		int j = 1; //represents col
		int flag = 0;

		List A = this.row[i];
		List B = M.row[i];

		int len = A.length();
		int len2 = B.length();
		len = Math.max(len, len2);
		boolean found1 = false;
		boolean found2 = false;
		while(i != temp){
			A = this.row[i];
			B = M.row[i];
			if(A.length() == 0 && B.length() != 0){
				M.row[i].moveFront();
				while(B.index() != -1){
					Entry T = (Entry)M.row[i].get();
					Entry RS = new Entry(T.Column, T.Value * -1);
					S.row[i].append(RS);
					M.row[i].moveNext();
				}
			}else if(A.length() != 0 && B.length() == 0){;
				this.row[i].moveFront();
				while(A.index() != -1){
					Entry T = (Entry)this.row[i].get();
					Entry RS = new Entry(T.Column, T.Value);
					S.row[i].append(RS);
					this.row[i].moveNext();
				}
			}else if(A.length() == 0 && B.length() == 0){
				//do nothing
			}else{
				M.row[i].moveFront();
				this.row[i].moveFront();
				A = this.row[i];
				B = M.row[i];

				while(A.index() != -1 && B.index() != -1){
					Entry T = (Entry)this.row[i].get();
					Entry T2 = (Entry)M.row[i].get();
					if(T.Column == T2.Column){
						Entry R = new Entry(T.Column, T.Value);
						Entry R2 = new Entry(T2.Column, T2.Value);
						R.Value = R.Value - R2.Value;
						//System.out.println("here");
						if(R.Value != 0){
							S.row[i].append(R);
						}
						M.row[i].moveNext();
						this.row[i].moveNext();
					}else if(T.Column > T2.Column){
						Entry R2 = new Entry(T2.Column, T2.Value * -1);
						S.row[i].append(R2);
						M.row[i].moveNext();
					}else if(T.Column < T2.Column){
						Entry R = new Entry(T.Column, T.Value);
						S.row[i].append(R);
						this.row[i].moveNext();
					}
				}

				if(A.index() == -1){
					while(B.index() != -1){
						Entry T = (Entry)M.row[i].get();
						Entry RS = new Entry(T.Column, T.Value * -1);
						S.row[i].append(RS);
						M.row[i].moveNext();
					}
				}else if(B.index() == -1){
					while(A.index() != -1){
						Entry T = (Entry)this.row[i].get();
						Entry RS = new Entry(T.Column, T.Value);
						S.row[i].append(RS);
						this.row[i].moveNext();
					}
				}
			}
			i++;
		}
		return S;
	}else{
		throw new RuntimeException("Matrix Error: add() called with invalid paramaters");
	}
}

// returns a new Matrix that is the transpose of this Matrix
Matrix transpose(){
	Matrix S = new Matrix(this.arr_len);
	int i = 1; //current row
	int temp = this.arr_len + 1;//getNNZ(); 
	List A = this.row[i];
	while(i != temp){
		A = row[i];
		this.row[i].moveFront();
		while( A.index() != -1){
			Entry T = (Entry)this.row[i].get();
			Entry R = new Entry(i, T.Value);
			S.row[T.Column].append(R);  //copy the object first
			this.row[i].moveNext();
		}
		i++;
	}
	return S;
}

// returns a new Matrix that is the product of this Matrix with M 
// pre: getSize()==M.getSize()
Matrix mult(Matrix M){
	M = M.transpose();
	if(getSize()==M.getSize()){
		Matrix S = new Matrix(this.arr_len);
		int temp = this.arr_len;//M.getNNZ();
		int temp2 = this.arr_len;//this.getNNZ();

		temp = Math.max(temp, temp2);

		int i = 1; //rows of A
		int j = 1; //rows of B
		int k = 1; //keeps track of current col
		double sum = 0;
		int flag = 0;

		List A = this.row[i];
		List B = M.row[j];

		int len = A.length();
		int len2 = B.length();
		len = Math.max(len, len2);
		boolean found1 = false;
		boolean found2 = false;
		while(i != (temp + 1)){ //while we havent reached end of A
			while(j != (temp + 1)){ //while havent reached end of B
				//System.out.println("j ="+j);
				A = this.row[i];
				B = M.row[j];

				if(A.length() == 0 && B.length() != 0){
					//do nothing
				}else if(A.length() != 0 && B.length() == 0){
					//do nothing
				}else if(A.length() == 0 && B.length() == 0){
					//do nothing
				}else{ //length is atleast 1 
					this.row[i].moveFront();
					M.row[j].moveFront();
					A = this.row[i];
					B = M.row[j];

					while(A.index() != -1 && B.index() != -1){
						Entry T = (Entry)this.row[i].get();
						Entry T2 = (Entry)M.row[j].get();
						if(T.Column == T2.Column){
							Entry R = new Entry(T.Column, T.Value);
							Entry R2 = new Entry(T2.Column, T2.Value);
							sum += R.Value * R2.Value;
							this.row[i].moveNext();
							M.row[j].moveNext();
						}else if(T.Column > T2.Column){
							M.row[j].moveNext();
						}else if(T.Column < T2.Column){
							this.row[i].moveNext();
						}
					}
				}


				if(sum != 0){
					Entry RS = new Entry(j, sum);
					S.row[i].append(RS);
				}
				sum = 0;
				j++;
			}
			j = 1;
			i++;
		}
		return S;
	}else{
		throw new RuntimeException("Matrix Error: add() called with invalid paramaters");
	}
}

// Other functions
// overrides Object's toString() method
public String toString(){
	StringBuffer sb = new StringBuffer();
	int temp = (arr_len + 1);// getNNZ();      
	int i = 1;
	if(temp == 0) return "";
	List A = row[i];
	while(i != (temp)){
		A = this.row[i];
		if(A.length() != 0){
			sb.append(i+": ");
			row[i].moveFront();
			sb.append(A.toString());
			A = row[i];
			sb.append("\n");
		}
		i++;
	}
	return new String(sb);
}

} //end class 



