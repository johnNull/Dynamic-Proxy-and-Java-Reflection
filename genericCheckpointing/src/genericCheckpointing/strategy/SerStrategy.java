package genericCheckpointing.strategy;
import genericCheckpointing.util.SerializableObject;

public interface SerStrategy{
	public void processInput(SerializableObject sObj);
}
