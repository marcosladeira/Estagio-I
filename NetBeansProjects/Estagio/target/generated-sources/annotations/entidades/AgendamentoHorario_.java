package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AgendamentoHorario.class)
public abstract class AgendamentoHorario_ {

	public static volatile SingularAttribute<AgendamentoHorario, Cliente> cliente;
	public static volatile SingularAttribute<AgendamentoHorario, Long> id;
	public static volatile SingularAttribute<AgendamentoHorario, Date> dataInicial;
	public static volatile SingularAttribute<AgendamentoHorario, Date> dataFinal;
	public static volatile SingularAttribute<AgendamentoHorario, String> descricao;

}

