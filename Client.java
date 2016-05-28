package test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

import org.omg.CORBA.portable.UnknownException;

public class Client {
	private static Socket socket;
	//private static Map map;
	//private static String name;
	
	/*
	private class KeyPressed {
		boolean up;
		boolean down;
		boolean left;
		boolean right;
	}
	*/
	
	/*
	public Client(String name){
		this.name = name; 
		key = new KeyPressed();
	}
	public Client(){
		this.name = "Player";
		key = new KeyPressed();
	}
	*/
	
	public static void main(String[] args) throws UnknownException, IOException, ClassNotFoundException{
		socket = new Socket ("localhost", Server.PORT);
		//
		// Display klasa tylko do wyświetlania
		//
		//Display display;
		//Player player;
		
		//
		// delete me
		Integer firstNumber = 1;
		//
		
		try{
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			//objectOutputStream.writeObject(name);
			while(true){
				// Message message = new Message(key.up, key.down, key.left, key.right);
				// objectOutputStream.writeObject(message); 
				// map = (Map)objectInputStream.readObject();
				// player = (Player)ObjectInputStream.readObject();
				// display = new Display(map, player);
				// display.animate();
				// display.showGame();
				
				//
				// delete me
				//
				Message message = new Message(firstNumber);
				objectOutputStream.writeObject(message); 
				Message returnMessage = (Message)objectInputStream.readObject();
				System.out.println(returnMessage.getResult());
				firstNumber++;
				//
			}
		}catch(ConnectException e){
			System.out.println("Server unreachable"); 
		}catch(SocketException e){
			System.out.println("Server connection lost");
		}
	}
	/*
	public void setKeyUp(boolean b){
		key.up= b;
	}
	public void setKeyDown(boolean b){
		key.down = b;
	}
	public void setKeyLeft(boolean b){
		key.left = b;
	}
	public void setKeyRight(boolean b){
		key.right = b;
	}
	*/
}
