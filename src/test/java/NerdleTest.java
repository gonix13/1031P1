import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.stream.Stream;

class NerdleTest {
    private static Nerdle nerdle;

    @BeforeAll
    static void setUp() {
        nerdle = new NerdleStub();
    }


    static Stream<Arguments> entradas(){
        Nerdle.SymbolHint[] resNotMini = new Nerdle.SymbolHint[]{Nerdle.SymbolHint.CORRECT,Nerdle.SymbolHint.CORRECT,
                Nerdle.SymbolHint.CORRECT,Nerdle.SymbolHint.CORRECT,Nerdle.SymbolHint.CORRECT,
                Nerdle.SymbolHint.CORRECT,Nerdle.SymbolHint.CORRECT,Nerdle.SymbolHint.CORRECT};
        Nerdle.SymbolHint[] resMini = new Nerdle.SymbolHint[]{Nerdle.SymbolHint.CORRECT,Nerdle.SymbolHint.CORRECT,
                Nerdle.SymbolHint.CORRECT,Nerdle.SymbolHint.CORRECT,Nerdle.SymbolHint.CORRECT,
                Nerdle.SymbolHint.CORRECT};
        return Stream.of(
                Arguments.of("11+11=22","11+11=22",false,resNotMini), //Correcto
                Arguments.of("9+1=10","9+1=10",true,resMini), //Correcto
                Arguments.of("11+11=22","9+1=10",true,null), //Exception(guess to long)
                Arguments.of("8+1=9","9+1=10",true,null), //Exception(guess to short)
                Arguments.of("9+1=10","8+1=9",true,null), //Exception(solution to short)
                Arguments.of("9+1=10","11+11=22",true,null),//Exception(solution to long)
                Arguments.of("9+1=10","11+11=22",false,null), //Exception(guess to short)
                Arguments.of("11*11=121","11+11=22",false,null), //Exception(guess to long)
                Arguments.of("11+11=22","9+1=10",false,null), //Exception(solution to short)
                Arguments.of("11+11=22","11*11=121",false,null),  //Exception(solution to long)
                Arguments.of("AAAAAAAA","11+11=22",false,null), //Exception(guess is not a number)
                Arguments.of("11+11=22","AAAAAAAA",false,null),  //Exception(solution is not a number)
                Arguments.of(null,"11+11=22",false,null),  //Exception(guess is null)
                Arguments.of("11+11=22",null,false,null),  //Exception(solution is null)
                Arguments.of("11*11=22","11+11=22",false,null), //Exception(guess is mathematically incorrect)
                Arguments.of("11+11=22","11*11=22",false,null)  //Exception(solution is mathematically incorrect)

        );
    }

    @ParameterizedTest
    @MethodSource("entradas")
    void getHints(String guess, String solution, boolean isMini, Nerdle.SymbolHint[] result) {
        assertThat(nerdle.getHints(guess,solution,isMini),is(result));
    }
}