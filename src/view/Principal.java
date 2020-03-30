package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Semaphore;

import controller.Jogador;
import controller.ThreadTriatlo;

public class Principal {

	public static void main(String[] args) {
		ArrayList<Jogador> ranking = new ArrayList<Jogador>();

		Semaphore s = new Semaphore(3);
		Semaphore sRanking = new Semaphore(1);
		
		for(int i = 0; i<25; i++) {
			ThreadTriatlo tt = new ThreadTriatlo(s, sRanking, ranking);
			tt.start();
			
			
		}
		
		
		

		
	}
	
}
