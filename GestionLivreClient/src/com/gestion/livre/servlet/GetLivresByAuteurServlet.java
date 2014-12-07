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
import com.gestion.livre.persistence.TLivre;

/**
 * Servlet implementation class GetLivresByAuteurServlet
 */
@WebServlet("/GetLivresByAuteurServlet")
public class GetLivresByAuteurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLivresByAuteurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramId =request.getParameter(ConstanteMetier.AUTEUR_ID_PARAM);
		
		if(paramId != null && paramId.trim().length() > 0){
			long auteurId = Long.valueOf(paramId);
			
			try {
				SOAPMessage soapRequest = createSoapRequest(auteurId);
				SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
				SOAPMessage soapResponse = soapConnection.call(soapRequest, ConstanteMetier.URL_WS);
				
				List<TLivre> livres = getResponse(soapResponse);
				
				request.setAttribute(ConstanteMetier.LIVRES_LIST_PARAM, livres);
				request.setAttribute(ConstanteMetier.AUTEUR_ID_PARAM, auteurId);
				this.getServletContext().getRequestDispatcher("/WEB-INF/livresByAuteur.jsp").forward(request, response);
				
			} catch (UnsupportedOperationException | SOAPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			//TODO message d'erreur !
		}
	}

	private List<TLivre> getResponse(SOAPMessage soapResponse) throws SOAPException {
		List<TLivre> livres = new ArrayList<TLivre>();
		SOAPBody soapResponseBody = soapResponse.getSOAPBody();
		NodeList livresList = soapResponseBody.getElementsByTagName(ConstanteMetier.XML_ELEMENT_LIVRE);
		
		int nbLivres = livresList.getLength();
		
		for(int i = 0; i < nbLivres; i++){
			NodeList livre =  livresList.item(i).getChildNodes();
			
			long id = 0;
			String titre = null;
			double prix = 0;
			String description = null;
			long auteurId = 0;
			String auteurNomPrenom = null;
			
			int nbNoeuds = livre.getLength();
			for(int j = 0; j < nbNoeuds; j++){
				switch (livre.item(j).getNodeName()) {
				case ConstanteMetier.XML_ELEMENT_ID:
					id = Long.valueOf(livre.item(j).getFirstChild().getTextContent());
					break;
				case ConstanteMetier.XML_ELEMENT_TITRE:
					titre = livre.item(j).getFirstChild().getTextContent();
					break;
				case ConstanteMetier.XML_ELEMENT_PRIX:
					prix = Double.valueOf(livre.item(j).getFirstChild().getTextContent());
					break;
				case ConstanteMetier.XML_ELEMENT_DESCRIPTION:
					description = livre.item(j).getFirstChild().getTextContent();
					break;
				case ConstanteMetier.XML_ELEMENT_AUTEUR_ID:
					auteurId = Long.valueOf(livre.item(j).getFirstChild().getTextContent());
					break;
				case ConstanteMetier.XML_ELEMENT_AUTEUR_NOM_PRENOM:
					auteurNomPrenom = livre.item(j).getFirstChild().getTextContent();
					break;
				}
			}
			
			if(id != 0 && titre != null && description != null){
				TLivre monLivre = new TLivre(id, titre, prix, description, auteurId, auteurNomPrenom);
				livres.add(monLivre);
			}
		}
		
		return livres;
	}

	private SOAPMessage createSoapRequest(long auteurId) throws SOAPException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		
		SOAPEnvelope soapEnv = soapPart.getEnvelope();
		soapEnv.addNamespaceDeclaration(ConstanteMetier.NAMESPACE_PREFIX, ConstanteMetier.NAMESPACE_URI);
		
		SOAPBody soapBody = soapEnv.getBody();
		SOAPElement getAllLivreByAuteurElement =  soapBody.addChildElement(ConstanteMetier.XML_ELEMENT_GET_ALL_LIVRE_BY_AUTEUR, ConstanteMetier.NAMESPACE_PREFIX);
		
		SOAPElement auteurIdElement = getAllLivreByAuteurElement.addChildElement(ConstanteMetier.XML_ELEMENT_AUTEUR_ID);
		auteurIdElement.setValue(String.valueOf(auteurId));

		
		return soapMessage;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
