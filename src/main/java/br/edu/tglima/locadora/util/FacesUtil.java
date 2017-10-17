package br.edu.tglima.locadora.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Esta classe implementa métodos para que o sistema envie mensagens para o
 * usuário. <br/>
 * <br/>
 * As mensagens enviadas podem ser classificadas em quatro grupos diferentes,
 * são eles: <br/>
 * <b>Alerta, Erro, Sucesso e Fatal Error.</b><br/>
 * <br/>
 *
 * Esta classe utiliza o FacesContext para realizar o envio das mensagens.
 *
 * @author tglima
 */
public class FacesUtil {

	/**
	 * Envia uma mensagem de caráter de erro ou falha ao usuário.
	 *
	 * @param clientId
	 *            Indique o ID do elemento para que a mensagem seja direcionada para
	 *            ele. Caso o parametro seja null, a mensagem será global.<br/>
	 *            <br/>
	 *
	 * @param msg
	 *            Mensagem que detalhada o ocorrido.
	 */
	public final static void enviarMsgErro(String clientId, String msg) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg);
		FacesContext.getCurrentInstance().addMessage(clientId, message);
	}

	/**
	 * Envia uma mensagem de caráter de erro ou falha ao usuário.
	 *
	 * @param msg
	 *            Mensagem que detalhada o ocorrido.
	 */
	public final static void enviarMsgErro(String msg) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * Envia uma mensagem de caráter de informativo ou de sucesso ao usuário.
	 *
	 * @param clientId
	 *            Indique o ID do elemento para que a mensagem seja direcionada para
	 *            ele. Caso o parametro seja null, a mensagem será global.<br/>
	 *            <br/>
	 *
	 * @param msg
	 *            Mensagem que detalhada o ocorrido.
	 */
	public final static void enviarMsgSucesso(String clientId, String msg) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg);
		FacesContext.getCurrentInstance().addMessage(clientId, message);
	}

	/**
	 * Envia uma mensagem de caráter de informativo ou de sucesso ao usuário.
	 *
	 * @param msg
	 *            Mensagem que detalhada o ocorrido.
	 */
	public final static void enviarMsgSucesso(String msg) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * Envia uma mensagem de caráter de alerta ao usuário.
	 *
	 * @param clientId
	 *            Indique o ID do elemento para que a mensagem seja direcionada para
	 *            ele. Caso o parametro seja null, a mensagem será global.<br/>
	 *            <br/>
	 *
	 * @param msg
	 *            Mensagem que detalhada o ocorrido.
	 */
	public final static void enviarMsgAlerta(String clientId, String msg) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, null, msg);
		FacesContext.getCurrentInstance().addMessage(clientId, message);
	}

	/**
	 * Envia uma mensagem de caráter de alerta ao usuário.
	 *
	 * @param msg
	 *            Mensagem que detalhada o ocorrido.
	 */
	public final static void enviarMsgAlerta(String msg) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, null, msg);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * Envia uma mensagem indicando a ocorrência de um erro fatal.
	 *
	 * @param msg
	 *            Mensagem que detalhada o ocorrido.
	 */
	public final static void enviarMsgFatalError(String msg) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, null, msg);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
