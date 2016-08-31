package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.Contratante;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-31T11:44:52")
@StaticMetamodel(Banco.class)
public class Banco_ { 

    public static volatile SingularAttribute<Banco, String> nmBanco;
    public static volatile SingularAttribute<Banco, Integer> idBanco;
    public static volatile SingularAttribute<Banco, String> cdBanco;
    public static volatile SingularAttribute<Banco, Boolean> inAtivo;
    public static volatile CollectionAttribute<Banco, Contratante> contratanteCollection;

}