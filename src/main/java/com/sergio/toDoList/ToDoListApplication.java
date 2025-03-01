package com.sergio.toDoList;

import com.sergio.toDoList.service.CrudTarefa;
import com.sergio.toDoList.service.CrudUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ToDoListApplication implements CommandLineRunner{

	@Autowired
	private CrudUsuario crudUsuario;

	@Autowired
	private CrudTarefa crudTarefa;

	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Boolean isTrue = true;
		Scanner scanner = new Scanner(System.in);

		while (isTrue) {
			System.out.println("Qual entidade você deseja interagir?");
			System.out.println("0 - Sair");
			System.out.println("1 - Usuario");
			System.out.println("2 - Tarefa");

			int opcao = scanner.nextInt();

			switch (opcao) {
				case 1:
					this.crudUsuario.menu(scanner);
					break;
				case 2:
					scanner.nextLine();
					System.out.println("Favor informar seu login para prosseguir");
					String login = scanner.nextLine();

					this.crudTarefa.menu(scanner, login);
					break;
				default:
					isTrue = false;
					break;
			}
		}
	}

}
