package org.ra.synchthread;

public class Consumer implements Runnable {
	
	private static final int N_PRODOTTI = 5;

	private Magazzino magazzino;
	private Thread t;

	public Consumer(Magazzino magazzino) {
		super();
		this.magazzino = magazzino;
		this.t = new Thread(this, "Consumer");
		t.start();
		
		System.out.println("[thread] - " + t.getName() + "- start");
	}

	public Magazzino getMagazzino() {
		return magazzino;
	}

	@Override
	public synchronized void run() {
		
		for (int i = 0; i < N_PRODOTTI; i++) {
			String prodotto = this.magazzino.get();
			System.out.println("[thread] - " + t.getName() + " consumato: " + prodotto);
		}
	}
}
