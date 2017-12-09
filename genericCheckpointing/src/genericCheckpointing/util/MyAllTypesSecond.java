package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject{

	private double myDouble;
	private double myOtherDouble;
	private float myFloat;
	private short myShort;
	private short myOtherShort;
	private char myChar;

	public MyAllTypesSecond(){

	}

	public double getmyDouble(){
		return myDouble;
	}

	public void setmyDouble(double x){
		myDouble = x;
	}

	public double getmyOtherDouble(){
		return myOtherDouble;
	}

	public void setmyOtherDouble(double x){
		myOtherDouble = x;
	}

	public float getmyFloat(){
		return myFloat;
	}

	public void setmyFloat(float x){
		myFloat = x;
	}

	public short getmyShort(){
		return myShort;
	}

	public void setmyShort(short x){
		myShort = x;
	}

	public short getmyOtherShort(){
		return myOtherShort;
	}

	public void setmyOtherShort(short x){
		myOtherShort = x;
	}

	public char getmyChar(){
		return myChar;
	}

	public void setmyChar(char x){
		myChar = x;
	}
}
