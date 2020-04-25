/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;
import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.BaixaContasPagar;
import entidades.ContasPagar;
import entidades.Fornecedor;
import facade.ContasPagarFacade;
import facade.FornecedorFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author marcos-ladeira
 */
@ManagedBean
@SessionScoped
public class ContasPagarControle implements Serializable {

    @EJB
    private ContasPagarFacade contasPagarFacade;
    @EJB
    private FornecedorFacade fornecedorFacade;
    private ContasPagar cp;
    private ConverterGenerico fornecedorConverter;
    private BaixaContasPagar baixaContasPagar;
    private MoneyConverter moneyConverter;

    public void baixar(){
        if(cp.getValor()>=(cp.getTotalBaixado()+baixaContasPagar.getValor())){
        baixaContasPagar.setContasPagar(cp);
        cp.getBaixaContasPagar().add(baixaContasPagar);  
        baixaContasPagar = new BaixaContasPagar();
        }else{
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "O valor baixado é maior que o valor do contas a pagar!",
                            null));
        }
    }
    
    public void novaBaixa(ContasPagar cp){
        this.cp = cp;
        baixaContasPagar = new BaixaContasPagar();
    }
    
    public void removerBaixa(BaixaContasPagar b){
        cp.getBaixaContasPagar().remove(b);
    }
    
    public String getCorValor(){        
        if(Objects.equals(cp.getTotalBaixado(), cp.getValor())){
            return "green";
        }else{
            return "red";
        }
    }
    
    public MoneyConverter getMoneyConverter() {
        if(moneyConverter == null){
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }
    

    public BaixaContasPagar getBaixaContasPagar() {
        return baixaContasPagar;
    }

    public void setBaixaContasPagar(BaixaContasPagar baixaContasPagar) {
        this.baixaContasPagar = baixaContasPagar;
    }

    public ConverterGenerico getFornecedorConverter() {
        if (fornecedorConverter == null) {
            fornecedorConverter = new ConverterGenerico(fornecedorFacade);
        }
        return fornecedorConverter;
    }

    public void setFornecedorConverter(ConverterGenerico fornecedorConverter) {
        this.fornecedorConverter = fornecedorConverter;
    }

    public void novo() {
        cp = new ContasPagar();
        cp.setBaixaContasPagar(new ArrayList<BaixaContasPagar>());
    }

    public void salvar() {
        contasPagarFacade.save(cp);
    }

    public void excluir(ContasPagar e) {
        contasPagarFacade.remove(e);
    }

    public void editar(ContasPagar e) {
        cp = e;
    }

    public ContasPagar getCp() {
        return cp;
    }

    public void setCp(ContasPagar cp) {
        this.cp = cp;
    }

    public List<ContasPagar> getLista() {
        return contasPagarFacade.findAll();
    }

    public List<Fornecedor> getListaFornecedorsFiltrando(String filtro) {
        return fornecedorFacade.listaFiltrando(filtro, "nome");
    }

}
