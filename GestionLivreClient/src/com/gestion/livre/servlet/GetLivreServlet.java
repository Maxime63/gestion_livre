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
import com.gestion.livre.persistence.TLivre;

/**
 * Servlet implementation class GetLivreServlet
 */
@WebServlet("/GetLivreServlet")
public class GetLivreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLivreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramLivreId =request.getParameter(ConstanteMetier.LIVRE_ID_PARAM);
		String paramAuteurId =request.getParameter(ConstanteMetier.AUTEUR_ID_PARAM);
		
		
		if(paramLivreId != null && paramLivreId.trim().length() > 0){
			if(paramAuteurId != null && paramAuteurId.trim().length() > 0){
				long livreId = Long.valueOf(paramLivreId.trim());
				long auteurId = Long.valueOf(paramAuteurId.trim());
				
				try {
					SOAPMessage soapRequest = createSoapRequest(livreId, auteurId);
					SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
					SOAPMessage soapResponse = soapConnection.call(soapRequest, ConstanteMetier.URL_WS);
					
					TLivre livre = getLivre(soapResponse);
					
					request.setAttribute(ConstanteMetier.LIVRE_PARAM, livre);
					this.getServletContext().getRequestDispatcher("/WEB-INF/livre.jsp").forward(request, response);
					
				} catch (UnsupportedOperationException | SOAPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				
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
		// TODO Auto-generated method stub
	}
	
	private TLivre getLivre(SOAPMessage soapResponse) throws SOAPException {
		TLivre livre = null;
		SOAPBody soapResponseBody = soapResponse.getSOAPBody();
		NodeList livresList = soapResponseBody.getElementsByTagName(ConstanteMetier.XML_ELEMENT_LIVRE);
		
		int nbLivres = livresList.getLength();
		
		if(nbLivres == 1){
			NodeList livreNode =  livresList.item(0).getChildNodes();
			
			long id = 0;
			String titre = null;
			double prix = 0;
			String description = null;
			long auteurId = 0;
			String auteurNomPrenom = null;
			
			
			int nbChildNodes = livreNode.getLength();
			for(int j = 0; j < nbChildNodes; j++){
				switch (livreNode.item(j).getNodeName()) {
				case ConstanteMetier.XML_ELEMENT_ID:
					id = Long.valueOf(livreNode.item(j).getFirstChild().getTextContent());
					break;
				case ConstanteMetier.XML_ELEMENT_TITRE:
					titre = livreNode.item(j).getFirstChild().getTextContent();
					break;
				case ConstanteMetier.XML_ELEMENT_PRIX:
					prix = Double.valueOf(livreNode.item(j).getFirstChild().getTextContent());
					break;
				case ConstanteMetier.XML_ELEMENT_DESCRIPTION:
					description = livreNode.item(j).getFirstChild().getTextContent();
					break;
				case ConstanteMetier.XML_ELEMENT_AUTEUR_ID:
					auteurId = Long.valueOf(livreNode.item(j).getFirstChild().getTextContent());
					break;
				case ConstanteMetier.XML_ELEMENT_AUTEUR_NOM_PRENOM:
					auteurNomPrenom = livreNode.item(j).getFirstChild().getTextContent();
					break;
				}
			}
			
			if(id != 0 && titre != null && description != null){
				livre = new TLivre(id, titre, prix, description, auteurId, auteurNomPrenom);
			}
		}
		
		return livre;
	}

	private SOAPMessage createSoapRequest(long livreId, long auteurId) throws SOAPException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		
		SOAPEnvelope soapEnv = soapPart.getEnvelope();
		soapEnv.addNamespaceDeclaration(ConstanteMetier.NAMESPACE_PREFIX, ConstanteMetier.NAMESPACE_URI);
		
		SOAPBody soapBody = soapEnv.getBody();
		SOAPElement getLivreElement =  soapBody.addChildElement(ConstanteMetier.XML_ELEMENT_GET_LIVRE, ConstanteMetier.NAMESPACE_PREFIX);
		
		SOAPElement livreIdElement = getLivreElement.addChildElement(ConstanteMetier.XML_ELEMENT_ID);
		livreIdElement.setValue(String.valueOf(livreId));

		SOAPElement auteurIdElement = getLivreElement.addChildElement(ConstanteMetier.XML_ELEMENT_AUTEUR_ID);
		auteurIdElement.setValue(String.valueOf(auteurId));

		
		return soapMessage;
	}

}
