package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.ContratoCliente;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-29T12:56:06")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile SingularAttribute<Empresa, Boolean> inAtivo;
    public static volatile SingularAttribute<Empresa, Integer> idEmpresa;
    public static volatile SingularAttribute<Empresa, String> dsEmpresa;
    public static volatile CollectionAttribute<Empresa, ContratoCliente> contratoClienteCollection;

}