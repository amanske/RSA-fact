import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.*;

public class Main {
	public static void main (String args[]) {
		String[] data;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		BigInteger modN, eexp, dexp;
		while((line = br.readLine()) =! null){
			data = line.split(" ");
			modN = new BigInteger(data[0]);
			eexp = new BigInteger(data[1]);
			dexp = new BigInteger(data[2]);

			BigInteger sqrtN = new BigInteger(sqrt(modN));


			
		}
	}
}
