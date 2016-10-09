package io.springbatch.reader;

import java.util.List;
import org.springframework.batch.item.ItemReader;

import java.util.Iterator;

/**
 * Created by willharmer on 09/10/2016.
 */
public class StatelessItemReader implements ItemReader<String> {
    public final Iterator<String> data;

    public StatelessItemReader(List<String> data) {
        this.data = data.iterator();
    }

    @Override
    public String read() throws Exception {
        if(this.data.hasNext()) {
            return this.data.next();
        } else {
            return null;
        }
    }
}
