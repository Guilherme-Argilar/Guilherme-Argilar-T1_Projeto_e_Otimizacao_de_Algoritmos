import java.util.*;

public class MinimizarPagamentosDeEmprestimos {

    public static void main(String[] args) {
        double[] taxasDeJuros = { 1.1, 1.5, 1.25, 1.05 }; // Exemplo de taxas de juros
        double[] ordemDePagamento = calcularOrdemPagamento(taxasDeJuros);
        System.out.println("Ordem de pagamento ótima:");
        int i = 1;
        for (double d : ordemDePagamento) {
            System.out.println(i+" - Pagar o emprestimo do Banco com taxas de: "+d);
            i++;
        }
    }

    public static double[] calcularOrdemPagamento(double[] taxasDeJuros) {
        int n = taxasDeJuros.length;
        double matrix[][] = new double[n][n];
        List<Integer> disponiveis = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            disponiveis.add(i);
        }
        double[] ordemDePagamento = new double[n];

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                // Percorre a Matriz de Pagamentos e preenche com os valores com juros
                matrix[i][j] = Math.pow(taxasDeJuros[i], j + 1) * 1000;
            }

        }


        printMatrix(matrix);
        

        
        for (int j = 0; j < n; j++) {
            //atribuições para garantir que o loop ira passar por toda a matriz
            int melhorIndice = -1;
            double maiorValor = Double.MIN_VALUE;
            for (int i : disponiveis) {
                if (matrix[i][j] > maiorValor) {
                    melhorIndice = i;
                    maiorValor = matrix[i][j];
                }
            }
            //Adiciona o maior valor da coluna na ordem de pagamento e remove o indice da lista de disponiveis
            ordemDePagamento[j] = taxasDeJuros[melhorIndice];
            disponiveis.remove((Integer) melhorIndice);
        }

        return ordemDePagamento;
    }

    public static void printMatrix(double[][] matrix) {
        // Imprime a matriz
        for (double[] ds : matrix) {
            for (double d : ds) {
                System.out.printf("%.2f",d);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
