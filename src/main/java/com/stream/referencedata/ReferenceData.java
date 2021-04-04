package com.stream.referencedata;

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
		referenceData.save(entity);
		
		System.out.println(referenceData.find("denyUsers", "420"));
	}

}
