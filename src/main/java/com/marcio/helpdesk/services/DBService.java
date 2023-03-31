package com.marcio.helpdesk.services;

import com.marcio.helpdesk.domain.Chamado;
import com.marcio.helpdesk.domain.Cliente;
import com.marcio.helpdesk.domain.Tecnico;
import com.marcio.helpdesk.domain.enums.Perfil;
import com.marcio.helpdesk.domain.enums.Prioridade;
import com.marcio.helpdesk.domain.enums.Status;
import com.marcio.helpdesk.repositories.ChamadoRepository;
import com.marcio.helpdesk.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB() {

        Tecnico tec1 = new Tecnico(null, "Marcio Souza", "791.694.660-25", "marcio@mail.com", encoder.encode("123"));
        tec1.addPerfis(Perfil.ADMIN);
        Tecnico tec2 = new Tecnico(null, "Adriano Teixeira", "906.474.150-62", "adriano@mail.com", encoder.encode("123"));
        Tecnico tec3 = new Tecnico(null, "Gustavo Santos", "628.143.430-76", "gustavo@mail.com", encoder.encode("123"));
        Tecnico tec4 = new Tecnico(null, "Danilo Oliveira", "007.063.630-35", "danilo@mail.com", encoder.encode("123"));
        Tecnico tec5 = new Tecnico(null, "Edcarlos Silva", "751.079.310-65", "edcarlos@mail.com", encoder.encode("123"));


        Cliente cli1 = new Cliente(null, "Vanessa Prieto", "457.042.280-20", "vanessa@mail.com", encoder.encode("123"));
        Cliente cli2 = new Cliente(null, "Rodrigo Leite", "311.003.230-90", "rodrigo@mail.com", encoder.encode("123"));
        Cliente cli3 = new Cliente(null, "Emerson Oliveira", "814.870.790-10", "emerson@mail.com", encoder.encode("123"));
        Cliente cli4 = new Cliente(null, "Thiago Silva", "941.385.450-50", "thiago@mail.com", encoder.encode("123"));
        Cliente cli5 = new Cliente(null, "Jessica Pavan", "760.501.490-20", "jessica@mail.com", encoder.encode("123"));

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
        Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 02", "Segundo chamado", tec2, cli2);
        Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 03", "Terceiro chamado", tec3, cli3);
        Chamado c4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 04", "Quarto chamado", tec4, cli4);
        Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 05", "Quinto chamado", tec4, cli4);
        Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 06", "Sexto chamado", tec4, cli4);

        pessoaRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, cli1, cli2, cli3, cli4, cli5));
        chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
    }

}
