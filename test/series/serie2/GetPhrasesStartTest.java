package series.serie2;

import org.junit.jupiter.api.Test;

import java.util.*;

import static series.serie2.Iterables_.getPhrasesStart;
import static java.util.Collections.unmodifiableList;

public class GetPhrasesStartTest extends IterablesTest {
    private static final Iterable<String> empty = Collections.emptyList();

    private static final Iterable<String> aRato = unmodifiableList(Arrays.asList("o", "rato", "roeu", "a",
            "rolha", "da", "garrafa", "do", "rei", "da", "russia")),
            aRio = unmodifiableList(Arrays.asList("fui", "ao", "mar", "colher",
                    "cordões", "vim", "do", "mar", "cordões", "colhi")),
            aOriginal = unmodifiableList(Arrays.asList("o", "original", "nunca",
                    "se", "desoriginou", "nem", "nunca", "se",
                    "desoriginalizara")),
            aTigre = unmodifiableList(Arrays.asList("três", "pratos", "de",
                    "trigo", "para", "três", "tristes", "tigres")),
            aMassa = unmodifiableList(Arrays.asList("o", "mação", "amassou", "a", "massa", "com", "o", "maçarico"));

    private static final String strRato = "o rato roeu a rolha da garrafa do rei da russia",
                                   strRio = "fui ao mar colher cordões vim do mar cordões colhi",
                              strOriginal = "o original nunca se desoriginou nem nunca se desoriginalizara",
                                 strTigre = "três pratos de trigo para três tristes tigres",
                                 strMassa = "o mação amassou a massa com o maçarico";

    @Test
    public void getPhrasesStart_onEmptySequence() {
        // None sequences
        Collection<Iterable<String>> seq = new ArrayList<>();
        assertIterableEmpty(getPhrasesStart(seq, "pri"));
        // Four sequences empty
        for (int i = 0; i < 4; ++i)
            seq.add(empty);
        assertIterableEmpty(getPhrasesStart(seq, "pri"));
    }



    @Test
    public void getPhrasesStart_onSequenceOfOneElementSequence() {
        List<Iterable<String>> seq = new ArrayList<>();
        List<String> expected = Collections.singletonList(strRio);

        // One sequence of one element which does not contain the prefix
        seq.add(aRio);
        assertIterableEmpty(getPhrasesStart(seq, "rio"));

        // One sequence of one element containing the prefix
        assertIterableEquals(expected, getPhrasesStart(seq, "fui"));
    }

    @Test
    public void getPhrasesStart_onSequencesWhichNotStarts() {
        List<Iterable<String>> seq = new ArrayList<>(Arrays.asList(empty, aRato, empty, aTigre, empty));
        // One sequence of sequences empty and not empty which does not start with the prefix
         assertIterableEmpty(getPhrasesStart(seq, "original"));
    }

    @Test
    public void getPhrasesStart_onSequencesThatStarts() {
        List<Iterable<String>> seq = new ArrayList<>(Arrays.asList(aRato, aTigre, aOriginal));
        List<String> expected = Arrays.asList(strRato, strOriginal);
        // First and last sequence containing the prefix
        assertIterableEquals(expected, getPhrasesStart(seq, "o"));

        // First sequence containing the prefix
        seq = new ArrayList<>(Arrays.asList(aTigre, empty, empty, aRio, aRato, empty, aOriginal, empty, empty));
        expected = Collections.singletonList(strTigre);
        assertIterableEquals(expected, getPhrasesStart(seq, "três"));

        // First sequence containing the prefix
        seq = new ArrayList<>(Arrays.asList(empty, aTigre, empty, empty, aRio, aRato, empty, aOriginal, empty, empty));
        expected = Collections.singletonList(strTigre);
        assertIterableEquals(expected, getPhrasesStart(seq, "três"));
    }

    @Test
    public void getPhrasesStart_onSeveralSequencesThatStarts() {
        List<Iterable<String>> seq = new ArrayList<>(Arrays.asList(aRato, aRio, aOriginal, aTigre, aMassa));
        List<String> expected = Arrays.asList(strRato, strOriginal, strMassa);
        // Several sequences starting with the prefix
        assertIterableEquals(expected, getPhrasesStart(seq, "o"));
    }
}