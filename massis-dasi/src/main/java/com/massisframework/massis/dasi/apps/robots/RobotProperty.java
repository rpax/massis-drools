package com.massisframework.massis.dasi.apps.robots;

public enum RobotProperty {
	ENERGY, IDLE, TEAM_NAME, LOCATION;
	public String getValueDescription()
	{
		switch (this)
		{
		case ENERGY:
			return "Energia actual del agente";
		case IDLE:
			return "Activo";
		case LOCATION:
			return "Localizacion del agente";
		case TEAM_NAME:
			return "Equipo al que pertenece el agente";
		default:
			throw new UnsupportedOperationException(
					"Case not considered: " + this);
		}
	}
}
