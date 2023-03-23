package com.marcio.helpdesk;

import com.marcio.helpdesk.domain.Chamado;
import com.marcio.helpdesk.domain.Cliente;
import com.marcio.helpdesk.domain.Tecnico;
import com.marcio.helpdesk.domain.enums.Perfil;
import com.marcio.helpdesk.domain.enums.Prioridade;
import com.marcio.helpdesk.domain.enums.Status;
import com.marcio.helpdesk.repositories.ChamadoRepository;
import com.marcio.helpdesk.repositories.ClientRepository;
import com.marcio.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public static void main(String[] args) {
        SpringApplication.run(HelpdeskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "791.694.660-25", "valdir@mail.com", "123");
        tec1.addPerfis(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "604.616.570-90", "torvalds@mail.com", "123");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clientRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));

    }
}
