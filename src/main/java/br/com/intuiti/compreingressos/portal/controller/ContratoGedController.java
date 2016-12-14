package br.com.intuiti.compreingressos.portal.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logmanager.Level;
import org.omnifaces.util.Faces;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.intuiti.compreingressos.portal.bean.ContratoGedFacade;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil;
import br.com.intuiti.compreingressos.portal.controller.util.JsfUtil.PersistAction;
import br.com.intuiti.compreingressos.portal.model.ContratoGed;

@ManagedBean(name = "contratoGedController")
@ViewScoped
public class ContratoGedController implements Serializable {
	
	private static final String FILE_PATH = "/compreingressos-portal/uploads/";
	
	private br.com.intuiti.compreingressos.portal.bean.ContratoGedFacade ejbFacade;
	private List<ContratoGed> items = null;
	private ContratoGed selected;
	private FileUpload dsArquivo;
	private StreamedContent file;
	
	public ContratoGedController() {
	}

	public ContratoGed getSelected() {
		return selected;
	}

	public void setSelected(ContratoGed selected) {
		this.selected = selected;
	}

	public FileUpload getDsArquivo() {
		return dsArquivo;
	}

	public void setDsArquivo(FileUpload dsArquivo) {
		this.dsArquivo = dsArquivo;
	}

	protected void setEmbeddableKey() {
	}

	protected void initializeEmbeddableKey() {
	}
	
	public ContratoGedFacade getFacade() {
		return ejbFacade;
	}
	
	public ContratoGed prepareCreate() {
		selected = new ContratoGed();
		initializeEmbeddableKey();
		return selected;
	}
	
	public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleNv").getString("ContratoGedCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleNv").getString("ContratoGedUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleNv").getString("ContratoGedDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
//	Upload de arquivo
    public void upload(FileUploadEvent evento) throws IOException {
    	Date date = new Date();
    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    	HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
    	
    	FacesContext aFacesContext = FacesContext.getCurrentInstance();
    	ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();
    	String fileName = date.getTime() + "-" + "-" + evento.getFile().getFileName();
    	
    	byte[] arq = evento.getFile().getContents();
    	saveFileDisk(evento.getFile(), fileName);
    	selected.setDsArquivo(fileName);
    }
    
    public void saveFileDisk(UploadedFile file, String fileName) throws IOException {
    	String filePath = "/compreingressos-portal/uploads/";
    	InputStream in = file.getInputstream();
    	FileOutputStream out = new FileOutputStream(filePath + fileName);
    	int content;
    	while ((content = in.read()) > -1) {
    		out.write(content);
    	}
    	in.close();
    	out.close();
    }
    
    public InputStream getArquivo() throws FileNotFoundException {
    	return new FileInputStream(new File("/compreingressos-portal/uploads/", selected.getDsArquivo()));
    }
    
    public List<ContratoGed> getItems() {
    	if(items == null) {
    		items = getFacade().findAll();
    	}
    	return items;
    }
    
    private void persist(PersistAction persistAction, String successMessage) {
    	if (selected != null) {
    		setEmbeddableKey();
    		try {
    			if(persistAction != PersistAction.DELETE) {
    				getFacade().edit(selected);
    			} else {
    				getFacade().remove(selected);
    			}
    			JsfUtil.addSuccessMessage(successMessage);
    		} catch (EJBException ex) {
    			String msg = "";
    			Throwable cause = ex.getCause();
    			if (cause != null) {
    				msg = cause.getLocalizedMessage();
    			}
    			if (msg.length() > 0) {
    				JsfUtil.addErrorMessage(msg);
    			} else {
    				JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleNv").getString("PersistenceErrorOccured"));
    			}
    		} catch (Exception ex) {
    			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
    			JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleNv").getString("PersistenceErrorOccured"));
    		}
    	}
    }
    
   public ContratoGed getContratoGed(java.lang.Integer id) {
	   return getFacade().find(id);
   }
   
   public List<ContratoGed> getItemsAvailableSelectMany(){
	   return getFacade().findAll();
   }
    
   public List<ContratoGed> getItemsAvailableSelectOne() {
	   return getFacade().findAll();
   }
   
   public void download(ContratoGed anexo) {
	   try {
		   Faces.sendFile(readFile(anexo.getDsArquivo()), anexo.getDsArquivo(), false);
		   InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(FILE_PATH + anexo.getDsArquivo());
		   file = new DefaultStreamedContent(stream,"image/jpg", anexo.getDsArquivo());
	   } catch (IOException ex) {
		   JsfUtil.addErrorMessage(ex, "Não foi possível fazer download do arquivo " + anexo.getDsArquivo());
	   } catch (Exception ex) {
		   JsfUtil.addErrorMessage(ex, "Não foi possível fazer download do arquivo " + anexo.getDsArquivo());
	   }
   }
   
   public InputStream readFile(String fileName) {
	   InputStream in = null;
	   File file = new File(FILE_PATH + fileName);
	   try {
		   in = new FileInputStream(file);
	   } catch (FileNotFoundException ex) {
		   Logger.getLogger(ContratoGedController.class.getName()).log(Level.SEVERE, null, ex);
	   }
	   return in;
   }
   
   public StreamedContent getFile() {
	   return file;
   }
   
   @FacesConverter(forClass = ContratoGed.class)
   public static class ContratoGedControllerConverter implements Converter {
	   
	   @Override
	   public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
           if (value == null || value.length() == 0) {
               return null;
           }
           ContratoGedController controller = (ContratoGedController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "contratoGedController");
           return controller.getContratoGed(getKey(value));
	   }
	   
	   java.lang.Integer getKey(String value) {
		   java.lang.Integer key;
		   key = Integer.valueOf(value);
		   return key;
	   }
	
	   String getStringKey(java.lang.Integer value){
		   StringBuilder sb = new StringBuilder();
		   sb.append(value);
		   return sb.toString();
	   }
	   
	   @Override
	   public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
		   if (object == null) {
			   return null;
		   }
		   if (object instanceof ContratoGed) {
			   ContratoGed o = (ContratoGed) object;
			   return getStringKey(o.getIdContratoGed());
		   } else {
			   Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}, thrown); expected type {2}", new Object[]{object, object.getClass().getName(), ContratoGed.class.getName()});
			   return null;
		   }
	   }
   }
}
