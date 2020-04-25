/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.Produto;
import entidades.GrupoProduto;
import facade.ProdutoFacade;
import facade.GrupoProdutoFacade;
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
public class ProdutoControle implements Serializable{
    
    @EJB
    private ProdutoFacade produtoFacade;
    @EJB
    private GrupoProdutoFacade grupoProdutoFacade;
    private Produto prod;     
    private ConverterGenerico grupoProdutoConverter;
    private MoneyConverter moneyConverter;

    public MoneyConverter getMoneyConverter() {
        if(moneyConverter == null){
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }

    public ConverterGenerico getGrupoProdutoConverter() {
        if(grupoProdutoConverter == null){
            grupoProdutoConverter = new ConverterGenerico(grupoProdutoFacade);
        }
        return grupoProdutoConverter;
    }

    public void setGrupoProdutoConverter(ConverterGenerico grupoProdutoConverter) {
        this.grupoProdutoConverter = grupoProdutoConverter;
    }
    
    public void novo(){
        prod = new Produto();
    }

    public void salvar(){          
        produtoFacade.save(prod);        
    }
    
    public void excluir(Produto p){
        produtoFacade.remove(p);
    }
    
    public void editar(Produto p){        
        prod = p;
    }

    public Produto getProd() {
        return prod;
    }

    public void setProd(Produto prod) {
        this.prod = prod;
    }

    public List<Produto> getLista(){
        return produtoFacade.findAll();
    }
    
    public List<GrupoProduto> getListaFiltrando(String filtro){
        return grupoProdutoFacade.listaFiltrando(filtro, "descricao");
    }

    
}