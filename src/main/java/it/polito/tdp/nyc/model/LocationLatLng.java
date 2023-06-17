package it.polito.tdp.nyc.model;

import java.util.Objects;

import com.javadocmd.simplelatlng.LatLng;

public class LocationLatLng {
	
	private String location;
	private LatLng avgPos;
	
	public LocationLatLng(String location1, LatLng avgPos) {
		super();
		this.location = location1;
		this.avgPos = avgPos;
	}

	public String getLocation1() {
		return location;
	}

	public void setLocation1(String location1) {
		this.location = location1;
	}

	public LatLng getAvgPos() {
		return avgPos;
	}

	public void setAvgPos(LatLng avgPos) {
		this.avgPos = avgPos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(avgPos, location);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocationLatLng other = (LocationLatLng) obj;
		return Objects.equals(avgPos, other.avgPos) && Objects.equals(location, other.location);
	}


	
	@Override
	public String toString() {
		return "LocationLatLng [location=" + location + ", avgPos=" + avgPos + "]";
	}	

}
