/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Cidade;
import entidades.Estado;
import facade.CidadeFacade;
import facade.EstadoFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author marcos-ladeira
 */
@ManagedBean
@SessionScoped
public class CidadeControle implements Serializable{
    
    @EJB
    private CidadeFacade cidadeFacade;
    @EJB
    private EstadoFacade estadoFacade;
    private Cidade cid;     
    private ConverterGenerico estadoConverter;

    public ConverterGenerico getEstadoConverter() {
        if(estadoConverter == null){
            estadoConverter = new ConverterGenerico(estadoFacade);
        }
        return estadoConverter;
    }

    public void setEstadoConverter(ConverterGenerico estadoConverter) {
        this.estadoConverter = estadoConverter;
    }
    
    public void novo(){
        cid = new Cidade();
    }

    public void salvar(){          
        cidadeFacade.save(cid);        
    }
    
    public void excluir(Cidade e){
        cidadeFacade.remove(e);
    }
    
    public void editar(Cidade e){        
        cid = e;
    }

    public Cidade getCid() {
        if(cid == null){
         
               cid = new Cidade();
        }
        return cid;
    }

    public void setCid(Cidade cid) {
        this.cid = cid;
    }

    public List<Cidade> getLista(){
        return cidadeFacade.findAll();
    }
    
    public List<Estado> getListaEstadosFiltrando(String filtro){
        return estadoFacade.listaFiltrando(filtro, "nome", "sigla");
    }

    
}
