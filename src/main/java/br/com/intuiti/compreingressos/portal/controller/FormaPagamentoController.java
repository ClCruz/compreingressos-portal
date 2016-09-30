package br.com.intuiti.compreingressos.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.NamingException;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.intuiti.compreingressos.portal.bean.FormaPagamentoFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.FormaPagamento;

@ManagedBean(name = "formaPagamentoController")
@ViewScoped
public class FormaPagamentoController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private br.com.intuiti.compreingressos.portal.bean.FormaPagamentoFacade ejbFacade;
	private LazyDataModel<FormaPagamento> items = null;
	private FormaPagamento selected;
	private final Map<String, Object> filtros = new HashMap<>();

	public FormaPagamentoController() {
	}

	public FormaPagamento getSelected() {
		return selected;
	}

	public void setSelected(FormaPagamento selected) {
		this.selected = selected;
	}

	protected void setEmbeddableKeys() {
	}

	protected void initializeEmbeddableKey() {
	}

	private FormaPagamentoFacade getFacade() {
		return ejbFacade;
	}

	public FormaPagamento prepareCreate() {
		selected = new FormaPagamento();
		initializeEmbeddableKey();
		return selected;
	}

	public void create() {
		persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle")
				.getString("FormaPagamentoCreated"));
		if (!JsfUtil.isValidationFailed()) {
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public void update() {
		persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle")
				.getString("FormaPagamentoUpdated"));
	}

	public void destroy() {
		persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle")
				.getString("FormaPagamentoDeleted"));
		if (!JsfUtil.isValidationFailed()) {
			selected = null; // Remove selection
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public LazyDataModel<FormaPagamento> getItems() {
		if (items == null) {
			items = new FormaPagamentoLazy(getFacade().findAll(0, 10, null, SortOrder.UNSORTED, filtros));
		}
		return items;
	}

	private void persist(PersistAction persistAction, String successMessage) {
		if (selected != null) {
			setEmbeddableKeys();
			try {
				if (persistAction != PersistAction.DELETE) {
					if (persistAction == PersistAction.CREATE) {
						if (getFacade().findDsMp(
								selected.getDsFormaPagamento(),
								selected.getInTipoMeioPagamento())) {
							getFacade().edit(selected);
							JsfUtil.addSuccessMessage(successMessage);
						} else {
							JsfUtil.addErrorMessage("Já existe uma forma de pagamento com essa descrição e meio de pagamento cadastrado.");
						}
					} else if (persistAction == PersistAction.UPDATE) {
						if (getFacade().findDsMpId(
								selected.getDsFormaPagamento(),
								selected.getInTipoMeioPagamento(),
								selected.getIdFormaPagamento())) {
							getFacade().edit(selected);
							JsfUtil.addSuccessMessage(successMessage);
						} else {
							JsfUtil.addErrorMessage("Já existe uma forma de pagamento com essa descrição e meio de pagamento cadastrado.");
						}
					}
				} else {
					getFacade().remove(selected);
					JsfUtil.addSuccessMessage(successMessage);
				}
			} catch (EJBException ex) {
				String msg = "";
				Throwable cause = ex.getCause();
				if (cause != null) {
					msg = cause.getLocalizedMessage();
				}
				if (msg.length() > 0) {
					JsfUtil.addErrorMessage(msg);
				} else {
					JsfUtil.addErrorMessage(
							ex,
							ResourceBundle.getBundle("/Bundle").getString(
									"PersistenceErrorOccured"));
				}
			} catch (Exception ex) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
						null, ex);
				JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle")
						.getString("PersistenceErrorOccured"));
			}
		}
	}

	public FormaPagamento getFormaPagamento(java.lang.Integer id) {
		return getFacade().find(id);
	}

	public List<FormaPagamento> getItemsAvailableSelectMany() {
		return getFacade().findAll();
	}

	public List<FormaPagamento> getItemsAvailableSelectOne() {
		return getFacade().findAll();
	}
	
    public class FormaPagamentoLazy extends LazyDataModel<FormaPagamento> {
    	
    	private static final long serialVersionUID = 1L;
        private List<FormaPagamento> objList = null;

        public FormaPagamentoLazy(List<FormaPagamento> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<FormaPagamento> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                FormaPagamentoFacade objFacade = (FormaPagamentoFacade) ctx.lookup("java:global/compreingressos-portal/FormaPagamentoFacade!br.com.intuiti.compreingressos.portal.bean.FormaPagamentoFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public FormaPagamento getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (FormaPagamento obj : objList) {
                if (id.equals(obj.getIdFormaPagamento())) {
                    return obj;
                }
            }
            return null;
        }

        @Override
        public Object getRowKey(FormaPagamento ob) {
            return ob.getIdFormaPagamento();
        }
    }

	@FacesConverter(forClass = FormaPagamento.class)
	public static class FormaPagamentoControllerConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext facesContext,
				UIComponent component, String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			FormaPagamentoController controller = (FormaPagamentoController) facesContext
					.getApplication()
					.getELResolver()
					.getValue(facesContext.getELContext(), null,
							"formaPagamentoController");
			return controller.getFormaPagamento(getKey(value));
		}

		java.lang.Integer getKey(String value) {
			java.lang.Integer key;
			key = Integer.valueOf(value);
			return key;
		}

		String getStringKey(java.lang.Integer value) {
			StringBuilder sb = new StringBuilder();
			sb.append(value);
			return sb.toString();
		}

		@Override
		public String getAsString(FacesContext facesContext,
				UIComponent component, Object object) {
			if (object == null) {
				return null;
			}
			if (object instanceof FormaPagamento) {
				FormaPagamento o = (FormaPagamento) object;
				return getStringKey(o.getIdFormaPagamento());
			} else {
				Logger.getLogger(this.getClass().getName()).log(
						Level.SEVERE,
						"object {0} is of type {1}; expected type: {2}",
						new Object[] { object, object.getClass().getName(),
								FormaPagamento.class.getName() });
				return null;
			}
		}

	}

}
