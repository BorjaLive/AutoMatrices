package matrices;

import java.util.Scanner;

public class Matrices {

    static Scanner scaner = new Scanner(System.in);
    static CalculadoraMatricial matrices = new CalculadoraMatricial();
    
    public static void main(String[] args) {
        System.out.println("-----------| Matrices |-----------");
        
        //matrices.agregarMatriz(new double[][] {{1,2,3,2},{-1,1,2,-1},{0,3,1,2},{2,1,0,1}},4,4,"Defecto");
        //matrices.agregarMatriz(new double[][] {{1,0,2},{0,1,1},{1,0,1}}, 3, 3, "A");
        //matrices.agregarMatriz(new double[][] {{1,0,2},{1,0,1},{1,1,1}}, 3, 3, "B");
        //matrices.agregarMatriz(new double[][] {{2,1,-1},{1,0,2},{-1,1,1}}, 3, 3, "A");
        matrices.agregarMatriz(new double[][] {{1,0,2},{-1,3,1},{0,1,2}}, 3, 3, "A");
        
        menu();
    }
    
    public static void menu(){
        System.out.println("\nOpciones:\n"
                + "1. Introducir matriz\n"
                + "2. Eliminar una matriz\n"
		+ "3. Generar elemento neutro\n"
                + "4. Dibujar una matriz\n"
                + "\n"
                + "5. Sumar matrices\n"
                + "6. Restar matrices\n"
                + "7. Multiplicar matrices\n"
                + "8. Dividir matrices\n"
                + "9. Hacer potencia de una matriz\n"
                + "\n"
                + "10. Calcular matriz inversa\n"
                + "\n"
                + "11. Calcular el rango\n"
                + "12. Calcular el determinate\n"
                + "\n"
                + "13. Hallar una menor\n"
                + "14. Hallar la matriz transpuesta\n"
                + "15. Hallar el adjunto de una matriz\n"
                + "0. Salir");
        
        switch(scaner.nextInt()){
            case 0:
                System.exit(0);
                break;
            case 1:
                introducir();
                break;
            case 2:
                eliminar();
                break;
            case 3:
                neutro();
                break;
            case 4:
                dibujar();
                break;
            case 5:
                sumar();
                break;
            case 6:
                restar();
                break;
            case 7:
                multiplicar();
                break;
            case 8:
                dividir();
                break;
            case 9:
                potencia();
                break;
            case 10:
                inversa();
                break;
            case 11:
                rango();
                break;
            case 12:
                determinante();
                break;
            case 13:
                menor();
                break;
            case 14:
                transpuesta();
                break;
            case 15:
                adjunto();
                break;
        }
        menu();
    }
    
    public static void introducir(){
        System.out.print("\nIntroduce el numero de filas: ");
        int nf = scaner.nextInt();
        System.out.print("Introduce el numero de columnas: ");
        int nc = scaner.nextInt();
        double[][] m = new double[nf][nc];
        for(int i = 0; i < nf; i++){
            for(int j = 0; j < nc; j++){
                m[i][j] = -144;
            }
        }
        boolean terminado;
        int x,y;
        while(true){
            terminado = true;
            x = -1;
            y = -1;
            for(int i = 0; i < nf; i++){
                for(int j = 0; j < nc; j++){
                    if((int)m[i][j] == -144){
                        terminado = false;
                        if(x == -1){
                            x = i;
                            y = j;
                            System.out.print("X");
                        }else{
                            System.out.print("-");
                        }
                    }else{
                        System.out.print(Double.toString(m[i][j]));
                    }
                    System.out.print(" ");
                }
                System.out.print("\n");
            }
            if(terminado){
                break;
            }
            System.out.print("Introduce el valor para la X: ");
            m[x][y] = scaner.nextDouble();
        }
        System.out.print("Introduce el nombre que quieres darle a la matriz: ");
        
        System.out.println("Matriz introducida correctamente ("+(matrices.agregarMatriz(m,nf,nc,scaner.next())+1)+")");
    }
    
    public static void eliminar(){
        System.out.println("Seleciona la matriz que quieres dibujar.");
        matrices.eliminarMatriz(seleccionar());
        System.out.println();
    }
    
    public static void neutro(){
        System.out.println("Introduce el numero de filas: ");
        int f = scaner.nextInt();
        System.out.println("Introduce el numero de columnas: ");
        double[][] resultado = matrices.crearMatrizNeutra(f,scaner.nextInt());
        
        mostrarResultado(resultado);
    }
    
    public static void dibujar(){
        System.out.println("Seleciona la matriz que quieres dibujar.");
        matrices.imprimirMatriz(seleccionar());
        System.out.println();
    }
    
    public static void sumar(){
        System.out.println("Seleciona que quieres sumar\n"
                + "1. Matriz + escalar\n"
                + "2. Matriz + Matriz");
        Matriz m;
        double[][] resultado;
        switch(scaner.nextInt()){
            case 1:
                System.out.println("\nSeleciona la matriz que quieres usar.");
                m = matrices.getMatriz(seleccionar());
                System.out.print("Introduce el numero que le quieres sumar: ");
                resultado = matrices.sumarEscalar(m, scaner.nextInt());
                break;
            case 2:
                System.out.println("\nSeleciona la matriz que quieres usar.");
                m = matrices.getMatriz(seleccionar());
                System.out.println("Seleciona la mariz que le quieres sumar: ");
                resultado = matrices.sumarMatrices(m, matrices.getMatriz(seleccionar()));
                break;
            default:
                return;
        }
        mostrarResultado(resultado);
    }
    
    public static void restar(){
        System.out.println("Seleciona que quieres restar\n"
                + "1. Matriz - escalar\n"
                + "2. Matriz - Matriz");
        Matriz m;
        double[][] resultado;
        switch(scaner.nextInt()){
            case 1:
                System.out.println("\nSeleciona la matriz que quieres usar.");
                m = matrices.getMatriz(seleccionar());
                System.out.print("Introduce el numero que le quieres restar: ");
                resultado = matrices.restarEscalar(m, scaner.nextInt());
                System.out.println("Operacion calculada con exito.");
                break;
            case 2:
                System.out.println("\nSeleciona la matriz que quieres usar.");
                m = matrices.getMatriz(seleccionar());
                System.out.println("Seleciona la mariz que le quieres restar: ");
                resultado = matrices.restarMatrices(m, matrices.getMatriz(seleccionar()));
                System.out.println("Operacion calculada con exito.");
                break;
            default:
                return;
        }
        mostrarResultado(resultado);
    }
    
    public static void multiplicar(){
        System.out.println("Seleciona que quieres multiplicar\n"
                + "1. Matriz * escalar\n"
                + "2. Matriz * Matriz");
        Matriz m;
        double[][] resultado;
        switch(scaner.nextInt()){
            case 1:
                System.out.println("\nSeleciona la matriz que quieres usar.");
                m = matrices.getMatriz(seleccionar());
                System.out.print("Introduce el numero que le quieres multiplicar: ");
                resultado = matrices.multiplicarEscalar(m, scaner.nextInt());
                System.out.println("Operacion calculada con exito.");
                break;
            case 2:
                System.out.println("\nSeleciona la matriz que quieres usar.");
                m = matrices.getMatriz(seleccionar());
                System.out.println("Seleciona la mariz que le quieres muliplicar: ");
                resultado = matrices.multiplicarMatrices(m, matrices.getMatriz(seleccionar()));
                System.out.println("Operacion calculada con exito.");
                break;
            default:
                return;
        }
        mostrarResultado(resultado);
    }
    
    public static void dividir(){
        System.out.println("Seleciona que quieres dividir\n"
                + "1. Matriz / escalar\n"
                + "2. Matriz / Matriz");
        Matriz m;
        double[][] resultado;
        switch(scaner.nextInt()){
            case 1:
                System.out.println("\nSeleciona la matriz que quieres usar.");
                m = matrices.getMatriz(seleccionar());
                System.out.print("Introduce el numero que le quieres dividir: ");
                resultado = matrices.dividirEscalar(m, scaner.nextInt());
                System.out.println("Operacion calculada con exito.");
                break;
            case 2:
                System.out.println("Operacion no soportada\n");
                return;
            default:
                return;
        }
        mostrarResultado(resultado);
    }
    
    public static void potencia(){
        System.out.println("\nSeleciona la matriz que quieres usar.");
        Matriz m = matrices.getMatriz(seleccionar());
        System.out.print("Introduceel exponenda al que la quieres elevar: ");
        double[][] resultado = matrices.potencia(m, scaner.nextInt());
        System.out.println("Operacion calculada con exito.");
        mostrarResultado(resultado);
    }
    
    public static void inversa(){
        System.out.println("Seleciona la matriz que quieres usar.");
        double[][] resultado = matrices.ObtenerInversa(matrices.getMatriz(seleccionar()));
        
        mostrarResultado(resultado);
    }
    
    public static void rango(){
        System.out.println("Seleciona la matriz que quieres usar.");
        System.out.println("El rango es de: "+matrices.calcularRango(matrices.getMatriz(seleccionar()))+"\n");
    }
    
    public static void menor(){
        System.out.println("\nSeleciona la matriz que quieres usar.");
        Matriz m = matrices.getMatriz(seleccionar());
        System.out.print("Introduceel la fila a eliminar: ");
        int f = scaner.nextInt()-1;
        System.out.print("Introduceel la columna a eliminar: ");
        int c = scaner.nextInt()-1;
        double[][] resultado = matrices.extraerMenor(m, f, c);
        
        mostrarResultado(resultado);
    }
    
    public static void determinante(){
        System.out.println("Seleciona la matriz que quieres usar.");
        System.out.println("El determinante es: "+matrices.calcularDeterminanate(matrices.getMatriz(seleccionar()))+"\n");
    }
    
    public static void transpuesta(){
        System.out.println("Seleciona la matriz que quieres usar.");
        double[][] resultado = matrices.obtenerTranspuesta(matrices.getMatriz(seleccionar()));
        
        mostrarResultado(resultado);
    }
    
    public static void adjunto(){
        System.out.println("Seleciona la matriz que quieres usar.");
        double[][] resultado = matrices.calcularMatrizAdjunta(matrices.getMatriz(seleccionar()));
        
        mostrarResultado(resultado);
    }
    
    
    public static int seleccionar(){
        for(int i = 0; i < matrices.getNumeroMatrices(); i++){
            System.out.println((i+1)+". "+matrices.getNombreMatriz(i));
        }
        System.out.print("Introduce el numero de la matriz: ");
        int n = scaner.nextInt();
        if(n <= matrices.getNumeroMatrices()){
            return n-1;
        }else{
            System.out.println("Error en la seleccion, vuelve a introducir un valor dentro de los permitidos");
            return seleccionar();
        }
    }
    
    public static void mostrarResultado(double[][] resultado){
        if(resultado == null){
            System.out.println("La operación no se pudo completar");
        }else{
            System.out.println("Operación completada con exito");
            boolean c = true;
            while(c){
                System.out.println("\nSeleciona que quieres hacer con el resultado:\n"
                        + "1. Mostrar resultado\n"
                        + "2. Guardar resultado\n"
                        + "3. Volver al menu");
                switch(scaner.nextInt()){
                    case 1:
                        matrices.imprimirMatriz(resultado, resultado.length, resultado[0].length);
                        System.out.println();
                        break;
                    case 2:
                        System.out.print("Introduce el nombre que quieres darle a la matriz: ");
                        System.out.println("Matriz introducida correctamente ("+(matrices.agregarMatriz(resultado, resultado.length, resultado[0].length, scaner.next())+1)+")");
                        break;
                    case 3:
                        c = false;
                        break;
                }
            }
        }
    }
}
