package com.sergio.toDoList.service;

import com.sergio.toDoList.entities.Tarefa;
import com.sergio.toDoList.entities.Usuario;
import com.sergio.toDoList.repository.TarefaRepository;
import com.sergio.toDoList.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudTarefa {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void menu(Scanner scanner, String login) {
        Boolean isTrue = true;

        Usuario usuarioVerificado = this.usuarioRepository.buscarPorLogin(login);

        if (usuarioVerificado != null) {
            while (isTrue) {
                System.out.println("\nQual ação você quer executar?");
                System.out.println("0 - Voltar ao menu anterior");
                System.out.println("1 - Cadastrar uma nova tarefa");
                System.out.println("2 - Atualizar uma tarefa");
                System.out.println("3 - Visualizar todos as tarefas");
                System.out.println("4 - Deletar uma tarefa");
                System.out.println("5 - Finalizar uma tarefa");

                int opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        this.cadastrar(scanner, usuarioVerificado);
                        break;
                    case 2:
                        this.atualizar(scanner, usuarioVerificado);
                        break;
                    case 3:
                        this.visualizar(usuarioVerificado);
                        break;
                    case 4:
                        this.deletar(scanner, usuarioVerificado);
                        break;
                    case 5:
                        this.finalizar(scanner, usuarioVerificado);
                        break;
                    default:
                        isTrue = false;
                        break;
                }
            }
            System.out.println();
        }
        else {
            System.out.println("O login do usuario informado: " + usuarioVerificado.getLogin() + " é inválido ou não existe\n");
        }
    }


    private void cadastrar(Scanner scanner, Usuario usuarioVerificado) {
        scanner.nextLine();

        System.out.print("Digite o titulo da tarefa: ");
        String titulo = scanner.nextLine();

        System.out.print("Digite a descricao da tarefa: ");
        String descricao = scanner.nextLine();

        Tarefa tarefa = new Tarefa(titulo, descricao, usuarioVerificado);
        this.tarefaRepository.save(tarefa);

        System.out.println("Tarefa salva no Banco!!!\n");
    }


    private void atualizar(Scanner scanner, Usuario usuarioVerificado) {
        scanner.nextLine();

        System.out.print("Digite o Id da tarefa a ser atualizada: ");
        Long id = scanner.nextLong();

        Optional<Tarefa> optional = this.tarefaRepository.findById(id);

        if (optional.isPresent()) {
            scanner.nextLine();

            Tarefa tarefa = optional.get();

            if(tarefa.getUsuario().getLogin().equals(usuarioVerificado.getLogin())){
                System.out.print("Digite o titulo da tarefa para atualizar: ");
                String titulo = scanner.nextLine();

                System.out.print("Digite a descricao da tarefa para atualizar: ");
                String descricao = scanner.nextLine();

                Tarefa tarefaOfc = new Tarefa(titulo, descricao, usuarioVerificado);
                this.tarefaRepository.save(tarefaOfc);

                System.out.println("Tarefa atualizada no Banco!!!\n");
            }else {
                System.out.println("Essa tarefa não pertence ao seu usuario portanto não pode ser atualizada");
            }

        } else {
            System.out.println("O id da tarefa informada: " + id + " é inválido\n");
        }
    }

    private void visualizar(Usuario usuarioVerificado) {
        Iterable<Tarefa> tarefas = this.tarefaRepository.buscarTarefaPorUsuario(usuarioVerificado.getId());

        for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa);
        }

        System.out.println();

    }

    private void deletar(Scanner scanner, Usuario usuarioVerificado) {
        System.out.print("Digite o Id da tarefa a ser deletada: ");
        Long id = scanner.nextLong();

        Optional<Tarefa> optional = this.tarefaRepository.findById(id);

        if (optional.isPresent()) {
            Tarefa tarefa = optional.get();

            if(tarefa.getUsuario().getLogin().equals(usuarioVerificado.getLogin())){
                this.tarefaRepository.deleteById(id);
                System.out.println("Tarefa Deletada!\n");
            }else {
                System.out.println("Essa tarefa não pertence ao seu usuario portanto não pode ser deletada");
            }
        }
        else {
            System.out.println("O id da tarefa informada: " + id + " é inválido\n");
        }

    }

    private void finalizar(Scanner scanner, Usuario usuarioVerificado) {
        System.out.print("Digite o Id da tarefa a ser finalizada: ");
        Long id = scanner.nextLong();

        Optional<Tarefa> optional = this.tarefaRepository.findById(id);

        if (optional.isPresent()) {
            Tarefa tarefa = optional.get();

            if(tarefa.getUsuario().getLogin().equals(usuarioVerificado.getLogin())){
                tarefa.finalizarTarefa();
                tarefaRepository.save(tarefa);
                System.out.println("Tarefa finalizada!\n");
            }else {
                System.out.println("Essa tarefa não pertence ao seu usuario portanto não pode ser finalizada");
            }
        }
        else {
            System.out.println("O id da tarefa informada: " + id + " é inválido\n");
        }
    }

}
