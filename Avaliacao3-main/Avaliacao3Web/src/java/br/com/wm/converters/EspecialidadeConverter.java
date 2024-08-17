/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wm.converters;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import br.com.av3.modelo.Especialidade;
import br.com.av3.servico.ServicoEspecialidade;

@Named("EspecialidadeConverter")
@Stateless
public class EspecialidadeConverter  implements Converter<Especialidade>{
    @EJB(lookup="java:global/Avaliacao3EJB/EspecialidadeDao!br.com.av3.servico.ServicoEspecialidade")
    ServicoEspecialidade servicoEspecialidade;
    
    @Override
    public Especialidade getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            long id = Long.parseLong(value);
            return servicoEspecialidade.buscar(id);
        } catch(Throwable t) {
            t.printStackTrace();
            return null;
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Especialidade value) {
        if (value != null) {
            return String.valueOf( value.getId() );
        }
        return null; //To change body of generated methods, choose Tools | Templates.
    }
    
}
