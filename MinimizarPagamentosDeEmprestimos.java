import java.util.*;

public class MinimizarPagamentosDeEmprestimos {

    public static void main(String[] args) {
        double[] taxasDeJuros = { 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2.0 };// Exemplo de taxas de juros
        calcularOrdemPagamento(taxasDeJuros);
        
    }

    public static void calcularOrdemPagamento(double[] taxasDeJuros) {
        int n = taxasDeJuros.length;
        double matrix[][] = new double[n][n];
        double[] ordemDePagamento = new double[n];
        double total = 0;
        //cria uma lista de inteiros para armazenar os indices disponiveis
        List<Integer> disponiveis = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            disponiveis.add(i);
        }

        for (int j = 0; j < n; j++) {
            //atribuições para garantir que o loop ira passar por toda a matriz
            int melhorIndice = -1;
            double maiorValor = -1;

            for (int i : disponiveis) {
                // Percorre a Matriz de Pagamentos e preenche com os valores com juros
                matrix[i][j] = Math.pow(taxasDeJuros[i], j + 1) * 1000;
                // Verifica se o valor atual é o maior da coluna, se for salva o indice e o valor
                if (matrix[i][j] > maiorValor) {
                    melhorIndice = i;
                    maiorValor = matrix[i][j];
                }
            }
            //Adiciona o maior valor da coluna na ordem de pagamento e remove a linha do indice da lista de disponiveis
            total+=maiorValor;
            ordemDePagamento[j] = taxasDeJuros[melhorIndice];
            disponiveis.remove((Integer) melhorIndice);
        }

        toString(matrix, ordemDePagamento, total);




    }

    public static void toString(double[][] matrix, double[] ordemDePagamento, double total) {
        // Imprime a matriz
        for (double[] ds : matrix) {
            for (double d : ds) {
                System.out.printf("%.2f", d);
                System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println("Ordem de pagamento ótima:");
        int i = 1;
        
        for (double d : ordemDePagamento) {
            System.out.println(i + " - Pagar o empréstimo do Banco com taxas de: " + d);
            total+=d;
            i++;
        }
        System.out.printf("O pagamento minimo é de: " +"%.2f\n", total);
        
    }
}
