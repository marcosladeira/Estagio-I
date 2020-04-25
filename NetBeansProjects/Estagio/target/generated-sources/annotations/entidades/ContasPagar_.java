package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContasPagar.class)
public abstract class ContasPagar_ {

	public static volatile SingularAttribute<ContasPagar, Compra> compra;
	public static volatile SingularAttribute<ContasPagar, Date> dataVencimento;
	public static volatile SingularAttribute<ContasPagar, Double> valor;
	public static volatile SingularAttribute<ContasPagar, Long> id;
	public static volatile SingularAttribute<ContasPagar, Fornecedor> fornecedor;
	public static volatile SingularAttribute<ContasPagar, Date> dataLancamento;
	public static volatile ListAttribute<ContasPagar, BaixaContasPagar> baixaContasPagar;
	public static volatile SingularAttribute<ContasPagar, String> numParcelas;
	public static volatile SingularAttribute<ContasPagar, String> descricao;

}

