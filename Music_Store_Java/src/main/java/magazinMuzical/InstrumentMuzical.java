package magazinMuzical;


/**
 * Am folosit aceasta clasa pentru a stoca date referitoare la instrumentele muzicale ale magazinului
 */
public class InstrumentMuzical{
	private int id;
	private String instrument; 
	private String tipInstrument;
	private String brand;
	private String material;
	private String culoare;
	private double pret;
	public InstrumentMuzical()
	{
		id = 0;
		instrument = null; 
		tipInstrument = null;
		brand = null;
		material = null;
		culoare = null;
		pret = 0;
	}
	/**
	 * Constructorul explicit al claasei InstrumentMuzical
	 * @param id id ul instrumentului, unic
	 * @param instrument denumirea instrumentului (chitara, orga, flaut, toba etc)
	 * @param tipInstrument tipul de instrument(de suflat, de percutie, cu coarde, electric)
	 * @param brand brandul instrumentului
	 * @param material materialul din care e confectrionat instrumentul
	 * @param culoare culoarea instrumentului
	 * @param pret pretul instrumentului
	 */
	public InstrumentMuzical(int id, String instrument, String tipInstrument, String brand, String material, String culoare, double pret)
	{
		this.id = id;
		this.instrument = instrument;
		this.tipInstrument = tipInstrument;
		this.brand = brand;
		this.material = material;
		this.culoare = culoare;
		this.pret = pret;
	}
	public int getId()
	{
		return this.id;
	}
	public String getInstrument()
	{
		return this.instrument;
	}
	public String getTipInstrument()
	{
		return this.tipInstrument;
	}
	public String getBrand()
	{
		return this.brand;
	}
	public String getMaterial()
	{
		return this.material;
	}
	public String getCuloare()
	{
		return this.culoare;
	}
	public double getPret()
	{
		return this.pret;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
