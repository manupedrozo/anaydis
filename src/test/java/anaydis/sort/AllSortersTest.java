package anaydis.sort;

import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.data.IntegerDataSetGenerator;
import anaydis.sort.data.StringDataSetGenerator;
import anaydis.sort.provider.SorterProvider;
import anaydis.sort.provider.SorterProviderImpl;
import org.assertj.core.util.Iterables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(Parameterized.class)
public class AllSortersTest extends AbstractSorterTest{

    @Parameter
    public Sorter sorter = null;

    @Parameters(name = "sorter {0}")
    public static List<Object[]> sorters(){
        final SorterProvider provider = new SorterProviderImpl();
        return Arrays.stream(Iterables.toArray(provider.getAllSorters()))
                .map(sorter -> new Object[]{sorter})
                .collect(Collectors.toList());
    }

    @Test
    public void testSorter(){
        testSorter(createIntegerDataSetGenerator(), sorter.getType(), 10);
    }

    @Override
    protected DataSetGenerator<String> createStringDataSetGenerator() {
        return new StringDataSetGenerator();
    }

    @Override
    protected DataSetGenerator<Integer> createIntegerDataSetGenerator() {
        return new IntegerDataSetGenerator();
    }

    @Override
    protected SorterProvider getSorterProvider() {
        return new SorterProviderImpl();
    }
}
