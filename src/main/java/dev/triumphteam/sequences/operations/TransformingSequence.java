package dev.triumphteam.sequences.operations;

import dev.triumphteam.sequences.AbstractSequence;
import dev.triumphteam.sequences.BaseSequence;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.function.Function;

public final class TransformingSequence<T, R> extends AbstractSequence<R> {

    private final BaseSequence<T> sequence;
    private final Function<T, R> transformer;

    public TransformingSequence(@NotNull final BaseSequence<T> sequence, @NotNull final Function<T, R> transformer) {
        this.sequence = sequence;
        this.transformer = transformer;
    }

    @NotNull
    @Override
    public Iterator<R> iterator() {
        return new Iterator<R>() {
            private final Iterator<T> iterator = sequence.iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public R next() {
                return transformer.apply(iterator.next());
            }
        };
    }
}
