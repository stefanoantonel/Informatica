package modelo;

public class UnidadMedida {
	private Integer id;
	private String descripcion;
	
	public UnidadMedida(Integer i, String d)
	{
		id=i;
		descripcion=d;
	}
	
	public Integer getId()
	{
		return id;
	}
	public String getDesc()
	{
		return descripcion;
	}
}
