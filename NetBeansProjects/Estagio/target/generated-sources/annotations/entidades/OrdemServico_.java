package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrdemServico.class)
public abstract class OrdemServico_ {

	public static volatile SingularAttribute<OrdemServico, Cliente> cliente;
	public static volatile ListAttribute<OrdemServico, ItensServico> itensServico;
	public static volatile ListAttribute<OrdemServico, ItensVenda> itensVenda;
	public static volatile SingularAttribute<OrdemServico, Double> valorTotal;
	public static volatile SingularAttribute<OrdemServico, Date> dataOrdemServico;
	public static volatile ListAttribute<OrdemServico, ContasReceber> contasReceber;
	public static volatile SingularAttribute<OrdemServico, Long> id;

}

