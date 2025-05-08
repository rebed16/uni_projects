package administrareMagazinMuzica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import magazinMuzical.AlbumMuzical;

class AlbumMuzicalTest {

	 @Test
	    void testConstructorExplicit() {
	        AlbumMuzical album = new AlbumMuzical(1, "Album Test", "Artist Test", "Rock", AlbumMuzical.Format.CD, 50.0);
	        assertEquals(1, album.getId());
	        assertEquals("Album Test", album.getNumeAlbum());
	        assertEquals("Artist Test", album.getNumeArtist());
	        assertEquals("Rock", album.getGen());
	        assertEquals(AlbumMuzical.Format.CD, album.getFormat());
	        assertEquals(50.0, album.pret(), 0.01);
	    }

	    @Test
	    void testConstructorImplicit() {
	        AlbumMuzical album = new AlbumMuzical();
	        assertEquals(0, album.getId());
	        assertNull(album.getNumeAlbum());
	        assertNull(album.getNumeArtist());
	        assertNull(album.getGen());
	        assertNull(album.getFormat());
	        assertEquals(0.0, album.pret(), 0.01);
	    }
}
