package genericCheckpointing.strategy;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.FileIOInterface;

public interface SerStrategy{
	public SerializableObject processInput(SerializableObject sObj, FileIOInterface fio);
}
