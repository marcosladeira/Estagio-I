package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItensServico.class)
public abstract class ItensServico_ {

	public static volatile SingularAttribute<ItensServico, Double> preco;
	public static volatile SingularAttribute<ItensServico, Long> id;
	public static volatile SingularAttribute<ItensServico, Servico> servico;
	public static volatile SingularAttribute<ItensServico, OrdemServico> ordemServico;

}

