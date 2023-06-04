package com.example.demo.model;

public class Localidad {

	private double longitud;
	private double latitud;

	public Localidad(double latitude, double longitude) {
		this.latitud = latitude;
		this.longitud = longitude;
	}

	public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;

	public double haversine(Localidad destino) {
// distance between latitudes and longitudes
		double dLat = Math.toRadians(destino.latitud - this.latitud);
		double dLon = Math.toRadians(destino.longitud - this.longitud);

// convert to radians
		destino.latitud = Math.toRadians(destino.latitud);
		this.latitud = Math.toRadians(this.latitud);

// apply formulae
		double a = Math.pow(Math.sin(dLat / 2), 2)
				+ Math.pow(Math.sin(dLon / 2), 2) * Math.cos(this.latitud) * Math.cos(destino.latitud);
		double rad = 6371;
		double c = 2 * Math.asin(Math.sqrt(a));
		return rad * c;
	}

	// return string representation of this point
	public String toString() {
		return "(" + latitud + ", " + longitud + ")";
	}

}
