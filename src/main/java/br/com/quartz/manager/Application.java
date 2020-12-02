package br.com.quartz.manager;

import java.util.Scanner;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	final JobManager manager;
	Scanner scanner;

	public Application(JobManager manager) {
		this.manager = manager;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {

		SchedulerFactory schf = new StdSchedulerFactory();
		Scheduler sch = schf.getScheduler();

		boolean system=true;
		Scanner scanner = new Scanner(System.in);

		while(system) {
			System.out.println("Qual acao vc quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Criar Job");
			System.out.println("2 - Reagendar Job");
			System.out.println("3 - Pausar/Despausar Job");
			System.out.println("4 - Visualizar Jobs e seus status");
			System.out.println("5 - Pausar/Despausar scheduler");

			int action = scanner.nextInt();

			switch(action) {
				case 1:
					manager.createJob(scanner, sch);
					break;

				case 2:
					System.out.println("Nome do job a ser reagendado:");
					String name = scanner.next();
					// encontrar job, mostrar intervalo atual
					// pegar novo intervalo
					break;
				
				case 3:
					// pegar nome do job e pausar/despausar dependendo do status atual
					break;

				case 4:
					// mostrar todos os jobs
				case 5:
					// pausar/despausar scheduler e dizer seu novo status
				default:
					system = false;
					break;
			}
		}

		scanner.close();
	}


}