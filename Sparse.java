//Shant Narkizian
//snarkizi
//Sparse.java
//pa3
import java.io.*;
import java.util.Scanner;

public class Sparse{
	public static void main(String[] args) throws IOException{

		Scanner in = null;
		Scanner in2 = null;
		PrintWriter out = null;
		String[] token = null;
		String line = null;
		String line2 = null;
		int n, a, b;


		if(args.length < 2){  //make sure there are 2 files 
			System.out.println("Usage: Sparse infile outfile");
			System.exit(1);
		}

		in = new Scanner(new File(args[0]));

		line = in.nextLine()+" ";
		token = line.split("\\s+");

		n = Integer.parseInt(token[0]);
		a = Integer.parseInt(token[1]); 
		b = Integer.parseInt(token[2]);

		Matrix A = new Matrix(n);
		Matrix B = new Matrix(n);

		in.close(); 

		in2 = new Scanner(new File(args[0]));
		in2.nextLine();
		in2.nextLine();
		int i = 0;
		int t0, t1;
		Double t2;

		while(i < a){
			line2 = in2.nextLine()+" ";
			token = line2.split("\\s+");

			t0 = Integer.parseInt(token[0]);
			t1 = Integer.parseInt(token[1]);
			t2 = Double.parseDouble(token[2]);
			//System.out.println(token[0]);
			//System.out.println(token[1]);
			//System.out.println(token[2]);
			A.changeEntry(t0, t1, t2);

			i++;
		}

		i = 0;
		in2.nextLine();
		while(i < b){
			line2 = in2.nextLine()+" ";
			token = line2.split("\\s+");     

			t0 = Integer.parseInt(token[0]);
			t1 = Integer.parseInt(token[1]);
			t2 = Double.parseDouble(token[2]); 
			B.changeEntry(t0, t1, t2);
			i++;
		}

		in2.close();
		out = new PrintWriter(new FileWriter(args[1]));

		out.println("A has "+A.getNNZ()+" non-zero entries:");
		out.println(A);

		out.println("B has "+B.getNNZ()+" non-zero entries:");
		out.println(B);

		out.println("(1.5)*A =");
		Matrix C = A.scalarMult(1.5);
		out.println(C);

		out.println("A+B =");
		Matrix D = A.add(B);
		out.println(D);

		out.println("A+A =");
		Matrix E = A.add(A);
		out.println(E);

		out.println("B-A =");
		Matrix F = B.sub(A);
		out.println(F);

		out.println("A-A ="); 
		Matrix G = A.sub(A);
		out.println(G); 

		out.println("Transpose(A) =");
		Matrix H = A.transpose();
		out.println(H);

		out.println("A*B =");
		Matrix I = A.mult(B);
		out.println(I);

		out.println("B*B =");
		Matrix J = B.mult(B);
		out.println(J); 

		out.close();

	}
}
