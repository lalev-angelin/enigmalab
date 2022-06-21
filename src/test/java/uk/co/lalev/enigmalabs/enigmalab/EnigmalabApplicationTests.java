package uk.co.lalev.enigmalabs.enigmalab;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
		for (int i=1; i<26; i++) {
			assertEquals(0, map.get((char)('A'+i)));
		}
		assertEquals(4, map.get('A'));
		assertEquals(0, map.get('C'));
		assertEquals(0, map.get('D'));
		assertEquals(0, map.get('F'));
		assertEquals(0, map.get('Q'));
		assertEquals(0, map.get('Z'));

		map = FreqCounter.countEveryNth("ABCABCABCABC", 1);
		assertEquals(4, map.get('A'));
		assertEquals(4, map.get('B'));
		assertEquals(4, map.get('C'));
		assertEquals(0, map.get('D'));

		map = FreqCounter.countEveryNth("ABCABCABCABC", 2);
		assertEquals(2, map.get('A'));
		assertEquals(2, map.get('B'));
		assertEquals(2, map.get('C'));
		assertEquals(0, map.get('D'));

		map = FreqCounter.countEveryNth("ABCABCABCABC", 3);
		assertEquals(4, map.get('A'));
		assertEquals(0, map.get('B'));
		assertEquals(0, map.get('C'));
		assertEquals(0, map.get('D'));
	}

	@Test
	void TestViginereCipher() {
		VigenereCipher v = new VigenereCipher("ABCDEF");
		assertEquals("ABCDEFABCDEFABCDEF", v.encrypt("AAAAAAAAAAAAAAAAAA"));
		assertEquals("ZABCDEZABCDE", v.encrypt("ZZZZZZZZZZZZ"));
		assertEquals("ZABCDEZABCDE", v.encrypt("ZZZZZZZZZZZZ"));
		v = new VigenereCipher("VWXYZ");
		assertEquals("WXYZAWXYZAWX", v.encrypt("BBBBBBBBBBBB"));


		v = new VigenereCipher("ABCDEF");
		assertEquals("AAAAAAAAAAAAAAAAAA", v.decrypt("ABCDEFABCDEFABCDEF"));
		assertEquals("ZZZZZZZZZZZZ", v.decrypt("ZABCDEZABCDE"));
		assertEquals("ZZZZZZZZZZZZ", v.decrypt("ZABCDEZABCDE"));
		v = new VigenereCipher("VWXYZ");
		assertEquals("BBBBBBBBBBBB", v.decrypt("WXYZAWXYZAWX"));
	}

	@Test
	void TestDifferentialCounter() {
		DifferentialCounter c = new DifferentialCounter(1, 7);
		var result = c.count("ABCDEFGABCDEFGABCDEFGABCDEFG");
		assertEquals(4, result.get(1).get('A'));
		assertEquals(4, result.get(1).get('B'));
		assertEquals(4, result.get(1).get('C'));
		assertEquals(4, result.get(1).get('D'));

		assertEquals(4, result.get(7).get('A'));
		assertEquals(0, result.get(7).get('B'));
		assertEquals(0, result.get(7).get('C'));
		assertEquals(0, result.get(7).get('D'));
		assertEquals(0, result.get(7).get('Z'));
	}

	@Test
	void TestChiSquaredAnalyser() {
		ChiSquaredAnalyser c = new ChiSquaredAnalyser();
		String s = "THEENIGMAMACHINEWASINVENTEDBYGERMANENGINEERARTHURSCHERBIUSATTHEENDOFWORLDWARIT" +
				"HISWASUNKNOWNUNTILWHENAPAPERBYKARLDELEEUWWASFOUNDTHATDESCRIBEDINDETAILSCHERBIUSCHANGESTHEGERMA" +
				"NFIRMSCHERBIUSRITTERCOFOUNDEDBYSCHERBIUSPATENTEDIDEASFORACIPHERMACHINEINANDBEGANMARKETINGTHEFINIS" +
				"HEDPRODUCTUNDERTHEBRANDNAMEENIGMAININITIALLYTARGETEDATCOMMERCIALMARKETSEARLYMODELSWEREUSEDCOMMERCIAL" +
				"LYFROMTHEEARLYSANDADOPTEDBYMILITARYANDGOVERNMENTSERVICESOFSEVERALCOUNTRIESMOSTNOTABLYNAZIGERMANYBEFO" +
				"REANDDURINGWORLDWARIISEVERALDIFFERENTENIGMAMODELSWEREPRODUCEDBUTTHEGERMANMILITARYMODELSHAVINGAPLUGBOARD" +
				"WERETHEMOSTCOMPLEXJAPANESEANDITALIANMODELSWEREALSOINUSEWITHITSADOPTIONINSLIGHTLYMODIFIEDFORMBYTHEGERMANN" +
				"AVYINANDTHEGERMANARMYANDAIRFORCESOONAFTERTHENAMEENIGMABECAMEWIDELYKNOWNINMILITARYCIRCLESPREWARGERMANMILITA" +
				"RYPLANNINGEMPHASIZEDFASTMOBILEFORCESANDTACTICSLATERKNOWNASBLITZKRIEGWHICHDEPENDONRADIOCOMMUNICATIONFORCOMMA" +
				"NDANDCOORDINATIONSINCEADVERSARIESWOULDLIKELYINTERCEPTRADIOSIGNALSMESSAGESHADTOBEPROTECTEDWITHSECUREENCIPHERME" +
				"NTCOMPACTANDEASILYPORTABLETHEENIGMAMACHINEFILLEDTHATNEED";
		s = s + "TOFACILITATEMAKINGTHECATALOGANDCALLEDITACYCLOMETERTHISCOMPRISEDTWOSETSOFROTORSCONNECTEDBYWIRESTHROUGHWHICHELECTRICCURRENTCOULDRUNROTORNINTHESECONDSETWASTHREELETTERSOUTOFPHASEWITHRESPECTTOROTORNINTHEFIRSTSETWHEREASROTORSLANDMINTHESECONDSETWEREALWAYSSETTHESAMEWAYASROTORSLANDMINTHEFIRSTSETPREPARATIONOFTHISCATALOGUSINGTHECYCLOMETERWASSAIDREJEWSKILABORIOUSANDTOOKOVERAYEARBUTWHENITWASREADYOBTAININGDAILYKEYSWASAQUESTIONOFSOMEFIFTEENMINUTESHOWEVERONNOVEMBERTHEGERMANSCHANGEDTHEENIGMAREFLECTORNECESSITATINGTHEPRODUCTIONOFANEWCATALOGATASKWHICHSAYSREJEWSKICONSUMEDONACCOUNTOFOURGREATEREXPERIENCEPROBABLYSOMEWHATLESSTHANAYEARSTIME";
		c.computeChiSquare(s, 1);

		for (int i=1; i<15; i++) {
			c.computeChiSquare("GLAWAMCENQWUUMJWJEOAAZAFGIZTLKAJZEJWAKEFRINSEXDMEWYZRVXAHWWLGLAWAHKXJSNDQAWJVXDAFAWKHRGFBAJMAXEDJLAFNTWHRVXQXENDQIHWRYSONWBGHRZLUEPVRWYJVFAVVRZWGEEDFGDWEFEMFGDSAKAKGLAYRVISAJEJZWYZRVXAHWNAGXAJPSBGHRZWQFUKPLAJOMQKCEPWAXAVVHASFJKJNGEHUINENGDAAIEFNRZTRKWFZENCRXEFTXDWSMJAFLAVCVKVHGPMAHAJGLATEEJVAEIWRREYZEEFVRELVEHDLXWJTIPWQEPUBQIWEGESYQWJXIPKRENDLQKVRPOORVAMFIZUBQIWEGESYPUXESILUIASEPUKNRZSQSLLRHXQZMHAGENQNRZYBZAJAQAFGWAJIMYWFSBKRZAJNPYGHRPJVIOEBWPFBXWTYCJSMMCWEQWFLFAXBVASAHZMEMJYJSNDQAWJVMOWIINSYHEXSINWAXAFVKISZSZWYWSWEILJBHQURHXMGXDWTINENRIAYMPSECIGQIHKUERAAKWHYYCTBENVJINWGLAEBWPUBQLDRBFSCEJWFIWFQMPSYMWFZSZWYWSWEIWDFSEFHWAOVXDAGWWVBTPABREFFPEYUXHQZSZASMAVSSNEOCPZRKAJZEJFNZUAAEJVGLAYRVISAENELEJVNMNXBVYWFSKFNJPWEXDWAEIWRREYZEXWPEIWJMZWYCGFBAJAAQEDVXWJLGEJPPAKCVAONVCWEQWFZMHAGENQCPWFAMJYRQLZNWERRHBSFXIGOMHWSSNURWWFQXWUGMYKYEPWEOJGJRWKOPELMONARKSZVGDVRTAFQSJJNHEGPSIEHREUNXEGAJKJPSIENRZSAHYGBVZAAEPABROAAGASQZAJFENARWSGHPZDVOADLMJLRVYWCXNSQMKKVKJSYWIWFWWYRWDSQXKTRTNGGIYLRHSAGLOWPYNWRRYACLAJZIJLPSIHNGPSAHASFMHQCSNLNFHWGLAWAMCENQWUUMJWSMHDRHPZNXJWRHPGSEYAYMPSGIISXMJYGLAUNXWDBKWFQGWDYIZAGEYQPPKERXAJGLEKPSIHEMOWQXSGFIPKBJNGGSNKPSJFRGPWQFUOVVAKGLNGHKDOUMYZRPAUGVEUPYNJRRPUBYHVEYJJBXKJAMJLUIOWPSJVFIPONWPZEIADRXPWEWKMGSBHUEOWJMPZEIOHRGPLBVKLBVJAAXDWSMNKGWALJLAJREOJBXKJFPWFQQEFGLAKRGKFQWALJINWNPSSLWOWGXDWFEIWJEUSFVKLBVODNRZEVRPZRJEJFXOWGTNWCENSGMKFBJPZVWYSGEHGTYOAAKPZRGUUYSIWGINONWOSVHNWWISKXMHSOSNABYOSAHPGBOKNRVWQRENTHXSZRRELJEOJREZQBFPSVREFTHWAYCGWLWSSFEMMRWPABRKXFSIWSMBLRIJEVRQLRWDGJIRWESJFBZAEOINLUICWEQWFFGDSAKAVGLAWAMCENVAXYIYLBVJWPIOKVXWLVRCLUILJBHQUGMKFBJWFRAYSGEHGTEPSFOSZVGDKNCOJRNAOFOEUBROMZIZGAEYUBYJLBJKMEKNWNXAJRBLWEMAFPILJBFWTYCOGZISZNXHWFWPZNRWQRENKGMIW", i);
		};
	}

}
