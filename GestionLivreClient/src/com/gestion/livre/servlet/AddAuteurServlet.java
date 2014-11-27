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
 * Servlet implementation class AddAuteurServlet
 */
@WebServlet("/AddAuteurServlet")
public class AddAuteurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAuteurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/addAuteur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter(ConstanteMetier.AUTEUR_LASTNAME_PARAM);
		String prenom = request.getParameter(ConstanteMetier.AUTEUR_FIRSTNAME_PARAM);
		String domicile = request.getParameter(ConstanteMetier.AUTEUR_HOME_PARAM);
		
		if(nom != null && nom.trim().length() > 0){
			if(prenom != null && prenom.trim().length() > 0){
				if(domicile != null && domicile.trim().length() > 0){
					nom = nom.trim();
					prenom = prenom.trim();
					domicile = domicile.trim();
					
					try {
						SOAPMessage soapRequest = createSoapRequest(nom, prenom, domicile);
						SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
						SOAPMessage soapResponse = soapConnection.call(soapRequest, ConstanteMetier.URL_WS);
		
						TAuteur auteur = getCreatedAuteur(soapResponse);
						
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

	private SOAPMessage createSoapRequest(String nom, String prenom, String domicile) throws SOAPException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		
		SOAPEnvelope soapEnv = soapPart.getEnvelope();
		soapEnv.addNamespaceDeclaration(ConstanteMetier.NAMESPACE_PREFIX, ConstanteMetier.NAMESPACE_URI);
		
		SOAPBody soapBody = soapEnv.getBody();
		SOAPElement auteurElement = soapBody.addChildElement(ConstanteMetier.XML_ELEMENT_ADD_AUTEUR, ConstanteMetier.NAMESPACE_PREFIX);
		SOAPElement nomElement = auteurElement.addChildElement(ConstanteMetier.XML_ELEMENT_NOM);
		nomElement.setValue(nom);
		SOAPElement prenomElement = auteurElement.addChildElement(ConstanteMetier.XML_ELEMENT_PRENOM);
		prenomElement.setValue(prenom);
		SOAPElement domicileElement = auteurElement.addChildElement(ConstanteMetier.XML_ELEMENT_DOMICILE);
		domicileElement.setValue(domicile);
		
		return soapMessage;
	}
	
	private TAuteur getCreatedAuteur(SOAPMessage soapResponse) throws SOAPException {
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
