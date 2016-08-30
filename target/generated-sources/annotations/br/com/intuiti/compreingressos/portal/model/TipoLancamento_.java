package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.CanalVenda;
import br.com.intuiti.compreingressos.portal.model.ContaContabil;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-30T11:27:15")
@StaticMetamodel(TipoLancamento.class)
public class TipoLancamento_ { 

    public static volatile SingularAttribute<TipoLancamento, Character> inAplicacaoRegra;
    public static volatile SingularAttribute<TipoLancamento, Integer> idTipoLancamento;
    public static volatile SingularAttribute<TipoLancamento, ContaContabil> idContaContabilCre;
    public static volatile SingularAttribute<TipoLancamento, Boolean> inAtivo;
    public static volatile SingularAttribute<TipoLancamento, String> inAcrescentaSubstrai;
    public static volatile SingularAttribute<TipoLancamento, Boolean> inValidoExtrato;
    public static volatile SingularAttribute<TipoLancamento, String> dsTipoLancamento;
    public static volatile SingularAttribute<TipoLancamento, CanalVenda> idCanalVenda;
    public static volatile SingularAttribute<TipoLancamento, String> inPcValor;
    public static volatile SingularAttribute<TipoLancamento, Date> dtUpdate;
    public static volatile SingularAttribute<TipoLancamento, Date> dtInsert;
    public static volatile SingularAttribute<TipoLancamento, ContaContabil> idContaContabilDeb;
    public static volatile SingularAttribute<TipoLancamento, Boolean> inValidoBordero;
    public static volatile SingularAttribute<TipoLancamento, Integer> idFormaPagamento;

}