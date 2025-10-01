package com.rt.springboot.app.view.xml;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import com.rt.springboot.app.models.entity.Client;

@XmlRootElement(name = "clients")
public class ClientList {

	@XmlElement(name = "client")
	public List<Client> clients;

	public ClientList() {}
	
	public ClientList(List<Client> clients) {
		this.clients = clients;
	}

	public List<Client> getClients() {
		return clients;
	}
	
}
