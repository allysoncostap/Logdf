package org.allysoncp.repositories;

import org.allysoncp.entity.Chamado;
import org.allysoncp.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
    List<Chamado> findByStatus(Status status);
@Query("SELECT obj FROM Chamado obj WHERE obj.usuario.id =:id_user ORDER BY obj.status")
    List<Chamado> findAllByUsuario(@Param(value = "id_user") Integer id_User);
}
