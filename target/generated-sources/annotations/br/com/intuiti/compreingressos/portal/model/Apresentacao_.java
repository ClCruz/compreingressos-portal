package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.Evento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-21T09:29:23")
@StaticMetamodel(Apresentacao.class)
public class Apresentacao_ { 

    public static volatile SingularAttribute<Apresentacao, Integer> idApresentacao;
    public static volatile SingularAttribute<Apresentacao, Integer> codApresentacao;
    public static volatile SingularAttribute<Apresentacao, String> hrApresentacao;
    public static volatile SingularAttribute<Apresentacao, Boolean> inAtivo;
    public static volatile SingularAttribute<Apresentacao, Evento> idEvento;
    public static volatile SingularAttribute<Apresentacao, Date> dtApresentacao;
    public static volatile SingularAttribute<Apresentacao, String> dsPiso;

}