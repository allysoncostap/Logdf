package org.allysoncp.resouse;

import org.allysoncp.entity.Usuario;
import org.allysoncp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value ="/usuarios")
public class UsuarioResouce {

    @Autowired
    public UsuarioService usuarioService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario>findById(@PathVariable Integer id){
        Usuario newUser =usuarioService.findById(id);
        return  ResponseEntity.ok().body(newUser);
    }

@PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario user){
        Usuario newUser= usuarioService.create(user);
    URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/usuario/{id}").buildAndExpand(newUser.getId()).toUri();
    return  ResponseEntity.created(uri).build();
}

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id, @RequestBody  Usuario user){
        Usuario newObj = usuarioService.update(id, user);
        return  ResponseEntity.ok().body(newObj);
    }



    @PatchMapping(value = "/{id}")
    public ResponseEntity<Usuario> updatePatch(@PathVariable Integer id,  @RequestBody Usuario obj){
        Usuario newObj = usuarioService.update(id, obj);
        return  ResponseEntity.ok().body(newObj);

    }
}







