//Shant Narkizian
//snarkizi
//MatrixTest.java
//pa3
public class MatrixTest{
	public static void main(String[] args){
		int i, j;//, n=100000;
		int n = 20;
		Matrix A = new Matrix(n);
		Matrix B = new Matrix(n);

		A.changeEntry(3,3,9); B.changeEntry(1,1,1);
		A.changeEntry(1,2,2); B.changeEntry(1,3,1);
		A.changeEntry(1,3,3); B.changeEntry(3,1,1);
		A.changeEntry(2,1,4); B.changeEntry(3,2,1);
		A.changeEntry(2,2,5); B.changeEntry(3,3,1);
		A.changeEntry(2,3,6);// B.changeEntry(2,3,0);
		A.changeEntry(3,1,7);// B.changeEntry(3,1,1);
		A.changeEntry(3,2,8);// B.changeEntry(3,2,1);
		A.changeEntry(1,1,1);// B.changeEntry(3,3,1);

		/*        A = new Matrix(10);
			  A.changeEntry(2, 1, 2);
			  A.changeEntry(3, 1, 5);
			  A.changeEntry(1, 2, 2);
			  A.changeEntry(1, 3, 5);
			  A.changeEntry(1, 1, 4);
			  A.changeEntry(2, 2, 2);
			  A.changeEntry(2, 5, 0);
			  A.changeEntry(2, 3, 0);
			  A.changeEntry(3, 3, 5);
			  System.out.println(A.getNNZ());// 7) return 1;
			  System.out.println(A.getSize())
			  System.out.println(A);
			  A.changeEntry(1, 3, 0);
			  A.changeEntry(3, 1, 0);
			  A.changeEntry(3, 3, 0);
			  System.out.println(A.getNNZ());// return 2;
			  System.out.println(A);

			  A  = new Matrix(10);
			  B  = new Matrix(10);
			  A.changeEntry(1, 1, 4);
			  A.changeEntry(1, 2, 2);
			  A.changeEntry(1, 3, 0);
			  A.changeEntry(2, 1, 2);
			  A.changeEntry(3, 1, 0);
			  A.changeEntry(2, 2, 2);
			  A.changeEntry(3, 3, 0);
			  Matrix C = A.add(A);
			  System.out.println(C.getNNZ());// != 4 || A.getNNZ() != 4) return 1;
			  System.out.println(A.getNNZ());
			  B.changeEntry(1, 1, -4);
			  B.changeEntry(1, 2, 0);
			  B.changeEntry(2, 1, 0);
			  B.changeEntry(2, 2, -2);
			  B.changeEntry(2, 4, 2);
			  B.changeEntry(3, 1, 0);
			  B.changeEntry(3, 2, 2);
			  B.changeEntry(7, 8, 5);
			  System.out.println(A);
			  System.out.println(B);
			  C = A.add(B);
			  System.out.println(C);
			  System.out.println(C.getNNZ());//5
		//if (C.getNNZ() != 5) return 2;
		*/
		A = new Matrix(10);
		B = new Matrix(15);
		A.changeEntry(1, 1, 1);
		B.changeEntry(1, 1, 1);
		System.out.println(A.equals(B));//if (A.equals(B)) return 1;
		B = new Matrix(10);
		A.changeEntry(1, 1, 1);
		A.changeEntry(1, 3, 1);
		B.changeEntry(1, 1, 1);
		B.changeEntry(1, 3, 1);
		System.out.println(!A.equals(B));//if (!A.equals(B)) return 2;
		A.changeEntry(1, 3, 0);
		System.out.println(A.equals(B));//if (A.equals(B)) return 3;
		A.changeEntry(1, 1, 0);
		B.makeZero();
		A.changeEntry(10, 10, 10);
		B.changeEntry(10, 10, 10);
		System.out.println(!A.equals(B));
		A = new Matrix(100);
		B = new Matrix(100);
		int valcount = 1;
		for ( j = 1; j <= 100; j++) {
			for (int k = 1; k <= 100; k++) {
				// hint: this is 1-10000 left-to-right row-by-row
				A.changeEntry(j, k, valcount++);
			}
			B.changeEntry(j, j, 1); // hint: this is the identity matrix
		}
		Matrix C = A.scalarMult(2);
		if (!C.equals(A.add(A))) System.out.println("passed");// return 5;
		C = A.scalarMult(-2);
		// System.out.println(C);
		//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		//System.out.println(A);
		//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		Matrix E= A.sub(A).sub(A).sub(A);
		System.out.println(E);
		//Matrix F= E.sub(A);
		//System.out.println(A.sub(A).sub(A).sub(A));
		if (!C.equals(A.sub(A).sub(A).sub(A))) System.out.println("passed");//return 6;
		//want this to be true

		/*
		   System.out.println(A.getNNZ());
		   System.out.println(A);

		   System.out.println(B.getNNZ());
		   System.out.println(B);

		   Matrix C = A.scalarMult(1.5);
		   System.out.println(C.getNNZ());
		   System.out.println(C);

		   Matrix D = A.add(B);
		   System.out.println(D.getNNZ());
		   System.out.println(D);

		   Matrix E = A.add(A);
		   System.out.println(E.getNNZ());
		   System.out.println(E);

		   E = A.add(B);
		   System.out.println(E.getNNZ());
		   System.out.println(E);

		   Matrix F = B.sub(A);
		   System.out.println(F.getNNZ());
		   System.out.println(F);

		   F = A.sub(B);
		   System.out.println(F.getNNZ());
		   System.out.println(F);

		   Matrix G = A.sub(A);
		   System.out.println(G.getNNZ());
		   System.out.println(G);

		   Matrix H = B.transpose();
		   System.out.println(H.getNNZ());
		   System.out.println(H);
		   */

	}
}
