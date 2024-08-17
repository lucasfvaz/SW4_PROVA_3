/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.av3.ejbs;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.av3.modelo.Especialidade;
import br.com.av3.servico.ServicoEspecialidade;


@Stateless
public class EspecialidadeDao  implements ServicoEspecialidade{
     @PersistenceContext
     EntityManager em;
     
     
    @Override
    public List<Especialidade> listarEspecilidadeGeral() {
        return em.createQuery("SELECT d FROM Especialidade d order by d.descricao")
                .getResultList();
    }

    @Override
    public Especialidade buscar(Long id) {
        return em.find(Especialidade.class, id);
    }

    

    

   
}
