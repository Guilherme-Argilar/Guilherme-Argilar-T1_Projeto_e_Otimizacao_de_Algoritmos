
public class StrassenAlgorithm {

    public static void main(String[] args) {
        int[][] A = { { 12, 34 }, { 22, 10 } };
        int[][] B = { { 3, 4 }, { 2, 1 } };
        int[][] C = strassen(A, B);
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C.length; j++) {
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
            int newSize = n / 2;
            int[][] A11 = new int[newSize][newSize];
            int[][] A12 = new int[newSize][newSize];
            int[][] A21 = new int[newSize][newSize];
            int[][] A22 = new int[newSize][newSize];

            int[][] B11 = new int[newSize][newSize];
            int[][] B12 = new int[newSize][newSize];
            int[][] B21 = new int[newSize][newSize];
            int[][] B22 = new int[newSize][newSize];

            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    A11[i][j] = A[i][j];
                    A12[i][j] = A[i][j + newSize];
                    A21[i][j] = A[i + newSize][j];
                    A22[i][j] = A[i + newSize][j + newSize];

                    B11[i][j] = B[i][j];
                    B12[i][j] = B[i][j + newSize];
                    B21[i][j] = B[i + newSize][j];
                    B22[i][j] = B[i + newSize][j + newSize];
                }
            }

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

            // Combina os quatro blocos para formar a matriz resultante
            int[][] C = new int[n][n];
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    C[i][j] = C11[i][j];
                    C[i][j + newSize] = C12[i][j];
                    C[i + newSize][j] = C21[i][j];
                    C[i + newSize][j + newSize] = C22[i][j];
                }
            }

            return C;
        }
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

}
