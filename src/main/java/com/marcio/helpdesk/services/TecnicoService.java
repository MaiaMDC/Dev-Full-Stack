package com.marcio.helpdesk.services;

import com.marcio.helpdesk.domain.Pessoa;
import com.marcio.helpdesk.domain.Tecnico;
import com.marcio.helpdesk.domain.dtos.TecnicoDTO;
import com.marcio.helpdesk.repositories.PessoaRepository;
import com.marcio.helpdesk.repositories.TecnicoRepository;
import com.marcio.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.marcio.helpdesk.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id:" + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCpfEEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return repository.save(newObj);
    }

    public Tecnico update(Integer id, TecnicoDTO objDTO) {
        objDTO.setId(id);
        Tecnico oldObj = findById(id);
        validaPorCpfEEmail(objDTO);
        oldObj = new Tecnico(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && !Objects.equals(obj.get().getId(), objDTO.getId())){
            throw new DataIntegrityViolationException("CPF já cadastrado no Sistema");
        }
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && !Objects.equals(obj.get().getId(), objDTO.getId())){
            throw new DataIntegrityViolationException("Email já cadastrado no Sistema");
        }
    }

}
