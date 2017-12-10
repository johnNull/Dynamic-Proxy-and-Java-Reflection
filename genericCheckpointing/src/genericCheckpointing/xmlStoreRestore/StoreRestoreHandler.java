package genericCheckpointing.xmlStoreRestore;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.Results;
import genericCheckpointing.strategy.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StoreRestoreHandler implements InvocationHandler{

	private Object target;
	private FileProcessor fp;
	private Results r;
	private SerStrategy strat;

	//constructor that takes directory for input/output files
	public StoreRestoreHandler(String dir){
		fp = new FileProcessor(dir);
		r = new Results(dir);	
	}

	//redirect writeObj calls to XMLSerialization strategy for processing input
	//or redirect readObj calls to XMLDeserialization strategy for processing input
	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable{
			String mName = m.getName();
			if(mName.equals("readObj")){
				strat = new XMLDeserialization();
				Object serialized;
				serialized = strat.processInput(null, fp);
				return serialized;
			}
			if(mName.equals("writeObj")){
				strat = new XMLSerialization();
				strat.processInput((SerializableObject)args[0], r);
			}
			return null;
			
	}

	//closes file processor
	public void closeFP(){
		fp.fpClose();
	}

}
