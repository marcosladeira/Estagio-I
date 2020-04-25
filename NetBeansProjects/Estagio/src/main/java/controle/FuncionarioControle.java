/*
 * To change this licenfun header, choofun Licenfun Headers in Project Properties.
 * To change this template file, choofun Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.Funcao;
import entidades.Funcionario;
import facade.FuncaoFacade;
import facade.FuncionarioFacade;
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
public class FuncionarioControle implements Serializable {
    @EJB
    private FuncionarioFacade funcionarioFacade;
    @EJB 
    private FuncaoFacade funcaoFacade;
    private Funcionario fun;  
    private ConverterGenerico funcaoConverter;
    private MoneyConverter moneyConverter;

    
    public ConverterGenerico getFuncaoConverter() {
        if(funcaoConverter == null){
            funcaoConverter = new ConverterGenerico(funcaoFacade);
        }
        return funcaoConverter;
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
    
    public void novo(){
        fun = new Funcionario();
    }

    public void salvar(){          
        funcionarioFacade.save(fun);        
    }
    
    public void excluir(Funcionario e){
        funcionarioFacade.remove(e);
    }
    
    public void editar(Funcionario e){        
        fun = e;
    }

    public Funcionario getFun() {
        return fun;
    }

    public void setFun(Funcionario e) {
        this.fun = e;
    }

    public List<Funcionario> getLista(){
        return funcionarioFacade.findAll();
    }
    
    public List<Funcao> getListaFuncaoFiltrando(String filtro){
        return funcaoFacade.listaFiltrando(filtro, "nome");
    }

    
}
