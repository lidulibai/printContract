import java.lang.invoke.MethodHandle;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;

import jdk.nashorn.internal.parser.Token;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
           NoSuchMethodException, InvocationTargetException {
        // Integer exitCode = new Executor<>(Print::new).run(Print::init);
        // Integer exitCode = new Executor<>(Token::new).run(Token::create);
        if (args.length > 0) {
            Class<?> tokenClass = Class.forName(args[0]);
            // Executor.ConstructorNoJs f = null;
            // new Executor<>((Executor.ConstructorNoJs)tokenClass.getConstructor(ByteBuffer.class, MethodHandle.class)).run((Executor.Run)tokenClass.getMethod("create").invoke(null, null));
            System.out.println(tokenClass.getMethod("create"));
            new ReflectExecutor(tokenClass.getConstructor(ByteBuffer.class, MethodHandle.class)).run(tokenClass.getMethod("create"));
        }
        // Integer exitCode = new Executor<>((Executor.ConstructorNoJs)tokenClass.newInstance());
        // if (exitCode == null) throw new IllegalStateException("Did not get exit code");
        // if (exitCode != 0) throw new IllegalStateException("Expected exit code 0, got: " + exitCode);
    }
}
