package administrareMagazinMuzica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import magazinMuzical.InstrumentMuzical;

class InstrumentMuzicalTest {

	@Test
    void testConstructorExplicit() {
        InstrumentMuzical instrument = new InstrumentMuzical(1, "Chitara", "Cu coarde", "Fender", "Lemn", "Maro", 1500.0);
        assertEquals(1, instrument.getId());
        assertEquals("Chitara", instrument.getInstrument());
        assertEquals("Cu coarde", instrument.getTipInstrument());
        assertEquals("Fender", instrument.getBrand());
        assertEquals("Lemn", instrument.getMaterial());
        assertEquals("Maro", instrument.getCuloare());
        assertEquals(1500.0, instrument.getPret(), 0.01);
    }

    @Test
    void testConstructorImplicit() {
        InstrumentMuzical instrument = new InstrumentMuzical();
        assertEquals(0, instrument.getId());
        assertNull(instrument.getInstrument());
        assertNull(instrument.getTipInstrument());
        assertNull(instrument.getBrand());
        assertNull(instrument.getMaterial());
        assertNull(instrument.getCuloare());
        assertEquals(0.0, instrument.getPret(), 0.01);
    }


}
