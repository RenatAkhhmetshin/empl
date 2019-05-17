package com.svb.empl.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum OrgType implements EnumClass<Integer> {
	
	MANAGED (1),
	ESTABLISHED (2);
	
	private Integer id;
	
	OrgType(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return id;
	}
	
	@Nullable
	public static OrgType fromId(Integer id) {
		for (OrgType at : OrgType.values()) {
			if (at.getId().equals(id)) {
				return at;
			}
		}
		return null;
	}
}