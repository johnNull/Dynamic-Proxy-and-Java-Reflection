package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject{

	private double myDoubleT;
	private double myOtherDoubleT;
	private float myFloatT;
	private short myShortT;
	private short myOtherShortT;
	private char myCharT;

	public MyAllTypesSecond(){

	}

	public double getmyDoubleT(){
		return myDoubleT;
	}

	public void setmyDoubleT(double x){
		myDoubleT = x;
	}

	public double getmyOtherDoubleT(){
		return myOtherDoubleT;
	}

	public void setmyOtherDoubleT(double x){
		myOtherDoubleT = x;
	}

	public float getmyFloatT(){
		return myFloatT;
	}

	public void setmyFloatT(float x){
		myFloatT = x;
	}

	public short getmyShortT(){
		return myShortT;
	}

	public void setmyShortT(short x){
		myShortT = x;
	}

	public short getmyOtherShortT(){
		return myOtherShortT;
	}

	public void setmyOtherShortT(short x){
		myOtherShortT = x;
	}

	public char getmyCharT(){
		return myCharT;
	}

	public void setmyCharT(char x){
		myCharT = x;
	}

	@Override
	public boolean equals(Object o){
		return false;
	}
}
