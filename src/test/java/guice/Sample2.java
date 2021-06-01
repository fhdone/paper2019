package guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import javax.inject.Singleton;

//https://zhuanlan.zhihu.com/p/32299568
//@ImplementedBy(SimpleHelloPrinter.class)
interface IHelloPrinter {
    void print();
}


class SampleModule extends AbstractModule {
    @Override
    protected void configure() {
        //bind(IHelloPrinter.class).to(SimpleHelloPrinter.class);
        bind(IHelloPrinter.class).to(ComplexHelloPrinter.class);

        bind(IHelloPrinter.class).annotatedWith(Names.named("simple")).to(SimpleHelloPrinter.class);
        bind(IHelloPrinter.class).annotatedWith(Names.named("complex")).to(ComplexHelloPrinter.class);
    }

}

@Singleton
class SimpleHelloPrinter implements IHelloPrinter {
    public void print() {
        System.out.println("Hello, Simple World");
    }
}

@Singleton
class ComplexHelloPrinter implements IHelloPrinter {
    public void print() {
        System.out.println("Hello, Complex World");
    }

}

@Singleton
public class Sample2 {

    @Inject
    @Named("simple")
    private IHelloPrinter simplePrinter;

    @Inject
    @Named("complex")
    private IHelloPrinter complexPrinter;

    @Inject
    private IHelloPrinter printer;

    public void hello() {
        printer.print();

        simplePrinter.print();
        complexPrinter.print();
    }

    public static void main(String[] args) {
//        Injector injector = Guice.createInjector();
//        Sample2 sample = injector.getInstance(Sample2.class);
//        sample.hello();

        Injector injector = Guice.createInjector(new SampleModule());
        Sample2 sample = injector.getInstance(Sample2.class);
        sample.hello();


    }

}
