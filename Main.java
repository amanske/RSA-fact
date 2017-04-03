//Code is based on theory presented in App. C in http://csrc.nist.gov/publications/nistpubs/800-56B/sp800-56B.pdf [2017-04-03]
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.*;
import java.io.IOException;
import java.util.Random;

public class Main {
	public static void main (String args[]) {
		String[] data;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BigInteger modN, eexp, dexp, k;
		BigInteger y = null;
			try{
				for (String line = br.readLine(); line != null; line = br.readLine()) {

					data = line.split(" ");
					modN = new BigInteger(data[0]);
					eexp = new BigInteger(data[1]);
					dexp = new BigInteger(data[2]);
					k = new BigInteger(dexp.multiply(eexp).subtract(BigInteger.ONE).toString()); // k = d*e - 1

					if(!k.testBit(0)){ //if k is even (if the LSB is 1, the whole number is odd)
						//rewrite k as (2^t)*r, r = largest integer that can divide k
						BigInteger r = k;
						BigInteger t = new BigInteger("1");
						//since k (and r) are even, we continue to divide until we cant split r by 2 anymore. 
						r = r.divide(BigInteger.valueOf(2));
						while(!r.testBit(0)){
							r = r.divide(BigInteger.valueOf(2));
							t = t.add(BigInteger.ONE);
						} //r now holds the value of the biggest integer that can divide k

						Random rand = new Random();
						//loop 100 times
						searchloop: for (int i = 1; i <= 100; i++) {
							BigInteger bigrand = bigRand(modN, rand); //generate a random integer
							y = new BigInteger(bigrand.modPow(r, modN).toString()); // y = bigrand^r mod n

							if(y.equals(BigInteger.ONE) || y.equals(modN.subtract(BigInteger.ONE))){ //if y = 1 or y = n - 1 then start searchloop again
								continue searchloop; //start over
							}

							//iterate from j=1 to t-1
							for (BigInteger j = BigInteger.ONE; j.compareTo(t) <= 0; j = j.add(BigInteger.ONE)) {
								BigInteger x = y.modPow(BigInteger.valueOf(2), modN); // x = y^2 mod n


								if (x.equals(BigInteger.ONE)) { //found
								    break searchloop;
								}
								if (x.equals(modN.subtract(BigInteger.ONE))) { //x = n-1 , jump to seachloop
								    continue searchloop;
								}
									y = x;

					        }

							BigInteger x = y.modPow(BigInteger.valueOf(2), modN); // x = y^2 mod n

					        if (x.equals(BigInteger.ONE)) { //found
						            break searchloop;
					        }
						}

						
				        BigInteger p = new BigInteger(y.subtract(BigInteger.ONE).gcd(modN).toString()); //p = gcd(y-1, n)
				        BigInteger q = new BigInteger(modN.divide(p).toString()); //q = n/p

				        //print p and q, smallest of the first.
				        if(p.compareTo(q) <= 0){
					        System.out.print(p + " ");
					        System.out.println(q);
				        } else {
				        	System.out.print(q + " ");
					        System.out.println(p);
				        }
				    
					}

				}
			} catch (IOException e){
				//If we reach an empty line the input is completed, do nothing
			}

	}
	/* Generates a random BigInteger. */
	private static BigInteger bigRand(BigInteger n, Random rand) {
    BigInteger bigrand;
    do {
        bigrand = new BigInteger(n.bitLength(), rand);
    } while (bigrand.compareTo(n) >= 0); //bigrand >= n
    return bigrand;
}
}
