package genericCheckpointing.xmlStoreRestore;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.Results;
import genericCheckpointing.strategy.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StoreRestoreHandler implements InvocationHandler{

	private Object target;
	private FileProcessor fp;

	public StoreRestoreHandler(String dir){
		fp = new FileProcessor(dir);
	}

	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable{
			Object result = m.invoke(target, args);
			return result;

	}

}
