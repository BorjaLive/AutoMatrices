package matrices;

public class Matriz {
    
    private double[][] M;
    private int Nf,Nc;
    private final String nombre;
    
    public Matriz(double[][] M, int Nf,int Nc,String nombre){
        this.M = M;
        this.Nf = Nf;
        this.Nc = Nc;
        this.nombre = nombre;
    }
    
    public int getFilas(){
        return Nf;
    }
    public int getColumnas(){
        return Nc;
    }
    public double[][] getMatriz(){
        return M;
    }
    public void setFilas(int Nf){
        this.Nf = Nf;
    }
    public void setColumnas(int Nc){
        this.Nc = Nc;
    }
    public void setMatriz(double[][] M){
        this.M = M;
    }
    
    @Override
    public String toString(){
        return nombre+" "+Nf+"x"+Nc;
    }
}
