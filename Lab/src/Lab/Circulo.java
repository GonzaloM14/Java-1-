package Lab;

public record Circulo(Double radio) {
	

	
	public Double area(Circulo r) {
		Double dA= (this.radio*this.radio*Math.PI);
		return dA;
	}
	
	public Double perimetro(Circulo r) {
		Double dA= (this.radio*2*Math.PI);
		return dA;	
	}
	public static void main(String[] args){
		Circulo r = new Circulo(4.);
		
		System.out.println(r.area(r));
		System.out.println(r.perimetro(r));
		
		
	
	}
}
