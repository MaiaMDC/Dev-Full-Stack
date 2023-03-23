package com.marcio.helpdesk.services;

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
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB() {

        Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "791.694.660-25", "valdir@mail.com", "123");
        tec1.addPerfis(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "604.616.570-9", "torvalds@mail.com", "123");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);

        Tecnico tec2 = new Tecnico(null, "Mario Alberto", "791.694.660-26", "mario@mail.com", "123");
        tec1.addPerfis(Perfil.ADMIN);

        Cliente cli2 = new Cliente(null, "Renan Camargo", "604.616.570-91", "renan@mail.com", "123");

        Chamado c2 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 02", "Segundo chamado", tec2, cli2);

        Tecnico tec3 = new Tecnico(null, "Marcio Douglas", "791.694.660-27", "marcio@mail.com", "123");
        tec1.addPerfis(Perfil.ADMIN);

        Cliente cli3 = new Cliente(null, "Adriano Concead", "604.616.570-92", "concead@mail.com", "123");

        Chamado c3 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 03", "Terceiro chamado", tec3, cli3);

        tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3));
        clientRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
        chamadoRepository.saveAll(Arrays.asList(c1, c2, c3));
    }

}
