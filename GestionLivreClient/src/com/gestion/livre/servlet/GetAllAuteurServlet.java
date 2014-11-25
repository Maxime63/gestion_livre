package com.gestion.livre.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.NodeList;

import com.gestion.livre.metier.ConstanteMetier;
import com.gestion.livre.persistence.TAuteur;
import com.sun.org.apache.xml.internal.security.transforms.TransformationException;

public class GetAllAuteurServlet extends HttpServlet {

	/** */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			SOAPMessage soapMessage = createRequest();
			
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			SOAPMessage soapResponse = soapConnection.call(soapMessage, ConstanteMetier.URL_WS);

			List<TAuteur> auteurs = getAllAuteur(soapResponse);
			req.setAttribute(ConstanteMetier.AUTEURS_LIST_PARAM, auteurs);
						
			this.getServletContext().getRequestDispatcher("/WEB-INF/auteurs.jsp").forward(req, resp);
		} catch (SOAPException e) {
			//TODO Gérer les erreurs internes !
			e.printStackTrace();
		}
	}

	private SOAPMessage createRequest() throws SOAPException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		
		SOAPEnvelope soapEnv = soapPart.getEnvelope();
		soapEnv.addNamespaceDeclaration(ConstanteMetier.NAMESPACE_PREFIX, ConstanteMetier.NAMESPACE_URI);
		
		SOAPBody soapBody = soapEnv.getBody();
		soapBody.addChildElement(ConstanteMetier.XML_ELEMENT_GET_ALL_AUTEUR, ConstanteMetier.NAMESPACE_PREFIX);
		return soapMessage;
	}
	
	private List<TAuteur> getAllAuteur(SOAPMessage soapResponse) throws SOAPException{
		List<TAuteur> auteurs = new ArrayList<TAuteur>();
		SOAPBody soapResponseBody = soapResponse.getSOAPBody();
		NodeList auteursList = soapResponseBody.getElementsByTagName(ConstanteMetier.XML_ELEMENT_AUTEUR);
		
		int nbAuteurs = auteursList.getLength();
		
		for(int i = 0; i < nbAuteurs; i++){
			NodeList auteur =  auteursList.item(i).getChildNodes();
			
			long id = 0;
			String nom = null;
			String prenom = null;
			String domicile = null;
			
			int nbNoeuds = auteur.getLength();
			for(int j = 0; j < nbNoeuds; j++){
				switch (auteur.item(j).getNodeName()) {
				case ConstanteMetier.XML_ELEMENT_ID:
					id = Long.valueOf(auteur.item(j).getFirstChild().getTextContent());
					break;
				case ConstanteMetier.XML_ELEMENT_NOM:
					nom = auteur.item(j).getFirstChild().getTextContent();
					break;
				case ConstanteMetier.XML_ELEMENT_PRENOM:
					prenom = auteur.item(j).getFirstChild().getTextContent();
					break;
				case ConstanteMetier.XML_ELEMENT_DOMICILE:
					domicile = auteur.item(j).getFirstChild().getTextContent();
					break;
				}
			}
			
			if(id != 0 && nom != null && prenom != null && domicile != null){
				TAuteur monAuteur = new TAuteur(id, nom, prenom, domicile);
				auteurs.add(monAuteur);
			}
		}
		
		return auteurs;
	}
}
