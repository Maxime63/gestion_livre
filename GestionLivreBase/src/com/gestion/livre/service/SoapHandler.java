package com.gestion.livre.service;

import java.util.Iterator;

import javax.xml.bind.JAXB;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SAAJResult;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;

import com.gestion.livre.service.jaxws.CreateAuteur;
import com.gestion.livre.service.jaxws.GetAllAuteur;

public class SoapHandler {
	private static final String NAMESPACE_URI = "http://service.livre.gestion.com/";
	private static final QName CREATE_AUTEUR_QNAME = new QName(NAMESPACE_URI, "createAuteur");
	private static final QName GET_ALL_AUTEUR_QNAME = new QName(NAMESPACE_URI, "getAllAuteur");
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
				
				System.out.println("reçu : " + qname);
				System.out.println("vrai : " + GET_ALL_AUTEUR_QNAME);
				
				if(CREATE_AUTEUR_QNAME.equals(qname)){
					response = appelerCreateAuteur(soapElement);
					break;
				}
				if(GET_ALL_AUTEUR_QNAME.equals(qname)){
					response = appelerGetAllAuteur(soapElement);
					break;
				}
			}
		}
		
		SOAPMessage soapResponse = messageFactory.createMessage();
		soapBody = soapResponse.getSOAPBody();
		
		if(response != null){
			JAXB.marshal(response, new SAAJResult(soapBody));
		}
//		else{
//			SOAPFault fault = soapBody.addFault();
//			
//		}
		
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
	
}
