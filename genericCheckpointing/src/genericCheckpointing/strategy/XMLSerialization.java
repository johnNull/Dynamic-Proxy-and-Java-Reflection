package genericCheckpointing.strategy;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.FileIOInterface;
import genericCheckpointing.Results;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class XMLSerialization implements SerStrategy{
	Results r;

	//Set up Serialization String for one Object and write it to the file
	public SerializableObject processInput(SerializableObject s, FileIOInterface re){
		r = (Results)re;
		r.append("<DPSerialization>\n");
			Class<?> cName = s.getClass();
			r.append(" <complexType xsi:type=\""+cName.getName()+"\">\n");
				String[] ss = getFieldStrings(cName, s);
				for(int i = 0; i < ss.length; i++)
					r.append(ss[i]);
			r.append(" </complexType>\n");
		r.append("</DPSerialization>\n");
		r.writeToFile();
		return null;
	}

	//Assuming all fields of the class are primitive, and have getters that are named correctly
	//return string formatted for field values
	String[] getFieldStrings(Class<?> cl, SerializableObject so){
			try{
				Field[] fs = cl.getDeclaredFields();
				Method[] ms = new Method[fs.length];
				for(int i = 0; i < ms.length; i++){
						ms[i] = cl.getMethod("get" + fs[i].getName());
				}
				String[] ss= new String[ms.length];
				for(int i = 0; i < ms.length; i++){
					if(fs[i].getType().getName().equals("boolean") || fs[i].getType().getName().equals("char") || fs[i].getType().getName().equals("float") || fs[i].getType().getName().equals("short"))
						ss[i] = "  <" + fs[i].getName() + " xsi:type=\"xsd:" + fs[i].getType().getName() + "\">" + ms[i].invoke(so) + "</" + fs[i].getName() + ">\n";
					else if(fs[i].getType().getName().equals("java.lang.String"))
						ss[i] = "  <" + fs[i].getName() + " xsi:type=\"xsd:string\">" + ms[i].invoke(so) + "</" + fs[i].getName() + ">\n";
					else if(fs[i].getType().getName().equals("int")){
						if((int)ms[i].invoke(so) >= 10)
							ss[i] = "  <" + fs[i].getName() + " xsi:type=\"xsd:" + fs[i].getType().getName() + "\">" + ms[i].invoke(so) + "</" + fs[i].getName() + ">\n";
						else
							ss[i] = "";
					}
					else if(fs[i].getType().getName().equals("long")){
						if((long)ms[i].invoke(so) >= 10)
							ss[i] = "  <" + fs[i].getName() + " xsi:type=\"xsd:" + fs[i].getType().getName() + "\">" + ms[i].invoke(so) + "</" + fs[i].getName() + ">\n";
						else
							ss[i] = "";
					}
					else if((double)ms[i].invoke(so) >= 10)
						ss[i] = "  <" + fs[i].getName() + " xsi:type=\"xsd:" + fs[i].getType().getName() + "\">" + ms[i].invoke(so) + "</" + fs[i].getName() + ">\n";
					else
						ss[i] = "";
				}
				return ss;
			}catch(IllegalAccessException | InvocationTargetException | NoSuchMethodException e){System.out.println(e.toString());}
			return null;
	}
}
