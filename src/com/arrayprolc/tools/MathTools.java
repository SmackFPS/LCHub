package com.arrayprolc.tools;

import java.util.Random;

public class MathTools {
	
	static Random rand = new Random();
	
	public static int getClosestTo(int j, int rejex){
		int working = rejex; for(int i = 0; i < 9*7; i++) if(j > working) working = working + rejex; return working;
	}
	
	public static long getClosestTo(long j, long rejex){
		long working = rejex; for(long i = 0; i < 9*7; i++) if(j > working) working = working + rejex; return working;
	}
	
	public static double getClosestTo(double j, double rejex){
		double working = rejex; for(double i = 0; i < 9*7; i++) if(j > working) working = working + rejex; return working;
	}
	
	public static int round(double input){
		return (int) Math.round(input);
	}
	
	public static long roundLong(double input){
		return Math.round(input);
	}
	
	public static int randInt(int min, int max) {
	    return rand.nextInt((max - min) + 1) + min;
	}
	
	public static double getAverage(double[] values){
		double w = 0; for(double d : values) w = w + d; return w / values.length;
	}
	
	public static double addAll(double[] values){
		double w = 0; for(double d : values) w = w + d; return w;
	}
	
	public static int addAll(int[] values){
		int w = 0; for(int d : values) w = w + d; return w;
	}
	
	public static long addAll(long[] values){
		long w = 0; for(long d : values) w = w + d; return w;
	}
}
