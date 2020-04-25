package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Caixa.class)
public abstract class Caixa_ {

	public static volatile SingularAttribute<Caixa, Double> valorFechamento;
	public static volatile SingularAttribute<Caixa, Double> valorAbertura;
	public static volatile SingularAttribute<Caixa, Long> id;
	public static volatile SingularAttribute<Caixa, Date> dataFechamento;
	public static volatile SingularAttribute<Caixa, Status> statusCaixa;
	public static volatile SingularAttribute<Caixa, Date> dataAbertura;

}

