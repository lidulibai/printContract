import java.io.IOException;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.nio.ByteBuffer;
import java.lang.reflect.Method;

public class Executor<T> {
    @FunctionalInterface
    public interface ConstructorNoJs<T> {
        T create(ByteBuffer mem, MethodHandle println);
    }

    @FunctionalInterface
    public interface Run<T> {
        void run(T instance);
    }

    protected static final int PAGE_SIZE = 65536;

    public OutputStream out = System.out;

    protected final ByteBuffer mem;

    protected final T instance;

    protected Integer exitCode;

    public Executor(ConstructorNoJs<T> constructor) {
        this(16, constructor);
    }

    public Executor(int maxMemPages, ConstructorNoJs<T> constructor) {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        // MethodType methodType = MethodType.methodType(int.class, int.class, int.class);
        MethodType createType = MethodType.methodType(int.class, int.class, int.class, int.class, int.class);
        try {
            mem = ByteBuffer.allocateDirect(maxMemPages * PAGE_SIZE);
            // instance = constructor.create(mem, lookup.bind(this, "println", methodType));
            instance = constructor.create(mem, lookup.bind(this, "createToken", createType));
        } catch(NoSuchMethodException|IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }

    public Integer run(Run<T> run) {
        run.run(instance);
        return exitCode;
    }

    protected void wasmWrite(long fd, byte[] bytes) {
        if (fd != 2) throw new UnsupportedOperationException("Only fd 2 support on write, got " + fd);
        try {
            String result = new String(bytes);
            out.write(result.trim().getBytes());
        } catch(IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected int createToken(int a, int b, int c, int d) {
        System.out.println("createToken");
        RC20Coin coin = new RC20Coin();
        coin.setName(new String(getBytes(a, new byte[c - a])).trim());
        coin.setNum(b);
        coin.setSymbol(new String(getBytes(c, new byte[16])).trim());
        coin.setPrecision(d);
        System.out.print(coin);
        return 0;
    }

    protected int println(int sp, int result) {
        wasmWrite(2, getBytes(sp, new byte[65536 - sp]));
        return result;
    }

    protected byte[] getBytes(int offset, byte[] bytes) {
        ByteBuffer buf = mem.duplicate();
        buf.position(offset);
        buf.get(bytes);
        return bytes;
    }
}
