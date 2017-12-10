package genericCheckpointing.util;
import java.util.Objects;

public class MyAllTypesFirst extends SerializableObject{
	
	private int myInt;
	private int myOtherInt;
	private long myLong;
	private long myOtherLong;
	private String myString;
	private boolean myBool;
	
	public MyAllTypesFirst(){}

	//explicit value constructor
	public MyAllTypesFirst(int i, int i2, long l, long l2, String s, boolean b){
		myInt = i;
		myOtherInt = i2;
		myLong = l;
		myOtherLong = l2;
		myString = s;
		myBool = b;
	}

	//Getters and setters for fields
	public int getmyInt(){
		return myInt;
	}

	public void setmyInt(int x){
		myInt = x;
	}
	
	public int getmyOtherInt(){
		return myOtherInt;
	}

	public void setmyOtherInt(int x){
		myOtherInt = x;
	}

	public long getmyLong(){
		return myLong;
	}

	public void setmyLong(long x){
		myLong = x;
	}

	public long getmyOtherLong(){
		return myOtherLong;
	}

	public void setmyOtherLong(long x){
		myOtherLong = x;
	}

	public String getmyString(){
		return myString;
	}

	public void setmyString(String x){
		myString = x;
	}

	public boolean getmyBool(){
		return myBool;
	}

	public void setmyBool(boolean x){
		myBool = x;
	}

	@Override
	public boolean equals(Object o){
		if(!(o instanceof MyAllTypesFirst))
			return false;
		MyAllTypesFirst obj = (MyAllTypesFirst)o;
		if(myInt == obj.myInt && myOtherInt == obj.myOtherInt && myLong == obj.myLong && myOtherLong == obj.myOtherLong && myString.equals(obj.myString) && myBool == obj.myBool)
			return true;
		return false;
	}

	@Override
	public int hashCode(){
		int ret = Objects.hash(myInt, myOtherInt, myLong, myOtherLong, myString, myBool);
		return ret;
	}

	@Override
	public String toString(){
		return "MyAllTypesFirst Fields:\nmyInt: " + myInt + "\nmyOtherInt: " + myOtherInt + "\nmyLong: " + myLong + "\nmyOtherLong: " + myOtherLong + "\nmyString: " + myString + "\nmyBool: " + myBool + "\n";
	}
}
