package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.ContratoCliente;
import br.com.intuiti.compreingressos.portal.model.Evento;
import br.com.intuiti.compreingressos.portal.model.ModalidadeCobranca;
import br.com.intuiti.compreingressos.portal.model.TipoLancamento;
import br.com.intuiti.compreingressos.portal.model.Usuario;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-19T14:20:40")
@StaticMetamodel(ContratoClienteTipoLancamento.class)
public class ContratoClienteTipoLancamento_ { 

    public static volatile SingularAttribute<ContratoClienteTipoLancamento, TipoLancamento> idTipoLancamento;
    public static volatile SingularAttribute<ContratoClienteTipoLancamento, Date> dtVigencia;
    public static volatile SingularAttribute<ContratoClienteTipoLancamento, Evento> idEvento;
    public static volatile SingularAttribute<ContratoClienteTipoLancamento, Date> dtInativacao;
    public static volatile SingularAttribute<ContratoClienteTipoLancamento, BigDecimal> vlMinimoTipoLancamento;
    public static volatile SingularAttribute<ContratoClienteTipoLancamento, Usuario> idUsuarioInsert;
    public static volatile SingularAttribute<ContratoClienteTipoLancamento, Usuario> idUsuarioInativacao;
    public static volatile SingularAttribute<ContratoClienteTipoLancamento, Integer> idContratoClienteTipoLancamento;
    public static volatile SingularAttribute<ContratoClienteTipoLancamento, BigDecimal> vlAplicacaoTipoLancamento;
    public static volatile SingularAttribute<ContratoClienteTipoLancamento, ModalidadeCobranca> idModalidadeCobranca;
    public static volatile SingularAttribute<ContratoClienteTipoLancamento, ContratoCliente> idContratoCliente;

}