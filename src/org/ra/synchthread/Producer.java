package org.ra.synchthread;

public class Producer implements Runnable {

	private static final int N_PRODOTTI = 5;
	
	private Magazzino magazzino;
	private Thread t;

	public Producer(Magazzino magazzino) {
		super();
		this.magazzino = magazzino;
		this.t = new Thread(this, "Producer");
		t.start();

		System.out.println("[thread] - " + t.getName() + "- start");
	}

	public Magazzino getMagazzino() {
		return magazzino;
	}

	@Override
	public void run() {
		for (int i = 0; i < N_PRODOTTI; i++) {
			String prodotto = this.magazzino.put();
			System.out.println("[thread] - " + t.getName() + " creato: " + prodotto);
		}
	}

}
