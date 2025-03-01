package com.sergio.toDoList.service;

import com.sergio.toDoList.entities.Usuario;
import com.sergio.toDoList.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void menu(Scanner scanner) {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nQual ação você quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar novo usuario");
            System.out.println("2 - Atualizar um usuario");
            System.out.println("3 - Visualizar todos os usuarios");
            System.out.println("4 - Deletar um usuario");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    this.cadastrar(scanner);
                    break;
                case 2:
                    this.atualizar(scanner);
                    break;
                case 3:
                    this.visualizar();
                    break;
                case 4:
                    this.deletar(scanner);
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }
        System.out.println();
    }


    private void cadastrar(Scanner scanner) {
        scanner.nextLine();

        System.out.print("Digite o nome do usuario: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o login do usuario: ");
        String login = scanner.nextLine();

        Usuario usuario = new Usuario(nome, login);
        this.usuarioRepository.save(usuario);
        System.out.println("Usuario salvo no Banco!!!\n");
    }


    private void atualizar(Scanner scanner) {
        System.out.print("Digite o Id do usuario a ser atualizado: ");
        Long id = scanner.nextLong();

        scanner.nextLine();

        Optional<Usuario> optional = this.usuarioRepository.findById(id);

        if (optional.isPresent()) {
            System.out.print("Digite o nome do usuario para atualizar: ");
            String nome = scanner.nextLine();

            System.out.print("Digite o login do usuario para atualizar: ");
            String login = scanner.nextLine();

            Usuario usuario = optional.get();
            usuario.setNome(nome);
            usuario.setLogin(login);

            usuarioRepository.save(usuario);
            System.out.println("Usuario atualizado com sucesso!!!\n");
        }
        else {
            System.out.println("O id do usuario informado: " + id + " é inválido\n");
        }
    }

    private void visualizar() {
        Iterable<Usuario> usuarios = this.usuarioRepository.findAll();

        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }

        System.out.println();
    }

    private void deletar(Scanner scanner) {
        System.out.print("Digite o Id do Usuario a ser deletado: ");
        Long id = scanner.nextLong();

        Optional<Usuario> optional = this.usuarioRepository.findById(id);

        if (optional.isPresent()) {
            this.usuarioRepository.deleteById(id);
            System.out.println("Usuario Deletado!\n");
        }
        else {
            System.out.println("O id do usuario informado: " + id + " é inválido\n");
        }
    }
}
