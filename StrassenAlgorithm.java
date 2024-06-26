public class StrassenAlgorithm {

    public static void main(String[] args) {

        int[][] A = { { 1, 2, 3 }, {1, 2, 3}, {0, 0, 2 } };
        int[][] B = { { 1, 0, 0}, { 0, 1, 0 }, { 0, 0, 1 } };

        int maxSize = Math.max(Math.max(A.length, B.length), Math.max(A[0].length, B[0].length)); // pega o maior lado entre as matrizes
        if (maxSize % 2 != 0) { //caso o maior lado seja um valor impar, adiciona 1 para tornar par
            maxSize++;
        }
        int[][] paddedA = normalizaMatriz(A, maxSize); //normaliza as matrizes para o maior tamanho
        int[][] paddedB = normalizaMatriz(B, maxSize);

        
        int[][] C = strassen(paddedA, paddedB);

        
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] strassen(int[][] A, int[][] B) {
        int n = A.length;

        // Caso base: Se a matriz for 1x1, apenas faça a multiplicação simples.
        if (n == 1) {
            int[][] C = new int[1][1];
            C[0][0] = A[0][0] * B[0][0];
            return C;
        } else {

            // Divide as matrizes em quatro submatrizes
            
            int [][][] submatrizesA = divideMatriz(A); //divide a matriz A em 4 submatrizes
            int [][][] submatrizesB = divideMatriz(B); //divide a matriz B em 4 submatrizes

            int[][] A11 = submatrizesA[0];
            int[][] A12 = submatrizesA[1];
            int[][] A21 = submatrizesA[2];
            int[][] A22 = submatrizesA[3];

            int[][] B11 = submatrizesB[0];
            int[][] B12 = submatrizesB[1];
            int[][] B21 = submatrizesB[2];
            int[][] B22 = submatrizesB[3];

            // Calcula sete produtos recursivamente
            int[][] P1 = strassen(A11, subtrairMatriz(B12, B22));
            int[][] P2 = strassen(somarMatriz(A11, A12), B22);
            int[][] P3 = strassen(somarMatriz(A21, A22), B11);
            int[][] P4 = strassen(A22, subtrairMatriz(B21, B11));
            int[][] P5 = strassen(somarMatriz(A11, A22), somarMatriz(B11, B22));
            int[][] P6 = strassen(subtrairMatriz(A12, A22), somarMatriz(B21, B22));
            int[][] P7 = strassen(subtrairMatriz(A11, A21), somarMatriz(B11, B12));

            // Calcula os quatro blocos da matriz resultante
            int[][] C11 = somarMatriz(subtrairMatriz(somarMatriz(P5, P4), P2), P6);
            int[][] C12 = somarMatriz(P1, P2);
            int[][] C21 = somarMatriz(P3, P4);
            int[][] C22 = subtrairMatriz(subtrairMatriz(somarMatriz(P5, P1), P3), P7);
            
            int newSize = n / 2;
            int[][] C = new int[n][n];
            // Combina os quatro blocos para formar a matriz resultante
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    C[i][j] = C11[i][j]; //C11
                    C[i][j + newSize] = C12[i][j]; //C12
                    C[i + newSize][j] = C21[i][j]; //C21
                    C[i + newSize][j + newSize] = C22[i][j]; //C22
                }
            }

            return C;
        }
    }

    public static int[][][] divideMatriz(int[][] matriz) {
        int n = matriz.length; // como a matriz foi normalizada antes desta função ser chamada, n é sempre par
        int newSize = n / 2;
        int[][][] submatrizes = new int[4][newSize][newSize];
    
        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                submatrizes[0][i][j] = matriz[i][j];  // A11
                submatrizes[1][i][j] = matriz[i][j + newSize];  // A12
                submatrizes[2][i][j] = matriz[i + newSize][j];  // A21
                submatrizes[3][i][j] = matriz[i + newSize][j + newSize];  // A22
            }
        }
    
        return submatrizes;
    }
  
    public static int[][] somarMatriz(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    public static int[][] subtrairMatriz(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    // retorna a matriz normalizada para o tamanho size
    public static int[][] normalizaMatriz(int[][] matriz, int n) {
        int[][] paddedMatriz = new int[n][n];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                // atribui o valor da matriz original para a paddedMatriz, o restante da matriz sera 0
                paddedMatriz[i][j] = matriz[i][j]; 
            }
        }
        return paddedMatriz;
    }
}
