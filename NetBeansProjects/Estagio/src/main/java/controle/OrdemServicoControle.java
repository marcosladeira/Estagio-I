/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.Cliente;
import entidades.ContasReceber;
import entidades.ItensVenda;
import entidades.ItensServico;
import entidades.OrdemServico;
import entidades.Produto;
import entidades.Servico;
import facade.ClienteFacade;
import facade.OrdemServicoFacade;
import facade.ProdutoFacade;
import facade.ServicoFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
public class OrdemServicoControle implements Serializable {

    @EJB
    private OrdemServicoFacade ordemServicoFacade;
    @EJB
    private ClienteFacade clienteFacade;
    @EJB    
    private ServicoFacade servicoFacade;
    @EJB
    private ProdutoFacade produtoFacade;
    private OrdemServico os;
    private ItensServico itensServico;
    private ItensVenda itensVenda;
    private ConverterGenerico clienteConverter;
    private ConverterGenerico servicoConverter;
    private ConverterGenerico produtoConverter;
    private MoneyConverter moneyConverter;
    private Boolean osTelaAgenda = false;
    private Integer numeroParcelas;
    
    public void addServico(){
        itensServico.setOrdemServico(os);
        os.getItensServico().add(itensServico);
        itensServico = new ItensServico();
    }
    
    public void removeServico(ItensServico it) {
        os.getItensServico().remove(it);
    }
    
    
     public void addProduto() {
        if (itensVenda.getQuantidade() == null || itensVenda.getPreco() == null) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Preencha todos os campos!",
                            null));
        } else {
            int estoque = itensVenda.getProduto().getEstoque();
            ItensVenda itemTemp = null;
            for (ItensVenda it : os.getItensVenda()) {
                if (it.getProduto().equals(itensVenda.getProduto())) {
                    itemTemp = it;
                    estoque = (int) (estoque - it.getQuantidade());
                }
            }
            if ((estoque - itensVenda.getQuantidade()) < 0) {
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Estoque insuficiente!",
                                "Restam apenas "
                                + estoque
                                + " unidades."));
            } else {
                if (itemTemp == null) {
                    itensVenda.setOrdemServico(os);
                    os.getItensVenda().add(itensVenda);
                } else {
                    itemTemp.setQuantidade(itemTemp.getQuantidade() + itensVenda.getQuantidade());
                }
                itensVenda = new ItensVenda();
            }
        }
    }

    public void removeProd(ItensVenda it) {
        os.getItensVenda().remove(it);
    }
   
    public void gerarParcelas() {
        os.setContasReceber(new ArrayList<ContasReceber>());
        Double valor = os.getValorTotal() / numeroParcelas;
        Date dataVen = os.getDataOrdemServico();
        for (Integer i = 1; i <= numeroParcelas; i++) {
            ContasReceber cr = new ContasReceber();
            cr.setDataLancamento(os.getDataOrdemServico());
            cr.setDescricao(os.getCliente().getNome());
            cr.setNumParcelas(i.toString() + "/" + numeroParcelas.toString());
            cr.setValor(valor);
            cr.setDataVencimento(dataVen);
            cr.setOrdemServico(os);
            if(os.getCliente() != null){
                cr.setCliente(os.getCliente());
            }            
            os.getContasReceber().add(cr);
            //Soma 1 mês no oscimento
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataVen);
            cal.add(Calendar.MONTH, 1);
            dataVen = cal.getTime();
        }
    }
    
     public void editarEventoAgenda() {
        ordemServicoFacade.carregaItensServico(os);
        ordemServicoFacade.carregaItensProduto(os);
        ordemServicoFacade.carregaContaReceberOS(os);
        itensServico = new ItensServico();
        itensVenda = new ItensVenda();
        setOsTelaAgenda(Boolean.TRUE);
    }
    
     public ItensVenda getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(ItensVenda itensVenda) {
        this.itensVenda = itensVenda;
    }
    
     public ItensServico getItensServico() {
        return itensServico;
    }

    public void setItensServico(ItensServico itensServico) {
        this.itensServico = itensServico;
    }
    
    
    public Boolean getOsTelaAgenda() {
        return osTelaAgenda;
    }

    public void setOsTelaAgenda(Boolean osTelaAgenda) {
        this.osTelaAgenda = osTelaAgenda;
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

     public ConverterGenerico getServicoConverter() {
        if (servicoConverter == null) {
            servicoConverter = new ConverterGenerico(servicoFacade);
        }
        return servicoConverter;
    }
      public void setServicoConverter(ConverterGenerico servicoConverter) {
        this.servicoConverter = servicoConverter;
    }
     
    public ConverterGenerico getProdutoConverter() {
        if (produtoConverter == null) {
            produtoConverter = new ConverterGenerico(produtoFacade);
        }
        return produtoConverter;
    }

    public void setProdutoConverter(ConverterGenerico produtoConverter) {
        this.produtoConverter = produtoConverter;
    }

    public MoneyConverter getMoneyConverter() {
        if (moneyConverter == null) {
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }
    
     public void atualizaPrecoServico() {
        itensServico.setPreco(itensServico.getServico().getPreco());
    }

    public void atualizaPrecoProduto() {
        itensVenda.setPreco(itensVenda.getProduto().getPrecoVenda());
    }
    
     public void novo() {
        os = new OrdemServico();
        itensServico = new ItensServico();
        itensVenda = new ItensVenda();
        numeroParcelas = 1;
        os.setContasReceber(new ArrayList<ContasReceber>());
    }
     
       public void salvar() {
        ordemServicoFacade.save(os);
    }

    public void excluir(OrdemServico e) {
        ordemServicoFacade.remove(e);
    }

    public void editar(OrdemServico e) {
        os = e;
    }

    public OrdemServico getOs() {
        return os;
    }

    public void setOs(OrdemServico os) {
        this.os = os;
    }

    public List<OrdemServico> getLista() {
        return ordemServicoFacade.findAll();
    }

    public List<Cliente> getListaClientesFiltrando(String filtro) {
        return clienteFacade.listaFiltrando(filtro, "nome", "cpfCnpj");
    }

    public List<Produto> getListaProdutosFiltrando(String filtro) {
        return produtoFacade.listaFiltrando(filtro, "nome");
    }
    
     public List<Servico> getListaServicosFiltrando(String filtro) {
        return servicoFacade.listaFiltrando(filtro, "nome");
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }


}
