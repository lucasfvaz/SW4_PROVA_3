/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.av3.servico;

import javax.ejb.Remote;
import br.com.av3.modelo.Consulta;
import br.com.av3.modelo.Medico;
import java.util.List;

@Remote
public interface ServicoConsulta {
     public void marcarConsulta(Consulta c);
     public void cancelarConsulta(Consulta c);
     public List<Consulta> listarConsultas();
     public List<Consulta> listarconsultasPormedico(Medico m);
     public Consulta buscar(int id);
}
