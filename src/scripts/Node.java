package scripts;

public class Node {
	Node prev;
	Node next;
	int id;				// Used for EXTERNAL reference calls
	int index;			// Order of the Nodes
	int segcount;
	int offset;
	String title = "";
	Segment firstseg;
	public Node(Node prev, int index, int segcount, int offset, int id) {
		this.prev = prev;
		this.index = index;
		this.segcount = segcount;
		this.offset = offset;
		this.id = id;
	}
	private int calcPosition() {
		int placeCount = 1;
		Node curNode = this;
		while (curNode.prev!=null) {
			curNode = curNode.prev;
			placeCount+=1; 
		}
		return(placeCount);
	}
	public void calcOffset() {
		Node curNode = GetFirstNode();
		int nodeCount = CountAllNodes(curNode);
		int[] segs = ArrayAllSegs(curNode);
		int off = 32 + (16*(nodeCount));
		for (int i=0;i<nodeCount;i++) {
			curNode.offset = off;
			off+=(segs[i]*128);
			if (curNode.next!=null) {
				curNode = curNode.next;
			}
		}
	}
	public Segment newSegment() {	//Creates and returns a new segment
		Segment nSeg = new Segment(this);
		if (firstseg!=null) {
			Segment currentseg = firstseg;
			while(currentseg.next!=null) {
				currentseg=currentseg.next;
			}
			currentseg.next = nSeg;
		} else {
			firstseg = nSeg;
		}
		nSeg.parent = this;
		segcount += 1;
		calcOffset();
		return(nSeg);
	}
	public int CountAllNodes(Node Fnode) {
		int nodeCount = 1;
		Node curNode = Fnode;
		while (curNode.next!=null) {
			curNode = curNode.next;
			nodeCount++;
		}
		return(nodeCount);
	}
	public int CountAllSegs(Node Fseg) {	// For toString
		int segNum = 0;
		Node curNode = Fseg;
		while (curNode.next!=null) {
			curNode = curNode.next;
			segNum+=curNode.segcount;
		}
		segNum+=curNode.segcount;
		return(segNum);
	}
	public int[] ArrayAllSegs(Node Fnode) {
		Node curNode = Fnode;
		int[] array = new int[CountAllNodes(Fnode)];
		for (int i=0;i<array.length;i++) {
			array[i] = curNode.segcount;
			if (curNode.next!=null) {
				curNode = curNode.next;
			}
		}
		return(array);
	}
	public Node GetFirstNode() {
		Node curNode = this;
		while (curNode.prev!=null) {
			curNode = curNode.prev;
		}		
		return(curNode);
	}
	public String toString(String headerA, String headerB) {
		Node currentNode = GetFirstNode();
		Segment currentSeg;
		Segment functCall = new Segment(null);
		int totalNode = CountAllNodes(currentNode);
		String hexa = headerA;
		String numOfNodes = functCall.XByteHex(totalNode, 0, 4);	//# of Nodes
		hexa += numOfNodes + headerB;
		for (int i=0;i<totalNode;i++) {
			hexa += functCall.XByteHex(currentNode.offset, 0, 16);
			hexa += functCall.XByteHex(currentNode.segcount, 0, 8) + functCall.XByteHex(currentNode.id, 0, 8);
			currentNode = currentNode.next;
		}
		currentNode = GetFirstNode();
		currentSeg = currentNode.firstseg;
		while (true) {
			if (currentSeg!=null) {
				hexa += currentSeg.toString();				
			}
			if ((currentSeg!=null)&&(currentSeg.next!=null)) {
				currentSeg = currentSeg.next;
			} else {
				if (currentNode.next!=null) {
					currentNode = currentNode.next;
					currentSeg = currentNode.firstseg;
				} else {
					break;					
				}
			}
			
		}
		return(hexa);
	}
	public void calcAllOffset() {
		Node curNode = GetFirstNode();
		for (int i=0;i<CountAllNodes(curNode);i++) {
			curNode.calcOffset();
			if (curNode.next!=null) {
				curNode=curNode.next;
			}
		}
	}
	public String TitletoString() {
		Node currentNode = GetFirstNode();
		Segment currentSeg;
		int totalNode = CountAllNodes(currentNode);
		String thktit = "THKTIT\n";
		for (int i=0;i<totalNode;i++) {
			thktit += currentNode.title + "\n";
			currentNode = currentNode.next;			
		}
		System.out.println("Generated Titles: " + thktit);
		return(thktit);
	}
}
