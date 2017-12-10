package genericCheckpointing.util;
import java.util.Objects;

public class MyAllTypesSecond extends SerializableObject{

	private double myDoubleT;
	private double myOtherDoubleT;
	private float myFloatT;
	private short myShortT;
	private short myOtherShortT;
	private char myCharT;

	public MyAllTypesSecond(){}

	//explicit value constructor
	public MyAllTypesSecond(double d, double d2, float f, short s, short s2, char c){
		myDoubleT = d;
		myOtherDoubleT = d2;
		myFloatT = f;
		myShortT = s;
		myOtherShortT = s2;
		myCharT = c;
	}

	//Getters and setters for fields
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
		if(!(o instanceof MyAllTypesSecond))
			return false;
		MyAllTypesSecond obj = (MyAllTypesSecond)o;
		if(myDoubleT == obj.myDoubleT && myOtherDoubleT == obj.myOtherDoubleT && myFloatT == obj.myFloatT && myShortT == obj.myShortT && myOtherShortT == obj.myOtherShortT && myCharT == obj.myCharT)
			return true;
		return false;
	}

	@Override
	public int hashCode(){
		int ret = Objects.hash(myFloatT, myShortT, myOtherShortT, myCharT, myDoubleT, myOtherDoubleT);
		return ret;
	}

	@Override
	public String toString(){
		return "MyAllTypesSecond Fields:\nmyDoubleT: " + myDoubleT + "\nmyOtherDoubleT: " + myOtherDoubleT + "\nmyFloatT: " + myFloatT + "\nmyShortT: " + myShortT + "\nmyOtherShortT: " + myOtherShortT + "\nmyCharT: " + myCharT + "\n";
	}
}
