/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author marcos-ladeira
 */
@Entity
public class OrdemServico implements Serializable, ClassePai {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dataOrdemServico;
    @ManyToOne
    private Cliente cliente;
    private Double valorTotal;
    @OneToMany(cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, 
            mappedBy = "ordemServico", 
            orphanRemoval = true)
    private List<ItensServico> itensServico;
    @OneToMany(cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, 
            mappedBy = "ordemServico", 
            orphanRemoval = true)
    private List<ItensVenda> itensVenda;
    @OneToMany(cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, 
            mappedBy = "ordemServico", 
            orphanRemoval = true)
    private List<ContasReceber> contasReceber;
    
    public OrdemServico(){
        itensServico = new ArrayList<ItensServico>();
        itensVenda = new ArrayList<ItensVenda>();
        dataOrdemServico = new Date();
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataOrdemServico() {
        return dataOrdemServico;
    }

    public void setDataOrdemServico(Date dataOrdemServico) {
        this.dataOrdemServico = dataOrdemServico;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

   public Double getValorTotal() {
        valorTotal = 0d;
        for (ItensServico is: itensServico){
            valorTotal = valorTotal + is.getPreco();
        }
        for (ItensVenda  iv: itensVenda){
            valorTotal = valorTotal + iv.getSubTotal();
        }
        return valorTotal;
   }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItensServico> getItensServico() {
        return itensServico;
    }

    public void setItensServico(List<ItensServico> itensServico) {
        this.itensServico = itensServico;
    }

    public List<ItensVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItensVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public List<ContasReceber> getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(List<ContasReceber> contasReceber) {
        this.contasReceber = contasReceber;
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
        if (!(object instanceof OrdemServico)) {
            return false;
        }
        OrdemServico other = (OrdemServico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.OrdemServico[ id=" + id + " ]";
    }

    
}
