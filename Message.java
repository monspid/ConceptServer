package test;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/*
	private static boolean up;
	private static boolean down;
	private static boolean left;
	private static boolean right;
	
	public Message(boolean u, boolean d, boolean l, boolean r){
		up = u;
		down = d;
		left = l;
		right = r;
	}
	
	public boolean getUp(){
		return up;
	}
	
	public boolean getDown(){
		return down;
	}
	
	public boolean getLeft(){
		return left;
	}
	
	public boolean getRight(){
		return right;
	}
	*/
	
	//delete
	Integer firstNumber = null;
	Integer result = null;
	public Message(Integer firstNumber){
		this.firstNumber = firstNumber;
	}
	public Integer getFirst(){
		return firstNumber;
	}
	
	public Integer getResult(){
		return result;
	}
	
	public void setResult(Integer result){
		this.result = result;
	}

}
