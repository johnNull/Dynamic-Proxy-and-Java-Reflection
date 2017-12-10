package genericCheckpointing.strategy;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.FileIOInterface;

//Interface for serialization and deserialization strategies
public interface SerStrategy{
	public SerializableObject processInput(SerializableObject sObj, FileIOInterface fio);
}
