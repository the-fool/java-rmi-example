package lab7RMI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MatrixServerImpl extends UnicastRemoteObject implements MatrixServerIntf {
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
		System.err.println("Server connected.");
	}


	public int[] addMatrix(int[] m, int rows) throws RemoteException {
		if (firstMatrix == null) {
			firstMatrix = m;
			System.out.println("Received Matrix A: \n" + prettyPrintMatrix(firstMatrix, rows));
			return null;
		} 
		System.out.println("Received Matrix B: \n" + prettyPrintMatrix(m, rows));
		for (int i = 0; i < m.length; i++)
			m[i] = m[i] = firstMatrix[i];
		System.out.println("Summed Matrix: \n" + prettyPrintMatrix(m, rows));
		return m;
	}
	
	private String prettyPrintMatrix(int[] m, int rows) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			sb.append("[");
			for (int j = 0; j < m.length/rows; j++)
				sb.append(m[j + (i * rows)] + " ");
			sb.append("]");
			sb.append(System.getProperty("line.separator"));		
		}
		
		return sb.toString();
	}

}
