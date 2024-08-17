/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.wm.beans;

import br.com.av3.modelo.Consulta;
import br.com.av3.servico.ServicoConsulta;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 *
 * @author aluno
 */
@Named(value = "gerenciadorConsultasBean")
@SessionScoped
public class GerenciadorConsultasBean implements Serializable {
   // colocar lookup certo para gerenciador consulta Bean 
   @EJB(lookup="java:global/Avaliacao3EJB/ConsultaDao!br.com.av3.servico.ServicoConsulta")
    ServicoConsulta servicoConsulta;
    
    @Inject
    MedicoBean medicoBean;
    
    
     private String nomePaciente;
     private String telefonePaciente;
     private Date dataHora;
     private Consulta consultaagendada;
     private List<Consulta> consulAgendada;
    public GerenciadorConsultasBean() {
        consultaagendada =  new Consulta();
    }
    @PostConstruct
    public void iniciar() {
       consulAgendada = servicoConsulta.listarConsultas();
        
    }

     public String confirmarConsulta() {
         consultaagendada.setNomePaciente(nomePaciente);
         consultaagendada.setTelefonePaciente(telefonePaciente);
         consultaagendada.setDataHora(dataHora);
         consultaagendada.setIdMedico(medicoBean.getMedicoSelecionado());
         getConsultasAgendadas();
         if(ExisteConsultaNesseHorario(consultaagendada)){
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Horário não Disponivel",
                            "Este Horário não está disponível pois já tem consulta nesse horário"));
         }else{
           FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Consulta Marcada",
                            "Consulta registrada com sucesso"));
           servicoConsulta.marcarConsulta(consultaagendada);
         }
         getConsultasPormedico();
        return null;
     }
     
     
     public Date getDateNow() {
         Date now;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date()); 
        now = cal.getTime();
       return now;
    }
     public void validateData(FacesContext context, UIComponent component, Object value ) throws ValidatorException {
     
     if (value == null) {
         return;
       }
     Date dataSelecionada = (Date) value;
     
     Date dataSelect = zeraCalendar(dataSelecionada);
     if (dataSelect.before(getDateNow())) {
        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data Inválida", "A data selecionada é anterior à data atual. "));
     }
   
   }
     public Date zeraCalendar(Date dataselect) {
         Calendar cal = Calendar.getInstance();
        cal.setTime(dataselect); 
        
      return cal.getTime();
    }
     public String cancelarConsulta(Consulta c) {
         FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Consulta Cancelada",
                            "Consulta cancelada com sucesso"));
        servicoConsulta.cancelarConsulta(c);
        return null;
     }
    public MedicoBean getMedicoBean() {
        return medicoBean;
    }

    public void setMedicoBean(MedicoBean medicoBean) {
        this.medicoBean = medicoBean;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getTelefonePaciente() {
        return telefonePaciente;
    }

    public void setTelefonePaciente(String telefonePaciente) {
        this.telefonePaciente = telefonePaciente;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    
    
    public ServicoConsulta getServicoConsulta() {
        return servicoConsulta;
    }

    public void setServicoConsulta(ServicoConsulta servicoConsulta) {
        this.servicoConsulta = servicoConsulta;
    }

    public Consulta getConsultaagendada() {
        return consultaagendada;
    }

    public void setConsultaagendada(Consulta consultaagendada) {
        this.consultaagendada = consultaagendada;
    }
    
    public List<Consulta> getConsultasPormedico() {
        if(medicoBean.getMedicoSelecionado().getId() == null){
             return servicoConsulta.listarConsultas();
        }else{
           return servicoConsulta.listarconsultasPormedico(medicoBean.getMedicoSelecionado());
        }
    }
    
    public List<Consulta> getConsultasAgendadas() {
             return servicoConsulta.listarConsultas();
        
    }
    
    
    public boolean ExisteConsultaNesseHorario(Consulta c){
        boolean existe = false;
        for(Consulta cv :  this.consulAgendada){
            //System.out.println(cv.getIdMedico());
            if(cv.getIdMedico().equals(c.getIdMedico()) && cv.getDataHora().getTime() == c.getDataHora().getTime()){
                
                existe = true;
            }else{
                existe = false;   
            }
        }
        
        return existe;
    }

    public List<Consulta> getConsulAgendada() {
        return consulAgendada;
    }

    public void setConsulAgendada(List<Consulta> consulAgendada) {
        this.consulAgendada = consulAgendada;
    }
    
    
    
}
