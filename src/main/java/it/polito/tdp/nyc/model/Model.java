package it.polito.tdp.nyc.model;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;


import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
	
	private Graph<String, DefaultWeightedEdge> grafo;
	private NYCDao dao;
	
	public Model() {
		super();
		this.dao = new NYCDao();
	}
	
	public void creaGrafo(String provider, Double x) {
		
		System.out.println(x);
		
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		List<String> vertici = this.dao.getVertici(provider);
		
		Graphs.addAllVertices(grafo, vertici);
		
		System.out.println(grafo.vertexSet().size());
		
		List<LocationLatLng> locations = this.dao.getLocations(provider);
			
		for(LocationLatLng l1 : locations) {
			for(LocationLatLng l2 : locations) {
				if(l1.getLocation1().compareTo(l2.getLocation1()) < 0){
					//non riesco a usare il metodo distance della libreria simplelatlng
					//quindi non creo gli archi e non posso fare niente altro...
					//PORCODDIO
					//poi un santo sul gruppo mi ha detto come usarla e adesso sembra funzionare 
					// E FUNZIONAAAAAAAAAA
					
					
					if(LatLngTool.distance(l1.getAvgPos(), l2.getAvgPos(), LengthUnit.KILOMETER) <= x) {
						
						this.grafo.addEdge(l1.getLocation1(), l2.getLocation1());
						this.grafo.setEdgeWeight(l1.getLocation1(), l2.getLocation1(), 
								LatLngTool.distance(l1.getAvgPos(), l2.getAvgPos(), LengthUnit.METER));
						
					}
				}
			}
		}
		
		System.out.println(this.grafo.edgeSet().size());
		
		
	}
	
	public List<String> tendina() {
		return this.dao.getTendina();
	}
	
	public int getNumVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNumArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public Map<String, Integer> getNodiMax() {
		
		Map<String, Integer> mappa = new HashMap<String, Integer>();
		
		int max = 0;
			
		for(String s : this.grafo.vertexSet()) {
			if(this.grafo.degreeOf(s) > max)
				max = this.grafo.degreeOf(s);
		}
		
		for(String s : this.grafo.vertexSet()) {
			if(this.grafo.degreeOf(s) == max) {
				mappa.put(s, this.grafo.degreeOf(s));
			}
				
		}
		
		return mappa;
			
	}
		
}
