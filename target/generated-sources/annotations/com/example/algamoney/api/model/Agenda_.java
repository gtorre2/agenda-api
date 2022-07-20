package com.example.algamoney.api.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Agenda.class)
public abstract class Agenda_ {

	public static volatile SingularAttribute<Agenda, Long> codigo;
	public static volatile SingularAttribute<Agenda, String> telefone;
	public static volatile SingularAttribute<Agenda, String> nome;
	public static volatile SingularAttribute<Agenda, LocalDate> dataNascimento;

	public static final String CODIGO = "codigo";
	public static final String TELEFONE = "telefone";
	public static final String NOME = "nome";
	public static final String DATA_NASCIMENTO = "dataNascimento";

}

