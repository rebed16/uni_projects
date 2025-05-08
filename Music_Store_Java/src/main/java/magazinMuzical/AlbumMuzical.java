package magazinMuzical;


/**
 * Am folosit aceasta clasa pentru a stoca informatii referitoare la albumele muzicale ale magazinului
 */
public class AlbumMuzical{
	private int id;
    private String numeAlbum;
    private String numeArtist;
    private String gen;
    public enum Format{CD, vinil}
    private Format format;
    private double pret;
    public AlbumMuzical()
    {
    	id = 0;
    	numeAlbum = null;
    	numeArtist = null;
    	gen = null;
    	format = null;
    	pret = 0;
    }
    /**
     * Constructorul explicit al clasei AlbumMuzical
     * @param id id ul instrumentului, unic
     * @param numeAlbum numele albumului
     * @param numeArtist numele artistului albumului
     * @param gen genul de muzica(rock, pop, muzica clasica, folk etc)
     * @param format formatul albumului(CD sau vinil)
     * @param pret pretul albumului
     */
    public AlbumMuzical(int id, String numeAlbum, String numeArtist,String gen, Format format, double pret)
    {
    	this.id = id;
    	this.numeAlbum = numeAlbum;
    	this.numeArtist = numeArtist;
    	this.gen = gen;
    	this.format = format;
    	this.pret = pret;
    }
    public int getId()
    {
    	return this.id;
    }
    public String getNumeAlbum()
    {
    	return this.numeAlbum;
    }
    public String getNumeArtist()
    {
    	return this.numeArtist;
    }
    public String getGen()
    {
    	return this.gen;
    }
    public Format getFormat()
    {
    	return this.format;
    }
    public double pret()
    {
    	return this.pret;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
