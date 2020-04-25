package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Perfil.class)
public abstract class Perfil_ {

	public static volatile ListAttribute<Perfil, Permissoes> permissoes;
	public static volatile SingularAttribute<Perfil, String> nome;
	public static volatile SingularAttribute<Perfil, Long> id;

}

