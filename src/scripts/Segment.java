package scripts;

public class Segment {
	Node parent;
	Segment next;
	boolean nodeEnd = false;
	String choice = "00";		//"00" = No Choice; "40" = Begin First Choice; "80" = Begin Last Choice 
	String interruptType = "00";	//"00" = None; "08" = Return; "80" = Loopback
	String ifCon = "00";		//"00" = None; "01" = end "choice"; "02" = if; "04" = else/else if; "08" = end if
	String check = "0200";		//Function Type; Note that byte order is reversed in the actual display
	int parameter = 0;			//For "check"
	int paramete2 = 0;
	int referenceType = 0;		//"0" = None; "1" = External/55, "2" = Local
	int referenceID = 0;
	int action = 0;
	int actUnkn1 = 0;
	int actUnkn2 = 0;
	int actUnkn3 = 0;
	int actUnkn4 = 0;
	int actUnkn5 = 0;
	String unknGap1 = massPrint("0", 10);
	String unknGap2 = massPrint("0", 4);
	String unknGap3 = massPrint("0", 32);
	String unknGap4 = massPrint("0", 8);
	String unknGap5 = massPrint("0", 40);
	String unknGap6 = massPrint("0", 8);	
	String unknGap7 = massPrint("0", 48);
	public Segment(Node parent) {
		this.parent = parent;
	}
	public String massPrint(String text, int amt) {		//Returns a String of a bunch of characters
		String value = "";
		for(int i=0;i<amt;i++) {
			value += text;
		}
		return(value);
	}
	public String XByteHex(int ori, int offset, int len) {
		String oldval = Integer.toHexString(ori+offset);	
		String newhex = "";								//The Parameter being exported
		if (oldval.length()%2==1) {						//Formats Parameter (1)
			oldval = "0" + oldval;
		}
		for (int i=oldval.length();i>0;i-=2) {			//Reverses Byte Order
			newhex += oldval.substring(i-2, i);
		}			
		while (newhex.length()<len) {						//Formats Parameter (2)
			newhex += "00";
		}
		return(newhex);
	}
	public String toString() {
		String hexa = "";								//The full hex string
		String convPara = XByteHex(parameter, 0, 8);
		String convPar2 = XByteHex(paramete2, 0, 8);		
		String convAct = XByteHex(action, 0, 8);
		String convU1 = XByteHex(actUnkn1, 0, 8);
		String convU2 = XByteHex(actUnkn2, 0, 8);		
		String convU3 = XByteHex(actUnkn3, 0, 8);		
		String convU4 = XByteHex(actUnkn4, 0, 8);		
		String convU5 = XByteHex(actUnkn5, 0, 8);		
		if (nodeEnd) {									//Sets up segment to be a node end
			hexa += "01" + massPrint("0", 4) + unknGap1 + massPrint("0", 4) + unknGap2 + massPrint("0", 8) + unknGap3 + massPrint("0", 8) + XByteHex(parent.index, 9999, 8) + massPrint("f", 32) + unknGap5 + massPrint("0", 32) + unknGap6 + massPrint("0", 16) + unknGap7;
			return(hexa);
		} else if (!choice.equals("00")) {				//Checks to see if the segment makes a "choice" (If true, zeros-out everything except choice and parameter)
			hexa += choice + massPrint("0", 4) + unknGap1 + massPrint("0", 4) + unknGap2 + convPara + unknGap3 + convPar2 + unknGap4;
		} /*else if(!interruptType.equals("00")) {		//Checks Interrupt Type; Auto-returns if there's an interrupt 
			hexa += choice + interruptType + massPrint("0", 12) + "02" + massPrint("0", 62) + massPrint("f", 32) + massPrint("0", 144);
			return(hexa);
		}*/ else {										//Just puts everything into "hexa"
			hexa += choice + interruptType + ifCon + unknGap1 + check + unknGap2 + convPara + unknGap3 + convPar2 + unknGap4;
		}
		if (referenceType==0) {							//Checks Reference Type
			hexa += massPrint("f", 32) + unknGap5;
		} else {
			if (referenceType==1) {
				hexa += XByteHex(55, 0, 8) + XByteHex(referenceID, 0, 8) + massPrint("f", 16) + unknGap5;
			} else {
				hexa += massPrint("f", 16) + XByteHex(referenceID, 0, 8) + massPrint("0", 8) + unknGap5;				
			}
		}
		hexa += convAct + convU1 + convU2 + convU3 + unknGap6 + convU4 + convU5 + unknGap7;
		return(hexa);
	}
}
