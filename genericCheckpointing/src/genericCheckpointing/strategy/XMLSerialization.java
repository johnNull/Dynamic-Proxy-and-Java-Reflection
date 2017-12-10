package genericCheckpointing.strategy;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.FileIOInterface;
import genericCheckpointing.Results;

public class XMLSerialization implements SerStrategy{
	Results r;
	public SerializableObject processInput(SerializableObject s, FileIOInterface re){
		r = (Results)re;
		r.write("line");
		return null;
	}
}
