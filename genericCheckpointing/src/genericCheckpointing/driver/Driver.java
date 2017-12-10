package genericCheckpointing.driver;

import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;
import java.util.Vector;

// import the other types used in this file

public class Driver {
    
    public static void main(String[] args) {
	
	// FIXME: done read the value of checkpointFile from the command line
	if(args == null || args.length != 3)
				throw new IllegalArgumentException("Three arguments are required, mode, number of objects, and file name");
	else{
		int NUM_OF_OBJECTS = 0;
		int iterateAmt = 0;
		boolean ser = true;
		try{
			NUM_OF_OBJECTS = Integer.parseInt(args[1]);
		}
		catch(Exception e){
			
		}

		ProxyCreator pc = new ProxyCreator();

		StoreRestoreHandler srh = new StoreRestoreHandler(args[2]);

		// create a proxy
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
									 new Class[] {
									     StoreI.class, RestoreI.class
									 }, 
									 srh
									 );

		MyAllTypesFirst myFirst;
		MyAllTypesSecond  mySecond;


		// Use an if/switch to proceed according to the command line argument
		// For deser, just deserliaze the input file into the data structure and then print the objects
		// The code below is for "serdeser" mode
		// For "serdeser" mode, both the serialize and deserialize functionality should be called.
		if(args[0].equals("deser")){
			ser = false;
			iterateAmt = NUM_OF_OBJECTS;
		}
		else if(args[0].equals("serdeser")){
			ser = true;
			iterateAmt = NUM_OF_OBJECTS*2;
		}
		else
			throw new IllegalArgumentException("Available modes are serdeser and deser");

		// create a data structure to store the objects being serialized
		// NUM_OF_OBJECTS refers to the count for each of MyAllTypesFirst and MyAllTypesSecond
		Vector<SerializableObject> vOld = new Vector<SerializableObject>();

		//Serialize
		for (int i=0; i<NUM_OF_OBJECTS && ser; i++) {
		    	myFirst = new MyAllTypesFirst(i*3, i*5, i+1, i*10, "String: " + i, (i%3 == 0));
		    	mySecond = new MyAllTypesSecond(i*3.14, i*7.2, i * 2.33f, (short)(i*9), (short)(i*2), (char)(i + 65));
			vOld.add(myFirst);
			vOld.add(mySecond);

		    // store myFirst and mySecond in the data structure
		    ((StoreI) cpointRef).writeObj(myFirst, 1, "XML");
		    ((StoreI) cpointRef).writeObj(mySecond, 1, "XML");

		}

		SerializableObject myRecordRet;
		// create a data structure to store the returned ojects
		Vector<SerializableObject> vNew = new Vector<SerializableObject>();
		
		//Deserialize
		for (int j=0; j<iterateAmt; j++) {

		    	myRecordRet = ((RestoreI) cpointRef).readObj("XML");
			if(myRecordRet == null)
			throw new IllegalArgumentException("Number of objects is too large");
		    	vNew.add(myRecordRet);
		}
		// invoke a method on the handler to close the file (if it hasn't already been closed)
		srh.closeFP();

		// compare and confirm that the serialized and deserialzed objects are equal. 
		// The comparison should use the equals and hashCode methods. Note that hashCode 
		// is used for key-value based data structures
		int mismatch = 0;
		if(vOld.size() == vNew.size() && ser){		
			for(int i = 0; i < vOld.size(); i++){
				if(!vOld.get(i).equals(vNew.get(i)) && vOld.get(i).hashCode() != vNew.get(i).hashCode())
					mismatch++;
			}
			System.out.println(mismatch + " mismatched objects");
		}
		else{
			for(int i = 0; i < vNew.size(); i++)
				System.out.println(vNew.get(i).toString());
		}
		
	}
    
    }
}
