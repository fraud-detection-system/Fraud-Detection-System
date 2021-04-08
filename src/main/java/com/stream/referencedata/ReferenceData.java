package com.stream.referencedata;

import java.util.Date;
import java.util.Optional;

import com.stream.fraud.model.Entity;

public class ReferenceData {
	EntityRepository entityRepository = new EntityRepository();
	{
		entityRepository.initialize();
	}
	
	public Entity find(String entityType, String key) {
		Optional<Object> value = entityRepository.find(entityType+":"+key);
		if(value.isPresent() && value.get() instanceof Entity) {
			return (Entity) value.get();
		}
		return null;
	}
	
	public void save(Entity entity) {
		String entityType = (String)entity.getAttribute("type");
		String key = (String) entity.getAttribute("id");
		entityRepository.save(entityType+":"+key, entity);
	}
	
	public static void main(String []args) {
		ReferenceData referenceData = new ReferenceData();
		Entity entity = new Entity();
		entity.setAttribute("type", "denyUsers");
		entity.setAttribute("id", "420");
		entity.setAttribute("name", "Charles Shobraj");
		entity.setAttribute("denyUsers", "true");
		referenceData.save(entity);
		
		System.out.println(referenceData.find("denyUsers", "420"));
		
		entity = new Entity();
		entity.setAttribute("type", "denyIPAddress");
		entity.setAttribute("id", "172.1.1.1");
		entity.setAttribute("name", "Seattle Hackers");
		entity.setAttribute("denyIPAdress", "true");
		referenceData.save(entity);
		
		entity = new Entity();
		entity.setAttribute("type", "account");
		entity.setAttribute("id", "420");
		entity.setAttribute("balance", "1000");
		entity.setAttribute("status", "active");
		entity.setAttribute("lastTxnTime", ""+new Date());
		referenceData.save(entity);
		
		entity = new Entity();
		entity.setAttribute("type", "user");
		entity.setAttribute("id", "420");
		entity.setAttribute("homeLocation", "1234");
		entity.setAttribute("homeCity", "Bengaluru");
		entity.setAttribute("phoneLocation", "32323");
		entity.setAttribute("emailId", "a@b.com");
		referenceData.save(entity);
		
		entity = new Entity();
		entity.setAttribute("type", "userConfiguration");
		entity.setAttribute("id", "420");
		entity.setAttribute("maxAutoApproval", "1000");
		entity.setAttribute("approver", "878787");
		referenceData.save(entity);
	}

}
