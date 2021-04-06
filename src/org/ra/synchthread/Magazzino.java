package org.ra.synchthread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Magazzino {
	private List<String> prodotti = new ArrayList<>();
	
	public Magazzino() {
		super();
	}
	
	public int getNumProdotti() {
		return this.prodotti.size();
	}

	public boolean isVuoto() {
		return this.prodotti.isEmpty();
	}

	public synchronized String put() {
		// se il magazzino non è vuoto, allora aspetta che il consumer lo svuoti
		if(!this.isVuoto()) {
			try {
				// mi metto in pausa aspettando che il consumer mi notifichi lo svuotamento del magazzino
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		String prodotto = creaProdottofittizio();
		this.prodotti.add(prodotto);
		this.notify();
		
		return prodotto;
	}

	private String creaProdottofittizio() {
		Random r = new Random();
		int randomInt = r.nextInt(12456300);
		
		String prodotto = "a" + randomInt + "x";
		return prodotto;
	}
	
	public synchronized String get() {
		// se il magazzino è vuoto, allora aspetto che il producer me lo riempia
		if(this.isVuoto()) {
			try {
				// mi metto in pausa aspettando che il producer mi notifichi che il magazzino non è più vuoto
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		String result = this.prodotti.remove(this.prodotti.size()-1);
		this.notify();
		
		return result;
	}
}