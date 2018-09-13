//Shant Narkizian
//snarkizi
//List.java
//pa3
class List {

	private class Node {

		// Fields
		Object data = new Object(); //data is of type object
		Node next;
		Node prev;

		// Constructor
		Node(Object x) {
			this.data = x;
			this.next = null;
			this.prev = null;
		}

		// toString()
		// Overrides Object's toString() method.
		public String toString() {
			return String.valueOf(data);
		}

		// equals(): overrides Object's equals() method
		public boolean equals(Object x) {
			boolean eq = false;
			Node that;
			if (x instanceof Node) {
				that = (Node) x;
				eq = (this.data == that.data);
			}
			return eq;
		}
	}

	// Fields
	private Node front;
	private Node back;
	private Node curs;
	private int length;

	//Constructor
	List() {
		front = null;
		back = null;
		curs = null;
		length = 0;
	}

	// Access Functions --------------------------------------------------------

	// length()
	// Returns the number of elements in this List
	int length() {
		return length;
	}

	// index()
	// If cursor is defined, returns the index of the cursor element, otherwise -1
	int index() {
		if (curs != null) {
			Node T = front;
			int ind = 0;
			while (T != curs) {
				ind++;
				T = T.next;
			}
			return ind;
		} else {
			return -1;
		}
	}

	// front()
	// Returns front element 
	// Pre: length() > 0
	Object front() {
		if (length <= 0) {
			throw new RuntimeException("List Error: front() called on empty List");
		} else {
			return front.data;
		}
	}

	// back()
	// Returns back element 
	// Pre: length() > 0
	Object back() {
		if (length <= 0) {
			throw new RuntimeException("List Error: back() called on empty List");
		} else {
			return back.data;
		}
	}

	// get()
	// Returns cursor element
	// Pre: length() > 0, index() >= 0
	Object get() {
		if (length > 0 && index() >= 0) { //could be a problem here
			return curs.data;
		} else {
			throw new RuntimeException("List Error: get() called on empty List");
		}
	}

	// equals(List L)
	// Returns true if and only if this List and L are the same
	// integer sequence. The states of the cursors in the two Lists 
	// are not used in determining equality.
	public boolean equals(Object x) {
		boolean eq = false;
		List S;
		Node N, M;
		if (x instanceof List) {
			S =(List)x;
			eq = (this.length == S.length);
			N = this.front;
			M = S.front;
			while (eq && N != null) { 
				eq = (N.data).equals(M.data); //if objects are equal
				N = N.next;
				M = M.next;
			}
		}
		return eq;
	}

	// Manipulation Procedures -------------------------------------------------

	// clear()
	// Resets this List to its original empty state
	void clear() {
		front = null;
		back = null;
		curs = null;
		length = 0;
	}

	// moveFront()
	// If List is non-empty, places the cursor under the front element,
	// otherwise does nothing.
	void moveFront() {
		if (length > 0) {
			curs = front;
		}
	}

	// moveBack()
	// If List is non-empty, places the cursor under the back element,
	// otherwise does nothing.
	void moveBack() {
		if (length > 0) {
			curs = back;
		}
	}

	// movePrev()
	// If cursor is defined and not at front, moves cursor one step toward 
	// front of this List, if cursor is defined and at front, cursor becomes 
	// undefined, if cursor is undefined does nothing.
	void movePrev() {
		if (curs != null && curs != front) {
			curs = curs.prev;
		} else if (curs != null && curs == front) {
			curs = null;
		}
	}

	// moveNext()
	// If cursor is defined and not at back, moves cursor one step toward 
	// back of this List, if cursor is defined and at back, cursor becomes 
	// undefined, if cursor is undefined does nothing.
	void moveNext() {
		if (curs != null && curs != back) {
			curs = curs.next;
		} else if (curs != null && curs == back) {
			curs = null;
		}
	}

	// prepend(int data)
	// Insert new element into this List. If List is non-empty,
	// insertion takes place before front element
	void prepend(Object data) {
		if (length == 0) {
			Node N = new Node(data);
			front = N;
			back = N;
			N.next = null;
			length++;
		} else {
			Node N = new Node(data);
			N.next = front;
			front.prev = N;
			front = N;
			length++;
		}
	}

	// append(int data)
	// Insert new element into this List. If List is non-empty,
	// insertion takes place after back element.
	void append(Object data) {
		if (length == 0) {
			Node N = new Node(data);
			front = N;
			back = N;
			N.next = null;
			length++;
		} else {
			Node N = new Node(data);
			N.prev = back;
			back.next = N;
			back = N;
			N.next = null;
			length++;
		}
	}

	// insertBefore(int data)
	// Insert new element before cursor.
	// Pre: length()>0, index()>=0
	void insertBefore(Object data) {
		if (length > 0 && index() >= 0) {
			if (length == 1 || curs == front) {
				prepend(data);
			} else {
				Node N = new Node(data);
				N.prev = curs.prev;
				N.next = N.prev.next;
				N.prev.next = N;
				curs.prev = N;
				length++;
			}
		}
	}

	// insertAfter(int data)
	// Inserts new element after cursor.
	// Pre: length()>0, index()>=0
	void insertAfter(Object data) {
		if (length > 0 && index() >= 0) {
			if (length == 1) {
				append(data);
			} else if (curs == back) {
				Node N = new Node(data);
				curs.next = N;
				N.prev = curs;
				back = N;
				N.next = null;
				length++;
			} else {
				Node N = new Node(data);
				N.next = curs.next;
				N.prev = N.next.prev;
				N.next.prev = N;
				curs.next = N;
				length++;
			}
		}
	}

	// deleteFront()
	// Deletes the front element 
	// Pre: length()>0
	void deleteFront() {
		if (length > 0) {
			if (length == 1) {
				clear();
			} else {
				if (curs == front) {
					curs = null;
				}
				front = front.next;
				front.prev = null;
				length--;
			}
		}
	}

	// deleteBack()
	// Deletes the back element 
	// Pre: length()>0
	void deleteBack() {
		if (length > 0) {
			if (length == 1) {
				clear();
			} else {
				if (curs == back) {
					curs = null;
				}
				back = back.prev;
				back.next = null;
				length--;
			}
		}
	}

	// delete()
	// Deletes cursor element, making cursor undefined
	// Pre: length()>0, index()>=0
	void delete() {
		if (length > 0 && index() >= 0) {
			if (length == 1) {
				clear();
			} else if (front == curs) {
				front = front.next;
				front.prev = null;
				curs = null;
				length--;
			} else if (curs == back) {
				back = back.prev;
				back.next = null;
				curs = null;
				length--;
			} else {
				curs.prev.next = curs.next;
				curs.next.prev = curs.prev;
				curs = null;
				length--;
			}
		}
	}

	// toString()
	// Overrides Object's toString() method.
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Node N = front;
		while (N != null) {
			sb.append(" ");
			sb.append(N.toString());
			N = N.next;
		}
		return new String(sb);
	}
} //end class
