package uk.co.lalev.enigmalabs.enigmalab;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnigmalabApplicationTests {

	@Test
	void testCaesarCipher() {

		assertThrows(CipherException.class, ()->new CaesarCipher(27));
		assertThrows(CipherException.class, ()->new CaesarCipher(-27));
		assertDoesNotThrow(()->new CaesarCipher(0));
		assertDoesNotThrow(()->new CaesarCipher(26));
		assertDoesNotThrow(()->new CaesarCipher(26));


		CaesarCipher c = new CaesarCipher(4);

		assertEquals( "EEEEE", c.encrypt("AAAAA"));
		assertEquals( "GEIWEV", c.encrypt("CAESAR"));
		assertEquals("AAAAA", c.encrypt("WWWWW"));

		assertEquals("AAAAA", c.decrypt("EEEEE"));
		assertEquals("CAESAR", c.decrypt("GEIWEV"));
		assertEquals("WWWWW", c.decrypt("AAAAA"));

		c = new CaesarCipher(12);
		assertEquals( "MMMMM", c.encrypt("AAAAA"));
		assertEquals( "OMQEMD", c.encrypt("CAESAR"));
		assertEquals("IIIII", c.encrypt("WWWWW"));

		assertEquals("AAAAA", c.decrypt("MMMMM"));
		assertEquals("CAESAR", c.decrypt("OMQEMD"));
		assertEquals("WWWWW", c.decrypt("IIIII"));

		c = new CaesarCipher(22);

		assertEquals( "WWWWW", c.encrypt("AAAAA"));
		assertEquals( "YWAOWN", c.encrypt("CAESAR"));
		assertEquals("SSSSS", c.encrypt("WWWWW"));

		assertEquals("AAAAA", c.decrypt("WWWWW"));
		assertEquals("CAESAR", c.decrypt("YWAOWN"));
		assertEquals("WWWWW", c.decrypt("SSSSS"));

		c = new CaesarCipher(-4);

		assertEquals( "WWWWW", c.encrypt("AAAAA"));
		assertEquals( "YWAOWN", c.encrypt("CAESAR"));
		assertEquals("SSSSS", c.encrypt("WWWWW"));

		assertEquals("AAAAA", c.decrypt("WWWWW"));
		assertEquals("CAESAR", c.decrypt("YWAOWN"));
		assertEquals("WWWWW", c.decrypt("SSSSS"));

		c = new CaesarCipher(-20);

		assertEquals( "GGGGG", c.encrypt("AAAAA"));
		assertEquals( "IGKYGX", c.encrypt("CAESAR"));
		assertEquals("CCCCC", c.encrypt("WWWWW"));

		assertEquals("AAAAA", c.decrypt("GGGGG"));
		assertEquals("CAESAR", c.decrypt("IGKYGX"));
		assertEquals("WWWWW", c.decrypt("CCCCC"));
	}

	@Test
	void TestFrequencyCounter() {
		var map = FreqCounter.count("AAAA");
		assertEquals(4, map.get('A'));
		assertEquals(0, map.get('B'));
		assertEquals(0, map.get('C'));
		assertEquals(0, map.get('D'));
	}

}
