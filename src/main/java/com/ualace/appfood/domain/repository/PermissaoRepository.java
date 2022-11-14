package com.ualace.appfood.domain.repository;


import com.ualace.appfood.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
//    List<Permissao> listar();
//    Permissao buscarPorId(Long id);
//    Permissao salvar(Permissao permissao);
//    void remover(Permissao permissao);


}
