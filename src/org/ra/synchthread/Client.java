package org.ra.synchthread;

public class Client {
	
	public static void main(String[] args) {
		Thread currentThread = Thread.currentThread();
		System.out.println("[thread] - " + currentThread.getName() + "- start");

		Magazzino magazzino = new Magazzino();
		
		new Producer(magazzino);
		new Consumer(magazzino);
	}
}
