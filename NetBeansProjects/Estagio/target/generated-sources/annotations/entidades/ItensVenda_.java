package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItensVenda.class)
public abstract class ItensVenda_ {

	public static volatile SingularAttribute<ItensVenda, Double> preco;
	public static volatile SingularAttribute<ItensVenda, Produto> produto;
	public static volatile SingularAttribute<ItensVenda, Long> id;
	public static volatile SingularAttribute<ItensVenda, OrdemServico> ordemServico;
	public static volatile SingularAttribute<ItensVenda, Double> quantidade;

}

