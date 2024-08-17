/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.av3.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ertel
 */
@Entity
@Table(name = "consulta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consulta.findAll", query = "SELECT c FROM Consulta c"),
    @NamedQuery(name = "Consulta.findById", query = "SELECT c FROM Consulta c WHERE c.id = :id"),
    @NamedQuery(name = "Consulta.findByNomePaciente", query = "SELECT c FROM Consulta c WHERE c.nomePaciente = :nomePaciente"),
    @NamedQuery(name = "Consulta.findByTelefonePaciente", query = "SELECT c FROM Consulta c WHERE c.telefonePaciente = :telefonePaciente"),
    @NamedQuery(name = "Consulta.findByDataHora", query = "SELECT c FROM Consulta c WHERE c.dataHora = :dataHora")})
public class Consulta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nome_paciente")
    private String nomePaciente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "telefone_paciente")
    private String telefonePaciente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    @JoinColumn(name = "id_medico", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Medico idMedico;

    public Consulta() {
    }

    public Consulta(Long id) {
        this.id = id;
    }

    public Consulta(Long id, String nomePaciente, String telefonePaciente, Date dataHora) {
        this.id = id;
        this.nomePaciente = nomePaciente;
        this.telefonePaciente = telefonePaciente;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Medico getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Medico idMedico) {
        this.idMedico = idMedico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consulta)) {
            return false;
        }
        Consulta other = (Consulta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Consulta[ id=" + id + " ]";
    }
    
}
