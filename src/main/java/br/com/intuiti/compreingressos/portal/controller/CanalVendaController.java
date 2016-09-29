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

import br.com.intuiti.compreingressos.portal.bean.CanalVendaFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.CanalVenda;

@ManagedBean(name = "canalVendaController")
@ViewScoped
public class CanalVendaController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private br.com.intuiti.compreingressos.portal.bean.CanalVendaFacade ejbFacade;
	private LazyDataModel<CanalVenda> items = null;
	private CanalVenda selected;
	private final Map<String, Object> filtros = new HashMap<>();

	public CanalVendaController() {
	}

	public CanalVenda getSelected() {
		return selected;
	}

	public void setSelected(CanalVenda selected) {
		this.selected = selected;
	}

	protected void setEmbeddableKeys() {
	}

	protected void initializeEmbeddableKey() {
	}

	private CanalVendaFacade getFacade() {
		return ejbFacade;
	}

	public CanalVenda prepareCreate() {
		selected = new CanalVenda();
		initializeEmbeddableKey();
		return selected;
	}

	public void create() {
		persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle")
				.getString("CanalVendaCreated"));
		if (!JsfUtil.isValidationFailed()) {
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public void update() {
		persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle")
				.getString("CanalVendaUpdated"));
	}

	public void destroy() {
		persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle")
				.getString("CanalVendaDeleted"));
		if (!JsfUtil.isValidationFailed()) {
			selected = null; // Remove selection
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public LazyDataModel<CanalVenda> getItems() {
		if (items == null) {
			items = new CanalVendaLazy(getFacade().findAll(0, 10, null, SortOrder.UNSORTED, filtros));
		}
		return items;
	}

	private void persist(PersistAction persistAction, String successMessage) {
		if (selected != null) {
			setEmbeddableKeys();
			try {
				if (persistAction != PersistAction.DELETE) {
					if (persistAction == PersistAction.CREATE) {
						if (getFacade().findDesc(selected.getDsCanalVenda())) {
							getFacade().edit(selected);
							JsfUtil.addSuccessMessage(successMessage);
						} else {
							JsfUtil.addErrorMessage("Já existe um canal de venda cadastrado com essa descrição.");
						}
					} else if (persistAction == PersistAction.UPDATE) {
						if (getFacade().findDescId(selected.getDsCanalVenda(),
								selected.getIdCanalVenda())) {
							getFacade().edit(selected);
							JsfUtil.addSuccessMessage(successMessage);
						} else {
							JsfUtil.addErrorMessage("Já existe um canal de venda cadastrado com essa descrição.");
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

	public CanalVenda getCanalVenda(java.lang.Integer id) {
		return getFacade().find(id);
	}

	public List<CanalVenda> getItemsAvailableSelectMany() {
		return getFacade().findAll();
	}

	public List<CanalVenda> getItemsAvailableSelectOne() {
		return getFacade().findAll();
	}
	
	public class CanalVendaLazy extends LazyDataModel<CanalVenda> {
    	
    	private static final long serialVersionUID = 1L;
        private List<CanalVenda> listaObj = null;

        public CanalVendaLazy(List<CanalVenda> listaObj) {
            this.listaObj = listaObj;
        }

        public List<CanalVenda> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	listaObj = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                CanalVendaFacade objetoFacade = (CanalVendaFacade) ctx.lookup("java:global/compreingressos-portal/CanalVendaFacade!br.com.intuiti.compreingressos.portal.bean.CanalVendaFacade");
                listaObj = objetoFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objetoFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                Logger.getLogger(CanalVenda.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listaObj;
        }

        public CanalVenda getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (CanalVenda list : listaObj) {
                if (id.equals(list.getIdCanalVenda())) {
                    return list;
                }
            }
            return null;
        }

        @Override
        public Object getRowKey(CanalVenda obj) {
            return obj.getIdCanalVenda();
        }

    }

	@FacesConverter(forClass = CanalVenda.class)
	public static class CanalVendaControllerConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext facesContext,
				UIComponent component, String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			CanalVendaController controller = (CanalVendaController) facesContext
					.getApplication()
					.getELResolver()
					.getValue(facesContext.getELContext(), null,
							"canalVendaController");
			return controller.getCanalVenda(getKey(value));
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
			if (object instanceof CanalVenda) {
				CanalVenda o = (CanalVenda) object;
				return getStringKey(o.getIdCanalVenda());
			} else {
				Logger.getLogger(this.getClass().getName()).log(
						Level.SEVERE,
						"object {0} is of type {1}; expected type: {2}",
						new Object[] { object, object.getClass().getName(),
								CanalVenda.class.getName() });
				return null;
			}
		}

	}

}
