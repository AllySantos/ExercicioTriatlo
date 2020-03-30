package controller;

public class Jogador implements Comparable<Jogador>{

	private int id;
	private int pontos;
	
	public Jogador(int id, int pontos) {
		// TODO Auto-generated constructor stub
		
		setId(id);
		setPontos(pontos);
	}

	@Override
	public int compareTo(Jogador o) {

		if(this.pontos > o.pontos) {
			return -1;
		}else if(this.pontos < o.pontos) {
			return +1;
		}
		return 0;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	
}
