/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Cidade;
import entidades.Fornecedor;
import facade.CidadeFacade;
import facade.FornecedorFacade;
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
public class FornecedorControle implements Serializable{
    
    @EJB
    private FornecedorFacade fornecedorFacade;
    private Fornecedor fornecedor;
    @EJB
    private CidadeFacade cidadeFacade;
    private ConverterGenerico converterCidade;
    
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
    
    
    
    public void salvar(){          
        fornecedorFacade.save(fornecedor);        
    }
    
    public void novo() {
        fornecedor = new Fornecedor();
    }

    public void excluir(Fornecedor e) {
        fornecedorFacade.remove(e);
    }

    public void editar(Fornecedor e) {
        this.fornecedor = e;
    }

    public List<Fornecedor> getLista() {
        return fornecedorFacade.findAll();
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
}
