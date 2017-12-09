package genericCheckpointing.xmlStoreRestore;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.Results;
import genericCheckpointing.strategy.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StoreRestoreHandler implements InvocationHandler{

	private Object target;
	private FileProcessor fp;
	private Results r;
	private SerStrategy strat;

	public StoreRestoreHandler(String dir){
		fp = new FileProcessor(dir);
		r = new Results(dir);	
	}

	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable{
			String mName = m.getName();
			if(mName.equals("readObj")){
				strat = new XMLDeserialization();
				Object serialized;
				serialized = strat.processInput(null, fp);
				return serialized;
			}
			return null;
			
	}

}
