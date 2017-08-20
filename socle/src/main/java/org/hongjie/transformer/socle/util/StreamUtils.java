package org.hongjie.transformer.socle.util;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtils {

    /**
     *  Create stream from iterator, not paralle
     * @param sourceIterator
     * @param <T>
     * @return Stream
     */
    public static <T> Stream<T> asStream(Iterator<T> sourceIterator) {
        return asStream(sourceIterator, false);
    }

    /**
     * Create stream from iterator
     * @param sourceIterator
     * @param parallel
     * @param <T>
     * @return Stream
     */
    public static <T> Stream<T> asStream(Iterator<T> sourceIterator, boolean parallel) {
        Iterable<T> iterable = () -> sourceIterator;
        return StreamSupport.stream(iterable.spliterator(), parallel);
    }

}