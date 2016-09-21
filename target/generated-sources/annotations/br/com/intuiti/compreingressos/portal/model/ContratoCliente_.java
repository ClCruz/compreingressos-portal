package br.com.intuiti.compreingressos.portal.model;

import br.com.intuiti.compreingressos.portal.model.Contratante;
import br.com.intuiti.compreingressos.portal.model.ContratoClientePrazoPagamento;
import br.com.intuiti.compreingressos.portal.model.ContratoClienteTipoLancamento;
import br.com.intuiti.compreingressos.portal.model.Empresa;
import br.com.intuiti.compreingressos.portal.model.LocalEvento;
import br.com.intuiti.compreingressos.portal.model.ModalidadeContrato;
import br.com.intuiti.compreingressos.portal.model.SegmentoEvento;
import br.com.intuiti.compreingressos.portal.model.TipoContrato;
import br.com.intuiti.compreingressos.portal.model.Usuario;
import br.com.intuiti.compreingressos.portal.model.Vendedor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-21T09:29:23")
@StaticMetamodel(ContratoCliente.class)
public class ContratoCliente_ { 

    public static volatile SingularAttribute<ContratoCliente, String> dsObsAprovador;
    public static volatile SingularAttribute<ContratoCliente, String> dsHorarioBilheteria;
    public static volatile SingularAttribute<ContratoCliente, Usuario> idUsuarioAprovador;
    public static volatile CollectionAttribute<ContratoCliente, ContratoClienteTipoLancamento> contratoClienteTipoLancamentoCollection;
    public static volatile SingularAttribute<ContratoCliente, Contratante> idContratante;
    public static volatile SingularAttribute<ContratoCliente, String> dsHorarioCallcenter;
    public static volatile SingularAttribute<ContratoCliente, Vendedor> idVendedor;
    public static volatile SingularAttribute<ContratoCliente, Date> dtInativacao;
    public static volatile SingularAttribute<ContratoCliente, String> dsResumida;
    public static volatile SingularAttribute<ContratoCliente, String> dsMotivoInativacao;
    public static volatile SingularAttribute<ContratoCliente, Empresa> idEmpresa;
    public static volatile SingularAttribute<ContratoCliente, Date> dtTerminoVigencia;
    public static volatile SingularAttribute<ContratoCliente, String> cdSiglaProjeto;
    public static volatile SingularAttribute<ContratoCliente, Usuario> idUsuarioSolicitante;
    public static volatile SingularAttribute<ContratoCliente, Date> dtAssinaturaContrato;
    public static volatile CollectionAttribute<ContratoCliente, ContratoClientePrazoPagamento> contratoClientePrazoPagamentoCollection;
    public static volatile SingularAttribute<ContratoCliente, SegmentoEvento> idSegmentoEvento;
    public static volatile SingularAttribute<ContratoCliente, String> icStatusContrato;
    public static volatile SingularAttribute<ContratoCliente, TipoContrato> idTipoContrato;
    public static volatile SingularAttribute<ContratoCliente, String> dsObsVendedor;
    public static volatile SingularAttribute<ContratoCliente, LocalEvento> idLocalEvento;
    public static volatile SingularAttribute<ContratoCliente, Date> dtInicioVigencia;
    public static volatile SingularAttribute<ContratoCliente, Date> dtAprovacao;
    public static volatile SingularAttribute<ContratoCliente, Integer> idContratoCliente;
    public static volatile SingularAttribute<ContratoCliente, ModalidadeContrato> idModalidadeContrato;

}