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
 * Servlet implementation class UpdateAuteurServlet
 */
@WebServlet("/UpdateAuteurServlet")
public class UpdateAuteurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAuteurServlet() {
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
				TAuteur auteur = getAuteur(soapResponse);
				
				request.setAttribute(ConstanteMetier.AUTEUR_PARAM, auteur);
				this.getServletContext().getRequestDispatcher("/WEB-INF/updateAuteur.jsp").forward(request, response);
				
			} catch (UnsupportedOperationException | SOAPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			//TODO message d'erreur !
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter(ConstanteMetier.AUTEUR_LASTNAME_PARAM);
		String prenom = request.getParameter(ConstanteMetier.AUTEUR_FIRSTNAME_PARAM);
		String domicile = request.getParameter(ConstanteMetier.AUTEUR_HOME_PARAM);
		String auteurIdParam = request.getParameter(ConstanteMetier.AUTEUR_ID_PARAM);
		
		if(auteurIdParam != null && auteurIdParam.trim().length() > 0){
			if(nom != null && nom.trim().length() > 0){
				if(prenom != null && prenom.trim().length() > 0){
					if(domicile != null && domicile.trim().length() > 0){
						nom = nom.trim();
						prenom = prenom.trim();
						domicile = domicile.trim();
						Long auteurId = Long.valueOf(auteurIdParam.trim());
						
						try {
							SOAPMessage soapRequest = createSoapRequestUpdateAuteur(nom, prenom, domicile, auteurId);
							SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
				            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
							SOAPMessage soapResponse = soapConnection.call(soapRequest, ConstanteMetier.URL_WS);
			
							TAuteur auteur = getUpdatedAuteur(soapResponse);
							
							request.setAttribute(ConstanteMetier.AUTEUR_PARAM, auteur);
							this.getServletContext().getRequestDispatcher("/WEB-INF/auteur.jsp").forward(request, response);
						} catch (UnsupportedOperationException | SOAPException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else{
						//TODO message d'erreur pour le domicile
					}
				}
				else{
					//TODO message d'erreur pour le prénom
				}
			}
			else{
				//TODO message d'erreur pour le nom
			}
		}
		else{
			//TODO message d'erreur pour l'auteur id
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
	
	private SOAPMessage createSoapRequestUpdateAuteur(String nom, String prenom,
			String domicile, long auteurId) throws SOAPException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		
		SOAPEnvelope soapEnv = soapPart.getEnvelope();
		soapEnv.addNamespaceDeclaration(ConstanteMetier.NAMESPACE_PREFIX, ConstanteMetier.NAMESPACE_URI);
		
		SOAPBody soapBody = soapEnv.getBody();
		SOAPElement auteurElement = soapBody.addChildElement(ConstanteMetier.XML_ELEMENT_UPDATE_AUTEUR, ConstanteMetier.NAMESPACE_PREFIX);
		SOAPElement auteurIdElement = auteurElement.addChildElement(ConstanteMetier.XML_ELEMENT_ID);
		auteurIdElement.setValue(String.valueOf(auteurId));
		SOAPElement nomElement = auteurElement.addChildElement(ConstanteMetier.XML_ELEMENT_NOM);
		nomElement.setValue(nom);
		SOAPElement prenomElement = auteurElement.addChildElement(ConstanteMetier.XML_ELEMENT_PRENOM);
		prenomElement.setValue(prenom);
		SOAPElement domicileElement = auteurElement.addChildElement(ConstanteMetier.XML_ELEMENT_DOMICILE);
		domicileElement.setValue(domicile);
		
		return soapMessage;
	}
	
	private TAuteur getUpdatedAuteur(SOAPMessage soapResponse) throws SOAPException {
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
}
