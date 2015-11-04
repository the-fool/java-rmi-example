package lab7RMI;
import java.rmi.*;

public interface MatrixServerIntf extends Remote {
	public int[] addMatrix(int[] m, int rows) throws RemoteException; 
}
