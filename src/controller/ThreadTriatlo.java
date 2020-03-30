package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Semaphore;

public class ThreadTriatlo extends Thread{

	Semaphore semaTiro;
	
	Semaphore semaRanking;

	ArrayList<Jogador> ranking = new ArrayList<Jogador>();
	
	
	int pontosTiros;
	
	public static int pontos = 250;
	public static int competidores;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		corrida();
		//mostraRanking();
		super.run();
	}
	public ThreadTriatlo(Semaphore s, Semaphore sRanking, ArrayList<Jogador> ranking) {
		// TODO Auto-generated constructor stub
	
		this.semaTiro = s;
		this.semaRanking = sRanking;
		this.ranking = ranking;
	}
	
	public void corrida() {
		
		
		int velocidade = (int) ((Math.random() * 16) + 20); //De 20 a 25 ms
		
		int distanciaPercorrida = 0;
		
		System.out.println("CORRIDA | Jogador " + getId() + " correndo " + velocidade + "m/s");

		while(distanciaPercorrida < 3000) {
			distanciaPercorrida += velocidade;
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Joga pra proxima prova
		try {
			semaTiro.acquire();
			tiroAlvo();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tiroAlvo() {

		double tempoTiro = ((Math.random() * 2.6) + 0.5); // De 0.5 até 3s 
		
		double miliTempo = tempoTiro * 1000;
		
		int sleepTempoTiro = (int) miliTempo;
		
		System.out.println("TIRO AO ALVO | Jogador " + getId() + " atirando em " + tempoTiro + "s por tiro");

		try {
			sleep(sleepTempoTiro * 3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Pontuação dos tiros
		pontosTiros = (int) (Math.random() * 10) + 1; //Tiro 1
		pontosTiros += (int) (Math.random() * 10) + 1;   //Tiro 2
		pontosTiros += (int) (Math.random() * 10) + 1;   //Tiro 3

		
		System.out.println("Jogador " + getId() + " com " + pontosTiros + " pontos de tiro");

		//Próxima Prova
				try {
					semaTiro.release();
					ciclismo();
				} catch (Exception e) {
					// TODO: handle exception
				}finally {
					semaTiro.release();
				}

		
		
	}
	
	public void ciclismo() {
		

		int velocidade = (int) ((Math.random() * 11) + 30); //De 30 a 40 ms
		
		int distanciaPercorrida = 0;
		 
		System.out.println("CICLISMO | Jogador " + getId() + " pedalando em " + velocidade + " m/s");

		while(distanciaPercorrida < 5000) {
			distanciaPercorrida += velocidade;
			
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			semaRanking.acquire();
			addRanking();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			semaRanking.release();
		}
		
		
		
		
	}
	
	public void addRanking() {
		System.out.println("PONTUAÇÃO | Jogador " + getId() + " ficou com " + pontos + " + " + pontosTiros + " pontos de tiro");

		Jogador j = new Jogador((int) getId(), (pontos + pontosTiros));
		ranking.add(j);
		
		
		pontos -= 10;
		competidores++;
		
		if(competidores == 25) {
			mostraRanking();
		}
		//System.out.println("JOGADOR N° " + getId() + " CHEGOU EM " + posicao +"° || PRÊMIO");
	
	
		
	}
	
	public void mostraRanking() {
		System.out.println("RESULTADO FINAL");
		Collections.sort(ranking);
		int pos = 1;
		
		for (Jogador jogador : ranking) {
			System.out.println(pos + "° Jogador n° " + jogador.getId() + " com " + jogador.getPontos() + " pontos");
		
			pos++;
		}
	}
	
}
