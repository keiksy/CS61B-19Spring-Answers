package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int T;
    private double[] percoRates;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N<=0 || T<=0) throw new IllegalArgumentException();
        this.T = T;
        percoRates = new double[T];

        for(int i = 0; i < T; i++) {
            Percolation percolation = pf.make(N);
            while(true) {
                int r, l;
                do {
                    r = StdRandom.uniform(0, N);
                    l = StdRandom.uniform(0, N);
                } while(!percolation.isOpen(r, l));
                percolation.open(r, l);
                if (percolation.percolates()) {
                    percoRates[i] = (double)percolation.numberOfOpenSites() / (N*N);
                    break;
                }
            }
        }
    }

    public double mean() {
        return StdStats.mean(percoRates);
    }

    public double stddev() {
        return StdStats.stddev(percoRates);
    }

    public double confidenceLow() {
        return mean() - (1.96*Math.sqrt(stddev())/Math.sqrt(T));
    }

    public double confidenceHigh() {
        return mean() + (1.96*Math.sqrt(stddev())/Math.sqrt(T));
    }
}
