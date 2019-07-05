package reuse;

public class FilterProcessor {
    public static void main(String[] args) {
        String str = "Hello World!";
        WaveForm wf = new WaveForm();
        Apply.process(new Lower(), str);
        Apply.process(new Upper(), str);
        Apply.process(new FilterAdapter(new LowPass(1)), wf);
        Apply.process(new FilterAdapter(new HighPass(2)), wf);
    }
}
class Apply {
    static void process(Processor p, Object o) {
        System.err.println("Use " + p.name());
        System.err.println(p.process(o));
    }
}
interface Processor {
    String name();
    Object process(Object input);
}
abstract class StringProcessor implements Processor {
    @Override
    public String name() {
        return getClass().getSimpleName();
    }
    public abstract String process(Object input);
}
class Upper extends StringProcessor {
    @Override
    public String process(Object input) {
        return ((String) input).toUpperCase();
    }
}
class Lower extends StringProcessor {
    @Override
    public String process(Object input) {
        return ((String) input).toLowerCase();
    }
}
class WaveForm {
    private static int count;
    private final int id = count++;

    @Override
    public String toString() {
        return "WaveForm " + id;
    }
}
class Filter {
    String name() {
        return getClass().getSimpleName();
    }
    WaveForm process(WaveForm input) {
        return input;
    }
}
class LowPass extends Filter {
    private int cutoff;
    public LowPass(int cutoff) {
        this.cutoff = cutoff;
    }

    @Override
    WaveForm process(WaveForm input) {
        return input;
    }
}
class HighPass extends Filter {
    private int cutoff;
    public HighPass(int cutoff) {
        this.cutoff = cutoff;
    }

    @Override
    WaveForm process(WaveForm input) {
        return input;
    }
}
class FilterAdapter implements Processor {
    private Filter filter;
    public FilterAdapter(Filter filter) {
        this.filter = filter;
    }
    @Override
    public String name() {
        return filter.name();
    }

    @Override
    public WaveForm process(Object input) {
        return filter.process((WaveForm) input);
    }
}














