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

	public SerializableObject processInput(SerializableObject s, FileIOInterface filep){
		try{
			fp = (FileProcessor)filep;
			String ln;
			String[] fields;
			ln = getNextClass();
			System.out.println(ln);
			Class<?> c = Class.forName(ln);
			Object obj = c.newInstance();
			ln = fp.readLine();
			while(!ln.equals(" </complexType>") && !ln.equals("\t</complexType>")){
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

	String[] getFields(String ln){
		System.out.println(ln + "I am here");
		Matcher m = valFieldReg.matcher(ln);
		m.find();
		System.out.println(m.group(1) + " " + m.group(2) + " " + m.group(3));
		return new String[]{m.group(1), m.group(2), m.group(3)};
	}

	String  getNextClass(){
		String ln = "";
		Matcher m = classReg.matcher(ln);
		while(!m.find() || ln.equals("-1")){
			ln = fp.readLine();
			m = classReg.matcher(ln);
		}
		return m.group(1);
	}

	//Could use apache ClassUtils, but not sure if that is allowed
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
