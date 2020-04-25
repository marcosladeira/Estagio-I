package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BaixaContasPagar.class)
public abstract class BaixaContasPagar_ {

	public static volatile SingularAttribute<BaixaContasPagar, ContasPagar> contasPagar;
	public static volatile SingularAttribute<BaixaContasPagar, Double> valor;
	public static volatile SingularAttribute<BaixaContasPagar, Long> id;
	public static volatile SingularAttribute<BaixaContasPagar, Date> dataBaixa;

}

