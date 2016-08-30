package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.GeneroEvento;
import br.com.intuiti.compreingressos.portal.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-30T11:27:15")
@StaticMetamodel(SegmentoEvento.class)
public class SegmentoEvento_ { 

    public static volatile SingularAttribute<SegmentoEvento, Boolean> inAtivo;
    public static volatile SingularAttribute<SegmentoEvento, Usuario> idUsuario;
    public static volatile SingularAttribute<SegmentoEvento, String> dsSegmentoEvento;
    public static volatile SingularAttribute<SegmentoEvento, Integer> idSegmentoEvento;
    public static volatile CollectionAttribute<SegmentoEvento, GeneroEvento> generoEventoCollection;

}