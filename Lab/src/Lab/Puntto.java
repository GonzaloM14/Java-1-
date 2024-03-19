package Lab;

public record Puntto(Double x, Double y)implements Comparable<Puntto> {
	/*
	 * El record nos proporciona de serie y de forma implícita:
	 * 	- Un constructor
	 * 	- Los getters, que curiosamente no empiezan por get
	 * 	- toString, hashCode y equals
	 */
	
	
	
	
	// Métodos de factoría
	public static Puntto parse(String str) {
		//Formato "(x,y)"
		String str2=str.replace("(", "").replace("(", "");
		String [] trozos=str2.split(",");
		Double x = Double.valueOf(trozos[0]);
		Double y = Double.valueOf(trozos[1]);
		return new Puntto(x,y);
	}
	
	
	
	// Propiedades derivadas
	public Cuadrante cuadrante() {
		if (this.x>0 && this.y>0) {
			return Cuadrante.PRIMER;	
		}
		else if (this.x<0 && this.y>0) {
			return Cuadrante.SEGUNDO;	
		}
		else if (this.x<0 && this.y<0) {
			return Cuadrante.TERCER;	
		}
		else if (this.x>0 && this.y<0) {
			return Cuadrante.CUARTO;	
		}
		else if (this.x.equals(0.)&& this.y.equals(0.)) {
			return Cuadrante.CENTRO;	
		}
		else {
			return Cuadrante.ENTRE_DOS;
		}
	}
	
	
	
	//Operaciones
	public Double distancia(Puntto p) {
		Double dx= this.x -p.x;
		Double dy= this.y -p.y;
		
		return Math.sqrt(dx*dx+dy*dy);
	}
	
	
	/*********** Tests ***********/
		public static void main(String[] args){
			Puntto p1 = new Puntto(0.0,-5.7);
			Puntto p2 = new Puntto(4.2,3.1);
			System.out.println(p1.distancia(p2));
			
		}



	@Override
	public int compareTo(Puntto p) {
		// por la x,y e igualdad de x,la y
		int res= this.x.compareTo(p.x);
		if(res==0) {
			res=this.y.compareTo(p.y);
		}
		return res;
	}
}
