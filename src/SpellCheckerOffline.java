public class SpellCheckerOffline extends SpellChecker {
    @Override
    public int[] grammarCheck(String str, Language lang) {
        return new int[0];
    }

    @Override
    public int[] spellCheck(String str, Language lang) {
        return new int[0];
    }
}

