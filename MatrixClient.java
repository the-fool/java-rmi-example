package lab7RMI;

import java.rmi.Naming;
import java.util.Random;

public class MatrixClient {
	Random r;
	public MatrixClient() {
		r = new Random();
		sendMatrix();
	}
	
	private int[] generateMatrix(int rows) {
		int[] m = new int[rows * rows];
		for (int i = 0; i < rows; i++)
			for (int j=0; j < rows; j++)
				m[i + (j * rows)] = r.nextInt(2);
		return m;
	}

	private void sendMatrix() {
		try {
			String serverObjectName = "//127.0.0.1/Server";
			System.err.println("Getting Connected to the server: "+ serverObjectName );
			MatrixServerIntf mserver = (MatrixServerIntf) Naming.lookup(serverObjectName);
			System.err.println("Connected to server.");
			
			int m[] = generateMatrix(3);
			
			int ret[] = mserver.addMatrix(m, 3);
			System.out.println("Sent to server: \n" + prettyPrintMatrix(m, 3));
			if (ret == null) {
				System.out.println("Server waiting for second matrix...");
			} else {
				System.out.println(ret);
			}
		} catch (java.rmi.ConnectException ce) {
			System.err.println("Connection to server failed. " + "Server may be temporarily unavailable.");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

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
	public static void main(String args0[]) {
		new MatrixClient();
	}
}