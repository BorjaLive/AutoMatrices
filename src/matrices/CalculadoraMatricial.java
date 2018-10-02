package matrices;

import java.util.ArrayList;

public class CalculadoraMatricial {
    
    static ArrayList <Matriz> matrices = new ArrayList<>();
    
    public CalculadoraMatricial(){
        
    }
    
    public double[][] crearMatrizNeutra(int nf,int nc){
        double[][] m = new double[nf][nc];
        for(int i = 0; i < nf; i++){
            for(int j = 0; j < nc; j++){
                m[i][j] = i==j?1:0;
            }
        }
        return m;
    }
    
    public double[][] ObtenerInversa(Matriz matriz){
        if(matriz.getColumnas() != matriz.getFilas()){
            return null;
        }
        
        int N = matriz.getColumnas();
        double[][] M = obtener_copia(matriz);
        double[][] I = crearMatrizNeutra(N, N);
        
        for(int i = 0; i < N; i++){
            for(int j = 1 + i; j < N; j++){
                I = restar_gauss(M[i][i],j,M[j][i],i,N,I);
                M = restar_gauss(M[i][i],j,M[j][i],i,N,M);
            }
        }
        for(int i = N - 1; i > 0; i--){
            for(int j = i - 1; j >= 0; j--){
                I = restar_gauss(M[i][i],j,M[j][i],i,N,I);
                M = restar_gauss(M[i][i],j,M[j][i],i,N,M);
            }
        }
        for(int i = 0; i < N; i++){
            I = dividir_gauss(i,M[i][i],N,I);
            M = dividir_gauss(i,M[i][i],N,M);
        }
        
        return I;
    }
    
    private double[][] restar_gauss(double X, int F1, double Y, int F2, int Nc, double[][] matriz){
        for(int i = 0; i < Nc; i++){
            matriz[F1][i] = (matriz[F1][i] * X) - (matriz[F2][i] * Y);
        }
        return matriz;
    }   // X = multiplo de la fina 1; F1 = nº fila 1; Y = multiplo de la fila 2; F2 = nº fila 2; Nc = número de columnas de la matriz; matriz = la matriz
    
    private double[][] dividir_gauss(int j, double valor, int Nc, double[][] matriz){
        for(int i = 0; i < Nc; i++){
            matriz[j][i] /= valor;
        }
        return matriz;
    }   // j = número de la fila; valor = valor por el que se divide; Nc = número de columnas de la matriz; matriz = la matriz
    
    
    public int calcularRango(Matriz matriz){
        double[][] M = obtener_copia(matriz);
        
        for(int i = 0; i < matriz.getColumnas(); i++){
            for(int j = 1 + i; j < matriz.getFilas(); j++){
                M = restar_gauss(M[i][i],j,M[j][i],i,matriz.getColumnas(),M);
            }
        }
        
        boolean eliminable;
        double razon;
        ArrayList <Integer> eliminables = new ArrayList<>();
        
        for(int i = 0; i < matriz.getFilas(); i++){
            for(int j = 0; j < matriz.getFilas(); j++){
                eliminable = true;
                razon = -144;
                if(j != i && !eliminables.contains(j)){
                    for(int k = 0; k < matriz.getColumnas(); k++){
                        if(M[j][k] != 0){
                            if(M[i][k] == 0){
                                eliminable = false;
                            }else{
                                if(double_equal(razon,-144)){
                                    razon = M[i][k] / M[j][k];
                                }else{
                                    if(!double_equal(M[i][k] / M[j][k], razon)){
                                        eliminable = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }else{
                    eliminable = false;
                }
                if(eliminable && !eliminables.contains(j)){
                    eliminables.add(j);
                }
            }
        }
        
        return matriz.getFilas()-eliminables.size();
    }
    
    
    public double[][] sumarEscalar(Matriz matriz, double n){
        double[][] M = obtener_copia(matriz);
        
        for(int i = 0; i < matriz.getFilas(); i ++){
            for(int j = 0; j < matriz.getColumnas(); j ++){
                M[i][j] += n;
            }
        }
        
        return M;
    }
    
    public double[][] sumarMatrices(Matriz a, Matriz b){
        if(a.getFilas() != b.getFilas() || a.getColumnas() != b.getColumnas()){
            return null;
        }
        
        double[][] A = obtener_copia(a);
        double[][] B = obtener_copia(b);
        
        for(int i = 0; i < a.getFilas(); i ++){
            for(int j = 0; j < a.getColumnas(); j ++){
                A[i][j] += B[i][j];
            }
        }
        
        return A;
    }
    
    public double[][] restarEscalar(Matriz matriz, double n){
        double[][] M = obtener_copia(matriz);
        
        for(int i = 0; i < matriz.getFilas(); i ++){
            for(int j = 0; j < matriz.getColumnas(); j ++){
                M[i][j] -= n;
            }
        }
        
        return M;
    }
    
    public double[][] restarMatrices(Matriz a, Matriz b){
        if(a.getFilas() != b.getFilas() || a.getColumnas() != b.getColumnas()){
            return null;
        }
        
        double[][] A = obtener_copia(a);
        double[][] B = obtener_copia(b);
        
        for(int i = 0; i < a.getFilas(); i ++){
            for(int j = 0; j < a.getColumnas(); j ++){
                A[i][j] -= B[i][j];
            }
        }
        
        return A;
    }
    
    public double[][] dividirEscalar(Matriz matriz, double n){
        double[][] M = obtener_copia(matriz);
        
        for(int i = 0; i < matriz.getFilas(); i ++){
            for(int j = 0; j < matriz.getColumnas(); j ++){
                M[i][j] /= n;
            }
        }
        
        return M;
    }
    
    public double[][] multiplicarEscalar(Matriz matriz, double n){
        double[][] M = obtener_copia(matriz);
        
        for(int i = 0; i < matriz.getFilas(); i ++){
            for(int j = 0; j < matriz.getColumnas(); j ++){
                M[i][j] *= n;
            }
        }
        
        return M;
    }
    
    public double[][] multiplicarMatrices(Matriz a, Matriz b){
        if(a.getColumnas()!= b.getFilas()){
            return null;
        }
        
        double[][] M = new double[a.getFilas()][b.getColumnas()];
        
        double parcial;
        for(int i = 0; i < a.getFilas(); i ++){
            for(int j = 0; j < b.getColumnas(); j ++){
                parcial = 0;
                for(int k = 0; k < a.getColumnas(); k++){
                    parcial += a.getMatriz()[i][k] * b.getMatriz()[k][j];
                }
                M[i][j] = parcial;
            }
        }
        
        return M;
    }
    
    public double[][] potencia(Matriz matriz, int e){
        if(matriz.getFilas() != matriz.getColumnas()){
            return null;
        }
        
        double[][] M = obtener_copia(matriz);
        
        for(int i = 0; i < e-1; i++){
            M = multiplicarMatrices(new Matriz(M, matriz.getFilas(), matriz.getColumnas(), ""), matriz);
        }
        
        return M;
    }
    
    public double[][] extraerMenor(Matriz matriz, int f, int c){
        double[][] M = new double[matriz.getFilas()-1][matriz.getColumnas()-1];
        
        int ii = 0;
        int jj;
        for(int i = 0; i < matriz.getFilas(); i++){
            if(i != f){
                jj = 0;
                for(int j = 0; j < matriz.getColumnas(); j++){
                    if(j != c){
                        M[ii][jj] = matriz.getMatriz()[i][j];
                        jj++;
                    }
                }
                ii++;
            }
        }
        
        return M;
    }
    
    public double calcularDeterminanate(Matriz matriz){
        double[][] M = obtener_copia(matriz);
        
        if(matriz.getFilas() == 1 && matriz.getColumnas() == 1){
            return M[0][0];
        }else if(matriz.getFilas() == 2 && matriz.getColumnas() == 2){
            return (M[0][0]*M[1][1])-(M[0][1]*M[1][0]);
        }else if(matriz.getFilas() == 3 && matriz.getColumnas() == 3){
            return ((M[0][0]*M[1][1]*M[2][2])+(M[0][1]*M[1][2]*M[2][0])+(M[1][0]*M[2][1]*M[0][2]))-((M[0][2]*M[1][1]*M[2][0])+(M[0][1]*M[1][0]*M[2][2])+(M[1][2]*M[2][1]*M[0][0]));
        }else{
            double d = 0;
            for(int i = 0; i < matriz.getFilas(); i ++){
                for(int j = 0; j < matriz.getColumnas(); j ++){
                    d += M[i][j]*calcularAdjunto(matriz,i,j);
                }
            }
            return d;
        }
    }
    
    public double calcularAdjunto(Matriz matriz, int i, int j){
        double[][] M = extraerMenor(matriz,i,j);
        double d = calcularDeterminanate(new Matriz(M, matriz.getFilas()-1, matriz.getColumnas()-1, ""));
        return par(i+j)?d:d*-1;
    }
    
    public double[][] calcularMatrizAdjunta(Matriz matriz){
        double[][] resultado = new double[matriz.getFilas()][matriz.getColumnas()];
        
        for(int i = 0; i < matriz.getFilas(); i++){
            for(int j = 0; j < matriz.getColumnas(); j++){
                resultado[i][j] = (par(i+j)?1:-1) * calcularDeterminanate(new Matriz(extraerMenor(matriz, i, j), matriz.getFilas()-1, matriz.getColumnas()-1, ""));
                //resultado[i][j] = calcularDeterminanate(new Matriz(extraerMenor(matriz, i, j), matriz.getFilas()-1, matriz.getColumnas()-1, ""));
            }
        }
        
        return resultado;
    }
    
    private boolean par(int n){
        String s = String.valueOf(n);
        char c = s.charAt(s.length()-1);
        return c == '0' || c == '2' || c == '4' || c == '6' || c == '8';
    }
    
    
    public double[][] obtenerTranspuesta(Matriz matriz){
        double[][] M = new double[matriz.getFilas()][matriz.getColumnas()];
        
        for(int i = 0; i < matriz.getFilas(); i++){
            for(int j = 0; j < matriz.getColumnas(); j++){
                M[j][i] = matriz.getMatriz()[i][j];
            }
        }
        
        return M;
    }
    
    public int agregarMatriz(double[][] matriz, int Nf, int Nc, String nombre){
        matrices.add(new Matriz(matriz, Nf, Nc, nombre));
        return matrices.size()-1;
    }
    
    public void eliminarMatriz(int n){
        matrices.remove(n);
    }
    
    public int getNumeroMatrices(){
        return matrices.size();
    }
    
    public String getNombreMatriz(int n){
        return matrices.get(n).toString();
    }
    
   public Matriz getMatriz(int n){
       return matrices.get(n);
   } //Evitar usar esta función
    
    private String simplificar(double n){
        if(n-(int)n == 0){
            return Integer.toString((int)n);
        }
        
        if(n == Math.PI){
            return "π";
        }else if(n == Math.E){
            return "e";
        }
        
        double original = n;
        int b = 1;
        while (true){
           n *= b;
           if(n-(int)n == 0){
               break;
           }else{
               n = original;
               b++;
           }
           if(Double.isInfinite(n)){
               return String.valueOf(original);
           }
        }
        
        return (int)n+"/"+b;
    }
    
    private boolean double_equal(double d1, double d2){
        return d1 < d2+0.0001 && d1 > d2-0.0001;
    }
    
    
    public void imprimirMatriz(int n){
        Matriz matriz = matrices.get(n);
        for(int i = 0; i < matriz.getFilas(); i++){
            System.out.print("| ");
            for(int j = 0; j < matriz.getColumnas(); j++){
                System.out.print(simplificar(matriz.getMatriz()[i][j])+" ");
            }
            System.out.println(" |");
        }
    }
    public void imprimirMatriz(double[][] matriz, int Nf, int Nc){
        for(int i = 0; i < Nf; i++){
            System.out.print("| ");
            for(int j = 0; j < Nc; j++){
                System.out.print(simplificar(matriz[i][j])+" ");
            }
            System.out.println(" |");
        }
    }
    public void imprimirMatriz(Matriz matriz){
        for(int i = 0; i < matriz.getFilas(); i++){
            System.out.print("| ");
            for(int j = 0; j < matriz.getColumnas(); j++){
                System.out.print(simplificar(matriz.getMatriz()[i][j])+" ");
            }
            System.out.println(" |");
        }
    }
    
    public double[][] obtener_copia(Matriz m){
        double[][] nueva = new double[m.getFilas()][m.getColumnas()];
        for(int i = 0; i < m.getFilas(); i++){
            for(int j = 0; j < m.getColumnas(); j++){
                nueva[i][j] = m.getMatriz()[i][j];
            }
        }
        return nueva;
    }
}