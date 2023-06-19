package aplicacao;

import java.io.IOException;
import java.util.Scanner;

import classes.Piloto;

public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {

        final int MAX_ELEMENTOS = 10;
        int opcao, qtdCadastrados = 0;
        Piloto[] pilotos = new Piloto[MAX_ELEMENTOS];



        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo brevete");
            System.out.println("4 - Cadastrar aeronave");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine();

            if (opcao == 1) {
                
                if (qtdCadastrados == MAX_ELEMENTOS) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos.");
                    voltarMenu(in);
                    continue;
                }

                
                System.out.println("Insira o nome do piloto: ");
                String nome = in.nextLine();
                System.out.println("Insira o cpf: ");
                String cpf = in.nextLine();
                if(cpf.length() != 11){
                    System.out.println("Cpf inválido!");

                }

                
                System.out.println("Insira seu brevete: ");
                String breve = in.nextLine();

                Piloto piloto = new Piloto(nome, cpf, breve);
                pilotos[qtdCadastrados] = piloto;
                qtdCadastrados = qtdCadastrados + 1;

                System.out.println("\nPiloto cadastrado com sucesso.");
                voltarMenu(in);
            } else if (opcao == 2) {
               
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há pilotos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }

               
                System.out.println("*** Pilotos cadrastados ***");
                for (Piloto piloto : pilotos) {
                    if (piloto != null) {
                        System.out.println("Nome: " + piloto.getNome());
                        System.out.println("CPF: " + piloto.getCpf());
                        System.out.println("Brevê: " + piloto.getBreve());
                        System.out.println("------------");
                    }
                }

                voltarMenu(in);
            } else if (opcao == 3) {
                boolean key = false;
                Piloto pilotoEncontrado = null;
                System.out.print("\nInforme o Brevete: ");
                String breve = in.nextLine();

                for (int i = 0; !key && i <= pilotos.length; i++) {
                    key = breve.equals(pilotos[i].getBreve());
                    pilotoEncontrado = pilotos[i];
                }

                if (key) {
                    System.out.printf("BREVE: " + pilotoEncontrado.getBreve() + " cadastrado.");
                } else if (pilotoEncontrado == null) {
                    System.out.println("Piloto não cadastrado.");
                }

                
                voltarMenu(in);
            } else if (opcao == 4) {
                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.println("\nSem pilotos, não há como cadastrar uma aeronave");
                    voltarMenu(in);
                    continue;
                }
                

                System.out.print("Informe o modelo da aeronave: ");
                String modelo = in.nextLine();

                System.out.print("Informe o número de série: ");
                String numeroSerie = in.nextLine();

                System.out.println("\nAeronave cadastrada com sucesso.");
                // Exiba os dados da aeronave
                voltarMenu(in);
            } else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");

        System.out.flush();

    }
}
