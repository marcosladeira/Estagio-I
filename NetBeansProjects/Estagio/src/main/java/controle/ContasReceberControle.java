/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.BaixaContasReceber;
import entidades.ContasReceber;
import entidades.Cliente;
import facade.ContasReceberFacade;
import facade.ClienteFacade;
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
public class ContasReceberControle implements Serializable {

    @EJB
    private ContasReceberFacade contasReceberFacade;
    @EJB
    private ClienteFacade clienteFacade;
    private ContasReceber cr;
    private ConverterGenerico clienteConverter;
    private BaixaContasReceber baixaContasReceber;
    private MoneyConverter moneyConverter;

    public void baixar(){
        if(cr.getValor()>=(cr.getTotalBaixado()+baixaContasReceber.getValor())){
        baixaContasReceber.setContasReceber(cr);
        cr.getBaixaContasReceber().add(baixaContasReceber);  
        baixaContasReceber = new BaixaContasReceber();
        }else{
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "O valor baixado é maior que o valor do contas a receber!",
                            null));
        }
    }
    
    public void novaBaixa(ContasReceber cr){
        this.cr = cr;
        baixaContasReceber = new BaixaContasReceber();
    }
    
    public void removerBaixa(BaixaContasReceber b){
        cr.getBaixaContasReceber().remove(b);
    }
    
    public String getCorValor(){        
        if(Objects.equals(cr.getTotalBaixado(), cr.getValor())){
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
    

    public BaixaContasReceber getBaixaContasReceber() {
        return baixaContasReceber;
    }

    public void setBaixaContasReceber(BaixaContasReceber baixaContasReceber) {
        this.baixaContasReceber = baixaContasReceber;
    }

    public ConverterGenerico getClienteConverter() {
        if (clienteConverter == null) {
            clienteConverter = new ConverterGenerico(clienteFacade);
        }
        return clienteConverter;
    }

    public void setClienteConverter(ConverterGenerico clienteConverter) {
        this.clienteConverter = clienteConverter;
    }

    public void novo() {
        cr = new ContasReceber();
        cr.setBaixaContasReceber(new ArrayList<BaixaContasReceber>());
    }

    public void salvar() {
        contasReceberFacade.save(cr);
    }

    public void excluir(ContasReceber e) {
        contasReceberFacade.remove(e);
    }

    public void editar(ContasReceber e) {
        cr = e;
    }

    public ContasReceber getCr() {
        return cr;
    }

    public void setCr(ContasReceber cr) {
        this.cr = cr;
    }

    public List<ContasReceber> getLista() {
        return contasReceberFacade.findAll();
    }

    public List<Cliente> getListaClientesFiltrando(String filtro) {
        return clienteFacade.listaFiltrando(filtro, "nome");
    }

}