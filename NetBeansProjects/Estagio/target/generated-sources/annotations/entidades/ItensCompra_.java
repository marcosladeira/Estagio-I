package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItensCompra.class)
public abstract class ItensCompra_ {

	public static volatile SingularAttribute<ItensCompra, Double> preco;
	public static volatile SingularAttribute<ItensCompra, Compra> compra;
	public static volatile SingularAttribute<ItensCompra, Produto> produto;
	public static volatile SingularAttribute<ItensCompra, Long> id;
	public static volatile SingularAttribute<ItensCompra, Double> quantidade;

}

