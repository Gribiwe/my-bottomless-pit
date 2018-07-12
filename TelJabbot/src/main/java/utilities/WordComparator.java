package utilities;

import study.Word;

import java.util.Comparator;

/**
 * Created by nikit on 2018/02/22.
 */
public class WordComparator implements Comparator<Word>{
    @Override
    public int compare(Word o1, Word o2) {
        return o1.getRememberLVL()-o2.getRememberLVL();
    }
}
