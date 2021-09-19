package scripts;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Converter {
	public String FileToHex(String loca) {
		try {	

			String holder;
			StringBuilder hexa = new StringBuilder("");
			File file = new File(loca);
			FileInputStream fis = new FileInputStream(file);
			FileChannel fC = fis.getChannel();

			ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());			
			fC.read(byteBuffer);
			byteBuffer.flip();
			
			for (int i=0;i<byteBuffer.limit();i++) {
				holder = Integer.toHexString(byteBuffer.get() & 0xFF);
				if (holder.length()>1) {
					hexa.append(holder);
				} else {
					hexa.append("0");
					hexa.append(holder);
				}
			}
			
			fC.close();
			fis.close();
			return(hexa.toString());
		} catch(Exception e) {	// Standard Error Catch
			return("z");
		}
	}
	
	public void HexToFile(String hexa, String loca) {
		int holder;
		try {	//Prepares the Buffered Output Stream, Converts Hexa to Decimal,and Writes to File 
			BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(loca));
		    for(int i=0;i<hexa.length();i+=2) {
		    	holder = Integer.parseInt(hexa.substring(i, i+2), 16);
		    	writer.write(holder);
		    }				     
		    writer.close();
	    } catch(Exception e) {
	    	System.out.println("Error");
	    }
    }
	
	public boolean stringAllInts(String base) {
		try {
			Integer.parseInt(base);
		} catch(Exception NumberFormatException) {
			System.out.println(base + " is not an integer. (sAI)");
			return(false);
		}
		return(true);
	}
	
	public boolean stringAllIntsHex(String base) {
		try {
			Integer.parseInt(base, 16);
		} catch(Exception NumberFormatException) {
			System.out.println(base + " is not an integer. (sAIH)");
			return(false);
		}
		return(true);
	}
	
	// Alternative File Reading Method for writing regular, readable text, instead of hexadeciamal data
	public String getTextFrom(String header, String loca) {
		String newText = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(loca));
			String s;
			while ((s = br.readLine()) != null) {
				newText += s + "\n";
			}
			if ((newText.length()<header.length())||(!newText.substring(0, header.length()).equals(header))) {
				//System.out.println("Header Mismatch in " + loca);
				newText = "END";
			}
			br.close();
		} catch(Exception e) {
			//System.out.println("Error in " + loca);
			newText = "END";
		}
		return(newText);
	}
	
	// Alternative File Writing Method for writing regular, readable text, instead of hexadeciamal data
	public void writeTextTo(String text, String loca) {
		System.out.println("Starting Write");
		try {
			FileWriter fr = new FileWriter(loca);
			//System.out.println("Writing \"" + titles + "\" to " + loca);
			fr.write(text);
			fr.close();
		} catch(Exception e) {
			System.out.println("Error");
		}
	}
}
