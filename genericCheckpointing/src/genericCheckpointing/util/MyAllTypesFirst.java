package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject{
	
	private int myInt;
	private int myOtherInt;
	private long myLong;
	private long myOtherLong;
	private String myString;
	private boolean myBool;
	

	public MyAllTypesFirst(){

	}

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
		return false;
	}
}
