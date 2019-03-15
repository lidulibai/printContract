import java.io.IOException;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.nio.ByteBuffer;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectExecutor<T> {

    protected static final int PAGE_SIZE = 65536;

    public OutputStream out = System.out;

    protected final ByteBuffer mem;

    protected final T instance;

    protected Integer exitCode;

    public ReflectExecutor(Constructor m) throws InstantiationException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        this(16, m);
    }

    public ReflectExecutor(int maxMemPages, Constructor m) throws InstantiationException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType createType = MethodType.methodType(int.class, int.class, int.class, int.class, int.class);
        // try {
            mem = ByteBuffer.allocateDirect(maxMemPages * PAGE_SIZE);
            instance = (T)m.newInstance(mem, lookup.bind(this, "createToken", createType));
        // } catch(InstantiationException e) {
            // e.printStackTrace();
        // } catch(NoSuchMethodException e) {
            // e.printStackTrace();
        // } catch(IllegalAccessException e) {
            // e.printStackTrace();
        // } catch(InvocationTargetException e) {
            // e.printStackTrace();
        // } catch(Exception e){
            // e.printStackTrace();
        // }
    }

    public Integer run(Method run) throws IllegalAccessException, InvocationTargetException {
        run.invoke(instance);
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
