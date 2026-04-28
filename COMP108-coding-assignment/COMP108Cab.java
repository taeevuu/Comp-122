//
// Coded by Prudence Wong 2021-03-06
// Updated 2023-02-25
// Updated 2026-03-07
//
// Note: You are allowed to add additional methods if you need.
// Name: Alex Tilev
// Student ID: 201915483
//
// Time Complexity and explanation: 
// f denotes initial cabinet size
// n denotes the total number of requests 
// d denotes number of distinct requests
// You can use any of the above notations or define additional notation as you wish.
// 
// moveToFront():
// O(n * (f + d))
// The list can grow up to (f + d) nodes in total (f initial nodes, plus up to d
// new nodes inserted on misses).
// For each of the n requests, we traverse at most (f + d) nodes to find the target: O(f + d).
// Removing a found node and inserting it at the head are both O(1) pointer updates.
// Total across all n requests: O(n * (f + d)).
//
// freqCount():
// O(n * (f + d))
// The list can grow up to (f + d) nodes in total.
// For each of the n requests, we traverse at most (f + d) nodes to find the target: O(f + d).
// After finding the target, we traverse at most (f + d) nodes again to find the
// correct sorted insertion position: O(f + d).
// Removal and all types of insertion (head, tail, or middle) are O(1) pointer updates.
// Total across all n requests: O(n * (f + d)).
//

class COMP108Cab {

	public COMP108Node head, tail;
	
	public COMP108Cab() {
		head = null;
		tail = null;
	}

	// move the file requested to the beginning of the list
	public COMP108CabOutput moveToFront(int rArray[], int rSize) {
		COMP108CabOutput output = new COMP108CabOutput(rSize);

		for (int i = 0; i < rSize; i++) {
			int key = rArray[i];

			// Walk the list from head, counting each comparison
			COMP108Node curr = head;
			int comparisons = 0;
			boolean found = false;
			while (curr != null) {
				comparisons++;          // count this comparison
				if (curr.data == key) {
					found = true;
					break;
				}
				curr = curr.next;
			}

			output.compare[i] = comparisons;

			if (found) {
				output.hitCount++;
				// Move curr to front only if it is not already the head
				if (curr != head) {
					removeNode(curr);   // detach from current position
					insertHead(curr);   // attach at head
				}
			} else {
				output.missCount++;
				// File not in cabinet: insert a new node at the head
				COMP108Node newNode = new COMP108Node(key);
				insertHead(newNode);
			}
		}

		output.cabFromHead = headToTail();
		output.cabFromTail = tailToHead();
		return output;
	}

	// move the file requested so that order is by non-increasing frequency
	public COMP108CabOutput freqCount(int rArray[], int rSize) {
		COMP108CabOutput output = new COMP108CabOutput(rSize);

		for (int i = 0; i < rSize; i++) {
			int key = rArray[i];

			// Walk the list from head, counting each comparison
			COMP108Node curr = head;
			int comparisons = 0;
			boolean found = false;
			while (curr != null) {
				comparisons++;          // count this comparison
				if (curr.data == key) {
					found = true;
					break;
				}
				curr = curr.next;
			}

			output.compare[i] = comparisons;

			if (found) {
				output.hitCount++;
				// Remove node, increment its frequency, then reinsert in sorted position
				removeNode(curr);
				curr.freq++;
				insertByFreq(curr);
			} else {
				output.missCount++;
				// File not in cabinet: new node starts with freq = 1 (set by constructor)
				COMP108Node newNode = new COMP108Node(key);
				insertByFreq(newNode);
			}
		}

		output.cabFromHead = headToTail();
		output.cabFromTail = tailToHead();
		output.cabFromHeadFreq = headToTailFreq();
		return output;
	}

	// Helper: detach 'node' from whatever position it is in the doubly linked list.
	// Updates head/tail and neighbouring nodes' next/prev pointers.
	private void removeNode(COMP108Node node) {
		if (node.prev != null) {
			// There is a node before this one; point it past 'node'
			node.prev.next = node.next;
		} else {
			// 'node' was the head; the next node becomes the new head
			head = node.next;
		}

		if (node.next != null) {
			// There is a node after this one; point it back past 'node'
			node.next.prev = node.prev;
		} else {
			// 'node' was the tail; the previous node becomes the new tail
			tail = node.prev;
		}

		// Clean up the detached node's own pointers
		node.next = null;
		node.prev = null;
	}

	// Helper: insert 'node' into the list so that the list stays in non-increasing
	// frequency order.  When frequencies tie, 'node' goes AFTER all existing nodes
	// with the same frequency (i.e. it lands just before the first node whose
	// frequency is strictly less than node.freq).
	private void insertByFreq(COMP108Node node) {
		// Walk forward until we find the first node with freq < node.freq
		COMP108Node curr = head;
		while (curr != null && curr.freq >= node.freq) {
			curr = curr.next;
		}

		if (curr == null) {
			// Every existing node has freq >= node.freq  →  insert at tail
			insertTail(node);
		} else if (curr == head) {
			// Every existing node has freq < node.freq  →  insert at head
			insertHead(node);
		} else {
			// Insert between curr.prev and curr
			COMP108Node prev = curr.prev;
			node.next = curr;
			node.prev = prev;
			prev.next = node;
			curr.prev = node;
		}
	}

	// DO NOT change this method
	// insert newNode to head of list
	public void insertHead(COMP108Node newNode) {		

		newNode.next = head;
		newNode.prev = null;
		if (head == null)
			tail = newNode;
		else
			head.prev = newNode;
		head = newNode;
	}

	// DO NOT change this method
	// insert newNode to tail of list
	public void insertTail(COMP108Node newNode) {

		newNode.next = null;
		newNode.prev = tail;
		if (tail != null)
			tail.next = newNode;
		else head = newNode;
		tail = newNode;
	}

	// DO NOT change this method
	// delete the node at the head of the linked list
	public COMP108Node deleteHead() {
		COMP108Node curr;

		curr = head;
		if (curr != null) {
			head = head.next;
			if (head == null)
				tail = null;
			else
				head.prev = null;
		}
		return curr;
	}
	
	// DO NOT change this method
	// empty the cabinet by repeatedly removing head from the list
	public void emptyCab() {
		while (head != null)
			deleteHead();
	}


	// DO NOT change this method
	// this will turn the list into a String from head to tail
	// Only to be used for output, do not use it to manipulate the list
	public String headToTail() {
		COMP108Node curr;
		String outString="";
		
		curr = head;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.next;
		}
		return outString;
	}

	// DO NOT change this method
	// this will turn the list into a String from tail to head
	// Only to be used for output, do not use it to manipulate the list
	public String tailToHead() {
		COMP108Node curr;
		String outString="";
		
		curr = tail;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.prev;
		}
		return outString;
	}

	// DO NOT change this method
	// this will turn the frequency of the list nodes into a String from head to tail
	// Only to be used for output, do not use it to manipulate the list
	public String headToTailFreq() {
		COMP108Node curr;
		String outString="";
		
		curr = head;
		while (curr != null) {
			outString += curr.freq;
			outString += ",";
			curr = curr.next;
		}
		return outString;
	}

}