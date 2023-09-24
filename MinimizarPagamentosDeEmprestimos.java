import java.util.Arrays;

public class MinimizarPagamentosDeEmprestimos {

    public static void minimizarPagamentos(double[] taxasDeJuros) {
        int n = taxasDeJuros.length;
        Arrays.sort(taxasDeJuros);

        double[][] dp = new double[n][n];

        // Preenche a matriz DP
        // i = emprestimos, j = meses
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == 0) {
                    dp[i][j] = 1000 * Math.pow(taxasDeJuros[i], j + 1);
                } else {
                    dp[i][j] = dp[i - 1][j] + 1000 * Math.pow(taxasDeJuros[i], j + 1);
                    if (j > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1000 * Math.pow(taxasDeJuros[i], j + 1));
                    }
                }
            }
            System.out.println(Arrays.toString(dp[i]));
        }


        // Encontra a ordem ótima
        int[] ordem = new int[n];
        int j = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j == 0 || dp[i][j] < dp[i][j - 1]) {
                ordem[j] = i;
                j--;
            }
        }

        // Imprime a ordem ótima
        System.out.println("Ordem ótima para pagamento dos empréstimos:");
        for (int i = 0; i < n; i++) {
            System.out.println(
                    "Mês " + (i + 1) + ": Banco " + (ordem[i] + 1) + " (taxa de juros: " + taxasDeJuros[ordem[i]]
                            + ") - valor: R$" + 1000 * Math.pow(taxasDeJuros[ordem[i]], i + 1));
        }

        // Imprime o valor total gasto mínimo
        System.out.println("Valor total gasto mínimo: R$" + dp[n - 1][n - 1]);
    }



    public static void main(String[] args) {
        double[] taxasDeJuros = { 1.1, 1.08, 1.22, 1.12, 1.3, 1.28, 1.25, 1.15, 1.18, 1.35, 1.4 };
        minimizarPagamentos(taxasDeJuros);

    }
}
