*
 * Example usage
 * To compile:
 * javac -cp target/*.jar example.java
 *
 * To run:
 * java  -Djava.library.path=lib   example
 *
 */
import org.dfdeshom.math.*;

class pidigits {

    GMP q = new GMP(1), r = new GMP(0),
   s = new GMP(0), t = new GMP(1);
    GMP u = new GMP(1), v = new GMP(1),
   w = new GMP(1);

   int i, k, c;
   int digit;
   int d;
   StringBuffer strBuf = new StringBuffer(20);
   final int n;
	 

   private pidigits(int n)
   {
      this.n=n;
   }

   private void compose_r(int bq, int br, int bs, int bt)
   {

     u.multiply(r, new GMP(bs));
     r.multiply(r, new GMP(bq));
     v.multiply(t, new GMP(br));
     r.add(r, v);
     t.multiply(t, new GMP(bt));
     t.add(t, u);
     s.multiply(s, new GMP(bt));
     u.multiply(q, new GMP(bs));
     s.add(s, u);
     q.multiply(q, new GMP(bq));
   }

   /* Compose matrix with numbers on the left. */
   private void compose_l(int bq, int br, int bs, int bt)
   {
     r.multiply(r, new GMP(bt));
     u.multiply(q, new GMP(br));
     r.add(r, u);
     u.multiply(t, new GMP(bs));
     t.multiply(t, new GMP(bt));
     v.multiply(s, new GMP(br));
     t.add(t, v);
     s.multiply(s, new GMP(bq));
     s.add(s, u);
     q.multiply(q, new GMP(bq));
   }

   /* Extract one digit. */
   private int extract(int j)
   {
     u.multiply(q, new GMP(j));
     u.add(u, r);
     v.multiply(s, new GMP(j));
     v.add(v, t);
     //w.divide(u, v);
     return w.intValue();
   }
	  /* Print one digit. Returns 1 for the last digit. */
	    private boolean prdigit(int y)
	    {
	       strBuf.append(y);
	       if (++i % 10 == 0 || i == n) {
	          if (i%10!=0) for (int j=10-(i%10);j>0;j--) { strBuf.append(" "); }
	          strBuf.append("\t:");
	          strBuf.append(i);
	          System.out.println(strBuf);
	          strBuf = new StringBuffer(20);
	       }
	       return i == n;
	    }
	    /* Generate successive digits of PI. */
	    void pidigits()
	    {
	      int k = 1;
	      d = 0;
	      i = 0;
	      //q.set(1);
	      //r.set(0);
	      //s.set(0);
	      //t.set(1);
	      for (;;) {
	        int y = extract(3);
	        if (y == extract(4)) {
	          if (prdigit(y)) return;
	          compose_r(10, -10*y, 0, 1);
	        } else {
	          compose_l(k, 4*k+2, 0, 2*k+1);
	          k++;
	        }
	      }
	    }
	     public static void main(String[] args) {
	         //Properties p = System.getProperties();
	         //p.list(System.out);
	     pidigits m = new pidigits(Integer.parseInt(args[0]));
	       m.pidigits();

	     }
	 }