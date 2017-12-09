package genericCheckpointing.strategy;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.FileIOInterface;
import genericCheckpointing.util.FileProcessor;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class XMLDeserialization implements SerStrategy{

	final Pattern valFieldReg = Pattern.compile("xsd:(.*)\">(.*)</(\\w+)");
	final Pattern classReg = Pattern.compile("<complexType xsi:type=\"(.+)\">");
	FileProcessor fp;

	public SerializableObject processInput(SerializableObject s, FileIOInterface filep){
		System.out.println("Before getFields");
		System.out.flush();
		fp = (FileProcessor)filep;
		String ln;
		String[] fields;
		ln = getNextClass();
		System.out.println(ln);
		ln = fp.readLine();
		fields = getFields(ln);
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
}
