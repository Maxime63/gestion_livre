package com.gestion.livre.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class GetAuteur
 */
@WebServlet("/GetAuteur")
public class GetAuteurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
				TAuteur auteur = getAuteur(soapResponse);
				
				request.setAttribute(ConstanteMetier.AUTEUR_PARAM, auteur);
				this.getServletContext().getRequestDispatcher("/WEB-INF/auteur.jsp").forward(request, response);
				
			} catch (UnsupportedOperationException | SOAPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			//TODO message d'erreur !
		}
	}

	private SOAPMessage createSoapRequest(long id) throws SOAPException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		
		SOAPEnvelope soapEnv = soapPart.getEnvelope();
		soapEnv.addNamespaceDeclaration(ConstanteMetier.NAMESPACE_PREFIX, ConstanteMetier.NAMESPACE_URI);
		
		SOAPBody soapBody = soapEnv.getBody();
		SOAPElement auteurElement = soapBody.addChildElement(ConstanteMetier.XML_ELEMENT_GET_AUTEUR, ConstanteMetier.NAMESPACE_PREFIX);
		SOAPElement idElement = auteurElement.addChildElement(ConstanteMetier.XML_ELEMENT_ID);
		idElement.setValue(String.valueOf(id));
		
		return soapMessage;
	}

	private TAuteur getAuteur(SOAPMessage soapResponse) throws SOAPException {
		TAuteur auteur = null;
		SOAPBody soapResponseBody = soapResponse.getSOAPBody();
		NodeList auteursList = soapResponseBody.getElementsByTagName(ConstanteMetier.XML_ELEMENT_AUTEUR);
		
		int nbAuteurs = auteursList.getLength();
		
		if(nbAuteurs == 1){
			NodeList auteurNode =  auteursList.item(0).getChildNodes();
			
			long id = 0;
			String nom = null;
			String prenom = null;
			String domicile = null;
			
			int nbNoeuds = auteurNode.getLength();
			for(int j = 0; j < nbNoeuds; j++){
				switch (auteurNode.item(j).getNodeName()) {
				case ConstanteMetier.XML_ELEMENT_ID:
					id = Long.valueOf(auteurNode.item(j).getFirstChild().getTextContent());
					break;
				case ConstanteMetier.XML_ELEMENT_NOM:
					nom = auteurNode.item(j).getFirstChild().getTextContent();
					break;
				case ConstanteMetier.XML_ELEMENT_PRENOM:
					prenom = auteurNode.item(j).getFirstChild().getTextContent();
					break;
				case ConstanteMetier.XML_ELEMENT_DOMICILE:
					domicile = auteurNode.item(j).getFirstChild().getTextContent();
					break;
				}
			}
			
			if(id != 0 && nom != null && prenom != null && domicile != null){
				auteur = new TAuteur(id, nom, prenom, domicile);
			}
		}
		
		return auteur;
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
