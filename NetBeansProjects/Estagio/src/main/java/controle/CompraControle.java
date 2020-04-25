        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.Compra;
import entidades.Fornecedor;
import entidades.ContasPagar;
import entidades.ItensCompra;
import entidades.Produto;
import facade.CompraFacade;
import facade.FornecedorFacade;
import facade.ProdutoFacade;
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
public class CompraControle implements Serializable {

    @EJB
    private CompraFacade compraFacade;
    @EJB
    private FornecedorFacade fornecedorFacade;
    @EJB
    private ProdutoFacade produtoFacade;
    private Compra comp;
    private ItensCompra itensCompra;
    private ConverterGenerico fornecedorConverter;
    private ConverterGenerico produtoConverter;
    private MoneyConverter moneyConverter;
    private Integer numeroParcelas;

    public void addProduto() {
        if (itensCompra.getQuantidade() == null || itensCompra.getPreco() == null) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Preencha todos os campos!",
                            null));
        } else {
            int estoque = itensCompra.getProduto().getEstoque();
            ItensCompra itemTemp = null;
            for (ItensCompra it : comp.getItensCompra()) {
                if (it.getProduto().equals(itensCompra.getProduto())) {
                    itemTemp = it;
                    estoque = (int) (estoque + it.getQuantidade());
                }
            }
            
                if (itemTemp == null) {
                    itensCompra.setCompra(comp);
                    comp.getItensCompra().add(itensCompra);
                } else {
                    itemTemp.setQuantidade(itemTemp.getQuantidade() + itensCompra.getQuantidade());
                }
                itensCompra = new ItensCompra();
            }
        }
    

    public void removeProd(ItensCompra it) {
        comp.getItensCompra().remove(it);
    }

    public void gerarParcelas() {
        comp.setContasPagar(new ArrayList<ContasPagar>());
        Double valor = comp.getValorTotal() / numeroParcelas;
        Date dataComp = comp.getDataCompra();
        for (Integer i = 1; i <= numeroParcelas; i++) {
            ContasPagar cp = new ContasPagar();
            cp.setDataLancamento(comp.getDataCompra());
            cp.setDescricao(comp.getFornecedor().getNome());
            cp.setNumParcelas(i.toString() + "/" + numeroParcelas.toString());
            cp.setValor(valor);
            cp.setDataVencimento(dataComp);
            cp.setCompra(comp);
            if(comp.getFornecedor() != null){
                cp.setFornecedor(comp.getFornecedor());
            }            
            comp.getContasPagar().add(cp);
            //Soma 1 mês no compcimento
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataComp);
            cal.add(Calendar.MONTH, 1);
            dataComp = cal.getTime();
        }
    }

    public ItensCompra getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(ItensCompra itensCompra) {
        this.itensCompra = itensCompra;
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

    public void atualizaPrecoProduto() {
        itensCompra.setPreco(itensCompra.getProduto().getPrecoCompra());
    }

    public void novo() {
        comp = new Compra();
        itensCompra = new ItensCompra();
        numeroParcelas = 1;
        comp.setContasPagar(new ArrayList<ContasPagar>());
    }

    public void salvar() {
        compraFacade.save(comp);
    }

    public void excluir(Compra e) {
        compraFacade.remove(e);
    }

    public void editar(Compra e) {
        comp = e;
    }

    public Compra getComp() {
        return comp;
    }

    public void setComp(Compra comp) {
        this.comp = comp;
    }

    public List<Compra> getLista() {
        return compraFacade.findAll();
    }

    public List<Fornecedor> getListaFornecedorsFiltrando(String filtro) {
        return fornecedorFacade.listaFiltrando(filtro, "nome", "cnpj");
    }

    public List<Produto> getListaProdutosFiltrando(String filtro) {
        return produtoFacade.listaFiltrando(filtro, "nome");
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

}