package lesson151008;

import java.util.Random;

public class HomeWork {

	public static void main(String[] args) {
		
		System.out.println("start");
		long start = System.nanoTime();
		double[][] matrix = generate();
		System.out.println("generated");
		long stop = System.nanoTime();
		System.out.println("Elapsed = " + (stop - start));

		start = System.nanoTime();
		process(matrix);
		stop = System.nanoTime();
		System.out.println("Elapsed = " + (stop - start));
		
	}

	private static void process(double[][] matrix) {
		
		Thread[] threads = new Thread[matrix.length];
		
		for (int i = 0; i < matrix.length; i++) {
			final double[] row = matrix[i];
			threads[i] = new Thread(() -> processRow(row));
			threads[i].start();
		}
		
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void processRow(double[] ds) {
		for (int i = 0; i < ds.length; i++) {
			Math.pow(ds[i], ds[i]);
		}
	}

	private static double[][] generate() {
		
		double[][] matrix = new double[10][50_000_000];
		Thread[] threads = new Thread[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			final int k = i;
			threads[k] = new Thread(() -> {
				Random random = new Random();
				for (int j = 0; j < matrix[k].length; j++) {
					matrix[k][j] = random.nextDouble();
				}
			});
			threads[k].start();
		}

		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return matrix;
	}
	
	
}
