package genericCheckpointing.strategy;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.FileIOInterface;
import genericCheckpointing.Results;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class XMLSerialization implements SerStrategy{
	Results r;
	public SerializableObject processInput(SerializableObject s, FileIOInterface re){
		r = (Results)re;
		r.append("<DPSerialization>\n");
			Class<?> cName = s.getClass();
			r.append(" <complexType xsi:type=\""+cName.getName()+"\">\n");
				String[] ss = getFieldStrings(cName);
				for(int i = 0; i < ss.length; i++)
					r.append(ss[i]);
			r.append(" </complexType>");
		r.append("</DPSerialization>\n");
		r.writeToFile();
		return null;
	}

	//Assuming all fields of the class have getters, return all of the getter Methods
	public String[] getFieldStrings(Class<?> cl){
			try{
				System.out.println("Here");
				Object s = cl.newInstance();
				Field[] fs = s.getClass().getDeclaredFields();
				Method[] ms = new Method[fs.length];
				for(int i = 0; i < ms.length; i++){
						ms[i] = cl.getMethod("get" + fs[0].getName());
						System.out.println("Got Method");
				}
				String[] ss= new String[ms.length];
				for(int i = 0; i < ms.length; i++){
					ss[i] = "  <" + fs[i].getName() + " xsi:type=\"xsd:" + fs[i].getType().getName() + "\">" + ms[i].invoke(s) + "</" + fs[i].getName() + ">\n";
					System.out.println("  <" + fs[i].getName() + " xsi:type=\"xsd:" + fs[i].getType().getName() + "\">" + fs[i].getType().cast(ms[i].invoke(s)) + "</" + fs[i].getName() + ">\n");
				}
				return ss;
			}catch(InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e){e.toString();}
			return null;
	}
}
