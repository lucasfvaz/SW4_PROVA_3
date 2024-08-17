/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.av3.servico;

import java.util.List;
import javax.ejb.Remote;
import br.com.av3.modelo.Consulta;
import br.com.av3.modelo.Especialidade;
import br.com.av3.modelo.Medico;


@Remote
public interface ServicoMedico {
    public List<Medico> listarMedicos();
    public List<Medico> listarMedicosPorEspecilidade(Especialidade e);
    public Medico buscar(Long id);
}
