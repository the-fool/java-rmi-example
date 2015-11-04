package lab7RMI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MatrixServerImpl extends UnicastRemoteObject implements MatrixServerIntf {
	boolean receivedMatrix;
	int[] firstMatrix;
	
	protected MatrixServerImpl() throws RemoteException {
		super();
		firstMatrix = null;
	}

	public static void main(String[] args) throws RemoteException {
		System.err.println("Initializing server.");
		MatrixServerImpl impl = new MatrixServerImpl();
		
		String serverObjectName = "//localhost/Server";
		try {
		Naming.rebind(serverObjectName, impl);
		} catch (Exception e) {
			System.out.println("Server failed to initialize: " + e);
		}
	}

	@Override
	public int[] addMatrix(int[] m, int rows) throws RemoteException {
		if (firstMatrix == null) {
			firstMatrix = m;
			System.out.println("Received Matrix A: " + firstMatrix);
			
		}
		return null;
	}

}
