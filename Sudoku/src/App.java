import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Area;
import model.Quadro;
import util.QuadroPadrao;

public class App {

    private final static Scanner scanner = new Scanner(System.in);

    private static Quadro quadro;

    private static int QUADRO_LIMITE = 9;

    public static void main(String[] args) throws Exception {
        
        final var posicoes = Stream.of(args)
            .collect(Collectors.toMap(
                    k -> k.split(";")[0],
                    v -> v.split(";")[1]));          
                    
        var opcao = -1;

        while(true){
            System.out.println("Digite a opção desejada:");
            System.out.println("1 - iniciar novo jogo");
            System.out.println("2 - colocar novo numero");
            System.out.println("3 - remover um numero");
            System.out.println("4 - visualizar jogo atual");
            System.out.println("5 - verificar status do jogo");
            System.out.println("6 - limpar jogo");
            System.out.println("7 - finalizar jogo");
            System.out.println("8 - Sair");
            
            try{
                opcao = scanner.nextInt();
                switch (opcao) {
                    case 1 -> iniciarNovoJogo(posicoes);
                    case 2 -> incluirNumero();
                    case 3 -> removerNumero();
                    case 4 -> visualizarJogoAtual();
                    case 5 -> verificarStatusJogo();
                    case 6 -> limparJogo();
                    case 7 -> finalizarJogo();    
                    case 8 -> System.exit(0);                   
                    default -> System.out.println("Opção inválida, tente novamente.");                    
                }
            } catch (Exception e) {
                System.out.println("Opção Inválida, deve ser informado numeros de 1 a 8! Tente novamente.");
                scanner.next(); // Limpa o buffer do scanner
                continue;
            }
        }        
    }

    private static void iniciarNovoJogo(final Map<String, String> posicoes) {
        if (nonNull(quadro)) {
            System.out.println("Já existe um jogo em andamento. Por favor, finalize ou limpe o jogo atual antes de iniciar um novo.");
            return;
        }
        List<List<Area>> areas = new ArrayList<>();
        for (int i = 0; i < QUADRO_LIMITE; i++) {
            areas.add(new ArrayList<>());
            for (int j = 0; j < QUADRO_LIMITE; j++) {
                var posicao = posicoes.get("%s,%s".formatted(i, j));
                var esperada = Integer.parseInt(posicao.split(",")[0]);
                var fixa = Boolean.parseBoolean(posicao.split(",")[1]);

                var areaAtual = new Area(esperada, fixa);
                areas.get(i).add(areaAtual);
            }      
        }
        quadro = new Quadro(areas);
        System.out.println("Novo jogo iniciado com sucesso!");
    }

    private static void incluirNumero() {
        if (isNull(quadro)) {
            System.out.println("O jogo ainda nao foi iniciado. Por favor, inicie um novo jogo antes de incluir numeros.");
            return;
        }
        System.out.println("Informe a coluna em qual número será inserido");
        var coluna = validaIntervaloNumero(0, 8);
        System.out.println("Informe a linha em qual número será inserido");
        var linha = validaIntervaloNumero(0, 8);
        System.out.printf("Informe o número que vai entrar na posição [%s,%s]\n", coluna, linha);
        var value = validaIntervaloNumero(1, 9);
        if (!quadro.MudarValor(coluna, linha, value)){
            System.out.printf("A posição [%s,%s] tem um valor fixo\n", coluna, linha);
        }        
    }

    private static void removerNumero() {
        if (isNull(quadro)) {
            System.out.println("O jogo ainda nao foi iniciado. Por favor, inicie um novo jogo antes de incluir numeros.");
            return;
        }
        System.out.println("Informe a coluna que em que o número será removido");
        var coluna = validaIntervaloNumero(0, 8);
        System.out.println("Informe a linha que em que o número será removido");
        var linha = validaIntervaloNumero(0, 8);
        if (!quadro.LimparValor(coluna, linha)){
            System.out.printf("A posição [%s,%s] tem um valor fixo\n", coluna, linha);
        }
    }

    private static void visualizarJogoAtual() {
        if (isNull(quadro)) {
            System.out.println("O jogo ainda nao foi iniciado. Por favor, inicie um novo jogo antes de incluir numeros.");
            return;
        }

        var args = new Object[81];
        var argPos = 0;
        for (int i = 0; i < QUADRO_LIMITE; i++) {
            for (var coluna: quadro.getAreas()){
                args[argPos ++] = " " + ((isNull(coluna.get(i).getAtual())) ? " " : coluna.get(i).getAtual());
            }
        }
        System.out.println("Seu jogo se encontra da seguinte forma");
        System.out.printf((QuadroPadrao.QUADRO_PADRAO) + "\n", args);        
    }

    private static void verificarStatusJogo() {
        if (isNull(quadro)) {
            System.out.println("O jogo ainda nao foi iniciado. Por favor, inicie um novo jogo antes de incluir numeros.");
            return;
        }

        System.out.printf("O jogo atualmente se encontra no status %s\n", quadro.getStatus().getLabel());
        if(quadro.TemErros()){
            System.out.println("O jogo contém erros");
        } else {
            System.out.println("O jogo não contém erros");
        }        
    }

    private static void limparJogo() {
        if (isNull(quadro)) {
            System.out.println("O jogo ainda nao foi iniciado. Por favor, inicie um novo jogo antes de incluir numeros.");
            return;
        }

        System.out.println("Tem certeza que deseja limpar seu jogo e perder todo seu progresso?");
        var confirm = scanner.next();
        while (!confirm.equalsIgnoreCase("sim") && !confirm.equalsIgnoreCase("não")){
            System.out.println("Informe 'sim' ou 'não'");
            confirm = scanner.next();
        }

        if(confirm.equalsIgnoreCase("sim")){
            quadro.reset();
        }
    }

    private static void finalizarJogo() {
        if (isNull(quadro)) {
            System.out.println("O jogo ainda nao foi iniciado. Por favor, inicie um novo jogo antes de incluir numeros.");
            return;
        }

        if (quadro.JogoFinalizado()){
            System.out.println("Parabéns você concluiu o jogo");
            visualizarJogoAtual();
            quadro = null;
        } else if (quadro.TemErros()) {
            System.out.println("Seu jogo contém, erros, verifique seu board e ajuste-o");
        } else {
            System.out.println("Você ainda precisa preenhcer algum espaço");
        }
    }

    private static int validaIntervaloNumero(final int min, final int max){
        var current = scanner.nextInt();
        while (current < min || current > max){
            System.out.printf("Informe um número entre %s e %s\n", min, max);
            current = scanner.nextInt();
        }
        return current;
    }
}
