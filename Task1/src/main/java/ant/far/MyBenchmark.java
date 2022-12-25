package ant.far;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

public class MyBenchmark {

    @State(Scope.Benchmark)
    public static class MyState {
        private TreeSet<Integer> treeSet;
        private LinkedHashSet<Integer> linkedHashSet;
        private HashSet<Integer> hashSet;
        //private int[] elements;
        Random random;
        int MAX = 1000000;

        @Setup
        public void setup() {
            linkedHashSet = new LinkedHashSet<Integer>();
            treeSet = new TreeSet<Integer>();
            hashSet = new HashSet<Integer>();
            random = new Random();
        }
    }

    // @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    // @Warmup(iterations = 5)
    // @Measurement(iterations = 10)
    // @Fork(value = 1, warmups = 1)
    // public void insertIntoLinkedHashSet(MyState state) {
    //     for (int i=0; i<state.MAX; i++) {
    //         state.linkedHashSet.add(state.random.nextInt(state.MAX));
    //     }
    // }

    // @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    // @Warmup(iterations = 5)
    // @Measurement(iterations = 10)
    // @Fork(value = 1, warmups = 1)
    // public void sortLinkedHashSet(MyState state, Blackhole blackhole) {
    //   blackhole.consume(state.linkedHashSet.stream().sorted());
    // }

    // @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    // @Warmup(iterations = 5)
    // @Measurement(iterations = 10)
    // @Fork(value = 1, warmups = 1)
    // public boolean searchInLinkedHashSet(MyState state) {
    //     return state.linkedHashSet.contains(state.random.nextInt(state.MAX));
    // }



    @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 5)
    @Measurement(iterations = 10)
    @Fork(value = 1, warmups = 1)
    public void insertIntoTreeSet(MyState state) {
        for (int i=0; i<state.MAX; i++) {
            state.treeSet.add(state.random.nextInt(state.MAX));
        }
    }


    @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 5)
    @Measurement(iterations = 10)
    @Fork(value = 1, warmups = 1)
    public void sortTreeSet(MyState state, Blackhole blackhole) {
           blackhole.consume(state.treeSet.descendingSet());
    }

    // @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    // @Warmup(iterations = 5)
    // @Measurement(iterations = 10)
    // @Fork(value = 1, warmups = 1)
    // public boolean searchInTreeSet(MyState state) {
    //     return state.treeSet.contains(state.random.nextInt(state.MAX));
    // }
 
    // @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    // @Warmup(iterations = 5)
    // @Measurement(iterations = 10)
    // @Fork(value = 1, warmups = 1)
    // public void insertIntoHashSet(MyState state) {
    //     for (int i=0; i<state.MAX; i++) {
    //         state.hashSet.add(state.random.nextInt(state.MAX));
    //     }
    // }

    // @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    // @Warmup(iterations = 5)
    // @Measurement(iterations = 10)
    // @Fork(value = 1, warmups = 1)
    // public void sortHashSet(MyState state, Blackhole blackhole) {
    //   blackhole.consume(state.hashSet.stream().sorted());
    // }
  
    // @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.SECONDS)
    // @Warmup(iterations = 5)
    // @Measurement(iterations = 10)
    // @Fork(value = 1, warmups = 1)
    // public boolean searchInHashSet(MyState state) {
    //     return state.hashSet.contains(state.random.nextInt(state.MAX));
    // }
    
}