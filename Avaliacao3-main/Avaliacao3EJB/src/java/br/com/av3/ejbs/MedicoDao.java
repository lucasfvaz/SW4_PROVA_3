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
import javax.persistence.Query;
import br.com.av3.modelo.Consulta;
import br.com.av3.modelo.Especialidade;
import br.com.av3.modelo.Medico;
import br.com.av3.servico.ServicoMedico;

@Stateless
public class MedicoDao implements ServicoMedico{
     @PersistenceContext
     EntityManager em;
    @Override
    public List<Medico> listarMedicos() {
        return em.createQuery("select me FROM Medico me ORDER BY me.nome")
                .getResultList();
    }

    @Override
    public List<Medico> listarMedicosPorEspecilidade(Especialidade e) {
        Query q = em.createQuery("select me from Medico me where me.idEspec = :idEspec order by me.nome", Medico.class);
        q.setParameter("idEspec", e);
        return q.getResultList(); 
    }

    

    @Override
    public Medico buscar(Long id) {
        return em.find(Medico.class, id);
    }
    
}
