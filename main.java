//http://stackoverflow.com/questions/2921406/calculate-primes-p-and-q-from-private-exponent-d-public-exponent-e-and-the
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
