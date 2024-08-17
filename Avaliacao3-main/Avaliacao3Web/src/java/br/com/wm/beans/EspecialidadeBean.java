package br.com.wm.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import br.com.av3.modelo.Especialidade;
import br.com.av3.servico.ServicoEspecialidade;



@Named(value = "consultaBean")
@SessionScoped
public class EspecialidadeBean implements Serializable {
    
    @EJB(lookup="java:global/Avaliacao3EJB/EspecialidadeDao!br.com.av3.servico.ServicoEspecialidade")
    ServicoEspecialidade servicoEspecialidade;
    
    
    public List<Especialidade> getEspecialidades() {
        return servicoEspecialidade.listarEspecilidadeGeral();
    }
    
   
    public List<SelectItem> getEspecialidadesAsItems() {
        LinkedList<SelectItem> items = new LinkedList<>();
        items.add(new SelectItem(null,"Selecione uma especialidade"));
        for (Especialidade e : getEspecialidades()) {
            items.add(new SelectItem(e, e.getDescricao()));
        }
        return items;
    }
    
    
    
    
    
}
