package com.brum.domain.dto.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import com.brum.domain.dto.v1.SheetDTO;
import com.brum.domain.entities.Sheet;
import com.brum.domain.entities.SheetExpenses;
import com.brum.domain.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "user", "expenses" })
public class SheetDTOH extends RepresentationModel<SheetDTOH> {

	@JsonProperty("id")
	private Long key;
	private String name;
	private List<SheetExpensesDTOH> expenses;
	@JsonIgnore
	private Long userId;

	public SheetDTOH() {
	}

	public SheetDTOH(String name, List<SheetExpensesDTOH> expenses, Long userId) {
		this.name = name;
		this.expenses = expenses;
		this.userId = userId;
	}

	public SheetDTOH(String name, Long userId) {
		this.name = name;
		this.userId = userId;
		this.expenses = new ArrayList<SheetExpensesDTOH>();
	}

	public SheetDTOH(Sheet sheet) {
		this.key = sheet.getId();
		this.name = sheet.getName();
		this.userId = sheet.getUser().getId();
		this.expenses = new ArrayList<SheetExpensesDTOH>();

		if (sheet.getExpenses() != null) {
			this.expenses = sheet.getExpenses().stream().map(SheetExpensesDTOH::new).collect(Collectors.toList());
		} else {
			this.expenses = new ArrayList<SheetExpensesDTOH>();
		}

	}

	public SheetDTOH(SheetDTO sheet) {
		this.key = sheet.getKey();
		this.name = sheet.getName();
		this.userId = sheet.getUserId();
		this.expenses = new ArrayList<SheetExpensesDTOH>();

		if (sheet.getExpenses() != null) {
			this.expenses = sheet.getExpenses().stream().map(SheetExpensesDTOH::new).collect(Collectors.toList());
		} else {
			this.expenses = new ArrayList<SheetExpensesDTOH>();
		}

	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SheetExpensesDTOH> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<SheetExpensesDTOH> expenses) {
		this.expenses = expenses;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long user) {
		this.userId = user;
	}

	public Sheet dtoToEntity() {
		Sheet sheet = new Sheet();
		sheet.setId(this.key);
		sheet.setName(this.name);
		sheet.setUser(new User());
		sheet.getUser().setId((this.userId));

		if (this.expenses != null) {
			sheet.setExpenses(this.expenses.stream().map(SheetExpensesDTOH::dtoToEntity).collect(Collectors.toList()));
		} else {
			sheet.setExpenses(new ArrayList<SheetExpenses>());
		}
		return sheet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(expenses, key, name, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SheetDTOH other = (SheetDTOH) obj;
		return Objects.equals(expenses, other.expenses) && Objects.equals(key, other.key)
				&& Objects.equals(name, other.name) && Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "SheetDTO [key=" + key + ", name=" + name + ", expenses=" + expenses + ", userId=" + userId + "]";
	}
}
