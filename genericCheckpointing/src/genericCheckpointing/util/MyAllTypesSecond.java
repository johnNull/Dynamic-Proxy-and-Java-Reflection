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

	public double getmyDoubleT(){
		return myDouble;
	}

	public void setmyDoubleT(double x){
		myDouble = x;
	}

	public double getmyOtherDoubleT(){
		return myOtherDouble;
	}

	public void setmyOtherDoubleT(double x){
		myOtherDouble = x;
	}

	public float getmyFloatT(){
		return myFloat;
	}

	public void setmyFloatT(float x){
		myFloat = x;
	}

	public short getmyShortT(){
		return myShort;
	}

	public void setmyShortT(short x){
		myShort = x;
	}

	public short getmyOtherShortT(){
		return myOtherShort;
	}

	public void setmyOtherShortT(short x){
		myOtherShort = x;
	}

	public char getmyCharT(){
		return myChar;
	}

	public void setmyCharT(char x){
		myChar = x;
	}
}
