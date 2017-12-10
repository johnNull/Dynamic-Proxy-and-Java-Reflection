package genericCheckpointing.driver;

import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

// import the other types used in this file

public class Driver {
    
    public static void main(String[] args) {
	
	// FIXME: done read the value of checkpointFile from the command line
	if(args == null || args.length != 3)
				throw new IllegalArgumentException("Three arguments are required, mode, number of objects, and file name");
	else{
		int NUM_OF_OBJECTS = 0;
		try{
			NUM_OF_OBJECTS = Integer.parseInt(args[1]);
		}
		catch(Exception e){
			
		}

		ProxyCreator pc = new ProxyCreator();

		// FIXME: done create an instance of StoreRestoreHandler (which implements
		// the InvocationHandler
		StoreRestoreHandler srh = new StoreRestoreHandler(args[2]);

		// create a proxy
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
									 new Class[] {
									     StoreI.class, RestoreI.class
									 }, 
									 srh
									 );
	
		// FIXME: done invoke a method on the handler instance to set the file name for checkpointFile and open the file

		MyAllTypesFirst myFirst;
		MyAllTypesSecond  mySecond;

		/*SerializableObject myRecordRet;
		SerializableObject myRecordRet2;
		myRecordRet = ((RestoreI) cpointRef).readObj("XML");
		myRecordRet2 = ((RestoreI) cpointRef).readObj("XML");
		System.out.println(((MyAllTypesFirst)myRecordRet).getmyInt() + " I am in Driver");
		System.out.println(((MyAllTypesSecond)myRecordRet2).getmyDoubleT() + " I am in Driver");*/

		myFirst = new MyAllTypesFirst();
		myFirst.setmyInt(4);
		myFirst.setmyOtherInt(2);
		myFirst.setmyLong(32);
		myFirst.setmyOtherLong(27);
		myFirst.setmyString("Working");
		myFirst.setmyBool(false);
		((StoreI) cpointRef).writeObj(myFirst, 1, "XML");
		((StoreI) cpointRef).writeObj(myFirst, 1, "XML");

		// Use an if/switch to proceed according to the command line argument
		// For deser, just deserliaze the input file into the data structure and then print the objects
		// The code below is for "serdeser" mode
		// For "serdeser" mode, both the serialize and deserialize functionality should be called.

		// create a data structure to store the objects being serialized
		// NUM_OF_OBJECTS refers to the count for each of MyAllTypesFirst and MyAllTypesSecond
		/*COMMENTS HEREfor (int i=0; i<NUM_OF_OBJECTS; i++) {

		    // FIXME: create these object instances correctly using an explicit value constructor
		    // use the index variable of this loop to change the values of the arguments to these constructors
		    myFirst = new MyAllTypesFirst();
		    mySecond = new MyAllTypesSecond();

		    // FIXME: store myFirst and mySecond in the data structure
		    ((StoreI) cpointRef).writeObj(myFirst, 1, "XML");
		    ((StoreI) cpointRef).writeObj(mySecond, 1, "XML");

		}

		SerializableObject myRecordRet;

		// create a data structure to store the returned ojects
		for (int j=0; j<2*NUM_OF_OBJECTS; j++) {

		    myRecordRet = ((RestoreI) cpointRef).readObj("XML");
		    // FIXME: store myRecordRet in the vector
		}COMMENTS HERE*/

		// FIXME: invoke a method on the handler to close the file (if it hasn't already been closed)
		srh.closeFP();

		// FIXME: compare and confirm that the serialized and deserialzed objects are equal. 
		// The comparison should use the equals and hashCode methods. Note that hashCode 
		// is used for key-value based data structures
	}
    
    }
}
