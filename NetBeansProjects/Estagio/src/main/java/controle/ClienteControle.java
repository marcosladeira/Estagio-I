/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Cidade;
import entidades.Cliente;
import facade.CidadeFacade;
import facade.ClienteFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author marcos-ladeira
 */
@ManagedBean
@SessionScoped
public class ClienteControle implements Serializable{
    
    @EJB
    private ClienteFacade clienteFacade;
    private Cliente cliente;
    private String tipoPessoa="PessoaFisica";
    @EJB
    private CidadeFacade cidadeFacade;
    private ConverterGenerico converterCidade;
    
    public Boolean isPF() {
        if (tipoPessoa.equals("PessoaFisica")) {
            return true;
        }
        return false;
    }

    public Boolean isPJ() {
        if (tipoPessoa.equals("PessoaJuridica")) {
            return true;
        }
        return false;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }
    
      public void salvar() {
        clienteFacade.save(cliente);
    }

    public List<Cidade> listaFiltrando(String parte) {
        return cidadeFacade.listaFiltrando(parte, "nome");
    }

    public ConverterGenerico getConverterCidade() {
        if (converterCidade == null) {
            converterCidade = new ConverterGenerico(cidadeFacade);
        }
        return converterCidade;
    }

    public void setConverterCidade(ConverterGenerico converterCidade) {
        this.converterCidade = converterCidade;
    }

    public void novo() {
        cliente = new Cliente();
    }

    public void excluir(Cliente e) {
        clienteFacade.remove(e);
    }

    public void editar(Cliente e) {
        this.cliente = e;
    }

    public List<Cliente> getLista() {
        return clienteFacade.findAll();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
    
    
     
