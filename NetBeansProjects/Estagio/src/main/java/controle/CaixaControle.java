/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.MoneyConverter;
import entidades.Caixa;
import entidades.Status;
import facade.CaixaFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent; 

/**
 *
 * @author Camila Padilha
 */
@ManagedBean
@SessionScoped
public class CaixaControle implements Serializable {

    @EJB
    private CaixaFacade caixaFacade;

    private Caixa caixa;
    private MoneyConverter moneyConverter;
    private boolean desabilitarBotaoAbrir = false;
    private boolean desabilitarBotaoFechar = true;
    
    public void novo() {
        caixa = new Caixa();
//        caixa.setContasPagars(new ArrayList<ContasPagar>());
//        caixa.setContasReceber(new ArrayList<ContasReceber>());
        caixa.setStatusCaixa(Status.Aberto);
    }

//    public void colunaSelecionada(SelectEvent event) {
//        if (((Caixa) event.getObject()).getDataFechamento() == null) {
//            desabilitarBotaoAbrir = true;
//            desabilitarBotaoFechar = false;
//        }
//        else{
//            desabilitarBotaoAbrir = false;
//            desabilitarBotaoFechar = true;
//        }
//    }  
    
    public void carregarCaixaAberto() {
        if (caixaFacade.buscarCaixaAberto() != null) {
            caixa = caixaFacade.buscarCaixaAberto();
            desabilitarBotaoAbrir = true;
            desabilitarBotaoFechar = false;
            caixa.setStatusCaixa(Status.Aberto);
        }
        else{
            desabilitarBotaoAbrir = false;
            desabilitarBotaoFechar = true;
        }
     
    }

    public void fecharCaixa() {
        Caixa caixa = caixaFacade.buscarCaixaAberto();
        Double valorCr = caixaFacade.buscaContasReceber(caixa);
        Double valorCp = caixaFacade.buscaContasPagar(caixa);
        desabilitarBotaoAbrir = false;
        desabilitarBotaoFechar = true;
        
        Date dataAtualDoSistema = new Date();
        
        caixa.setDataFechamento(dataAtualDoSistema);
        caixa.setStatusCaixa(Status.Fechado);
        if(valorCp == null){
          valorCp = 0.0;
        }
        if(valorCr == null){
            valorCr = 0.0;
        }
        caixa.setValorFechamento(caixa.getValorAbertura() + valorCr - valorCp);
        caixaFacade.salvarRetornando(caixa);
    }

    public void salvar() {
       
       caixa = caixaFacade.salvarRetornando(caixa);
        desabilitarBotaoAbrir = true;
        desabilitarBotaoFechar = false;
        caixaFacade.save(caixa);
    }
//    public void salvarCaixaMovimentado() {
//        caixa = caixaFacade.salvarRetornando(caixa);
//    }

    public List<Caixa> listaCaixa() {
        return caixaFacade.listaTodos();
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

    public Caixa getCaixa() {
        if(caixa == null){
            caixa = new Caixa();
        }
        
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    public boolean isDesabilitarBotaoAbrir() {
        return desabilitarBotaoAbrir;
    }

    public void setDesabilitarBotaoAbrir(boolean desabilitarBotaoAbrir) {
        this.desabilitarBotaoAbrir = desabilitarBotaoAbrir;
    }

    public boolean isDesabilitarBotaoFechar() {
        return desabilitarBotaoFechar;
    }

    public void setDesabilitarBotaoFechar(boolean desabilitarBotaoFechar) {
        this.desabilitarBotaoFechar = desabilitarBotaoFechar;
    }

    

}