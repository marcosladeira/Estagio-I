/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.MoneyConverter;
import entidades.Servico;
import facade.ServicoFacade;
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
public class ServicoControle implements Serializable{
    
    @EJB
    private ServicoFacade servicoFacade;
    private Servico se;     
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
    
    public void novo(){
        se = new Servico();
    }

    public void salvar(){          
        servicoFacade.save(se);        
    }
    
    public void excluir(Servico e){
        servicoFacade.remove(e);
    }
    
    public void editar(Servico e){        
        se = e;
    }

    public Servico getSe() {
        return se;
    }

    public void setSe(Servico e) {
        this.se = e;
    }

    public List<Servico> getLista(){
        return servicoFacade.findAll();
    }
    
    
}