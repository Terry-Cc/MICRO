package com.micro.jfxexe.handler.chain;

import com.micro.common.anno.ChainOrder;
import com.micro.common.util.chain.ChainHandler;
import com.micro.common.util.chain.ChainParam;
import com.micro.common.util.other.CommonUtils;
import com.micro.jfxexe.common.SortType;
import com.micro.jfxexe.domain.Note;
import com.micro.jfxexe.domain.SearchParam;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author XiongJiaMin
 * @apiNote 排序
 * @since 2023-01-04 16:12
 **/
@ChainOrder(order = 4, name = "searchNoteChain")
@SuppressWarnings("unused")
public class SortSearchChain extends ChainHandler {

    @Override
    public void handler(ChainParam chainParam) {
        SearchParam searchParam = (SearchParam) chainParam;
        SortType sortType = searchParam.getSortType();
        List<Note> notes = searchParam.getNotes();
        if (CommonUtils.isEmpty(notes)) {
            return;
        }
        if (SortType.TIME_DOWN.equals(sortType)) {
            notes = notes.stream()
                    .sorted((a, b) -> {
                        if (a.getCreatedDate().equals(b.getCreatedDate())) return 0;
                        return a.getCreatedDate().isAfter(b.getCreatedDate()) ? 1 : -1;
                    })
                    .collect(Collectors.toList());
        } else if (SortType.TIME_UP.equals(sortType)) {
            notes = notes.stream()
                    .sorted((a, b) -> {
                        if (a.getCreatedDate().equals(b.getCreatedDate())) return 0;
                        return a.getCreatedDate().isAfter(b.getCreatedDate()) ? -1 : 1;
                    })
                    .collect(Collectors.toList());
        }
        searchParam.setNotes(notes);
    }
}
