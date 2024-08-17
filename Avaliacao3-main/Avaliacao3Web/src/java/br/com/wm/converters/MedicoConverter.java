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
import br.com.av3.modelo.Medico;
import br.com.av3.servico.ServicoMedico;

@Named("MedicoConverter")
@Stateless
public class MedicoConverter  implements Converter<Medico>{
    @EJB(lookup="java:global/Avaliacao3EJB/MedicoDao!br.com.av3.servico.ServicoMedico")
    ServicoMedico servicoMedico;

    @Override
    public Medico getAsObject(FacesContext context, UIComponent component, String value) {
         try {
            long id = Long.parseLong(value);
            return servicoMedico.buscar(id);
        } catch (Exception t) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Medico value) {
        if (value != null) {
            return String.valueOf( value.getId() );
        }
        return null;  //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
