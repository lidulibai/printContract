package Token;

import AMounts.Amount;
import Configure.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.math.RoundingMode.FLOOR;

public class TokenCoin
{
    private static final Logger logger = LoggerFactory.getLogger(TokenCoin.class);

    public static TokenCoin YiMiToken = new TokenCoin("YiMiToken");

    private final String name;
    private final long count;
    private final BigInteger factor;
    private final int exp;

    private TokenCoin(String name) {
        this(name, 0);
    }

    private TokenCoin(String name, long count) {
        this.name = name;
        this.count = count;
        this.exp = 18;
        this.factor = BigInteger.TEN.pow(exp);
    }

    public final long getCount() { return count; }

    public final String getName() { return name; }

    public Amount of(BigInteger number) {
        return new Amount(factor.multiply(number));
    }

    public BigDecimal fromAmount(Amount amount) {
        BigDecimal nano = new BigDecimal(amount.getNano());
        return nano.movePointLeft(exp).setScale(exp, FLOOR);
    }
}