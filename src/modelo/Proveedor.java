package modelo;

import java.util.ArrayList;

import persistencia.ProveedorDAO;

public class Proveedor {

	ProveedorDAO pDao;
	ArrayList<ArrayList<Integer>> proveedores = new ArrayList<>();
	
	public Proveedor()
	{
		pDao= new ProveedorDAO();
		proveedores= pDao.obtenerProveedor();
	}
	
	public ArrayList<ArrayList<Integer>> loadcapacidadLote(Integer idArt)
	{
		
		ArrayList<ArrayList<Integer>> provs = new ArrayList<>();
		ArrayList<Integer> aux;
		Integer art_id;
		for (ArrayList<Integer> p : proveedores)
		{
			art_id =p.get(0);
//			System.out.println("art en prov: "+art_id);
			if(art_id==idArt)
			{
				aux= new ArrayList<>();
				Integer prov = p.get(1);
				aux.add(prov);
				Integer cap = p.get(2);
				aux.add(cap);
				Integer lote = p.get(3);
				aux.add(lote);
				provs.add(aux);
			}
		}
		
		return provs;
		
	}
	
	public Integer getCapacidad (Integer art, Integer prov)
	{
		Integer art_id;
		Integer pro_id;
		Integer lead=null;
		for (ArrayList<Integer> p : proveedores)
		{
			art_id =p.get(0);
			pro_id =p.get(1);
			if(art_id==art && pro_id==prov)
			{
				lead= p.get(2);
			}
		}
		
		return lead;
	}
	public Integer getLote (Integer art, Integer prov)
	{
		Integer art_id;
		Integer pro_id;
		Integer lead=null;
		for (ArrayList<Integer> p : proveedores)
		{
			art_id =p.get(0);
			pro_id =p.get(1);
			if(art_id==art && pro_id==prov)
			{
				lead= p.get(3);
			}
		}
		
		return lead;
	}
	
	public Integer getLeadTime (Integer art, Integer prov)
	{
		Integer art_id;
		Integer pro_id;
		Integer lead=null;
		for (ArrayList<Integer> p : proveedores)
		{
			art_id =p.get(0);
			pro_id =p.get(1);
			if(art_id==art && pro_id==prov)
			{
				lead= p.get(4);
			}
		}
		
		return lead;
	}
	
	public String getDescripcion(int provId){
		String nombre=this.pDao.getDescripcion(provId);
		return nombre;
	}


}
