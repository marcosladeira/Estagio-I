package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContasReceber.class)
public abstract class ContasReceber_ {

	public static volatile SingularAttribute<ContasReceber, Cliente> cliente;
	public static volatile SingularAttribute<ContasReceber, Date> dataVencimento;
	public static volatile SingularAttribute<ContasReceber, Double> valor;
	public static volatile ListAttribute<ContasReceber, BaixaContasReceber> baixaContasReceber;
	public static volatile SingularAttribute<ContasReceber, Long> id;
	public static volatile SingularAttribute<ContasReceber, OrdemServico> ordemServico;
	public static volatile SingularAttribute<ContasReceber, Date> dataLancamento;
	public static volatile SingularAttribute<ContasReceber, String> numParcelas;
	public static volatile SingularAttribute<ContasReceber, String> descricao;

}

