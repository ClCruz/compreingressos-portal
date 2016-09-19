package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.Contratante;
import br.com.intuiti.compreingressos.portal.model.Estado;
import br.com.intuiti.compreingressos.portal.model.LocalEvento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-19T14:20:40")
@StaticMetamodel(Municipio.class)
public class Municipio_ { 

    public static volatile CollectionAttribute<Municipio, Contratante> contratanteCollection1;
    public static volatile SingularAttribute<Municipio, Estado> idEstado;
    public static volatile CollectionAttribute<Municipio, LocalEvento> localEventoCollection;
    public static volatile SingularAttribute<Municipio, Integer> idMunicipio;
    public static volatile CollectionAttribute<Municipio, Contratante> contratanteCollection;
    public static volatile SingularAttribute<Municipio, String> dsMunicipio;

}