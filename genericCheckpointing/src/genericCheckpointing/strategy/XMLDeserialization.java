package genericCheckpointing.strategy;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.FileIOInterface;
import genericCheckpointing.util.FileProcessor;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class XMLDeserialization implements SerStrategy{

	final Pattern valFieldReg = Pattern.compile("xsd:(.*)\">(.*)</(\\w+)");
	final Pattern classReg = Pattern.compile("<complexType xsi:type=\"(.+)\">");
	FileProcessor fp;

	//Create a SerializableObject based on XML fields and return it
	public SerializableObject processInput(SerializableObject s, FileIOInterface filep){
		try{
			fp = (FileProcessor)filep;
			String ln;
			String[] fields;
			ln = getNextClass();
			if(ln == null) return null;
			Class<?> c = Class.forName(ln);
			Object obj = c.newInstance();
			ln = fp.readLine();
			while(!ln.equals(" </complexType>") && !ln.equals("\t</complexType>") && ln != null){
				fields = getFields(ln);
				Class<?>[] sig = new Class<?>[]{getPrimClass(fields[0])};
				Method m = c.getMethod("set" + fields[2], sig);
				Object[] params = new Object[]{getParamType(fields[0], fields[1])};
				m.invoke(obj, params);
				ln = fp.readLine();
			}
			return (SerializableObject)obj;
		}catch(ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){System.out.println(e.toString());}
		return null;
	}

	//Return the type, value, and variable name of a field
	String[] getFields(String ln){
		Matcher m = valFieldReg.matcher(ln);
		m.find();
		return new String[]{m.group(1), m.group(2), m.group(3)};
	}

	//Find the string containing class info from XML and return the class name
	String  getNextClass(){
		String ln = "";
		Matcher m = classReg.matcher(ln);
		while(!m.find() || ln.equals("-1")){
			ln = fp.readLine();
			if(ln == null) return null;
			m = classReg.matcher(ln);
		}
		return m.group(1);
	}

	//Could use apache ClassUtils, but not sure if that is allowed
	//returns Class variable based on text input
	Class getPrimClass(String type){
		if(type.equals("int"))
			return int.class;
		if(type.equals("double"))
			return double.class;
		if(type.equals("boolean"))
			return boolean.class;
		if(type.equals("short"))
			return short.class;
		if(type.equals("long"))
			return long.class;
		if(type.equals("float"))
			return float.class;
		if(type.equals("string"))
			return String.class;
		if(type.equals("char"))
			return char.class;
		return null;
	}

	//Parses input value according to specified type String
	Object getParamType(String type, String val){
		if(type.equals("int"))
			return Integer.parseInt(val);
		if(type.equals("double"))
			return Double.parseDouble(val);
		if(type.equals("boolean"))
			return Boolean.parseBoolean(val);
		if(type.equals("short"))
			return Short.parseShort(val);
		if(type.equals("long"))
			return Long.parseLong(val);
		if(type.equals("float"))
			return Float.parseFloat(val);
		if(type.equals("string"))
			return val;
		if(type.equals("char"))
			return val.charAt(0);
		return null;
	}
}
