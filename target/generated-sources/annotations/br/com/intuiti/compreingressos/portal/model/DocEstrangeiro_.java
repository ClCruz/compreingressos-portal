package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.Cliente;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-16T11:05:10")
@StaticMetamodel(DocEstrangeiro.class)
public class DocEstrangeiro_ { 

    public static volatile SingularAttribute<DocEstrangeiro, String> dsDocEstrangeiro;
    public static volatile SingularAttribute<DocEstrangeiro, Integer> idDocEstrangeiro;
    public static volatile CollectionAttribute<DocEstrangeiro, Cliente> clienteCollection;

}