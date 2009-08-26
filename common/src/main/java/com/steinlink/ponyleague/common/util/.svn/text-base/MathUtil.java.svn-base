package com.steinlink.ponyleague.common.util;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 7, 2007
 * Time: 4:59:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class MathUtil {

    /**
      * Euclid's greatest common divisor algorithm.
      * Find the largest number that evenly divides into both n and d.
      * require: n >= 0, d >= 0.
      * fastest if n >= d.
      */
    public static long gcd(long u, long v) {
        long gcd = 1;
        long r = 0;

        while (true) {
          if (v == 0) {
            gcd = u;
            break;
          } else {
            r = u % v;
            u = v;
            v = r;
          }
        }

        return gcd;
    }

    public static long gcd(List<Long> f) {

         LinkedList<Long> factors = new LinkedList<Long>();
         for (Iterator i = f.iterator(); i.hasNext();) {
             factors.add((Long)i.next());
         }

         while (factors.size() > 1) {
             long a = factors.remove(0);
             long b = factors.remove(0);
             factors.add(gcd(a, b));
         }
         return factors.get(0);
    }

    public static long mean(List<Long> f) {

        long total = 0;
        for (Iterator i = f.iterator(); i.hasNext();) {
            total  += (Long)i.next();
        }
        return total / f.size();
    }

    public static long lcm(List<Long> f) {

        LinkedList<Long> factors = new LinkedList<Long>();
        for (Iterator i = f.iterator(); i.hasNext();) {
            factors.add((Long)i.next());
        }

        while (factors.size() > 1) {
            long a = factors.remove(0);
            long b = factors.remove(0);
            factors.add((a*b) / gcd(a,b));
        }
        return factors.get(0);

    }
}
