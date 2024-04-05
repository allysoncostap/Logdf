package org.allysoncp.service;

import org.allysoncp.entity.Chamado;
import org.allysoncp.entity.Status;
import org.allysoncp.entity.Usuario;
import org.allysoncp.repositories.ChamadoRepository;
import org.allysoncp.repositories.UsuarioRepository;
import org.allysoncp.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.CharArrayReader;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario findById(Integer id) {

            Optional<Usuario> obj = usuarioRepository.findById(id);
            Usuario usuario = obj.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! Id:" + id + ",  Tipo: " + Usuario.class.getName()));



            return usuario;
        }

    public Usuario create( Usuario obj) {
        obj.setId(null);
        return usuarioRepository.save(obj);

    }
    public void delete(Integer id) {
        Usuario obj = findById(id);
        usuarioRepository.delete(obj);
    }




    public Usuario update(Integer id, Usuario obj) {
        Usuario newObj = findById(id);
        updateData(newObj,obj);
        return usuarioRepository.save(newObj);

    }
    public void updateData(Usuario newUser, Usuario user){
        newUser.setNome(user.getNome());
        newUser.setEmail(user.getEmail());
        newUser.setTelefone(user.getTelefone());
        newUser.setEdificio(user.getEdificio());
        newUser.setSala(user.getSala());
        newUser.setAndar(user.getAndar());
        newUser.setAtivo(user.getAtivo());
        newUser.setFilas(user.getFilas());


    }




}