package org.allysoncp.resouse;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.allysoncp.dtos.IntroChamadoDtos;
import org.allysoncp.entity.Chamado;
import org.allysoncp.entity.Status;
import org.allysoncp.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.*;
import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService chamadoService;

@GetMapping(value = "/{id}")
    public ResponseEntity<Chamado> findById(@PathVariable Integer id){
        Chamado obj = chamadoService.findById(id);
        return  ResponseEntity.ok().body(obj);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Chamado>> encontrarChamadosPorStatus(@PathVariable String status) {
        Status statusEnum = Status.valueOf(status.toUpperCase()); // Converte a string para o enum Status
        return ResponseEntity.ok(chamadoService.findByStatus(statusEnum));
    }

@GetMapping
    public ResponseEntity<List<IntroChamadoDtos>>findAll(@RequestParam(value ="usuario", defaultValue = "0") Integer id_User){
    List<Chamado> list = chamadoService.findAll(id_User);
    List<IntroChamadoDtos>listDTO= list.stream().map(IntroChamadoDtos::new).collect(Collectors.toList());
return  ResponseEntity.ok().body(listDTO);}
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/todos")
    public ResponseEntity<List<IntroChamadoDtos>> findAllT(){
List<Chamado> list= chamadoService.findAllT();
List<IntroChamadoDtos>listDTO = list.stream().map(obj -> new IntroChamadoDtos(obj)).collect(Collectors.toList());
   return ResponseEntity.ok().body(listDTO);
    }




@PostMapping
    public ResponseEntity<Chamado> Create(@RequestParam(value = "usuario",defaultValue = "0") Integer id_user,@RequestBody Chamado obj){
    Chamado newObj = chamadoService.create(id_user,obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/chamados/{id}").buildAndExpand(newObj.getId()).toUri();
    return ResponseEntity.created(uri).build();
}

@DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
    chamadoService.delete(id);
    return ResponseEntity.noContent().build();

}


@PutMapping(value = "/{id}")
    public ResponseEntity<Chamado>update(@PathVariable Integer id,@RequestBody Chamado obj){
    Chamado newObj = chamadoService.update(id,obj);
    return ResponseEntity.ok().body(newObj);
}

@PatchMapping(value = "/{id}")
    public ResponseEntity<Chamado>updatePatch(@PathVariable Integer id, @RequestBody Chamado  obj ){
    Chamado newObj = chamadoService.update(id,obj);
    return ResponseEntity.ok().body(newObj);
}


}
