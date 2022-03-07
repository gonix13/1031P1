public class NerdleStub extends Nerdle{
    @Override
    public boolean validateExpression(String expression) {
        if (expression.equals("AAAAAAAA") || expression.equals("11*11=22")) return false;
        return true;
    }
}
