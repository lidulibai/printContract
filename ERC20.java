import java.math.BigDecimal;

public interface ERC20 {
    String name();
    String symbol();
    int decimal();
    long totalSupply();
    BigDecimal balanceOf(String address);
    boolean transfer(String addressTo, BigDecimal value);
    boolean approve(String spender, BigDecimal value);
    boolean transferFrom(String addressFrom, BigDecimal value);
    BigDecimal allowance(String addressOwner, String spender);
}
