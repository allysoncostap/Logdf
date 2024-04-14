package org.allysoncp.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.allysoncp.entity.Chamado;
import org.allysoncp.entity.Status;
import org.allysoncp.entity.Usuario;
import org.allysoncp.repositories.ChamadoRepository;
import org.allysoncp.repositories.UsuarioRepository;
import org.allysoncp.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private UsuarioService usuarioService;


    @Autowired
    private UsuarioRepository usuarioRepository;


    public Chamado findById(Integer id) {
        Optional<Chamado> obj = chamadoRepository.findById(id);
        Chamado chamado = obj.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! Id:" + id + ",  Tipo: " + Chamado.class.getName()));

        // Verifica se o usuário associado ao chamado é nulo
        if (chamado.getUsuario() == null) {
            throw new ObjectNotFoundException(" 1 Usuário não encontrado para o chamado! Id do chamado: " + id);
        }

        // Limpa a associação com o usuário para evitar recursão infinita ao serializar
        chamado.getUsuario().setChamados(null);

        return chamado;
    }

    public List<Chamado> findByStatus(Status status) {
        return chamadoRepository.findByStatus(status);

    }

    public List<Chamado> findAll(Integer id_User) {
        usuarioService.findById(id_User);
        return chamadoRepository.findAllByUsuario(id_User);
    }

    public Chamado create(Integer id_User, Chamado obj) {
        obj.setId(null);
        Usuario user = usuarioService.findById(id_User);
        obj.setUsuario(user);
        return chamadoRepository.save(obj);

    }

    public List<Chamado> findAllT() {
        return chamadoRepository.findAll();
    }


    public void delete(Integer id) {
        Chamado obj = findById(id);
        chamadoRepository.delete(obj);
    }


    public Chamado update(Integer id, Chamado obj) {
        // Encontra o Chamado a ser atualizado pelo ID
        Chamado newObj = findById(id);
        // Atualiza os dados do Chamado com base nos dados recebidos no objeto "obj"
        updateData(newObj, obj);
        // Salva e retorna o Chamado atualizado
        return chamadoRepository.save(newObj);
    }

    // Método privado para atualizar os dados do Chamado
    private void updateData(Chamado newObj, Chamado obj) {
        // Atualiza os campos do Chamado
        newObj.setStatus(obj.getStatus());
        newObj.setFilas(obj.getFilas());
        newObj.setDescricao(obj.getDescricao());


        // Verifica se o campo de usuário não é nulo e se tem um ID válido
        if (obj.getUsuario() != null && obj.getUsuario().getId() != null) {
            // Carrega o usuário correspondente ao ID fornecido
            Optional<Usuario> optionalUsuario = usuarioRepository.findById(obj.getUsuario().getId());

            // Verifica se o usuário foi encontrado
            if (optionalUsuario.isPresent()) {
                // Define o usuário do newObj como o usuário encontrado
                newObj.setUsuario(optionalUsuario.get());
            } else {
                // Se o usuário não for encontrado, lança uma exceção ou trata de outra forma apropriada
                throw new RuntimeException("Usuário com ID " + obj.getUsuario().getId() + " não encontrado.");
            }
        }
    }




    @Bean
    public void iniciarAtualizacaoStatusChamados() {
        try {
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    System.out.println("Iniciando atualização de status de chamados...");

                    List<Chamado> chamados = chamadoRepository.findByStatus(Status.ABERTO);
                    LocalDateTime now = LocalDateTime.now(); // Obtenha o tempo atual apenas uma vez para garantir consistência
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
                    for (Chamado chamado : chamados) {
                        LocalDateTime chamadoDate = LocalDateTime.parse(chamado.getDataFormatada(), formatter);
                        Duration tempoDecorrido = Duration.between(chamadoDate, now);
                        if (tempoDecorrido.toMinutes() > 1) {
                            try {
                                chamado.setStatus(Status.VENCIDO);
                                chamadoRepository.save(chamado);
                            } catch (Exception e) {
                                // Lidere com a exceção aqui
                                e.printStackTrace(); // Apenas um exemplo, você pode tratar a exceção de acordo com a sua lógica
                            }
                        }
                    }
                } catch (Exception e) {
                    // Lidere com a exceção aqui
                    e.printStackTrace(); // Apenas um exemplo, você pode tratar a exceção de acordo com a sua lógica
                }
            }, 0, 1, TimeUnit.MINUTES); // Verificar a cada minuto
        } catch (Exception e) {
            // Lidere com a exceção aqui
            e.printStackTrace(); // Apenas um exemplo, você pode tratar a exceção de acordo com a sua lógica
        }
    }
}