package com.gestion.livre.service;

import java.util.Iterator;

import javax.xml.bind.JAXB;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SAAJResult;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;

import com.gestion.livre.service.jaxws.CreateAuteur;
import com.gestion.livre.service.jaxws.CreateLivre;
import com.gestion.livre.service.jaxws.DeleteAuteur;
import com.gestion.livre.service.jaxws.DeleteLivre;
import com.gestion.livre.service.jaxws.GetAllAuteur;
import com.gestion.livre.service.jaxws.GetAllLivres;
import com.gestion.livre.service.jaxws.GetAllLivresByAuteur;
import com.gestion.livre.service.jaxws.GetAuteur;
import com.gestion.livre.service.jaxws.GetLivre;
import com.gestion.livre.service.jaxws.UpdateAuteur;
import com.gestion.livre.service.jaxws.UpdateLivre;

public class SoapHandler {
	private static final String NAMESPACE_URI = "http://service.livre.gestion.com/";
	private static final QName CREATE_AUTEUR_QNAME = new QName(NAMESPACE_URI, "createAuteur");
	private static final QName GET_ALL_AUTEUR_QNAME = new QName(NAMESPACE_URI, "getAllAuteur");
	private static final QName GET_AUTEUR_QNAME = new QName(NAMESPACE_URI, "getAuteur");
	private static final QName DELETE_AUTEUR_QNAME = new QName(NAMESPACE_URI, "deleteAuteur");
	private static final QName UPDATE_AUTEUR_QNAME = new QName(NAMESPACE_URI, "updateAuteur");
	private static final QName CREATE_LIVRE_QNAME = new QName(NAMESPACE_URI, "createLivre");
	private static final QName GET_ALL_LIVRES_QNAME = new QName(NAMESPACE_URI, "getAllLivres");
	private static final QName GET_ALL_LIVRES_BY_AUTEUR_QNAME = new QName(NAMESPACE_URI, "getAllLivresByAuteur");
	private static final QName GET_LIVRE_QNAME = new QName(NAMESPACE_URI, "getLivre");
	private static final QName DELETE_LIVRE_QNAME = new QName(NAMESPACE_URI, "deleteLivre");
	private static final QName UPDATE_LIVRE_QNAME = new QName(NAMESPACE_URI, "updateLivre");
	private MessageFactory messageFactory;
	private SoapAdapter adapter;
	
	public SoapHandler() throws SOAPException{
		messageFactory = MessageFactory.newInstance();
		adapter = new SoapAdapter();
	}
	
	public SOAPMessage handleSOAPRequest(SOAPMessage request) throws SOAPException{
		SOAPBody soapBody = request.getSOAPBody();
		Iterator iterator = soapBody.getChildElements();
		Object response = null;
		
		while(iterator.hasNext()){
			Object next = iterator.next();
			if(next instanceof SOAPElement){
				SOAPElement soapElement = (SOAPElement) next;
				QName qname = soapElement.getElementQName();
				
				if(CREATE_AUTEUR_QNAME.equals(qname)){
					response = appelerCreateAuteur(soapElement);
					break;
				}
				if(GET_ALL_AUTEUR_QNAME.equals(qname)){
					response = appelerGetAllAuteur(soapElement);
					break;
				}
				if(GET_AUTEUR_QNAME.equals(qname))
				{
					response = appelerGetAuteur(soapElement);
					break;
				}
				if(DELETE_AUTEUR_QNAME.equals(qname))
				{
					response = appelerDeleteAuteur(soapElement);
					break;
				}
				if(UPDATE_AUTEUR_QNAME.equals(qname))
				{
					response = appelerUpdateAuteur(soapElement);
					break;
				}
				if(CREATE_LIVRE_QNAME.equals(qname))
				{
					response = appelerCreateLivre(soapElement);
					break;
				}
				if(GET_ALL_LIVRES_QNAME.equals(qname))
				{
					response = appelerGetAllLivres(soapElement);
					break;
				}
				if(GET_ALL_LIVRES_BY_AUTEUR_QNAME.equals(qname))
				{
					response = appelerGetAllLivresByAuteur(soapElement);
					break;
				}
				if(GET_LIVRE_QNAME.equals(qname))
				{
					response = appelerGetLivre(soapElement);
					break;
				}
				if(UPDATE_LIVRE_QNAME.equals(qname))
				{
					response = appelerUpdateLivre(soapElement);
					break;
				}
				if(DELETE_LIVRE_QNAME.equals(qname))
				{
					response = appelerDeleteLivre(soapElement);
					break;
				}
			}
		}
		
		SOAPMessage soapResponse = messageFactory.createMessage();
		soapBody = soapResponse.getSOAPBody();
		
		if(response != null){
			JAXB.marshal(response, new SAAJResult(soapBody));
		}
		else{
			SOAPFault fault = soapBody.addFault();	
		}
		
		return soapResponse;
	}
	
	private Object appelerCreateAuteur(SOAPElement soapElement){
		CreateAuteur createAuteur = JAXB.unmarshal(new DOMSource(soapElement), CreateAuteur.class);
		return adapter.adapterCreateAuteur(createAuteur);
	}
	
	private Object appelerGetAllAuteur(SOAPElement soapElement){
		GetAllAuteur getAllAuteur = JAXB.unmarshal(new DOMSource(soapElement), GetAllAuteur.class);
		return adapter.adapterGetAllAuteur(getAllAuteur);
	}
	
	private Object appelerGetAuteur(SOAPElement soapElement)
	{
		GetAuteur getAuteur = JAXB.unmarshal(new DOMSource(soapElement), GetAuteur.class);
		return adapter.adapterGetAuteur(getAuteur);
	}
	
	private Object appelerDeleteAuteur(SOAPElement soapElement)
	{
		DeleteAuteur deleteAuteur = JAXB.unmarshal(new DOMSource(soapElement), DeleteAuteur.class);
		return adapter.adapterDeleteAuteur(deleteAuteur);
	}
	
	private Object appelerUpdateAuteur(SOAPElement soapElement)
	{
		UpdateAuteur updateAuteur = JAXB.unmarshal(new DOMSource(soapElement), UpdateAuteur.class);
		return adapter.adapterUpdateAuteur(updateAuteur);
	}
	
	private Object appelerCreateLivre(SOAPElement soapElement)
	{
		CreateLivre createLivre = JAXB.unmarshal(new DOMSource(soapElement), CreateLivre.class);
		return adapter.adapterCreateLivre(createLivre);
	}
	
	private Object appelerGetAllLivres(SOAPElement soapElement){
		GetAllLivres getAllLivres = JAXB.unmarshal(new DOMSource(soapElement), GetAllLivres.class);
		return adapter.adapterGetAllLivres(getAllLivres);
	}
	
	private Object appelerGetAllLivresByAuteur(SOAPElement soapElement){
		GetAllLivresByAuteur getAllLivresByAuteurr = JAXB.unmarshal(new DOMSource(soapElement), GetAllLivresByAuteur.class);
		return adapter.adapterGetAllLivresByAuteur(getAllLivresByAuteurr);
	}
	
	private Object appelerGetLivre(SOAPElement soapElement)
	{
		GetLivre getLivre = JAXB.unmarshal(new DOMSource(soapElement), GetLivre.class);
		return adapter.adapterGetLivre(getLivre);
	}
	
	private Object appelerDeleteLivre(SOAPElement soapElement)
	{
		DeleteLivre deleteLivre = JAXB.unmarshal(new DOMSource(soapElement), DeleteLivre.class);
		return adapter.adapterDeleteLivre(deleteLivre);
	}
	
	private Object appelerUpdateLivre(SOAPElement soapElement)
	{
		UpdateLivre updateLivre = JAXB.unmarshal(new DOMSource(soapElement), UpdateLivre.class);
		return adapter.adapterUpdateLivre(updateLivre);
	}
	
}
