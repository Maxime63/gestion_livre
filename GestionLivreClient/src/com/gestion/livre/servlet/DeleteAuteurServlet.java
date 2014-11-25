package com.gestion.livre.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.NodeList;

import com.gestion.livre.metier.ConstanteMetier;
import com.gestion.livre.persistence.TAuteur;

/**
 * Servlet implementation class DeleteAuteurServlet
 */
@WebServlet("/DeleteAuteurServlet")
public class DeleteAuteurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAuteurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramId =request.getParameter(ConstanteMetier.AUTEUR_ID_PARAM);
		
		if(paramId != null && paramId.trim().length() > 0){
			long id = Long.valueOf(paramId);
			
			try {
				SOAPMessage soapRequest = createSoapRequest(id);
				SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
				SOAPMessage soapResponse = soapConnection.call(soapRequest, ConstanteMetier.URL_WS);
				boolean isDeleted = getResponse(soapResponse);
				
				request.setAttribute(ConstanteMetier.IS_DELETED_PARAM, isDeleted);
				this.getServletContext().getRequestDispatcher("/getAllAuteur").forward(request, response);
				
			} catch (UnsupportedOperationException | SOAPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			//TODO message d'erreur !
		}
	}

	private boolean getResponse(SOAPMessage soapResponse) throws SOAPException {
		boolean isDeleted = false;
		
		SOAPBody soapResponseBody = soapResponse.getSOAPBody();
		NodeList isDeletedNode = soapResponseBody.getElementsByTagName(ConstanteMetier.XML_ELEMENT_IS_DELETED);
		
		if(isDeletedNode.getLength() == 1){
			isDeleted = Boolean.getBoolean(isDeletedNode.item(0).getFirstChild().getTextContent());
		}
		
		return isDeleted;
	}

	private SOAPMessage createSoapRequest(long id) throws SOAPException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		
		SOAPEnvelope soapEnv = soapPart.getEnvelope();
		soapEnv.addNamespaceDeclaration(ConstanteMetier.NAMESPACE_PREFIX, ConstanteMetier.NAMESPACE_URI);
		
		SOAPBody soapBody = soapEnv.getBody();
		SOAPElement auteurElement = soapBody.addChildElement(ConstanteMetier.XML_ELEMENT_DELETE_AUTEUR, ConstanteMetier.NAMESPACE_PREFIX);
		SOAPElement idElement = auteurElement.addChildElement(ConstanteMetier.XML_ELEMENT_ID);
		idElement.setValue(String.valueOf(id));
		
		return soapMessage;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
