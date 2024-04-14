package org.allysoncp.service;

import jakarta.transaction.Transactional;
import org.allysoncp.entity.Chamado;
import org.allysoncp.entity.Filas;
import org.allysoncp.entity.Status;
import org.allysoncp.entity.Usuario;
import org.allysoncp.repositories.ChamadoRepository;
import org.allysoncp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import static java.util.Arrays.asList;
@Service
public class DBService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;
@Transactional
    public void instanciaBaseDeDados() {

    Usuario usuario1 = new Usuario(1,"Maria Santos", "maria@example.com", "987654321", "Edifício B", "Sala 202", 2, true, Filas.NIVEL1);


    Usuario usuario2 = new Usuario(null, "Maria Santos", "maria@example.com", "987654321", "Edifício B", "Sala 202", 2, true, Filas.NIVEL1);
    Usuario usuario3 = new Usuario(null, "Pedro Oliveira", "pedro@example.com", "555555555", "Edifício C", "Sala 303", 3, true, Filas.NIVEL1);
    Usuario usuario4 = new Usuario(null, "Ana Lima", "ana@example.com", "222222222", "Edifício A", "Sala 102", 1, true, Filas.NIVEL3);
    Usuario usuario5 = new Usuario(null, "Carlos Silva", "carlos@example.com", "333333333", "Edifício B", "Sala 201", 2, true, Filas.PRESENCIAL);
    Usuario usuario6 = new Usuario(null, "Mariana Oliveira", "mariana@example.com", "444444444", "Edifício C", "Sala 304", 3, true, Filas.COORDENACAO);

    Chamado chamado1 = new Chamado(null, Status.VENCIDO, LocalDateTime.now() , usuario1, "Problema com o computador", Filas.NIVEL1);

    Chamado chamado2 = new Chamado(null, Status.VENCIDO,LocalDateTime.now(), usuario2, "Solicitação de suporte técnico", Filas.NIVEL3);
    Chamado chamado3 = new Chamado(null, Status.SUSPENSO,LocalDateTime.now(), usuario3, "Dúvida sobre software", Filas.NIVEL2);
    Chamado chamado4 = new Chamado(null, Status.FECHADO,LocalDateTime.now(), usuario4, "Atualização de software", Filas.NIVEL1);
    Chamado chamado5 = new Chamado(null, Status.ABERTO,LocalDateTime.now(), usuario5, "Problema de conexão de rede", Filas.NIVEL2);
    Chamado chamado6 = new Chamado(null, Status.EM_ANDAMENTO, LocalDateTime.now(), usuario1, "Consulta sobre políticas internas", Filas.NIVEL3);
    Chamado chamado7 = new Chamado(null, Status.FECHADO,LocalDateTime.now(), usuario1, "Instalação de novo software", Filas.NIVEL1);
    Chamado chamado8 = new Chamado(null, Status.ABERTO, LocalDateTime.now(), usuario2, "Problema de impressão", Filas.NIVEL3);
    Chamado chamado9 = new Chamado(null, Status.EM_ANDAMENTO, LocalDateTime.now(), usuario3, "Solicitação de treinamento", Filas.NIVEL1);





            usuarioRepository.saveAll((asList(usuario1, usuario2, usuario3, usuario4, usuario5, usuario6)));
            chamadoRepository.saveAll(asList(chamado1, chamado2, chamado3, chamado4, chamado5, chamado6, chamado7, chamado8, chamado9));

}}
