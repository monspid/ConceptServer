package test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


public class Server {
	public static final int PORT = 2345;
	private int current;
	private int usersConnected;
	//private GameEngine gameEng;
	//private int lane;
	Server(){
		current = 0;
		usersConnected = 0;
		//lane = 0;
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		Server server = new Server();
		server.runServer();
	}
	
	public void runServer() throws IOException, ClassNotFoundException{		
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("Server ready");
			//gameEng = new GameEngine();
			Thread game = new Thread(new Game());
			game.start();
			while(true) {
				Socket clientSocket = serverSocket.accept();	
				usersConnected++; 
				
				Thread tServer = new Thread(new ClientsHandle(clientSocket));
				tServer.start();		
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public class Game implements Runnable{
		public void run(){
			while(true){
				//gameEng.makeObjects();
				//
				// Albo tak albo w wątku ClientsHandle
				// Przykładowy GameEngine dla ClientsHandle
				//
				//gameEng.removeObjects();
				//gameEng.updateVehicles();
				//gameEng.checkPlayerCollisions();
				//gameEng.checkOCollisions();
				
				//delete
				current++;
			}
				
		}
	}
	
	public class ClientsHandle implements Runnable {
		Socket socket;
		ObjectInputStream objectInputStream;
		ObjectOutputStream objectOutputStream;
		
		public ClientsHandle(Socket clientSocket) {
			try{
				socket = clientSocket;
				objectInputStream = new ObjectInputStream(socket.getInputStream());
				objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			Message msg;
			//String name = (String)objectInputStream.readObject());
			//
			// może stworzyć nową clasę CarPlayer???
			// wtedy w grze będzie łatwo można rozróżniać czy obiekt jest graczem
			// chyba, że by odpalać te związane z playerem w tym wątku
			//
			//Player player = new Player(name, new Car(lane, 0, usersConnected));
			//lane++;
			//gameEng.addPlayer(player);
			try {
				while ((msg = (Message)objectInputStream.readObject()) != null) {
					// gameEng.updatePlayer(player, msg.getUp(), msg.getDown(), msg.getLeft(), msg.getRight());
					//
					// Albo tak albo w watku Game
					//
					//gameEng.removeObjects(player);
					//gameEng.updateVehicles(player);
					//gameEng.checkPlayerCollisions(player);
					//gameEng.checkOCollisions(player);
					
					//map = gameEng.getMap();
					//objectOutputStream.writeObject(map);
					//objectOutputStream.writeObject(player);
					
					//
					// delete me
					//
					doSomething(msg);
					objectOutputStream.writeObject(msg);
					//
				}
				socket.close();
			}catch(SocketException e){
				System.out.println("is disconnected");
				usersConnected--;
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		//
		// delete me
		//
		private void doSomething(Message msg){
			msg.setResult(msg.getFirst()+ current);
		}
		//
	}

}
