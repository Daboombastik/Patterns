import java.util.function.Supplier;
 import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        var logger = Logger.getAnonymousLogger();

        var typeA = Factory.create(Type.TYPE_A);
        var typeB = Factory.create(Type.TYPE_B);

        logger.info(typeA.getDescription());
        logger.info(typeB.getDescription());
    }
}

interface CommonType{
    String getDescription();
}

class TypeA implements CommonType{
    @Override
    public String getDescription(){
        return "Class TypeA";
    }
}
class TypeB implements CommonType{
    @Override
    public String getDescription(){
        return "Class TypeB";
    }
}

enum Type {
    TYPE_A(TypeA::new),
    TYPE_B(TypeB::new);

    private final Supplier<CommonType> supplier;

    Type(Supplier<CommonType> supplier) {
        this.supplier = supplier;
    }

    public Supplier<CommonType> getSupplier(){
        return this.supplier;
    }
}

class Factory{
    public static CommonType create(Type type){
        return type.getSupplier().get();
    }
}