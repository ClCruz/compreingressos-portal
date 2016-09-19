package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.Base;
import br.com.intuiti.compreingressos.portal.model.ContratoClienteTipoLancamento;
import br.com.intuiti.compreingressos.portal.model.LocalEvento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-19T14:20:40")
@StaticMetamodel(Evento.class)
public class Evento_ { 

    public static volatile SingularAttribute<Evento, Base> idBase;
    public static volatile SingularAttribute<Evento, Boolean> inAntiFraude;
    public static volatile SingularAttribute<Evento, Integer> idEvento;
    public static volatile SingularAttribute<Evento, Boolean> inAtivo;
    public static volatile SingularAttribute<Evento, String> dsEvento;
    public static volatile CollectionAttribute<Evento, ContratoClienteTipoLancamento> contratoClienteTipoLancamentoCollection;
    public static volatile SingularAttribute<Evento, Character> inVendeItau;
    public static volatile SingularAttribute<Evento, Short> codPeca;
    public static volatile SingularAttribute<Evento, Character> inVerNoBordero;
    public static volatile SingularAttribute<Evento, LocalEvento> idLocalEvento;
    public static volatile SingularAttribute<Evento, Short> qtIngrPorPedido;
    public static volatile SingularAttribute<Evento, Character> inobrigaCPFPos;
    public static volatile SingularAttribute<Evento, Character> inExibeTelaAssinante;
    public static volatile SingularAttribute<Evento, Character> inEntregaIngresso;
    public static volatile SingularAttribute<Evento, Character> inobrigafonePos;
    public static volatile SingularAttribute<Evento, Character> inimprimicanhotoPos;

}