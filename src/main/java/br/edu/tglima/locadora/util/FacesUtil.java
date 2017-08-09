package br.edu.tglima.locadora.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Esta classe implementa métodos para que o sistema envie mensagens para o usuário. 
 * <br/><br/>
 * As mensagens enviadas podem ser classificadas em quatro grupos diferentes, são eles:
 * <br/> 
 * <b>Alerta, Erro, Sucesso e Fatal Error.</b><br/><br/>
 * 
 * Esta classe utiliza o FacesContext para realizar o envio das mensagens. 
 * 
 * @author tglima
 */
public class FacesUtil {

	/**
	 * Envia uma mensagem de caráter de erro ou falha ao usuário.
	 * 
	 * @param clientId Indique o ID do elemento para que a mensagem seja direcionada para ele. 
	 * Caso o parametro seja null, a mensagem será global.<br/><br/>
	 * 
	 * @param detail Mensagem que detalhada do ocorrido.
	 */
	public final static void enviarMsgErro(String clientId, String detail){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, detail);
		FacesContext.getCurrentInstance().addMessage(clientId, message);
	}
	/**
	 * Envia uma mensagem de caráter de informativo ou de sucesso ao usuário.
	 *  
	 * @param clientId Indique o ID do elemento para que a mensagem seja direcionada para ele. 
	 * Caso o parametro seja null, a mensagem será global.<br/><br/>
	 * 
	 * @param detail Mensagem que detalhada do ocorrido.
	 */
	public final static void enviarMsgSucesso(String clientId, String detail){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, null, detail);
		FacesContext.getCurrentInstance().addMessage(clientId, message);
	}
	/**
	 * Envia uma mensagem de caráter de alerta ao usuário
	 * 
	 * @param clientId Indique o ID do elemento para que a mensagem seja direcionada para ele. 
	 * Caso o parametro seja null, a mensagem será global.<br/><br/>
	 * 
	 * @param detail Mensagem que detalhada do ocorrido.
	 */
	public final static void exibirAlerta(String clientId, String detail){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, null, detail);
		FacesContext.getCurrentInstance().addMessage(clientId, message);
	}
	/**
	 * Envia uma mensagem indicando a ocorrência de um erro fatal.
	 * 
	 * @param clientId Indique o ID do elemento para que a mensagem seja direcionada para ele. 
	 * Caso o parametro seja null, a mensagem será global.<br/><br/>
	 * 
	 * @param detail Mensagem que detalhada do ocorrido.
	 */
	public final static void exibirFatalError(String clientId, String detail){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, null, detail);
		FacesContext.getCurrentInstance().addMessage(clientId, message);
	}
	
	
}
