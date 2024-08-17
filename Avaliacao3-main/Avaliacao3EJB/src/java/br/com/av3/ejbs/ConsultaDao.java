
package br.com.av3.ejbs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.av3.modelo.Consulta;
import br.com.av3.modelo.Medico;
import br.com.av3.servico.ServicoConsulta;
import java.util.List;
import javax.persistence.Query;

@Stateless
public class ConsultaDao implements ServicoConsulta {
     @PersistenceContext
     EntityManager em;

    @Override
    public void marcarConsulta(Consulta c) {
        
        em.persist(c);
    }

    @Override
    public void cancelarConsulta(Consulta c) {
        if (!em.contains(c)) {
           c = em.merge(c);
        }
        em.remove(c);
    }

    @Override
    public Consulta buscar(int id) {
        return em.find(Consulta.class, id);
    }
    
    @Override
    public List<Consulta> listarConsultas() {
        return em.createNamedQuery("Consulta.findAll").getResultList();
    }

    @Override
    public List<Consulta> listarconsultasPormedico(Medico m) {
        Query q = em.createQuery("select me from Consulta me where me.idMedico = :idMedico order by me.nomePaciente", Consulta.class);
        q.setParameter("idMedico", m);
        return q.getResultList();  

    }
     
}
