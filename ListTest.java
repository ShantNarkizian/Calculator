//Shant Narkizian
//snarkizi
//ListTest.java
//pa3
public class ListTest{
	public static void main(String[] args){

		List A = new List();
		List B = new List();
		List D = new List();
		List E = new List();
		D.append(1);
		D.append(2);
		E.append(3);
		System.out.println(D);
		System.out.println(E);
		System.out.println(D.equals(E));
		for(int i=1; i<=20; i++){
			A.append(i);
			B.prepend(i);
		}
		System.out.println(A);
		System.out.println(B);
		System.out.println(A.equals(B));


		/*
		   List A = new List();
		   A.prepend(5);
		   A.prepend(65);
		   A.prepend(43);
		   A.prepend(2);
		   A.prepend(8);
		   A.prepend(1);
		   A.moveFront();
		   A.deleteFront();
		   if (A.index() != -1) System.out.println("failed1");
		   A.moveBack();
		   A.deleteFront();
		   if (A.index() != 3) System.out.println("failed2");
		   */

		/*  List A = new List();
		    List B = new List();
		    List D = new List();
		    List E = new List();
		    D.append(1);
		    D.append(2);
		    E.append(3);
		    System.out.println(D);
		    System.out.println(E);
		    System.out.println(D.equals(E));
		    for(int i=1; i<=20; i++){
		    A.append(i);
		    B.prepend(i);
		    }
		    System.out.println(A);
		    System.out.println(B);
		    System.out.println(A.equals(B));
		    for(A.moveFront(); A.index()>=0; A.moveNext()){
		    System.out.print(A.get()+" ");
		    }
		// System.out.println("index ="+ A.index());
		System.out.println();
		for(B.moveBack(); B.index()>=0; B.movePrev()){
		System.out.print(B.get()+" ");
		}
		System.out.println();
		//  System.out.println("index ="+ B.index());

		List C = A.copy();
		System.out.println(A);
		System.out.println(B);
		// System.out.println(C);
		System.out.println(A.equals(B));
		// System.out.println(B.equals(C));
		// System.out.println(C.equals(A));
		*/

		// List C = A.copy();
		// C.clear();
		// System.out.println(C);
		// List D = A.copy();
		// D.moveFront();
		// D.moveBack();
		// D.movePrev();
		// D.movePrev();
		// D.movePrev();
		// D.movePrev();
		// D.index();
		// D.moveNext();
		// D.insertBefore(33);
		// D.append(4);

		//  D.clear();

	}
}
