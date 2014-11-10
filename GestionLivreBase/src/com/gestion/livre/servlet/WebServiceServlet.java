package com.gestion.livre.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import com.gestion.livre.service.SoapHandler;
import com.sun.xml.internal.ws.resources.SoapMessages;

public class WebServiceServlet extends HttpServlet{
	static MessageFactory messageFactory;
	static SoapHandler soapHandler;
	
	static{
		try{
			messageFactory = MessageFactory.newInstance();
			soapHandler = new SoapHandler();
		} catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
	static MimeHeaders getHeaders(HttpServletRequest req){
		Enumeration headerNames = req.getHeaderNames();
		MimeHeaders headers = new MimeHeaders();
		
		while(headerNames.hasMoreElements()){
			String headerName = (String) headerNames.nextElement();
			String headerValue = req.getHeader(headerName);
			StringTokenizer values = new StringTokenizer(headerValue, ",");
			
			while(values.hasMoreTokens()){
				headers.addHeader(headerName, values.nextToken().trim());
			}
		}
		
		return headers;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		try{
			MimeHeaders headers = getHeaders(req);
			InputStream is = req.getInputStream();
			SOAPMessage soapResquest = messageFactory.createMessage(headers, is);
			
			//Gérer soapResquest
			SOAPMessage soapResponse = soapHandler.handleSOAPRequest(soapResquest);
			
			//Créer HttpServletResponse
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("text/xml;charset=\"utf-8\"");
			OutputStream os = resp.getOutputStream();
			soapResponse.writeTo(os);
			os.flush();
		}catch(
				Exception e){
			throw new IOException("Exception while creating SOAP message.", e);
		}
	}
}
